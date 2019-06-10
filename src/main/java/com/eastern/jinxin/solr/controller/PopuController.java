package com.eastern.jinxin.solr.controller;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eastern.jinxin.redis.service.CacheService;
import com.eastern.jinxin.redis.utils.RedisUtils;
import com.eastern.jinxin.solr.model.PopuInfo;
import com.eastern.jinxin.solr.model.PopuPoints;
import com.eastern.jinxin.solr.service.impl.PopuSolrDataServiceImpl;
import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
 

@Controller
public class PopuController {
	@Autowired
	public PopuSolrDataServiceImpl popuSolrDataServiceImpl;
	@Autowired
	private CacheService cacheService;
	
	@SysLogAnn("查询人口信息")
	//查询
	@RequestMapping("/PopuByQueryValue")
	public @ResponseBody R update(@RequestParam Map<String, Object> params) throws Exception{
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"queryValue", "type"});
		popuSolrDataServiceImpl.querySolrDocuments(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	@SysLogAnn("查询人口坐标及信息")
	@RequestMapping("/popu/ByPoint")
	public @ResponseBody R addressBy(@RequestParam Map<String, Object> params) throws Exception {
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"queryValue", "type","name"});
	 	//取出多变的坐标并封装
		popuSolrDataServiceImpl.querySolrDocumentsData(pageInfo);
		return R.ok().put("page", pageInfo);
 
	}
	@RequestMapping("/PopuById")
	public @ResponseBody R querySolrDocumentById(String name, String value) throws Exception{
		PopuInfo popuInfo = popuSolrDataServiceImpl.querySolrDocumentById(name, value);
		return R.ok().put("PopuInfo", popuInfo);
	}
	
	//测试
	@RequestMapping(value="/popuSolr")
	public @ResponseBody List<PopuPoints> popuSolrSearch(@RequestParam("popuValue") String popuValue){
		List<PopuPoints> points=popuSolrDataServiceImpl.popuList();
		return points;
	}
}
