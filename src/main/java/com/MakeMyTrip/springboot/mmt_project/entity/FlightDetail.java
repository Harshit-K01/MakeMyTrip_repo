package com.MakeMyTrip.springboot.mmt_project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name="flight_details")
public class FlightDetail {

    // FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flight_number")
    private int flightNumber;
    @NotEmpty
    @Column
    private String airline;
    @NotNull
    @Column(name="depart_day")
    private LocalDate departDay;
    @NotNull
    @Column(name="arrive_day")
    private LocalDate arriveDay;
    @NotNull
    @Column(name="depart_time")
    private LocalTime departTime;
    @NotNull
    @Column(name="arrive_time")
    private LocalTime arriveTime;
    @NotEmpty
    @Column
    private String source;
    @NotEmpty
    @Column
    private String destination;
    @Column
    private Long duration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flightNumber")
    @JsonManagedReference
    private List<FareDetail> fareDetails;

    // CONSTRUCTORS
    public FlightDetail(){

    }

    public FlightDetail( String airline, LocalDate departDay, LocalDate arriveDay, LocalTime departTime, LocalTime arriveTime, String source, String destination) {
        this.airline = airline;
        this.departDay = departDay;
        this.arriveDay = arriveDay;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.source = source;
        this.destination = destination;
    }


    // GETTERS/SETTERS

    public int getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(int flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public LocalDate getDepartDay() {
        return departDay;
    }

    public void setDepartDay(LocalDate departDay) {
        this.departDay = departDay;
    }

    public LocalDate getArriveDay() {
        return arriveDay;
    }

    public void setArriveDay(LocalDate arriveDay) {
        this.arriveDay = arriveDay;
    }

    public LocalTime getDepartTime() {
        return departTime;
    }

    public void setDepartTime(LocalTime departTime) {
        this.departTime = departTime;
    }

    public LocalTime getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(LocalTime arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Long getDuration() {
        return duration;
    }

    @PrePersist
    public void setDuration(){
        LocalDateTime departDate=LocalDateTime.of(departDay,departTime);
        LocalDateTime arriveDate=LocalDateTime.of(arriveDay,arriveTime);
        this.duration=Duration.between(departDate,arriveDate).toMinutes();

    }

    public List<FareDetail> getFareDetails() {
        return fareDetails;
    }

    public void setFareDetails(List<FareDetail> fareDetails) {
        this.fareDetails = fareDetails;
    }

    // toString() method

    @Override
    public String toString() {
        return "Flight_details{" +
                "flightNumber=" + flightNumber +
                ", airline='" + airline + '\'' +
                ", departTime=" + departTime +
                ", arriveTime=" + arriveTime +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
