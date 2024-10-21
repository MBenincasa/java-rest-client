package io.github.mbenincasa.javarestclient.client;

import io.github.mbenincasa.javarestclient.client.request.booking.BookingPostRequest;
import io.github.mbenincasa.javarestclient.client.request.reqres.ReqResPatchRequest;
import io.github.mbenincasa.javarestclient.client.request.reqres.ReqResPostRequest;
import io.github.mbenincasa.javarestclient.client.request.reqres.ReqResPutRequest;
import io.github.mbenincasa.javarestclient.client.response.openweathermap.GeocodingLocationDTO;
import io.github.mbenincasa.javarestclient.client.response.booking.BookingGetResponse;
import io.github.mbenincasa.javarestclient.client.response.booking.BookingPostResponse;
import io.github.mbenincasa.javarestclient.client.response.reqres.*;
import io.github.mbenincasa.javarestclient.exception.RestClientException;
import io.github.mbenincasa.javarestclient.http.HttpStatus;
import io.github.mbenincasa.javarestclient.http.MediaType;
import io.github.mbenincasa.javarestclient.support.HeadersBuilder;
import io.github.mbenincasa.javarestclient.support.UriBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
                        .uri("https://reqres.in/api/users/{id}")
                        .pathVariable("id", 2)
                        .build())
                .headers(HeadersBuilder.create()
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
    public void testGetRequest2() throws RestClientException {
        var response = restClient.get()
                .uri(UriBuilder.create()
                        .uri("https://restful-booker.herokuapp.com/booking/{id}")
                        .pathVariable("id", 2)
                        .build())
                .headers(HeadersBuilder.create()
                        .accept(MediaType.APPLICATION_XML)
                        .build())
                .retrieve();

        assertNotNull(response);
        BookingGetResponse payload = response.getBody(BookingGetResponse.class);
        assertNotNull(payload);
        assertNotNull(payload.getFirstname());
        assertNotNull(payload.getLastname());
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    public void testPostRequest() throws RestClientException {
        var response = restClient.post()
                .uri(UriBuilder.create()
                        .uri("https://reqres.in/api/users")
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
    public void testPostRequest2() throws RestClientException {
        var response = restClient.post()
                .uri(UriBuilder.create()
                        .uri("https://restful-booker.herokuapp.com/booking")
                        .build())
                .headers(HeadersBuilder.create()
                        .contentType(MediaType.TEXT_XML)
                        .accept(MediaType.APPLICATION_XML)
                        .build())
                .body(new BookingPostRequest("Mark", "Jackson", 10, true, new BookingPostRequest.BookingPostRequestDates("2022-01-01", "2023-01-01"), "Breakfast"))
                .retrieve();

        assertNotNull(response);
        BookingPostResponse payload = response.getBody(BookingPostResponse.class);
        assertNotNull(payload);
        assertNotNull(payload.getBooking());
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    public void testPutRequest() throws RestClientException {
        var response = restClient.put()
                .uri(UriBuilder.create()
                        .uri("https://reqres.in/api/users/{id}")
                        .pathVariable("id", 2)
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
                        .uri("https://reqres.in/api/users/{id}")
                        .pathVariable("id", 2)
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
                        .uri("https://reqres.in/api/users/{id}")
                        .pathVariable("id", 2)
                        .build())
                .retrieve();

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }

    @Test
    public void testHeadRequest() throws RestClientException {
        var response = restClient.head()
                .uri(UriBuilder.create()
                        .uri("https://reqres.in/api/users/{id}")
                        .pathVariable("id", 2)
                        .build())
                .retrieve();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    public void testOptionsRequest() throws RestClientException {
        var response = restClient.options()
                .uri(UriBuilder.create()
                        .uri("https://reqres.in/api/users/{id}")
                        .pathVariable("id", 2)
                        .build())
                .retrieve();

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatus());
    }

    @Test
    public void testRequestWithQueryParam() throws RestClientException {
        var response = restClient.get()
                .uri(UriBuilder.create()
                        .uri("https://reqres.in/api/users")
                        .queryParam("page", 2)
                        .build())
                .headers(HeadersBuilder.create()
                        .accept(MediaType.APPLICATION_JSON)
                        .build())
                .retrieve();

        assertNotNull(response);
        ReqResGetQueryParamResponse payload = response.getBody(ReqResGetQueryParamResponse.class);
        assertNotNull(payload);
        assertEquals(2, payload.getPage());
        assertEquals(6, payload.getPerPage());
        assertEquals(12, payload.getTotal());
        assertEquals(2, payload.getTotalPages());
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    /*
    @Test
    public void testRequestGetList() throws RestClientException {
        var response = restClient.get()
                .uri(UriBuilder.create()
                        .uri("http://api.openweathermap.org/geo/1.0/direct")
                        .queryParam("appid", "API_KEY")
                        .queryParam("q", "London,England,GB")
                        .queryParam("limit", 1)
                        .build())
                .headers(HeadersBuilder.create()
                        .accept(MediaType.APPLICATION_JSON)
                        .build())
                .retrieve();

        assertNotNull(response);
        List<GeocodingLocationDTO> payload = response.getBodyAsList(GeocodingLocationDTO.class);
        assertNotNull(payload);
        assertEquals(1, payload.size());
        GeocodingLocationDTO dto = payload.get(0);
        assertNotNull(dto);
        assertEquals("London", dto.getName());
        assertEquals("GB", dto.getCountry());
        assertEquals("England", dto.getState());
        assertEquals("Londra", dto.getLocalNames().getIt());
    }
    */

    /*
    @Test
    public void testRequestGetFile() throws RestClientException {
        var response = restClient.get()
                .uri(UriBuilder.create()
                        .uri("https://tile.openweathermap.org/map/{layer}/{z}/{x}/{y}.png")
                        .pathVariable("layer", "temp_new")
                        .pathVariable("z", 0)
                        .pathVariable("x", 0)
                        .pathVariable("y", 0)
                        .queryParam("appid", "API_KEY")
                        .build())
                .retrieve();

        var fileRaw = response.getBodyAsRaw();

        assertNotNull(fileRaw);
        assertTrue(fileRaw.length > 0);
    }
    */
}