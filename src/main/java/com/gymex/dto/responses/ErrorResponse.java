package com.gymex.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
public class ErrorResponse {
    private final int status;
    private final String message;
    private final LocalDateTime timestamp;

    public ErrorResponse(int status, String message,LocalDateTime localDateTime) {
        this.status = status;
        this.message = message;
        this.timestamp = localDateTime;
    }

    public int getStatusCode() {
        return status;
    }
}
