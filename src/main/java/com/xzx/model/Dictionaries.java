package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 数据字典表
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
public class Dictionaries extends Model<Dictionaries> {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典主键ID
     */
    @TableId(value = "DictionariesId", type = IdType.AUTO)
    private Integer DictionariesId;

    /**
     * 数据模型ID
     */
    private Integer DictionariesTypeId;

    /**
     * 对应数据模型内容名称
     */
    private String DictionariesName;

    public Integer getDictionariesId() {
        return DictionariesId;
    }

    public void setDictionariesId(Integer DictionariesId) {
        this.DictionariesId = DictionariesId;
    }
    public Integer getDictionariesTypeId() {
        return DictionariesTypeId;
    }

    public void setDictionariesTypeId(Integer DictionariesTypeId) {
        this.DictionariesTypeId = DictionariesTypeId;
    }
    public String getDictionariesName() {
        return DictionariesName;
    }

    public void setDictionariesName(String DictionariesName) {
        this.DictionariesName = DictionariesName;
    }

    @Override
    protected Serializable pkVal() {
        return this.DictionariesId;
    }

    @Override
    public String toString() {
        return "Dictionaries{" +
        "DictionariesId=" + DictionariesId +
        ", DictionariesTypeId=" + DictionariesTypeId +
        ", DictionariesName=" + DictionariesName +
        "}";
    }
}
