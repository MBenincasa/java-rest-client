package io.github.mbenincasa.http;

public enum MediaType {

    ALL("*/*"),
    APPLICATION_JSON("application/json");

    private final String value;

    MediaType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
