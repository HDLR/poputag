package com.eastern.jinxin.sys.operation.homepage.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastern.jinxin.sys.common.common.utils.Constant;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;
import com.eastern.jinxin.sys.operation.homepage.entity.SysHomepageManagerEntity;
import com.eastern.jinxin.sys.operation.homepage.service.SysHomepageManagerService;
import com.eastern.jinxin.sys.operation.menu.entity.SysMenuEntity;
import com.eastern.jinxin.sys.operation.menu.service.SysMenuService;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-08-13 17:14:45
 */
@RestController
@RequestMapping("/sys/syshomepagemanager")
public class SysHomepageManagerController extends AbstractController {
	@Autowired
	private SysHomepageManagerService sysHomepageManagerService;
	@Autowired
	private SysMenuService sysMenuService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("syshomepagemanager:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{"menuName"});
		sysHomepageManagerService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("syshomepagemanager:info")
	public R info(@PathVariable("id") Integer id){
		SysHomepageManagerEntity sysHomepageManager = sysHomepageManagerService.queryObject(id);
		
		return R.ok().put("sysHomepageManager", sysHomepageManager);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("syshomepagemanager:save")
	public R save(@RequestBody SysHomepageManagerEntity sysHomepageManager){
		sysHomepageManager.setCreateDate(new Date());
		sysHomepageManagerService.save(sysHomepageManager);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("syshomepagemanager:update")
	public R update(@RequestBody SysHomepageManagerEntity sysHomepageManager){
		sysHomepageManager.setUpdateDate(new Date());
		sysHomepageManagerService.update(sysHomepageManager);
		return R.ok();
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@RequestMapping("/select")
	public R select(){
		//查询列表数据
		List<SysMenuEntity> menuList = sysMenuService.queryNotButtonList();
		
		//添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setMenuId(0L);
		root.setName("选择首页菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("syshomepagemanager:delete")
	public R delete(@RequestBody Integer[] ids){
		sysHomepageManagerService.deleteBatch(ids);
		
		return R.ok();
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/getHomepageByUserRole")
	public R getHomepageByUserRole(){
		Long userId=getUser().getUserId();
		String  homepageUrl = sysHomepageManagerService.getHomepageByUserRole(userId);
		return R.ok().put("homepageUrl", homepageUrl);
	}
	
}
