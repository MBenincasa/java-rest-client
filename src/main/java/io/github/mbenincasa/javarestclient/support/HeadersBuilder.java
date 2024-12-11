package io.github.mbenincasa.javarestclient.support;

import io.github.mbenincasa.javarestclient.http.HttpHeaders;
import io.github.mbenincasa.javarestclient.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeadersBuilder {

    private static final Logger logger = LoggerFactory.getLogger(HeadersBuilder.class);

    private HttpHeaders httpHeaders;

    private HeadersBuilder() {
        this.httpHeaders = new HttpHeaders();
        logger.debug("HeadersBuilder initialized with empty HttpHeaders.");
    }

    public static HeadersBuilder create() {
        logger.info("Creating new HeadersBuilder instance.");
        return new HeadersBuilder();
    }

    public HeadersBuilder add(String key, Object value) {
        logger.debug("Adding header: {} with value: {}", key, value);
        this.httpHeaders.add(key, String.valueOf(value));
        logger.trace("Current headers: {}", httpHeaders);
        return this;
    }

    public HeadersBuilder accept(MediaType mediaType) {
        logger.debug("Setting Accept header to: {}", mediaType.getValue());
        this.add(ACCEPT, mediaType.getValue());
        return this;
    }

    public HeadersBuilder acceptCharset(Object value) {
        logger.debug("Setting Accept-Charset header to: {}", value);
        this.add(ACCEPT_CHARSET, value);
        return this;
    }

    public HeadersBuilder acceptEncoding(Object value) {
        logger.debug("Setting Accept-Encoding header to: {}", value);
        this.add(ACCEPT_ENCODING, value);
        return this;
    }

    public HeadersBuilder acceptLanguage(Object value) {
        logger.debug("Setting Accept-Language header to: {}", value);
        this.add(ACCEPT_LANGUAGE, value);
        return this;
    }

    public HeadersBuilder accessControlRequestHeaders(Object value) {
        logger.debug("Setting Access-Control-Request-Headers to: {}", value);
        this.add(ACCESS_CONTROL_REQUEST_HEADERS, value);
        return this;
    }

    public HeadersBuilder accessControlRequestMethod(Object value) {
        logger.debug("Setting Access-Control-Request-Method to: {}", value);
        this.add(ACCESS_CONTROL_REQUEST_METHOD, value);
        return this;
    }

    public HeadersBuilder authorization(Object value) {
        logger.debug("Setting Authorization header to: {}", value);
        this.add(AUTHORIZATION, value);
        return this;
    }

    public HeadersBuilder cacheControl(Object value) {
        logger.debug("Setting Cache-Control header to: {}", value);
        this.add(CACHE_CONTROL, value);
        return this;
    }

    public HeadersBuilder contentMD5(Object value) {
        logger.debug("Setting Content-MD5 header to: {}", value);
        this.add(CONTENT_MD5, value);
        return this;
    }

    public HeadersBuilder contentLength(Object value) {
        logger.debug("Setting Content-Length header to: {}", value);
        this.add(CONTENT_LENGTH, value);
        return this;
    }

    public HeadersBuilder contentTransferEncoding(Object value) {
        logger.debug("Setting Content-Transfer-Encoding header to: {}", value);
        this.add(CONTENT_TRANSFER_ENCODING, value);
        return this;
    }

    public HeadersBuilder contentType(MediaType mediaType) {
        logger.debug("Setting Content-Type header to: {}", mediaType.getValue());
        this.add(CONTENT_TYPE, mediaType.getValue());
        return this;
    }

    public HeadersBuilder cookie(Object value) {
        logger.debug("Setting Cookie header to: {}", value);
        this.add(COOKIE, value);
        return this;
    }

    public HeadersBuilder date(Object value) {
        logger.debug("Setting Date header to: {}", value);
        this.add(DATE, value);
        return this;
    }

    public HeadersBuilder expect(Object value) {
        logger.debug("Setting Expect header to: {}", value);
        this.add(EXPECT, value);
        return this;
    }

    public HeadersBuilder from(Object value) {
        logger.debug("Setting From header to: {}", value);
        this.add(FROM, value);
        return this;
    }

    public HeadersBuilder host(Object value) {
        logger.debug("Setting Host header to: {}", value);
        this.add(HOST, value);
        return this;
    }

    public HeadersBuilder ifMatch(Object value) {
        logger.debug("Setting If-Match header to: {}", value);
        this.add(IF_MATCH, value);
        return this;
    }

    public HeadersBuilder ifModifiedSince(Object value) {
        logger.debug("Setting If-Modified-Since header to: {}", value);
        this.add(IF_MODIFIED_SINCE, value);
        return this;
    }

    public HeadersBuilder ifNoneMatch(Object value) {
        logger.debug("Setting If-None-Match header to: {}", value);
        this.add(IF_NONE_MATCH, value);
        return this;
    }

    public HeadersBuilder ifRange(Object value) {
        logger.debug("Setting If-Range header to: {}", value);
        this.add(IF_RANGE, value);
        return this;
    }

    public HeadersBuilder ifUnmodifiedSince(Object value) {
        logger.debug("Setting If-Unmodified-Since header to: {}", value);
        this.add(IF_UNMODIFIED_SINCE, value);
        return this;
    }

    public HeadersBuilder keepAlive(Object value) {
        logger.debug("Setting Keep-Alive header to: {}", value);
        this.add(KEEP_ALIVE, value);
        return this;
    }

    public HeadersBuilder maxForwards(Object value) {
        logger.debug("Setting Max-Forwards header to: {}", value);
        this.add(MAX_FORWARDS, value);
        return this;
    }

    public HeadersBuilder origin(Object value) {
        logger.debug("Setting Origin header to: {}", value);
        this.add(ORIGIN, value);
        return this;
    }

    public HeadersBuilder pragma(Object value) {
        logger.debug("Setting Pragma header to: {}", value);
        this.add(PRAGMA, value);
        return this;
    }

    public HeadersBuilder proxyAuthorization(Object value) {
        logger.debug("Setting Proxy-Authorization header to: {}", value);
        this.add(PROXY_AUTHORIZATION, value);
        return this;
    }

    public HeadersBuilder range(Object value) {
        logger.debug("Setting Range header to: {}", value);
        this.add(RANGE, value);
        return this;
    }

    public HeadersBuilder referer(Object value) {
        logger.debug("Setting Referer header to: {}", value);
        this.add(REFERER, value);
        return this;
    }

    public HeadersBuilder te(Object value) {
        logger.debug("Setting TE header to: {}", value);
        this.add(TE, value);
        return this;
    }

    public HeadersBuilder trailer(Object value) {
        logger.debug("Setting Trailer header to: {}", value);
        this.add(TRAILER, value);
        return this;
    }

    public HeadersBuilder transferEncoding(Object value) {
        logger.debug("Setting Transfer-Encoding header to: {}", value);
        this.add(TRANSFER_ENCODING, value);
        return this;
    }

    public HeadersBuilder upgrade(Object value) {
        logger.debug("Setting Upgrade header to: {}", value);
        this.add(UPGRADE, value);
        return this;
    }

    public HeadersBuilder userAgent(Object value) {
        logger.debug("Setting User-Agent header to: {}", value);
        this.add(USER_AGENT, value);
        return this;
    }

    public HeadersBuilder via(Object value) {
        logger.debug("Setting Via header to: {}", value);
        this.add(VIA, value);
        return this;
    }

    public HeadersBuilder warning(Object value) {
        logger.debug("Setting Warning header to: {}", value);
        this.add(WARNING, value);
        return this;
    }

    public HeadersBuilder xRequestedWith(Object value) {
        logger.debug("Setting X-Requested-With header to: {}", value);
        this.add(X_REQUESTED_WITH, value);
        return this;
    }

    public HeadersBuilder xDoNotTrack(Object value) {
        logger.debug("Setting X-Do-Not-Track header to: {}", value);
        this.add(X_DO_NOT_TRACK, value);
        return this;
    }

    public HeadersBuilder DNT(Object value) {
        logger.debug("Setting DNT header to: {}", value);
        this.add(DNT, value);
        return this;
    }

    public HeadersBuilder connection(Object value) {
        logger.debug("Setting Connection header to: {}", value);
        this.add(CONNECTION, value);
        return this;
    }

    public RestRequestHeaders build() {
        logger.info("Building RestRequestHeaders with current headers.");
        if(!this.httpHeaders.containsHeader(ACCEPT)) {
            logger.debug("Adding default Accept header: {}", MediaType.ALL.getValue());
            this.add(ACCEPT, MediaType.ALL.getValue());
        }
        if(!this.httpHeaders.containsHeader(CONTENT_TYPE)) {
            logger.debug("Adding default Content-Type header: {}", MediaType.APPLICATION_JSON.getValue());
            this.add(CONTENT_TYPE, MediaType.APPLICATION_JSON.getValue());
        }
        logger.debug("Headers build complete: {}", this.httpHeaders);
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
