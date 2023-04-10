package com.MakeMyTrip.springboot.mmt_project.dto;

import com.MakeMyTrip.springboot.mmt_project.entity.FareDetail;
import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FlightDetailDTOMapper implements Function<FlightDetail, FlightDetailDTO> {
    @Override
    public FlightDetailDTO apply(FlightDetail flightDetail){
        return new FlightDetailDTO(
                flightDetail.getAirline(),
                flightDetail.getDepartTime(),
                flightDetail.getArriveTime(),
                flightDetail.getDuration(),
                flightDetail.getFareDetails()
                        .stream()
                        .mapToInt(FareDetail::getFare));
    }
}
