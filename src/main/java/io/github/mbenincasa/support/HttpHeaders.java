package io.github.mbenincasa.support;


import java.util.*;

public class HttpHeaders {

    private final List<HttpHeader> headers;

    public HttpHeaders() {
        this.headers = new LinkedList<>();
    }

    public HttpHeaders(Map<String, List<String>> headers) {
        this();
        headers.forEach((headerName, headerValues) -> {
            if (headerValues != null && !headerValues.isEmpty()) {
                add(headerName, headerValues.get(0));
            }
        });
    }

    public void add(String headerName, String headerValue) {
        if (headerName == null)
            return;

        this.headers.removeIf(header -> header.getName().equalsIgnoreCase(headerName));
        this.headers.add(new HttpHeader(headerName, headerValue));
    }

    public boolean containsHeader(String headerName) {
        return this.headers.stream()
                .anyMatch(header -> header.getName().equalsIgnoreCase(headerName));
    }

    public String get(String headerName) {
        Optional<String> headerValue = this.headers.stream()
                .filter(header -> header.getName().equalsIgnoreCase(headerName))
                .map(HttpHeader::getValue)
                .findFirst();
        return headerValue.orElse(null);
    }

    public Iterator<HttpHeader> getAll() {
        return this.headers.iterator();
    }
}
