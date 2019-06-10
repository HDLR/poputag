package com.eastern.jinxin.business.recommend.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.utils.PhoneFormatCheckUtils;
import com.eastern.jinxin.sys.common.utils.StringUtils;
import com.eastern.jinxin.hbase.entity.TUserInfo;
import com.eastern.jinxin.redis.entity.TagConditionBase;
import com.eastern.jinxin.sys.configuration.shiro.utils.ShiroUtils;
import com.eastern.jinxin.business.recommend.dao.H62PolicyPushDao;
import com.eastern.jinxin.business.recommend.dao.H62PolicyPushTempDao;
import com.eastern.jinxin.business.recommend.dao.H62PushHisDao;
import com.eastern.jinxin.business.recommend.dao.H62RecomPolicyDao;
import com.eastern.jinxin.business.recommend.entity.H62PolicyPushEntity;
import com.eastern.jinxin.business.recommend.entity.H62PolicyPushTempEntity;
import com.eastern.jinxin.business.recommend.entity.H62PushHisEntity;
import com.eastern.jinxin.business.recommend.service.H62PolicyPushService;
import com.eastern.jinxin.business.recommend.service.H62PolicyPushTempService;
import com.eastern.jinxin.business.recommend.service.H62PushHisService;
import com.eastern.jinxin.business.userGroup.dao.H62CampaignInfoDao;

@Service("h62PolicyPushService")
 public class H62PolicyPushServiceImpl  extends ServiceImpl<H62PolicyPushDao, H62PolicyPushEntity> implements H62PolicyPushService {
	@Autowired
	private H62PolicyPushDao h62PolicyPushDao;
	@Autowired
	private H62RecomPolicyDao h62RecomPolicyDao;
	@Autowired
	private H62CampaignInfoDao h62CampaignInfoDao;
	@Autowired
	private H62PolicyPushTempDao h62PolicyPushTempDao;
	@Autowired
	private H62PushHisDao h62PushHisDao;
	@Autowired
	private H62PushHisService h62PushHisService;
	@Autowired
	private H62PolicyPushTempService h62PolicyPushTempService;
	@Autowired
	private PolicyPushSmsServiceImpl policyPushSmsService;
	
	//推送
	public Map<String, Object> sendPush(String pushId){
		Map<String, Object> rm = new HashMap<String, Object>();
		
		H62PolicyPushEntity h62PolicyPushEntity = this.queryObject(Integer.parseInt(pushId));
		H62PolicyPushTempEntity h62PolicyPushTempEntity = h62PolicyPushTempService.queryObject(h62PolicyPushEntity.getTempId());
		
		boolean sendTest = false;
		//为了防止骚扰，设置发送短信到开发人员手机号上
		List<Map<String, Object>> tests = h62PushHisDao.getTest();
		if(null != tests && !tests.isEmpty()) {
			Map<String, Object> m = tests.get(0);
			Integer isTest = (Integer) m.get("is_test");
			String testNo = (String) m.get("test_no");
			if (isTest.intValue() == 1) {
				sendTest = true;
				H62PushHisEntity his = getH62PushHisEntity(1, 0, h62PolicyPushTempEntity.getTempId(), pushId, 1);
				h62PushHisService.insert(his);
			} 
		}
		
		if(!sendTest) {
			MutliThread m1 = new MutliThread("SMSThread", pushId, "" + h62PolicyPushEntity.getCampId(), h62PolicyPushTempEntity);
			m1.start();
		}
		
		return rm;
	}
	
	class MutliThread extends Thread {
		private String campId;
		private H62PolicyPushTempEntity ppt;
		private String pushId;
		private int pageSize = 100;

		MutliThread(String name, String pushId, String campId, H62PolicyPushTempEntity ppt) {
			super(name);// 调用父类带参数的构造方法
			this.campId = campId;
			this.ppt = ppt;
			this.pushId = pushId;
		}

		public void run() {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("screeningId", campId);
			// 获取redis数组
			BitSet userBit = policyPushSmsService.getUserBitFromRedisSms(campId);
			// 获取userBit元素的长度
			int page = getPage(userBit, pageSize);
			List<TUserInfo> list = null;
			StringBuffer tel = new StringBuffer();
			String telStr = "";
			int success = 0;
			int fail = 0;
			H62PushHisEntity his = getH62PushHisEntity(success, fail, ppt.getTempId(), pushId, 0);
			h62PushHisService.insert(his);
			for (int i = 1; i <= page; i++) {
				list = policyPushSmsService.getUserDataFromHbaseSms(userBit, i, pageSize);
				tel = new StringBuffer();
				for (TUserInfo info : list) {
					/*
					if (PhoneFormatCheckUtils.isChinaPhoneLegal(info.getTel())) {
						tel.append(info.getTel()).append(";");
						success++;
					} else {
						fail++;
					}
					*/
				}
				if (tel.length() > 0) {
					telStr = tel.toString();
					telStr = telStr.substring(0, telStr.length() - 1);
				}
			}
			his.setHisSuccess(success);
			his.setHisFail(fail);
			his.setHisStatu(1);
			h62PushHisService.update(his);
		}
	}
	
	private int getPage(BitSet userBit, int pageSize) {
		String bit = userBit.toString();
		bit = bit.replace("{", "");
		bit = bit.replace("}", "");
		int page = 0;
		if (StringUtils.isNotBlank(bit)) {
			String[] bitArr = bit.split(",");
			int rows = bitArr.length;
			if (rows <= pageSize && rows > 0) {
				page = 1;
			} else if (rows > pageSize) {
				page = (rows) % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;
			}
		}
		return page;
	}

	
	private H62PushHisEntity getH62PushHisEntity(int success, int fail, int tempId, String pushId, int statu) {
		H62PushHisEntity obj = new H62PushHisEntity();
		obj.setCreateDt(new Date());
		obj.setCreateUser(new Long(ShiroUtils.getUserEntity().getUserId()).intValue());
		
		obj.setTempId(tempId);
		obj.setHisSuccess(success);
		obj.setHisFail(fail);
		obj.setPushId(Integer.parseInt(pushId));
		obj.setHisStatu(statu);
		return obj;
	}
	
	public Map<String, List> queryAllParams(){
		Map<String, List> m = new HashMap<String, List>();
		m.put("policys", h62RecomPolicyDao.queryRecomPolicyAll());
		m.put("camps", h62CampaignInfoDao.queryCampaignInfoAll());
		m.put("temps", h62PolicyPushTempDao.queryPolicyPushTempAll());
		return m;
	}
	
	public List<H62PolicyPushEntity> queryPolicyPushs(Pagination page, Map<String, Object> map){
		return h62PolicyPushDao.queryPolicyPushs(page, map);
	}
	
	@Override
	public H62PolicyPushEntity queryObject(Integer pushId){
		return h62PolicyPushDao.selectById(pushId);
//		return h62PolicyPushDao.getPolicyPush(pushId);
	}
	

	@Override
	public void save(H62PolicyPushEntity h62PolicyPush){
		h62PolicyPush.setCreateDt(new Date());
		h62PolicyPush.setCreateUser(new Long(ShiroUtils.getUserEntity().getUserId()).intValue());
		h62PolicyPushDao.insert(h62PolicyPush);
	}
	
	@Override
	public void update(H62PolicyPushEntity h62PolicyPush){
		h62PolicyPushDao.updateById(h62PolicyPush);
	}
	
	@Override
	public void delete(Integer pushId){
		h62PolicyPushDao.deleteById(pushId);
	}
	
	@Override
	public void deleteBatch(Integer[] pushIds){
		h62PolicyPushDao.deleteBatchIds(Arrays.asList(pushIds));
	}
	
	@Override
	public void queryList(PageInfo pageInfo) {
		Page<H62PolicyPushEntity> page = new Page<H62PolicyPushEntity>(pageInfo.getNowpage(), pageInfo.getSize());
//		pageInfo.setRows(h62PolicyPushDao.queryList(page, pageInfo.getCondition()));
		pageInfo.setRows(h62PolicyPushDao.queryPolicyPushs(page, pageInfo.getCondition()));
	    pageInfo.setTotal(h62PolicyPushDao.queryTotal(pageInfo.getCondition()));
	}
	
	
}
