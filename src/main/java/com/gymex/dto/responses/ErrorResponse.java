package com.gymex.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String errorCode;
    private Map<String, String> errors;

    public ErrorResponse(int status, String message, LocalDateTime localDateTime, String errorCode, Map<String, String> errors) {
        this.status = status;
        this.message = message;
        this.timestamp = localDateTime;
        this.errorCode = errorCode;
        this.errors = errors;

    }

    public ErrorResponse(int status, String message, LocalDateTime localDateTime, String errorCode) {
        this.status = status;
        this.message = message;
        this.timestamp = localDateTime;
        this.errorCode = errorCode;
    }


    public int getStatusCode() {
        return status;
    }
}
