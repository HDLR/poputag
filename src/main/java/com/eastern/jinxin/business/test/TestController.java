package com.eastern.jinxin.business.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.hbase.client.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import com.eastern.jinxin.hbase.entity.TUserInfo;
import com.eastern.jinxin.hbase.service.impl.HBaseConnectionMgr;
import com.eastern.jinxin.hbase.service.impl.HbaseServiceImpl;

@RestController
public class TestController {
	
	@Autowired
	private HBaseConnectionMgr connectionMgr;
	@Autowired
	private HbaseServiceImpl hbaseService;

	@RequestMapping("/test")
	public String test() throws IOException {
//		Table userTable = this.connectionMgr.getConnection().getTable(TUserInfo.tableName());
//		for (Integer user=1; user<=30; user++) {
//			TUserInfo userInfo = TUserInfo.findById(userTable, user.toString());
//			String results = JSON.toJSONString(userInfo);
//			System.out.println(results);
//		}
		
		for (Integer user=1; user<=30; user++) {
			Map<String,Object> tags = hbaseService.getUserDetail(user.toString());
//			System.out.println(tags);
		}
		
		return "success";
	}
}
