package com.xzx.service;

import com.xzx.model.Subscribetypematerial;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-10-30
 */
public interface ISubscribetypematerialService extends IService<Subscribetypematerial> {
	Subscribetypematerial getSubscribetypematerialInfo(String subscribeType);
}
