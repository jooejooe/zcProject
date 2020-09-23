package com.xzx.service.impl;

import com.xzx.model.Improveability;
import com.xzx.dao.ImproveabilityMapper;
import com.xzx.service.IImproveabilityService;
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
 * @since 2020-05-16
 */
@Service
public class ImproveabilityServiceImpl extends ServiceImpl<ImproveabilityMapper, Improveability> implements IImproveabilityService {
	@Autowired
	ImproveabilityMapper improveabilityMapper;
	
	public int addImproveAbility(Improveability improveability)
	{
		return improveabilityMapper.addImproveAbility(improveability);
	}
	
	public List<Improveability> getImproveAbilityDocList(String docType,String docTitle)
	{
		return improveabilityMapper.getImproveAbilityDocList(docType, docTitle);
	}
	
	public int updateImproveAbility(Improveability improveability)
	{
		return improveabilityMapper.updateImproveAbility(improveability);
	}
	
	
	public Improveability getInfoById(String id)
	{
		return improveabilityMapper.getInfoById(id);
	}
	
	public int getTitleCount(String title,int id)
	{
		return improveabilityMapper.getTitleCount(title, id);
	}
	
	public int delImproveAbility(Improveability improveability)
	{
		return improveabilityMapper.delImproveAbility(improveability);
	}
}
