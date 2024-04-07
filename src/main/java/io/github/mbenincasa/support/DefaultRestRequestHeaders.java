package io.github.mbenincasa.support;

import io.github.mbenincasa.http.HttpHeaders;

public class DefaultRestRequestHeaders implements RestRequestHeaders {

    private HttpHeaders httpHeaders;

    public DefaultRestRequestHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    @Override
    public HttpHeaders getHeaders() {
        return this.httpHeaders;
    }
}
