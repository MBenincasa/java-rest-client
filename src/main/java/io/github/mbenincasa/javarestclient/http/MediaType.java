package io.github.mbenincasa.javarestclient.http;

import java.util.Arrays;

public enum MediaType {

    ALL("*/*"),
    APPLICATION_JSON("application/json");

    private final String value;

    MediaType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static MediaType get(String contentType) {
        String[] parts = contentType.split(";");
        String mainType = parts[0].trim();

        return Arrays.stream(values())
                .filter(mediaType -> mediaType.getValue().equalsIgnoreCase(mainType))
                .findFirst()
                .orElse(null);
    }
}
