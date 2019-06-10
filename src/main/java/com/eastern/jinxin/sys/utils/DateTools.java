package com.eastern.jinxin.sys.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTools {

	public static String toString(Date date, String format) {
		try {
			return new SimpleDateFormat(format).format(date);
		} catch (Exception e) {
		}
		return null;
	}
	
}
