package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.entity.FareDetail;

import java.util.List;
import java.util.Optional;

public interface FareDetailService {

    List<FareDetail> findAllFares();

    Optional<FareDetail> findFareById(int theId);

    FareDetail saveFare(FareDetail theFareDetail);

    void deleteFareById(int theId);
}
