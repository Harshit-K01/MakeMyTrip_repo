package com.MakeMyTrip.springboot.mmt_project.dao;

import com.MakeMyTrip.springboot.mmt_project.entity.FareDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FareDetailsRepository extends JpaRepository<FareDetail, Integer> {
}
