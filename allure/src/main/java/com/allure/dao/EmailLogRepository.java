package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.EmailLog;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Integer> {

}
