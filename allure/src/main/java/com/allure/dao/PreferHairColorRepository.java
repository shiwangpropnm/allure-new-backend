package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.PreferHairColor;

@Repository
public interface PreferHairColorRepository extends JpaRepository<PreferHairColor, Integer> {

	List<PreferHairColor> findByUserId(Integer userId);
}
