package com.xzx.service.impl;

import com.xzx.model.Access;
import com.xzx.model.Speed;
import com.xzx.model.Speedhistory;
import com.xzx.model.Subscribe;
import com.xzx.dao.AccessMapper;
import com.xzx.dao.SpeedMapper;
import com.xzx.dao.SpeedhistoryMapper;
import com.xzx.dao.SubscribeMapper;
import com.xzx.service.ISubscribeService;
import com.xzx.viewModel.ItemDetailInfo;
import com.xzx.viewModel.SubscribeInfos;
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
 * @since 2019-09-26
 */
@Service
public class SubscribeServiceImpl extends ServiceImpl<SubscribeMapper, Subscribe> implements ISubscribeService {
	@Autowired
	SubscribeMapper subscribeMapper;
	
	@Autowired
	AccessMapper accessMapper;
	
	@Autowired
	SpeedMapper speedMapper;
	
	@Autowired
	SpeedhistoryMapper speedhistoryMapper;
	
	@Transactional
	public Boolean addSubscribe(SubscribeInfos subscribeInfos)
	{
		Subscribe subscribe=subscribeInfos.getSubscribe();
		List<Access> listAccess=subscribeInfos.getListAccess();
		
		int flag1=0;
		int flag2=0;
		
		int flag3=0;
		int flag4=0;
		
		flag1=subscribeMapper.addSubscribe(subscribe);
		
		int subId=subscribe.getSubscribeId();
		
		if(listAccess!=null&&listAccess.size()>0)
			flag2=accessMapper.insertBatchAccess(listAccess, subId,(subscribe.getModelType()+4),0);
		
		//当前进度初值增加
		Speed speed=new Speed();
		speed.setModelType(subscribe.getModelType()+4);
		speed.setModelId(subscribe.getSubscribeId());
		speed.setState(0);
		speed.setFairworkerId(subscribe.getFairworkerId());
		speed.setRegisterId(subscribe.getUserId());
		
		flag3=speedMapper.addSpeed(speed);
		
		//进度历史初始化增加
		Speedhistory speedhistory=new Speedhistory();
		speedhistory.setModelType(subscribe.getModelType()+4);
		speedhistory.setModelId(subscribe.getSubscribeId());
		speedhistory.setState(0);
		speedhistory.setFairworkerId(subscribe.getFairworkerId());
		speedhistory.setRegisterId(subscribe.getUserId());
		flag4=speedhistoryMapper.addSpeedHistory(speedhistory);
		
		boolean flag=flag1>0&&flag2>=0&&flag3>0&&flag4>0;
		
		return flag;
	}
	
	public List<ItemDetailInfo> getItemInfoById(String subscribeId)//,String userId
	{
		return subscribeMapper.getItemInfoById(subscribeId);//, userId
	}
	
	public Subscribe getSubscribeById(String subscribeId)
	{
		return subscribeMapper.getSubscribeById(subscribeId);
	}
	
	public int getUseCount(int userId,int subscribeType,int modelType,int speedModelType)
	{
		return subscribeMapper.getUseCount(userId, subscribeType, modelType, speedModelType);
	}
}
