package io.github.mbenincasa.javarestclient.support;

import java.net.URI;

public class DefaultRestRequestUri implements RestRequestUri {

    private URI uri;

    public DefaultRestRequestUri(URI uri) {
        this.uri = uri;
    }

    @Override
    public URI getUri() {
        return this.uri;
    }
}
