package io.github.mbenincasa.client;

import io.github.mbenincasa.support.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        public RestClientRequestBodySpec body() {
            return null;
        }

        @Override
        public RestClientResponseSpec retrieve() throws Exception {
            URL url = this.uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(httpMethod.name());
            Iterator<HttpHeader> headersIterator = this.httpHeaders.getAll();
            while (headersIterator.hasNext()) {
                HttpHeader header = headersIterator.next();
                connection.setRequestProperty(header.getName(), header.getValue());
            }
            connection.connect();

            return new DefaultRestClientResponse(connection);
        }

    }

    private static class DefaultRestClientResponse implements RestClientResponseSpec {

        private final int status;
        private final HttpHeaders headers;
        private final InputStream body;

        private DefaultRestClientResponse(HttpURLConnection connection) throws IOException {
            this.status = connection.getResponseCode();
            this.body = connection.getInputStream();
            this.headers = this.setHttpHeaders(connection);
        }

        @Override
        public int getStatus() {
            return this.status;
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.headers;
        }

        @Override
        public InputStream getBodyStream() {
            return this.body;
        }

        @Override
        public String getBodyString() throws IOException {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(this.body))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }
                return content.toString();
            }
        }

        private HttpHeaders setHttpHeaders(HttpURLConnection connection) {
            return new HttpHeaders(connection.getHeaderFields());
        }
    }
}
