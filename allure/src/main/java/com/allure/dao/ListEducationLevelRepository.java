package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.ListEducationLevel;

@Repository
public interface ListEducationLevelRepository extends JpaRepository<ListEducationLevel, Integer> {

}
