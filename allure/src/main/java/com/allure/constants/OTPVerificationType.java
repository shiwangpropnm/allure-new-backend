package com.allure.constants;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OTPVerificationType {

	LOGIN_MOBILE((byte)1),
	REGISTER_MOBILE((byte)2),
	UPDATE_EMAIL((byte)3),
	UPDATE_MOBILE((byte)4),
	FORGOT_PASSWORD_EMAIL((byte)5);
	
	private byte type;

	private static Map<Byte, OTPVerificationType> map = new HashMap<Byte, OTPVerificationType>();
	
	static {
        for (OTPVerificationType otpVerificationType : OTPVerificationType.values()) {
            map.put(otpVerificationType.type, otpVerificationType);
        }
    }
	
	public static OTPVerificationType valueOf(byte otpVerificationType) {
		return map.get(otpVerificationType);
	}
}
