package com.xzx.dao;

import com.xzx.model.Access_wait;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-08-27
 */
public interface Access_waitMapper extends BaseMapper<Access_wait> {
	@Insert({ "insert into access_wait(userId, uploaddate, accessurl, anjianId,accessname,usertype) "
    		+ "values(#{userId}, now(), #{accessurl},#{anjianId},#{accessname},#{usertype})" })
    @Options(useGeneratedKeys = true, keyProperty = "access_waitId")
	int addAccessWait(Access_wait access_wait);
	
	List<Access_wait> getWaitAccessList(@Param("caseId")String caseId,@Param("userId")String userId);
	
	@Delete("delete from access_wait where access_waitId=#{accessWaitId}")
	int delAccessWait(@Param("accessWaitId")String accessWaitId);
}
