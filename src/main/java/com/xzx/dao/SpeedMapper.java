package com.xzx.dao;

import com.xzx.model.Speed;
import com.xzx.viewModel.ProgressInfos;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
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
 * @since 2019-09-30
 */
public interface SpeedMapper extends BaseMapper<Speed> {
	@Insert({ "insert into speed(ModelType, ModelId, CreateDate, State,fairworkerId,registerId) "
			+ "values(#{ModelType}, #{ModelId}, now(), 0,#{fairworkerId},#{registerId})" })
	@Options(useGeneratedKeys = true, keyProperty = "SpeedId")
	int addSpeed(Speed speed);

	/**
	 * 更改最新进度
	 * @param speed
	 * @return
	 */
	int updateSpeed(Speed speed);

	/**
	 * 获取相应申请人，相应类型事项，相应状态的事项数量
	 * @param modelType
	 * @param registerId
	 * @param stateStrs
	 * @return
	 */
	@Select("select count(1) as count from speed where ModelType=#{modelType} and registerId=#{registerId} and FIND_IN_SET(State,#{stateStrs})")
	int existSpeed(@Param("modelType")String modelType,@Param("registerId")String registerId,@Param("stateStrs")String stateStrs);

	/**
	 * app工作人员获取审批列表
	 * @param userType
	 * @param userId
	 * @param State
	 * @param modelTypeStr
	 * @return
	 */
	List<Map<String, Object>> getSpeedList(@Param("userId")String userId,@Param("State")String State,@Param("modelTypeStr")String modelTypeStr);

	/**
	 * app普通用户获取已申办事项记录
	 * @param userId
	 * @param modelType
	 * @return
	 */
	List<Map<String, String>> getScheduleList(@Param("userId") String userId,@Param("modelType") String modelType);

	/**
	 * 根据id获取speed信息
	 * @param speedId
	 * @return
	 */
	@Select("select * from speed where SpeedId=#{speedId}")
	Speed getSpeedById(@Param("speedId")String speedId);

	List<ProgressInfos> getProgressByParam(@Param("modelType")String modelType,@Param("userId") String userId);

	List<ProgressInfos> getProgressByParamLive(@Param("modelType")String modelType,@Param("userId") String userId,@Param("IsOnline")String IsOnline);

	@Update("update speed set fairworkerId=#{FairworkerId},State=1 where ModelType=#{modelType} and ModelId=#{modelId}")
	int updateSpeedFairwork(@Param("FairworkerId") String FairworkerId,@Param("modelId") String modelId,@Param("modelType")String modelType);

	@Update("update speed set State=1 where ModelType=#{modelType} and ModelId=#{modelId}")
	int updateSpeedFairworkZc(@Param("modelId") String modelId,@Param("modelType")String modelType);
}
