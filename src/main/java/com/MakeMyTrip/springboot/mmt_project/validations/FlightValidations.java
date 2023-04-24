package com.MakeMyTrip.springboot.mmt_project.validations;

import com.MakeMyTrip.springboot.mmt_project.rest.FlightExceptions;

import java.time.LocalDate;

public class FlightValidations {

    public void isValidDepartDate(LocalDate departDate){
        if (departDate.isBefore(LocalDate.now())){
            throw new FlightExceptions("the date is not valid");
        }
    }

    public void isValidReturnDate(LocalDate returnDate, LocalDate departDate){
        if (returnDate.isBefore(departDate)){
            throw new FlightExceptions("return date should be after the departure date");
        }
    }

    public void isValidClassType(String classType){

        String ct=classType.toLowerCase();

        if (!(ct.equals("economy") || ct.equals("business"))){
            throw new FlightExceptions("classType must be either Economy or Business");
        }

    }

    public void isValidReturnTrip(String returnTrip){

        if (!(returnTrip.toLowerCase().equals("true") || returnTrip.toLowerCase().equals("false"))){
            throw new FlightExceptions("return trip parameter should be either true or false");
        }
    }

    public void isValidSortType(String sortType){

        if (!(sortType.toLowerCase().equals("fare") || sortType.toLowerCase().equals("duration"))){
            throw new FlightExceptions("sort type parameter should be either Fare or Duration");
        }
    }

    public void isValidDepartureType(String departureType){

        if (!(departureType.toLowerCase().equals("late") || departureType.toLowerCase().equals("morning"))){
            throw new FlightExceptions("departure type parameter should be either late or morning");
        }
    }



}
