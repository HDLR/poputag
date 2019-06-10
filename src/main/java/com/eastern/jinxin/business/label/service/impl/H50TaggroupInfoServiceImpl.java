package com.eastern.jinxin.business.label.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.business.label.dao.H50TaggroupInfoDao;
import com.eastern.jinxin.business.label.entity.H50TaggroupInfoEntity;
import com.eastern.jinxin.business.label.service.H50TaggroupInfoService;
import com.eastern.jinxin.business.label.util.ForMatterData;
import com.eastern.jinxin.business.label.util.NBL;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;



@Service("h50TaggroupInfoService")
 public class H50TaggroupInfoServiceImpl  extends ServiceImpl<H50TaggroupInfoDao, H50TaggroupInfoEntity> implements H50TaggroupInfoService {
	@Autowired
	private H50TaggroupInfoDao h50TaggroupInfoDao;
	
	@Autowired
	private NBL nbl;
	
	@Autowired
	private ForMatterData forMatterData;
	
	
	public List<Map<String, Object>> queryTagsData(List<String> checkTags) {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("checkTags", checkTags);
		List<Map<String, Object>> queryMaps = h50TaggroupInfoDao.queryTagsData(maps);
		//将原先的tag_id替换成计算后的key
		if(null != queryMaps && queryMaps.size() > 0) {
			for(Map<String, Object> m : queryMaps) {
				String tag_group_content = (String)m.get("tag_group_content");
				String key = this.getQueryGroupTagKey(tag_group_content);
				m.put("tag_id_group", key);
			}
		}
		return queryMaps;
	}
	
	@Override
	public H50TaggroupInfoEntity queryObject(Integer tagId){
		return h50TaggroupInfoDao.selectById(tagId);
	}
	

	@Override
	public void save(H50TaggroupInfoEntity h50TaggroupInfo){
		h50TaggroupInfoDao.insert(h50TaggroupInfo);
	}
	
	@Override
	public void update(H50TaggroupInfoEntity h50TaggroupInfo){
		h50TaggroupInfoDao.updateById(h50TaggroupInfo);
	}
	
	@Override
	public void delete(Integer tagId){
		h50TaggroupInfoDao.deleteById(tagId);
	}
	
	@Override
	public void deleteBatch(Integer[] tagIds){
		h50TaggroupInfoDao.deleteBatchIds(Arrays.asList(tagIds));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H50TaggroupInfoEntity> page = new Page<H50TaggroupInfoEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h50TaggroupInfoDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h50TaggroupInfoDao.queryTotal(pageInfo.getCondition()));
	}


	/* (non-Javadoc)
	 * <p>Title : getAllgroupTag</p>
	 * <p>Description : </p>	
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.H50TaggroupInfoService#getAllgroupTag()
	 */
	@Override
	public List<String> getAllgroupTagContent() {
		return h50TaggroupInfoDao.getAllgroupTagContent();
	}


	/* 
	 * <p>Title : getQueryGroupTagKey</p>
	 * <p>Description : 取得查询的key</p>	
	 * @param tagGroupContent
	 * @return	 
	 */
	@Override
	public String getQueryGroupTagKey(String tagGroupContent) {
//		String queryGroupTagKey=null;
//		String translate=formulaCalculator.replaceNot(tagGroupContent);//!9 转换成 9！f
//		System.out.println("after###"+translate);
//		try{
//			queryGroupTagKey=formulaCalculator.getResult(translate);
//			System.out.println("key###"+queryGroupTagKey);
//		}catch(Exception e){
//			System.out.println("####组合标签表达式无法计算####");
//		}
		
		String translate=forMatterData.replaceNot(tagGroupContent);//!9 转换成 9！f
		System.out.println("after###"+translate);
		String queryGroupTagKey=null;
		try {
			nbl.init();
			nbl.tran(translate);//切分、存放到栈
			nbl.zz_hz();//中缀 —> 后缀
			queryGroupTagKey=nbl.js_nbl();//计算表达式的值
			System.out.println("NBL###->"+queryGroupTagKey);
		} catch (Exception e) {
			System.out.println("####组合标签表达式非法组合####");
		}
		return queryGroupTagKey;
	}
	
	@Override
	public String isRightFormat(String tagGroupContent) {
		String translate=forMatterData.replaceNot(tagGroupContent);//!9 转换成 9！f
		System.out.println("after###"+translate);
		String queryGroupTagKey=null;
		try {
			nbl.init();
			nbl.tran(translate);//切分、存放到栈
			nbl.zz_hz();//中缀 —> 后缀
			queryGroupTagKey=nbl.js_nbl();//计算表达式的值
			System.out.println("NBL###->"+queryGroupTagKey);
		} catch (Exception e) {
			System.out.println("####组合标签表达式非法组合####");
		}
		return queryGroupTagKey;
	}
}
