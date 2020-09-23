package com.xzx.service;

import com.xzx.model.Access_wait;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-08-27
 */
public interface IAccess_waitService extends IService<Access_wait> {
	int addAccessWait(Access_wait access_wait);
	
	List<Access_wait> getWaitAccessList(String caseId,String userId);
}
