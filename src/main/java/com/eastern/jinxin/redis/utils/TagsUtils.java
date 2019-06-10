package com.eastern.jinxin.redis.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;

public class TagsUtils {
	
	public static final String GRP_BASE = "grp_0";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map gainTagsOne(String tag_ctgy_id, String tag_id) {
//		String jsonStr = "{\"grp_0\":{\""+ tag_ctgy_id +"\":[\""+ tag_id +"\"]}}";
		
		Map mChilder = new LinkedHashMap<>();
		List<String> list = new ArrayList<>();
		list.add(tag_id);
		mChilder.put(tag_ctgy_id, list);
		
		Map mParent = new LinkedHashMap<>();
		mParent.put(GRP_BASE, mChilder);
		
		return mParent;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map gainTagsMany(List<Map<String, Object>> mapLists) {
		if(null == mapLists || mapLists.size()<1) {
			return null;
		}
		Map mChilder = new LinkedHashMap<>();
		for(Map<String, Object> m : mapLists) {
			
			String tag_ctgy_id = (String)m.get("tag_ctgy_id");
			String tag_id = "" + m.get("tag_id");
			Object tag_id_group = m.get("tag_id_group");
			
			tag_id = (null != tag_id_group? ("" + tag_id_group) : tag_id);
			
			if(mChilder.containsKey(tag_ctgy_id)) {
				List<String> sList = (List<String>)mChilder.get(tag_ctgy_id);
				sList.add(tag_id);
				mChilder.put(tag_ctgy_id, sList);
			}else {
				List<String> list = new ArrayList<>();
				list.add(tag_id);
				mChilder.put(tag_ctgy_id, list);
			}
		}
		
		Map mParent = new LinkedHashMap<>();
		mParent.put(GRP_BASE, mChilder);
		
		return mParent;
	}
	
	//组合同类标签，大中小类
	public static Map<String, Map<String, List<String>>> combinationParent(List<Map<String, Object>> tags){
		//Map<String, Map<String, List<String>>> tagMap = new HashMap<String, Map<String, List<String>>>();
		Map<String, Map<String, List<String>>> tagMap = new LinkedMap();
		for(Map<String, Object> m : tags) {
			/*
			c.tag_ctgy_id showparent_id,
			c.tag_ctgy_nm showparent_nm,
			a.tag_ctgy_id,
			b.tag_ctgy_nm,
			a.tag_id,
			d.tag_nm,
			*/
			String showparent_nm = (String)m.get("showparent_nm");
			String tag_ctgy_nm = (String)m.get("tag_ctgy_nm");
			String tag_nm = (String)m.get("tag_nm");
			
			if(tagMap.containsKey(showparent_nm)) {
				Map<String, List<String>> cMap = tagMap.get(showparent_nm);
				if(cMap.containsKey(tag_ctgy_nm)) {
					cMap.get(tag_ctgy_nm).add(tag_nm);
				}else {
					List<String> nList = new ArrayList<String>();
					nList.add(tag_nm);
					cMap.put(tag_ctgy_nm, nList);
				}
			}else {
				//Map<String, List<String>> cMap = new HashMap<String, List<String>>();
				Map<String, List<String>> cMap = new LinkedMap();
				List<String> list = new ArrayList<String>();
				list.add(tag_nm);
				cMap.put(tag_ctgy_nm, list);
				tagMap.put(showparent_nm, cMap);
			}
		}
		return tagMap;
	}
	
	//组合同类标签
	public static Map<String, String> combinationTags(List<Map<String, Object>> tags) {
		Map<String, String> tagMap = new HashMap<String, String>();
		for(Map<String, Object> m : tags) {
			
			/*
			c.tag_ctgy_id showparent_id,
			c.tag_ctgy_nm showparent_nm,
			a.tag_ctgy_id,
			b.tag_ctgy_nm,
			a.tag_id,
			d.tag_nm,
			*/
			
//			String tag_ctgy_id = (String)m.get("tag_ctgy_id");
			String tag_ctgy_nm = (String)m.get("tag_ctgy_nm");
			String tag_nm = (String)m.get("tag_nm");
			
			if(tagMap.containsKey(tag_ctgy_nm)) {
				tagMap.put(tag_ctgy_nm, tagMap.get(tag_ctgy_nm) + "，" + tag_nm);
			}else {
				tagMap.put(tag_ctgy_nm, tag_nm);
			}
		}
		return tagMap;
	}
	
	//组合同类标签
	public static Map<String, String> combinationPolicyTags(List<Map<String, Object>> tags) {
		Map<String, String> tagMap = new HashMap<String, String>();
		for(Map<String, Object> m : tags) {
			
			/*
			SELECT
				c.tag_type_id,
				c.tag_type_nm,
				b.tag_id,
				b.tag_nm
			FROM
			*/
			
//				String tag_type_id = (String)m.get("tag_type_id");
			String tag_ctgy_nm = (String)m.get("tag_type_nm");
			String tag_nm = (String)m.get("tag_nm");
			
			if(tagMap.containsKey(tag_ctgy_nm)) {
				tagMap.put(tag_ctgy_nm, tagMap.get(tag_ctgy_nm) + "，" + tag_nm);
			}else {
				tagMap.put(tag_ctgy_nm, tag_nm);
			}
		}
		return tagMap;
	}
}
