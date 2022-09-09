package com.allure.dao;

import java.util.List;

import com.allure.entity.Users;

public interface MatchesRepositoryCustomDAO {

	List<Users> findMatchedUserProfiles(int userId);

}
