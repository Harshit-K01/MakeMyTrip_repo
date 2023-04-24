package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.dao.FlightDetailsRepository;
import com.MakeMyTrip.springboot.mmt_project.dto.FlightDetailDTO;
import com.MakeMyTrip.springboot.mmt_project.dto.FlightDetailDTOMapper;
import com.MakeMyTrip.springboot.mmt_project.entity.FareDetail;
import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import com.MakeMyTrip.springboot.mmt_project.validations.FlightValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightDetailServiceImpl implements FlightDetailService{

    private FlightDetailsRepository flightDetailsRepository;
    private final FlightDetailDTOMapper flightDetailDTOMapper;

    @Autowired
    public FlightDetailServiceImpl(FlightDetailsRepository theflightDetailsRepository, FlightDetailDTOMapper flightDetailDTOMapper){
        flightDetailsRepository=theflightDetailsRepository;
        this.flightDetailDTOMapper = flightDetailDTOMapper;
    }

    // GENERAL METHOD TO GET FLIGHTS BY PARAMETERS OR WITHOUT PARAMETERS
    @Override
    public List<FlightDetailDTO>  getFlights(String source,
                                            String destination,
                                            LocalDate departDay,
                                            String classType,
                                            String returnTrip,
                                            LocalDate returnDate,
                                            String sortType,
                                            String departureType,
                                            Integer pageNumber,
                                            Integer pageSize) {

        FlightValidations flightValidations=new FlightValidations();

        //VALIDATIONS
        flightValidations.isValidDepartDate(departDay);

        if (returnTrip.equals("true")){
            flightValidations.isValidReturnDate(returnDate,departDay);
        }

        flightValidations.isValidClassType(classType);

        flightValidations.isValidReturnTrip(returnTrip);

        if (!(sortType.equals("null"))){
            flightValidations.isValidSortType(sortType);
        }

        if (!(departureType.equals("null"))){
            flightValidations.isValidDepartureType(departureType);
        }

        List<FlightDetailDTO> flights = new ArrayList<>();

        //LIST FOR ONE WAY FLIGHTS
        List<FlightDetailDTO> oneWayFlight=findFlight(source,destination,departDay,classType,sortType,departureType,pageNumber,pageSize);
        flights.addAll(oneWayFlight);

        //LIST FOR RETURN TRIP FLIGHTS
        if (returnTrip.equals("true")){
            List<FlightDetailDTO> returnFlight = findFlight(destination, source, returnDate, classType,sortType,departureType,pageNumber,pageSize);
            flights.addAll(returnFlight);
        }

        if (flights==null){
            throw new RuntimeException("Flight not found");
        }

        return flights;
    }

    //METHOD TO FIND ALL FLIGHTS
    @Override
    public List<FlightDetailDTO> findAllFlights() {
        return flightDetailsRepository.findAll().stream()
                .map(flightDetailDTOMapper)
                .collect(Collectors.toList());
    }

    //METHOD TO FIND FLIGHT BY ID
    @Override
    public FlightDetail findById(int id) {
        return flightDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public FlightDetail findFlightById(int theId) {
        return flightDetailsRepository.findById(theId).orElse(null);
    }

    //METHOD TO FIND FLIGHT BY PARAMETERS
    @Override
    public List<FlightDetailDTO> findFlight(String source, String destination, LocalDate departDay, String classType, String sortType, String departureType,Integer pageNumber,Integer pageSize) {

        List<FlightDetail> flights=new ArrayList<>();

        //DEFAULT START AND END TIME
        LocalTime start=null;
        LocalTime end=null;

        //DEFAULT SORTBY TYPE
        Pageable sortByDuration=null;
        Pageable sortByFare= null;

        //SORTING LOGIC
        if (sortType.equals("duration")){
            sortByDuration=PageRequest.of(pageNumber,pageSize,Sort.by("duration"));
        } else if (sortType.equals("fare")) {
            sortByFare=PageRequest.of(pageNumber,pageSize,Sort.by("fareDetails.fare"));
        }

        Pageable sendFilter=null;

        if (sortByDuration != null){
            sendFilter=sortByDuration;
        } else if (sortByFare != null) {
            sendFilter=sortByFare;
        }
        else sendFilter=PageRequest.of(pageNumber,pageSize);

        //LIST OF FLIGHTS WITHOUT ANY FILTERING
        flights=flightDetailsRepository.findBySourceAndDestinationAndDepartDay(source, destination,departDay,sendFilter);

        //FILTERING LOGIC FOR MORNING AND LATE DEPARTURES
        if (departureType.equals("Morning")){

            start=LocalTime.of(5,0);
            end=LocalTime.of(12,0);
            flights=flightDetailsRepository.findBySourceAndDestinationAndDepartDayAndDepartTimeBetween(source, destination,departDay,start,end,sendFilter);

        } else if (departureType.equals("Late")) {

            start=LocalTime.of(18,0);
            end=LocalTime.of(23,59);
            flights=flightDetailsRepository.findBySourceAndDestinationAndDepartDayAndDepartTimeBetween(source, destination,departDay,start,end,sendFilter);

        }

        //LOGIC FOR REMOVING UNNECESSARY CLASSTYPE FROM FLIGHTS
        for (FlightDetail flight : flights) {
            flight.getFareDetails().removeIf(fareDetail -> !fareDetail.getClassType().equals(classType));
        }

        return flights
                .stream()
                .map(flightDetailDTOMapper)
                .collect(Collectors.toList());

    }

    //METHOD TO SAVE FLIGHT
    @Transactional
    @Override
    public FlightDetail saveFlight(FlightDetail theFlightDetail) {

        return flightDetailsRepository.save(theFlightDetail);

    }

    //METHOD TO DELETE FLIGHT
    @Transactional
    @Override
    public void deleteFlightById(int theId) {

        flightDetailsRepository.deleteById(theId);
    }
}
