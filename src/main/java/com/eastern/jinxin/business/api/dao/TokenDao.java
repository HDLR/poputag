package com.eastern.jinxin.business.api.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.eastern.jinxin.business.api.entity.TbTokenEntity;


/**
 * 用户Token
 * 
 * @author looyoo
 * @email service@gmail.com
 * @date 2017-03-23 15:22:07
 */
public interface TokenDao  extends BaseMapper<TbTokenEntity> {
    
    TbTokenEntity queryByUserId(Long userId);

    TbTokenEntity queryByToken(String token);
	
}
