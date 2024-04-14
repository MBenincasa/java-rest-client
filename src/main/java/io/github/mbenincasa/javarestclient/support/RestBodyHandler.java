package io.github.mbenincasa.javarestclient.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mbenincasa.javarestclient.http.MediaType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class RestBodyHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T deserialize(byte[] body, Class<T> bodyType, MediaType contentType) throws IOException {
        if (body == null || body.length == 0) {
            return null;
        }
        if (Objects.requireNonNull(contentType) == MediaType.APPLICATION_JSON) {
            return objectMapper.readValue(body, bodyType);
        }
        throw new RuntimeException();
    }

    public static String deserialize(byte[] body) {
        if (body == null || body.length == 0) {
            return null;
        }
        return new String(body, StandardCharsets.UTF_8);
    }

    public static byte[] serialize(Object body, MediaType contentType) throws JsonProcessingException {
        if (Objects.requireNonNull(contentType) == MediaType.APPLICATION_JSON) {
            return objectMapper.writeValueAsBytes(body);
        }
        throw new RuntimeException();
    }
}
