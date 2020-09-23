package com.xzx.dao;

import com.xzx.model.Consulttype;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-12-26
 */
public interface ConsulttypeMapper extends BaseMapper<Consulttype> {
	//@Select("select * from consulttype where State=0 and ConsultRegionId=#{regionId} order by Createdate desc")
	List<Consulttype> getConsulttypeList(@Param("regionId") int regionId);
}
