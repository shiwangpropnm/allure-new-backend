package com.allure.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.EmailSubscription;
import com.allure.entity.Users;

@Repository
public interface EmailSubscriptionRepository extends JpaRepository<EmailSubscription, Integer> {

	EmailSubscription findByUsers(Users users);
}
