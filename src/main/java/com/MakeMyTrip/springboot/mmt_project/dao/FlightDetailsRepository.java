package com.MakeMyTrip.springboot.mmt_project.dao;

import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface FlightDetailsRepository extends JpaRepository<FlightDetail, Integer> {

    //METHOD FOR FETCHING FLIGHTS
    List<FlightDetail> findBySourceAndDestinationAndDepartDay(String source, String destination, LocalDate departDay, Sort sort);

    //METHOD FOR FETCHING FLIGHTS BY FILTERING
    List<FlightDetail> findBySourceAndDestinationAndDepartDayAndDepartTimeBetween(String source, String destination, LocalDate departDay, LocalTime start,LocalTime end, Sort sort);
}


