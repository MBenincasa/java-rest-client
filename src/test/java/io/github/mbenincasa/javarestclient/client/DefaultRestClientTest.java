package io.github.mbenincasa.javarestclient.client;

import io.github.mbenincasa.javarestclient.exception.RestClientException;
import io.github.mbenincasa.javarestclient.http.HttpStatus;
import io.github.mbenincasa.javarestclient.http.MediaType;
import io.github.mbenincasa.javarestclient.support.HeadersBuilder;
import io.github.mbenincasa.javarestclient.support.UriBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class DefaultRestClientTest {

    private RestClient restClient;

    @BeforeEach
    void setUp() {
        restClient = new DefaultRestClient();
    }

    @Test
    public void testGetRequest() throws RestClientException {
        var response = restClient.get()
                .uri(UriBuilder.create()
                        .uri(URI.create("https://reqres.in/api/users/2"))
                        .build())
                .headers(HeadersBuilder.create()
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .build())
                .retrieve();

        assertNotNull(response);
        assertNotNull(response.getBodyAsString());
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    public void testPostRequest() throws RestClientException {
        var response = restClient.post()
                .uri(UriBuilder.create()
                        .uri(URI.create("https://reqres.in/api/users"))
                        .build())
                .headers(HeadersBuilder.create()
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .build())
                .body(new ReqResPostRequest("Mark", "Dev"))
                .retrieve();

        assertNotNull(response);
        assertNotNull(response.getBodyAsString());
        assertEquals(HttpStatus.CREATED, response.getStatus());
    }

    @Test
    public void testPutRequest() throws RestClientException {
        var response = restClient.put()
                .uri(UriBuilder.create()
                        .uri(URI.create("https://reqres.in/api/users/2"))
                        .build())
                .headers(HeadersBuilder.create()
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .build())
                .body(new ReqResPutRequest("Mark", "Dev"))
                .retrieve();

        assertNotNull(response);
        assertNotNull(response.getBodyAsString());
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    public void testDeleteRequest() throws RestClientException {
        var response = restClient.delete()
                .uri(UriBuilder.create()
                        .uri(URI.create("https://reqres.in/api/users/2"))
                        .build())
                .headers(HeadersBuilder.create()
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .build())
                .retrieve();

        assertNotNull(response);
        assertNull(response.getBodyAsString());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }
}