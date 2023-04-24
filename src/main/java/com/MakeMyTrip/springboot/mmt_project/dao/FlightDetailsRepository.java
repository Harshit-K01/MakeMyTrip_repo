package com.MakeMyTrip.springboot.mmt_project.dao;

import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FlightDetailsRepository extends JpaRepository<FlightDetail, Integer> {

    //METHOD FOR FETCHING FLIGHTS
    List<FlightDetail> findBySourceAndDestinationAndDepartDay(String source, String destination, LocalDate departDay, Pageable pageable);

    //METHOD FOR FETCHING FLIGHTS BY FILTERING
    List<FlightDetail> findBySourceAndDestinationAndDepartDayAndDepartTimeBetween(String source, String destination, LocalDate departDay, LocalTime start,LocalTime end, Pageable pageable);
}


