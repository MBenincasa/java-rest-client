package io.github.mbenincasa.javarestclient.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.github.mbenincasa.javarestclient.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class RestBodyHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestBodyHandler.class);
    private static final ObjectMapper jsonMapper  = new ObjectMapper();
    private static final XmlMapper xmlMapper = new XmlMapper();

    public static <T> T deserialize(byte[] body, Class<T> bodyType, MediaType contentType) throws IOException {
        logger.debug("Deserializing body with content type: {}", contentType);

        if (body == null || body.length == 0) {
            logger.info("Empty or null body provided for deserialization.");
            return null;
        }

        if (
                contentType == MediaType.APPLICATION_JSON ||
                contentType == MediaType.APPLICATION_VND_API_JSON
        ) {
            logger.trace("Using JSON mapper for deserialization.");
            return jsonMapper.readValue(body, bodyType);
        } else if (
                contentType == MediaType.APPLICATION_XML ||
                contentType == MediaType.APPLICATION_ATOM_XML ||
                contentType == MediaType.APPLICATION_RDF_XML ||
                contentType == MediaType.APPLICATION_RSS_XML ||
                contentType == MediaType.APPLICATION_XHTML_XML ||
                contentType == MediaType.TEXT_XML ||
                contentType == MediaType.TEXT_HTML
        ) {
            logger.trace("Using XML mapper for deserialization.");
            return xmlMapper.readValue(new String(body, StandardCharsets.UTF_8), bodyType);
        }

        throw new RuntimeException("Unsupported content type: " + contentType);
    }

    public static <T> List<T> deserializeList(byte[] body, Class<T> bodyType, MediaType contentType) throws IOException {
        logger.debug("Deserializing body as list with content type: {}", contentType);

        if (body == null || body.length == 0) {
            logger.info("Empty or null body provided for list deserialization.");
            return Collections.emptyList();
        }

        if (
                contentType == MediaType.APPLICATION_JSON ||
                contentType == MediaType.APPLICATION_VND_API_JSON
        ) {
            logger.trace("Using JSON mapper for list deserialization.");
            return jsonMapper.readValue(body, jsonMapper.getTypeFactory().constructCollectionType(List.class, bodyType));
        } else if (
                contentType == MediaType.APPLICATION_XML ||
                contentType == MediaType.APPLICATION_ATOM_XML ||
                contentType == MediaType.APPLICATION_RDF_XML ||
                contentType == MediaType.APPLICATION_RSS_XML ||
                contentType == MediaType.APPLICATION_XHTML_XML ||
                contentType == MediaType.TEXT_XML ||
                contentType == MediaType.TEXT_HTML) {

            logger.trace("Using XML mapper for list deserialization.");
            String bodyStr = new String(body, StandardCharsets.UTF_8);
            return xmlMapper.readValue(bodyStr, xmlMapper.getTypeFactory().constructCollectionType(List.class, bodyType));
        }

        throw new RuntimeException("Unsupported content type: " + contentType);
    }

    public static String deserialize(byte[] body) {
        logger.debug("Deserializing body to string.");

        if (body == null || body.length == 0) {
            logger.info("Empty or null body provided for string deserialization.");
            return null;
        }

        return new String(body, StandardCharsets.UTF_8);
    }

    public static byte[] serialize(Object body, MediaType contentType) throws JsonProcessingException {
        logger.debug("Serializing body with content type: {}", contentType);

        if (
                contentType == MediaType.APPLICATION_JSON ||
                contentType == MediaType.APPLICATION_VND_API_JSON
        ) {
            logger.trace("Using JSON mapper for serialization.");
            return jsonMapper.writeValueAsBytes(body);
        } else if (
                contentType == MediaType.APPLICATION_XML ||
                contentType == MediaType.APPLICATION_ATOM_XML ||
                contentType == MediaType.APPLICATION_RDF_XML ||
                contentType == MediaType.APPLICATION_RSS_XML ||
                contentType == MediaType.APPLICATION_XHTML_XML ||
                contentType == MediaType.TEXT_XML ||
                contentType == MediaType.TEXT_HTML
        ) {
            logger.trace("Using XML mapper for serialization.");
            return xmlMapper.writeValueAsBytes(body);
        }

        throw new RuntimeException("Unsupported content type: " + contentType);
    }
}
