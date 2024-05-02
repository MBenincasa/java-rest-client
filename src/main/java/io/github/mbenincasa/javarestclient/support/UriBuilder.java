package io.github.mbenincasa.javarestclient.support;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UriBuilder {

    private URI uri;
    private Map<String, List<Object>> queryParams;

    private UriBuilder() {
        this.queryParams = new HashMap<>();
    }

    public static UriBuilder create() {
        return new UriBuilder();
    }

    public UriBuilder uri(URI uri) {
        this.uri = uri;
        return this;
    }

    public UriBuilder uri(String uri) {
        this.uri = URI.create(uri);
        return this;
    }

    public UriBuilder queryParam(String name, Object... values) {
        if (!queryParams.containsKey(name)) {
            queryParams.put(name, new LinkedList<>());
        }
        for (Object value : values) {
            queryParams.get(name).add(value);
        }
        return this;
    }

    public RestRequestUri build() {
        StringBuilder uriBuilder = new StringBuilder(this.uri.toString());

        if (!queryParams.isEmpty()) {
            uriBuilder.append("?");
            queryParams.forEach((name, values) -> {
                for (Object value : values) {
                    uriBuilder.append(name)
                            .append("=")
                            .append(value.toString())
                            .append("&");
                }
            });
            uriBuilder.deleteCharAt(uriBuilder.length() - 1);
        }

        return new DefaultRestRequestUri(URI.create(uriBuilder.toString()));
    }
}
