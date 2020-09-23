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
 * @since 2020-08-26
 */
public class Access_tjyupload extends Model<Access_tjyupload> {

    private static final long serialVersionUID = 1L;

    /**
     * 附件ID
     */
    @TableId(value = "access_tjyId", type = IdType.AUTO)
    @ApiModelProperty("附件ID")
    private Integer access_tjyId;

    /**
     * 服务器返回附件路径
     */
    @ApiModelProperty("服务器返回附件路径")
    private String accessurl;

    @ApiModelProperty("工作人员id")
    private Integer fairworkerId;

    /**
     * 附件名称
     */
    @ApiModelProperty("附件名称")
    private String accessname;

    /**
     * 案件ID
     */
    @ApiModelProperty("案件ID")
    private Integer anjianId;

    public Integer getAccess_tjyId() {
        return access_tjyId;
    }

    public void setAccess_tjyId(Integer access_tjyId) {
        this.access_tjyId = access_tjyId;
    }
    public String getAccessurl() {
        return accessurl;
    }

    public void setAccessurl(String accessurl) {
        this.accessurl = accessurl;
    }
    public Integer getFairworkerId() {
        return fairworkerId;
    }

    public void setFairworkerId(Integer fairworkerId) {
        this.fairworkerId = fairworkerId;
    }
    public String getAccessname() {
        return accessname;
    }

    public void setAccessname(String accessname) {
        this.accessname = accessname;
    }
    public Integer getAnjianId() {
        return anjianId;
    }

    public void setAnjianId(Integer anjianId) {
        this.anjianId = anjianId;
    }

    @Override
    protected Serializable pkVal() {
        return this.access_tjyId;
    }

    @Override
    public String toString() {
        return "Access_tjyupload{" +
        "access_tjyId=" + access_tjyId +
        ", accessurl=" + accessurl +
        ", fairworkerId=" + fairworkerId +
        ", accessname=" + accessname +
        ", anjianId=" + anjianId +
        "}";
    }
}
