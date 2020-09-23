package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 使用地
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public class Useplace extends Model<Useplace> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "UsePlaceId", type = IdType.AUTO)
    private Integer UsePlaceId;

    /**
     * 使用地标题
     */
    private String Title;

    /**
     * 启用状态 0 启用 1未启用
     */
    private Integer State;

    /**
     * 创建时间
     */
    private String CreateDate;

    public Integer getUsePlaceId() {
        return UsePlaceId;
    }

    public void setUsePlaceId(Integer UsePlaceId) {
        this.UsePlaceId = UsePlaceId;
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
        return this.UsePlaceId;
    }

    @Override
    public String toString() {
        return "Useplace{" +
        "UsePlaceId=" + UsePlaceId +
        ", Title=" + Title +
        ", State=" + State +
        ", CreateDate=" + CreateDate +
        "}";
    }
}
