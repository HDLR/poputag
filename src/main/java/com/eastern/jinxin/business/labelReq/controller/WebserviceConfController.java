package com.eastern.jinxin.business.labelReq.controller;

import java.util.List;
import java.util.Map;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;
import com.eastern.jinxin.sys.common.common.utils.Constant;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.labelReq.entity.WebserviceConfEntity;
import com.eastern.jinxin.business.labelReq.service.WebserviceConfService;
import com.eastern.jinxin.business.userGroup.service.H62CampaignInfoService;


/**
 * webservice的API
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-19 10:52:54
 */
@RestController
@RequestMapping("tagreq/webserviceconf")
public class WebserviceConfController extends AbstractController {
	@Autowired
	private WebserviceConfService webserviceConfService;
	@Autowired
	private H62CampaignInfoService h62CampaignInfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"apiName"});
		webserviceConfService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	@RequestMapping("/queryAll")
	public R queryAll(){
		if(SecurityUtils.getSubject().isPermitted("tagreq:webservice:conf")) {
			List<WebserviceConfEntity> list = webserviceConfService.queryAll();
			return R.ok().put("list", list);
		}
		return R.ok().put("list", null);
	}
	
	@RequestMapping("/allGroup")
	public R allGroup(){
		List<Map<String, String>> rMaps = h62CampaignInfoService.queryCampaignTress2();
		return R.ok().put("list", rMaps);
	}
	
	
	/**
	 * 信息
	 */
	@SysLogAnn("API配置明细查询")
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		WebserviceConfEntity webserviceConf = webserviceConfService.queryObject(id);
		
		return R.ok().put("webserviceConf", webserviceConf);
	}
	
	/**
	 * 保存
	 */
	@SysLogAnn("API配置创建")
	@RequestMapping("/save")
	@RequiresPermissions("tagreq:webservice:conf")
	public R save(@RequestBody WebserviceConfEntity webserviceConf){
		webserviceConfService.save(webserviceConf);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLogAnn("API配置更新")
	@RequestMapping("/update")
	@RequiresPermissions("tagreq:webservice:conf")
	public R update(@RequestBody WebserviceConfEntity webserviceConf){
		webserviceConfService.update(webserviceConf);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLogAnn("API配置删除")
	@RequestMapping("/delete")
	@RequiresPermissions("tagreq:webservice:conf")
	public R delete(@RequestBody Long[] ids){
		webserviceConfService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
