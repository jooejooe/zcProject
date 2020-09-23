package com.xzx.dao;

import com.xzx.model.Appversion;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-01-09
 */
public interface AppversionMapper extends BaseMapper<Appversion> {
	@Select("select * from appversion where type=#{type}")
	Appversion getAppInfo(@Param("type")String type);
}
