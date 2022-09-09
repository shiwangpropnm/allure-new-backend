package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.ListHairColor;

@Repository
public interface ListHairColorRepository extends JpaRepository<ListHairColor, Integer> {

}
