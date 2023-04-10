package com.MakeMyTrip.springboot.mmt_project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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

    @Column(name = "class_type")
    private String classType;

    @Column(name = "fare")
    private int fare;



    // constructor
    public FareDetail() {

    }

    public FareDetail(FlightDetail flightNumber, String classType, int fare) {
        this.flightNumber = flightNumber;
        this.classType = classType;
        this.fare = fare;
    }

    // getters/setters

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
