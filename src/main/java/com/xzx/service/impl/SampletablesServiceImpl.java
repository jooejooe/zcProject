package com.xzx.service.impl;

import com.xzx.model.Sampletables;
import com.xzx.dao.SampletablesMapper;
import com.xzx.service.ISampletablesService;
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
public class SampletablesServiceImpl extends ServiceImpl<SampletablesMapper, Sampletables> implements ISampletablesService {
	@Autowired
	SampletablesMapper sampletablesMapper;

	public List<Sampletables> getSampleTablesList(int modelType,String title,String token)
	{
		return sampletablesMapper.getSampleTablesList(modelType, title, token);
	}

	public Sampletables getSampletablesById(String id)
	{
		return sampletablesMapper.getSampletablesById(id);
	}
}
