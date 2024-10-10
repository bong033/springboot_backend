package org.example.exception;

public class PasswordBlankException extends RuntimeException {

    public PasswordBlankException() {
        super("Password cannot be blank.");
    }
}

