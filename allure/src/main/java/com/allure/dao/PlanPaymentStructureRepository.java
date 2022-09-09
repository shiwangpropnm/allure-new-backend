package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.PlanPaymentStructures;

@Repository
public interface PlanPaymentStructureRepository extends JpaRepository<PlanPaymentStructures, Integer>{

}
