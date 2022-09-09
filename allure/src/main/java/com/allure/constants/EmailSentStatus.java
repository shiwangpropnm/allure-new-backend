package com.allure.constants;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailSentStatus {

	FAIL(false), SUCCESS(true);

	private boolean status;

	private static Map<Boolean, EmailSentStatus> map = new HashMap<Boolean, EmailSentStatus>();

	static {
		for (EmailSentStatus emailStatus : EmailSentStatus.values()) {
			map.put(emailStatus.status, emailStatus);
		}
	}

	public static EmailSentStatus valueOf(boolean emailStatus) {
		return map.get(emailStatus);
	}
}
