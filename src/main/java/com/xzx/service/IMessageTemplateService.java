package com.xzx.service;

import com.xzx.model.MessageTemplate;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-10-22
 */
public interface IMessageTemplateService extends IService<MessageTemplate> {
	MessageTemplate getTemplateInfo(int regionId,int state,int workerType,int itemType,int modelType);
	
	int getVerifyTemplateId(String workerType,int regionId);
}
