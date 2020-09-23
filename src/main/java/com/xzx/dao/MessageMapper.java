package com.xzx.dao;

import com.xzx.model.Message;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-09-27
 */
public interface MessageMapper extends BaseMapper<Message> {
	List<Message> getMessageByParams(@Param("userId")String userId,@Param("sendUserType")String sendUserType,@Param("UserType") String UserType,@Param("searchText") String searchText);

	@Select("select a.MessageId,a.Title,IFNULL(a.MessageCover,'') as MessageCover,a.MessageContext,a.CreateDate,a.CreateUser,b.WorkerName as CreateUserName from message a left join fairworker b on a.CreateUser=b.FairWorkerId where a.MessageId=#{msgId}")
	Message getMessageInfo(@Param("msgId") String msgId);
}
