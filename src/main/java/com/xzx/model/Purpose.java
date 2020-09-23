package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 公证用途
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public class Purpose extends Model<Purpose> {

    private static final long serialVersionUID = 1L;

    /**
     * 公正用途主键
     */
    @TableId(value = "PurposeId", type = IdType.AUTO)
    private Integer PurposeId;

    /**
     * 标题
     */
    private String Title;

    /**
     * 启用状态 0 未启用 1启用
     */
    private Integer State;

    private String CreateDate;

    public Integer getPurposeId() {
        return PurposeId;
    }

    public void setPurposeId(Integer PurposeId) {
        this.PurposeId = PurposeId;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
    }
    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.PurposeId;
    }

    @Override
    public String toString() {
        return "Purpose{" +
        "PurposeId=" + PurposeId +
        ", Title=" + Title +
        ", State=" + State +
        ", CreateDate=" + CreateDate +
        "}";
    }
}
