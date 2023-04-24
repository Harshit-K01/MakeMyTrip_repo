package com.MakeMyTrip.springboot.mmt_project.dto;

public record BookingDetailDTO(
        Integer fare,
        String firstName,
        String lastName,
        String phoneNumber,
        String emailId,
        String gender
) {

}
