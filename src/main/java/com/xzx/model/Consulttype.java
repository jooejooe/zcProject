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
public class Consulttype extends Model<Consulttype> {

    private static final long serialVersionUID = 1L;

    /**
     * 咨询类别ID
     */
    @TableId(value = "ConsultTypeId", type = IdType.AUTO)
    private Integer ConsultTypeId;

    /**
     * 咨询类别名称
     */
    private String TypeName;

    /**
     * 咨询区域ID
     */
    private Integer ConsultRegionId;

    /**
     * 状态 0 启用 1未启用
     */
    private Integer State;

    /**
     * 创建时间
     */
    private String Createdate;

    public Integer getConsultTypeId() {
        return ConsultTypeId;
    }

    public void setConsultTypeId(Integer ConsultTypeId) {
        this.ConsultTypeId = ConsultTypeId;
    }
    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }
    public Integer getConsultRegionId() {
        return ConsultRegionId;
    }

    public void setConsultRegionId(Integer ConsultRegionId) {
        this.ConsultRegionId = ConsultRegionId;
    }
    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
    }
    public String getCreatedate() {
        return Createdate;
    }

    public void setCreatedate(String Createdate) {
        this.Createdate = Createdate;
    }

    @Override
    protected Serializable pkVal() {
        return this.ConsultTypeId;
    }

    @Override
    public String toString() {
        return "Consulttype{" +
        "ConsultTypeId=" + ConsultTypeId +
        ", TypeName=" + TypeName +
        ", ConsultRegionId=" + ConsultRegionId +
        ", State=" + State +
        ", Createdate=" + Createdate +
        "}";
    }
}
