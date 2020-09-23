package com.xzx.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 区域
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public class Region extends Model<Region> {

    private static final long serialVersionUID = 1L;

    /**
     * 区域ID
     */
    @TableId(value = "RegionId", type = IdType.AUTO)
    private Integer RegionId;

    /**
     * 区域名称
     */
    private String RegionName;

    /**
     * 区域编码
     */
    private String RegionCode;

    /**
     * 上级区域ID
     */
    private Integer ParentId;

    public Integer getRegionId() {
        return RegionId;
    }

    public void setRegionId(Integer RegionId) {
        this.RegionId = RegionId;
    }
    public String getRegionName() {
        return RegionName;
    }

    public void setRegionName(String RegionName) {
        this.RegionName = RegionName;
    }
    public String getRegionCode() {
        return RegionCode;
    }

    public void setRegionCode(String RegionCode) {
        this.RegionCode = RegionCode;
    }
    public Integer getParentId() {
        return ParentId;
    }

    public void setParentId(Integer ParentId) {
        this.ParentId = ParentId;
    }

    @Override
    protected Serializable pkVal() {
        return this.RegionId;
    }

    @Override
    public String toString() {
        return "Region{" +
        "RegionId=" + RegionId +
        ", RegionName=" + RegionName +
        ", RegionCode=" + RegionCode +
        ", ParentId=" + ParentId +
        "}";
    }
}
