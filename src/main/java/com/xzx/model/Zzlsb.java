package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Helen
 * @since 2020-08-27
 */
public class Zzlsb extends Model<Zzlsb> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "zzlsbId", type = IdType.AUTO)
    @ApiModelProperty("质证记录临时表主键ID")
    private Integer zzlsbId;

    /**
     * 案件ID
     */
    @ApiModelProperty("案件ID")
    private Integer anjianId;

    /**
     * 证据ID
     */
    @ApiModelProperty("证据ID")
    private Integer tjyaccessId;

    /**
     * 仲裁员ID
     */
    @ApiModelProperty("仲裁员ID")
    private Integer fairworkerid;

    /**
     * 质证状态  0 不认可  1 认可
     */
    @ApiModelProperty("质证状态  0 不认可  1 认可")
    private Integer state;

    /**
     * 不认可原因
     */
    @ApiModelProperty("不认可原因")
    private String reason;

    /**
     * 当事人姓名
     */
    @ApiModelProperty("当事人姓名")
    private String realname;

    /**
     * 当事人ID
     */
    @ApiModelProperty("当事人ID")
    private Integer bfuserId;

    /**
     * 入库时间
     */
    @ApiModelProperty("入库时间")
    private String zzsj;

    public Integer getZzlsbId() {
        return zzlsbId;
    }

    public void setZzlsbId(Integer zzlsbId) {
        this.zzlsbId = zzlsbId;
    }
    public Integer getAnjianId() {
        return anjianId;
    }

    public void setAnjianId(Integer anjianId) {
        this.anjianId = anjianId;
    }
    public Integer getTjyaccessId() {
        return tjyaccessId;
    }

    public void setTjyaccessId(Integer tjyaccessId) {
        this.tjyaccessId = tjyaccessId;
    }
    public Integer getFairworkerid() {
        return fairworkerid;
    }

    public void setFairworkerid(Integer fairworkerid) {
        this.fairworkerid = fairworkerid;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
    public Integer getBfuserId() {
        return bfuserId;
    }

    public void setBfuserId(Integer bfuserId) {
        this.bfuserId = bfuserId;
    }
    public String getZzsj() {
        return zzsj;
    }

    public void setZzsj(String zzsj) {
        this.zzsj = zzsj;
    }

    @Override
    protected Serializable pkVal() {
        return this.zzlsbId;
    }

    @Override
    public String toString() {
        return "Zzlsb{" +
        "zzlsbId=" + zzlsbId +
        ", anjianId=" + anjianId +
        ", tjyaccessId=" + tjyaccessId +
        ", fairworkerid=" + fairworkerid +
        ", state=" + state +
        ", reason=" + reason +
        ", realname=" + realname +
        ", bfuserId=" + bfuserId +
        ", zzsj=" + zzsj +
        "}";
    }
}
