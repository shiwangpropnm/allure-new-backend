package com.allure.constants;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DistanceUnit {

	KILOMETERS(1), MILES(2);

	private int unit;
	
	private static Map<Integer, DistanceUnit> map = new HashMap<Integer, DistanceUnit>();
	
	static {
        for (DistanceUnit distanceUnit : DistanceUnit.values()) {
            map.put(distanceUnit.unit, distanceUnit);
        }
    }
	
	public static DistanceUnit valueOf(int unit) {
		return map.get(unit);
	}
}
