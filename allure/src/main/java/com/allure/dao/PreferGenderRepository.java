package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.PreferGender;

@Repository
public interface PreferGenderRepository extends JpaRepository<PreferGender, Integer> {

	List<PreferGender> findByUserId(Integer userId);

}
