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
 * @since 2019-10-09
 */
public class Lawparentnext extends Model<Lawparentnext> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    @TableId(value = "lawParentNextId", type = IdType.AUTO)
    private Integer lawParentNextId;

    /**
     * 法律援助类别细类主键关联
     */
    @ApiModelProperty("援助类别对应题目id")
    private Integer lawParentId;

    /**
     * 名称(答案)
     */
    @ApiModelProperty("选项内容")
    private String values;

    public Integer getLawParentNextId() {
        return lawParentNextId;
    }

    public void setLawParentNextId(Integer lawParentNextId) {
        this.lawParentNextId = lawParentNextId;
    }
    public Integer getLawParentId() {
        return lawParentId;
    }

    public void setLawParentId(Integer lawParentId) {
        this.lawParentId = lawParentId;
    }
    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    @Override
    protected Serializable pkVal() {
        return this.lawParentNextId;
    }

    @Override
    public String toString() {
        return "Lawparentnext{" +
        "lawParentNextId=" + lawParentNextId +
        ", lawParentId=" + lawParentId +
        ", values=" + values +
        "}";
    }
}
