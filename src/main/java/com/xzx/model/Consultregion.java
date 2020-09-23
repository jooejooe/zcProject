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
public class Consultregion extends Model<Consultregion> {

    private static final long serialVersionUID = 1L;

    /**
     * 咨询区域ID
     */
    @TableId(value = "ConsultRegionId", type = IdType.AUTO)
    private Integer ConsultRegionId;

    /**
     * 咨询区域名称
     */
    private String RegionName;

    /**
     * 状态 0 启用 1未启用
     */
    private Integer State;

    public Integer getConsultRegionId() {
        return ConsultRegionId;
    }

    public void setConsultRegionId(Integer ConsultRegionId) {
        this.ConsultRegionId = ConsultRegionId;
    }
    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String RegionName) {
        this.RegionName = RegionName;
    }
    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
    }

    @Override
    protected Serializable pkVal() {
        return this.ConsultRegionId;
    }

    @Override
    public String toString() {
        return "Consultregion{" +
        "ConsultRegionId=" + ConsultRegionId +
        ", RegionName=" + RegionName +
        ", State=" + State +
        "}";
    }
}
