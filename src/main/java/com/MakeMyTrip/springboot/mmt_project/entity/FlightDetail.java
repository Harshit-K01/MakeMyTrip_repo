package com.MakeMyTrip.springboot.mmt_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="flight_details")
public class FlightDetail {

    // fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flight_number")
    private int flightNumber;

    @Column(name="airline")
    private String airline;

    @Column(name="depart_day")
    private LocalDate departDay;

    @Column(name="arrive_day")
    private LocalDate arriveDay;

    @Column(name="depart_time")
    private LocalTime departTime;

    @Column(name="arrive_time")
    private LocalTime arriveTime;

    @Column(name="source")
    private String source;

    @Column(name="destination")
    private String destination;

    @Column(name = "duration")
    private Long duration;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "flightNumber")
    @JsonManagedReference
    private List<FareDetail> fareDetails;


    // constructor
    public FlightDetail(){

    }

    public FlightDetail(String airline, LocalDate departDay, LocalDate arriveDay, LocalTime departTime, LocalTime arriveTime, String source, String destination) {
        this.airline = airline;
        this.departDay = departDay;
        this.arriveDay = arriveDay;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        this.source = source;
        this.destination = destination;
    }


    // getters/setters

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
        LocalDateTime departDate=LocalDateTime.of(departDay,departTime);
        LocalDateTime arriveDate=LocalDateTime.of(arriveDay,arriveTime);
        duration=Duration.between(departDate,arriveDate).toMinutes();
        return duration;
    }

    @PrePersist
    @PreUpdate
    public void setDuration(){
        this.duration=getDuration();
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
