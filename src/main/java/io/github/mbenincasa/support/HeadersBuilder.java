package io.github.mbenincasa.support;

import io.github.mbenincasa.http.HttpHeaders;
import io.github.mbenincasa.http.MediaType;

public class HeadersBuilder {

    private HttpHeaders httpHeaders;

    private HeadersBuilder() {
        this.httpHeaders = new HttpHeaders();
    }

    public static HeadersBuilder create() {
        return new HeadersBuilder();
    }

    public HeadersBuilder add(String key, Object value) {
        this.httpHeaders.add(key, String.valueOf(value));
        return this;
    }

    public HeadersBuilder contentType(MediaType mediaType) {
        this.add(CONTENT_TYPE, mediaType.getValue());
        return this;
    }

    public HeadersBuilder accept(MediaType mediaType) {
        this.add(ACCEPT, mediaType.getValue());
        return this;
    }

    public RestRequestHeaders build() {
        if(!this.httpHeaders.containsHeader(ACCEPT)) {
            this.add(ACCEPT, MediaType.ALL.getValue());
        }
        if(!this.httpHeaders.containsHeader(CONTENT_TYPE)) {
            this.add(CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
        }
        return new DefaultRestRequestHeaders(this.httpHeaders);
    }

    private static final String ACCEPT = "Accept";
    private static final String CONTENT_TYPE = "Content-Type";
}
