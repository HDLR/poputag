package com.eastern.jinxin.business.label.service.impl;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.business.label.dao.CorpDepartStatInfoDao;
import com.eastern.jinxin.business.label.entity.CorpDepartStatInfoEntity;
import com.eastern.jinxin.business.label.service.CorpDepartStatInfoService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;



@Service("corpDepartStatInfoService")
 public class CorpDepartStatInfoServiceImpl  extends ServiceImpl<CorpDepartStatInfoDao, CorpDepartStatInfoEntity> implements CorpDepartStatInfoService {
	@Autowired
	private CorpDepartStatInfoDao corpDepartStatInfoDao;
	
	@Override
	public CorpDepartStatInfoEntity queryObject(String departName){
		return corpDepartStatInfoDao.selectById(departName);
	}
	

	@Override
	public void save(CorpDepartStatInfoEntity corpDepartStatInfo){
		corpDepartStatInfoDao.insert(corpDepartStatInfo);
	}
	
	@Override
	public void update(CorpDepartStatInfoEntity corpDepartStatInfo){
		corpDepartStatInfoDao.updateById(corpDepartStatInfo);
	}
	
	@Override
	public void delete(String departName){
		corpDepartStatInfoDao.deleteById(departName);
	}
	
	@Override
	public void deleteBatch(String[] departNames){
		corpDepartStatInfoDao.deleteBatchIds(Arrays.asList(departNames));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<CorpDepartStatInfoEntity> page = new Page<CorpDepartStatInfoEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(corpDepartStatInfoDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(corpDepartStatInfoDao.queryTotal(pageInfo.getCondition()));
	}


	/* (non-Javadoc)
	 * <p>Title : getDepTableCount</p>
	 * <p>Description : </p>	
	 * @param departName
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.CorpDepartStatInfoService#getDepTableCount(java.lang.String)
	 */
	@Override
	public List<Map<String, String>> getDepTableCount(String departName) {
		// TODO Auto-generated method stub
		return corpDepartStatInfoDao.getDepTableCount(departName);
	}


	/* (non-Javadoc)
	 * <p>Title : getDepDataPercent</p>
	 * <p>Description : </p>	
	 * @param departName
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.CorpDepartStatInfoService#getDepDataPercent(java.lang.String)
	 */
	@Override
	public List<Map<String, String>> getDepDataPercent(String departName) {
		// TODO Auto-generated method stub
		return corpDepartStatInfoDao.getDepDataPercent(departName);
	}


	/* (non-Javadoc)
	 * <p>Title : getDepDataPercentDetail</p>
	 * <p>Description : </p>	
	 * @param pageInfo	 
	 * @see com.eastern.jinxin.business.label.service.CorpDepartStatInfoService#getDepDataPercentDetail(com.eastern.jinxin.sys.common.common.utils.PageInfo)
	 */
	@Override
	public void getDepDataPercentDetail(PageInfo pageInfo) {
		Page<CorpDepartStatInfoEntity> page = new Page<CorpDepartStatInfoEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(corpDepartStatInfoDao.getDepDataPercentDetail(page, pageInfo.getCondition()));
	    pageInfo.setTotal(corpDepartStatInfoDao.getDepDataPercentDetailCount(pageInfo.getCondition()));

	}


	/* (non-Javadoc)
	 * <p>Title : getDataImputationList</p>
	 * <p>Description : </p>	
	 * @param pageInfo	 
	 * @see com.eastern.jinxin.business.label.service.CorpDepartStatInfoService#getDataImputationList(com.eastern.jinxin.sys.common.common.utils.PageInfo)
	 */
	@Override
	public void getDataImputationList(PageInfo pageInfo) {
		Page<CorpDepartStatInfoEntity> page = new Page<CorpDepartStatInfoEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(corpDepartStatInfoDao.getDataImputationList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(corpDepartStatInfoDao.getDataImputationCount(pageInfo.getCondition()));

	}


	/* (non-Javadoc)
	 * <p>Title : getImputationInfo</p>
	 * <p>Description : </p>	
	 * @param status
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.CorpDepartStatInfoService#getImputationInfo(java.lang.String)
	 */
	@Override
	public List<Map<String, String>> getImputationInfo(String status) {
		return corpDepartStatInfoDao.getImputationInfo(status);
	}


	/* (non-Javadoc)
	 * <p>Title : getImputationInfoFullRate</p>
	 * <p>Description : </p>	
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.CorpDepartStatInfoService#getImputationInfoFullRate()
	 */
	@Override
	public List<Map<String, String>> getImputationInfoFullRate() {
		// TODO Auto-generated method stub
		return corpDepartStatInfoDao.getImputationInfoFullRate();
	}


	/* (non-Javadoc)
	 * <p>Title : getImputationInfoAllCount</p>
	 * <p>Description : </p>	
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.CorpDepartStatInfoService#getImputationInfoAllCount()
	 */
	@Override
	public BigInteger getImputationInfoAllCount() {
		// TODO Auto-generated method stub
		return corpDepartStatInfoDao.getImputationInfoAllCount();
	}


	/* (non-Javadoc)
	 * <p>Title : getCount</p>
	 * <p>Description : </p>	
	 * @param status
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.CorpDepartStatInfoService#getCount(java.lang.String)
	 */
	@Override
	public int getCount(String status) {
		return corpDepartStatInfoDao.getCount(status);
	}
	
	
}
