package com.allure.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import com.allure.constants.DistanceUnit;

public class CommonUtils {

	public static int generateOTP() {
		return 12345;
	}

	public static double getDistanceBetweenTwoLocations(double lat1, double lon1, double lat2, double lon2, DistanceUnit unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == DistanceUnit.KILOMETERS) {
			dist = dist * 1.609344;
		}
		return (dist);
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	public static Date getSuspendedTillDate(Date date, @Value("${allure.suspend.account.days}") int suspendedTillDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + suspendedTillDays);
		return calendar.getTime();
	}

	
}
