package com.xzx.dao;

import com.xzx.model.Access_tjyupload;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-08-26
 */
public interface Access_tjyuploadMapper extends BaseMapper<Access_tjyupload> {
	@Insert({ "insert into access_tjyupload(accessurl, fairworkerId, accessname, anjianId) "
    		+ "values(#{accessurl}, #{fairworkerId}, #{accessname},#{anjianId})" })
    @Options(useGeneratedKeys = true, keyProperty = "access_tjyId")
	int addUploadAccess(Access_tjyupload access_tjyupload);
	
	@Delete("delete from access_tjyupload where fairworkerId=#{fairworkerId} and anjianId=#{caseId}")
	int delUploadAccess(@Param("fairworkerId")String fairworkerId,@Param("caseId")String caseId);
	
	@Select("select * from access_tjyupload where fairworkerId=#{fairworkerId} and anjianId=#{caseId}")
	List<Access_tjyupload> getUploadAccess(@Param("caseId")String caseId,@Param("fairworkerId")String fairworkerId);
}
