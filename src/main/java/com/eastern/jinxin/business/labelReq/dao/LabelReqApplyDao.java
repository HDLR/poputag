package com.eastern.jinxin.business.labelReq.dao;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.eastern.jinxin.business.labelReq.entity.LabelReqApplyEntity;


/**
 * 
 * 
 * @author service
 * @email service@gmail.com
 * @date 2019-02-28 10:58:16
 */
public interface LabelReqApplyDao  extends BaseMapper<LabelReqApplyEntity> {
	List<LabelReqApplyEntity> queryList(Pagination page, Map<String, Object> map);
	int queryTotal(Map<String, Object> map);
}
