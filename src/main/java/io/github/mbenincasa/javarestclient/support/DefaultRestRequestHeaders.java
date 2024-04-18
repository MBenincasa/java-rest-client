package io.github.mbenincasa.javarestclient.support;

import io.github.mbenincasa.javarestclient.http.HttpHeaders;

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
