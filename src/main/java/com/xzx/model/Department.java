package com.xzx.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 机构
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public class Department extends Model<Department> {

    private static final long serialVersionUID = 1L;

    /**
     * 机构ID
     */
    @TableId(value = "DepartmentId", type = IdType.AUTO)
    private Integer DepartmentId;

    /**
     * 机构编码
     */
    private String DepartmentCode;

    /**
     * 机构名称
     */
    private String DepartmentName;

    /**
     * 机构形象标识
     */
    private String DepartmentLogo;

    /**
     * 机构简称
     */
    private String DepartmentAbbreviation;

    /**
     * 执业证号
     */
    private String PractisingCode;

    /**
     * 是否承办涉外公正业务 0 是  1 否
     */
    private Integer IsFair;

    /**
     * 机构创建时间
     */
    private String DepartmentCreateTime;

    /**
     * 执业区域
     */
    private Integer AreaId;

    /**
     * 负责人
     */
    private String Responsible;

    /**
     * 发证机关
     */
    private String CertificationAuthority;

    /**
     * 发证时间
     */
    private String CertificationTime;

    /**
     * 机构联系电话
     */
    private String DepartmentPhone;

    /**
     * 传真号
     */
    private String DepartmentFax;

    /**
     * 电子邮箱
     */
    private String DepartmentEmail;

    /**
     * 单位邮编
     */
    private String DepartmentZipCode;

    /**
     * 机构地址
     */
    private String DepartmentAddress;

    /**
     * 官方网站
     */
    private String Internet;

    /**
     * 经度
     */
    private String X;

    /**
     * 纬度
     */
    private String Y;

    /**
     * 机构简介
     */
    private String DepartmentContext;

    /**
     * 有效 0 无效1
     */
    private Integer State;
    
    /**
     * 机构类别
     */
    private Integer DepartType;

	public Integer getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Integer DepartmentId) {
        this.DepartmentId = DepartmentId;
    }
    public String getDepartmentCode() {
        return DepartmentCode;
    }

    public void setDepartmentCode(String DepartmentCode) {
        this.DepartmentCode = DepartmentCode;
    }
    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String DepartmentName) {
        this.DepartmentName = DepartmentName;
    }
    public String getDepartmentLogo() {
        return DepartmentLogo;
    }

    public void setDepartmentLogo(String DepartmentLogo) {
        this.DepartmentLogo = DepartmentLogo;
    }
    public String getDepartmentAbbreviation() {
        return DepartmentAbbreviation;
    }

    public void setDepartmentAbbreviation(String DepartmentAbbreviation) {
        this.DepartmentAbbreviation = DepartmentAbbreviation;
    }
    public String getPractisingCode() {
        return PractisingCode;
    }

    public void setPractisingCode(String PractisingCode) {
        this.PractisingCode = PractisingCode;
    }
    public Integer getIsFair() {
        return IsFair;
    }

    public void setIsFair(Integer IsFair) {
        this.IsFair = IsFair;
    }
    public String getDepartmentCreateTime() {
        return DepartmentCreateTime;
    }

    public void setDepartmentCreateTime(String DepartmentCreateTime) {
        this.DepartmentCreateTime = DepartmentCreateTime;
    }
    public Integer getAreaId() {
        return AreaId;
    }

    public void setAreaId(Integer AreaId) {
        this.AreaId = AreaId;
    }
    public String getResponsible() {
        return Responsible;
    }

    public void setResponsible(String Responsible) {
        this.Responsible = Responsible;
    }
    public String getCertificationAuthority() {
        return CertificationAuthority;
    }

    public void setCertificationAuthority(String CertificationAuthority) {
        this.CertificationAuthority = CertificationAuthority;
    }
    public String getCertificationTime() {
        return CertificationTime;
    }

    public void setCertificationTime(String CertificationTime) {
        this.CertificationTime = CertificationTime;
    }
    public String getDepartmentPhone() {
        return DepartmentPhone;
    }

    public void setDepartmentPhone(String DepartmentPhone) {
        this.DepartmentPhone = DepartmentPhone;
    }
    public String getDepartmentFax() {
        return DepartmentFax;
    }

    public void setDepartmentFax(String DepartmentFax) {
        this.DepartmentFax = DepartmentFax;
    }
    public String getDepartmentEmail() {
        return DepartmentEmail;
    }

    public void setDepartmentEmail(String DepartmentEmail) {
        this.DepartmentEmail = DepartmentEmail;
    }
    public String getDepartmentZipCode() {
        return DepartmentZipCode;
    }

    public void setDepartmentZipCode(String DepartmentZipCode) {
        this.DepartmentZipCode = DepartmentZipCode;
    }
    public String getDepartmentAddress() {
        return DepartmentAddress;
    }

    public void setDepartmentAddress(String DepartmentAddress) {
        this.DepartmentAddress = DepartmentAddress;
    }
    public String getInternet() {
        return Internet;
    }

    public void setInternet(String Internet) {
        this.Internet = Internet;
    }
    public String getX() {
        return X;
    }

    public void setX(String X) {
        this.X = X;
    }
    public String getY() {
        return Y;
    }

    public void setY(String Y) {
        this.Y = Y;
    }
    public String getDepartmentContext() {
        return DepartmentContext;
    }

    public void setDepartmentContext(String DepartmentContext) {
        this.DepartmentContext = DepartmentContext;
    }
    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
    }
    
    public Integer getDepartType() {
		return DepartType;
	}

	public void setDepartType(Integer departType) {
		DepartType = departType;
	}

    @Override
    protected Serializable pkVal() {
        return this.DepartmentId;
    }

    @Override
    public String toString() {
        return "Department{" +
        "DepartmentId=" + DepartmentId +
        ", DepartmentCode=" + DepartmentCode +
        ", DepartmentName=" + DepartmentName +
        ", DepartmentLogo=" + DepartmentLogo +
        ", DepartmentAbbreviation=" + DepartmentAbbreviation +
        ", PractisingCode=" + PractisingCode +
        ", IsFair=" + IsFair +
        ", DepartmentCreateTime=" + DepartmentCreateTime +
        ", AreaId=" + AreaId +
        ", Responsible=" + Responsible +
        ", CertificationAuthority=" + CertificationAuthority +
        ", CertificationTime=" + CertificationTime +
        ", DepartmentPhone=" + DepartmentPhone +
        ", DepartmentFax=" + DepartmentFax +
        ", DepartmentEmail=" + DepartmentEmail +
        ", DepartmentZipCode=" + DepartmentZipCode +
        ", DepartmentAddress=" + DepartmentAddress +
        ", Internet=" + Internet +
        ", X=" + X +
        ", Y=" + Y +
        ", DepartmentContext=" + DepartmentContext +
        ", State=" + State +
        ", DepartType=" + DepartType +
        "}";
    }
}
