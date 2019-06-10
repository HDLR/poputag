package com.eastern.jinxin.business.statis.utils;

public class StatisUtils {

	/**
	 * '用于区分item_id是camp_id，tag_id或别的id，01：camp_id，02：tag_id'
	 */
	public static final String ITEM_TYPE_CAMP = "01";//01：camp_id
	public static final String ITEM_TYPE_TAG = "02";//02：tag_id
	
	/**
	 * '用于区分是哪一个界面操作的，01：候鸟人群筛选，02：群内微观画像，03：群内宏观画像'，04：群内特征报告,
	 */
	public static final String OPERATE_TYPE_PEOPLE = "01";//01：候鸟人群筛选'
	public static final String OPERATE_TYPE_MICR = "02";//02：群内微观画像'
	public static final String OPERATE_TYPE_MACR = "03";//03：群内宏观画像'
	public static final String OPERATE_TYPE_FEATURE = "04";//04：群内特征报告
}
