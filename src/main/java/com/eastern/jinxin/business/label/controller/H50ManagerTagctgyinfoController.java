package com.eastern.jinxin.business.label.controller;


import java.util.Date;
import java.util.Map;

import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.eastern.jinxin.business.label.entity.H50ManagerTagctgyinfoEntity;
import com.eastern.jinxin.business.label.service.H50ManagerTagctgyinfoService;
import com.eastern.jinxin.sys.common.common.utils.Constant;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-28 17:59:34
 */
@RestController
@RequestMapping("label/h50managertagctgyinfo")
public class H50ManagerTagctgyinfoController extends AbstractController {
	@Autowired
	private H50ManagerTagctgyinfoService h50ManagerTagctgyinfoService;
	
	/**
	 * 列表
	 */
	//标签工厂-输出管理
	@SysLogAnn("输出管理-列表查询")
	@RequestMapping("/list")
	@RequiresPermissions("h50managertagctgyinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{});
		h50ManagerTagctgyinfoService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 信息
	 */
	//标签工厂-输出管理
	@SysLogAnn("输出管理-查询")
	@RequestMapping("/info/{id}")
	@RequiresPermissions("h50managertagctgyinfo:info")
	public R info(@PathVariable("id") Integer id){
		H50ManagerTagctgyinfoEntity h50ManagerTagctgyinfo = h50ManagerTagctgyinfoService.queryObject(id);
		
		return R.ok().put("h50ManagerTagctgyinfo", h50ManagerTagctgyinfo);
	}
	
	/**
	 * 保存
	 */
	//标签工厂-输出管理
	@SysLogAnn("输出管理-新增")
	@RequestMapping("/save")
	@RequiresPermissions("h50managertagctgyinfo:save")
	public R save(@RequestBody H50ManagerTagctgyinfoEntity h50ManagerTagctgyinfo){
		h50ManagerTagctgyinfo.setCreatedTs(new Date());
		h50ManagerTagctgyinfo.setUpdatedTs(new Date());
		h50ManagerTagctgyinfo.setCreateUser(getUserId().intValue());
		h50ManagerTagctgyinfoService.save(h50ManagerTagctgyinfo);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	//标签工厂-输出管理
	@SysLogAnn("输出管理-修改")
	@RequestMapping("/update")
	@RequiresPermissions("h50managertagctgyinfo:update")
	public R update(@RequestBody H50ManagerTagctgyinfoEntity h50ManagerTagctgyinfo){
		h50ManagerTagctgyinfo.setUpdatedTs(new Date());
		h50ManagerTagctgyinfo.setUpdateUser(getUserId().intValue());
		h50ManagerTagctgyinfoService.update(h50ManagerTagctgyinfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	//标签工厂-输出管理
	@SysLogAnn("输出管理-删除")
	@RequestMapping("/delete")
	@RequiresPermissions("h50managertagctgyinfo:delete")
	public R delete(@RequestBody Integer[] ids){
		h50ManagerTagctgyinfoService.deleteBatch(ids);
		return R.ok();
	}
	
}
