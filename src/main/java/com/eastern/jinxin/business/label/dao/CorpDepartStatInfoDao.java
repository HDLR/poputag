package com.eastern.jinxin.business.label.dao;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.business.label.entity.CorpDepartStatInfoEntity;
/**
 * 法人库部门部门归集统计信息表
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-06-27 16:28:34
 */
public interface CorpDepartStatInfoDao  extends BaseMapper<CorpDepartStatInfoEntity> {
	List<CorpDepartStatInfoEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
	/**
	 * <p>Title : getDepTableCount</p>
	 * <p>Description : 各单位的表数量</p>	
	 * @param departName
	 * @return
	 */
	List<Map<String, String>> getDepTableCount(String departName);
	/**
	 * <p>Title : getDepDataPercent</p>
	 * <p>Description :各单位数据质量 </p>	
	 * @param departName
	 * @return
	 */
	List<Map<String, String>> getDepDataPercent(String departName);
	/**
	 * <p>Title : getDepDataPercentDetail</p>
	 * <p>Description : </p>	
	 * @param page
	 * @param condition
	 * @return
	 */
	List<Map<String, String>> getDepDataPercentDetail(Page<CorpDepartStatInfoEntity> page, Map<String, Object> condition);
	/**
	 * <p>Title : getDepDataPercentDetailCount</p>
	 * <p>Description : </p>	
	 * @param condition
	 * @return
	 */
	int getDepDataPercentDetailCount(Map<String, Object> condition);
	/**
	 * <p>Title : getDataImputationList</p>
	 * <p>Description : </p>	
	 * @param page
	 * @param condition
	 * @return
	 */
	List<Map<String, String>> getDataImputationList(Page<CorpDepartStatInfoEntity> page, Map<String, Object> condition);
	/**
	 * <p>Title : getDataImputationCount</p>
	 * <p>Description : </p>	
	 * @param condition
	 * @return
	 */
	int getDataImputationCount(Map<String, Object> condition);
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
	 * <p>Description : 取得基础或扩展信息项的数量
	 * 		1 ->基础信息项数量
	 * 		0 ->扩展信息项数量
	 * </p>	
	 * @param status
	 * @return
	 */
	int getCount(String status);
}
