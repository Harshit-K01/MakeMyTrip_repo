package com.MakeMyTrip.springboot.mmt_project.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record FlightDetailDTO(

        String airline,
        LocalTime departTime,
        LocalTime arriveTime,
        Long duration,
        java.util.stream.IntStream fare
) {

}
