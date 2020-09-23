package com.xzx.service;

import com.xzx.model.Message;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-27
 */
public interface IMessageService extends IService<Message> {
	List<Message> getMessageByParams(String userId,String sendUserType,String UserType,String searchText);

	Message getMessageInfo(String msgId);
}
