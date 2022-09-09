package com.allure.constants;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DpUploadType {

	EMPTY(-1), NOT_CHANGE(0), CHANGED(1), NEW(2);

	private Integer type;

	private static Map<Integer, DpUploadType> map = new HashMap<Integer, DpUploadType>();

	static {
		for (DpUploadType dpUploadType : DpUploadType.values()) {
			map.put(dpUploadType.type, dpUploadType);
		}
	}

	@JsonValue
	@JsonCreator
	public static DpUploadType valueOf(int dpUploadType) {
		return map.get(dpUploadType);
	}

}
