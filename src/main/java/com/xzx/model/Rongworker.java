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
 * @since 2020-05-18
 */
public class Rongworker extends Model<Rongworker> {

    private static final long serialVersionUID = 1L;

    /**
     * 融云客服表ID
     */
    @TableId(value = "RongworkerId", type = IdType.AUTO)
    @ApiModelProperty("融云客服表ID")
    private Integer RongworkerId;

    /**
     * 服务区域ID
     */
    @ApiModelProperty("服务区域ID")
    private Integer RegionId;

    /**
     * 服务类别ID
     */
    @ApiModelProperty("服务类别ID")
    private Integer DictionariesId;

    /**
     * 机构ID
     */
    @ApiModelProperty("机构ID")
    private Integer DepartmentId;
    
    @ApiModelProperty("工作人员姓名")
    private String workName;
    
    @ApiModelProperty("工作人员手机号码")
    private String workPhone;
    
    @ApiModelProperty("工作人员联系电话")
    private String workTelephone;

	/**
     * 服务人员ID
     */
    @ApiModelProperty("服务人员ID")
    private Integer FairworkerId;
    
    @ApiModelProperty("在线离线状态")
    private String isOnline;

    public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	public Integer getRongworkerId() {
        return RongworkerId;
    }

    public void setRongworkerId(Integer RongworkerId) {
        this.RongworkerId = RongworkerId;
    }
    public Integer getRegionId() {
        return RegionId;
    }

    public void setRegionId(Integer RegionId) {
        this.RegionId = RegionId;
    }
    public Integer getDictionariesId() {
        return DictionariesId;
    }

    public void setDictionariesId(Integer DictionariesId) {
        this.DictionariesId = DictionariesId;
    }
    public Integer getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(Integer DepartmentId) {
        this.DepartmentId = DepartmentId;
    }
    public Integer getFairworkerId() {
        return FairworkerId;
    }

    public void setFairworkerId(Integer FairworkerId) {
        this.FairworkerId = FairworkerId;
    }
    
    public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

    public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getWorkTelephone() {
		return workTelephone;
	}

	public void setWorkTelephone(String workTelephone) {
		this.workTelephone = workTelephone;
	}

	@Override
    protected Serializable pkVal() {
        return this.RongworkerId;
    }

    @Override
    public String toString() {
        return "Rongworker{" +
        "RongworkerId=" + RongworkerId +
        ", RegionId=" + RegionId +
        ", DictionariesId=" + DictionariesId +
        ", DepartmentId=" + DepartmentId +
        ", FairworkerId=" + FairworkerId +
        "}";
    }
}
