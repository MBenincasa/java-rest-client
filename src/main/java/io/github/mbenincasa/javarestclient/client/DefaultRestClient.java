package io.github.mbenincasa.javarestclient.client;

import io.github.mbenincasa.javarestclient.exception.RestClientException;
import io.github.mbenincasa.javarestclient.http.*;
import io.github.mbenincasa.javarestclient.support.HeadersBuilder;
import io.github.mbenincasa.javarestclient.support.RestBodyHandler;
import io.github.mbenincasa.javarestclient.support.RestRequestHeaders;
import io.github.mbenincasa.javarestclient.support.RestRequestUri;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;

public class DefaultRestClient implements RestClient {

    @Override
    public RestClientRequestSpec<?> get() {
        return new DefaultRestClientRequest(HttpMethod.GET);
    }

    @Override
    public RestClientRequestBodySpec post() {
        return new DefaultRestClientRequest(HttpMethod.POST);
    }

    @Override
    public RestClientRequestBodySpec put() {
        return new DefaultRestClientRequest(HttpMethod.PUT);
    }

    @Override
    public RestClientRequestBodySpec patch() {
        return new DefaultRestClientRequest(HttpMethod.PATCH);
    }

    @Override
    public RestClientRequestSpec<?> delete() {
        return new DefaultRestClientRequest(HttpMethod.DELETE);
    }

    @Override
    public RestClientRequestSpec<?> head() {
        return new DefaultRestClientRequest(HttpMethod.HEAD);
    }

    @Override
    public RestClientRequestSpec<?> options() {
        return new DefaultRestClientRequest(HttpMethod.OPTIONS);
    }

    private static class DefaultRestClientRequest implements RestClientRequestBodySpec {

        private URI uri;
        private HttpHeaders httpHeaders;
        private final HttpMethod httpMethod;
        private Object body;

        private DefaultRestClientRequest(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
        }

        @Override
        public RestClientRequestBodySpec uri(RestRequestUri r) {
            this.uri = r.getUri();
            return this;
        }

        @Override
        public RestClientRequestBodySpec headers(RestRequestHeaders r) {
            this.httpHeaders = r.getHeaders();
            return this;
        }

        @Override
        public RestClientRequestBodySpec body(Object body) {
            this.body = body;
            return this;
        }

        @Override
        public RestClientResponseSpec retrieve() throws RestClientException {
            try {
                if (this.httpHeaders == null) {
                    this.httpHeaders = HeadersBuilder.create().build().getHeaders();
                }

                if (this.uri == null) {
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
                    connection.setRequestProperty(header.getName(), header.getValue());
                }
                if(this.body != null) {
                    connection.setDoOutput(true);
                    byte[] requestBody = RestBodyHandler.serialize(this.body, MediaType.get(this.httpHeaders.get("Content-Type")));
                    connection.getOutputStream().write(requestBody);
                }
                connection.connect();

                return new DefaultRestClientResponse(connection);
            } catch (Exception e) {
                throw new RestClientException(e.getMessage(), e.getCause());
            }
        }

    }

    private static class DefaultRestClientResponse implements RestClientResponseSpec {

        private final HttpStatus status;
        private final HttpHeaders headers;
        private final byte[] body;

        private DefaultRestClientResponse(HttpURLConnection connection) throws RestClientException {
            try {
                this.status = HttpStatus.fromValue(connection.getResponseCode());
                this.body = connection.getInputStream().readAllBytes();
                this.headers = this.setHttpHeaders(connection);
            } catch (Exception e) {
                throw new RestClientException(e.getMessage(), e.getCause());
            } finally {
                connection.disconnect();
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
                return RestBodyHandler.deserialize(this.body, bodyType, MediaType.get(this.getHeaders().get("Content-Type")));
            } catch (Exception e) {
                throw new RestClientException(e.getMessage(), e.getCause());
            }
        }

        @Override
        public String getBodyAsString() {
            return RestBodyHandler.deserialize(this.body);
        }

        private HttpHeaders setHttpHeaders(HttpURLConnection connection) {
            return new HttpHeaders(connection.getHeaderFields());
        }
    }
}
