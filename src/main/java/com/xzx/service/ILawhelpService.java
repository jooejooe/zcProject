package com.xzx.service;

import com.xzx.model.Lawhelp;
import com.xzx.viewModel.ItemDetailInfo;

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
public interface ILawhelpService extends IService<Lawhelp> {
	List<ItemDetailInfo> getItemInfoById(String lawHelpId);//,String userId
	
	int getUseCount(String userId,String applyType);
}
