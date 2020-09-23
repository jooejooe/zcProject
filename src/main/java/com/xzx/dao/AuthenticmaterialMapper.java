package com.xzx.dao;

import com.xzx.model.Authenticmaterial;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-09-26
 */
public interface AuthenticmaterialMapper extends BaseMapper<Authenticmaterial> {
	@Select("select * from authenticmaterial where State=0 and assistanceId=#{assistanceId} order by CreateDate desc limit 1")
	Authenticmaterial getAuthenticmaterialInfo(@Param("assistanceId")String assistanceId);
}
