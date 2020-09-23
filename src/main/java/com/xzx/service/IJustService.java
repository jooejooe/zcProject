package com.xzx.service;

import com.xzx.model.Access;
import com.xzx.model.Just;
import com.xzx.viewModel.ItemDetailInfo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public interface IJustService extends IService<Just> {
	Boolean insertJust(Just just,List<Access> listAccess);
	
	List<ItemDetailInfo> getItemInfoById(String justId);//,String userId
	
	int getUseCount(int userId,String applyType);
}
