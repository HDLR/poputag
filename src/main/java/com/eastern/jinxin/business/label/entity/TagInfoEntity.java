package com.eastern.jinxin.business.label.entity;

import java.util.Date;

/**
 * 
 * @Description:标签信息实体
 * @author: haiping.tang
 * @date: 2017年7月14日 下午4:36:34
 */
public class TagInfoEntity {
	private String tag_id;
	private String tag_nm;
	private String tag_desc;
	private String tag_status;
	private String tag_status_str;
	private String tag_type_cd;
	private String tag_ctgy_id;
	private Date enabled_dt;
	private String enabled_dt_str;
	private Date disabled_dt;
	private String disabled_dt_str;
	private Date created_ts;
	private String created_ts_str;
	private Date updated_ts;
	private String updated_ts_str;
	private String have_tag_ind;
	private String unknown_ind;
	private Integer create_user;
	private String user_real_nm;
	private Integer update_user;
	private String update_real_nm;
	private String tag_ctgy_level;
	private String tag_type;
	private String up_tid;//上级的类型ID
	private String up_name;//上级标签类的名称
	//标签包含的人数
	private String personNumbers;//上级标签类的名称
	
	/**
	 * @return the personNumbers
	 */
	public String getPersonNumbers() {
		return personNumbers;
	}
	/**
	 * @param personNumbers the personNumbers to set
	 */
	public void setPersonNumbers(String personNumbers) {
		this.personNumbers = personNumbers;
	}
	/**
	 * @return the up_tid
	 */
	public String getUp_tid() {
		return up_tid;
	}
	/**
	 * @param up_tid the up_tid to set
	 */
	public void setUp_tid(String up_tid) {
		this.up_tid = up_tid;
	}
	
	/**
	 * @return the up_name
	 */
	public String getUp_name() {
		return up_name;
	}
	/**
	 * @param up_name the up_name to set
	 */
	public void setUp_name(String up_name) {
		this.up_name = up_name;
	}
	public String getTag_type() {
		return tag_type;
	}
	public void setTag_type(String tag_type) {
		this.tag_type = tag_type;
	}
	public String getTag_id() {
		return tag_id;
	}
	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}
	public String getTag_nm() {
		return tag_nm;
	}
	public void setTag_nm(String tag_nm) {
		this.tag_nm = tag_nm;
	}
	public String getTag_desc() {
		return tag_desc;
	}
	public void setTag_desc(String tag_desc) {
		this.tag_desc = tag_desc;
	}
	public String getTag_type_cd() {
		return tag_type_cd;
	}
	public void setTag_type_cd(String tag_type_cd) {
		this.tag_type_cd = tag_type_cd;
	}
	public String getTag_ctgy_id() {
		return tag_ctgy_id;
	}
	public void setTag_ctgy_id(String tag_ctgy_id) {
		this.tag_ctgy_id = tag_ctgy_id;
	}
	public Date getEnabled_dt() {
		return enabled_dt;
	}
	public void setEnabled_dt(Date enabled_dt) {
		this.enabled_dt = enabled_dt;
	}
	public String getEnabled_dt_str() {
		return enabled_dt_str;
	}
	public void setEnabled_dt_str(String enabled_dt_str) {
		this.enabled_dt_str = enabled_dt_str;
	}
	public Date getDisabled_dt() {
		return disabled_dt;
	}
	public void setDisabled_dt(Date disabled_dt) {
		this.disabled_dt = disabled_dt;
	}
	public String getDisabled_dt_str() {
		return disabled_dt_str;
	}
	public void setDisabled_dt_str(String disabled_dt_str) {
		this.disabled_dt_str = disabled_dt_str;
	}
	public Date getCreated_ts() {
		return created_ts;
	}
	public void setCreated_ts(Date created_ts) {
		this.created_ts = created_ts;
	}
	public String getCreated_ts_str() {
		return created_ts_str;
	}
	public void setCreated_ts_str(String created_ts_str) {
		this.created_ts_str = created_ts_str;
	}
	public Date getUpdated_ts() {
		return updated_ts;
	}
	public void setUpdated_ts(Date updated_ts) {
		this.updated_ts = updated_ts;
	}
	public String getUpdated_ts_str() {
		return updated_ts_str;
	}
	public String getTag_status() {
		return tag_status;
	}
	public void setTag_status(String tag_status) {
		this.tag_status = tag_status;
	}
	public String getTag_status_str() {
		return tag_status_str;
	}
	public void setTag_status_str(String tag_status_str) {
		this.tag_status_str = tag_status_str;
	}
	public void setUpdated_ts_str(String updated_ts_str) {
		this.updated_ts_str = updated_ts_str;
	}

	public String getHave_tag_ind() {
		return have_tag_ind;
	}
	public void setHave_tag_ind(String have_tag_ind) {
		this.have_tag_ind = have_tag_ind;
	}
	public String getUnknown_ind() {
		return unknown_ind;
	}
	public void setUnknown_ind(String unknown_ind) {
		this.unknown_ind = unknown_ind;
	}
	public Integer getCreate_user() {
		return create_user;
	}
	public void setCreate_user(Integer create_user) {
		this.create_user = create_user;
	}
	public String getUser_real_nm() {
		return user_real_nm;
	}
	public void setUser_real_nm(String user_real_nm) {
		this.user_real_nm = user_real_nm;
	}
	public Integer getUpdate_user() {
		return update_user;
	}
	public void setUpdate_user(Integer update_user) {
		this.update_user = update_user;
	}
	public String getUpdate_real_nm() {
		return update_real_nm;
	}
	public void setUpdate_real_nm(String update_real_nm) {
		this.update_real_nm = update_real_nm;
	}
	public String getTag_ctgy_level() {
		return tag_ctgy_level;
	}
	public void setTag_ctgy_level(String tag_ctgy_level) {
		this.tag_ctgy_level = tag_ctgy_level;
	}
	
	
	
}
