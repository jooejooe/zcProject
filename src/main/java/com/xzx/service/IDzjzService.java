package com.xzx.service;

import com.xzx.model.Dzjz;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-04-24
 */
public interface IDzjzService extends IService<Dzjz> {
	int addJZ(Dzjz dzjz);

	int getCaseNoCount(String jzbh,String jzId);
	
	int updateJZ(Dzjz dzjz);
	
	Dzjz getJZDocList(String type,String caseId,String docType);
}
