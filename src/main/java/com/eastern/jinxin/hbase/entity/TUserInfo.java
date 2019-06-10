package com.eastern.jinxin.hbase.entity;

import java.io.IOException;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TUserInfo {
	private static final Logger logger = LoggerFactory.getLogger(TUserInfo.class);
	private String userName;
	private String birthYears;
	private String userSex;
	private String userGuid;
	private String natvPlcUrbnId;//natv_plc_urbn_id居住地
	//user_base_tag-> popu_base_info
	public static final TableName tableName() {
		return TableName.valueOf("popu_base_info");
	}

	public String getUserGuid() {
		return this.userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public static TUserInfo buildFromResult(Result result) {
		TUserInfo findObj = null;
		if (HBaseUtil.isAnValidResult(result)) {
			findObj = new TUserInfo();

			String name = HBaseUtil.getValueAsString(result, _CustInfo.ColFamily(), _CustInfo.col_name());
			if ((name == null) || ("".equals(name)))
				name = HBaseUtil.getValueAsString(result, _CustInfo.ColFamily(), _CustInfo.col_u_nm());
			findObj.setUserName(name);

			String birthYears = HBaseUtil.getValueAsString(result, _CustInfo.ColFamily(), _CustInfo.col_birthYears());
			findObj.setBirthYears(birthYears);

			String userSex = HBaseUtil.getValueAsString(result, _CustInfo.ColFamily(), _CustInfo.col_sex());
			findObj.setUserSex(userSex);
			
			String natvPlcUrbnId = HBaseUtil.getValueAsString(result, _CustInfo.ColFamily(), _CustInfo.col_natv_plc_urbn_id());
			findObj.setNatvPlcUrbnId(natvPlcUrbnId);
		}

		return findObj;
	}

	public static TUserInfo buildFromResultSms(Result result) {
		TUserInfo findObj = null;
		if (HBaseUtil.isAnValidResult(result)) {
			findObj = new TUserInfo();
			String name = HBaseUtil.getValueAsString(result, _CustInfo.ColFamily(), _CustInfo.col_u_nm());
			findObj.setUserName(name);

			String tel = HBaseUtil.getValueAsString(result, _CustInfo.ColFamily(), _CustInfo.col_tel());
		}
		return findObj;
	}

	public static TUserInfo findById(Table userInfoTable, String userGUID) {
		Get get = new Get(userGUID.getBytes());

		get.addFamily(_CustInfo.ColFamily());
		// get.addColumn(family, qualifier);
		try {
			Result result = userInfoTable.get(get);

			TUserInfo findObj = buildFromResult(result);

			return findObj;
		} catch (IOException e) {
			logger.error("failed to find record ", e);
		}

		return null;
	}

	public static TUserInfo findByIdSms(Table userInfoTable, String userGUID) {
		Get get = new Get(userGUID.getBytes());

		get.addFamily(_CustInfo.ColFamily());
		try {
			Result result = userInfoTable.get(get);

			TUserInfo findObj = buildFromResultSms(result);

			return findObj;
		} catch (IOException e) {
			logger.error("failed to find record ", e);
		}

		return null;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getBirthYears() {
		return birthYears;
	}

	public void setBirthYears(String birthYears) {
		this.birthYears = birthYears;
	}
	
	public String getNatvPlcUrbnId() {
		return natvPlcUrbnId;
	}

	public void setNatvPlcUrbnId(String natvPlcUrbnId) {
		this.natvPlcUrbnId = natvPlcUrbnId;
	}

	public static final TUserInfo wrapperFindById(Table userInfoTable, String userGUID) {
		TUserInfo userInfoRecord;
		if (DataForDevTest.enableFakeData)
			userInfoRecord = DataForDevTest.findById_createIfMissing(userInfoTable, userGUID);
		else {
			userInfoRecord = findById(userInfoTable, userGUID);
		}

		return userInfoRecord;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("TUserInfo userName:").append(this.userName).append(", userSex:").append(this.userSex).append(", birthYears:").append(this.birthYears);
		return sb.toString();
	}

	public static class DataForDevTest {
		public static boolean enableFakeData = false;

		public static int test_index = 10;

		public static TUserInfo findById_createIfMissing(Table userInfoTable, String userGUID) {
			TUserInfo userInfoRecord = TUserInfo.findById(userInfoTable, userGUID);

			return userInfoRecord;
		}
	}

	public static final class _CustInfo {
		public static final byte[] ColFamily() {
			return "cf".getBytes();
		}

		public static final byte[] col_birthYears() {
			return "birth_years_cd".getBytes();
		}

		public static final byte[] col_sex() {
			return "gender_cd".getBytes();
		}

		public static final byte[] col_name() {
			return "indv_nm".getBytes();
		}

		public static final byte[] col_u_nm() {
			return "popu_nm".getBytes();
		}

		/**
		 * 联系信息
		 * 
		 * @return
		 */
		public static final byte[] col_tel() {
			return "comm_tel".getBytes();
		}
		
		//居住地
		public static final byte[] col_natv_plc_urbn_id() {
			return "natv_plc_urbn_id".getBytes();
		}
		
	}
}

/*
 * Location: C:\Users\root\Desktop\SeaBoxTag\WEB-INF\classes\ Qualified Name:
 * com.seabox.tagsys.usertags.hbase.entity.TUserInfo JD-Core Version: 0.6.0
 */