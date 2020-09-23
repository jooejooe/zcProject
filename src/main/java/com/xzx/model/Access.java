package com.xzx.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 相关附件
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public class Access extends Model<Access> {

    private static final long serialVersionUID = 1L;

    /**
     * 附件ID
     */
    @TableId(value = "AccessId", type = IdType.AUTO)
    @ApiModelProperty("附件ID")
    private Integer AccessId;

    /**
     * 附件内容
     */
    @ApiModelProperty("附件内容")
    private String AccessInfo;

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Integer UserId;

    /**
     * 在线服务类别
     */
    @ApiModelProperty("在线服务类别")
    private Integer ModelType;

    /**
     * 对应在线服务类别中的某一类别的某一条ID
     */
    @ApiModelProperty("对应在线服务类别中的某一类别的某一条ID")
    private Integer ModelId;
    
    /**
     * 上传附件的标题名称
     */
    @ApiModelProperty("上传附件的标题名称")
    private String AccessName;
    
    /**
     * 0：首次申办添加；1：补充材料追加
     */
    @ApiModelProperty("附件类型")
    private Integer AccessType;

    @ApiModelProperty("中间表信息id")
    private Integer authreginfoId;

    public Integer getAuthreginfoId() {
        return authreginfoId;
    }

    public void setAuthreginfoId(Integer authreginfoId) {
        this.authreginfoId = authreginfoId;
    }

    @TableField(exist=false)
    private String tjyAccessName;

	public String getTjyAccessName() {
		return tjyAccessName;
	}

	public void setTjyAccessName(String tjyAccessName) {
		this.tjyAccessName = tjyAccessName;
	}

	public Integer getAccessId() {
        return AccessId;
    }

    public void setAccessId(Integer AccessId) {
        this.AccessId = AccessId;
    }
    public String getAccessInfo() {
        return AccessInfo;
    }

    public void setAccessInfo(String AccessInfo) {
        this.AccessInfo = AccessInfo;
    }
    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }
    public Integer getModelType() {
        return ModelType;
    }

    public void setModelType(Integer ModelType) {
        this.ModelType = ModelType;
    }
    public Integer getModelId() {
        return ModelId;
    }

    public void setModelId(Integer ModelId) {
        this.ModelId = ModelId;
    }
    
    public String getAccessName() {
		return AccessName;
	}

	public void setAccessName(String accessName) {
		AccessName = accessName;
	}
	
	public Integer getAccessType() {
		return AccessType;
	}

	public void setAccessType(Integer accessType) {
		AccessType = accessType;
	}

    @Override
    protected Serializable pkVal() {
        return this.AccessId;
    }

    @Override
    public String toString() {
        return "Access{" +
        "AccessId=" + AccessId +
        ", AccessInfo=" + AccessInfo +
        ", UserId=" + UserId +
        ", ModelType=" + ModelType +
        ", ModelId=" + ModelId +
        ", AccessType=" + AccessType +
        ", authreginfoId=" + authreginfoId +
        "}";
    }
}
