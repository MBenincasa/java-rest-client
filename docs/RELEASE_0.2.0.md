# Release 0.2.0

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

Version 0.2.0 introduces significant enhancements and new features for the Java REST client library. This update focuses on expanding the capabilities of the REST client, improving error handling, and increasing flexibility in library usage. Key additions include support for HTTP PATCH, HEAD, and OPTIONS methods, along with the ability to handle query parameters and path variables. Additionally, critical issues have been addressed, improving the overall reliability of the library. Read on to learn more about the changes introduced in version 0.2.0.

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
- Support for JSON data with `application/json` media type

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
- `contentType(MediaType mediaType)`: Sets the `Content-Type` header to the specified media type.
- `accept(MediaType mediaType)`: Sets the `Accept` header to the specified media type.
- `build()`: Builds the headers and returns a `RestRequestHeaders` object.

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
        .uri("https://example.com/api/resource")
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
- `getBody(Class<T> bodyType)`: Returns the body of the response deserialized into the specified type. Currently, only deserialization of JSON data is supported.
- `getBodyAsString()`: Returns the body of the response as a string.

#### Example Usage

```java
// Get response status
HttpStatus status = response.getStatus();

// Get response headers
HttpHeaders headers = response.getHeaders();

// Get response body as a specific type
SomeResponseType body = response.getBody(SomeResponseType.class);

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