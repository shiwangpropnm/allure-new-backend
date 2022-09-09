package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.UserDevices;

@Repository
public interface UserDevicesRepository extends JpaRepository<UserDevices, Integer>{
	
	public List<UserDevices> findByUserId(int userId);
}
