package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.entity.FareDetail;
import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;

import java.util.List;
import java.util.Optional;

public interface FareDetailService {

    List<FareDetail> findAllFares();

    FareDetail findFareById(int theId);

    FareDetail saveFare(FareDetail theFareDetail);

    void deleteFareById(int theId);

    List<FareDetail> findByFlightNumber(FlightDetail flightNumber);

}
