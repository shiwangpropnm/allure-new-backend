package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.SexualOrientations;

@Repository
public interface SexualOrientationsRepository extends JpaRepository<SexualOrientations, Integer> {
	List<SexualOrientations> findAllByIsDeleted(boolean isDeleted);
}
