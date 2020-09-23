package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Helen
 * @since 2019-12-26
 */
public class Consultworker extends Model<Consultworker> {

    private static final long serialVersionUID = 1L;

    /**
     * 表ID
     */
    @TableId(value = "ConsultWorkerId", type = IdType.AUTO)
    private Integer ConsultWorkerId;

    /**
     * 咨询区域ID
     */
    private Integer ConsultRegionId;

    /**
     * 咨询类别ID
     */
    private Integer ConsultTypeId;

    /**
     * 机构ID
     */
    private Integer DepartmentId;

    /**
     * 咨询人员ID
     */
    private Integer FairWorkerId;

    public Integer getConsultWorkerId() {
        return ConsultWorkerId;
    }

    public void setConsultWorkerId(Integer ConsultWorkerId) {
        this.ConsultWorkerId = ConsultWorkerId;
    }
    public Integer getConsultRegionId() {
        return ConsultRegionId;
    }

    public void setConsultRegionId(Integer ConsultRegionId) {
        this.ConsultRegionId = ConsultRegionId;
    }
    public Integer getConsultTypeId() {
        return ConsultTypeId;
    }

    public void setConsultTypeId(Integer ConsultTypeId) {
        this.ConsultTypeId = ConsultTypeId;
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

    @Override
    protected Serializable pkVal() {
        return this.ConsultWorkerId;
    }

    @Override
    public String toString() {
        return "Consultworker{" +
        "ConsultWorkerId=" + ConsultWorkerId +
        ", ConsultRegionId=" + ConsultRegionId +
        ", ConsultTypeId=" + ConsultTypeId +
        ", DepartmentId=" + DepartmentId +
        ", FairWorkerId=" + FairWorkerId +
        "}";
    }
}
