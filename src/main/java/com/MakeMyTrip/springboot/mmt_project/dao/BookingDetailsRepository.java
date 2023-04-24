package com.MakeMyTrip.springboot.mmt_project.dao;

import com.MakeMyTrip.springboot.mmt_project.entity.BookingDetails;
import com.MakeMyTrip.springboot.mmt_project.entity.FareDetail;
import com.MakeMyTrip.springboot.mmt_project.entity.UserDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails,Integer> {

    List<BookingDetails> findByUser(UserDetails userId, Pageable paging);

    List<BookingDetails> findByFare(FareDetail fare,Pageable paging);

}
