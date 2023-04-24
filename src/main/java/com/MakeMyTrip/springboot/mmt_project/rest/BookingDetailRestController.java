package com.MakeMyTrip.springboot.mmt_project.rest;

import com.MakeMyTrip.springboot.mmt_project.dto.BookingDetailDTO;
import com.MakeMyTrip.springboot.mmt_project.dto.ShowBookingsDTO;
import com.MakeMyTrip.springboot.mmt_project.entity.BookingDetails;
import com.MakeMyTrip.springboot.mmt_project.service.BookingDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booking-api")
public class BookingDetailRestController {
    private BookingDetailService bookingDetailService;
    @Autowired
    public BookingDetailRestController(BookingDetailService theBookingDetailService){
        bookingDetailService=theBookingDetailService;
    }

    //API FOR ADDING BOOKING
    @PostMapping("/booking")
    public String addBooking(@Valid @RequestBody BookingDetailDTO bookingDetailDTO){
        bookingDetailService.saveBooking(bookingDetailDTO);
        return "booking saved successfully";

    }

//    API FOR SHOWING ALL/FLIGHT NUMBER SPECIFIC/EMAIL ID SPECIFIC BOOKINGS
    @GetMapping("/booking")
    public List<ShowBookingsDTO> findBooking(@Valid @RequestParam(required = false) String emailId,
                                             @RequestParam(required = false) Integer flightNumber,
                                             @RequestParam(required = false,defaultValue = "0") Integer pageNumber,
                                             @RequestParam(required = false,defaultValue = "2") Integer pageSize,
                                             @RequestParam(required = false,defaultValue = "fare") String sortBy){
        List<ShowBookingsDTO> bookings=new ArrayList<>();

        if (emailId!=null){
            bookings=bookingDetailService.findBookingsByEmail(emailId,pageNumber,pageSize,sortBy);
        } else if (flightNumber!=null) {
            bookings=bookingDetailService.findBookingsByFlightNumber(flightNumber,pageNumber,pageSize,sortBy);
        }else {
            bookings=bookingDetailService.findAllBookings(pageNumber,pageSize,sortBy);
        }
        return bookings;
    }

    //API FOR DELETING/CANCELLING A BOOKING
    @DeleteMapping("/booking/{bookingId}")
    public String cancelBooking(@PathVariable int bookingId){
        BookingDetails tempBooking=bookingDetailService.findBookingById(bookingId);

        if (tempBooking==null){
            throw new RuntimeException("booking not found");
        }

        bookingDetailService.deleteBookingById(bookingId);
        return "Cancelled booking number - "+ bookingId;

    }

}
















