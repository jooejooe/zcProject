package com.xzx.service.impl;

import com.xzx.model.Fairworker;
import com.xzx.dao.FairworkerMapper;
import com.xzx.service.IFairworkerService;
import com.xzx.viewModel.FairWorkerInfos;
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
public class FairworkerServiceImpl extends ServiceImpl<FairworkerMapper, Fairworker> implements IFairworkerService {
	@Autowired
	FairworkerMapper fairworkerMapper;
	public List<Fairworker> getWorkerList(String RegionId,String DepartmentId,String WorkerType)
	{
		return fairworkerMapper.getWorkerList(RegionId, DepartmentId,WorkerType);
	}
	
	public Fairworker appLogin(String logName,String password)
	{
		return fairworkerMapper.appLogin(logName,password);
	}
	
	public int getLogNameCount(String logName)
	{
		return fairworkerMapper.getLogNameCount(logName);
	}
	public String getNames(String workerids)
	{
		return fairworkerMapper.getNames(workerids);
	}

	public int testUserPassword(String userId,String password)
	{
		return fairworkerMapper.testUserPassword(userId, password);
	}
	
	public int updatePassword(String userId,String newPassword)
	{
		return fairworkerMapper.updatePassword(userId, newPassword);
	}
	
	public int fairworkerCountByPhone(String phone)
	{
		return fairworkerMapper.fairworkerCountByPhone(phone);
	}
	
	public List<FairWorkerInfos> getFairWorkerByDepartId(String type,String paramId,String searchText)
	{
		return fairworkerMapper.getFairWorkerByDepartId(type,paramId,searchText);
	}
	
	public List<Fairworker> getConsultworker(int RegionId,int TypeId)
	{
		return fairworkerMapper.getConsultworker(RegionId,TypeId);
	}
	
	public Fairworker getFairworkById(int fairworkerId)
	{
		return fairworkerMapper.getFairworkById(fairworkerId);
	}
	
	public List<Fairworker> getSecondAssignWorkerList(String RegionId,String DepartmentId,String WorkerType,String FirstFairworkerId)
	{
		return fairworkerMapper.getSecondAssignWorkerList(RegionId, DepartmentId, WorkerType, FirstFairworkerId);
	}
}
