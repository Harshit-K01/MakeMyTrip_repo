package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface FlightDetailService {

    List<FlightDetail> findAllFlights();

    Optional<FlightDetail> findFlightById(int theId);

    List<FlightDetail> findFlight(String source, String destination, Date departDay);

    FlightDetail saveFlight(FlightDetail theFlightDetail);

    void deleteFlightById(int theId);
}
