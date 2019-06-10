package com.eastern.jinxin.business.api.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.eastern.jinxin.business.api.dao.TokenDao;
import com.eastern.jinxin.business.api.entity.TbTokenEntity;
import com.eastern.jinxin.business.api.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;


@Service("tokenService")
public class TokenServiceImpl   extends ServiceImpl<TokenDao, TbTokenEntity> implements TokenService {
	@Autowired
	private TokenDao tokenDao;
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;

	@Override
	public TbTokenEntity queryByUserId(Long userId) {
		return tokenDao.queryByUserId(userId);
	}

	@Override
	public TbTokenEntity queryByToken(String token) {
		return tokenDao.queryByToken(token);
	}

	@Override
	public void save(TbTokenEntity token){
		tokenDao.insert(token);
	}
	
	@Override
	public void update(TbTokenEntity token){
		tokenDao.updateById(token);
	}

	@Override
	public Map<String, Object> createToken(long userId) {
		//生成一个token
		String token = UUID.randomUUID().toString();
		//当前时间
		Date now = new Date();

		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		TbTokenEntity TbToken = queryByUserId(userId);
		if(TbToken == null){
			TbToken = new TbTokenEntity();
			TbToken.setUserId(userId);
			TbToken.setToken(token);
			TbToken.setUpdateTime(now);
			TbToken.setExpireTime(expireTime);

			//保存token
			save(TbToken);
		}else{
			TbToken.setToken(token);
			TbToken.setUpdateTime(now);
			TbToken.setExpireTime(expireTime);

			//更新token
			update(TbToken);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		map.put("expire", EXPIRE);

		return map;
	}
}
