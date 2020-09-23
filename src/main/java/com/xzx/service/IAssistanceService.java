package com.xzx.service;

import com.xzx.model.Assistance;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
public interface IAssistanceService extends IService<Assistance> {	
	//List<Assistance> getAssistanceList(String assistancetype);
	List<Map<String, String>> getAssistanceList(String assistancetype,String userId,String modelType,String isSubscribe);

	List<Assistance> getAllAssistanceList(String assistancetype);
}
