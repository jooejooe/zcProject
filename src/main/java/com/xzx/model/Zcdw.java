package com.xzx.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 相关附件
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public class Zcdw extends Model<Zcdw> {

    private static final long serialVersionUID = 1L;

    /**
     * 仲裁单位id
     */
    @TableId(value = "zcdwId", type = IdType.AUTO)
    @ApiModelProperty("仲裁单位id")
    private Integer zcdwId;

    /**
     * 单位名称
     */
    @ApiModelProperty("单位名称")
    private String dwmc;

    /**
     * 法定代表人
     */
    @ApiModelProperty("法定代表人")
    private String fddbr;

    /**
     * 职务
     */
    @ApiModelProperty("职务")
    private String zw;

    /**
     * 地址信息
     */
    @ApiModelProperty("地址信息")
    private String dzxx;
    
    /**
     * 仲裁请求
     */
    @ApiModelProperty("仲裁请求")
    private String zcqq;

    public Integer getZcdwId() {
        return zcdwId;
    }

    public void setZcdwId(Integer zcdwId) {
        this.zcdwId = zcdwId;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getFddbr() {
        return fddbr;
    }

    public void setFddbr(String fddbr) {
        this.fddbr = fddbr;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getDzxx() {
        return dzxx;
    }

    public void setDzxx(String dzxx) {
        this.dzxx = dzxx;
    }

    public String getZcqq() {
        return zcqq;
    }

    public void setZcqq(String zcqq) {
        this.zcqq = zcqq;
    }

    @Override
    public String toString() {
        return "Zcdw{" +
        "zcdwId=" + zcdwId +
        ", dwmc=" + dwmc +
        ", fddbr=" + fddbr +
        ", zw=" + zw +
        ", dzxx=" + dzxx +
        ", zcqq=" + zcqq +
        "}";
    }

    @Override
    protected Serializable pkVal() {
        return this.zcdwId;
    }
}
