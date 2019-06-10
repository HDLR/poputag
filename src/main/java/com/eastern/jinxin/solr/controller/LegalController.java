package com.eastern.jinxin.solr.controller;

import java.util.Map;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastern.jinxin.solr.model.LegalInfo;
 
import com.eastern.jinxin.solr.service.LegalSolrDataService;
import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
@Controller
public class LegalController {
	@Autowired
	private LegalSolrDataService dataSolrService;
	@SysLogAnn("查询法人信息")
	@RequestMapping("/LegalByQueryValue")
//	@RequiresPermissions("solr:query")
	public @ResponseBody R update(@RequestParam Map<String, Object> params) throws Exception{
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"queryValue", "type"});
		dataSolrService.querySolrDocuments(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	@SysLogAnn("查询法人坐标及信息")
	@RequestMapping("/legal/ByPoint")
	public @ResponseBody R addressBy(@RequestParam Map<String, Object> params) throws Exception {
		//查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"queryValue", "type","name"});
	 	//取出多变的坐标并封装
		dataSolrService.querySolrDocumentsData(pageInfo);
		return R.ok().put("page", pageInfo);
 
	}
	
	@RequestMapping("/LegalById")
	public @ResponseBody R querySolrDocumentById(String name, String value) throws Exception{
		LegalInfo legalInfo = dataSolrService.querySolrDocumentById(name, value);
		return R.ok().put("legalInfo", legalInfo);
	}
	
	
/*	@RequestMapping("/httpRequestClient")
	public R httpRequestClient(String cardId) throws Exception {
		return dataSolrService.httpRequestClient(cardId);
	}*/
}
