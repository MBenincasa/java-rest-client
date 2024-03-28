package io.github.mbenincasa.support;

import java.net.URI;

public class RestClientRequestUriBuilder {

    private URI uri;

    public static RestClientRequestUriBuilder create() {
        return new RestClientRequestUriBuilder();
    }

    public RestClientRequestUriBuilder uri(URI uri) {
        this.uri = uri;
        return this;
    }

    public RestClientRequestUri build() {
        return new DefaultRestClientRequestUri(this.uri);
    }
}
