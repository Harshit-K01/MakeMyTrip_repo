package com.MakeMyTrip.springboot.mmt_project.dto;

import jakarta.validation.constraints.NotEmpty;

public record BookingDetailDTO(
        Integer fare,

        @NotEmpty(message = "firstname cannot be empty")
        String firstName,
        @NotEmpty(message = "lastname cannot be empty")
        String lastName,
        @NotEmpty(message = "phone number cannot be empty")
        String phoneNumber,
        @NotEmpty(message = "email id cannot be empty")
        String emailId,
        @NotEmpty(message = "gender cannot be empty")
        String gender
) {

}
