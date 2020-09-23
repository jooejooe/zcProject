package com.xzx.service;

import com.xzx.model.Fairworker;
import com.xzx.viewModel.FairWorkerInfos;

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
public interface IFairworkerService extends IService<Fairworker> {
	List<Fairworker> getWorkerList(String RegionId,String DepartmentId,String WorkerType);
	
	Fairworker appLogin(String logName,String password);
	
	int getLogNameCount(String logName);
	String getNames(String workerids);

	int testUserPassword(String userId,String password);
	
	int updatePassword(String userId,String newPassword);
	
	int fairworkerCountByPhone(String phone);
	
	List<FairWorkerInfos> getFairWorkerByDepartId(String type,String paramId,String searchText);
	
	List<Fairworker> getConsultworker(int RegionId,int TypeId);
	
	Fairworker getFairworkById(int fairworkerId);
	
	List<Fairworker> getSecondAssignWorkerList(String RegionId,String DepartmentId,String WorkerType,String FirstFairworkerId);
}
