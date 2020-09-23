package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 司法鉴定
 * </p>
 *
 * @author Helen
 * @since 2019-09-26
 */
public class Appraisal extends Model<Appraisal> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "AppraisalId", type = IdType.AUTO)
    @ApiModelProperty("司法鉴定主键id")
    private Integer AppraisalId;

    /**
     * 鉴定事项类别ID
     */
    @ApiModelProperty("鉴定事项类别ID")
    private Integer MatterType;

    /**
     * 对应鉴定事项类别ID
     */
    @ApiModelProperty("对方类型  读取数据字典表10")
    private Integer NextMatterType;

    /**
     * 鉴定机构
     */
    @ApiModelProperty("鉴定机构")
    private Integer MatterDepart;

    /**
     * 申请人ID
     */
    @ApiModelProperty("申请人ID")
    private Integer UserId;

    /**
     * 对方当事人或当事机构ID
     */
    @ApiModelProperty("对方当事人或当事机构ID")
    private Integer OtherPartyId;

    /**
     * 纠纷概要
     */
    @ApiModelProperty("纠纷概要")
    private String Summary;

    /**
     * 当事人申请事项
     */
    @ApiModelProperty("当事人申请事项")
    private String Context;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String CreateDate;
    
    /**
     * 工作人员id
     */
    @ApiModelProperty("工作人员id")
    private String FairworkerId;

	/**
     * 区域ID
     */
    @ApiModelProperty("区域ID")
    private Integer RegionId;
    
    /**
     * 对方当事人类型（数据字典表类型为10的数据id）
     */
    @ApiModelProperty("对方当事人类型（数据字典表类型为10的数据id）")
    private Integer OtherPartyType;

	public Integer getAppraisalId() {
        return AppraisalId;
    }

    public void setAppraisalId(Integer AppraisalId) {
        this.AppraisalId = AppraisalId;
    }
    public Integer getMatterType() {
        return MatterType;
    }

    public void setMatterType(Integer MatterType) {
        this.MatterType = MatterType;
    }
    public Integer getNextMatterType() {
        return NextMatterType;
    }

    public void setNextMatterType(Integer NextMatterType) {
        this.NextMatterType = NextMatterType;
    }
    public Integer getMatterDepart() {
        return MatterDepart;
    }

    public void setMatterDepart(Integer MatterDepart) {
        this.MatterDepart = MatterDepart;
    }
    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }
    public Integer getOtherPartyId() {
        return OtherPartyId;
    }

    public void setOtherPartyId(Integer OtherPartyId) {
        this.OtherPartyId = OtherPartyId;
    }
    public String getSummary() {
        return Summary;
    }

    public void setSummary(String Summary) {
        this.Summary = Summary;
    }
    public String getContext() {
        return Context;
    }

    public void setContext(String Context) {
        this.Context = Context;
    }
    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }
    public Integer getRegionId() {
        return RegionId;
    }

    public void setRegionId(Integer RegionId) {
        this.RegionId = RegionId;
    }
    

    public String getFairworkerId() {
		return FairworkerId;
	}

	public void setFairworkerId(String fairworkerId) {
		FairworkerId = fairworkerId;
	}
	
    public Integer getOtherPartyType() {
		return OtherPartyType;
	}

	public void setOtherPartyType(Integer otherPartyType) {
		OtherPartyType = otherPartyType;
	}

    @Override
    protected Serializable pkVal() {
        return this.AppraisalId;
    }

    @Override
    public String toString() {
        return "Appraisal{" +
        "AppraisalId=" + AppraisalId +
        ", MatterType=" + MatterType +
        ", NextMatterType=" + NextMatterType +
        ", MatterDepart=" + MatterDepart +
        ", UserId=" + UserId +
        ", OtherPartyId=" + OtherPartyId +
        ", Summary=" + Summary +
        ", Context=" + Context +
        ", CreateDate=" + CreateDate +
        ", RegionId=" + RegionId +
        ", FairworkerId=" + FairworkerId +
        "}";
    }
}
