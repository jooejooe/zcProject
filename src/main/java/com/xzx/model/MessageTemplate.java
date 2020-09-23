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
 * @since 2019-10-22
 */
public class MessageTemplate extends Model<MessageTemplate> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模板id
     */
    private Integer templateId;

    /**
     * 区域id
     */
    private Integer regionId;

    /**
     * 状态
     */
    private String state;
    
    /**
     * 需要传递的短信参数的个数
     */
    private Integer paramsCount;

	/**
     * 人员类型（工作人员，普通用户）
     */
    private String workerType;
    
    /**
     * 模板名称
     */
    private String templateName;

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }
    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    public String getWorkerType() {
        return workerType;
    }

    public void setWorkerType(String workerType) {
        this.workerType = workerType;
    }
    
    public Integer getParamsCount() {
		return paramsCount;
	}

	public void setParamsCount(Integer paramsCount) {
		this.paramsCount = paramsCount;
	}
	
    public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Messagetemplate{" +
        "id=" + id +
        ", templateId=" + templateId +
        ", regionId=" + regionId +
        ", state=" + state +
        ", workerType=" + workerType +
        ", paramsCount=" + paramsCount +
        ", templateName=" + templateName +
        "}";
    }
}
