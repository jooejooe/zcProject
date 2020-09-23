package com.xzx.service.impl;

import com.xzx.model.Aj_tjblls;
import com.xzx.dao.Aj_tjbllsMapper;
import com.xzx.service.IAj_tjbllsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

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
public class Aj_tjbllsServiceImpl extends ServiceImpl<Aj_tjbllsMapper, Aj_tjblls> implements IAj_tjbllsService {
	@Autowired
	Aj_tjbllsMapper aj_tjbllsMapper;
	
	public int addTjblTemp(Aj_tjblls aj_tjblls)
	{
		return aj_tjbllsMapper.addTjblTemp(aj_tjblls);
	}
	
	public int updateTjblTemp(Aj_tjblls aj_tjblls)
	{
		return aj_tjbllsMapper.updateTjblTemp(aj_tjblls);
	}
	
	public Aj_tjblls getTjblTemp(String caseId,String userId)
	{
		return aj_tjbllsMapper.getTjblTemp(caseId, userId);
	}
	
	public String getTjblTempById(String tjblTempId)
	{
		return aj_tjbllsMapper.getTjblTempById(tjblTempId);
	}
}
