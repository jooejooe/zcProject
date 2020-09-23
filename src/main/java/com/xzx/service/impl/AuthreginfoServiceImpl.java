package com.xzx.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xzx.dao.AuthreginfoMapper;
import com.xzx.model.Authreginfo;
import com.xzx.service.IAuthreginfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
@Service
public class AuthreginfoServiceImpl extends ServiceImpl<AuthreginfoMapper, Authreginfo> implements IAuthreginfoService {
	@Autowired
	AuthreginfoMapper authreginfoMapper;

	@Override
	public int insertinfo(Authreginfo authreginfo)
	{
		return authreginfoMapper.insertinfo(authreginfo);
	}

}
