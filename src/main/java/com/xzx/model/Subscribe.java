package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 预约
 * </p>
 *
 * @author Helen
 * @since 2019-09-26
 */
public class Subscribe extends Model<Subscribe> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "subscribeId", type = IdType.AUTO)
    @ApiModelProperty("预约主键ID")
    private Integer subscribeId;

    /**
     * 区域ID 关联区域表主键 
     */
    @ApiModelProperty("区域ID")
    private Integer RegionId;

    /**
     * 机构ID 关联机构表主键
     */
    @ApiModelProperty("机构ID")
    private Integer DepartMentId;

    /**
     * 预约工作人员ID 关联工作人员表主键
     */
    @ApiModelProperty("预约工作人员ID")
    private Integer fairworkerId;

    /**
     * 预约类型  读取数据字典表11
     */
    @ApiModelProperty("预约类型")
    private Integer subscribeType;

    /**
     * 申请人ID 关联注册表主键
     */
    @ApiModelProperty("申请人ID")
    private Integer UserId;

    /**
     * 预约时间
     */
    @ApiModelProperty("预约时间")
    private String subscribe;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String CreateDate;

    /**
     * 预约的具体业务 0 公证 1 人民仲裁 2司法鉴定 3 法律援助 4 律师预约
     */
    @ApiModelProperty("预约的具体业务 0 公证 1 人民仲裁 2司法鉴定 3 法律援助 4 律师预约")
    private Integer ModelType;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String Context;
    
    /**
     * 申请人类型
     */
    @ApiModelProperty("申请人类型")
    private int applyType;
    
    /**
     * 鉴定类型
     */
    @ApiModelProperty("鉴定类型")
    private int authenticateType;

	public Integer getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(Integer subscribeId) {
        this.subscribeId = subscribeId;
    }
    public Integer getRegionId() {
        return RegionId;
    }

    public void setRegionId(Integer RegionId) {
        this.RegionId = RegionId;
    }
    public Integer getDepartMentId() {
        return DepartMentId;
    }

    public void setDepartMentId(Integer DepartMentId) {
        this.DepartMentId = DepartMentId;
    }
    public Integer getFairworkerId() {
        return fairworkerId;
    }

    public void setFairworkerId(Integer fairworkerId) {
        this.fairworkerId = fairworkerId;
    }
    public Integer getSubscribeType() {
        return subscribeType;
    }

    public void setSubscribeType(Integer subscribeType) {
        this.subscribeType = subscribeType;
    }
    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }
    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }
    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }
    public Integer getModelType() {
        return ModelType;
    }

    public void setModelType(Integer ModelType) {
        this.ModelType = ModelType;
    }
    public String getContext() {
        return Context;
    }

    public void setContext(String Context) {
        this.Context = Context;
    }
    
    public int getApplyType() {
		return applyType;
	}

	public void setApplyType(int applyType) {
		this.applyType = applyType;
	}

	public int getAuthenticateType() {
		return authenticateType;
	}

	public void setAuthenticateType(int authenticateType) {
		this.authenticateType = authenticateType;
	}

    @Override
    protected Serializable pkVal() {
        return this.subscribeId;
    }

    @Override
    public String toString() {
        return "Subscribe{" +
        "subscribeId=" + subscribeId +
        ", RegionId=" + RegionId +
        ", DepartMentId=" + DepartMentId +
        ", fairworkerId=" + fairworkerId +
        ", subscribeType=" + subscribeType +
        ", UserId=" + UserId +
        ", subscribe=" + subscribe +
        ", CreateDate=" + CreateDate +
        ", ModelType=" + ModelType +
        ", Context=" + Context +
        ", applyType=" + applyType +
        ", authenticateType=" + authenticateType +
        "}";
    }
}
