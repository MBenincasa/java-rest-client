package io.github.mbenincasa.javarestclient.client;

import io.github.mbenincasa.javarestclient.client.request.ReqResPatchRequest;
import io.github.mbenincasa.javarestclient.client.request.ReqResPostRequest;
import io.github.mbenincasa.javarestclient.client.request.ReqResPutRequest;
import io.github.mbenincasa.javarestclient.client.response.ReqResGetResponse;
import io.github.mbenincasa.javarestclient.client.response.ReqResPatchResponse;
import io.github.mbenincasa.javarestclient.client.response.ReqResPostResponse;
import io.github.mbenincasa.javarestclient.client.response.ReqResPutResponse;
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
        ReqResGetResponse payload = response.getBody(ReqResGetResponse.class);
        assertNotNull(payload);
        assertNotNull(payload.getData());
        assertEquals(2, payload.getData().getId());
        assertEquals("janet.weaver@reqres.in", payload.getData().getEmail());
        assertEquals("Janet", payload.getData().getFirstName());
        assertEquals("Weaver", payload.getData().getLastName());
        assertNotNull(payload.getSupport());
        assertEquals("https://reqres.in/#support-heading", payload.getSupport().getUrl());
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
        ReqResPostResponse payload = response.getBody(ReqResPostResponse.class);
        assertNotNull(payload);
        assertEquals("Mark", payload.getName());
        assertEquals("Dev", payload.getJob());
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
        ReqResPutResponse payload = response.getBody(ReqResPutResponse.class);
        assertNotNull(payload);
        assertEquals("Mark", payload.getName());
        assertEquals("Dev", payload.getJob());
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    public void testPatchRequest() throws RestClientException {
        var response = restClient.patch()
                .uri(UriBuilder.create()
                        .uri(URI.create("https://reqres.in/api/users/2"))
                        .build())
                .headers(HeadersBuilder.create()
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .build())
                .body(new ReqResPatchRequest("Mark", "Dev"))
                .retrieve();

        assertNotNull(response);
        ReqResPatchResponse payload = response.getBody(ReqResPatchResponse.class);
        assertNotNull(payload);
        assertEquals("Mark", payload.getName());
        assertEquals("Dev", payload.getJob());
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
                        .build())
                .retrieve();

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }

    @Test
    public void testHeadRequest() throws RestClientException {
        var response = restClient.head()
                .uri(UriBuilder.create()
                        .uri(URI.create("https://reqres.in/api/users/2"))
                        .build())
                .headers(HeadersBuilder.create()
                        .contentType(MediaType.APPLICATION_JSON)
                        .build())
                .retrieve();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    public void testOptionsRequest() throws RestClientException {
        var response = restClient.options()
                .uri(UriBuilder.create()
                        .uri(URI.create("https://reqres.in/api/users/2"))
                        .build())
                .headers(HeadersBuilder.create()
                        .contentType(MediaType.APPLICATION_JSON)
                        .build())
                .retrieve();

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }
}