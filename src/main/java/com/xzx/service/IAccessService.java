package com.xzx.service;

import com.xzx.model.Access;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public interface IAccessService extends IService<Access> {
	List<Access> getAccessByItemId(String ModelId,String ModelType);
	
	int insertBatchAccess(List<Access> list,int justId,int ModelType,int AccessType);
	
	List<Access> getPartAccessByItemId(String ModelId,String ModelType);
	
	List<Access> getAccessListByItemId(String ModelId,String ModelType,String UserId);
}
