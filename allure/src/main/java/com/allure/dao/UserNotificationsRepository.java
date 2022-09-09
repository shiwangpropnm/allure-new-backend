package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.UserNotifications;

@Repository
public interface UserNotificationsRepository extends JpaRepository<UserNotifications, Integer>{
	
	public List<UserNotifications> findByUserId(int userId);
}
