package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.PreferAddiction;

@Repository
public interface PreferAddictionRepository extends JpaRepository<PreferAddiction, Integer> {

	List<PreferAddiction> findByUserId(Integer userId);

}
