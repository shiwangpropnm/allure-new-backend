package com.allure.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.allure.constants.EmailSentStatus;
import com.allure.constants.EmailType;
import com.allure.dao.EmailLogRepository;
import com.allure.entity.EmailLog;
import com.allure.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private MessageSource messageSource;

	@Value("${allure.email.support}")
	private String supportEmail;

	@Autowired
	private EmailLogRepository emailLogRepository;

	@Override
	@Async
	public void sendEmail(String[] toEmailList, String[] ccEmailList, String subject, String htmlBodyMessage,
			ClassPathResource[] attachments) throws MessagingException, IOException {
		// System.out.println("Execute method asynchronously. " +
		// Thread.currentThread().getName());
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		if (toEmailList == null || toEmailList.length == 0) {
			throw new RuntimeException("'To' field cannot be blank to send email message");
		}
		helper.setTo(toEmailList);
		if (ccEmailList != null && ccEmailList.length > 0) {
			helper.setCc(ccEmailList);
		}
		if (StringUtils.isNoneEmpty(subject)) {
			helper.setSubject(subject);
		}
		helper.setFrom("support@allure.com");
		if (StringUtils.isNotEmpty(htmlBodyMessage)) {
			helper.setText(htmlBodyMessage, true);
		}
		if (attachments != null && attachments.length > 0) {
			for (ClassPathResource attachment : attachments) {
				helper.addAttachment(attachment.getFilename(), attachment);
			}

		}
		javaMailSender.send(msg);
	}

	@Override
	@Async
	public void sendForgotPasswordOTPMail(Integer userId, String userEmail, Integer otp) {

		String subject = messageSource.getMessage("email.forget.password.send.otp.subject", null, Locale.ENGLISH);
		String htmlBodyMessage = messageSource.getMessage("email.forget.password.send.otp.body",
				new Object[] { otp, supportEmail }, Locale.ENGLISH);
		EmailSentStatus logStatus = EmailSentStatus.SUCCESS;
		String logMessage = "";
		try {
			sendEmail(new String[] { userEmail }, null, subject, htmlBodyMessage, null);
		} catch (Exception ex) {
			logStatus = EmailSentStatus.FAIL;
			logMessage = ex.getMessage();
		}
		logEmail(userId, userEmail, EmailType.FORGOT_PASSWORD.getType(), logStatus.isStatus(), logMessage);
	}

	private void logEmail(Integer userId, String userEmail, String emailType, boolean status, String message) {
		EmailLog emailLog = new EmailLog();
		emailLog.setUserId(userId);
		emailLog.setUserEmail(userEmail);
		emailLog.setEmailType(emailType);
		emailLog.setMessage(message);
		emailLog.setCreatedAt(new Date());
		emailLog.setStatus(status);
		emailLogRepository.save(emailLog);
	}

	@Override
	public void sendUpdateEmailMail(Integer userId, String newEmail, String oldEmail, int otp) {
		sendUpdateEmailOTPMail(userId, newEmail, otp);
		sendUpdateEmailWarningMail(userId, oldEmail);
	}

	public void sendUpdateEmailOTPMail(Integer userId, String newEmail, int otp) {
		String subject = messageSource.getMessage("email.update.email.send.otp.subject", null, Locale.ENGLISH);
		String htmlBodyMessage = messageSource.getMessage("email.update.email.send.otp.body",
				new Object[] { otp, supportEmail }, Locale.ENGLISH);
		EmailSentStatus logStatus = EmailSentStatus.SUCCESS;
		String logMessage = "";
		try {
			sendEmail(new String[] { newEmail }, null, subject, htmlBodyMessage, null);
		} catch (Exception ex) {
			logStatus = EmailSentStatus.FAIL;
			logMessage = ex.getMessage();
		}
		logEmail(userId, newEmail, EmailType.UPDATE_EMAIL_OTP.getType(), logStatus.isStatus(), logMessage);
	}

	public void sendUpdateEmailWarningMail(Integer userId, String oldEmail) {
		String subject = messageSource.getMessage("email.update.email.send.warning.subject", null, Locale.ENGLISH);
		String htmlBodyMessage = messageSource.getMessage("email.email.send.warning.body",
				new Object[] { supportEmail }, Locale.ENGLISH);
		EmailSentStatus logStatus = EmailSentStatus.SUCCESS;
		String logMessage = "";
		try {
			sendEmail(new String[] { oldEmail }, null, subject, htmlBodyMessage, null);
		} catch (Exception ex) {
			logStatus = EmailSentStatus.FAIL;
			logMessage = ex.getMessage();
		}
		logEmail(userId, oldEmail, EmailType.UPDATE_EMAIL_WARNING.getType(), logStatus.isStatus(), logMessage);
	}
}
