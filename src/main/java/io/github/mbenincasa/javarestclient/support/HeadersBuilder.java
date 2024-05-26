package io.github.mbenincasa.javarestclient.support;

import io.github.mbenincasa.javarestclient.http.HttpHeaders;
import io.github.mbenincasa.javarestclient.http.MediaType;

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

    public HeadersBuilder accept(MediaType mediaType) {
        this.add(ACCEPT, mediaType.getValue());
        return this;
    }

    public HeadersBuilder acceptCharset(Object value) {
        this.add(ACCEPT_CHARSET, value);
        return this;
    }

    public HeadersBuilder acceptEncoding(Object value) {
        this.add(ACCEPT_ENCODING, value);
        return this;
    }

    public HeadersBuilder acceptLanguage(Object value) {
        this.add(ACCEPT_LANGUAGE, value);
        return this;
    }

    public HeadersBuilder accessControlRequestHeaders(Object value) {
        this.add(ACCESS_CONTROL_REQUEST_HEADERS, value);
        return this;
    }

    public HeadersBuilder accessControlRequestMethod(Object value) {
        this.add(ACCESS_CONTROL_REQUEST_METHOD, value);
        return this;
    }

    public HeadersBuilder authorization(Object value) {
        this.add(AUTHORIZATION, value);
        return this;
    }

    public HeadersBuilder cacheControl(Object value) {
        this.add(CACHE_CONTROL, value);
        return this;
    }

    public HeadersBuilder contentMD5(Object value) {
        this.add(CONTENT_MD5, value);
        return this;
    }

    public HeadersBuilder contentLength(Object value) {
        this.add(CONTENT_LENGTH, value);
        return this;
    }

    public HeadersBuilder contentTransferEncoding(Object value) {
        this.add(CONTENT_TRANSFER_ENCODING, value);
        return this;
    }

    public HeadersBuilder contentType(MediaType mediaType) {
        this.add(CONTENT_TYPE, mediaType.getValue());
        return this;
    }

    public HeadersBuilder cookie(Object value) {
        this.add(COOKIE, value);
        return this;
    }

    public HeadersBuilder date(Object value) {
        this.add(DATE, value);
        return this;
    }

    public HeadersBuilder expect(Object value) {
        this.add(EXPECT, value);
        return this;
    }

    public HeadersBuilder from(Object value) {
        this.add(FROM, value);
        return this;
    }

    public HeadersBuilder host(Object value) {
        this.add(HOST, value);
        return this;
    }

    public HeadersBuilder ifMatch(Object value) {
        this.add(IF_MATCH, value);
        return this;
    }

    public HeadersBuilder ifModifiedSince(Object value) {
        this.add(IF_MODIFIED_SINCE, value);
        return this;
    }

    public HeadersBuilder ifNoneMatch(Object value) {
        this.add(IF_NONE_MATCH, value);
        return this;
    }

    public HeadersBuilder ifRange(Object value) {
        this.add(IF_RANGE, value);
        return this;
    }

    public HeadersBuilder ifUnmodifiedSince(Object value) {
        this.add(IF_UNMODIFIED_SINCE, value);
        return this;
    }

    public HeadersBuilder keepAlive(Object value) {
        this.add(KEEP_ALIVE, value);
        return this;
    }

    public HeadersBuilder maxForwards(Object value) {
        this.add(MAX_FORWARDS, value);
        return this;
    }

    public HeadersBuilder origin(Object value) {
        this.add(ORIGIN, value);
        return this;
    }

    public HeadersBuilder pragma(Object value) {
        this.add(PRAGMA, value);
        return this;
    }

    public HeadersBuilder proxyAuthorization(Object value) {
        this.add(PROXY_AUTHORIZATION, value);
        return this;
    }

    public HeadersBuilder range(Object value) {
        this.add(RANGE, value);
        return this;
    }

    public HeadersBuilder referer(Object value) {
        this.add(REFERER, value);
        return this;
    }

    public HeadersBuilder te(Object value) {
        this.add(TE, value);
        return this;
    }

    public HeadersBuilder trailer(Object value) {
        this.add(TRAILER, value);
        return this;
    }

    public HeadersBuilder transferEncoding(Object value) {
        this.add(TRANSFER_ENCODING, value);
        return this;
    }

    public HeadersBuilder upgrade(Object value) {
        this.add(UPGRADE, value);
        return this;
    }

    public HeadersBuilder userAgent(Object value) {
        this.add(USER_AGENT, value);
        return this;
    }

    public HeadersBuilder via(Object value) {
        this.add(VIA, value);
        return this;
    }

    public HeadersBuilder warning(Object value) {
        this.add(WARNING, value);
        return this;
    }

    public HeadersBuilder xRequestedWith(Object value) {
        this.add(X_REQUESTED_WITH, value);
        return this;
    }

    public HeadersBuilder xDoNotTrack(Object value) {
        this.add(X_DO_NOT_TRACK, value);
        return this;
    }

    public HeadersBuilder DNT(Object value) {
        this.add(DNT, value);
        return this;
    }

    public HeadersBuilder connection(Object value) {
        this.add(CONNECTION, value);
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
    private static final String ACCEPT_CHARSET = "Accept-Charset";
    private static final String ACCEPT_ENCODING = "Accept-Encoding";
    private static final String ACCEPT_LANGUAGE = "Accept-Language";
    private static final String ACCESS_CONTROL_REQUEST_HEADERS = "Access-Control-Request-Headers";
    private static final String ACCESS_CONTROL_REQUEST_METHOD = "Access-Control-Request-Method";
    private static final String AUTHORIZATION = "Authorization";
    private static final String CACHE_CONTROL = "Cache-Control";
    private static final String CONTENT_MD5 = "Content-MD5";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String COOKIE = "Cookie";
    private static final String DATE = "Date";
    private static final String EXPECT = "Expect";
    private static final String FROM = "From";
    private static final String HOST = "Host";
    private static final String IF_MATCH = "If-Match";
    private static final String IF_MODIFIED_SINCE = "If-Modified-Since";
    private static final String IF_NONE_MATCH = "If-None-Match";
    private static final String IF_RANGE = "If-Range";
    private static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
    private static final String KEEP_ALIVE = "Keep-Alive";
    private static final String MAX_FORWARDS = "Max-Forwards";
    private static final String ORIGIN = "Origin";
    private static final String PRAGMA = "Pragma";
    private static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
    private static final String RANGE = "Range";
    private static final String REFERER = "Referer";
    private static final String TE = "TE";
    private static final String TRAILER = "Trailer";
    private static final String TRANSFER_ENCODING = "Transfer-Encoding";
    private static final String UPGRADE = "Upgrade";
    private static final String USER_AGENT = "User-Agent";
    private static final String VIA = "Via";
    private static final String WARNING = "Warning";
    private static final String X_REQUESTED_WITH = "X-Requested-With";
    private static final String X_DO_NOT_TRACK = "X-Do-Not-Track";
    private static final String DNT = "DNT";
    private static final String CONNECTION = "Connection";
}
