package io.github.mbenincasa.javarestclient.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UriBuilder {

    private static final Logger logger = LoggerFactory.getLogger(UriBuilder.class);

    private String uri;
    private Map<String, List<Object>> queryParams;
    private Map<String, Object> pathVariables;

    private UriBuilder() {
        this.queryParams = new HashMap<>();
        this.pathVariables = new HashMap<>();
        logger.debug("UriBuilder initialized with empty query parameters and path variables.");
    }

    public static UriBuilder create() {
        logger.info("Creating new UriBuilder instance.");
        return new UriBuilder();
    }

    public UriBuilder uri(URI uri) {
        this.uri = uri.toString();
        logger.debug("URI set to: {}", this.uri);
        return this;
    }

    public UriBuilder uri(String uri) {
        this.uri = uri;
        logger.debug("URI set to: {}", this.uri);
        return this;
    }

    public UriBuilder queryParam(String name, Object... values) {
        logger.debug("Adding query parameters: {} with values: {}", name, values);
        if (!queryParams.containsKey(name)) {
            queryParams.put(name, new LinkedList<>());
        }
        for (Object value : values) {
            queryParams.get(name).add(value);
        }
        logger.trace("Current query parameters: {}", queryParams);
        return this;
    }

    public UriBuilder pathVariable(String name, Object value) {
        logger.debug("Adding path variable: {} with value: {}", name, value);
        pathVariables.put(name, value);
        logger.trace("Current path variables: {}", pathVariables);
        return this;
    }

    public RestRequestUri build() {
        logger.info("Building RestRequestUri from URI: {}", this.uri);
        String uriString = replacePathVariables(this.uri);
        StringBuilder uriBuilder = new StringBuilder(uriString);

        if (!queryParams.isEmpty()) {
            logger.debug("Appending query parameters to URI.");
            uriBuilder.append("?");
            queryParams.forEach((name, values) -> {
                for (Object value : values) {
                    uriBuilder.append(name)
                            .append("=")
                            .append(value.toString())
                            .append("&");
                }
            });
            uriBuilder.deleteCharAt(uriBuilder.length() - 1);
        }

        String finalUri = uriBuilder.toString();
        logger.debug("Final URI built: {}", finalUri);
        return new DefaultRestRequestUri(URI.create(finalUri));
    }

    private String replacePathVariables(String uriString) {
        logger.debug("Replacing path variables in URI: {}", uriString);
        for (Map.Entry<String, Object> entry : pathVariables.entrySet()) {
            String variable = "{" + entry.getKey() + "}";
            if (uriString.contains(variable)) {
                uriString = uriString.replace(variable, entry.getValue().toString());
                logger.trace("Replaced variable {} with value {}. Current URI: {}", variable, entry.getValue(), uriString);
            }
        }
        return uriString;
    }
}
