package com.eastern.jinxin.business.label.controller;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.eastern.jinxin.sys.common.common.utils.Constant;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastern.jinxin.business.label.entity.CorpDepartStatInfoEntity;
import com.eastern.jinxin.business.label.service.CorpDepartStatInfoService;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;


/**
 * 法人库部门部门归集统计信息表
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-06-27 16:28:34
 */
@RestController
@RequestMapping("label/corpdepartstatinfo")
public class CorpDepartStatInfoController extends AbstractController {
	@Autowired
	private CorpDepartStatInfoService corpDepartStatInfoService;
	
	/**
	 * 单位状况列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("corpdepartstatinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"departName"});
		corpDepartStatInfoService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 单位的资源项数据质量列表
	 */
	@RequestMapping("/depDataPercentDetailList")
	@RequiresPermissions("dataQuality:list")
	public R getDepDataPercentDetailList(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"departName"});
		corpDepartStatInfoService.getDepDataPercentDetail(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	/**
	 * 法人库 各单位数据归集列表
	 */
	@RequestMapping("/getDataImputationList")
	@RequiresPermissions("departData:list")
	public R getDataImputationList(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"departName"});
		corpDepartStatInfoService.getDataImputationList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 海南省各单位数据归集状况
	 */
	@RequestMapping("/getDeptTableStatus/{departName}")
	public R getDepTableCount(@PathVariable("departName") String departName){
		List<Map<String,String>> depTableInfo=corpDepartStatInfoService.getDepTableCount(departName);
		return R.ok().put("depTableInfo", depTableInfo);
	}
	
	/**
	 * 海南省各单位数据质量
	 */
	@RequestMapping("/getDeptDataQualityStatus/{departName}")
	public R getDepDataPercent(@PathVariable("departName") String departName){
		List<Map<String,String>> depTableInfo=corpDepartStatInfoService.getDepDataPercent(departName);
		return R.ok().put("depTableInfo", depTableInfo);
	}
	
	/**
	 * 基础信息/扩展信息查询
	 */
	@RequestMapping("/getImputationInfo/{status}")
	public R getImputationInfo(@PathVariable("status") String status){
		List<Map<String,String>> imputationInfo=corpDepartStatInfoService.getImputationInfo(status);
		int count=corpDepartStatInfoService.getCount(status);//取得信息项的数量
		return R.ok().put("imputationInfo", imputationInfo).put("ItemAllCount", count);
	}
	
	/**
	 * 归集信息完整率查询
	 */
	@RequestMapping("/getImputationInfoFullRate")
	public R getImputationInfoFullRate(){
		List<Map<String,String>> imputationInfo=corpDepartStatInfoService.getImputationInfoFullRate();
		return R.ok().put("imputationInfo", imputationInfo);
	}
	
	/**
	 * 归集信息总记录数
	 */
	@RequestMapping("/getImputationInfoAllCount")
	public R getImputationInfoAllCount(){
		BigInteger allCount=corpDepartStatInfoService.getImputationInfoAllCount();
		return R.ok().put("allCount", allCount);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{departName}")
	@RequiresPermissions("corpdepartstatinfo:info")
	public R info(@PathVariable("departName") String departName){
		CorpDepartStatInfoEntity corpDepartStatInfo = corpDepartStatInfoService.queryObject(departName);
		
		return R.ok().put("corpDepartStatInfo", corpDepartStatInfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("corpdepartstatinfo:save")
	public R save(@RequestBody CorpDepartStatInfoEntity corpDepartStatInfo){
		corpDepartStatInfoService.save(corpDepartStatInfo);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("corpdepartstatinfo:update")
	public R update(@RequestBody CorpDepartStatInfoEntity corpDepartStatInfo){
		corpDepartStatInfoService.update(corpDepartStatInfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("corpdepartstatinfo:delete")
	public R delete(@RequestBody String[] departNames){
		corpDepartStatInfoService.deleteBatch(departNames);
		
		return R.ok();
	}
	
}
