package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  仲裁员证据库
 * </p>
 *
 * @author Helen
 * @since 2020-08-25
 */
public class Tjy_access extends Model<Tjy_access> {

    private static final long serialVersionUID = 1L;

    /**
     * 仲裁员证据库
     */
    @TableId(value = "tjy_accessId", type = IdType.AUTO)
    @ApiModelProperty("仲裁员证据库id")
    private Integer tjy_accessId;

    /**
     * 案件ID
     */
    @ApiModelProperty("案件ID")
    private Integer anjianId;

    /**
     * 证据路径
     */
    @ApiModelProperty("证据路径")
    private String accessurl;

    /**
     * 上传仲裁员ID
     */
    @ApiModelProperty("上传仲裁员ID")
    private Integer fairworkerId;

    /**
     * 上传时间
     */
    @ApiModelProperty("上传时间")
    private String createdate;

    /**
     * 当事人或对方当事人ID
     */
    @ApiModelProperty("当事人或对方当事人ID")
    private Integer userId;

    /**
     * 用户类别  0 当事人  1 对方当事人  2 仲裁员
     */
    @ApiModelProperty("用户类别  0 当事人  1 对方当事人  2 仲裁员")
    private Integer usertype;

    /**
     * 证据名称
     */
    @ApiModelProperty("证据名称")
    private String accessname;

    /**
     * 附件id
     */
    @ApiModelProperty("附件id")
    private Integer accessid;
    
    /**
     * 质证确认历史记录
     */
    private List<String> confirmList;

	public Integer getTjy_accessId() {
        return tjy_accessId;
    }

    public void setTjy_accessId(Integer tjy_accessId) {
        this.tjy_accessId = tjy_accessId;
    }
    public Integer getAnjianId() {
        return anjianId;
    }

    public void setAnjianId(Integer anjianId) {
        this.anjianId = anjianId;
    }
    public String getAccessurl() {
        return accessurl;
    }

    public void setAccessurl(String accessurl) {
        this.accessurl = accessurl;
    }
    public Integer getFairworkerId() {
        return fairworkerId;
    }

    public void setFairworkerId(Integer fairworkerId) {
        this.fairworkerId = fairworkerId;
    }
    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }
    public String getAccessname() {
        return accessname;
    }

    public void setAccessname(String accessname) {
        this.accessname = accessname;
    }
    public Integer getAccessid() {
        return accessid;
    }

    public void setAccessid(Integer accessid) {
        this.accessid = accessid;
    }

    public List<String> getConfirmList() {
		return confirmList;
	}

	public void setConfirmList(List<String> confirmList) {
		this.confirmList = confirmList;
	}

    @Override
    protected Serializable pkVal() {
        return this.tjy_accessId;
    }

    @Override
    public String toString() {
        return "Tjy_access{" +
        "tjy_accessId=" + tjy_accessId +
        ", anjianId=" + anjianId +
        ", accessurl=" + accessurl +
        ", fairworkerId=" + fairworkerId +
        ", createdate=" + createdate +
        ", userId=" + userId +
        ", usertype=" + usertype +
        ", accessname=" + accessname +
        ", accessid=" + accessid +
        "}";
    }
}
