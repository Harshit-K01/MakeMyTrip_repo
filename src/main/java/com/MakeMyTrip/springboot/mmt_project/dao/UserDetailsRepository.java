package com.MakeMyTrip.springboot.mmt_project.dao;

import com.MakeMyTrip.springboot.mmt_project.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {

     UserDetails findByEmailId(String email);
}
