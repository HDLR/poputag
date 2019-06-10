package com.eastern.jinxin.business.userGroup.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;
import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.userGroup.service.MicroscopicPictureService;
import com.eastern.jinxin.sys.common.excel.ImportAndUpload;

@Controller
@RequestMapping("userGroup/microscopic")
public class MicroDownLoaderController extends AbstractController {

	@Autowired
	private MicroscopicPictureService microscopicPictureService;
	
	//导出所有人员的明细数据
//	@SysLogAnn("群微观画像明细下载")
	@RequestMapping("/downLoadExcel")
	public void queryPersonDetail(HttpServletRequest request, HttpServletResponse response, String campId) throws Exception{
		
		Map<String, Object> reMap = new HashMap<String, Object>();
		List<List<Map<String, Object>>> uList = microscopicPictureService.downLoadExcelMsg(campId);
		reMap.put("uList", uList);
		
		ImportAndUpload.doDownLoaderExcel(request, response, "campTemp", "14用户群组包含的所有人员明细信息", reMap);
	}
}
