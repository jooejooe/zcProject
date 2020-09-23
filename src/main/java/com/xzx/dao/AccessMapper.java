package com.xzx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xzx.model.Access;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public interface AccessMapper extends BaseMapper<Access> {
	
	int insertBatchAccess(@Param("list")List<Access> list,@Param("justId")int justId,@Param("ModelType") int ModelType,@Param("AccessType") int AccessType);
	
	@Select("select * from access where ModelId=#{ModelId} and ModelType=#{ModelType}")
	List<Access> getAccessByItemId(@Param("ModelId") String ModelId,@Param("ModelType") String ModelType);
	
	@Select("select AccessId,AccessName from access where ModelId=#{ModelId} and ModelType=#{ModelType}")
	List<Access> getPartAccessByItemId(@Param("ModelId") String ModelId,@Param("ModelType") String ModelType);
	
	@Select("select AccessId,AccessName,(select count(1) from tjy_access where anjianId=a.ModelId and userId=a.UserId and usertype!=2 and accessid=a.AccessId) as AccessInfo,(select accessname from tjy_access where anjianId=a.ModelId and userId=a.UserId and usertype!=2 and accessid=a.AccessId) as tjyAccessName from access a where ModelId=#{ModelId} and ModelType=#{ModelType} and UserId=#{UserId}")
	List<Access> getAccessListByItemId(@Param("ModelId") String ModelId,@Param("ModelType") String ModelType,@Param("UserId")String UserId);

	@Insert({ "insert into access(AccessInfo, UserId, ModelType, ModelId,AccessName,AccessType) "
    		+ "values(#{AccessInfo}, #{UserId}, #{ModelType},#{ModelId},#{AccessName},#{AccessType})" })
    @Options(useGeneratedKeys = true, keyProperty = "AccessId")
	int addAccess(Access access);
}
