package com.xzx.service.impl;

import com.xzx.model.Speed;
import com.xzx.model.Speedhistory;
import com.xzx.dao.SpeedMapper;
import com.xzx.dao.SpeedhistoryMapper;
import com.xzx.dao.SubscribeMapper;
import com.xzx.service.ISpeedService;
import com.xzx.viewModel.ProgressInfos;
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
 * @since 2019-09-30
 */
@Service
public class SpeedServiceImpl extends ServiceImpl<SpeedMapper, Speed> implements ISpeedService {
	@Autowired
	SpeedMapper speedMapper;

	@Autowired
	SpeedhistoryMapper speedhistoryMapper;

	@Autowired
	SubscribeMapper subscribeMapper;

	public int addSpeed(Speed speed)
	{
		return speedMapper.addSpeed(speed);
	}

	public int existSpeed(String modelType,String registerId,String stateStrs)
	{
		return speedMapper.existSpeed(modelType, registerId, stateStrs);
	}

	public List<Map<String, Object>> getSpeedList(String userId,String State,String modelTypeStr)
	{
		return speedMapper.getSpeedList(userId, State, modelTypeStr);
	}

	public List<Map<String, String>> getScheduleList(String userId,String modelType)
	{
		return speedMapper.getScheduleList(userId, modelType);
	}

	@Transactional
	public Boolean updateSpeedState(Speed speed,String newTime)
	{
		int flag1=0;
		int flag2=0;
		int flag3=0;

		//修改speed
		flag1=speedMapper.updateSpeed(speed);

		//根据speedId获取speed信息，修改为最新状态，变为speedHistory;添加speedhistory
		Speed currentSpeed=speedMapper.getSpeedById(Integer.toString(speed.getSpeedId()));

		Speedhistory speedhistory=new Speedhistory();
		speedhistory.setModelType(currentSpeed.getModelType());
		speedhistory.setModelId(currentSpeed.getModelId());
		speedhistory.setState(currentSpeed.getState());
		speedhistory.setRefuseReason(currentSpeed.getRefuseReason());
		speedhistory.setAddReason(currentSpeed.getAddReason());
		speedhistory.setEvaluateType(currentSpeed.getEvaluateType());
		speedhistory.setEvaluateContext(currentSpeed.getEvaluateContext());
		speedhistory.setFairworkerId(currentSpeed.getFairworkerId());
		speedhistory.setRegisterId(currentSpeed.getRegisterId());
		speedhistory.setCancelReason(currentSpeed.getCancelReason());

		flag2=speedhistoryMapper.addOthersSpeedHistory(speedhistory);

		//当事项类型为预约，而且更改状态为更改预约时间时，需修改预约业务表“预约时间”字段
		if(currentSpeed.getModelType()>=4&&currentSpeed.getModelType()<=8&&speed.getState()==3)
		{
			flag3=subscribeMapper.updateSubscribeTime(Integer.toString(currentSpeed.getModelId()), newTime);
		}

		boolean flag=flag1>0&&flag2>0&&flag3>=0;

		return flag;
	}

	public Speed getSpeedById(String speedId)
	{
		return speedMapper.getSpeedById(speedId);
	}

	public int updateSpeed(Speed speed)
	{
		return speedMapper.updateSpeed(speed);
	}

	public List<ProgressInfos> getProgressByParam(String modelType,String userId)
	{
		return speedMapper.getProgressByParam(modelType,userId);
	}

	public List<ProgressInfos> getProgressByParamLive(String modelType,String userId,String IsOnline)
	{
		return speedMapper.getProgressByParamLive(modelType,userId,IsOnline);
	}
}
