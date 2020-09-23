package com.xzx.service;

import com.xzx.model.Social;
import com.xzx.viewModel.FingersInfo;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-27
 */
public interface ISocialService extends IService<Social> {
	int getSocialByUserId(String userId);
	
	Social getSocialBySFZH(String SFZH);
	
	List<FingersInfo> getAllSocialFinger(String SFZH);
}
