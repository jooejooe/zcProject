package com.xzx.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xzx.dao.AuthworkerinfoMapper;
import com.xzx.dao.ZcdwMapper;
import com.xzx.model.Authworkerinfo;
import com.xzx.model.Zcdw;
import com.xzx.service.IAuthworkerinfoService;
import com.xzx.service.IZcdwService;
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
public class AuthworkerinfoServiceImpl extends ServiceImpl<AuthworkerinfoMapper, Authworkerinfo> implements IAuthworkerinfoService {
	@Autowired
	AuthworkerinfoMapper authworkerinfoMapper;

	public int insertAuthWorkerInfo(Authworkerinfo authworkerinfo)
	{
		return authworkerinfoMapper.insertAuthWorkerInfo(authworkerinfo);
	}

}
