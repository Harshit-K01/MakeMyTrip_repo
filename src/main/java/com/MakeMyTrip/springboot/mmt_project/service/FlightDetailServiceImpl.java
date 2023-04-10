package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.dao.FlightDetailsRepository;
import com.MakeMyTrip.springboot.mmt_project.dto.FlightDetailDTO;
import com.MakeMyTrip.springboot.mmt_project.dto.FlightDetailDTOMapper;
import com.MakeMyTrip.springboot.mmt_project.entity.FareDetail;
import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @Override
    public List<FlightDetail> findAllFlights() {
        return flightDetailsRepository.findAll();
    }

    @Override
    public Optional<FlightDetail> findFlightById(int theId) {
        return flightDetailsRepository.findById(theId);
    }

    @Override
    public List<FlightDetailDTO> findFlight(String source, String destination, LocalDate departDay, String classType, String sortType, String filterType) {

        List<FlightDetail> flights=new ArrayList<>();

        //DEFAULT START AND END TIME
        LocalTime start=null;
        LocalTime end=null;

        //DEFAULT SORTBY TYPE
        Sort sortByDuration=null;
        Sort sortByFare= null;

        //SORTING LOGIC
        if (sortType.equals("duration")){
            sortByDuration=Sort.by("duration");
        } else if (sortType.equals("fare")) {
            sortByFare=Sort.by("fareDetails.fare");
        }

        Sort sendFilter=null;

        if (sortByDuration != null){
            sendFilter=sortByDuration;
        } else if (sortByFare != null) {
            sendFilter=sortByFare;
        }

        //LIST OF FLIGHTS WITHOUT ANY FILTERING
        flights=flightDetailsRepository.findBySourceAndDestinationAndDepartDay(source, destination,departDay,sendFilter);

        //FILTERING LOGIC FOR MORNING AND LATE DEPARTURES
        if (filterType.equals("Morning departure")){

            start=LocalTime.of(5,0);
            end=LocalTime.of(12,0);
            flights=flightDetailsRepository.findBySourceAndDestinationAndDepartDayAndDepartTimeBetween(source, destination,departDay,start,end,sendFilter);

        } else if (filterType.equals("Late departure")) {

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

    @Transactional
    @Override
    public FlightDetail saveFlight(FlightDetail theFlightDetail) {

        return flightDetailsRepository.save(theFlightDetail);

    }

    @Transactional
    @Override
    public void deleteFlightById(int theId) {

        flightDetailsRepository.deleteById(theId);
    }
}
