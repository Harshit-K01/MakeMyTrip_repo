package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.dao.FareDetailsRepository;
import com.MakeMyTrip.springboot.mmt_project.dao.FlightDetailsRepository;
import com.MakeMyTrip.springboot.mmt_project.entity.FareDetail;
import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FareDetailServiceImpl implements FareDetailService{

    private FareDetailsRepository fareDetailsRepository;

    @Autowired
    public FareDetailServiceImpl(FareDetailsRepository thefareDetailsRepository){
        fareDetailsRepository=thefareDetailsRepository;
    }

    @Override
    public List<FareDetail> findAllFares() {
        return fareDetailsRepository.findAll();
    }

    @Override
    public Optional<FareDetail> findFareById(int theId) {
        return fareDetailsRepository.findById(theId);
    }

    @Transactional
    @Override
    public FareDetail saveFare(FareDetail theFareDetail) {
        return fareDetailsRepository.save(theFareDetail);
    }

    @Transactional
    @Override
    public void deleteFareById(int theId) {

        fareDetailsRepository.deleteById(theId);
    }
}
