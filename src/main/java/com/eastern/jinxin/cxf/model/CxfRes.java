package com.eastern.jinxin.cxf.model;

public class CxfRes {
	
	private String code;//代码，0：请求成功， 1：业务异常，2：系统异常， 3：无权限， 4：其他
	private String expre;//返回信息表述
	private String message;//返回信息封装，一般为json
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getExpre() {
		return expre;
	}
	public void setExpre(String expre) {
		this.expre = expre;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
