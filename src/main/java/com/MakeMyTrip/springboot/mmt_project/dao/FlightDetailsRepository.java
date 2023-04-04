package com.MakeMyTrip.springboot.mmt_project.dao;

import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface FlightDetailsRepository extends JpaRepository<FlightDetail, Integer> {

    @Query("Select f.flightNumber, f.airline, f.arriveDay,f.departDay,f.departTime,f.arriveTime,f.source,f.destination, fd.classType, fd.fare from FlightDetail f, FareDetail fd where f.flightNumber=fd.flightNumber and f.source=?1 and f.destination=?2 and f.departDay=?3 and fd.classType=?4 ")
    List<Object> findBySourceAndDestinationAndDepartDayAndClassType(@Param("source") String source, @Param("destination") String destination, @Param("departDay") LocalDate departDay, @Param("classType") String classType);
}