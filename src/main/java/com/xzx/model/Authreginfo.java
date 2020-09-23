package com.xzx.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 当事人列表中间表(与authentic关联的userid)
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public class Authreginfo extends Model<Authreginfo> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "authregId", type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Integer authregId;

    /**
     * 关联的当事人id字符串,用","分隔
     */
    @ApiModelProperty("关联的当事人id字符串,用\",\"分隔")
    private String registerId;

    /**
     * 双方id
     */
    @ApiModelProperty("双方id")
    private Integer sfid;

    /**
     * 是否是单位
     */
    @ApiModelProperty("是否是单位")
    private Integer isDw;

    /**
     * 单位id
     */
    @ApiModelProperty("单位id")
    private Integer dwId;

    /**
     * 代表id
     */
    @ApiModelProperty("代表id")
    private Integer dbId;

    @Override
    protected Serializable pkVal() {
        return this.authregId;
    }
    @Override
    public String toString() {
        return "Authreginfo{" +
                "authregId=" + authregId +
                ", registerId=" + registerId +
                ", sfid=" + sfid +
                ", isDw=" + isDw +
                ", dwId=" + dwId +
                ", dbId=" + dbId +
                "}";
    }

    public Integer getAuthregId() {
        return authregId;
    }

    public void setAuthregId(Integer authregId) {
        this.authregId = authregId;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }

    public Integer getSfid() {
        return sfid;
    }

    public void setSfid(Integer sfid) {
        this.sfid = sfid;
    }

    public Integer getIsDw() {
        return isDw;
    }

    public void setIsDw(Integer isDw) {
        this.isDw = isDw;
    }

    public Integer getDwId() {
        return dwId;
    }

    public void setDwId(Integer dwId) {
        this.dwId = dwId;
    }

    public Integer getDbId() {
        return dbId;
    }

    public void setDbId(Integer dbId) {
        this.dbId = dbId;
    }
}
