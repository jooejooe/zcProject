package com.xzx.dao;

import com.xzx.model.Access_bczj;

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
 * @since 2020-04-23
 */
public interface Access_bczjMapper extends BaseMapper<Access_bczj> {
	@Select("select * from access_bczj where ajId=#{ModelId} and Type=#{ModelType}")
	List<Access_bczj> getAccessByItemId(@Param("ModelId") String ModelId,@Param("ModelType") String ModelType);

	int insertBatchAccessZJ(@Param("list")List<Access_bczj> list,@Param("ModelId")String ModelId,@Param("ModelType")String ModelType);
	
	@Delete("delete from access_bczj where ajId=#{ModelId} and Type=#{ModelType}")
	int delAccessZJ(@Param("ModelId")String ModelId,@Param("ModelType")String ModelType);
}
