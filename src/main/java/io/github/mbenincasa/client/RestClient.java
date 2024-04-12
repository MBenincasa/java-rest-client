package io.github.mbenincasa.client;

import io.github.mbenincasa.exception.RestClientException;
import io.github.mbenincasa.http.HttpHeaders;
import io.github.mbenincasa.http.HttpStatus;
import io.github.mbenincasa.support.RestRequestHeaders;
import io.github.mbenincasa.support.RestRequestUri;

public interface RestClient {

    RestClientRequestSpec get();

    RestClientRequestBodySpec post();

    RestClientRequestBodySpec put();

    RestClientRequestSpec delete();

    interface RestClientRequestSpec {

        RestClientRequestSpec uri(RestRequestUri r);

        RestClientRequestSpec headers(RestRequestHeaders r);

        RestClientResponseSpec retrieve() throws RestClientException;
    }

    interface RestClientRequestBodySpec extends RestClientRequestSpec {

        RestClientRequestBodySpec body(Object body);
    }

    interface RestClientResponseSpec {

        HttpStatus getStatus();

        HttpHeaders getHeaders();

        <T> T getBody(Class<T> bodyType) throws RestClientException;

        String getBodyAsString();
    }
}
