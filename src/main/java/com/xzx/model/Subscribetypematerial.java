package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Helen
 * @since 2019-10-30
 */
public class Subscribetypematerial extends Model<Subscribetypematerial> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 预约类型id
     */
    private Integer subscribeTypeId;

    /**
     * 所需资料,以逗号分隔
     */
    private String DatasInfo;

    /**
     * 所需材料个数
     */
    private Integer DatasNumber;

    /**
     * 状态 0 启用 1未启用
     */
    private Integer State;

    /**
     * 创建时间
     */
    private String CreateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getSubscribeTypeId() {
        return subscribeTypeId;
    }

    public void setSubscribeTypeId(Integer subscribeTypeId) {
        this.subscribeTypeId = subscribeTypeId;
    }
    public String getDatasInfo() {
        return DatasInfo;
    }

    public void setDatasInfo(String DatasInfo) {
        this.DatasInfo = DatasInfo;
    }
    public Integer getDatasNumber() {
        return DatasNumber;
    }

    public void setDatasNumber(Integer DatasNumber) {
        this.DatasNumber = DatasNumber;
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
        return this.id;
    }

    @Override
    public String toString() {
        return "Subscribetypematerial{" +
        "id=" + id +
        ", subscribeTypeId=" + subscribeTypeId +
        ", DatasInfo=" + DatasInfo +
        ", DatasNumber=" + DatasNumber +
        ", State=" + State +
        ", CreateDate=" + CreateDate +
        "}";
    }
}
