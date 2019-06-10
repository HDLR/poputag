package com.eastern.jinxin.sys.operation.oss.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.eastern.jinxin.sys.operation.oss.entity.SysOssEntity;

/**
 * 文件上传
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2017-03-25 12:13:26
 */
public interface SysOssDao extends  BaseMapper<SysOssEntity> {

	List<SysOssEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
}
