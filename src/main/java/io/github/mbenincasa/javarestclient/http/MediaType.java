package io.github.mbenincasa.javarestclient.http;

import java.util.Arrays;

public enum MediaType {

    ALL("*/*"),
    APPLICATION_JSON("application/json"),
    APPLICATION_VND_API_JSON("application/vnd.api+json"),
    APPLICATION_XML("application/xml"),
    APPLICATION_ATOM_XML("application/atom+xml"),
    APPLICATION_RDF_XML("application/rdf+xml"),
    APPLICATION_RSS_XML("application/rss+xml"),
    APPLICATION_XHTML_XML("application/xhtml+xml"),
    TEXT_XML("text/xml"),
    TEXT_HTML("text/html");

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
