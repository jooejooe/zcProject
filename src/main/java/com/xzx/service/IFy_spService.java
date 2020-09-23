package com.xzx.service;

import com.xzx.model.Fy_sp;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-04-24
 */
public interface IFy_spService extends IService<Fy_sp> {
	Boolean operateSP(Fy_sp fy_sp);
	
	Fy_sp getSPInfoByCaseId(String caseId);
}
