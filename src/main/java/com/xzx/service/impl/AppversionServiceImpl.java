package com.xzx.service.impl;

import com.xzx.model.Appversion;
import com.xzx.dao.AppversionMapper;
import com.xzx.service.IAppversionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-01-09
 */
@Service
public class AppversionServiceImpl extends ServiceImpl<AppversionMapper, Appversion> implements IAppversionService {
	@Autowired
	AppversionMapper appversionMapper;
	public Appversion getAppInfo(String type)
	{
		return appversionMapper.getAppInfo(type);
	}
}
