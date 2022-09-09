package com.allure.dao;

import java.util.List;

import com.allure.entity.Users;

public interface ProfileLikeDislikeRepositoryCustomDAO {

	List<Users> findUsersProfileSwipedByUser(int userId, String likeStatus);

	List<Users> findUsersProfilesWhoSwipedUser(int userId, String likeStatus);

}
