package com.MakeMyTrip.springboot.mmt_project.rest;

public class FlightErrorResponse {
    private String message;

    public FlightErrorResponse(){

    }

    public FlightErrorResponse( String message) {

        this.message = message;

    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
