package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.dao.FlightDetailsRepository;
import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightDetailServiceImpl implements FlightDetailService{

    private FlightDetailsRepository flightDetailsRepository;

    @Autowired
    public FlightDetailServiceImpl(FlightDetailsRepository theflightDetailsRepository){
        flightDetailsRepository=theflightDetailsRepository;
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
    public List<FlightDetail> findFlight(String source, String destination, Date departDay) {

        return flightDetailsRepository.findBySourceAndDestinationAndDepartDay(source, destination,departDay);
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