package com.eastern.jinxin.business.api.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.eastern.jinxin.business.api.entity.TbUserEntity;


/**
 * 用户
 * 
 * @author looyoo
 * @email service@gmail.com
 * @date 2017-03-23 15:22:06
 */
public interface UserDao extends BaseMapper<TbUserEntity> {

    TbUserEntity queryByMobile(String mobile);

	List<TbUserEntity> queryList(Pagination page, Map<String, Object> map);
}
