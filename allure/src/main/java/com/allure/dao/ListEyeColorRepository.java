package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.ListEyeColor;

@Repository
public interface ListEyeColorRepository extends JpaRepository<ListEyeColor, Integer> {

}
