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
    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain"),
    APPLICATION_OCTET_STREAM("application/octet-stream"),
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
    APPLICATION_JSON_PATCH("application/json-patch+json"),
    APPLICATION_GRAPHQL("application/graphql"),
    APPLICATION_MSWORD("application/msword"),
    APPLICATION_VND_OPENXMLFORMATS_OFFICEDOCUMENT_WORDPROCESSINGML_DOCUMENT("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    APPLICATION_PDF("application/pdf"),
    IMAGE_PNG("image/png"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_GIF("image/gif"),
    IMAGE_SVG_XML("image/svg+xml"),
    VIDEO_MP4("video/mp4"),
    VIDEO_MPEG("video/mpeg"),
    AUDIO_MPEG("audio/mpeg"),
    AUDIO_WAV("audio/wav");

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

    @Override
    public String toString() {
        return "MediaType{" +
                "value='" + value + '\'' +
                '}';
    }
}
