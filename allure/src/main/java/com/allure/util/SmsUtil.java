package com.allure.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.allure.model.SmsRequestModel;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class SmsUtil {
	@Value("${account_sid}")
	public   String ACCOUNT_SID ;
	@Value("${auth_token}")
	public   String AUTH_TOKEN ;
	@Value("${sender_mobNo}")
	public  String sender_mobNo ;
	
	public  Message  sendSms(SmsRequestModel smr)
	{
		 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 Message message = Message
	                .creator(new PhoneNumber(smr.getMobileNumber()), // to
	                        new PhoneNumber(sender_mobNo), // from
	                        smr.getMessage()).create();
		 return message;
	}
	
	
}
