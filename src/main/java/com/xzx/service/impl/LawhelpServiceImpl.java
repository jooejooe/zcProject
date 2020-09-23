package com.xzx.service.impl;

import com.xzx.model.Lawhelp;
import com.xzx.dao.LawhelpMapper;
import com.xzx.service.ILawhelpService;
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
public class LawhelpServiceImpl extends ServiceImpl<LawhelpMapper, Lawhelp> implements ILawhelpService {
	@Autowired
	LawhelpMapper lawhelpMapper;

	public List<ItemDetailInfo> getItemInfoById(String lawHelpId)//,String userId
	{
		return lawhelpMapper.getItemInfoById(lawHelpId);//, userId
	}
	
	public int getUseCount(String userId,String applyType)
	{
		return lawhelpMapper.getUseCount(userId, applyType);
	}
}
