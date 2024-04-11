package io.github.mbenincasa.client;

import io.github.mbenincasa.http.*;
import io.github.mbenincasa.support.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;

public class DefaultRestClient implements RestClient {

    @Override
    public RestClientRequestSpec get() {
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
    public RestClientRequestSpec delete() {
        return new DefaultRestClientRequest(HttpMethod.DELETE);
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
        public RestClientRequestSpec uri(RestRequestUri r) {
            this.uri = r.getUri();
            return this;
        }

        @Override
        public RestClientRequestSpec headers(RestRequestHeaders r) {
            this.httpHeaders = r.getHeaders();
            return this;
        }

        @Override
        public RestClientRequestBodySpec body(Object body) {
            this.body = body;
            return this;
        }

        @Override
        public RestClientResponseSpec retrieve() throws Exception {
            URL url = this.uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(this.httpMethod.name());
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
        }

    }

    private static class DefaultRestClientResponse implements RestClientResponseSpec {

        private final HttpStatus status;
        private final HttpHeaders headers;
        private final byte[] body;

        private DefaultRestClientResponse(HttpURLConnection connection) throws IOException {
            this.status = HttpStatus.fromValue(connection.getResponseCode());
            this.body = connection.getInputStream().readAllBytes();
            this.headers = this.setHttpHeaders(connection);
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
        public <T> T getBody(Class<T> bodyType) throws IOException {
            return RestBodyHandler.deserialize(this.body, bodyType, MediaType.get(this.getHeaders().get("Content-Type")));
        }

        @Override
        public String getBodyAsString() throws IOException {
            return RestBodyHandler.deserialize(this.body, MediaType.get(this.getHeaders().get("Content-Type")));
        }

        private HttpHeaders setHttpHeaders(HttpURLConnection connection) {
            return new HttpHeaders(connection.getHeaderFields());
        }
    }
}
