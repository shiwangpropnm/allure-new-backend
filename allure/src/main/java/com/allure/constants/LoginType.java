package com.allure.constants;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginType {

	EMAIL(1),
	MOBILE(2);
	
	private int value;

	private static Map<Integer, LoginType> map = new HashMap<Integer, LoginType>();
	
	static {
        for (LoginType loginType : LoginType.values()) {
            map.put(loginType.value, loginType);
        }
    }
	
	public static LoginType valueOf(int loginType) {
		return map.get(loginType);
	}
}
