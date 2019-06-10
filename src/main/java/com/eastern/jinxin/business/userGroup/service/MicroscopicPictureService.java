package com.eastern.jinxin.business.userGroup.service;

import java.util.List;
import java.util.Map;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.hbase.entity.TUserInfo;

public interface MicroscopicPictureService {

	public List<List<Map<String, Object>>> downLoadExcelMsg(String campId);
	
	public List<TUserInfo> listByPage(PageInfo pageInfo);
	
	public Map<String, Object> queryUserDetail(String userGuid);
}
