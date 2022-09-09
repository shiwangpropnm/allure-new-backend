package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.ListAddiction;

@Repository
public interface ListAddictionRepository extends JpaRepository<ListAddiction, Integer> {

}
