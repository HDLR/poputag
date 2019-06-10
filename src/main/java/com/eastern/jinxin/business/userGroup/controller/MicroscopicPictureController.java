package com.eastern.jinxin.business.userGroup.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.label.service.H50TagInfoService;
import com.eastern.jinxin.business.statis.service.H62UsageItemLogService;
import com.eastern.jinxin.business.statis.utils.StatisUtils;
import com.eastern.jinxin.business.userGroup.service.MicroscopicPictureService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;

@RestController
@RequestMapping("userGroup/microscopicPicture")
public class MicroscopicPictureController extends AbstractController {

	@Autowired
	private MicroscopicPictureService microscopicPictureService;
	@Autowired
	private H62UsageItemLogService h62UsageItemLogService;
	@Autowired
	private H50TagInfoService h50TagInfoService;
	
	@RequestMapping("/listByPage")
	public R listByPage(@RequestParam Map<String, Object> params) {
		PageInfo pageInfo = new PageInfo(params,new String[]{"campIdP"});
		microscopicPictureService.listByPage(pageInfo);
		
		h62UsageItemLogService.insertH62UsageItemLog(Integer.parseInt("" + params.get("campIdP")), StatisUtils.ITEM_TYPE_CAMP, StatisUtils.OPERATE_TYPE_MICR, getUserId());
		
		return R.ok().put("page", pageInfo);
	}
	
	@SysLogAnn("查询微观画像明细")
	@RequestMapping("/queryPersonDetail")
	public R queryPersonDetail(String userGuid, String name) {
		Map<String, Object> personMap = microscopicPictureService.queryUserDetail(userGuid);
		
		/*
		{
			tagCtgyNm=出生年代, 
			userCount=2619947, 
			tagId=5001010699, 
			tagCtgyId=50010106, 
			tagNm=未知年代
		}
		*/
		/*List<String> checkTags = new ArrayList<String>();
		for(Map<String, Object> m : personMap) {
			checkTags.add("" + m.get("tagId"));
		}
		
		
		data: c,
        links: m,
        categories: n,
        
		List<Map<String, Object>> parents = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> dataChild = new HashMap<String, List<Map<String, Object>>>();
		
		List<Map<String, Object>> mapLists = h50TagInfoService.queryTagsData(checkTags);
		
		{
			showparent_id
			showparent_nm
			
			tag_ctgy_id
			tag_ctgy_nm
			
			tag_id
			tag_nm
		}
		
		
		Map<String, String> tagCtgyNmMap = new HashMap<String, String>();
		Map<String, String> parentMap = new HashMap<String, String>();
		
		for(Map<String, Object> m : mapLists) {
			
			Object showparent_nm = m.get("showparent_nm");
			Object tag_ctgy_nm = m.get("tag_ctgy_nm");
			Object tag_nm = m.get("tag_nm");
			
			if(dataChild.containsKey(showparent_nm)) {
				Map<String, Object> cMap = new HashMap<String, Object>();
				cMap.put("tag_ctgy_nm", tag_ctgy_nm);
				cMap.put("tag_nm", tag_nm);
				dataChild.get(showparent_nm).add(cMap);
			}else {
				Map<String, Object> cMap = new HashMap<String, Object>();
				List<Map<String, Object>> lishMap = new ArrayList<Map<String, Object>>();
				cMap.put("tag_ctgy_nm", tag_ctgy_nm);
				cMap.put("tag_nm", tag_nm);
				lishMap.add(cMap);
				dataChild.put((String)showparent_nm, lishMap);
				
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("name", showparent_nm);
				
				if("家庭特征".equals(showparent_nm)) {
					p.put("color", "#fc9b6f");
					p.put("img", "jttx.png");
					
				}else if("工作特征".equals(showparent_nm)) {
					p.put("color", "#ff7b8c");
					p.put("img", "gztx.png");
					
				}else if("流动状况".equals(showparent_nm)) {
					p.put("color", "#83d587");
					p.put("img", "ldzk.png");
					
				}else if("资产状况".equals(showparent_nm)) {
					p.put("color", "#9587f1");
					p.put("img", "zczk.png");
					
				}else if("健康状况".equals(showparent_nm)) {
					p.put("color", "#72ddc6");
					p.put("img", "jkzk.png");
					
				}else if("专业领域".equals(showparent_nm)) {
					p.put("color", "#7d8ef3");
					p.put("img", "zyly.png");
					
				}else if("社会保障".equals(showparent_nm)) {
					p.put("color", "#46a8f9");
					p.put("img", "shbz.png");
					
				}else if("信用评价".equals(showparent_nm)) {
					p.put("color", "#f5bc34");
					p.put("img", "xypj.png");
					
				}else {
					p.put("color", "#4da1ff");
					p.put("img", "jbsx.png");
				}
				parents.add(p);
			}
			
			parentMap.put("" + m.get("showparent_id"), "" + showparent_nm);
		};*/
		
		Map<String, Object> resM = new HashMap<String, Object>();
		//resM.put("personInfo", personMap)
		resM.put("name", name);
		resM.put("dataChild", personMap);
		return R.ok().put("userPerson", resM);
	}
	
}
