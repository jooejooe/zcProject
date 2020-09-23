package com.xzx.service;

import com.xzx.model.Subscribetype;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-10-30
 */
public interface ISubscribetypeService extends IService<Subscribetype> {
	//List<Subscribetype> getSubscribeType();
	List<Map<String, String>> getSubscribeType(String userId,String subscribeModelType,String speedModelType);
}
