package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.UserSexualOrientations;
import com.allure.entity.Users;

@Repository
public interface UserSexualOrientationsRepository extends JpaRepository<UserSexualOrientations, Integer>, UserSexualOrientationsRepositoryCustomDAO {

	void deleteBySexualOrientationsIdAndUsersId(Integer sexualOrientationId, int userId);

	List<UserSexualOrientations> findAllByUsers(Users user);
}
