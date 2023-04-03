package com.MakeMyTrip.springboot.mmt_project.dao;

import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface FlightDetailsRepository extends JpaRepository<FlightDetail, Integer> {

    public List<FlightDetail> findBySourceAndDestinationAndDepartDay(String source, String destination, Date departDay);
}
