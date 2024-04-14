package io.github.mbenincasa.javarestclient.support;

import java.net.URI;

public class UriBuilder {

    private URI uri;

    public static UriBuilder create() {
        return new UriBuilder();
    }

    public UriBuilder uri(URI uri) {
        this.uri = uri;
        return this;
    }

    public RestRequestUri build() {
        return new DefaultRestRequestUri(this.uri);
    }
}
