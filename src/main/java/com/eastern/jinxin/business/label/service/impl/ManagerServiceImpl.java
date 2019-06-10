package com.eastern.jinxin.business.label.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.business.label.dao.ManagerDao;
import com.eastern.jinxin.business.label.entity.ManagerEntity;
import com.eastern.jinxin.business.label.entity.TagInfoEntity;
import com.eastern.jinxin.business.label.service.ManagerService;
import com.eastern.jinxin.sys.common.DateUtils;
import com.eastern.jinxin.sys.common.NumUtils;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.excel.enums.TagStaticCode;
import com.eastern.jinxin.sys.common.excel.enums.colnums.imp.TagColumns;



@Service("managerService")
 public class ManagerServiceImpl  extends ServiceImpl<ManagerDao, ManagerEntity> implements ManagerService {
	@Autowired
	private ManagerDao managerDao;
	private List<String> resList=null;
	
	@Override
	public ManagerEntity queryObject(String tagCtgyId){
		return managerDao.selectById(tagCtgyId);
	}
	

	@Override
	public void save(ManagerEntity manager){
		managerDao.insert(manager);
	}
	
	@Override
	public void update(ManagerEntity manager){
		managerDao.updateById(manager);
	}
	
	@Override
	public void delete(String tagCtgyId){
		managerDao.deleteById(tagCtgyId);
	}
	
	@Override
	public void deleteBatch(String[] tagCtgyIds){
		managerDao.deleteBatchIds(Arrays.asList(tagCtgyIds));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<ManagerEntity> page = new Page<ManagerEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(managerDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(managerDao.queryTotal(pageInfo.getCondition()));
	}


	@Override
	public List<Map<String, Object>> queryAllTagExcel() {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		List<Map<String,Object>> list = null;
		//到DAO中实现该查询
	    list = managerDao.queryAllTagExcel(paramMap);
		return list;
	}


	/* (non-Javadoc)
	 * <p>Title : queryTagListExcel</p>
	 * <p>Description : </p>	
	 * @param pageParam
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.ManagerService#queryTagListExcel(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> queryTagListExcel(Map<String, String> paramMap) {
		List<Map<String,Object>> list = null;
		int tagId=Integer.parseInt(paramMap.get("tagId"));
		 if(paramMap.get("queryOrSelect").equals("0")){
			paramMap.put("id", tagId+"");
			list = managerDao.queryListExcel2(paramMap);
		}else{
			if (!paramMap.get(TagStaticCode.tagLevel).equals(TagStaticCode.queryCType)) {
				list = managerDao.queryTagCtyListExcel2(paramMap);
			} else {
				list = managerDao.queryTagListExcel2(paramMap);
			}
		}
		return list;
	}


	/* (non-Javadoc)
	 * <p>Title : insertExcelData</p>
	 * <p>Description : </p>	
	 * @param list
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.ManagerService#insertExcelData(java.util.List)
	 */
	@Override
	public Map<String,List<String>> insertExcelData(List<Map<String, String>> list,Long userId) throws Exception {
		Map<String, Object> param = null;// new HashMap<String, Object>();
		Map<String, String> excMap = null;
		Map<String,List<String>> result=new HashMap<String,List<String>>();
		resList=new ArrayList<String>();
		String tag_data_tpe = "";// excMap.get(TagColums.tag_data_tpe);
		int count = 0;
		int success = 0;
		List<String> listIds = this.getAllTagIds();
		for (int i = 0; i < list.size(); i++) {
			count++;
			excMap = list.get(i);
			tag_data_tpe = excMap.get(TagColumns.tag_type.getCodeName());
			param = new HashMap<String, Object>();
			// 获取所有的标签ids
			try {
				if (!dataValid(i, excMap, listIds, param, result)) {
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			param.put("id", "");
			// 创建人修改人
		    param.put(TagStaticCode.create_user, userId);
		    param.put(TagStaticCode.update_user, userId);
			// 创建时间修改时间
			Date dt = new Date();
			param.put(TagStaticCode.created_ts, dt);
			param.put(TagStaticCode.updated_ts, dt);
			if (TagStaticCode.excel_tag_children_type.equals(tag_data_tpe)) {
				this.saveTag(param);
			} else {
				this.saveTagCtyg(param);
			}
			listIds.add((String) param.get(TagStaticCode.tag_id));
			success++;
			// insertMapTagIds.put((String)param.get("tag_id"),(String)param.get("have_tag_ind"));
			// System.out.println(param.toString());
		}
		List<String> datalist=new ArrayList<String>();
		datalist.add(""+count);
		datalist.add(""+success);
		datalist.add(""+(count - success));
		result.put("data", datalist);
		return result;
	}
	
	/**
	 * 获取所有的标签ids
	 * 
	 * @return
	 */
	public List<String> getAllTagIds() {
		List<String> list=managerDao.getAllTagIds();
		return list;
	}
	
	
	public void saveTag(Map<String, Object> param)throws Exception {
		// 编辑保存
		if (StringUtils.isNotBlank(param.get("id").toString())) {
			managerDao.updateTag(param);
		} // 新增保存
		else {
			managerDao.insertTag(param);
		}
	}
	
	public void saveTagCtyg(Map<String, Object> param)throws Exception {
		String pid = param.get(TagStaticCode.tag_ctgy_id).toString();
		if (StringUtils.isNotBlank(pid) && !pid.equals("-1")) {
			// 获取父类级别
		TagInfoEntity tie = this.findById(Integer.parseInt(param.get(TagStaticCode.tag_ctgy_id).toString()), TagStaticCode.ptype);
		param.put(TagStaticCode.tag_ctgy_level, Integer.parseInt(tie.getTag_ctgy_level()) + 1);
		} else {
			param.put(TagStaticCode.tag_ctgy_level, "1");
		}
		// 编辑保存
		if (StringUtils.isNotBlank(param.get("id").toString())) {
			managerDao.updateTagCtyg(param);
		} // 新增保存
		else {
			managerDao.insertTagCtyg(param);
		}
	}
	
	/**
	 * 校验插入数据的有效性
	 * 
	 * @param excMap
	 * @return
	 * @throws Exception
	 */
	private boolean dataValid(int index, Map<String, String> excMap, List<String> listIds, Map<String, Object> param,
			Map<String,List<String>> res) throws Exception {
		String tag_desc = excMap.get(TagColumns.tag_desc.getCodeName());
		if (!dataValidTagId(index, excMap, listIds, param, res)) {
			return false;
		}
		if (!dataValidTagNM(index, excMap, listIds, param, res)) {
			return false;
		}
		if (!dataValidTagCtgy(index, excMap, listIds, param, res)) {
			return false;
		}
		initHaveTagInd(index, excMap, listIds, param, res);
		initTagStatus(index, excMap, listIds, param, res);
		initEnableAndDisbable(index, excMap, listIds, param, res);
		param.put(TagStaticCode.tag_desc, StringUtils.isNotBlank(tag_desc) ? tag_desc : "");
		param.put(TagStaticCode.tag_type_cd, "A");
		param.put(TagStaticCode.unknown_ind, "0");
		return true;
	}
	
	private boolean dataValidTagId(int index, Map<String, String> excMap, List<String> listIds,
			Map<String, Object> param,Map<String,List<String>> res) {
		String tag_id = excMap.get(TagColumns.tag_id.getCodeName());
		if (StringUtils.isBlank(tag_id)) {
			String msg="第" + getIndex(index) + "行的记录,标签id不能为空";
			resList.add(msg);
			res.put("tips", resList);
			return false;
		}
		if (listIds.contains(tag_id)) {
			String msg="第" + getIndex(index) + "行的记录,标签id已存在";
			resList.add(msg);
			res.put("tips", resList);
			return false;
		}
		if (!NumUtils.isNumeric(tag_id)) {
			String msg="第" + getIndex(index) + "行的记录,标签id必须为数字";
			resList.add(msg);
			res.put("tips", resList);
			return false;
		}
		param.put("tag_id", tag_id);
		return true;
	}

	private boolean dataValidTagNM(int index, Map<String, String> excMap, List<String> listIds,
			Map<String, Object> param,Map<String,List<String>> res) {
		String tag_nm = excMap.get(TagColumns.tag_nm.getCodeName());
		if (StringUtils.isBlank(tag_nm)) {
			String msg="第" + getIndex(index) + "行的记录,标签名称不能为空";
			resList.add(msg);
			res.put("tips", resList);
			return false;
		}
		param.put("tag_nm", tag_nm);
		return true;

	}

	private boolean dataValidTagCtgy(int index, Map<String, String> excMap, List<String> listIds,
			Map<String, Object> param,Map<String,List<String>> res) {
		String tag_data_tpe = excMap.get(TagColumns.tag_type.getCodeName());
		String tag_ctgy_id = excMap.get(TagColumns.tag_ctgy_id.getCodeName());
		if (TagStaticCode.excel_tag_children_type.equals(tag_data_tpe)) {
			if (StringUtils.isBlank(tag_ctgy_id)) {
				String msg="第" + getIndex(index) + "行的记录,标签名称ID不能为空";
				resList.add(msg);
				res.put("tips", resList);
				return false;
			}
		}
		TagInfoEntity obj = null;
		if (StringUtils.isNotBlank(tag_ctgy_id)) {
			obj = (TagInfoEntity) this.findById(Integer.parseInt(tag_ctgy_id), TagStaticCode.ptype);
			if (obj == null) {
				String msg="第" + getIndex(index) + "行的记录,上级标签不存在";
				resList.add(msg);
				res.put("tips", resList);
				return false;
			}
		}
		// 上级标签是否可以使用
		if (obj != null) {
			if (TagStaticCode.excel_tag_children_type.equals(tag_data_tpe)) {
				if (!obj.getHave_tag_ind().equals("1")) {
					String msg="第" + getIndex(index) + "行的记录,上级标签不可以使用";
					resList.add(msg);
					res.put("tips", resList);
					return false;
				}
			} else {
				if (obj.getHave_tag_ind().equals("1")) {
					String msg="第" + getIndex(index) + "行的记录,上级标签不可以使用";
					resList.add(msg);
					res.put("tips", resList);
					return false;
				}
			}
		}
		param.put(TagStaticCode.tag_ctgy_id, StringUtils.isNotBlank(tag_ctgy_id) ? tag_ctgy_id : "");
		return true;
	}

	private void initHaveTagInd(int index, Map<String, String> excMap, List<String> listIds, Map<String, Object> param,
			Map<String,List<String>> res) {
		String have_tag_ind = excMap.get(TagColumns.have_tag_ind.getCodeName());
		if (StringUtils.isNotBlank(have_tag_ind)) {
			if (have_tag_ind.equals(TagStaticCode.excel_yes)) {
				param.put(TagStaticCode.have_tag_ind, 1);
			} else {
				param.put(TagStaticCode.have_tag_ind, 0);
			}
		} else {
			param.put(TagStaticCode.have_tag_ind, 1);
		}
	}
	
	private void initTagStatus(int index, Map<String, String> excMap, List<String> listIds, Map<String, Object> param,
			Map<String,List<String>> res) {
		String tag_status = excMap.get(TagColumns.tag_status.getCodeName());
		String tag_data_tpe = excMap.get(TagColumns.tag_type.getCodeName());
		if (TagStaticCode.excel_tag_children_type.equals(tag_data_tpe)) {
			if (TagStaticCode.excel_tag_status_disable.equals(tag_status)) {
				param.put(TagStaticCode.tag_status, 0);
			} else {
				param.put(TagStaticCode.tag_status, 1);
			}
		} else {
			if (TagStaticCode.excel_tag_status_disable.equals(tag_status)) {
				param.put(TagStaticCode.tag_status, 0);
			} else {
				param.put(TagStaticCode.tag_status, 2);
			}
		}
	}

	private void initEnableAndDisbable(int index, Map<String, String> excMap, List<String> listIds,
			Map<String, Object> param,Map<String,List<String>> res) throws Exception {
		String enabled_dt = excMap.get(TagColumns.enabled_dt.getCodeName());
		String disabled_dt = excMap.get(TagColumns.enabled_dt.getCodeName());
		param.put(TagStaticCode.enabled_dt, new Date());
		param.put(TagStaticCode.disabled_dt, DateUtils.getyyyyMMdd("2099-01-01"));
		if (StringUtils.isNotBlank(enabled_dt)) {
			param.put(TagStaticCode.enabled_dt, DateUtils.getyyyyMMddRepalcexg(enabled_dt));
		}
		if (StringUtils.isNotBlank(disabled_dt)) {
			param.put(TagStaticCode.disabled_dt, DateUtils.getyyyyMMddRepalcexg(disabled_dt));
		}
	}
	
	private String getIndex(int index) {
		return "【"+(index + 4)+"】";
	}
	
	public TagInfoEntity findById(Integer id, String type) {
		TagInfoEntity obj = null;
		if (type.equals(TagStaticCode.ptype)) {
			obj = (TagInfoEntity) managerDao.findTagCtgyById(id);
		} else {

			obj = (TagInfoEntity) managerDao.findTagById(id);
		}

		return obj;
	}


	/* (non-Javadoc)
	 * <p>Title : queryListById</p>
	 * <p>Description : </p>	
	 * @param pageParam
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.ManagerService#queryListById(java.util.Map)
	 */
	@Override
	public void queryListById(PageInfo pageInfo) {
		Page<TagInfoEntity> page = new Page<TagInfoEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		List<TagInfoEntity> list=managerDao.queryListById(page, pageInfo.getCondition());
		pageInfo.setRows(list);
	    pageInfo.setTotal(managerDao.queryClickNodeListTotal(pageInfo.getCondition()));
	}


	@Override
	public void queryTagCtyList(PageInfo pageInfo) {
		Page<TagInfoEntity> page = new Page<TagInfoEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		if (!pageInfo.getCondition().get(TagStaticCode.tagLevel).equals(TagStaticCode.queryCType)) {
			pageInfo.setRows(managerDao.queryTagCtyList(page, pageInfo.getCondition()));
		    pageInfo.setTotal(managerDao.queryTagCtyListTotal(pageInfo.getCondition()));
		} else {
			//查询叶子标签
			pageInfo.setRows(managerDao.queryTagList(page, pageInfo.getCondition()));
		    pageInfo.setTotal(managerDao.queryTagCounts(pageInfo.getCondition()));
		}

	}


	@Override
	public TagInfoEntity getTagInfoById(String tagId) {
		return managerDao.getTagInfoById(tagId);
	}


	/* (non-Javadoc)
	 * <p>Title : listLevelAll</p>
	 * <p>Description : </p>	
	 * @return	 
	 * @see com.eastern.jinxin.business.label.service.ManagerService#listLevelAll()
	 */
	@Override
	public Map<String, Object> listLevelAll() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		List<String> list = managerDao.listLevelAll();
		String levelStr = "";
		map.put(TagStaticCode.excel_tag_type, "-1");
		for (String level : list) {
			levelStr = NumUtils.toChinese(level);
			map.put("第" + levelStr + "级别", level);
		}
		map.put(TagStaticCode.excel_tag_children_type, "-2");
		return map;
		
	}

}
