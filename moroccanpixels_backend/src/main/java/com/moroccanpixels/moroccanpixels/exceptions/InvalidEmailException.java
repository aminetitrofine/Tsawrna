package com.moroccanpixels.moroccanpixels.exceptions;

public class InvalidEmailException extends RuntimeException{

    public InvalidEmailException(String message) {
        super(message);
    }

    public InvalidEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
