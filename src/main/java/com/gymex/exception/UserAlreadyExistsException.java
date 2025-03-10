package com.gymex.exception;

public class UserAlreadyExistsException extends UserServiceException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
