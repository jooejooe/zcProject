package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 公证办理
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public class Just extends Model<Just> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "JustId", type = IdType.AUTO)
    @ApiModelProperty("公证办理ID")
    private Integer JustId;

	/**
     * 公证处ID
     */
    @ApiModelProperty("公证处ID")
    private Integer DepartmentId;

    /**
     * 公证员ID
     */
    @ApiModelProperty("公证员ID")
    private Integer FairWorkerId;

    /**
     * 公证类型
     */
    @ApiModelProperty("公证类型")
    private Integer JustType;

    /**
     * 翻译语言 0 中文 1 英文
     */
    @ApiModelProperty("翻译语言 0 中文 1 英文")
    private Integer Language;

    /**
     * 选择用途
     */
    @ApiModelProperty("选择用途")
    private Integer PurposeId;

    /**
     * 使用地
     */
    @ApiModelProperty("使用地ID")
    private Integer UsePlaceId;

    /**
     * 申办事件费用及份数ID
     */
    @ApiModelProperty("申办事件费用及份数ID")
    private Integer BiddingMagttersMoneyId;

    /**
     * 申办公证业务类型ID
     */
    @ApiModelProperty("申办公证业务类型ID")
    private Integer BiddingMattersId;

    /**
     * 申办人ID
     */
    @ApiModelProperty("申办人ID")
    private Integer UserId;

    /**
     * 申办时间
     */
    @ApiModelProperty("申办时间")
    private String CreateDate;

    /**
     * 申办状态
     */
    @ApiModelProperty("申办状态")
    private Integer JustState;
    
    /**
     * 是否上门 0 不上门 1 上门
     */
    @ApiModelProperty("是否上门 0 不上门 1 上门")
    private Integer IsDoor;
    
    /**
     * 区域id
     */
    @ApiModelProperty("区域id")
    private Integer regionId;

	public Integer getJustId() {
        return JustId;
    }

    public void setJustId(Integer JustId) {
        this.JustId = JustId;
    }
    public Integer getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Integer DepartmentId) {
        this.DepartmentId = DepartmentId;
    }
    public Integer getFairWorkerId() {
        return FairWorkerId;
    }

    public void setFairWorkerId(Integer FairWorkerId) {
        this.FairWorkerId = FairWorkerId;
    }
    public Integer getJustType() {
        return JustType;
    }

    public void setJustType(Integer JustType) {
        this.JustType = JustType;
    }
    public Integer getLanguage() {
        return Language;
    }

    public void setLanguage(Integer Language) {
        this.Language = Language;
    }
    public Integer getPurposeId() {
        return PurposeId;
    }

    public void setPurposeId(Integer PurposeId) {
        this.PurposeId = PurposeId;
    }
    public Integer getUsePlaceId() {
        return UsePlaceId;
    }

    public void setUsePlaceId(Integer UsePlaceId) {
        this.UsePlaceId = UsePlaceId;
    }
    public Integer getBiddingMagttersMoneyId() {
        return BiddingMagttersMoneyId;
    }

    public void setBiddingMagttersMoneyId(Integer BiddingMagttersMoneyId) {
        this.BiddingMagttersMoneyId = BiddingMagttersMoneyId;
    }
    public Integer getBiddingMattersId() {
        return BiddingMattersId;
    }

    public void setBiddingMattersId(Integer BiddingMattersId) {
        this.BiddingMattersId = BiddingMattersId;
    }
    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }
    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }
    public Integer getJustState() {
        return JustState;
    }

    public void setJustState(Integer JustState) {
        this.JustState = JustState;
    }
    
    public Integer getIsDoor() {
		return IsDoor;
	}

	public void setIsDoor(Integer isDoor) {
		IsDoor = isDoor;
	}
	
    public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

    @Override
    protected Serializable pkVal() {
        return this.JustId;
    }

    @Override
    public String toString() {
        return "Just{" +
        "JustId=" + JustId +
        ", DepartmentId=" + DepartmentId +
        ", FairWorkerId=" + FairWorkerId +
        ", JustType=" + JustType +
        ", Language=" + Language +
        ", PurposeId=" + PurposeId +
        ", UsePlaceId=" + UsePlaceId +
        ", BiddingMagttersMoneyId=" + BiddingMagttersMoneyId +
        ", BiddingMattersId=" + BiddingMattersId +
        ", UserId=" + UserId +
        ", CreateDate=" + CreateDate +
        ", JustState=" + JustState +
        ", IsDoor=" + IsDoor +
        ", regionId=" + regionId +
        "}";
    }
}
