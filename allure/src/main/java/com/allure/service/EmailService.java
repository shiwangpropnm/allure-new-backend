package com.allure.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.core.io.ClassPathResource;

public interface EmailService {

	public void sendEmail(String[] toEmailList, String[] ccEmailList, String subject, String htmlBodyMessage,
			ClassPathResource[] attachment) throws MessagingException, IOException;

	void sendForgotPasswordOTPMail(Integer userId, String userEmail, Integer otp);

	public void sendUpdateEmailMail(Integer id, String newEmail, String email, int otp);

}
