package com.xzx.dao;

import com.xzx.model.Meeting;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-03-31
 */
public interface MeetingMapper extends BaseMapper<Meeting> {
	@Select("select meetingId,roomId,ifnull(roomname,'') roomname,ifnull(serverIp,'') serverIp,ifnull(port,'') port,ifnull(pass,'') pass,ifnull(creator,'') creator,ifnull(creattime,'') creattime,isdel from meeting where isdel=0")
	List<Meeting> getMeetingList();
}
