package io.github.mbenincasa.javarestclient.client;

import io.github.mbenincasa.javarestclient.exception.RestClientException;
import io.github.mbenincasa.javarestclient.http.HttpHeaders;
import io.github.mbenincasa.javarestclient.http.HttpStatus;
import io.github.mbenincasa.javarestclient.support.RestRequestHeaders;
import io.github.mbenincasa.javarestclient.support.RestRequestUri;

public interface RestClient {

    RestClientRequestSpec<?> get();

    RestClientRequestBodySpec post();

    RestClientRequestBodySpec put();

    RestClientRequestSpec<?> delete();

    RestClientRequestSpec<?> head();

    RestClientRequestSpec<?> options();

    interface RestClientRequestSpec<S extends RestClientRequestSpec<?>> {

        S uri(RestRequestUri r);

        S headers(RestRequestHeaders r);

        RestClientResponseSpec retrieve() throws RestClientException;
    }

    interface RestClientRequestBodySpec extends RestClientRequestSpec<RestClientRequestBodySpec> {

        RestClientRequestBodySpec body(Object body);
    }

    interface RestClientResponseSpec {

        HttpStatus getStatus();

        HttpHeaders getHeaders();

        <T> T getBody(Class<T> bodyType) throws RestClientException;

        String getBodyAsString();
    }
}
