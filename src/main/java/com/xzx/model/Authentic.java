package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 人民调节业务
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
public class Authentic extends Model<Authentic> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "AuthenticId", type = IdType.AUTO)
    @ApiModelProperty("人民调节主键id")
    private Integer AuthenticId;
    
    /**
     * 区域ID
     */
    @ApiModelProperty("区域ID")
    private Integer RegionId;

    /**
     * 仲裁机构ID
     */
    @ApiModelProperty("仲裁机构ID")
    private Integer AuthDepartId;
    
    /**
     * 仲裁类别 取在线申办数据字典
     */
    @ApiModelProperty("仲裁类别 取在线申办数据字典")
    private Integer assistanceId;

    /**
     * 申请人ID
     */
    @ApiModelProperty("申请人ID")
    private Integer UserId;

    /**
     * 对方当事人基本信息ID
     */
    @ApiModelProperty("对方当事人基本信息ID")
    private Integer OtherPartyId;

    /**
     * 纠纷概要
     */
    @ApiModelProperty("纠纷概要")
    private String SummaryContext;

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
     * 申请方当事人案情陈述
     */
    @ApiModelProperty("申请方当事人案情陈述")
    private String UserContext;
    
    /**
     * 被申请方当事人案情陈述
     */
    @ApiModelProperty("被申请方当事人案情陈述")
    private String OtherContext;
    
    /**
     * 预约时间
     */
    @ApiModelProperty("预约时间")
    private String YYSJ;
    
    /**
     * 指派人id 关联工作人员表主键ID
     */
    @ApiModelProperty("指派人id 关联工作人员表主键ID")
    private String zprId;
    
    /**
     * 指派时间
     */
    @ApiModelProperty("指派时间")
    private String zpsj;
    
    @ApiModelProperty("仲裁流转原因")
    private String TJLZYY;
    
    @ApiModelProperty("仲裁流转时间")
    private String TJLZSJ;

    @ApiModelProperty("申请方信息id")
    private Integer sqfinfoId;

    @ApiModelProperty("被申请方信息id")
    private Integer bsqfinfoId;

    @ApiModelProperty("二级类别id")
    private Integer assistanceSecondId;

    @ApiModelProperty("是否在线办理（0-在线办理【默认在线】，1-现场办理），2 委托机构办理")
    private Integer IsOnline;

    /**
     * 审批时间
     */
    @ApiModelProperty("审批时间")
    private String SPSJ;

	@ApiModelProperty("仲裁笔录")
    private String tjbl;

    @ApiModelProperty("申请方案由")
    private String sqfay;

    @ApiModelProperty("被申请方案由")
    private String bsqfay;

    @ApiModelProperty("受理费")
    private String slf;

    @ApiModelProperty("常规处理费")
    private String cgclf;

    @ApiModelProperty("案件金额")
    private String ajje;

    @ApiModelProperty("特殊处理费")
    private String tsclf;

    public String getAjje() {
        return ajje;
    }

    public void setAjje(String ajje) {
        this.ajje = ajje;
    }

    public String getSlf() {
        return slf;
    }

    public void setSlf(String slf) {
        this.slf = slf;
    }

    public String getCgclf() {
        return cgclf;
    }

    public void setCgclf(String cgclf) {
        this.cgclf = cgclf;
    }

    public String getTsclf() {
        return tsclf;
    }

    public void setTsclf(String tsclf) {
        this.tsclf = tsclf;
    }

    public String getSqfay() {
        return sqfay;
    }

    public Integer getIsOnline() {
        return IsOnline;
    }

    public void setIsOnline(Integer isOnline) {
        IsOnline = isOnline;
    }

    public void setSqfay(String sqfay) {
        this.sqfay = sqfay;
    }

    public String getBsqfay() {
        return bsqfay;
    }

    public void setBsqfay(String bsqfay) {
        this.bsqfay = bsqfay;
    }

    public Integer getAssistanceSecondId() {
        return assistanceSecondId;
    }

    public void setAssistanceSecondId(Integer assistanceSecondId) {
        this.assistanceSecondId = assistanceSecondId;
    }

    public Integer getAuthenticId() {
        return AuthenticId;
    }

    public void setAuthenticId(Integer AuthenticId) {
        this.AuthenticId = AuthenticId;
    }
    public Integer getAuthDepartId() {
        return AuthDepartId;
    }

    public void setAuthDepartId(Integer AuthDepartId) {
        this.AuthDepartId = AuthDepartId;
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
    public String getSummaryContext() {
        return SummaryContext;
    }

    public void setSummaryContext(String SummaryContext) {
        this.SummaryContext = SummaryContext;
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
    public Integer getAssistanceId() {
        return assistanceId;
    }

    public void setAssistanceId(Integer assistanceId) {
        this.assistanceId = assistanceId;
    }
    
    public String getFairworkerId() {
		return FairworkerId;
	}

	public void setFairworkerId(String fairworkerId) {
		FairworkerId = fairworkerId;
	}
	
	public String getUserContext() {
		return UserContext;
	}

	public void setUserContext(String userContext) {
		UserContext = userContext;
	}

	public String getOtherContext() {
		return OtherContext;
	}

	public void setOtherContext(String otherContext) {
		OtherContext = otherContext;
	}

	public String getYYSJ() {
		return YYSJ;
	}

	public void setYYSJ(String yYSJ) {
		YYSJ = yYSJ;
	}

	public String getZprId() {
		return zprId;
	}

	public void setZprId(String zprId) {
		this.zprId = zprId;
	}

	public String getZpsj() {
		return zpsj;
	}

	public void setZpsj(String zpsj) {
		this.zpsj = zpsj;
	}

	public String getSPSJ() {
		return SPSJ;
	}

	public void setSPSJ(String sPSJ) {
		SPSJ = sPSJ;
	}
	
	public String getTjbl() {
		return tjbl;
	}

	public void setTjbl(String tjbl) {
		this.tjbl = tjbl;
	}
	
    public String getTJLZYY() {
		return TJLZYY;
	}

	public void setTJLZYY(String tJLZYY) {
		TJLZYY = tJLZYY;
	}

	public String getTJLZSJ() {
		return TJLZSJ;
	}

	public void setTJLZSJ(String tJLZSJ) {
		TJLZSJ = tJLZSJ;
	}

    public Integer getSqfinfoId() {
        return sqfinfoId;
    }

    public void setSqfinfoId(Integer sqfinfoId) {
        this.sqfinfoId = sqfinfoId;
    }

    public Integer getBsqfinfoId() {
        return bsqfinfoId;
    }

    public void setBsqfinfoId(Integer bsqfinfoId) {
        this.bsqfinfoId = bsqfinfoId;
    }

    @Override
    protected Serializable pkVal() {
        return this.AuthenticId;
    }

    @Override
    public String toString() {
        return "Authentic{" +
        "AuthenticId=" + AuthenticId +
        ", AuthDepartId=" + AuthDepartId +
        ", UserId=" + UserId +
        ", OtherPartyId=" + OtherPartyId +
        ", SummaryContext=" + SummaryContext +
        ", Context=" + Context +
        ", CreateDate=" + CreateDate +
        ", RegionId=" + RegionId +
        ", assistanceId=" + assistanceId +
        ", FairworkerId=" + FairworkerId +
        ", UserContext=" + UserContext +
        ", OtherContext=" + OtherContext +
        ", YYSJ=" + YYSJ +
        ", zprId=" + zprId +
        ", zpsj=" + zpsj +
        ", SPSJ=" + SPSJ +
        ", sqfinfoId=" + sqfinfoId +
        ", bsqfinfoId=" + bsqfinfoId +
        "}";
    }
}
