package com.MakeMyTrip.springboot.mmt_project.rest;

import com.MakeMyTrip.springboot.mmt_project.dto.FlightDetailDTO;
import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import com.MakeMyTrip.springboot.mmt_project.service.FlightDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight_api")
public class FlightDetailRestController {

    private FlightDetailService flightDetailService;

    // CONSTRUCTOR
    @Autowired
    public FlightDetailRestController(FlightDetailService theFlightDetailService){
        flightDetailService=theFlightDetailService;
    }


    //API FOR FINDING ALL FLIGHTS
    @GetMapping("/flightsAll")
    public List<FlightDetail> findAllFlights(){
        return flightDetailService.findAllFlights();
    }

    //API FOR FINDING FLIGHTS BY FLIGHT NUMBER
    @GetMapping("/flights/{flightNum}")
    public Optional<FlightDetail> getFlightDetail(@PathVariable int flightNum) {

        Optional<FlightDetail> theFlightDetail=flightDetailService.findFlightById(flightNum);

        if (theFlightDetail==null){
            throw new RuntimeException("Flight number not found -"+ flightNum);
        }

        return theFlightDetail;
    }

    //API FOR FETCHING FLIGHTS BASED ON PARAMS
    @GetMapping("/flights")
    public List<FlightDetailDTO> getFlight(@RequestParam String source, @RequestParam String destination, @RequestParam LocalDate departDay, @RequestParam String classType, @RequestParam Boolean returnTrip, @RequestParam(required = false) LocalDate returnDate, @RequestParam(required = false, defaultValue = "null") String sortType, @RequestParam(required = false, defaultValue = "null") String filterType){

        List<FlightDetailDTO> flights = new ArrayList<>();

        //LIST FOR ONE WAY FLIGHTS
        List<FlightDetailDTO> oneWayFlight=flightDetailService.findFlight(source,destination,departDay,classType,sortType,filterType);
            flights.addAll(oneWayFlight);

        //LIST FOR RETURN TRIP FLIGHTS
        if (returnTrip==true){
            List<FlightDetailDTO> returnFlight = flightDetailService.findFlight(destination, source, returnDate, classType,sortType,filterType);
                flights.addAll(returnFlight);
        }

        if (flights==null){
            throw new RuntimeException("Flight not found");
        }

        return flights;

    }

    //API FOR ADDING FLIGHTS
    @PostMapping("/flights")
    public FlightDetail addFlight(@RequestBody FlightDetail theFlightDetail){

        theFlightDetail.setFlightNumber(0);
        FlightDetail dbFlight=flightDetailService.saveFlight(theFlightDetail);
        return dbFlight;
    }

    //API FOR UPDATING FLIGHTS
    @PutMapping("/flights")
    public FlightDetail updateFlightDetail(@RequestBody FlightDetail theFlightDetail){

        FlightDetail dbFlightDetail=flightDetailService.saveFlight(theFlightDetail);
        return dbFlightDetail;

    }

    //API FOR DELETING FLIGHT
    @DeleteMapping("/flights/{flightNumber}")
    public String deleteFLight(@PathVariable int flightNumber){

        Optional<FlightDetail> tempFlight=flightDetailService.findFlightById(flightNumber);

        if (tempFlight==null) {
            throw new RuntimeException("Flight number not found -"+ flightNumber);
        }

        flightDetailService.deleteFlightById(flightNumber);
        return "Deleted flight number - " + flightNumber;

    }

}





















