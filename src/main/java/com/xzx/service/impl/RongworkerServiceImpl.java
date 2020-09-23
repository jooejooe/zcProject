package com.xzx.service.impl;

import com.xzx.model.Rongworker;
import com.xzx.dao.RongworkerMapper;
import com.xzx.service.IRongworkerService;
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
 * @since 2020-05-18
 */
@Service
public class RongworkerServiceImpl extends ServiceImpl<RongworkerMapper, Rongworker> implements IRongworkerService {
	@Autowired
	RongworkerMapper rongworkerMapper;
	
	public List<Rongworker> getRyWorkerList(String regionId,String typeId,String departId)
	{
		return rongworkerMapper.getRyWorkerList(regionId, typeId, departId);
	}
}
