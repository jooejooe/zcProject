package com.xzx.service.impl;

import com.xzx.model.Consulttype;
import com.xzx.dao.ConsulttypeMapper;
import com.xzx.service.IConsulttypeService;
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
public class ConsulttypeServiceImpl extends ServiceImpl<ConsulttypeMapper, Consulttype> implements IConsulttypeService {
	@Autowired
	ConsulttypeMapper consulttypeMapper;

	public List<Consulttype> getConsulttypeList(int regionId)
	{
		return consulttypeMapper.getConsulttypeList(regionId);
	}
}
