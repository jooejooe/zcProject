package com.xzx.service.impl;

import com.xzx.model.Consultregion;
import com.xzx.dao.ConsultregionMapper;
import com.xzx.service.IConsultregionService;
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
 * @since 2019-12-26
 */
@Service
public class ConsultregionServiceImpl extends ServiceImpl<ConsultregionMapper, Consultregion> implements IConsultregionService {
	@Autowired
	ConsultregionMapper consultregionMapper;
	
	public List<Consultregion> getConsultregionList()
	{
		return consultregionMapper.getConsultregionList();
	}
}
