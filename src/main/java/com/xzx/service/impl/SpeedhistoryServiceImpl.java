package com.xzx.service.impl;

import com.xzx.model.Speedhistory;
import com.xzx.dao.SpeedhistoryMapper;
import com.xzx.service.ISpeedhistoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-30
 */
@Service
public class SpeedhistoryServiceImpl extends ServiceImpl<SpeedhistoryMapper, Speedhistory> implements ISpeedhistoryService {
	@Autowired
	SpeedhistoryMapper speedhistoryMapper;
	
	public int addSpeedHistory(Speedhistory speedhistory)
	{
		return speedhistoryMapper.addSpeedHistory(speedhistory);
	}
}
