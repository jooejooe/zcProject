package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 消息
 * </p>
 *
 * @author Helen
 * @since 2019-09-27
 */
public class Message extends Model<Message> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "MessageId", type = IdType.AUTO)
    private Integer MessageId;

    /**
     * 消息标题
     */
    private String Title;

    /**
     * 推送类型 0 群体推送 1 单独推送
     */
    private Integer SendType;

    /**
     * 消息封面
     */
    private String MessageCover;

    /**
     * 推送消息附件
     */
    private String MessageSendAccress;

    /**
     * 消息内容
     */
    private String MessageContext;

    /**
     * 推送的消息的人员
     */
    private String UserId;

    /**
     * 推送时间
     */
    private String CreateDate;

    /**
     * 推送消息人员的类别 0 普通 1 工作人员 2 矫正人员 3 全部
     */
    private Integer SendUserType;

    /**
     * 当前操作人员
     */
    private Integer CreateUser;
    
    private String CreateUserName;

	public Integer getMessageId() {
        return MessageId;
    }

    public void setMessageId(Integer MessageId) {
        this.MessageId = MessageId;
    }
    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    public Integer getSendType() {
        return SendType;
    }

    public void setSendType(Integer SendType) {
        this.SendType = SendType;
    }
    public String getMessageCover() {
        return MessageCover;
    }

    public void setMessageCover(String MessageCover) {
        this.MessageCover = MessageCover;
    }
    public String getMessageSendAccress() {
        return MessageSendAccress;
    }

    public void setMessageSendAccress(String MessageSendAccress) {
        this.MessageSendAccress = MessageSendAccress;
    }
    public String getMessageContext() {
        return MessageContext;
    }

    public void setMessageContext(String MessageContext) {
        this.MessageContext = MessageContext;
    }
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }
    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }
    public Integer getSendUserType() {
        return SendUserType;
    }

    public void setSendUserType(Integer SendUserType) {
        this.SendUserType = SendUserType;
    }
    public Integer getCreateUser() {
        return CreateUser;
    }

    public void setCreateUser(Integer CreateUser) {
        this.CreateUser = CreateUser;
    }
    
    public String getCreateUserName() {
		return CreateUserName;
	}

	public void setCreateUserName(String createUserName) {
		CreateUserName = createUserName;
	}

    @Override
    protected Serializable pkVal() {
        return this.MessageId;
    }

    @Override
    public String toString() {
        return "Message{" +
        "MessageId=" + MessageId +
        ", Title=" + Title +
        ", SendType=" + SendType +
        ", MessageCover=" + MessageCover +
        ", MessageSendAccress=" + MessageSendAccress +
        ", MessageContext=" + MessageContext +
        ", UserId=" + UserId +
        ", CreateDate=" + CreateDate +
        ", SendUserType=" + SendUserType +
        ", CreateUser=" + CreateUser +
        "}";
    }
}
