package com.xzx.dao;

import com.xzx.model.Speedhistory;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
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
public interface SpeedhistoryMapper extends BaseMapper<Speedhistory> {
	@Insert({ "insert into speedhistory(ModelType, ModelId, CreateDate, State,fairworkerId,registerId) "
    		+ "values(#{ModelType}, #{ModelId}, now(), 0,#{fairworkerId},#{registerId})" })
    @Options(useGeneratedKeys = true, keyProperty = "SpeedHistoryId")
	int addSpeedHistory(Speedhistory speedhistory);
	
	@Insert({ "insert into speedhistory(ModelType, ModelId, CreateDate, State,refuseReason,addReason,evaluateType,evaluateContext,fairworkerId,registerId,cancelReason) "
    		+ "values(#{ModelType}, #{ModelId}, now(), #{State},#{refuseReason},#{addReason},#{evaluateType},#{evaluateContext},#{fairworkerId},#{registerId},#{cancelReason})" })
    @Options(useGeneratedKeys = true, keyProperty = "SpeedHistoryId")
	int addOthersSpeedHistory(Speedhistory speedhistory);
	
	@Update("update speedhistory set fairworkerId=#{FairworkerId} where ModelType=#{modelType} and ModelId=#{modelId}")
	int updateSpeedHistoryFairwork(@Param("FairworkerId") String FairworkerId,@Param("modelId") String modelId,@Param("modelType")String modelType);
}
