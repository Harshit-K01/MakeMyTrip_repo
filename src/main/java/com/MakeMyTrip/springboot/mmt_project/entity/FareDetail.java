package com.MakeMyTrip.springboot.mmt_project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="fare_details")
public class FareDetail {

    // fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int fareId;

    @ManyToOne
    @JoinColumn(name = "flight_number", nullable = false)
    @JsonBackReference
    private FlightDetail flightNumber;

    @OneToMany(mappedBy = "fare",cascade = CascadeType.ALL)
    @JsonManagedReference(value="booking-details")
    private List<BookingDetails> fareBookingDetails;

    @Column(name = "class_type")
    private String classType;

    @Column
    private int fare;



    // constructor
    public FareDetail() {

    }

    public FareDetail(FlightDetail flightNumber, List<BookingDetails> fareBookingDetails, String classType, int fare) {
        this.flightNumber = flightNumber;
        this.fareBookingDetails = fareBookingDetails;
        this.classType = classType;
        this.fare = fare;
    }

    // getters/setters

    public List<BookingDetails> getFareBookingDetails() {
        return fareBookingDetails;
    }

    public void setFareBookingDetails(List<BookingDetails> fareBookingDetails) {
        this.fareBookingDetails = fareBookingDetails;
    }

    public FlightDetail getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(FlightDetail flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public int getId() {
        return fareId;
    }

    public void setId(int fareId) {
        this.fareId = fareId;
    }


    // toString() method

    @Override
    public String toString() {
        return "Fare_details{" +
                "flightNumber=" + flightNumber +
                ", classType='" + classType + '\'' +
                ", fare=" + fare +
                '}';

    }
}
