package com.aa.vehiclemanagement.exception;

import lombok.*;

@Data
public class ResponseError  {
    private String message;

    public ResponseError(String message) {
        this.message = message;
    }
}
