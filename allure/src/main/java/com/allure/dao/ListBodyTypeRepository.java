package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.ListBodyType;

@Repository
public interface ListBodyTypeRepository extends JpaRepository<ListBodyType, Integer> {

}
