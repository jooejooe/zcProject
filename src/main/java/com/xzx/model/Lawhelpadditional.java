package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 【法律援助】申请时选择相应援助类别，对应各题目的选项内容
 * </p>
 *
 * @author Helen
 * @since 2019-10-09
 */
public class Lawhelpadditional extends Model<Lawhelpadditional> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 法律援助业务表id
     */
    private Integer lawhelpId;

    /**
     * 每种类型选择的选项或输入的内容拼接字符串，以逗号分隔
     */
    private String options;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getLawhelpId() {
        return lawhelpId;
    }

    public void setLawhelpId(Integer lawhelpId) {
        this.lawhelpId = lawhelpId;
    }
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Lawhelpadditional{" +
        "id=" + id +
        ", lawhelpId=" + lawhelpId +
        ", options=" + options +
        "}";
    }
}
