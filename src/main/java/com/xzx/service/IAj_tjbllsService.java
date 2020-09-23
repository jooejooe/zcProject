package com.xzx.service;

import com.xzx.model.Aj_tjblls;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-08-27
 */
public interface IAj_tjbllsService extends IService<Aj_tjblls> {
	int addTjblTemp(Aj_tjblls aj_tjblls);
	
	int updateTjblTemp(Aj_tjblls aj_tjblls);
	
	Aj_tjblls getTjblTemp(String caseId,String userId);
	
	String getTjblTempById(String tjblTempId);
}
