package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.ListReligion;

@Repository
public interface ListReligionRepository extends JpaRepository<ListReligion, Integer> {

}
