package com.MakeMyTrip.springboot.mmt_project.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "booking_details")
public class BookingDetails {

    //FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int bookingId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value="user-details")
    @JoinColumn(name = "user_id",nullable = false)
    private UserDetails user;

    @ManyToOne
    @JsonBackReference(value="booking-details")
    @JoinColumn(name = "fare_id")
    private FareDetail fare;

    @Column(name = "booking_time")
    private LocalDateTime bookingTime;

    //CONSTRUCTORS
    public BookingDetails() {
    }

    public BookingDetails(UserDetails user, FareDetail fare, LocalDateTime bookingTime) {
        this.user = user;
        this.fare = fare;
        this.bookingTime = bookingTime;
    }

    // GETTERS/SETTERS
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public FareDetail getFare() {
        return fare;
    }

    public void setFare(FareDetail fare) {
        this.fare = fare;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    @PrePersist
    public void setBookingTime() {

        this.bookingTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "BookingDetails{" +
                "bookingId=" + bookingId +
                ", userId=" + user +
                ", fareId=" + fare +
                ", bookingTime=" + bookingTime +
                '}';
    }
}
