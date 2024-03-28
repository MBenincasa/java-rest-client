package io.github.mbenincasa.support;

import java.net.URI;

public class DefaultRestClientRequestUri implements RestClientRequestUri {

    private URI uri;

    public DefaultRestClientRequestUri(URI uri) {
        this.uri = uri;
    }

    @Override
    public URI getUri() {
        return this.uri;
    }
}
