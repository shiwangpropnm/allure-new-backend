package com.allure.constants;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {

	ACTIVE(0), DELETED(1), SUSPENDED(2);

	private int value;

	private static Map<Integer, AccountStatus> map = new HashMap<Integer, AccountStatus>();

	static {
		for (AccountStatus loginType : AccountStatus.values()) {
			map.put(loginType.value, loginType);
		}
	}

	public static AccountStatus valueOf(int loginType) {
		return map.get(loginType);
	}
}
