package com.xzx.service.impl;

import com.xzx.model.Zzlog;
import com.xzx.dao.ZzlogMapper;
import com.xzx.dao.ZzlsbMapper;
import com.xzx.service.IZzlogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-08-27
 */
@Service
public class ZzlogServiceImpl extends ServiceImpl<ZzlogMapper, Zzlog> implements IZzlogService {
	@Autowired
	ZzlogMapper zzlogMapper;
	
	@Autowired
	ZzlsbMapper zzlsbMapper;
	
	@Transactional
	public Boolean addZzlog(String zzlsbIds)
	{
		int flag1=0;
		int flag2=0;
		
		flag1=zzlogMapper.addZzlog(zzlsbIds);
		flag2=zzlsbMapper.deleteZzlsb(zzlsbIds);
		
		boolean flag=flag1>0&&flag2>0;
		
		return flag;
	}
	
	public List<Map<String, Object>> getZZConfirmList()
	{
		return zzlogMapper.getZZConfirmList();
	}
}
