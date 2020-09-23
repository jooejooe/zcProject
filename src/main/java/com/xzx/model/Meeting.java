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
 * @since 2020-03-31
 */
public class Meeting extends Model<Meeting> {

    private static final long serialVersionUID = 1L;

    /**
     * 会议室主键ID
     */
    @TableId(value = "meetingId", type = IdType.AUTO)
    @ApiModelProperty("会议室主键ID")
    private Integer meetingId;

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
    @ApiModelProperty("会议室名称")
    private String serverIp;

    /**
     * 服务器端口
     */
    @ApiModelProperty("服务器端口")
    private String port;

    /**
     * 会议室密码
     */
    @ApiModelProperty("会议室密码")
    private String pass;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String creator;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    //@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String creattime;

    /**
     * 逻辑删除  0 未删除   1 已删除
     */
    @ApiModelProperty("逻辑删除  0 未删除   1 已删除")
    private Integer isdel;

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
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
    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }
    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime;
    }
    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    @Override
    protected Serializable pkVal() {
        return this.meetingId;
    }

    @Override
    public String toString() {
        return "Meeting{" +
        "meetingId=" + meetingId +
        ", roomId=" + roomId +
        ", roomname=" + roomname +
        ", serverIp=" + serverIp +
        ", port=" + port +
        ", pass=" + pass +
        ", creator=" + creator +
        ", creattime=" + creattime +
        ", isdel=" + isdel +
        "}";
    }
}
