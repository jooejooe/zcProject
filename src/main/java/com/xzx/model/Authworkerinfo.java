package com.xzx.model;

import com.baomidou.mybatisplus.activerecord.Model;
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
public class Authworkerinfo extends Model<Authworkerinfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 仲裁单位id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 工作人员id,用","分隔
     */
    @ApiModelProperty("工作人员id,用\",\"分隔")
    private String workerids;

    /**
     * 案件id
     */
    @ApiModelProperty("案件id")
    private Integer authId;


    @Override
    public String toString() {
        return "Zcdw{" +
        "id=" + id +
        ", workerids=" + workerids +
        ", authId=" + authId +
        "}";
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkerids() {
        return workerids;
    }

    public void setWorkerids(String workerids) {
        this.workerids = workerids;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
}
