package com.xzx.service.impl;

import com.xzx.model.Access;
import com.xzx.dao.AccessMapper;
import com.xzx.service.IAccessService;
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
 * @since 2019-09-24
 */
@Service
public class AccessServiceImpl extends ServiceImpl<AccessMapper, Access> implements IAccessService {
	@Autowired
	AccessMapper accessMapper;
	
	public List<Access> getAccessByItemId(String ModelId,String ModelType)
	{
		return accessMapper.getAccessByItemId(ModelId, ModelType);
	}
	
	public int insertBatchAccess(List<Access> list,int justId,int ModelType,int AccessType)
	{
		return accessMapper.insertBatchAccess(list, justId, ModelType,AccessType);
	}
	
	public List<Access> getPartAccessByItemId(String ModelId,String ModelType)
	{
		return accessMapper.getPartAccessByItemId(ModelId, ModelType);
	}
	
	public List<Access> getAccessListByItemId(String ModelId,String ModelType,String UserId)
	{
		return accessMapper.getAccessListByItemId(ModelId, ModelType, UserId);
	}
}
