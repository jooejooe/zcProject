package com.xzx.service;

import com.xzx.model.Pq;
import com.xzx.viewModel.PqInfos;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-04-24
 */
public interface IPqService extends IService<Pq> {
	List<PqInfos> getPqListByUser(String userId);
	
	List<PqInfos> getAllPqListByUser(String FairworkerId,String ajId);
}
