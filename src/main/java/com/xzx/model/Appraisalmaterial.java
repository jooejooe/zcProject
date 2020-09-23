package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 司法鉴定所需资料
 * </p>
 *
 * @author Helen
 * @since 2019-09-26
 */
public class Appraisalmaterial extends Model<Appraisalmaterial> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "appraisalmaterialId", type = IdType.AUTO)
    private Integer appraisalmaterialId;

    /**
     * 在线维护数据字典表，关联其主键ID
     */
    private Integer assistanceId;

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

    public Integer getAppraisalmaterialId() {
        return appraisalmaterialId;
    }

    public void setAppraisalmaterialId(Integer appraisalmaterialId) {
        this.appraisalmaterialId = appraisalmaterialId;
    }
    public Integer getAssistanceId() {
        return assistanceId;
    }

    public void setAssistanceId(Integer assistanceId) {
        this.assistanceId = assistanceId;
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
        return this.appraisalmaterialId;
    }

    @Override
    public String toString() {
        return "Appraisalmaterial{" +
        "appraisalmaterialId=" + appraisalmaterialId +
        ", assistanceId=" + assistanceId +
        ", DatasInfo=" + DatasInfo +
        ", DatasNumber=" + DatasNumber +
        ", State=" + State +
        ", CreateDate=" + CreateDate +
        "}";
    }
}
