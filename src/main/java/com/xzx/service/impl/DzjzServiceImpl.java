package com.xzx.service.impl;

import com.xzx.model.Dzjz;
import com.xzx.dao.DzjzMapper;
import com.xzx.service.IDzjzService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

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
public class DzjzServiceImpl extends ServiceImpl<DzjzMapper, Dzjz> implements IDzjzService {
	@Autowired
	DzjzMapper dzjzMapper;
	
	public int addJZ(Dzjz dzjz)
	{
		return dzjzMapper.addJZ(dzjz);
	}

	public int getCaseNoCount(String jzbh,String jzId)
	{
		return dzjzMapper.getCaseNoCount(jzbh,jzId);
	}
	
	public int updateJZ(Dzjz dzjz)
	{
		return dzjzMapper.updateJZ(dzjz);
	}
	
	public Dzjz getJZDocList(String type,String caseId,String docType)
	{
		return dzjzMapper.getJZDocList(type, caseId, docType);
	}
}
