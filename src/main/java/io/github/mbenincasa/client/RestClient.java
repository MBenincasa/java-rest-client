package io.github.mbenincasa.client;

import io.github.mbenincasa.http.HttpHeaders;
import io.github.mbenincasa.http.HttpStatus;
import io.github.mbenincasa.support.RestRequestHeaders;
import io.github.mbenincasa.support.RestRequestUri;

import java.io.IOException;
import java.io.InputStream;

public interface RestClient {

    RestClientRequestSpec get();

    RestClientRequestBodySpec post();

    RestClientRequestBodySpec put();

    RestClientRequestSpec delete();

    interface RestClientRequestSpec {

        RestClientRequestSpec uri(RestRequestUri r);

        RestClientRequestSpec headers(RestRequestHeaders r);

        RestClientResponseSpec retrieve() throws Exception;
    }

    interface RestClientRequestBodySpec extends RestClientRequestSpec {

        RestClientRequestBodySpec body();
    }

    interface RestClientResponseSpec {

        HttpStatus getStatus() throws IOException;

        HttpHeaders getHeaders();

        InputStream getBodyStream() throws IOException;

        String getBodyString() throws IOException;
    }
}
