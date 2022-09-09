package com.allure.dao;

import java.util.List;

import com.allure.entity.Users;

public interface BlockListRepositoryCustomDAO {

	List<String> findAllBlockedMobileByUserIdAndBlockedMobileIn(int userId, List<String> mobileNumbers);

	List<String> findAllBlockedMobileByUserAndBlockedMobileIn(Users user, List<String> mobileNumbers);

	List<String> findBlockedMobileByUser(Users user);
}
