package com.MakeMyTrip.springboot.mmt_project.rest;

import com.MakeMyTrip.springboot.mmt_project.dto.FlightDetailDTO;
import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import com.MakeMyTrip.springboot.mmt_project.service.FlightDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/flight-api")
public class FlightDetailRestController {

    private FlightDetailService flightDetailService;

    // CONSTRUCTOR
    @Autowired
    public FlightDetailRestController(FlightDetailService theFlightDetailService){
        flightDetailService=theFlightDetailService;
    }

    //API FOR FINDING ALL FLIGHTS
    @GetMapping("/all-flights")
    public List<FlightDetailDTO> findAllFlights(){
        return flightDetailService.findAllFlights();
    }

    //API FOR FINDING FLIGHTS BY FLIGHT NUMBER
    @GetMapping("/flights/{flight-Num}")
    public FlightDetail getFlightDetail(@PathVariable int flightNum) {
        FlightDetail theFlightDetail=flightDetailService.findFlightById(flightNum);
        if (theFlightDetail==null){
            throw new FlightExceptions("Flight number not found - "+ flightNum);
        }
        return theFlightDetail;
    }

    //API FOR FETCHING FLIGHTS BASED ON PARAMS
    @GetMapping("/flights")
    public List<FlightDetailDTO> getFlights(@Valid @RequestParam String source,
                                           @RequestParam String destination,
                                           @RequestParam LocalDate departDay,
                                           @RequestParam String classType,
                                           @RequestParam(required = false,defaultValue = "false") String returnTrip,
                                           @RequestParam(required = false) LocalDate returnDate,
                                           @RequestParam(required = false, defaultValue = "null") String sortType,
                                           @RequestParam(required = false, defaultValue = "null") String departureType,
                                           @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                           @RequestParam(required = false, defaultValue = "5") Integer pageSize){


        List<FlightDetailDTO> flights = flightDetailService.getFlights(source,destination,departDay,classType,returnTrip,returnDate,sortType,departureType,pageNumber,pageSize);

        return flights;

    }

    //API FOR ADDING FLIGHTS
    @PostMapping("/flights")
    public FlightDetail addFlight(@Valid @RequestBody FlightDetail theFlightDetail){

            theFlightDetail.setFlightNumber(0);
            FlightDetail dbFlight=flightDetailService.saveFlight(theFlightDetail);
            return dbFlight;

    }

    //API FOR UPDATING FLIGHTS
    @PutMapping("/flights")
    public FlightDetail updateFlightDetail( @RequestBody FlightDetail theFlightDetail){

        FlightDetail dbFlightDetail=flightDetailService.saveFlight(theFlightDetail);
        return dbFlightDetail;

    }

    //API FOR DELETING FLIGHT
    @DeleteMapping("/flights/{flightNumber}")
    public String deleteFLight(@PathVariable int flightNumber){

        FlightDetail tempFlight=flightDetailService.findFlightById(flightNumber);

        if (tempFlight==null) {
            throw new FlightExceptions("Flight number not found -"+ flightNumber);
        }

        flightDetailService.deleteFlightById(flightNumber);
        return "Deleted flight number - " + flightNumber;

    }



}





















