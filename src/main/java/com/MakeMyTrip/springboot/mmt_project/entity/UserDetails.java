package com.MakeMyTrip.springboot.mmt_project.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_details")
public class UserDetails {

    //FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "email_id")
    private String emailId;
    @Column
    private String gender;
    @OneToMany(mappedBy = "user",cascade =CascadeType.ALL )
    @JsonManagedReference(value="user-details")
    private List<BookingDetails> userBookingDetails;

    // GETTERS/SETTERS
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public List<BookingDetails> getUserBookingDetails() {
        return userBookingDetails;
    }
    public void setUserBookingDetails(List<BookingDetails> userBookingDetails) {
        this.userBookingDetails = userBookingDetails;
    }

    // CONSTRUCTORS
    public UserDetails() {
    }

    public UserDetails(String firstName, String lastName, String phoneNumber, String emailId, String gender) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "UserDetails{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailId='" + emailId + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
