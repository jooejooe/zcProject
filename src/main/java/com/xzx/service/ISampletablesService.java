package com.xzx.service;

import com.xzx.model.Sampletables;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-12-17
 */
public interface ISampletablesService extends IService<Sampletables> {
	List<Sampletables> getSampleTablesList(int modelType,String title,String token);
	
	Sampletables getSampletablesById(String id);
}
