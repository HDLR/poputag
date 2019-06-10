package com.eastern.jinxin.sys.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String format(Date date) throws Exception {
		return format(date, DatePattern.yyyyMMddHHmmss.getPattern());
	}

	public static String format(Date date, String pattern) throws Exception {
		if (date == null) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static Date parse(String dateString, String pattern) throws Exception {
		if (dateString == null) {
			return null;
		}
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			date = format.parse(dateString);
		} catch (Exception e) {
			Exception adqe = new Exception(e.getMessage());
			adqe.initCause(e);
			throw adqe;
		}
		return date;
	}

	/*
	public static void main(String[] args) throws Exception {
		String dtStr = "2017/09/12";
		System.out.println(dtStr.replaceAll("/", "-"));
		// System.out.println(format(null));
	}
	*/

	public static enum DatePattern {
		yyyyMMddHHmmss("yyyy-MM-dd HH:mm:ss"), yyyyMMdd("yyyy-MM-dd"), yyyyMMddxg("yyyy/MM/dd"), HHmmss("HH:mm:ss");

		private String pattern;

		private DatePattern(String pattern) {
			this.pattern = pattern;
		}

		public String getPattern() {
			return this.pattern;
		}
	}

	public static Date getyyyyMMdd(String dtStr) throws Exception {
		return DateUtils.parse(dtStr, DatePattern.yyyyMMdd.getPattern());
	}

	public static Date getyyyyMMddRepalcexg(String dtStr) throws Exception {
		dtStr=dtStr.replaceAll("/", "-");
		return DateUtils.parse(dtStr, DatePattern.yyyyMMdd.getPattern());
	}

}