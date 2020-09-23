package com.xzx.service.impl;

import com.xzx.model.Access_wait;
import com.xzx.dao.Access_waitMapper;
import com.xzx.service.IAccess_waitService;
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
 * @since 2020-08-27
 */
@Service
public class Access_waitServiceImpl extends ServiceImpl<Access_waitMapper, Access_wait> implements IAccess_waitService {
	@Autowired
	Access_waitMapper access_waitMapper;
	
	public int addAccessWait(Access_wait access_wait)
	{
		return access_waitMapper.addAccessWait(access_wait);
	}
	
	public List<Access_wait> getWaitAccessList(String caseId,String userId)
	{
		return access_waitMapper.getWaitAccessList(caseId, userId);
	}
}
