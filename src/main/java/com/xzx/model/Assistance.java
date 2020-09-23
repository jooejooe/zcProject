package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 在线申办类别码表
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
public class Assistance extends Model<Assistance> {

    private static final long serialVersionUID = 1L;

    /**
     * 法律援助类型主键
     */
    @TableId(value = "assistanceId", type = IdType.AUTO)
    private Integer assistanceId;

    /**
     * 标题
     */
    @TableField("title")
    private String  title;

    /**
     * 状态 0 启用 1未启用
     */
    private Integer state;

    /**
     * 创建时间
     */
    private String createdate;

    /**
     * 在线服务各类别字典  0 援助类型 1 仲裁类别 2 鉴定类别 3 预约类型
     */
    private Integer assistancetype;

    public Integer getAssistanceId() {
        return assistanceId;
    }

    public void setAssistanceId(Integer assistanceId) {
        this.assistanceId = assistanceId;
    }
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
    public Integer getAssistancetype() {
        return assistancetype;
    }

    public void setAssistancetype(Integer assistancetype) {
        this.assistancetype = assistancetype;
    }

    @Override
    protected Serializable pkVal() {
        return this.assistanceId;
    }

    @Override
    public String toString() {
        return "Assistance{" +
        "assistanceId=" + assistanceId +
        ",  title=" +  title +
        ", state=" + state +
        ", createdate=" + createdate +
        ", assistancetype=" + assistancetype +
        "}";
    }
}
