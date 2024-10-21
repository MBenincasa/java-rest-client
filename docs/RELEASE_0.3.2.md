# Release 0.3.2

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Usage](#usage)
    - [DefaultRestClient](#defaultrestclient)
    - [DefaultRestClientRequest](#defaultrestclientrequest)
    - [HeadersBuilder](#headersbuilder)
    - [UriBuilder](#uribuilder)
    - [DefaultRestClientResponse](#defaultrestclientresponse)
    - [HttpHeaders](#httpheaders)
    - [HttpStatus](#httpstatus)
    - [MediaType](#mediatype)

## Overview

Version 0.3.2 adds the `byte[] getBodyAsRaw()` method to retrieve the raw body of a response.

## Features

- HTTP GET requests
- HTTP POST requests
- HTTP PUT requests
- HTTP DELETE requests
- HTTP PATCH requests
- HTTP HEAD requests
- HTTP OPTIONS requests
- Support for query parameters and path variables
- Adding headers to requests
- Support for JSON data
- Support for XML data

## Usage

### DefaultRestClient

The `DefaultRestClient` class implements the `RestClient` interface and provides methods for creating different types of HTTP requests.

#### Methods

- `get()`: Creates a GET request.
- `post()`: Creates a POST request.
- `put()`: Creates a PUT request.
- `delete()`: Creates a DELETE request.
- `patch()`: Creates a PATCH request.
- `head()`: Creates a HEAD request.
- `options()`: Creates an OPTIONS request.

#### Default Behavior

When making requests with the `DefaultRestClient`, the following default settings apply:

- The `Content-Type` header is set to `application/json` if not explicitly specified.
- The `Accept` header is set to `*/*` if not explicitly specified.

For PATCH requests, the behavior is slightly modified:

- The request method is overridden to PUT.
- The `X-HTTP-Method-Override` header is set to PATCH to indicate the intended PATCH

### DefaultRestClientRequest

The `DefaultRestClientRequest` class implements the `RestClientRequestBodySpec` interface and represents a request to be sent to the server.

#### Methods

- `uri(RestRequestUri r)`: Sets the URI for the request.
- `headers(RestRequestHeaders r)`: Sets the headers for the request.
- `body(Object body)`: Sets the body of the request. The body will be serialized based on the chosen content-type.
- `retrieve()`: Sends the request to the server and returns the response.

#### Example

```java
RestClient restClient = new DefaultRestClient();

// GET request
RestClientResponseSpec response = restClient.get()
    .uri(UriBuilder.create()
        .uri("https://example.com/api/resource")
        .queryParam("page", 2)
        .build())
    .headers(HeadersBuilder.create()
        .accept(MediaType.APPLICATION_JSON)
        .build())
    .retrieve();

// POST request
RestClientResponseSpec response = restClient.post()
    .uri(UriBuilder.create()
        .uri("https://example.com/api/resource")
        .build())
    .headers(HeadersBuilder.create()
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .build())
    .body(new RequestBody("Example POST data"))
    .retrieve();

// PUT request
RestClientResponseSpec response = restClient.put()
    .uri(UriBuilder.create()
        .uri("https://example.com/api/resource/{id}")
        .pathVariable("id", 123)
        .build())
    .headers(HeadersBuilder.create()
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .build())
    .body(new RequestBody("Example PUT data"))
    .retrieve();

// PATCH request
RestClientResponseSpec response = restClient.patch()
        .uri(UriBuilder.create()
            .uri("https://example.com/api/resource/{id}")
            .pathVariable("id", 123)
            .build())
        .headers(HeadersBuilder.create()
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .build())
        .body(new RequestBody("Example PATCH data"))
        .retrieve();

// DELETE request
RestClientResponseSpec response = restClient.delete()
    .uri(UriBuilder.create()
        .uri("https://example.com/api/resource/{id}")
        .pathVariable("id", 123)
        .build())
    .retrieve();

// HEAD request
RestClientResponseSpec response = restClient.head()
        .uri(UriBuilder.create()
            .uri("https://example.com/api/resource/{id}")
            .pathVariable("id", 123)
            .build())
        .retrieve();

// OPTIONS request
RestClientResponseSpec response = restClient.options()
        .uri(UriBuilder.create()
            .uri("https://example.com/api/resource/{id}")
            .pathVariable("id", 123)
            .build())
        .retrieve();
```

### HeadersBuilder

The `HeadersBuilder` class provides a builder pattern for constructing HTTP headers to be included in the request.

#### Methods

- `create()`: Creates a new instance of `HeadersBuilder`.
- `add(String key, Object value)`: Adds a header with the specified key and value to the headers.
- `accept(MediaType mediaType)`: Sets the `Accept` header to the specified media type.
- `acceptCharset(Object value)`: Sets the `Accept-Charset` header to the specified value.
- `acceptEncoding(Object value)`: Sets the `Accept-Encoding` header to the specified value.
- `acceptLanguage(Object value)`: Sets the `Accept-Language` header to the specified value.
- `accessControlRequestHeaders(Object value)`: Sets the `Access-Control-Request-Headers` header to the specified value.
- `accessControlRequestMethod(Object value)`: Sets the `Access-Control-Request-Method` header to the specified value.
- `authorization(Object value)`: Sets the `Authorization` header to the specified value.
- `cacheControl(Object value)`: Sets the `Cache-Control` header to the specified value.
- `contentMD5(Object value)`: Sets the `Content-MD5` header to the specified value.
- `contentLength(Object value)`: Sets the `Content-Length` header to the specified value.
- `contentTransferEncoding(Object value)`: Sets the `Content-Transfer-Encoding` header to the specified value.
- `contentType(MediaType mediaType)`: Sets the `Content-Type` header to the specified media type.
- `cookie(Object value)`: Sets the `Cookie` header to the specified value.
- `date(Object value)`: Sets the `Date` header to the specified value.
- `expect(Object value)`: Sets the `Expect` header to the specified value.
- `from(Object value)`: Sets the `From` header to the specified value.
- `host(Object value)`: Sets the `Host` header to the specified value.
- `ifMatch(Object value)`: Sets the `If-Match` header to the specified value.
- `ifModifiedSince(Object value)`: Sets the `If-Modified-Since` header to the specified value.
- `ifNoneMatch(Object value)`: Sets the `If-None-Match` header to the specified value.
- `ifRange(Object value)`: Sets the `If-Range` header to the specified value.
- `ifUnmodifiedSince(Object value)`: Sets the `If-Unmodified-Since` header to the specified value.
- `keepAlive(Object value)`: Sets the `Keep-Alive` header to the specified value.
- `maxForwards(Object value)`: Sets the `Max-Forwards` header to the specified value.
- `origin(Object value)`: Sets the `Origin` header to the specified value.
- `pragma(Object value)`: Sets the `Pragma` header to the specified value.
- `proxyAuthorization(Object value)`: Sets the `Proxy-Authorization` header to the specified value.
- `range(Object value)`: Sets the `Range` header to the specified value.
- `referer(Object value)`: Sets the `Referer` header to the specified value.
- `te(Object value)`: Sets the `TE` header to the specified value.
- `trailer(Object value)`: Sets the `Trailer` header to the specified value.
- `transferEncoding(Object value)`: Sets the `Transfer-Encoding` header to the specified value.
- `upgrade(Object value)`: Sets the `Upgrade` header to the specified value.
- `userAgent(Object value)`: Sets the `User-Agent` header to the specified value.
- `via(Object value)`: Sets the `Via` header to the specified value.
- `warning(Object value)`: Sets the `Warning` header to the specified value.
- `xRequestedWith(Object value)`: Sets the `X-Requested-With` header to the specified value.
- `xDoNotTrack(Object value)`: Sets the `X-Do-Not-Track` header to the specified value.
- `DNT(Object value)`: Sets the `DNT` header to the specified value.
- `connection(Object value)`: Sets the `Connection` header to the specified value.

#### Example Usage

```java
// Example of creating headers using HeadersBuilder
HeadersBuilder headersBuilder = HeadersBuilder.create()
    .contentType(MediaType.APPLICATION_JSON)
    .accept(MediaType.APPLICATION_JSON);

// Add custom headers
headersBuilder.add("Custom-Header", "value");

// Build headers
RestRequestHeaders headers = headersBuilder.build();
```

### UriBuilder

The `UriBuilder` class provides a builder pattern for constructing URIs to be used in HTTP requests.

#### Methods

- `create()`: Creates a new instance of `UriBuilder`.
- `uri(URI uri)`: Sets the URI to the specified URI.
- `uri(String uri)`: Sets the URI to the specified string representation.
- `queryParam(String name, Object... values)`: Adds query parameters to the URI.
- `pathVariable(String name, Object value)`: Adds path variables to the URI.
- `build()`: Builds the URI and returns a `RestRequestUri` object.

#### Example Usage

```java
// Example of creating a URI using UriBuilder
UriBuilder uriBuilder = UriBuilder.create()
        .uri("https://example.com/api/resource/{id}")
        .queryParam("page", 1)
        .queryParam("limit", 10)
        .pathVariable("id", 123);

// Build URI
RestRequestUri uri = uriBuilder.build();
```

### DefaultRestClientResponse

The `DefaultRestClientResponse` class implements the `RestClientResponseSpec` interface and represents the response received from the server after making an HTTP request.

#### Methods

- `getStatus()`: Returns the HTTP status code of the response.
- `getHeaders()`: Returns the headers included in the response.
- `getBody(Class<T> bodyType)`: Returns the body of the response deserialized into the specified type. Either JSON or XML bodies can be deserialized.
- `getBodyAsList(Class<T> bodyType)`: Returns the deserialized response body to the specified type as List. Either JSON or XML bodies can be deserialized.
- `getBodyAsRaw()`: Returns the raw body of a response.
- `getBodyAsString()`: Returns the body of the response as a string.

#### Example Usage

```java
// Get response status
HttpStatus status = response.getStatus();

// Get response headers
HttpHeaders headers = response.getHeaders();

// Get response body as a specific type
SomeResponseType body = response.getBody(SomeResponseType.class);

// Get body raw
byte[] bodyRaw = response.getBodyAsRaw();

// Get response body as a string
String responseBody = response.getBodyAsString();
```

### HttpHeaders

The `HttpHeaders` class represents a collection of HTTP headers to be included in an HTTP request or response.

#### Methods

- `add(String headerName, String headerValue)`: Adds a header with the specified name and value to the headers.
- `containsHeader(String headerName)`: Checks if the headers contain a header with the specified name.
- `get(String headerName)`: Returns the value of the header with the specified name, or `null` if the header is not present.
- `getAll()`: Returns an iterator over all headers in the collection.

### HttpStatus

The `HttpStatus` enum represents HTTP status codes and their corresponding reason phrases.

#### Methods

- `getValue()`: Returns the integer value of the HTTP status code.
- `getReasonPhrase()`: Returns the reason phrase of the HTTP status code.
- `fromValue(int value)`: Returns the `HttpStatus` enum constant corresponding to the specified integer value.

### MediaType

The `MediaType` enum represents media types (MIME types) used in HTTP requests and responses.

#### Methods

- `getValue()`: Returns the string value representing the media type.
- `get(String contentType)`: Returns the `MediaType` enum constant corresponding to the specified content type string.