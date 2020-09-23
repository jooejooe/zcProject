package com.xzx.dao;

import com.xzx.model.Subscribetypematerial;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-10-30
 */
public interface SubscribetypematerialMapper extends BaseMapper<Subscribetypematerial> {
	@Select("select * from subscribetypematerial where State=0 and subscribeTypeId=#{subscribeType} order by CreateDate desc limit 1")
	Subscribetypematerial getSubscribetypematerialInfo(@Param("subscribeType")String subscribeType);
}
