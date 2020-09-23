package com.xzx.service.impl;

import com.xzx.model.MessageTemplate;
import com.xzx.dao.MessageTemplateMapper;
import com.xzx.service.IMessageTemplateService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-10-22
 */
@Service
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplate> implements IMessageTemplateService {
	@Autowired
	MessageTemplateMapper messageTemplateMapper;

	public MessageTemplate getTemplateInfo(int regionId,int state,int workerType,int itemType,int modelType)
	{
		return messageTemplateMapper.getTemplateInfo(regionId, state, workerType,itemType,modelType);
	}
	
	public int getVerifyTemplateId(String workerType,int regionId)
	{
		return messageTemplateMapper.getVerifyTemplateId(workerType,regionId);
	}
}
