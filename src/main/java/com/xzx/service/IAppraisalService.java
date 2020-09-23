package com.xzx.service;

import com.xzx.model.Appraisal;
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
public interface IAppraisalService extends IService<Appraisal> {
	int addAppraisal(Appraisal appraisal);
	
	List<ItemDetailInfo> getItemInfoById(String appraisalId);//,String userId
	
	int getUseCount(String userId,String applyType);
}
