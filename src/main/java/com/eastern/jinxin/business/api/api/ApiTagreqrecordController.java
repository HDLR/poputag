package com.eastern.jinxin.business.api.api;

import com.eastern.jinxin.business.api.annotation.IgnoreAuth;
import com.eastern.jinxin.business.labelReq.entity.TagReqRecordEntity;
import com.eastern.jinxin.business.labelReq.service.TagReqRecordService;
import com.eastern.jinxin.business.userGroup.entity.H62CampaignInfoEntity;
import com.eastern.jinxin.business.userGroup.service.H62CampaignInfoService;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiTagreqrecordController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiTagreqrecordController.class);
	@Autowired
	private TagReqRecordService tagReqRecordService;

	@IgnoreAuth
	@GetMapping("taggroup")
	public R taggroup(long conId) {
		logger.info("请求参数conId：" + conId);
		R r = R.ok();
		
		TagReqRecordEntity tagReqRecord = tagReqRecordService.queryObject(conId);
		if(null != tagReqRecord) {
			//审批状态 0 未审批、1审批通过、-1 审批不通过
			String status = tagReqRecord.getApprovalStatus();
			if("1".equals(status)) {
				H62CampaignInfoEntity entity = SpringContextHolder.getBean(H62CampaignInfoService.class).queryCampaign2(tagReqRecord.getWebserviceId().intValue());
				r = r.put("data", entity);
				
			}else if("-1".equals(status)) {
				r = R.error().put("msg", "接口 审批不通过！");
			}else {
				r = R.error().put("msg", "接口未审批！");
			}
			
		}else {
			r = R.error().put("msg", "接口id有误，请管理员确认！");
		}
		
		logger.info("返回结果：" + r);
		return r;
	}
}
