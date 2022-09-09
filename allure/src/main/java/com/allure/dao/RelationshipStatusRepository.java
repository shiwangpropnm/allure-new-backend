package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.RelationshipStatus;

@Repository
public interface RelationshipStatusRepository extends JpaRepository<RelationshipStatus, Integer> {

}
