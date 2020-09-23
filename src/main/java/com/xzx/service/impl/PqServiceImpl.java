package com.xzx.service.impl;

import com.xzx.model.Pq;
import com.xzx.dao.PqMapper;
import com.xzx.service.IPqService;
import com.xzx.viewModel.PqInfos;
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
 * @since 2020-04-24
 */
@Service
public class PqServiceImpl extends ServiceImpl<PqMapper, Pq> implements IPqService {
	@Autowired
	PqMapper pqMapper;
	
	public List<PqInfos> getPqListByUser(String userId)
	{
		return pqMapper.getPqListByUser(userId);
	}
	
	public List<PqInfos> getAllPqListByUser(String FairworkerId,String ajId)
	{
		return pqMapper.getAllPqListByUser(FairworkerId,ajId);
	}
}
