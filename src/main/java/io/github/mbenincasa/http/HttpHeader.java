package io.github.mbenincasa.http;

public class HttpHeader {

    private final String name;
    private final String value;

    protected HttpHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }
}
