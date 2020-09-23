package com.xzx.service;

import com.xzx.model.Rongworker;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-05-18
 */
public interface IRongworkerService extends IService<Rongworker> {
	List<Rongworker> getRyWorkerList(String regionId,String typeId,String departId);
}
