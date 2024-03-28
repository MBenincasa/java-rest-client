package io.github.mbenincasa.client;

import io.github.mbenincasa.support.HttpMethod;
import io.github.mbenincasa.support.RestClientRequestUri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class DefaultRestClient implements RestClient {

    @Override
    public RestClientRequestSpec get() {
        return new DefaultRestClientRequest(HttpMethod.GET);
    }

    @Override
    public RestClientRequestBodySpec post() {
        return new DefaultRestClientRequest(HttpMethod.POST);
    }

    private static class DefaultRestClientRequest implements RestClientRequestBodySpec {

        private URI uri;
        private final HttpMethod httpMethod;

        private DefaultRestClientRequest(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
        }

        @Override
        public RestClientRequestSpec uri(RestClientRequestUri r) {
            this.uri = r.getUri();
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
            connection.connect();

            return new DefaultRestClientResponse(connection);
        }

    }

    private static class DefaultRestClientResponse implements RestClientResponseSpec {

        private final int status;
        private final Map<String, List<String>> headers;
        private final InputStream body;

        private DefaultRestClientResponse(HttpURLConnection connection) throws IOException {
            this.status = connection.getResponseCode();
            this.body = connection.getInputStream();
            this.headers = connection.getHeaderFields();
        }

        @Override
        public int getStatus() {
            return this.status;
        }

        @Override
        public Map<String, List<String>> getHeaders() {
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
    }
}
