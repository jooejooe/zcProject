package com.xzx.service.impl;

import com.xzx.model.Zzlsb;
import com.xzx.dao.ZzlsbMapper;
import com.xzx.service.IZzlsbService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-08-27
 */
@Service
public class ZzlsbServiceImpl extends ServiceImpl<ZzlsbMapper, Zzlsb> implements IZzlsbService {
	@Autowired
	ZzlsbMapper zzlsbMapper;
	
	public int addZzlsb(Zzlsb zzlsb)
	{
		return zzlsbMapper.addZzlsb(zzlsb);
	}
	
	public Map<String, String> getZzlsbInfo(int caseId,int workerId,int tjyAccessId)
	{
		return zzlsbMapper.getZzlsbInfo(caseId, workerId, tjyAccessId);
	}
	
	public List<Zzlsb> getLsbListByIds(String ids)
	{
		return zzlsbMapper.getLsbListByIds(ids);
	}
}
