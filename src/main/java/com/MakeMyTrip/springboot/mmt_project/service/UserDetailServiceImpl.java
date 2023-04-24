package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.dao.UserDetailsRepository;
import com.MakeMyTrip.springboot.mmt_project.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailService{

    private UserDetailsRepository userDetailRepository;

    @Autowired
    public UserDetailServiceImpl(UserDetailsRepository theUserDetailRepository){
        userDetailRepository=theUserDetailRepository;
    }

    //METHOD TO SAVE USER
    @Transactional
    @Override
    public UserDetails saveUser(UserDetails theUserDetail) {

        return userDetailRepository.save(theUserDetail);
    }

    //METHOD TO FIND USER BY EMAIL
    @Override
    public UserDetails findByEmail(String emailId) {
        return userDetailRepository.findByEmailId(emailId);
    }
}
