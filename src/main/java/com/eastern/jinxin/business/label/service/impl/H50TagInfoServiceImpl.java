package com.eastern.jinxin.business.label.service.impl;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eastern.jinxin.sys.common.excel.enums.TagStaticCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.business.label.dao.H50TagInfoDao;
import com.eastern.jinxin.business.label.dao.H50TaggroupInfoDao;
import com.eastern.jinxin.business.label.entity.H50TagInfoEntity;
import com.eastern.jinxin.business.label.service.H50TagInfoService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;



@Service("h50TagInfoService")
 public class H50TagInfoServiceImpl  extends ServiceImpl<H50TagInfoDao, H50TagInfoEntity> implements H50TagInfoService {
	@Autowired
	private H50TagInfoDao h50TagInfoDao;
	@Autowired
	private H50TaggroupInfoDao h50TaggroupInfoDao;
	
	public List<Map<String, Object>> queryTagsData(List<String> checkTags) {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("checkTags", checkTags);
		return h50TagInfoDao.queryTagsData(maps);
	}
	
	public List<Map<String, Object>> queryTagsAndGroupTagsData(List<String> checkTags) {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("checkTags", checkTags);
		return h50TagInfoDao.queryTagsAndGroupTagsData(maps);
	}
	
	public List<Map<String, Object>> queryTagsAndGroupTagsDataByCtgyId(List<String> checkTags) {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("checkTags", checkTags);
		return h50TagInfoDao.queryTagsAndGroupTagsDataByCtgyId(maps);
	}
	
	public int tagUsageByTagId(String tag_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("tag_id", tag_id);
		return h50TagInfoDao.tagUsageByTagId(map);
	}
	
	public List<Map<String, Object>> queryTagsUsage(List<String> tagIds, String countDesc, String limit) {
		Map<String, Object> maps = new HashMap<String, Object>();
		
		maps.put("tagIds", tagIds);
		maps.put("countDesc", countDesc);
		maps.put("limit", limit);
		/*
		 q
			tagIds
			countDesc
			limit
		r
			d.showparent_id,
			d.showparent_nm,
			d.tag_ctgy_nm,
			d.tag_nm,
			d.count,
			d.groupNm
		 */
		List<Map<String, Object>> listMaps = h50TagInfoDao.queryTagsUsage(maps);
		if(null != listMaps) {
			for(Map<String, Object> m : listMaps) {
				if(null == m.get("showparent_nm") && null != m.get("groupNm")) {
					m.put("showparent_id", "P_A");
					m.put("showparent_nm", "组合标签");
					m.put("tag_ctgy_nm", "组合标签列表");
					m.put("tag_nm", m.get("groupNm"));
				}
			}
		}
		return listMaps;
	}
	
	//查询所有标签
	@Override
	public List<Map<String, Object>> queryAllTags(){
		return h50TagInfoDao.queryAllTags();
	}
	
	//查询所有标签包含组合标签
	@Override
	public List<Map<String, Object>> queryAllTagsAndGroupTags(){
		return h50TagInfoDao.queryAllTagsAndGroupTags();
	}
	
	@Override
	public H50TagInfoEntity queryObject(BigInteger tagId){
		return h50TagInfoDao.selectById(tagId);
	}
	

	@Override
	public void save(H50TagInfoEntity h50TagInfo){
		h50TagInfoDao.insert(h50TagInfo);
	}
	
	@Override
	public void update(H50TagInfoEntity h50TagInfo){
		h50TagInfoDao.updateById(h50TagInfo);
	}
	
	@Override
	public void delete(Integer tagId){
		h50TagInfoDao.deleteById(tagId);
	}
	
	@Override
	public void deleteBatch(Integer[] tagIds){
		h50TagInfoDao.deleteBatchIds(Arrays.asList(tagIds));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H50TagInfoEntity> page = new Page<H50TagInfoEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h50TagInfoDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h50TagInfoDao.queryTotal(pageInfo.getCondition()));
	}

	/* (non-Javadoc)
	 * <p>Title : del</p>
	 * <p>Description : 删除标签do</p>	
	 * @param tag_id
	 * @param type
	 * @return	 
	 */
	@Override
	public int del(BigInteger tag_id) {
		boolean result=canDel(tag_id);
		if(result){
			return 0;//不能删除
		}else{//删除标签
			h50TagInfoDao.deleteById(tag_id);//删除标签项
		}
		return 1;
	}
	
	/**
	 * 标签下线
	 */
	@Override
	public int downLine(BigInteger tag_id) {
		boolean result=canDel(tag_id);
		if(result){
			return 0;//不能下线
		}else{
			H50TagInfoEntity tagInfo=new H50TagInfoEntity();
			tagInfo.setTagId(tag_id);
			tagInfo.setDisabledDt(new Date());
			tagInfo.setActiveInd("0");
			h50TagInfoDao.downLine(tagInfo);//下线该标签项
		}
		return 1;
	}
	/**
	 * 
	 * <p>Title : canDel</p>
	 * <p>Description : 判断标签是否可以删除、线下的条件</p>	
	 * @param tag_id
	 * @return
	 */
	public boolean canDel(BigInteger tag_id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put(TagStaticCode.tag_id, tag_id);
		int result = h50TagInfoDao.findCampaignTagRela(param);
		List<String> groupTagContentList=h50TaggroupInfoDao.getAllgroupTagContent();
		boolean isUseTag=false;
		for(String content:groupTagContentList){
			if(content.lastIndexOf(tag_id+"")>0){;
				isUseTag=true;	
				break;
			}
		}
		return result>=1||isUseTag;
	}

	@Override
	public void upLine(H50TagInfoEntity h50TagInfo) {
		h50TagInfoDao.upLine(h50TagInfo);
		
	}

	@Override
	public List<String> queryNotTagId(String tagId) {
		// TODO Auto-generated method stub
		return h50TagInfoDao.queryNotTagId(tagId);
	}
	
}
