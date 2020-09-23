package com.xzx.model;

import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModelProperty;

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
 * @since 2020-08-26
 */
public class Wdmeeting extends Model<Wdmeeting> {

    private static final long serialVersionUID = 1L;

    /**
     * 网动会议室主键ID
     */
    @TableId(value = "wdmeetingId", type = IdType.AUTO)
    @ApiModelProperty("网动会议室主键ID")
    private Integer wdmeetingId;

    /**
     * 会议室ID
     */
    @ApiModelProperty("会议室ID")
    private Integer roomId;

    /**
     * 会议室名称
     */
    @ApiModelProperty("会议室名称")
    private String roomname;

    /**
     * 服务器地址
     */
    @ApiModelProperty("服务器地址")
    private String serverIP;

    /**
     * 会议室密码
     */
    @ApiModelProperty("会议室密码")
    private String roompass;

    /**
     * 端口号
     */
    @ApiModelProperty("端口号")
    private String port;

    /**
     * 创建人ID
     */
    @ApiModelProperty("创建人ID")
    private Integer creator;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date creatdate;

    /**
     * 是否删除  0 未删除  1已删除
     */
    @ApiModelProperty("是否删除  0 未删除  1已删除")
    private Integer isdel;

    /**
     * 仲裁员工作人员id
     */
    @ApiModelProperty("仲裁员工作人员id")
    private Integer fairworkerId;

    public Integer getWdmeetingId() {
        return wdmeetingId;
    }

    public void setWdmeetingId(Integer wdmeetingId) {
        this.wdmeetingId = wdmeetingId;
    }
    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }
    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }
    public String getRoompass() {
        return roompass;
    }

    public void setRoompass(String roompass) {
        this.roompass = roompass;
    }
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }
    public Date getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }
    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
    public Integer getFairworkerId() {
        return fairworkerId;
    }

    public void setFairworkerId(Integer fairworkerId) {
        this.fairworkerId = fairworkerId;
    }

    @Override
    protected Serializable pkVal() {
        return this.wdmeetingId;
    }

    @Override
    public String toString() {
        return "Wdmeeting{" +
        "wdmeetingId=" + wdmeetingId +
        ", roomId=" + roomId +
        ", roomname=" + roomname +
        ", serverIP=" + serverIP +
        ", roompass=" + roompass +
        ", port=" + port +
        ", creator=" + creator +
        ", creatdate=" + creatdate +
        ", isdel=" + isdel +
        ", fairworkerId=" + fairworkerId +
        "}";
    }
}
