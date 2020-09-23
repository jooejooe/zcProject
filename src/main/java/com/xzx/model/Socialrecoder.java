package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Helen
 * @since 2019-10-10
 */
public class Socialrecoder extends Model<Socialrecoder> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("社矫打卡记录主键id")
    @TableId(value = "socialrecoderId", type = IdType.AUTO)
    private Integer socialrecoderId;

    /**
     * 社交人员ID 关联社交人员表
     */
    @ApiModelProperty("社矫人员ID")
    private Integer socialId;

    /**
     * 社矫人员身份证号
     */
    @ApiModelProperty("社矫人员身份证号")
    private String socialIdCard;

    /**
     * 社矫人员手机号码
     */
    @ApiModelProperty("社矫人员手机号码")
    private String socialPhone;

    /**
     * 社矫人员姓名
     */
    @ApiModelProperty("社矫人员姓名")
    private String socialName;

    /**
     * 打卡时间
     */
    @ApiModelProperty("打卡时间")
    private Date recoderDate;

    public Integer getSocialrecoderId() {
        return socialrecoderId;
    }

    public void setSocialrecoderId(Integer socialrecoderId) {
        this.socialrecoderId = socialrecoderId;
    }
    public Integer getSocialId() {
        return socialId;
    }

    public void setSocialId(Integer socialId) {
        this.socialId = socialId;
    }
    public String getSocialIdCard() {
        return socialIdCard;
    }

    public void setSocialIdCard(String socialIdCard) {
        this.socialIdCard = socialIdCard;
    }
    public String getSocialPhone() {
        return socialPhone;
    }

    public void setSocialPhone(String socialPhone) {
        this.socialPhone = socialPhone;
    }
    public String getSocialName() {
        return socialName;
    }

    public void setSocialName(String socialName) {
        this.socialName = socialName;
    }
    public Date getRecoderDate() {
        return recoderDate;
    }

    public void setRecoderDate(Date recoderDate) {
        this.recoderDate = recoderDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.socialrecoderId;
    }

    @Override
    public String toString() {
        return "Socialrecoder{" +
        "socialrecoderId=" + socialrecoderId +
        ", socialId=" + socialId +
        ", socialIdCard=" + socialIdCard +
        ", socialPhone=" + socialPhone +
        ", socialName=" + socialName +
        ", recoderDate=" + recoderDate +
        "}";
    }
}
