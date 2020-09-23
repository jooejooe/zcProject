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
public class Fy_sp extends Model<Fy_sp> {

    private static final long serialVersionUID = 1L;

    /**
     * 调委会审批要件表主键ID
     */
    @TableId(value = "fy_spId", type = IdType.AUTO)
    @ApiModelProperty("调委会审批要件表主键ID")
    private Integer fy_spId;

    /**
     * 案件信息表主键ID  关联
     */
    @ApiModelProperty("案件信息表主键ID  关联")
    private Integer fy_ajId;

    /**
     * 接收_仲裁结案（撤销）反馈函
     */
    @ApiModelProperty("接收_仲裁结案（撤销）反馈函")
    private String js_tjjacgfkh;

    /**
     * 接收_人民仲裁协议书
     */
    @ApiModelProperty("接收_人民仲裁协议书")
    private String js_rmtjxys;

    /**
     * 仲裁笔录
     */
    @ApiModelProperty("仲裁笔录")
    private String js_tjbl;

    /**
     * 接受_送达手续
     */
    @ApiModelProperty("接受_送达手续")
    private String js_sdsx;

    /**
     * 转回_仲裁笔录
     */
    @ApiModelProperty("转回_仲裁笔录")
    private String zh_tjbl;

    /**
     * 转回_仲裁结案(仲裁不成功)反馈函
     */
    @ApiModelProperty("转回_仲裁结案(仲裁不成功)反馈函")
    private String zh_tjjabcgfkh;

    /**
     * 诉讼引导_仲裁笔录
     */
    @ApiModelProperty("诉讼引导_仲裁笔录")
    private String ssyd_tjbl;

    /**
     * 诉讼引导_仲裁结案(仲裁不成功)反馈函
     */
    @ApiModelProperty("诉讼引导_仲裁结案(仲裁不成功)反馈函")
    private String ssyd_tjjabcgfkh;
    
    /**
     * 进度表主键id
     */
    @ApiModelProperty("进度表主键id")
    private String speedId;
    
    /**
     * 审批状态
     */
    @ApiModelProperty("审批状态")
    private String State;

	public String getSpeedId() {
		return speedId;
	}

	public void setSpeedId(String speedId) {
		this.speedId = speedId;
	}

	public Integer getFy_spId() {
        return fy_spId;
    }

    public void setFy_spId(Integer fy_spId) {
        this.fy_spId = fy_spId;
    }
    public Integer getFy_ajId() {
        return fy_ajId;
    }

    public void setFy_ajId(Integer fy_ajId) {
        this.fy_ajId = fy_ajId;
    }
    public String getJs_tjjacgfkh() {
        return js_tjjacgfkh;
    }

    public void setJs_tjjacgfkh(String js_tjjacgfkh) {
        this.js_tjjacgfkh = js_tjjacgfkh;
    }
    public String getJs_rmtjxys() {
        return js_rmtjxys;
    }

    public void setJs_rmtjxys(String js_rmtjxys) {
        this.js_rmtjxys = js_rmtjxys;
    }
    public String getJs_tjbl() {
        return js_tjbl;
    }

    public void setJs_tjbl(String js_tjbl) {
        this.js_tjbl = js_tjbl;
    }
    public String getJs_sdsx() {
        return js_sdsx;
    }

    public void setJs_sdsx(String js_sdsx) {
        this.js_sdsx = js_sdsx;
    }
    public String getZh_tjbl() {
        return zh_tjbl;
    }

    public void setZh_tjbl(String zh_tjbl) {
        this.zh_tjbl = zh_tjbl;
    }
    public String getZh_tjjabcgfkh() {
        return zh_tjjabcgfkh;
    }

    public void setZh_tjjabcgfkh(String zh_tjjabcgfkh) {
        this.zh_tjjabcgfkh = zh_tjjabcgfkh;
    }
    public String getSsyd_tjbl() {
        return ssyd_tjbl;
    }

    public void setSsyd_tjbl(String ssyd_tjbl) {
        this.ssyd_tjbl = ssyd_tjbl;
    }
    public String getSsyd_tjjabcgfkh() {
        return ssyd_tjjabcgfkh;
    }

    public void setSsyd_tjjabcgfkh(String ssyd_tjjabcgfkh) {
        this.ssyd_tjjabcgfkh = ssyd_tjjabcgfkh;
    }
    
    public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

    @Override
    protected Serializable pkVal() {
        return this.fy_spId;
    }

    @Override
    public String toString() {
        return "Fy_sp{" +
        "fy_spId=" + fy_spId +
        ", fy_ajId=" + fy_ajId +
        ", js_tjjacgfkh=" + js_tjjacgfkh +
        ", js_rmtjxys=" + js_rmtjxys +
        ", js_tjbl=" + js_tjbl +
        ", js_sdsx=" + js_sdsx +
        ", zh_tjbl=" + zh_tjbl +
        ", zh_tjjabcgfkh=" + zh_tjjabcgfkh +
        ", ssyd_tjbl=" + ssyd_tjbl +
        ", ssyd_tjjabcgfkh=" + ssyd_tjjabcgfkh +
        "}";
    }
}
