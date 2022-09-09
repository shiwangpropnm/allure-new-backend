package com.allure.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.allure.entity.OtpVerification;
import com.allure.entity.Users;

@Repository
public interface OtpVerificationRepository extends CrudRepository<OtpVerification, Integer> {
	public OtpVerification findByUsersAndOtpAndType(Users users, int otp, byte type);
}
