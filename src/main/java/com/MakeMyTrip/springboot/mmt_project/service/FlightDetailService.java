package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.dto.FlightDetailDTO;
import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightDetailService {

    List<FlightDetailDTO> getFlights(String source,
                                     String destination,
                                     LocalDate departDay,
                                     String classType,
                                     String returnTrip,
                                     LocalDate returnDate,
                                     String sortType,
                                     String departureType,
                                     Integer pageNumber,
                                     Integer pageSize);

    List<FlightDetailDTO> findAllFlights();

    FlightDetail findById(int id);

    FlightDetail findFlightById(int theId);

    List<FlightDetailDTO> findFlight(String source, String destination, LocalDate departDay, String classType, String sortType, String departureType,
                                     Integer pageNumber, Integer pageSize );


    FlightDetail saveFlight(FlightDetail theFlightDetail);

    void deleteFlightById(int theId);
}
