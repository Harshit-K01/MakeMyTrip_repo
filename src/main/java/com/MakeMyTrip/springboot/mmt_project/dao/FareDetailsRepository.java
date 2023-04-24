package com.MakeMyTrip.springboot.mmt_project.dao;

import com.MakeMyTrip.springboot.mmt_project.entity.FareDetail;
import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FareDetailsRepository extends JpaRepository<FareDetail, Integer> {

    boolean existsByFareId(Integer fareId);
    List<FareDetail> findByFlightNumber(FlightDetail flightNumber);
}
