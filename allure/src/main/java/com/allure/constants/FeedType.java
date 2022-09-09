package com.allure.constants;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FeedType {
	MATCH((byte)1),
	IMAGE_UPLOAD((byte)2);
	
	private byte type;
	
	private static Map<Byte, FeedType> map = new HashMap<Byte, FeedType>();
	
	static {
        for (FeedType feedType : FeedType.values()) {
            map.put(feedType.type, feedType);
        }
    }
	
	public static FeedType valueOf(byte feedType) {
		return map.get(feedType);
	}

}
