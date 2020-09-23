package com.xzx.service.impl;

import com.xzx.model.Lawprovision;
import com.xzx.dao.LawprovisionMapper;
import com.xzx.service.ILawprovisionService;
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
 * @since 2019-12-17
 */
@Service
public class LawprovisionServiceImpl extends ServiceImpl<LawprovisionMapper, Lawprovision> implements ILawprovisionService {
	@Autowired
	LawprovisionMapper lawprovisionMapper;

	public List<Lawprovision> getLawProvisionList(int modelType,String title,String token)
	{
		return lawprovisionMapper.getLawProvisionList(modelType, title, token);
	}

	public Lawprovision getLawprovisionById(String id)
	{
		return lawprovisionMapper.getLawprovisionById(id);
	}
}
