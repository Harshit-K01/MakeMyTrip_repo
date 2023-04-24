package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.dto.BookingDetailDTO;
import com.MakeMyTrip.springboot.mmt_project.dto.ShowBookingsDTO;
import com.MakeMyTrip.springboot.mmt_project.entity.BookingDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookingDetailService {

    @Transactional
    void saveBooking(BookingDetailDTO bookingDetailDTO);

    void deleteBookingById(int bookingId);
    BookingDetails findBookingById(int bookingId);
    List<ShowBookingsDTO> findAllBookings(Integer pageNumber,Integer pageSize,String sortBy);

    List<ShowBookingsDTO> findBookingsByEmail(String emailId,Integer pageNumber,Integer pageSize,String sortBy);

    List<ShowBookingsDTO> findBookingsByFlightNumber(Integer flightNumber,Integer pageNumber,Integer pageSize,String sortBy);
}
