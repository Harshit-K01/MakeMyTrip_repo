package com.MakeMyTrip.springboot.mmt_project.dao;

import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface FlightDetailsRepository extends JpaRepository<FlightDetail, Integer> {

    //    @Query("Select f.flightNumber, f.airline, f.arriveDay,f.departDay,f.departTime,f.arriveTime,f.source,f.destination, fd.classType, fd.fare,f.duration from FlightDetail f, FareDetail fd where f.flightNumber=fd.flightNumber and f.source=?1 and f.destination=?2 and f.departDay=?3 and fd.classType=?4 ")
    List<FlightDetail> findBySourceAndDestinationAndDepartDay(String source, String destination, LocalDate departDay, Sort sort);
}


