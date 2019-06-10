package com.eastern.jinxin.business.statis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.business.statis.dao.H62UsageItemLogDao;
import com.eastern.jinxin.business.statis.entity.H62UsageItemLogEntity;
import com.eastern.jinxin.business.statis.service.H62UsageItemLogService;



@Service("h62UsageItemLogService")
 public class H62UsageItemLogServiceImpl  extends ServiceImpl<H62UsageItemLogDao, H62UsageItemLogEntity> implements H62UsageItemLogService {
	@Autowired
	private H62UsageItemLogDao h62UsageItemLogDao;
	
	@Override
	public List<Map<String, Object>> queryCampUsageLog(List<String> campIds){
		Map<String, List<String>> m = new HashMap<String, List<String>>();
		m.put("campIds", campIds);
		List<Map<String, Object>> mList = h62UsageItemLogDao.queryCampUsageLog(m);
		
		//排序
		Collections.sort(mList, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> m1, Map<String, Object> m2) {
                Integer sum1 = Integer.parseInt("" + m1.get("count"));
                Integer sum2 = Integer.parseInt("" + m2.get("count"));
                return sum1.compareTo(sum2);
            }
        });
		return mList;
	}
	
	@Override
	public void insertH62UsageItemLog(int itemId, String itemType, String operateType, long operateUser) {
		H62UsageItemLogEntity e = new H62UsageItemLogEntity();
		e.setItemId(itemId);
		e.setItemType(itemType);
		e.setOperateType(operateType);
		e.setOperateUser(new Long(operateUser).intValue());
		Date dt = new Date();
		e.setCreatedTs(dt);
		e.setUpdatedTs(dt);
		this.insert(e);
	}
	
	@Override
	public H62UsageItemLogEntity queryObject(Integer id){
		return h62UsageItemLogDao.selectById(id);
	}
	

	@Override
	public void save(H62UsageItemLogEntity h62UsageItemLog){
		h62UsageItemLogDao.insert(h62UsageItemLog);
	}
	
	@Override
	public void update(H62UsageItemLogEntity h62UsageItemLog){
		h62UsageItemLogDao.updateById(h62UsageItemLog);
	}
	
	@Override
	public void delete(Integer id){
		h62UsageItemLogDao.deleteById(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		h62UsageItemLogDao.deleteBatchIds(Arrays.asList(ids));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H62UsageItemLogEntity> page = new Page<H62UsageItemLogEntity>(pageInfo.getNowpage(), pageInfo.getSize());
		pageInfo.setRows(h62UsageItemLogDao.queryList(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h62UsageItemLogDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
