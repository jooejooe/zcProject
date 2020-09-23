package com.xzx.service;

import com.xzx.model.Speed;
import com.xzx.viewModel.ProgressInfos;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-30
 */
public interface ISpeedService extends IService<Speed> {
	int addSpeed(Speed speed);
	
	int existSpeed(String modelType,String registerId,String stateStrs);
	
	List<Map<String, Object>> getSpeedList(String userId,String State,String modelTypeStr);
	
	List<Map<String, String>> getScheduleList(String userId,String modelType);
	
	Boolean updateSpeedState(Speed speed,String newTime);
	
	Speed getSpeedById(String speedId);
	
	int updateSpeed(Speed speed);
	
	List<ProgressInfos> getProgressByParam(String modelType,String userId);
	
	List<ProgressInfos> getProgressByParamLive(String modelType,String userId,String IsOnline);
}
