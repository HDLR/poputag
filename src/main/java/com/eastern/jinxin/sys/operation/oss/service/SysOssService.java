package com.eastern.jinxin.sys.operation.oss.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.operation.oss.entity.SysOssEntity;

/**
 * 文件上传
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2017-03-25 12:13:26
 */
public interface SysOssService  extends IService<SysOssEntity>{
	
	SysOssEntity queryObject(Long id);
	
	void queryList(PageInfo pageInfo);
	
	void save(SysOssEntity sysOss);
	
	void update(SysOssEntity sysOss);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
