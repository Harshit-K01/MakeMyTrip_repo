package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.entity.UserDetails;

public interface UserDetailService {

    UserDetails saveUser(UserDetails theUserDetail);

    UserDetails findByEmail(String emailId);
}
