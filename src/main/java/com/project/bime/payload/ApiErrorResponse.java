package com.project.bime.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiErrorResponse {
    private int status;
    private String message;
    private long timestamp;
    private String path;
    private Map<String, String> validationErrors;

    public ApiErrorResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = new Date().getTime();
    }
}
