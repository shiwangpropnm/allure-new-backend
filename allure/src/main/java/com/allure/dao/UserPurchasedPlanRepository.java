package com.allure.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.UserPurchasedPlans;
import com.allure.entity.Users;

@Repository
public interface UserPurchasedPlanRepository extends JpaRepository<UserPurchasedPlans, Integer> {

	UserPurchasedPlans findByUsersAndPlansTypeAndTransactionOnBeforeAndValidtillAfterAndIsSuccess(Users user,
			String planType, Date today1, Date today2, boolean isSuccess);

	List<UserPurchasedPlans> findAllByUsers(Users idToUsersNonEntity);

}
