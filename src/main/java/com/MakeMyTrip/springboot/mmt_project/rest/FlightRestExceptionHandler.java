package com.MakeMyTrip.springboot.mmt_project.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class FlightRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<FlightErrorResponse> handleException(RuntimeException exc) {

        FlightErrorResponse error=new FlightErrorResponse();
        error.setMessage(exc.getMessage());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<FlightErrorResponse> handleException(DateTimeParseException exc){

        FlightErrorResponse error=new FlightErrorResponse();

        error.setMessage(exc.getMessage());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String,String>> handleException(MethodArgumentNotValidException exc) {

        Map<String,String> resp=new HashMap<>();
        exc.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldname=((FieldError) error).getField();
            String message=error.getDefaultMessage();
            resp.put(fieldname,message);
        });
        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
    }
}
