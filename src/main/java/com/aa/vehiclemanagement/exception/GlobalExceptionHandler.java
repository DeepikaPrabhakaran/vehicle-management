package com.aa.vehiclemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<ResponseError> handleVehicleNotFoundException(VehicleNotFoundException ex){
        return new ResponseEntity<>(new ResponseError(ex.getMessage()),HttpStatus.NOT_FOUND);
    }
}
