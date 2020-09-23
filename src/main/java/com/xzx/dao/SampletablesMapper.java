package com.xzx.dao;

import com.xzx.model.Sampletables;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-12-17
 */
public interface SampletablesMapper extends BaseMapper<Sampletables> {
	List<Sampletables> getSampleTablesList(@Param("modelType") int modelType,@Param("title") String title,@Param("token") String token);
	
	@Select("select * from sampletables where id=#{id}")
	Sampletables getSampletablesById(@Param("id") String id);
}
