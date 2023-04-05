package com.MakeMyTrip.springboot.mmt_project.rest;

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
    public List<FlightDetail> getFlight(@RequestParam String source, @RequestParam String destination, @RequestParam LocalDate departDay, @RequestParam String classType,@RequestParam Boolean returnTrip,@RequestParam(required = false) LocalDate returnDate,@RequestParam(required = false, defaultValue = "null") String sortType){

        List<FlightDetail> flights = new ArrayList<>();

            List<FlightDetail> oneWayFlight=flightDetailService.findFlight(source,destination,departDay,classType,sortType);
            flights.addAll(oneWayFlight);



        if (returnTrip==true){

            List<FlightDetail> returnFlight = flightDetailService.findFlight(destination, source, returnDate, classType,sortType);
                flights.addAll(returnFlight);
        }


        if (flights==null){
            throw new RuntimeException("Flight not found");
        }

        return flights;

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





















