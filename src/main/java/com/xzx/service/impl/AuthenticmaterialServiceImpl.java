package com.xzx.service.impl;

import com.xzx.model.Authenticmaterial;
import com.xzx.dao.AuthenticmaterialMapper;
import com.xzx.service.IAuthenticmaterialService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

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
public class AuthenticmaterialServiceImpl extends ServiceImpl<AuthenticmaterialMapper, Authenticmaterial> implements IAuthenticmaterialService {
	@Autowired
	AuthenticmaterialMapper authenticmaterialMapper;
	
	public Authenticmaterial getAuthenticmaterialInfo(String assistanceId)
	{
		return authenticmaterialMapper.getAuthenticmaterialInfo(assistanceId);
	}
}
