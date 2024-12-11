package io.github.mbenincasa.javarestclient.client;

import io.github.mbenincasa.javarestclient.exception.RestClientException;
import io.github.mbenincasa.javarestclient.http.*;
import io.github.mbenincasa.javarestclient.support.HeadersBuilder;
import io.github.mbenincasa.javarestclient.support.RestBodyHandler;
import io.github.mbenincasa.javarestclient.support.RestRequestHeaders;
import io.github.mbenincasa.javarestclient.support.RestRequestUri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class DefaultRestClient implements RestClient {

    private static final Logger logger = LoggerFactory.getLogger(DefaultRestClient.class);

    @Override
    public RestClientRequestSpec<?> get() {
        logger.info("Creating GET request");
        return new DefaultRestClientRequest(HttpMethod.GET);
    }

    @Override
    public RestClientRequestBodySpec post() {
        logger.info("Creating POST request");
        return new DefaultRestClientRequest(HttpMethod.POST);
    }

    @Override
    public RestClientRequestBodySpec put() {
        logger.info("Creating PUT request");
        return new DefaultRestClientRequest(HttpMethod.PUT);
    }

    @Override
    public RestClientRequestBodySpec patch() {
        logger.info("Creating PATCH request");
        return new DefaultRestClientRequest(HttpMethod.PATCH);
    }

    @Override
    public RestClientRequestSpec<?> delete() {
        logger.info("Creating DELETE request");
        return new DefaultRestClientRequest(HttpMethod.DELETE);
    }

    @Override
    public RestClientRequestSpec<?> head() {
        logger.info("Creating HEAD request");
        return new DefaultRestClientRequest(HttpMethod.HEAD);
    }

    @Override
    public RestClientRequestSpec<?> options() {
        logger.info("Creating OPTIONS request");
        return new DefaultRestClientRequest(HttpMethod.OPTIONS);
    }

    private static class DefaultRestClientRequest implements RestClientRequestBodySpec {

        private URI uri;
        private HttpHeaders httpHeaders;
        private final HttpMethod httpMethod;
        private Object body;

        private DefaultRestClientRequest(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            logger.trace("Initialized request with method: {}", httpMethod);
        }

        @Override
        public RestClientRequestBodySpec uri(RestRequestUri r) {
            this.uri = r.getUri();
            logger.debug("Set URI: {}", this.uri);
            return this;
        }

        @Override
        public RestClientRequestBodySpec headers(RestRequestHeaders r) {
            this.httpHeaders = r.getHeaders();
            logger.debug("Set headers: {}", this.httpHeaders);
            return this;
        }

        @Override
        public RestClientRequestBodySpec body(Object body) {
            this.body = body;
            logger.debug("Set request body: {}", body);
            return this;
        }

        @Override
        public RestClientResponseSpec retrieve() throws RestClientException {
            logger.info("Executing request with method: {}, URI: {}", this.httpMethod, this.uri);
            try {
                if (this.httpHeaders == null) {
                    logger.trace("Headers not provided, using default headers");
                    this.httpHeaders = HeadersBuilder.create().build().getHeaders();
                }

                if (this.uri == null) {
                    logger.error("URI is null");
                    throw new Exception("The uri cannot be null. Invoke the uri() method");
                }

                URL url = this.uri.toURL();
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (this.httpMethod.equals(HttpMethod.PATCH)) {
                    connection.setRequestMethod(HttpMethod.PUT.name());
                    this.httpHeaders.add("X-HTTP-Method-Override", HttpMethod.PATCH.name());
                } else {
                    connection.setRequestMethod(this.httpMethod.name());
                }

                Iterator<HttpHeader> headersIterator = this.httpHeaders.getAll();
                while (headersIterator.hasNext()) {
                    HttpHeader header = headersIterator.next();
                    logger.trace("Adding header: {}: {}", header.getName(), header.getValue());
                    connection.setRequestProperty(header.getName(), header.getValue());
                }
                if (this.body != null) {
                    connection.setDoOutput(true);
                    byte[] requestBody = RestBodyHandler.serialize(this.body, MediaType.get(this.httpHeaders.get("Content-Type")));
                    logger.debug("Serialized request body: {}", new String(requestBody));
                    connection.getOutputStream().write(requestBody);
                }
                connection.connect();

                return new DefaultRestClientResponse(connection);
            } catch (Exception e) {
                logger.error("Error during request execution: {}", e.getMessage());
                throw new RestClientException(e.getMessage(), e.getCause());
            }
        }

    }

    private static class DefaultRestClientResponse implements RestClientResponseSpec {

        private final HttpStatus status;
        private final HttpHeaders headers;
        private final byte[] body;

        private DefaultRestClientResponse(HttpURLConnection connection) throws RestClientException {
            logger.info("Processing response");
            try {
                this.status = HttpStatus.fromValue(connection.getResponseCode());
                this.headers = setHttpHeaders(connection);
                this.body = readResponseBody(connection);
                logger.debug("Response status: {}", this.status);
                logger.trace("Response headers: {}", this.headers);
            } catch (Exception e) {
                logger.error("Error processing response: {}", e.getMessage());
                throw new RestClientException("Error processing response: " + e.getMessage(), e);
            } finally {
                connection.disconnect();
                logger.trace("Connection closed");
            }
        }

        @Override
        public HttpStatus getStatus() {
            return this.status;
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.headers;
        }

        @Override
        public <T> T getBody(Class<T> bodyType) throws RestClientException {
            try {
                T deserializedBody = RestBodyHandler.deserialize(this.body, bodyType, MediaType.get(this.getHeaders().get("Content-Type")));
                logger.debug("Deserialized response body: {}", deserializedBody);
                return deserializedBody;
            } catch (Exception e) {
                logger.error("Error deserializing response body: {}", e.getMessage());
                throw new RestClientException(e.getMessage(), e.getCause());
            }
        }

        @Override
        public <T> List<T> getBodyAsList(Class<T> bodyType) throws RestClientException {
            try {
                List<T> deserializedList = RestBodyHandler.deserializeList(this.body, bodyType, MediaType.get(this.getHeaders().get("Content-Type")));
                logger.debug("Deserialized response body as list: {}", deserializedList);
                return deserializedList;
            } catch (Exception e) {
                logger.error("Error deserializing response body as list: {}", e.getMessage());
                throw new RestClientException(e.getMessage(), e.getCause());
            }
        }

        @Override
        public String getBodyAsString() {
            String bodyAsString = RestBodyHandler.deserialize(this.body);
            logger.trace("Response body as string: {}", bodyAsString);
            return bodyAsString;
        }

        @Override
        public byte[] getBodyAsRaw() {
            logger.trace("Response body as raw bytes");
            return this.body;
        }

        private HttpHeaders setHttpHeaders(HttpURLConnection connection) {
            HttpHeaders headers = new HttpHeaders(connection.getHeaderFields());
            logger.debug("Extracted response headers: {}", headers);
            return headers;
        }

        private byte[] readResponseBody(HttpURLConnection connection) throws IOException {
            try (InputStream inputStream = connection.getInputStream()) {
                logger.trace("Reading response body from input stream");
                return inputStream.readAllBytes();
            } catch (IOException e) {
                logger.error("Error reading response body: {}", e.getMessage());
                return connection.getErrorStream().readAllBytes();
            }
        }
    }
}
