package com.eastern.jinxin.sys.operation.oss.entity;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;


/**
 * <p>
 * 文件上传
 * </p>
 *
 * @author haiping.tang
 * @since 2018-03-26
 */
@TableName("sys_oss")
public class SysOssEntity extends Model<SysOssEntity> {
	@TableField(exist = false)
    private static final long serialVersionUID = 1L;

	@TableId(type = IdType.AUTO)
	private Long id;
    /**
     * URL地址
     */
	private String url;
    /**
     * 创建时间
     */
	@TableField("create_date")
	private Date createDate;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
