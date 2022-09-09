package com.allure.dao;

import java.util.List;

public interface UserPassionsRepositoryCustomDAO {
	
	List<Integer> getPassionsIdByUsersId(int userId);
}
