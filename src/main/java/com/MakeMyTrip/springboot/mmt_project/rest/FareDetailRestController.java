package com.MakeMyTrip.springboot.mmt_project.rest;

import com.MakeMyTrip.springboot.mmt_project.entity.FareDetail;
import com.MakeMyTrip.springboot.mmt_project.service.FareDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fare-api")
public class FareDetailRestController {

    private FareDetailService fareDetailService;

    //CONSTRUCTOR
    @Autowired
    public FareDetailRestController(FareDetailService theFareDetailService){
        fareDetailService=theFareDetailService;
    }

    //API FOR FINDING ALL FARES
    @GetMapping("/fares")
    public List<FareDetail> findAllFares(){
        return fareDetailService.findAllFares();
    }

    //API FOR GETTING FARE DETAILS
    @GetMapping("/fares/{fare-Id}")
    public FareDetail getFareDetail(@PathVariable int fareId) {

        FareDetail theFareDetail=fareDetailService.findFareById(fareId);

        if (ObjectUtils.isEmpty(theFareDetail)){
            throw new RuntimeException("Fare Id not found -"+ fareId);
        }

        return theFareDetail;
    }

    //API FOR ADDING FARE
    @PostMapping("/fares")
    public FareDetail addFare(@RequestBody FareDetail theFareDetail){

        theFareDetail.setId(0);
        FareDetail dbFare=fareDetailService.saveFare(theFareDetail);
        return dbFare;
    }

    //API FOR UPDATING FARE DETAIL
    @PutMapping("/fares")
    public FareDetail updateFareDetail(@RequestBody FareDetail theFareDetail){

        FareDetail dbFareDetail=fareDetailService.saveFare(theFareDetail);
        return dbFareDetail;

    }

    //API FOR DELETING FARE
    @DeleteMapping("/fares/{fare-Id}")
    public String deleteFare(@PathVariable int fareId){

        FareDetail tempFare=fareDetailService.findFareById(fareId);

        if (ObjectUtils.isEmpty(tempFare)) {
            throw new RuntimeException("Fare Id not found -"+ fareId);
        }

        fareDetailService.deleteFareById(fareId);
        return "Deleted flight number - " + fareId;

    }
}
