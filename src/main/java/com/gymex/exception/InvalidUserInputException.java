package com.gymex.exception;

public class InvalidUserInputException extends UserServiceException {
    public InvalidUserInputException(String message) {
        super(message);
    }
}
