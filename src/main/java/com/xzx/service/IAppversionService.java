package com.xzx.service;

import com.xzx.model.Appversion;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-01-09
 */
public interface IAppversionService extends IService<Appversion> {
	Appversion getAppInfo(String type);
}
