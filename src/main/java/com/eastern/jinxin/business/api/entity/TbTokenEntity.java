package com.eastern.jinxin.business.api.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;


/**
 * <p>
 * 用户Token
 * </p>
 *
 * @author haiping.tang
 * @since 2018-03-26
 */
@TableName("tb_token")
public class TbTokenEntity extends Model<TbTokenEntity> {
	@TableField(exist = false)
    private static final long serialVersionUID = 1L;

	@TableId("user_id")
	private Long userId;
    /**
     * token
     */
	private String token;
    /**
     * 过期时间
     */
	@TableField("expire_time")
	private Date expireTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

}
