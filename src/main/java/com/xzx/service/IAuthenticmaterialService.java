package com.xzx.service;

import com.xzx.model.Authenticmaterial;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-26
 */
public interface IAuthenticmaterialService extends IService<Authenticmaterial> {
	Authenticmaterial getAuthenticmaterialInfo(String assistanceId);
}
