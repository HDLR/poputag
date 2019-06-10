package com.eastern.jinxin.hbase.entity;

public class PersonEnum {

	public static enum PersonSex
	   {
	     SEX_MR("10", "男"), SEX_MS("20", "女"), YES("Y", "是"), NO("N", "否"), NULL("未知", "");
	 
	     public String val;
	     public String showVal;
	 
	     private PersonSex(String val, String showVal) { this.showVal = showVal;
	       this.val = val; }
	 
	     public String toString()
	     {
	       return this.val;
	     }
	   }
}
