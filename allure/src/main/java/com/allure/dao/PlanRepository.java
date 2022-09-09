package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.Plans;

@Repository
public interface PlanRepository extends JpaRepository<Plans, Integer>{

	List<Plans> findAllByIsDeleted(boolean isDeleted);

}
