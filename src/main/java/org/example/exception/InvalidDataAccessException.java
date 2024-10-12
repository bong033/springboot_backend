package org.example.exception;

public class InvalidDataAccessException extends RuntimeException {

    public InvalidDataAccessException(String message) {
        super(message);
    }
}
