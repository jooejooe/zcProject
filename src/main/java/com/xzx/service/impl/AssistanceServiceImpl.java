package com.xzx.service.impl;

import com.xzx.model.Assistance;
import com.xzx.dao.AssistanceMapper;
import com.xzx.service.IAssistanceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
@Service
public class AssistanceServiceImpl extends ServiceImpl<AssistanceMapper, Assistance> implements IAssistanceService {
	@Autowired
	AssistanceMapper assistanceMapper;
	
	public List<Map<String, String>> getAssistanceList(String assistancetype,String userId,String modelType,String isSubscribe)
	{
		return assistanceMapper.getAssistanceList(assistancetype, userId, modelType,isSubscribe);
	}
	
	public List<Assistance> getAllAssistanceList(String assistancetype)
	{
		return assistanceMapper.getAllAssistanceList(assistancetype);
	}
}
