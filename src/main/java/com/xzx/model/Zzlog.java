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
public class Zzlog extends Model<Zzlog> {

    private static final long serialVersionUID = 1L;

    /**
     * 质证记录主键ID
     */
    @TableId(value = "zzlogId", type = IdType.AUTO)
    @ApiModelProperty("质证记录主键ID")
    private Integer zzlogId;

    /**
     * 仲裁员ID
     */
    @ApiModelProperty("仲裁员ID")
    private Integer fairworkerId;

    /**
     * 证据接收方用户ID
     */
    @ApiModelProperty("证据接收方用户ID")
    private Integer reciveuserId;

    /**
     * 案件ID
     */
    @ApiModelProperty("案件ID")
    private Integer anjianId;

    /**
     * 证据库主键ID
     */
    @ApiModelProperty("证据库主键ID")
    private Integer tjy_accessId;

    /**
     * 质证状态  0 不认可  1 认可
     */
    @ApiModelProperty("质证状态  0 不认可  1 认可")
    private Integer zjstate;

    /**
     * 质证时间
     */
    @ApiModelProperty("质证时间")
    private String zzsj;

    /**
     * 质证时对证据的不认可原因
     */
    @ApiModelProperty("质证时对证据的不认可原因")
    private String reason;

    /**
     * 仲裁员推送证据时间
     */
    @ApiModelProperty("仲裁员推送证据时间")
    private String posttime;

    public Integer getZzlogId() {
        return zzlogId;
    }

    public void setZzlogId(Integer zzlogId) {
        this.zzlogId = zzlogId;
    }
    public Integer getFairworkerId() {
        return fairworkerId;
    }

    public void setFairworkerId(Integer fairworkerId) {
        this.fairworkerId = fairworkerId;
    }
    public Integer getReciveuserId() {
        return reciveuserId;
    }

    public void setReciveuserId(Integer reciveuserId) {
        this.reciveuserId = reciveuserId;
    }
    public Integer getAnjianId() {
        return anjianId;
    }

    public void setAnjianId(Integer anjianId) {
        this.anjianId = anjianId;
    }
    public Integer getTjy_accessId() {
        return tjy_accessId;
    }

    public void setTjy_accessId(Integer tjy_accessId) {
        this.tjy_accessId = tjy_accessId;
    }
    public Integer getZjstate() {
        return zjstate;
    }

    public void setZjstate(Integer zjstate) {
        this.zjstate = zjstate;
    }
    public String getZzsj() {
        return zzsj;
    }

    public void setZzsj(String zzsj) {
        this.zzsj = zzsj;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public String getPosttime() {
        return posttime;
    }

    public void setPosttime(String posttime) {
        this.posttime = posttime;
    }

    @Override
    protected Serializable pkVal() {
        return this.zzlogId;
    }

    @Override
    public String toString() {
        return "Zzlog{" +
        "zzlogId=" + zzlogId +
        ", fairworkerId=" + fairworkerId +
        ", reciveuserId=" + reciveuserId +
        ", anjianId=" + anjianId +
        ", tjy_accessId=" + tjy_accessId +
        ", zjstate=" + zjstate +
        ", zzsj=" + zzsj +
        ", reason=" + reason +
        ", posttime=" + posttime +
        "}";
    }
}
