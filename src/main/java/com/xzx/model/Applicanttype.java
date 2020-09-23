package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 法律援助所需资料
 * </p>
 *
 * @author Helen
 * @since 2019-09-26
 */
public class Applicanttype extends Model<Applicanttype> {

    private static final long serialVersionUID = 1L;

    /**
     * 法律援助申请人类型主键
     */
    @TableId(value = "ApplicantTypeId", type = IdType.AUTO)
    private Integer ApplicantTypeId;

    /**
     * 标题
     */
    private String Title;

    /**
     * 所需资料,以逗号分隔开
     */
    private String DatasInfo;

    /**
     * 所需资料个数
     */
    private Integer DatasNumber;

    /**
     * 状态 0 启用 1未启用
     */
    private Integer State;

    /**
     * 创建时间
     */
    private String CreateDate;

    public Integer getApplicantTypeId() {
        return ApplicantTypeId;
    }

    public void setApplicantTypeId(Integer ApplicantTypeId) {
        this.ApplicantTypeId = ApplicantTypeId;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public String getDatasInfo() {
        return DatasInfo;
    }

    public void setDatasInfo(String DatasInfo) {
        this.DatasInfo = DatasInfo;
    }
    public Integer getDatasNumber() {
        return DatasNumber;
    }

    public void setDatasNumber(Integer DatasNumber) {
        this.DatasNumber = DatasNumber;
    }
    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
    }
    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.ApplicantTypeId;
    }

    @Override
    public String toString() {
        return "Applicanttype{" +
        "ApplicantTypeId=" + ApplicantTypeId +
        ", Title=" + Title +
        ", DatasInfo=" + DatasInfo +
        ", DatasNumber=" + DatasNumber +
        ", State=" + State +
        ", CreateDate=" + CreateDate +
        "}";
    }
}
