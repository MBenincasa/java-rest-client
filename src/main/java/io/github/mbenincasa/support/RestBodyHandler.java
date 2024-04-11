package io.github.mbenincasa.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mbenincasa.http.MediaType;

import java.io.IOException;
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

    public static String deserialize(byte[] body, MediaType contentType) throws IOException {
        if (body == null || body.length == 0) {
            return null;
        }

        if (Objects.requireNonNull(contentType) == MediaType.APPLICATION_JSON) {
            Object jsonObject = objectMapper.readValue(body, Object.class);
            return objectMapper.writeValueAsString(jsonObject);
        }
        throw new RuntimeException();
    }

    public static byte[] serialize(Object body, MediaType contentType) throws JsonProcessingException {
        if (Objects.requireNonNull(contentType) == MediaType.APPLICATION_JSON) {
            return objectMapper.writeValueAsBytes(body);
        }
        throw new RuntimeException();
    }
}
