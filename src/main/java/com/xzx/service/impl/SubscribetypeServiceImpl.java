package com.xzx.service.impl;

import com.xzx.model.Subscribetype;
import com.xzx.dao.SubscribetypeMapper;
import com.xzx.service.ISubscribetypeService;
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
 * @since 2019-10-30
 */
@Service
public class SubscribetypeServiceImpl extends ServiceImpl<SubscribetypeMapper, Subscribetype> implements ISubscribetypeService {
	@Autowired
	SubscribetypeMapper subscribetypeMapper;

	public List<Map<String, String>> getSubscribeType(String userId,String subscribeModelType,String speedModelType)
	{
		return subscribetypeMapper.getSubscribeType(userId,subscribeModelType,speedModelType);
	}
}
