package io.github.mbenincasa.javarestclient.support;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UriBuilder {

    private String uri;
    private Map<String, List<Object>> queryParams;
    private Map<String, Object> pathVariables;

    private UriBuilder() {
        this.queryParams = new HashMap<>();
        this.pathVariables = new HashMap<>();
    }

    public static UriBuilder create() {
        return new UriBuilder();
    }

    public UriBuilder uri(URI uri) {
        this.uri = uri.toString();
        return this;
    }

    public UriBuilder uri(String uri) {
        this.uri = uri;
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

    public UriBuilder pathVariable(String name, Object value) {
        pathVariables.put(name, value);
        return this;
    }

    public RestRequestUri build() {
        String uriString = replacePathVariables(this.uri);
        StringBuilder uriBuilder = new StringBuilder(uriString);

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

    private String replacePathVariables(String uriString) {
        for (Map.Entry<String, Object> entry : pathVariables.entrySet()) {
            String variable = "{" + entry.getKey() + "}";
            if (uriString.contains(variable)) {
                uriString = uriString.replace(variable, entry.getValue().toString());
            }
        }
        return uriString;
    }
}
