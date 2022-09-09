package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.ProfileLikeDislike;
import com.allure.entity.Users;

@Repository
public interface ProfileLikeDislikeRepository extends JpaRepository<ProfileLikeDislike, Integer>, ProfileLikeDislikeRepositoryCustomDAO{

	ProfileLikeDislike findByUsersByUserIdIdAndUsersByProfileIdId(int loggedInUserId, int likeProfileUserId);

	ProfileLikeDislike findByUsersByUserIdAndUsersByProfileId(Users loggedInUser, Users profileLikedUser);


}
