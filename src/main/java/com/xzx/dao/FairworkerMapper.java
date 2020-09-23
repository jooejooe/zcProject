package com.xzx.dao;

import com.xzx.model.Fairworker;
import com.xzx.viewModel.FairWorkerInfos;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public interface FairworkerMapper extends BaseMapper<Fairworker> {
//	@Select("select FairWorkerId,WorkerName,Speciality,Image,(select ifnull(round((count(1)*5-sum(evaluateType))/count(1),1),0) from speed where FairWorkerId=a.FairWorkerId and evaluateState=1) as MZ,a.Telephone from fairworker a where RegionId=#{RegionId} and DepartmentId=#{DepartmentId} and WorkerType=#{WorkerType}")
	List<Fairworker> getWorkerList(@Param("RegionId")String RegionId,@Param("DepartmentId")String DepartmentId,@Param("WorkerType")String WorkerType);
	
	//@Select("select * from fairworker where LoginUserName=#{logName} and LoginPass=#{password} and WorkerType<>7")
	@Select("select * from fairworker where State=0 and Phone=#{logName} and LoginPass=#{password} and WorkerType<>7")
	Fairworker appLogin(@Param("logName") String logName,@Param("password") String password);
	
	@Select("select count(1) from fairworker where LoginUserName=#{logName} and WorkerType<>7")
	int getLogNameCount(@Param("logName") String logName);
	
	@Select("select count(1) from fairworker where State=0 and FairWorkerId=#{userId} and LoginPass=#{password} and WorkerType<>7")
	int testUserPassword(@Param("userId") String userId,@Param("password") String password);
	
	@Update("update fairworker set LoginPass=#{newPassword} where FairWorkerId=#{userId}")
	int updatePassword(@Param("userId") String userId,@Param("newPassword") String newPassword);
	
	@Select("select count(1) from fairworker where Phone=#{phone}")
	int fairworkerCountByPhone(@Param("phone") String phone);

	String getNames(@Param("workerids") String workerids);

	List<FairWorkerInfos> getFairWorkerByDepartId(@Param("type")String type,@Param("paramId") String paramId,@Param("searchText") String searchText);
	
	//@Select("select b.* from consultworker a left join fairworker b on a.FairWorkerId=b.FairWorkerId where a.ConsultRegionId=#{RegionId}")
	List<Fairworker> getConsultworker(@Param("RegionId") int RegionId,@Param("TypeId") int TypeId);
	
	@Select("select * from fairworker where FairWorkerId=#{fairworkerId}")
	Fairworker getFairworkById(@Param("fairworkerId") int fairworkerId);
	
	List<Fairworker> getSecondAssignWorkerList(@Param("RegionId")String RegionId,@Param("DepartmentId")String DepartmentId,@Param("WorkerType")String WorkerType,@Param("FirstFairworkerId")String FirstFairworkerId);
}
