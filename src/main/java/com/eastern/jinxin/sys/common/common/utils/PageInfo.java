package com.eastern.jinxin.sys.common.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @description：分页实体类 (结合jqery easyui)
 * @author：Wangzhixuan
 * @date：2015年4月23日 上午1:41:46
 */
@SuppressWarnings("rawtypes")
public class PageInfo {

	private final static int PAGESIZE = 10; // 默认显示的记录数

	private int total; // 总记录
	private List rows; // 显示的记录
	private int totalPage; // 总页数
	@JsonIgnore
	private int from;
	@JsonIgnore
	private int size;
	@JsonIgnore
	private int nowpage; // 当前页
	@JsonIgnore
	private int pagesize; // 每页显示的记录数
	@JsonIgnore
	private Map<String, Object> condition; // 查询条件

	@JsonIgnore
	private String sort = "seq";// 排序字段
	@JsonIgnore
	private String order = "asc";// asc，desc mybatis Order 关键字

	public PageInfo() {
	}

	// 构造方法
	public PageInfo(Map<String, Object> params, String[] queryParam) {
		// 分页参数
		this.nowpage = Integer.parseInt(params.get("page").toString());
		this.pagesize = Integer.parseInt(params.get("limit").toString());
		// 计算当前页
		if (this.nowpage < 0) {
			this.nowpage = 1;
		}
		// 记录每页显示的记录数
		if (this.pagesize < 0) {
			this.pagesize = PAGESIZE;
		}
		// 计算开始的记录和结束的记录
		this.from = (this.nowpage - 1) * this.pagesize;
		this.size = this.pagesize;
		condition=new HashMap<String,Object>();
		for (String p : queryParam) {
			if(params.get(p) instanceof String){
				condition.put(p, ((String)params.get(p)).trim());
			}else{
				condition.put(p, params.get(p));
			}
		}
		if(params.get("createUserId")!=null){
			condition.put("createUserId", params.get("createUserId"));
		}

	}

	// 构造方法
	public PageInfo(int nowpage, int pagesize) {
		// 计算当前页
		if (nowpage < 0) {
			this.nowpage = 1;
		} else {
			// 当前页
			this.nowpage = nowpage;
		}
		// 记录每页显示的记录数
		if (pagesize < 0) {
			this.pagesize = PAGESIZE;
		} else {
			this.pagesize = pagesize;
		}
		// 计算开始的记录和结束的记录
		this.from = (this.nowpage - 1) * this.pagesize;
		this.size = this.pagesize;
	}

	// 构造方法
	public PageInfo(int nowpage, int pagesize, String sort, String order) {
		// 计算当前页
		if (nowpage < 0) {
			this.nowpage = 1;
		} else {
			// 当前页
			this.nowpage = nowpage;
		}
		// 记录每页显示的记录数
		if (pagesize < 0) {
			this.pagesize = PAGESIZE;
		} else {
			this.pagesize = pagesize;
		}
		// 计算开始的记录和结束的记录
		this.from = (this.nowpage - 1) * this.pagesize;
		this.size = this.pagesize;
		// 排序字段，正序还是反序
		this.sort = sort;
		this.order = order;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNowpage() {
		return nowpage;
	}

	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getTotalPage() {
		if (total > 0) {
			this.totalPage = total % size > 0 ? total / size + 1 : total / size;
		} else {
			this.totalPage = 1;
		}
		return this.totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
