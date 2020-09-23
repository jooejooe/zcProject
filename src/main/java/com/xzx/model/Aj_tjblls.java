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
public class Aj_tjblls extends Model<Aj_tjblls> {

    private static final long serialVersionUID = 1L;

    /**
     * 案件的临时仲裁笔录存储主键ID
     */
    @TableId(value = "aj_tjblls_Id", type = IdType.AUTO)
    @ApiModelProperty("案件的临时仲裁笔录存储主键ID")
    private Integer aj_tjblls_Id;

    /**
     * 笔录内容
     */
    @ApiModelProperty("笔录内容")
    private String blnr;

    /**
     * 案件ID
     */
    @ApiModelProperty("案件ID")
    private Integer anjianId;

    /**
     * 接收人ID
     */
    @ApiModelProperty("接收人ID")
    private Integer reciveuserId;

    public Integer getAj_tjblls_Id() {
        return aj_tjblls_Id;
    }

    public void setAj_tjblls_Id(Integer aj_tjblls_Id) {
        this.aj_tjblls_Id = aj_tjblls_Id;
    }
    public String getBlnr() {
        return blnr;
    }

    public void setBlnr(String blnr) {
        this.blnr = blnr;
    }
    public Integer getAnjianId() {
        return anjianId;
    }

    public void setAnjianId(Integer anjianId) {
        this.anjianId = anjianId;
    }
    public Integer getReciveuserId() {
        return reciveuserId;
    }

    public void setReciveuserId(Integer reciveuserId) {
        this.reciveuserId = reciveuserId;
    }

    @Override
    protected Serializable pkVal() {
        return this.aj_tjblls_Id;
    }

    @Override
    public String toString() {
        return "Aj_tjblls{" +
        "aj_tjblls_Id=" + aj_tjblls_Id +
        ", blnr=" + blnr +
        ", anjianId=" + anjianId +
        ", reciveuserId=" + reciveuserId +
        "}";
    }
}
