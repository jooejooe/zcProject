package com.xzx.service.impl;

import com.xzx.model.Subscribetypematerial;
import com.xzx.dao.SubscribetypematerialMapper;
import com.xzx.service.ISubscribetypematerialService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

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
public class SubscribetypematerialServiceImpl extends ServiceImpl<SubscribetypematerialMapper, Subscribetypematerial> implements ISubscribetypematerialService {
	@Autowired
	SubscribetypematerialMapper subscribetypematerialMapper;
	
	public Subscribetypematerial getSubscribetypematerialInfo(String subscribeType)
	{
		return subscribetypematerialMapper.getSubscribetypematerialInfo(subscribeType);
	}
}
