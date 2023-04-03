package com.MakeMyTrip.springboot.mmt_project.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.sql.Date;
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
    private Date departDay;

    @Column(name="arrive_day")
    private Date arriveDay;

    @Column(name="depart_time")
    private Time departTime;

    @Column(name="arrive_time")
    private Time arriveTime;

    @Column(name="source")
    private String source;

    @Column(name="destination")
    private String destination;

    @OneToMany
    private List<FareDetail> fareDetail= new ArrayList<>();


    // constructor
    public FlightDetail(){

    }

    public FlightDetail(String airline, Date departDay, Date arriveDay, Time departTime, Time arriveTime, String source, String destination) {
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

    public Date getDepartDay() {
        return departDay;
    }

    public void setDepartDay(Date departDay) {
        this.departDay = departDay;
    }

    public Date getArriveDay() {
        return arriveDay;
    }

    public void setArriveDay(Date arriveDay) {
        this.arriveDay = arriveDay;
    }

    public Time getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Time departTime) {
        this.departTime = departTime;
    }

    public Time getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Time arriveTime) {
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
