package io.github.mbenincasa.support;

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

    public RestRequestHeaders build() {
        return new DefaultRestRequestHeaders(this.httpHeaders);
    }
}
