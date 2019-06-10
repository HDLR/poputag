package com.eastern.jinxin.business.label.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eastern.jinxin.sys.aop.annotation.SysLogAnn;
import com.eastern.jinxin.business.label.entity.H50TagCategoryInfoEntity;
import com.eastern.jinxin.business.label.service.H50TagCategoryInfoService;
import com.eastern.jinxin.sys.common.common.utils.Constant;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.common.common.utils.R;
import com.eastern.jinxin.sys.operation.base.controller.AbstractController;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2018-05-09 11:16:19
 */
@RestController
@RequestMapping("label/h50tagcategoryinfo")
public class H50TagCategoryInfoController extends AbstractController {
	@Autowired
	private H50TagCategoryInfoService h50TagCategoryInfoService;
	
	/**
	 * 列表
	 */
	//标签工厂-标签市场
	@SysLogAnn("标签市场-列表查询")
	@RequestMapping("/list")
	@RequiresPermissions("h50tagcategoryinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		PageInfo pageInfo = new PageInfo(params,new String[]{});
		h50TagCategoryInfoService.queryList(pageInfo);
		return R.ok().put("page", pageInfo);
	}
	
	/**
	 * 获得tagCategory树形信息，（标签-标签类别）进行treeGrid的填充的
	 */
	@RequestMapping("/getTagCategoryTree")
	public List<H50TagCategoryInfoEntity> getTagCategoryTree(@RequestParam(value="time",defaultValue="0") String time){
		Map<String, Object> params=new HashMap<String,Object>();
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		if(!time.equals("0")){
			String[] selectTime=time.split("~");
			params.put("creatdateStart", selectTime[0].trim());
			params.put("creatdateEnd", selectTime[1].trim());
		}
		// 查询列表数据
		List<H50TagCategoryInfoEntity> list=h50TagCategoryInfoService.getTagCategoryTree(params);
		return list;
	}
	
	/**
	 * 选择菜单树形(添加、修改菜单)
	 */
	@RequestMapping("/select/{haveTagInd}")
	public R select(@PathVariable("haveTagInd") String haveTagInd){
		//查询列表数据
		List<H50TagCategoryInfoEntity> menuList=h50TagCategoryInfoService.getTag(haveTagInd);
		//添加顶级菜单
		H50TagCategoryInfoEntity root = new H50TagCategoryInfoEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-2L);
		root.setTagCtgyLevel("0");//设置为顶层
		menuList.add(root);
		
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 获得标签树（标签规则用于左边）
	 */
	@RequestMapping("/getTagTree/{haveTagInd}")
	public R getTagTree(@PathVariable("haveTagInd") String haveTagInd){
		//查询列表数据
		List<H50TagCategoryInfoEntity> menuList=h50TagCategoryInfoService.getTag(haveTagInd);
		
		//添加顶级菜单
//		H50TagCategoryInfoEntity root = new H50TagCategoryInfoEntity();
//		root.setMenuId(0L);
//		root.setName("标签树");
//		root.setParentId(-2L);
//		root.setTagCtgyLevel("0");//设置为顶层
//		menuList.add(root);
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 根据ID查询标签的信息
	 */
	//标签工厂-标签创建
	@SysLogAnn("标签创建-查询标签的信息")
	@RequestMapping("/info/{tagCtgyId}")
	@RequiresPermissions("h50tagcategoryinfo:info")
	public R info(@PathVariable("tagCtgyId") String tagCtgyId){
		H50TagCategoryInfoEntity h50TagCategoryInfo = h50TagCategoryInfoService.queryObject(tagCtgyId);
		return R.ok().put("h50TagCategoryInfo", h50TagCategoryInfo);
	}
	
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("h50tagcategoryinfo:save")
	public R save(@RequestBody H50TagCategoryInfoEntity h50TagCategoryInfo){
		h50TagCategoryInfo.setCreateUser( getUserId());
		h50TagCategoryInfoService.save(h50TagCategoryInfo);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("h50tagcategoryinfo:update")
	public R update(@RequestBody H50TagCategoryInfoEntity h50TagCategoryInfo){
		h50TagCategoryInfoService.update(h50TagCategoryInfo);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("h50tagcategoryinfo:delete")
	public R delete(@RequestBody String[] tagCtgyIds){
		h50TagCategoryInfoService.deleteBatch(tagCtgyIds);
		return R.ok();
	}
	
	//标签工厂-标签创建
	@SysLogAnn("标签创建-标签删除")
	@RequestMapping("/deleteTag/{tagCtgyId}")
	@RequiresPermissions("h50tagcategoryinfo:delete")
	public Map<String, Object> del(@PathVariable("tagCtgyId")String tagCtgyId) {
		int isok = this.h50TagCategoryInfoService.del(tagCtgyId);
		String msg = "";
		if (isok == 0) {
			msg = "标签类别已被引用，不可删除";
		} else if (isok == 1) {
			msg = "删除成功";
		} else if (isok == -1) {
			msg = "该标签为父标签，不可删除";
		}
		return R.ok().put("msg",msg);
	}
	
	/**
	 * 标签上线,根据ID修改状态
	 */
	//标签工厂-标签创建
	@SysLogAnn("标签创建-标签上线")
	@RequestMapping("/upline/{tagCtgyId}")
	@RequiresPermissions("h50tagcategoryinfo:up")
	public R upline(@PathVariable("tagCtgyId") String tagCtgyId,H50TagCategoryInfoEntity h50TagCategoryInfo){
		h50TagCategoryInfo.setTagCtgyId(tagCtgyId);
		h50TagCategoryInfo.setUpdateUser(getUserId());
		h50TagCategoryInfoService.upLine(h50TagCategoryInfo);
		return R.ok();
	}
	
	//标签工厂-标签创建
	@SysLogAnn("标签创建-标签下线")
	@RequestMapping("/downTag/{tagId}")
	@RequiresPermissions("h50tagcategoryinfo:down")
	public Map<String, Object> downLine(@PathVariable("tagId")String tagId) {
		int isok = this.h50TagCategoryInfoService.downLine(tagId);
		String msg = "";
		if (isok == 0) {
			msg = "标签类别已被引用，不可下线";
		} else if (isok == 1) {
			msg = "标签类别下线成功";
		} else if (isok == -1) {
			msg = "该标签为父标签，不可下线";
		}
		return R.ok().put("msg",msg);
	}
	
	
	/**
	 * 查询一级 二级标签类别
	 */
	//标签工厂-标签市场
	@SysLogAnn("标签市场-查询所有标签类")
	@RequestMapping("/getCtgyList")
	public R getCtgyList(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		List<H50TagCategoryInfoEntity> list=h50TagCategoryInfoService.getCtgyList();
		return R.ok().put("page", list);
	}
	
	/**
	 * 查询一级 二级标签类别
	 */
	//标签工厂-标签市场
	@SysLogAnn("标签市场-查询所有标签类")
	@RequestMapping("/findAllRecursion")
	public R getCtgyListByRecursion(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		// 查询列表数据
		List<H50TagCategoryInfoEntity> list=h50TagCategoryInfoService.findAllRecursion();
		return R.ok().put("tagList", list);
	}
}
