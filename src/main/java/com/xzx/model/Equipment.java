package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 终端设备表
 * </p>
 *
 * @author Helen
 * @since 2019-09-29
 */
public class Equipment extends Model<Equipment> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "equipmentId", type = IdType.AUTO)
    private Integer equipmentId;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 登录状态 0 已登录 1 未登录
     */
    private Integer loginstate;

    /**
     * 令牌
     */
    private String token;

    /**
     * 所属机构
     */
    private Integer departmentId;

    /**
     * 所属区域
     */
    private Integer region;

    /**
     * 设备唯一码
     */
    private String equipmentCode;

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }
    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
    public Integer getLoginstate() {
        return loginstate;
    }

    public void setLoginstate(Integer loginstate) {
        this.loginstate = loginstate;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }
    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.equipmentId;
    }

    @Override
    public String toString() {
        return "Equipment{" +
        "equipmentId=" + equipmentId +
        ", equipmentName=" + equipmentName +
        ", loginstate=" + loginstate +
        ", token=" + token +
        ", departmentId=" + departmentId +
        ", region=" + region +
        ", equipmentCode=" + equipmentCode +
        "}";
    }
}
