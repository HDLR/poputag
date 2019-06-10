package com.eastern.jinxin.business.userGroup.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.userGroup.service.H62CampaignInfoService;

@RestController
@RequestMapping("userGroup/analysis")
public class GroupAnalysisController {

	@Autowired
	private H62CampaignInfoService h62CampaignInfoService;
	
	@SysLogAnn("群热度分析")
	@RequestMapping("/list")
	public R queryPersonCountSum(@RequestParam("campIds") List<String> campIds) {
		R returnR = R.ok();
		
		List<Map<String, Object>> ms = h62CampaignInfoService.heatAnalysis(campIds);
		returnR.put("camps", ms);
		
		return returnR;
	}
}
