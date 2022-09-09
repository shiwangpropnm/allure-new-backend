package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.UserPassions;
import com.allure.entity.Users;

@Repository
public interface UserPassionsRepository extends JpaRepository<UserPassions, Integer>, UserPassionsRepositoryCustomDAO {

	void deleteByPassionsIdAndUsersId(Integer passionId, int userId);

	List<UserPassions> findAllByUsers(Users user);
		
}

