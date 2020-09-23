package com.xzx.service;

import com.xzx.model.Subscribe;
import com.xzx.viewModel.ItemDetailInfo;
import com.xzx.viewModel.SubscribeInfos;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-26
 */
public interface ISubscribeService extends IService<Subscribe> {
	Boolean addSubscribe(SubscribeInfos subscribeInfos);
	
	List<ItemDetailInfo> getItemInfoById(String subscribeId);//,String userId
	
	Subscribe getSubscribeById(String subscribeId);
	
	int getUseCount(int userId,int subscribeType,int modelType,int speedModelType);
}
