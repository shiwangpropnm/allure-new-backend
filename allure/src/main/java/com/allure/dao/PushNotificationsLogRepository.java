package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.PushNotificationsLog;

@Repository
public interface PushNotificationsLogRepository extends JpaRepository<PushNotificationsLog, Integer>{
	
	public List<PushNotificationsLog> findByUserId(int userId);
}
