package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 法律援助
 * </p>
 *
 * @author Helen
 * @since 2019-09-26
 */
public class Lawhelp extends Model<Lawhelp> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "LawHelpId", type = IdType.AUTO)
    @ApiModelProperty("法律援助主键id")
    private Integer LawHelpId;

    /**
     * 法律援助机构
     */
    @ApiModelProperty("法律援助机构id")
    private Integer LawDepartment;

    /**
     * 法律援助申请人类型
     */
    @ApiModelProperty("法律援助申请人类型")
    private Integer ApplicantTypeId;

    /**
     * 援助类别Id
     */
    @ApiModelProperty("援助类别Id")
    private Integer assistanceId;

    /**
     * 申请人ID
     */
    @ApiModelProperty("申请人ID")
    private Integer UserId;

    /**
     * 对方当事人Id
     */
    @ApiModelProperty("对方当事人Id")
    private Integer OtherPartyId;

    /**
     * 法律状态
     */
    @ApiModelProperty("法律状态 取数据字典表9")
    private Integer LawState;

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
     * 区域ID
     */
    @ApiModelProperty("区域ID")
    private Integer RegionId;
    
    /**
     * 工作人员id
     */
    @ApiModelProperty("工作人员id")
    private String FairworkerId;
    
    /**
     * 相应援助类别选择的选项id或内容拼接串
     */
    @ApiModelProperty("相应援助类别选择的选项id或内容拼接串")
    private String selectOptions;

	public Integer getLawHelpId() {
        return LawHelpId;
    }

    public void setLawHelpId(Integer LawHelpId) {
        this.LawHelpId = LawHelpId;
    }
    public Integer getLawDepartment() {
        return LawDepartment;
    }

    public void setLawDepartment(Integer LawDepartment) {
        this.LawDepartment = LawDepartment;
    }
    public Integer getApplicantTypeId() {
        return ApplicantTypeId;
    }

    public void setApplicantTypeId(Integer ApplicantTypeId) {
        this.ApplicantTypeId = ApplicantTypeId;
    }
    public Integer getAssistanceId() {
        return assistanceId;
    }

    public void setAssistanceId(Integer assistanceId) {
        this.assistanceId = assistanceId;
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
    public Integer getLawState() {
        return LawState;
    }

    public void setLawState(Integer LawState) {
        this.LawState = LawState;
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
	
	public String getSelectOptions() {
		return selectOptions;
	}

	public void setSelectOptions(String selectOptions) {
		this.selectOptions = selectOptions;
	}

    @Override
    protected Serializable pkVal() {
        return this.LawHelpId;
    }

    @Override
    public String toString() {
        return "Lawhelp{" +
        "LawHelpId=" + LawHelpId +
        ", LawDepartment=" + LawDepartment +
        ", ApplicantTypeId=" + ApplicantTypeId +
        ", assistanceId=" + assistanceId +
        ", UserId=" + UserId +
        ", OtherPartyId=" + OtherPartyId +
        ", LawState=" + LawState +
        ", Summary=" + Summary +
        ", Context=" + Context +
        ", CreateDate=" + CreateDate +
        ", RegionId=" + RegionId +
        ", FairworkerId=" + FairworkerId +
        "}";
    }
}
