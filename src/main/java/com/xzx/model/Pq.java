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
 * @since 2020-04-24
 */
public class Pq extends Model<Pq> {

    private static final long serialVersionUID = 1L;

    /**
     * 排期主键ID
     */
    @TableId(value = "pqId", type = IdType.AUTO)
    @ApiModelProperty("排期主键ID")
    private Integer pqId;

    /**
     * 案件ID主键
     */
    @ApiModelProperty("案件ID主键")
    private Integer  ajId;

    /**
     * 排期开始日期
     */
    @ApiModelProperty("排期开始日期")
    private String pqstart;

    /**
     * 排期结束日期
     */
    @ApiModelProperty("排期结束日期")
    private String pqend;

    /**
     * 排期人 工作人员主键ID
     */
    @ApiModelProperty("排期人 工作人员主键ID")
    private Integer fairworkerId;
    
	@ApiModelProperty("当前进度id")
	private String speedId;
	
	@ApiModelProperty("排期对应speed表状态值")
	private String speedState;

	public Integer getAjId() {
		return ajId;
	}

	public void setAjId(Integer ajId) {
		this.ajId = ajId;
	}

	public String getSpeedId() {
		return speedId;
	}

	public void setSpeedId(String speedId) {
		this.speedId = speedId;
	}

	public Integer getPqId() {
        return pqId;
    }

    public void setPqId(Integer pqId) {
        this.pqId = pqId;
    }

    public String getPqstart() {
        return pqstart;
    }

    public void setPqstart(String pqstart) {
        this.pqstart = pqstart;
    }
    public String getPqend() {
        return pqend;
    }

    public void setPqend(String pqend) {
        this.pqend = pqend;
    }
    public Integer getFairworkerId() {
        return fairworkerId;
    }

    public void setFairworkerId(Integer fairworkerId) {
        this.fairworkerId = fairworkerId;
    }
    
    public String getSpeedState() {
		return speedState;
	}

	public void setSpeedState(String speedState) {
		this.speedState = speedState;
	}

    @Override
    protected Serializable pkVal() {
        return this.pqId;
    }

    @Override
    public String toString() {
        return "Pq{" +
        "pqId=" + pqId +
        ",  ajId=" +  ajId +
        ", pqstart=" + pqstart +
        ", pqend=" + pqend +
        ", fairworkerId=" + fairworkerId +
        "}";
    }
}
