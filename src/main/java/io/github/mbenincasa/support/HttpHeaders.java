package io.github.mbenincasa.support;


import java.util.*;

public class HttpHeaders {

    private final Map<String, List<String>> headers;

    public HttpHeaders() {
        this.headers = new LinkedHashMap<>();
    }

    public HttpHeaders(Map<String, List<String>> headers) {
        this.headers = headers;
    }

    public void add(String headerName, String headerValue) {
        List<String> headerValues = this.containsHeader(headerName)
                ? this.headers.get(headerName)
                : new LinkedList<>();
        headerValues.add(headerValue);
        this.headers.put(headerName, headerValues);
    }

    public boolean containsHeader(String headerName) {
        return this.headers.containsKey(headerName);
    }

    public List<String> get(String headerName) {
        if (!containsHeader(headerName))
            return null;

        return this.headers.get(headerName);
    }

    public String getFirst(String headerName) {
        List<String> headerValues = this.get(headerName);

        return headerValues == null || headerValues.isEmpty()
                ? null
                : headerValues.get(0);
    }

    public Iterator<AbstractMap.SimpleEntry<String, String>> getAll() {
        return this.headers.entrySet().stream()
                .map(entry -> {
                    String key = entry.getKey();
                    String value = entry.getValue().isEmpty() ? null : entry.getValue().get(0);
                    return new AbstractMap.SimpleEntry<>(key, value);
                })
                .iterator();
    }
}
