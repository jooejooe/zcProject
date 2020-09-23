package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 对方当事人信息
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
public class Otherparty extends Model<Otherparty> {

    private static final long serialVersionUID = 1L;

    /**
     * 对方当事人ID
     */
    @TableId(value = "OtherpartyId", type = IdType.AUTO)
    @ApiModelProperty("对方当事人ID")
    private Integer OtherpartyId;

    /**
     * 对方当事人姓名
     */
    @ApiModelProperty("对方当事人姓名")
    private String OtherpartyName;

    /**
     * 与申请人关系
     */
    @ApiModelProperty("与申请人关系")
    private String RelationsUser;

    /**
     * 对方当事人联系电话
     */
    @ApiModelProperty("对方当事人联系电话")
    private String RelationsPhone;

    /**
     * 对方当事人电子邮箱
     */
    @ApiModelProperty("方当事人电子邮箱")
    private String RelationsEmail;

    /**
     * 对方当事人所住地
     */
    @ApiModelProperty("对方当事人所住地")
    private String RelationsAddress;

    /**
     * 对方当事人地址
     */
    @ApiModelProperty("对方当事人地址")
    private String RelationPlace;

    /**
     * 在线服务类别 0 公正 1 人民仲裁 2 司法鉴定 3 法律援助
     */
    @ApiModelProperty("在线服务类别 0 公证  1 人民仲裁 2 司法鉴定 3 法律援助")
    private Integer OnlineType;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String SFZH;

    /**
     * 对方机构名称
     */
    @ApiModelProperty("对方机构名称")
    private String OtherDepartNAME;

    /**
     * 对方机构联系电话
     */
    @ApiModelProperty("对方机构联系电话")
    private String OtherDepartPhone;

    /**
     * 对方机构地址
     */
    @ApiModelProperty("对方机构地址")
    private String OtherDepartAddress;
    
    /**
     * 工作单位
     */
    @ApiModelProperty("WorkDepart")
    private String WorkDepart;

	public Integer getOtherpartyId() {
        return OtherpartyId;
    }

    public void setOtherpartyId(Integer OtherpartyId) {
        this.OtherpartyId = OtherpartyId;
    }
    public String getOtherpartyName() {
        return OtherpartyName;
    }

    public void setOtherpartyName(String OtherpartyName) {
        this.OtherpartyName = OtherpartyName;
    }
    public String getRelationsUser() {
        return RelationsUser;
    }

    public void setRelationsUser(String RelationsUser) {
        this.RelationsUser = RelationsUser;
    }
    public String getRelationsPhone() {
        return RelationsPhone;
    }

    public void setRelationsPhone(String RelationsPhone) {
        this.RelationsPhone = RelationsPhone;
    }
    public String getRelationsEmail() {
        return RelationsEmail;
    }

    public void setRelationsEmail(String RelationsEmail) {
        this.RelationsEmail = RelationsEmail;
    }
    public String getRelationsAddress() {
        return RelationsAddress;
    }

    public void setRelationsAddress(String RelationsAddress) {
        this.RelationsAddress = RelationsAddress;
    }
    public String getRelationPlace() {
        return RelationPlace;
    }

    public void setRelationPlace(String RelationPlace) {
        this.RelationPlace = RelationPlace;
    }
    public Integer getOnlineType() {
        return OnlineType;
    }

    public void setOnlineType(Integer OnlineType) {
        this.OnlineType = OnlineType;
    }
    public String getSFZH() {
        return SFZH;
    }

    public void setSFZH(String SFZH) {
        this.SFZH = SFZH;
    }
    public String getOtherDepartNAME() {
        return OtherDepartNAME;
    }

    public void setOtherDepartNAME(String OtherDepartNAME) {
        this.OtherDepartNAME = OtherDepartNAME;
    }
    public String getOtherDepartPhone() {
        return OtherDepartPhone;
    }

    public void setOtherDepartPhone(String OtherDepartPhone) {
        this.OtherDepartPhone = OtherDepartPhone;
    }
    public String getOtherDepartAddress() {
        return OtherDepartAddress;
    }

    public void setOtherDepartAddress(String OtherDepartAddress) {
        this.OtherDepartAddress = OtherDepartAddress;
    }
    
    public String getWorkDepart() {
		return WorkDepart;
	}

	public void setWorkDepart(String workDepart) {
		WorkDepart = workDepart;
	}

    @Override
    protected Serializable pkVal() {
        return this.OtherpartyId;
    }

    @Override
    public String toString() {
        return "Otherparty{" +
        "OtherpartyId=" + OtherpartyId +
        ", OtherpartyName=" + OtherpartyName +
        ", RelationsUser=" + RelationsUser +
        ", RelationsPhone=" + RelationsPhone +
        ", RelationsEmail=" + RelationsEmail +
        ", RelationsAddress=" + RelationsAddress +
        ", RelationPlace=" + RelationPlace +
        ", OnlineType=" + OnlineType +
        ", SFZH=" + SFZH +
        ", OtherDepartNAME=" + OtherDepartNAME +
        ", OtherDepartPhone=" + OtherDepartPhone +
        ", OtherDepartAddress=" + OtherDepartAddress +
        ", WorkDepart=" +WorkDepart+
        "}";
    }
}
