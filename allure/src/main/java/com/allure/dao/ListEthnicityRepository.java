package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.ListEthnicity;

@Repository
public interface ListEthnicityRepository extends JpaRepository<ListEthnicity, Integer> {

}
