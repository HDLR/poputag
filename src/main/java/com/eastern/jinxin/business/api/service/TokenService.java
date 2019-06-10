package com.eastern.jinxin.business.api.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.eastern.jinxin.business.api.entity.TbTokenEntity;


/**
 * 用户Token
 * 
 * @author looyoo
 * @email service@gmail.com
 * @date 2017-03-23 15:22:07
 */
public interface TokenService  extends IService<TbTokenEntity>{

	TbTokenEntity queryByUserId(Long userId);

	TbTokenEntity queryByToken(String token);
	
	void save(TbTokenEntity token);
	
	void update(TbTokenEntity token);

	/**
	 * 生成token
	 * @param userId  用户ID
	 * @return        返回token相关信息
	 */
	Map<String, Object> createToken(long userId);

}
