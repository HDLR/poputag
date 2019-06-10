package com.eastern.jinxin.business.label.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.label.entity.ManagerEntity;
import com.eastern.jinxin.business.label.entity.TagInfoEntity;
import com.eastern.jinxin.business.label.service.ManagerService;
import com.eastern.jinxin.sys.common.common.utils.Constant;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.common.excel.ImportAndUpload;
import com.eastern.jinxin.sys.common.excel.analysisExcel.AnalysisExcTagMana2007;
import com.eastern.jinxin.sys.common.excel.enums.colnums.CodeUtils;
import com.eastern.jinxin.sys.common.excel.model.AnalysisModel;
import com.eastern.jinxin.redis.service.CacheService;
import com.eastern.jinxin.redis.utils.RedisUtils;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-18 09:00:47
 */
@RestController
@RequestMapping("label/manager")
public class ManagerController extends AbstractController {
	@Autowired
	private ManagerService managerService;
	@Autowired
	private CacheService cacheService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("manager:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{});
		managerService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{tagCtgyId}")
	@RequiresPermissions("manager:info")
	public R info(@PathVariable("tagCtgyId") String tagCtgyId){
		ManagerEntity manager = managerService.queryObject(tagCtgyId);
		
		return R.ok().put("manager", manager);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("manager:save")
	public R save(@RequestBody ManagerEntity manager){
		managerService.save(manager);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("manager:update")
	public R update(@RequestBody ManagerEntity manager){
		managerService.update(manager);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("manager:delete")
	public R delete(@RequestBody String[] tagCtgyIds){
		managerService.deleteBatch(tagCtgyIds);
		return R.ok();
	}
	
	/**
	 * 下载模板
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws Exception
	 */
	//标签工厂
	@SysLogAnn("标签工厂-下载模板")
	@RequestMapping("/downloadTemp/{tempCode}")
	@RequiresPermissions("manager:download")
	public void downloadTemp(@PathVariable("tempCode")String tempCode,HttpServletRequest request, HttpServletResponse response)
			throws IOException, Exception {
		ImportAndUpload.downloadFile(request, response, tempCode);
	}

	/**
	 * 导出数据到excel
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws Exception
	 */
	//标签工厂
	@RequestMapping("/downloadExcel/{params}")
	@RequiresPermissions("manager:outputExcel")
	public void downloadExcel(HttpServletRequest request, HttpServletResponse response,@PathVariable("params")String params)
			throws IOException, Exception {
		Map<String,String > maps = (Map<String, String>)JSON.parse(params);  
		String tempCode = maps.get("tempCode");
		String tagName = maps.get("tagNameUpload");
		String tagStatus = maps.get("tagStatusUpload");
		String doType = maps.get("doType");
		String tagId = maps.get("tagIdUpload");
		String tagLevel = maps.get("tagLevelUpload");
		String queryOrSelect = maps.get("queryOrSelect");
		Map<String, String> pageParam = new HashMap<String, String>();
		pageParam.put("tagName", tagName);
		pageParam.put("tagStatus", tagStatus);
		pageParam.put("doType", doType);
		pageParam.put("tagId", tagId);
		pageParam.put("queryOrSelect", queryOrSelect);
		pageParam.put("tagLevel", tagLevel);
		List<Map<String, Object>> list = null;
		if (doType.equals("all")) {// 导出所有
			list = this.managerService.queryAllTagExcel();
		} else {// 导出查询列表
			list = this.managerService.queryTagListExcel(pageParam);
		}
		ImportAndUpload.importExcel(request, response, tempCode, "标签管理", list, CodeUtils.getTagColumnsImport());
	}

	/**
	 * 上传
	 * 
	 * @param downType
	 * @return
	 * @throws Exception 
	 */
	//标签工厂
	@RequestMapping("/upload")
	@RequiresPermissions("manager:inputExcel")
	public R upload(@RequestParam("file") MultipartFile file) throws Exception {
		AnalysisExcTagMana2007 exc = new AnalysisExcTagMana2007();
		AnalysisModel anal = new AnalysisModel(2, CodeUtils.getTagColumnsUpload());
		//List<Map<String, String>> list = exc.initExce(request, anal);
		List<Map<String, String>> list=exc.analysisExcel(file.getInputStream(),anal);
		Long userId=getUserId();
		// 将excel数据插入到数据库中
		Map<String,List<String>> result = managerService.insertExcelData(list,userId);
		return R.ok().put("msg", result);
	}
	
	/**
	 * 
	 * <p>Title : queryListById</p>
	 * <p>Description : 点击树节点进行数据的展示</p>	
	 * @param params
	 * @return
	 */
	//标签工厂
	@SysLogAnn("标签规则-点击树节点进行查询")
	@RequestMapping("/queryListById")
	public R queryListById(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"tagCtgyId"});
		managerService.queryListById(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	/**
	 * 
	 * <p>Title : queryTagCtyList</p>
	 * <p>Description : 点击按钮进行查询列表</p>	
	 * @param params
	 * @return
	 */
	//标签工厂
	@SysLogAnn("标签规则-点击按钮进行查询")
	@RequestMapping("/queryTagCtyList")
	public R queryTagCtyList(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"tagName","tagStatus","tagLevel","startTime","endTime"});
		managerService.queryTagCtyList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	/**
	 *h50_tag_info 和h50_tag_category_info union的信息
	 */
	@RequestMapping("/getInfo/{tagId}")
	public R getTagInfoById(@PathVariable("tagId") String tagId){
		TagInfoEntity tagInfoEntity = managerService.getTagInfoById(tagId);
		Long numbers=cacheService.queryCountByKey( RedisUtils.KEY+tagId);
		tagInfoEntity.setPersonNumbers(numbers+"");
		return R.ok().put("TagInfoEntity", tagInfoEntity);
	}
	
	@RequestMapping("/listLevelAll")
	@ResponseBody
	public Map<String, Object> listLevelAll() {
		return managerService.listLevelAll();
	}
}
