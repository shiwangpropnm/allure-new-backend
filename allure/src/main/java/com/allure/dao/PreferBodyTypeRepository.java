package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.PreferBodyType;

@Repository
public interface PreferBodyTypeRepository extends JpaRepository<PreferBodyType, Integer> {

	List<PreferBodyType> findByUserId(Integer userId);

}

