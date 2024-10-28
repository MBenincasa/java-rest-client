package io.github.mbenincasa.javarestclient.exception;

public class RestClientException extends Exception {

    public RestClientException(String message) {
        super(message);
    }

    public RestClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
