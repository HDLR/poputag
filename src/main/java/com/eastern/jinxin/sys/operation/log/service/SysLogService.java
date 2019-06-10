package com.eastern.jinxin.sys.operation.log.service;

import com.baomidou.mybatisplus.service.IService;

import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.sys.operation.log.entity.SysLogEntity;

/**
 * 系统日志
 * 
 * @author zdl_gis
 * @email service@gmail.com
 * @date 2017-03-08 10:40:56
 */
public interface SysLogService extends IService<SysLogEntity> {

	SysLogEntity queryObject(Long id);

	void queryList(PageInfo pageInfo);

	void save(SysLogEntity sysLog);

	void update(SysLogEntity sysLog);

	void delete(Long id);

	void deleteBatch(Long[] ids);
}
