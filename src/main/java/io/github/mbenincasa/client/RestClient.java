package io.github.mbenincasa.client;

import io.github.mbenincasa.support.RestClientRequestUri;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface RestClient {

    RestClientRequestSpec get();

    RestClientRequestBodySpec post();

    interface RestClientRequestSpec {

        RestClientRequestSpec uri(RestClientRequestUri r);

        RestClientResponseSpec retrieve() throws Exception;
    }

    interface RestClientRequestBodySpec extends RestClientRequestSpec {

        RestClientRequestBodySpec body();
    }

    interface RestClientResponseSpec {

        int getStatus() throws IOException;

        Map<String, List<String>> getHeaders();

        InputStream getBodyStream() throws IOException;

        String getBodyString() throws IOException;
    }
}
