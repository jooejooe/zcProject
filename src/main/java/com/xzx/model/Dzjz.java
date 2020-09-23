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
public class Dzjz extends Model<Dzjz> {

    private static final long serialVersionUID = 1L;

    /**
     * 电子卷宗主键ID
     */
    @TableId(value = "jzId", type = IdType.AUTO)
    @ApiModelProperty("电子卷宗主键ID")
    private Integer jzId;

    /**
     * 类别 0 公证 1 仲裁 2 援助 3 鉴定 4律师
     */
    @ApiModelProperty("类别 0 公证 1 仲裁 2 援助 3 鉴定 4律师")
    private Integer type;

    /**
     * 案件ID主键
     */
    @ApiModelProperty("案件ID主键")
    private Integer ajId;

    /**
     * 卷宗编号
     */
    @ApiModelProperty("卷宗编号")
    private String bh;

    /**
     * 工作人员主键ID  创建人
     */
    @ApiModelProperty("工作人员主键ID  创建人")
    private Integer cjr;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String cjsj;
    
    @ApiModelProperty("附件地址")
    private String accessurl;
    
    @ApiModelProperty("电子卷宗类别")
    private String jztyle;

    public Integer getJzId() {
        return jzId;
    }

    public void setJzId(Integer jzId) {
        this.jzId = jzId;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getAjId() {
        return ajId;
    }

    public void setAjId(Integer ajId) {
        this.ajId = ajId;
    }
    public Integer getCjr() {
        return cjr;
    }

    public void setCjr(Integer cjr) {
        this.cjr = cjr;
    }
    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    @Override
    protected Serializable pkVal() {
        return this.jzId;
    }

    @Override
    public String toString() {
        return "Dzjz{" +
        "jzId=" + jzId +
        ", type=" + type +
        ", ajId=" + ajId +
        ", bh=" + bh +
        ", cjr=" + cjr +
        ", cjsj=" + cjsj +
        "}";
    }

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getAccessurl() {
		return accessurl;
	}

	public void setAccessurl(String accessurl) {
		this.accessurl = accessurl;
	}

	public String getJztyle() {
		return jztyle;
	}

	public void setJztyle(String jztyle) {
		this.jztyle = jztyle;
	}
}
