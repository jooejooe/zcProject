package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 社矫人员
 * </p>
 *
 * @author Helen
 * @since 2019-09-27
 */
public class Social extends Model<Social> {

    private static final long serialVersionUID = 1L;

    /**
     * 社矫人员ID
     */
    @ApiModelProperty("社矫人员ID")
    @TableId(value = "SocialUserId", type = IdType.AUTO)
    private Integer SocialUserId;

    /**
     * 社矫人员姓名
     */
    @ApiModelProperty("社矫人员姓名")
    private String SocialUserName;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String SocialIdCard;

    /**
     * 电话号码
     */
    @ApiModelProperty("电话号码")
    private String SocialPhone;

    /**
     * 人员照片
     */
    @ApiModelProperty("人员照片")
    private String SocialImage;

    @ApiModelProperty("社矫人员身份证照片")
    private String SocialIdCardImage;

    /**
     * 监管方式 0 宽管   1 严管
     */
    @ApiModelProperty("监管方式 0 宽管   1 严管")
    private Integer SuperviseType;

    /**
     * 打卡统计周期
     */
    @ApiModelProperty("打卡统计周期")
    private String StatisticsDays;

    /**
     * 原判开始日期
     */
    @ApiModelProperty("原判开始日期")
    private String JudgementStartTime;

    /**
     * 原判结束日期
     */
    @ApiModelProperty("原判结束日期")
    private String JudgementEndTime;

    /**
     * 是否需要打卡  0 需要  1不需要
     */
    @ApiModelProperty("是否需要打卡  0 需要  1不需要")
    private Integer IsPunch;

    /**
     * 户籍地址
     */
    @ApiModelProperty("户籍地址")
    private String HouseholdAddress;

    /**
     * 现住址
     */
    @ApiModelProperty("现住址")
    private String Address;

    /**
     * 家属姓名
     */
    @ApiModelProperty("家属姓名")
    private String FamilyName;

    /**
     * 家属联系电话
     */
    @ApiModelProperty("家属联系电话")
    private String FamilyPhone;

    /**
     * 担保人姓名
     */
    @ApiModelProperty("担保人姓名")
    private String GuaranteeName;

    /**
     * 担保人联系电话
     */
    @ApiModelProperty("担保人联系电话")
    private String GuaranteePhone;

    /**
     * 朋友姓名
     */
    @ApiModelProperty("朋友姓名")
    private String FriendName;

    /**
     * 朋友联系电话
     */
    @ApiModelProperty("朋友联系电话")
    private String FriendPhone;

    /**
     * 监管人ID
     */
    @ApiModelProperty("监管人ID")
    private Integer UserId;

    /**
     * 状态 0 激活 1停用
     */
    @ApiModelProperty("状态 0 激活 1停用")
    private Integer State;
    
    @ApiModelProperty("指纹")
    private String HandImage;

	public Integer getSocialUserId() {
        return SocialUserId;
    }

    public void setSocialUserId(Integer SocialUserId) {
        this.SocialUserId = SocialUserId;
    }
    public String getSocialUserName() {
        return SocialUserName;
    }

    public void setSocialUserName(String SocialUserName) {
        this.SocialUserName = SocialUserName;
    }
    public String getSocialIdCard() {
        return SocialIdCard;
    }

    public void setSocialIdCard(String SocialIdCard) {
        this.SocialIdCard = SocialIdCard;
    }
    public String getSocialPhone() {
        return SocialPhone;
    }

    public void setSocialPhone(String SocialPhone) {
        this.SocialPhone = SocialPhone;
    }
    public String getSocialImage() {
        return SocialImage;
    }

    public void setSocialImage(String SocialImage) {
        this.SocialImage = SocialImage;
    }
    public String getSocialIdCardImage() {
        return SocialIdCardImage;
    }

    public void setSocialIdCardImage(String SocialIdCardImage) {
        this.SocialIdCardImage = SocialIdCardImage;
    }
    public Integer getSuperviseType() {
        return SuperviseType;
    }

    public void setSuperviseType(Integer SuperviseType) {
        this.SuperviseType = SuperviseType;
    }
    public String getStatisticsDays() {
        return StatisticsDays;
    }

    public void setStatisticsDays(String StatisticsDays) {
        this.StatisticsDays = StatisticsDays;
    }
    public String getJudgementStartTime() {
        return JudgementStartTime;
    }

    public void setJudgementStartTime(String JudgementStartTime) {
        this.JudgementStartTime = JudgementStartTime;
    }
    public String getJudgementEndTime() {
        return JudgementEndTime;
    }

    public void setJudgementEndTime(String JudgementEndTime) {
        this.JudgementEndTime = JudgementEndTime;
    }
    public Integer getIsPunch() {
        return IsPunch;
    }

    public void setIsPunch(Integer IsPunch) {
        this.IsPunch = IsPunch;
    }
    public String getHouseholdAddress() {
        return HouseholdAddress;
    }

    public void setHouseholdAddress(String HouseholdAddress) {
        this.HouseholdAddress = HouseholdAddress;
    }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String FamilyName) {
        this.FamilyName = FamilyName;
    }
    public String getFamilyPhone() {
        return FamilyPhone;
    }

    public void setFamilyPhone(String FamilyPhone) {
        this.FamilyPhone = FamilyPhone;
    }
    public String getGuaranteeName() {
        return GuaranteeName;
    }

    public void setGuaranteeName(String GuaranteeName) {
        this.GuaranteeName = GuaranteeName;
    }
    public String getGuaranteePhone() {
        return GuaranteePhone;
    }

    public void setGuaranteePhone(String GuaranteePhone) {
        this.GuaranteePhone = GuaranteePhone;
    }
    public String getFriendName() {
        return FriendName;
    }

    public void setFriendName(String FriendName) {
        this.FriendName = FriendName;
    }
    public String getFriendPhone() {
        return FriendPhone;
    }

    public void setFriendPhone(String FriendPhone) {
        this.FriendPhone = FriendPhone;
    }
    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer UserId) {
        this.UserId = UserId;
    }
    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
    }
    
    public String getHandImage() {
		return HandImage;
	}

	public void setHandImage(String handImage) {
		HandImage = handImage;
	}

    @Override
    protected Serializable pkVal() {
        return this.SocialUserId;
    }

    @Override
    public String toString() {
        return "Social{" +
        "SocialUserId=" + SocialUserId +
        ", SocialUserName=" + SocialUserName +
        ", SocialIdCard=" + SocialIdCard +
        ", SocialPhone=" + SocialPhone +
        ", SocialImage=" + SocialImage +
        ", SocialIdCardImage=" + SocialIdCardImage +
        ", SuperviseType=" + SuperviseType +
        ", StatisticsDays=" + StatisticsDays +
        ", JudgementStartTime=" + JudgementStartTime +
        ", JudgementEndTime=" + JudgementEndTime +
        ", IsPunch=" + IsPunch +
        ", HouseholdAddress=" + HouseholdAddress +
        ", Address=" + Address +
        ", FamilyName=" + FamilyName +
        ", FamilyPhone=" + FamilyPhone +
        ", GuaranteeName=" + GuaranteeName +
        ", GuaranteePhone=" + GuaranteePhone +
        ", FriendName=" + FriendName +
        ", FriendPhone=" + FriendPhone +
        ", UserId=" + UserId +
        ", State=" + State +
        ", HandImage=" + HandImage +
        "}";
    }
}
