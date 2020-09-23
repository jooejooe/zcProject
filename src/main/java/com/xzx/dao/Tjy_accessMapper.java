package com.xzx.dao;

import com.xzx.model.Tjy_access;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-08-25
 */
public interface Tjy_accessMapper extends BaseMapper<Tjy_access> {
	int insertBatchTJYAccess(@Param("list")List<Tjy_access> list);
	
	@Delete("delete from tjy_access where anjianId=#{caseId}")
	int delTJYAccess(@Param("caseId")String caseId);
	
	@Select("select * from tjy_access where anjianId=#{caseId} and fairworkerId=#{workerId}")
	List<Tjy_access> getTjyAccessList(@Param("caseId")String caseId,@Param("workerId")String workerId);
	
	@Select("select * from tjy_access where tjy_accessId in(select DISTINCT tjy_accessId from zzlog where anjianId=#{caseId} and reciveuserId=#{userId})")
	List<Tjy_access> getDsrTjyAccessList(@Param("caseId")String caseId,@Param("userId")String userId);
	
	int addWaitTJYAccess(@Param("workId")String workId,@Param("accessId")String accessId,@Param("accessWaitId")String accessWaitId);
}
