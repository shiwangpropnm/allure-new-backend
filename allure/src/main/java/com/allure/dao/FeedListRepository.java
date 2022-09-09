package com.allure.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.FeedList;
import com.allure.entity.Users;

@Repository
public interface FeedListRepository extends JpaRepository<FeedList, Integer> {

	List<FeedList> findAllByUsersByUserId(Users loggedInUser);

	List<FeedList> findAllByUsersByUserIdOrderByCreatedAtDesc(Users loggedInUser);
	
	
}
