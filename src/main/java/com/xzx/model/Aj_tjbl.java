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
 * @since 2020-08-27
 */
public class Aj_tjbl extends Model<Aj_tjbl> {

    private static final long serialVersionUID = 1L;

    /**
     * 案件仲裁笔录ID
     */
    @TableId(value = "anjian_tjbl_Id", type = IdType.AUTO)
    @ApiModelProperty("案件仲裁笔录ID")
    private Integer anjian_tjbl_Id;

    /**
     * 案件ID
     */
    @ApiModelProperty("案件ID")
    private Integer anjianId;

    /**
     * 当事人或对方当事人ID
     */
    @ApiModelProperty("当事人或对方当事人ID")
    private Integer userId;

    /**
     * 仲裁笔录内容
     */
    @ApiModelProperty("仲裁笔录内容")
    private String tjblinfo;

    /**
     * 记录时间
     */
    @ApiModelProperty("记录时间")
    private String createdate;

    /**
     * 仲裁员ID
     */
    @ApiModelProperty("仲裁员ID")
    private Integer fairworkerId;
    
    @ApiModelProperty("0 不认可  1 认可")
    private Integer state;
    
    @ApiModelProperty("不认可原因")
    private String reason;

    public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getAnjian_tjbl_Id() {
        return anjian_tjbl_Id;
    }

    public void setAnjian_tjbl_Id(Integer anjian_tjbl_Id) {
        this.anjian_tjbl_Id = anjian_tjbl_Id;
    }
    public Integer getAnjianId() {
        return anjianId;
    }

    public void setAnjianId(Integer anjianId) {
        this.anjianId = anjianId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getTjblinfo() {
        return tjblinfo;
    }

    public void setTjblinfo(String tjblinfo) {
        this.tjblinfo = tjblinfo;
    }
    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
    public Integer getFairworkerId() {
        return fairworkerId;
    }

    public void setFairworkerId(Integer fairworkerId) {
        this.fairworkerId = fairworkerId;
    }

    @Override
    protected Serializable pkVal() {
        return this.anjian_tjbl_Id;
    }

    @Override
    public String toString() {
        return "Aj_tjbl{" +
        "anjian_tjbl_Id=" + anjian_tjbl_Id +
        ", anjianId=" + anjianId +
        ", userId=" + userId +
        ", tjblinfo=" + tjblinfo +
        ", createdate=" + createdate +
        ", fairworkerId=" + fairworkerId +
        "}";
    }
}
