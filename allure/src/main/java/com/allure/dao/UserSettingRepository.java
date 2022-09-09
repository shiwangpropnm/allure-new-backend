package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.UserSetting;
import com.allure.entity.Users;

@Repository
public interface UserSettingRepository extends JpaRepository<UserSetting, Integer> {

	UserSetting findByUserId(int userId);

	UserSetting findByUsers(Users user);
}
