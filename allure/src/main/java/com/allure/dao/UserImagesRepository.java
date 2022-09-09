package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.UserImages;
import com.allure.entity.UserImagesId;

@Repository
public interface UserImagesRepository extends JpaRepository<UserImages, UserImagesId>{
	
	public List<UserImages> findByUserId(int userId);
}
