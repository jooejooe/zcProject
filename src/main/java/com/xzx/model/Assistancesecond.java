package com.xzx.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 在线申办类别码表
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
public class Assistancesecond extends Model<Assistancesecond> {

    private static final long serialVersionUID = 1L;

    /**
     * 二级类型主键
     */
    @TableId(value = "asssecid", type = IdType.AUTO)
    private Integer asssecid;

    /**
     * 名称
     */
    @TableField("assname")
    private String  assname;

    /**
     * 状态 0 启用 1未启用
     */
    private Integer state;

    /**
     * 创建时间
     */
    private String createtime;

    /**
     * 父级id
     */
    private Integer parentid;


    @Override
    protected Serializable pkVal() {
        return this.asssecid;
    }

    @Override
    public String toString() {
        return "Assistance{" +
        "assistanceId=" + asssecid +
        ",  title=" +  assname +
        ", state=" + state +
        ", createdate=" + createtime +
        ", assistancetype=" + parentid +
        "}";
    }

    public Integer getAsssecid() {
        return asssecid;
    }

    public void setAsssecid(Integer asssecid) {
        this.asssecid = asssecid;
    }

    public String getAssname() {
        return assname;
    }

    public void setAssname(String assname) {
        this.assname = assname;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
