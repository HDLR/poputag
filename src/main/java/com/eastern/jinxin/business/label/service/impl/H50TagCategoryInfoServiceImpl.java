package com.eastern.jinxin.business.label.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.business.label.dao.H50TagCategoryInfoDao;
import com.eastern.jinxin.business.label.dao.H50TagInfoDao;
import com.eastern.jinxin.business.label.entity.H50TagCategoryInfoEntity;
import com.eastern.jinxin.business.label.service.H50TagCategoryInfoService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.excel.enums.TagStaticCode;

@Service("h50TagCategoryInfoService")
public class H50TagCategoryInfoServiceImpl extends ServiceImpl<H50TagCategoryInfoDao, H50TagCategoryInfoEntity>
		implements H50TagCategoryInfoService {
	@Autowired
	private H50TagCategoryInfoDao h50TagCategoryInfoDao;
	@Autowired
	private H50TagInfoDao h50TagInfoDao;
	

	@Override
	public H50TagCategoryInfoEntity queryObject(String tagCtgyId) {
		return h50TagCategoryInfoDao.selectById(tagCtgyId);
	}

	@Override
	public void save(H50TagCategoryInfoEntity h50TagCategoryInfo) {
		h50TagCategoryInfoDao.insertTagCategoryInfo(h50TagCategoryInfo);
	}

	@Override
	public void update(H50TagCategoryInfoEntity h50TagCategoryInfo) {
		h50TagCategoryInfoDao.updateById(h50TagCategoryInfo);
	}

	@Override
	public void delete(String tagCtgyId) {
		h50TagCategoryInfoDao.deleteById(tagCtgyId);
	}

	@Override
	public void deleteBatch(String[] tagCtgyIds) {
		h50TagCategoryInfoDao.deleteBatchIds(Arrays.asList(tagCtgyIds));
	}




	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H50TagCategoryInfoEntity> page = new Page<H50TagCategoryInfoEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h50TagCategoryInfoDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h50TagCategoryInfoDao.queryTotal(pageInfo.getCondition()));

	}

	@Override
	public List<H50TagCategoryInfoEntity> getTagCategoryTree(Map<String, Object> params) {
		List<H50TagCategoryInfoEntity> list=h50TagCategoryInfoDao.getTagCategoryTree(params);
		return list;
	}

	@Override
	public List<H50TagCategoryInfoEntity> getTag(String haveTagInd) {
		List<H50TagCategoryInfoEntity> list=h50TagCategoryInfoDao.getTag(haveTagInd);
		return list;
	}

	/* (non-Javadoc)
	 * <p>Title : upLine</p>
	 * <p>Description : 标签上线</p>	
	 * @param tagCtgyId	 
	 */
	@Override
	public void upLine(H50TagCategoryInfoEntity h50TagCategoryInfo) {
		h50TagCategoryInfoDao.upLine(h50TagCategoryInfo);
	}
	
	@Override
	public int del(String tagCtgyId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(TagStaticCode.tag_id, tagCtgyId);
		int result = h50TagInfoDao.findCampaignTagRela(param);
		if (result > 0) {
			return 0;
		}
		//取子节点
		List<H50TagCategoryInfoEntity> lr = h50TagCategoryInfoDao.getChildrens(tagCtgyId);
		if (lr != null && lr.size() > 0) {
			return -1;
		}
		//删除
		h50TagCategoryInfoDao.deleteById(tagCtgyId);
		return 1;
	}
	
	/**
	 * 标签下线
	 */
	@Override
	public int downLine(String tagCtgyId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(TagStaticCode.tag_id, tagCtgyId);
		int result = h50TagInfoDao.findCampaignTagRela(param);
		if (result > 0) {
			return 0;
		}
		//取子节点
		List<H50TagCategoryInfoEntity> lr = h50TagCategoryInfoDao.getChildrens(tagCtgyId);
		if (lr != null && lr.size() > 0) {
			return -1;
		}
		//删除
		H50TagCategoryInfoEntity h50TagCategoryInfoEntity =new H50TagCategoryInfoEntity();
		h50TagCategoryInfoEntity.setTagCtgyId(tagCtgyId);
		h50TagCategoryInfoEntity.setTagCtgyStatus(0);
		h50TagCategoryInfoEntity.setDisabledDt(new Date());
		h50TagCategoryInfoDao.downLine(h50TagCategoryInfoEntity);
		return 1;
	}

	/* (non-Javadoc)
	 * <p>Title : getCtgyList</p>
	 * <p>Description : </p>	
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.H50TagCategoryInfoService#getCtgyList()
	 */
	@Override
	public List<H50TagCategoryInfoEntity> getCtgyList() {
		// TODO Auto-generated method stub
		return h50TagCategoryInfoDao.getCtgyList();
	}

	/* (non-Javadoc)
	 * <p>Title : findAllRecursion</p>
	 * <p>Description : </p>	
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.H50TagCategoryInfoService#findAllRecursion()
	 */
	@Override
	public List<H50TagCategoryInfoEntity> findAllRecursion() {
		// TODO Auto-generated method stub
		return h50TagCategoryInfoDao.findAllRecursion();
	}
	

}
