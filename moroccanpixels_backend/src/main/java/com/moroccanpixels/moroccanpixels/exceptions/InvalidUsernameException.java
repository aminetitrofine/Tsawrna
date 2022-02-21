package com.moroccanpixels.moroccanpixels.exceptions;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException(String message) {
        super(message);
    }
    public InvalidUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}
