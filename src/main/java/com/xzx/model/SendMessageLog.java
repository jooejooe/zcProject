package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Helen
 * @since 2019-10-21
 */
public class SendMessageLog extends Model<SendMessageLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 模板id
     */
    private Integer templateId;

    /**
     * 0-发送成功,1-发送失败
     */
    private Integer state;

    /**
     * 手机号码
     */
    private String telephone;

    /**
     * 发送参数
     */
    private String params;

    /**
     * 区域id
     */
    private Integer regionId;

    /**
     * 腾讯云返回的错误消息
     */
    private String errorMsg;

    /**
     * 发送时间
     */
    private Date sendTime;
    
    /**
     * 短信类型（0-通知类；1-验证码）
     */
    private int messageType;

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
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    
    public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Sendmessagelog{" +
        "id=" + id +
        ", templateId=" + templateId +
        ", state=" + state +
        ", telephone=" + telephone +
        ", params=" + params +
        ", regionId=" + regionId +
        ", errorMsg=" + errorMsg +
        ", sendTime=" + sendTime +
        ", messageType=" + messageType +
        "}";
    }
}
