package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 申办进度历史记录
 * </p>
 *
 * @author Helen
 * @since 2019-09-30
 */
public class Speedhistory extends Model<Speedhistory> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "SpeedHistoryId", type = IdType.AUTO)
    private Integer SpeedHistoryId;

    /**
     * 办理类别
     */
    private Integer ModelType;

    /**
     * 办理事项的具体ID
     */
    private Integer ModelId;

    /**
     * 进度状态时间
     */
    private String CreateDate;

    /**
     * 当前状态 
			在线办理：0 待审批  1 审批中 2 审核通过 3 补充材料 4 已撤销  5 未评价 6 已评价 7 未通过;
			预约：0 待确定 1 预约成功 2 预约失败 3 更改预约时间
     */
    private Integer State;

    /**
     * 拒绝原因
     */
    private String refuseReason;

    /**
     * 补充原因
     */
    private String addReason;

    /**
     * 0：非常满意，1：满意 2：一般 3：不满意
     */
    private Integer evaluateType;

    /**
     * 评价内容
     */
    private String evaluateContext;

    /**
     * fairworker表主键
     */
    private Integer fairworkerId;

    /**
     * register表Id
     */
    private Integer registerId;
    
    /**
     * 撤销原因
     */
    private String cancelReason;

    @ApiModelProperty("申请方ids")
    private Integer sqfinfoId;

    @ApiModelProperty("被申请方ids")
    private Integer bsqfinfoId;

    public Integer getSqfinfoId() {
        return sqfinfoId;
    }

    public void setSqfinfoId(Integer sqfinfoId) {
        this.sqfinfoId = sqfinfoId;
    }

    public Integer getBsqfinfoId() {
        return bsqfinfoId;
    }

    public void setBsqfinfoId(Integer bsqfinfoId) {
        this.bsqfinfoId = bsqfinfoId;
    }

    public Integer getSpeedHistoryId() {
        return SpeedHistoryId;
    }

    public void setSpeedHistoryId(Integer SpeedHistoryId) {
        this.SpeedHistoryId = SpeedHistoryId;
    }
    public Integer getModelType() {
        return ModelType;
    }

    public void setModelType(Integer ModelType) {
        this.ModelType = ModelType;
    }
    public Integer getModelId() {
        return ModelId;
    }

    public void setModelId(Integer ModelId) {
        this.ModelId = ModelId;
    }
    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }
    public Integer getState() {
        return State;
    }

    public void setState(Integer State) {
        this.State = State;
    }
    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }
    public String getAddReason() {
        return addReason;
    }

    public void setAddReason(String addReason) {
        this.addReason = addReason;
    }
    public Integer getEvaluateType() {
        return evaluateType;
    }

    public void setEvaluateType(Integer evaluateType) {
        this.evaluateType = evaluateType;
    }
    public String getEvaluateContext() {
        return evaluateContext;
    }

    public void setEvaluateContext(String evaluateContext) {
        this.evaluateContext = evaluateContext;
    }
    public Integer getFairworkerId() {
        return fairworkerId;
    }

    public void setFairworkerId(Integer fairworkerId) {
        this.fairworkerId = fairworkerId;
    }
    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }
    
	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

    @Override
    protected Serializable pkVal() {
        return this.SpeedHistoryId;
    }

    @Override
    public String toString() {
        return "Speedhistory{" +
        "SpeedHistoryId=" + SpeedHistoryId +
        ", ModelType=" + ModelType +
        ", ModelId=" + ModelId +
        ", CreateDate=" + CreateDate +
        ", State=" + State +
        ", refuseReason=" + refuseReason +
        ", addReason=" + addReason +
        ", evaluateType=" + evaluateType +
        ", evaluateContext=" + evaluateContext +
        ", fairworkerId=" + fairworkerId +
        ", registerId=" + registerId +
        ", cancelReason=" + cancelReason +
        "}";
    }
}
