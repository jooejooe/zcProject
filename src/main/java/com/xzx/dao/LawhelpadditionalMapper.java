package com.xzx.dao;

import com.xzx.model.Lawhelpadditional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-10-09
 */
public interface LawhelpadditionalMapper extends BaseMapper<Lawhelpadditional> {
	
	@Insert("insert into lawhelpadditional (lawhelpId,options) values(#{lawhelpId},#{options})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int addSelectOptions(Lawhelpadditional lawhelpadditional);
}
