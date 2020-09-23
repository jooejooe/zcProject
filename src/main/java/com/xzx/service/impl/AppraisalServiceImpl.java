package com.xzx.service.impl;

import com.xzx.model.Appraisal;
import com.xzx.dao.AppraisalMapper;
import com.xzx.service.IAppraisalService;
import com.xzx.viewModel.ItemDetailInfo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-26
 */
@Service
public class AppraisalServiceImpl extends ServiceImpl<AppraisalMapper, Appraisal> implements IAppraisalService {
	@Autowired
	AppraisalMapper appraisalMapper;
	
	public int addAppraisal(Appraisal appraisal)
	{
		return appraisalMapper.addAppraisal(appraisal);
	}
	
	public List<ItemDetailInfo> getItemInfoById(String appraisalId)//,String userId
	{
		return appraisalMapper.getItemInfoById(appraisalId);//, userId
	}
	
	public int getUseCount(String userId,String applyType)
	{
		return appraisalMapper.getUseCount(userId, applyType);
	}
}
