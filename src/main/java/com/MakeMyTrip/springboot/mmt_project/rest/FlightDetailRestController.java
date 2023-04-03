package com.MakeMyTrip.springboot.mmt_project.rest;

import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import com.MakeMyTrip.springboot.mmt_project.service.FlightDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flight_api")
public class FlightDetailRestController {

    private FlightDetailService flightDetailService;

    @Autowired
    public FlightDetailRestController(FlightDetailService theFlightDetailService){
        flightDetailService=theFlightDetailService;
    }

    @GetMapping("/flightsAll")
    public List<FlightDetail> findAllFlights(){
        return flightDetailService.findAllFlights();
    }

    @GetMapping("/flights/{flightNum}")
    public Optional<FlightDetail> getFlightDetail(@PathVariable int flightNum) {
        Optional<FlightDetail> theFlightDetail=flightDetailService.findFlightById(flightNum);

        if (theFlightDetail==null){
            throw new RuntimeException("Flight number not found -"+ flightNum);
        }

        return theFlightDetail;
    }

    @GetMapping("/flights")
    public List<Object> getFlight(@RequestParam String source, @RequestParam String destination, @RequestParam LocalDate departDay, @RequestParam String classType){
        List<Object> theFlight=flightDetailService.findFlight(source,destination,departDay,classType);

        if (theFlight==null){
            throw new RuntimeException("Flight not found");
        }

        return theFlight;


    }
    @PostMapping("/flights")
    public FlightDetail addFlight(@RequestBody FlightDetail theFlightDetail){

        theFlightDetail.setFlightNumber(0);

        FlightDetail dbFlight=flightDetailService.saveFlight(theFlightDetail);

        return dbFlight;
    }

    @PutMapping("/flights")
    public FlightDetail updateFlightDetail(@RequestBody FlightDetail theFlightDetail){
        FlightDetail dbFlightDetail=flightDetailService.saveFlight(theFlightDetail);

        return dbFlightDetail;
    }

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





















