package com.xzx.service.impl;

import com.xzx.model.Message;
import com.xzx.dao.MessageMapper;
import com.xzx.service.IMessageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-27
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {
	@Autowired
	MessageMapper messageMapper;
	
	public List<Message> getMessageByParams(String userId,String sendUserType,String UserType,String searchText)
	{
		return messageMapper.getMessageByParams(userId, sendUserType, UserType,searchText);
	}
	
	public Message getMessageInfo(String msgId)
	{
		return messageMapper.getMessageInfo(msgId);
	}
}
