package com.xzx.service.impl;

import com.xzx.model.Socialrecoder;
import com.xzx.dao.SocialrecoderMapper;
import com.xzx.service.ISocialrecoderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-10-10
 */
@Service
public class SocialrecoderServiceImpl extends ServiceImpl<SocialrecoderMapper, Socialrecoder> implements ISocialrecoderService {
	@Autowired
	SocialrecoderMapper socialrecoderMapper;
	
	public int addSocialRecorder(Socialrecoder socialrecoder)
	{
		return socialrecoderMapper.addSocialRecorder(socialrecoder);
	}
}
