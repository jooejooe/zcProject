package com.xzx.service.impl;

import com.xzx.model.Aj_tjbl;
import com.xzx.dao.Aj_tjblMapper;
import com.xzx.dao.Aj_tjbllsMapper;
import com.xzx.service.IAj_tjblService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

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
public class Aj_tjblServiceImpl extends ServiceImpl<Aj_tjblMapper, Aj_tjbl> implements IAj_tjblService {
	@Autowired
	Aj_tjblMapper aj_tjblMapper;
	
	@Autowired
	Aj_tjbllsMapper aj_tjbllsMapper;
	
	@Transactional
	public Boolean addTjbl(Aj_tjbl aj_tjbl)
	{		
		int flag1=aj_tjblMapper.addTjbl(aj_tjbl);
		
		int flag2=aj_tjbllsMapper.delTjblTemp(Integer.toString(aj_tjbl.getAnjianId()), Integer.toString(aj_tjbl.getUserId()));
		
		Boolean flag=flag1>0&&flag2>0;
		
		return flag;
	}
}
