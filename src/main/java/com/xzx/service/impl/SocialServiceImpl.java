package com.xzx.service.impl;

import com.xzx.model.Social;
import com.xzx.dao.SocialMapper;
import com.xzx.service.ISocialService;
import com.xzx.viewModel.FingersInfo;
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
 * @since 2019-09-27
 */
@Service
public class SocialServiceImpl extends ServiceImpl<SocialMapper, Social> implements ISocialService {
	@Autowired
	SocialMapper socialMapper;
	
	public int getSocialByUserId(String userId)
	{
		return socialMapper.getSocialByUserId(userId);
	}
	
	public Social getSocialBySFZH(String SFZH)
	{
		return socialMapper.getSocialBySFZH(SFZH);
	}
	
	public List<FingersInfo> getAllSocialFinger(String SFZH)
	{
		return socialMapper.getAllSocialFinger(SFZH);
	}
}
