package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.dao.FareDetailsRepository;
import com.MakeMyTrip.springboot.mmt_project.entity.FareDetail;
import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FareDetailServiceImpl implements FareDetailService{

    private FareDetailsRepository fareDetailsRepository;

    @Autowired
    public FareDetailServiceImpl(FareDetailsRepository thefareDetailsRepository){
        fareDetailsRepository=thefareDetailsRepository;
    }

    //METHOD TO FIND ALL FARES
    @Override
    public List<FareDetail> findAllFares() {
        return fareDetailsRepository.findAll();
    }

    //METHOD TO FIND FARE BY ID
    @Override
    public FareDetail findFareById(int theId) {
        FareDetail fare=fareDetailsRepository.findById(theId).orElse(null);
        if (fare==null){
            throw new RuntimeException("fare not found");
        }else{
            return fare;
        }
    }

    //METHOD TO SAVE FARE
    @Transactional
    @Override
    public FareDetail saveFare(FareDetail theFareDetail) {
        return fareDetailsRepository.save(theFareDetail);
    }

    //METHOD TO DELETE FARE BY ID
    @Transactional
    @Override
    public void deleteFareById(int theId) {
        fareDetailsRepository.deleteById(theId);
    }

    //METHOD TO FIND FARE BY FLIGHT NUMBER
    @Override
    public List<FareDetail> findByFlightNumber(FlightDetail flightNumber) {
        return fareDetailsRepository.findByFlightNumber(flightNumber);
    }
}
