package com.allure.dao;

import java.util.List;

public interface UserSexualOrientationsRepositoryCustomDAO {

	List<Integer> getSexualOrientationIdsByUsersId(int userId);
}
