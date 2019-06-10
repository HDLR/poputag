package com.eastern.jinxin.business.label.service;


import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.business.label.entity.CorpDepartStatInfoEntity;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;

/**
 * 法人库部门部门归集统计信息表
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-06-27 16:28:34
 */
public interface CorpDepartStatInfoService  extends IService<CorpDepartStatInfoEntity>{
	
	CorpDepartStatInfoEntity queryObject(String departName);
	
	void queryList(PageInfo pageInfo);
	
	void save(CorpDepartStatInfoEntity corpDepartStatInfo);
	
	void update(CorpDepartStatInfoEntity corpDepartStatInfo);
	
	void delete(String departName);
	
	void deleteBatch(String[] departNames);

	/**
	 * <p>Title : getDepTableCount</p>
	 * <p>Description : </p>	
	 * @param departName
	 * @return
	 */
	List<Map<String, String>> getDepTableCount(String departName);

	/**
	 * <p>Title : getDepDataPercent</p>
	 * <p>Description : </p>	
	 * @param departName
	 * @return
	 */
	List<Map<String, String>> getDepDataPercent(String departName);

	/**
	 * <p>Title : getDepDataPercentDetail</p>
	 * <p>Description : </p>	
	 * @param pageInfo
	 */
	void getDepDataPercentDetail(PageInfo pageInfo);

	/**
	 * <p>Title : getDataImputationList</p>
	 * <p>Description : </p>	
	 * @param pageInfo
	 */
	void getDataImputationList(PageInfo pageInfo);

	/**
	 * <p>Title : getImputationInfo</p>
	 * <p>Description : </p>	
	 * @param status
	 * @return
	 */
	List<Map<String, String>> getImputationInfo(String status);

	/**
	 * <p>Title : getImputationInfoFullRate</p>
	 * <p>Description : </p>	
	 * @return
	 */
	List<Map<String, String>> getImputationInfoFullRate();

	/**
	 * <p>Title : getImputationInfoAllCount</p>
	 * <p>Description : </p>	
	 * @return
	 */
	BigInteger getImputationInfoAllCount();

	/**
	 * <p>Title : getCount</p>
	 * <p>Description : </p>	
	 * @param status
	 * @return
	 */
	int getCount(String status);
}
