package com.xzx.service.impl;

import com.xzx.model.Access;
import com.xzx.model.Just;
import com.xzx.model.Speed;
import com.xzx.model.Speedhistory;
import com.xzx.dao.AccessMapper;
import com.xzx.dao.JustMapper;
import com.xzx.dao.SpeedMapper;
import com.xzx.dao.SpeedhistoryMapper;
import com.xzx.service.IJustService;
import com.xzx.viewModel.ItemDetailInfo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
@Service
public class JustServiceImpl extends ServiceImpl<JustMapper, Just> implements IJustService {
	@Autowired
	JustMapper justMapper;
	
	@Autowired
	AccessMapper accessMapper;
	
	@Autowired
	SpeedMapper speedMapper;
	
	@Autowired
	SpeedhistoryMapper speedhistoryMapper;
	
	@Transactional
	public Boolean insertJust(Just just,List<Access> listAccess)
	{
		int flag1=0;
		int flag2=0;
		
		int flag3=0;
		int flag4=0;
		
		flag1=justMapper.addJust(just);	
		int justId=just.getJustId();

		if(listAccess!=null&&listAccess.size()>0)
			flag2=accessMapper.insertBatchAccess(listAccess, justId,0,0);
		
		// 0 公证办理 1 人民仲裁 2 司法鉴定 3 法律援助
		
		//当前进度初值增加
		Speed speed=new Speed();
		speed.setModelType(0);
		speed.setModelId(justId);
		speed.setState(0);
		speed.setFairworkerId(just.getFairWorkerId());
		speed.setRegisterId(just.getUserId());
		
		flag3=speedMapper.addSpeed(speed);
		
		//进度历史初始化增加
		Speedhistory speedhistory=new Speedhistory();
		speedhistory.setModelType(0);
		speedhistory.setModelId(justId);
		speedhistory.setState(0);
		speedhistory.setFairworkerId(just.getFairWorkerId());
		speedhistory.setRegisterId(just.getUserId());
		flag4=speedhistoryMapper.addSpeedHistory(speedhistory);
		
		boolean flag=flag1>0&&flag2>=0&&flag3>0&&flag4>0;
		
		return flag;
	}
	
	public List<ItemDetailInfo> getItemInfoById(String justId)//,String userId
	{
		return justMapper.getItemInfoById(justId);//, userId
	}
	
	public int getUseCount(int userId,String applyType)
	{
		return justMapper.getUseCount(userId, applyType);
	}
}
