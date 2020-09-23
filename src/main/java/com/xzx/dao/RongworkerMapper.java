package com.xzx.dao;

import com.xzx.model.Rongworker;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-05-18
 */
public interface RongworkerMapper extends BaseMapper<Rongworker> {
	List<Rongworker> getRyWorkerList(@Param("regionId")String regionId,@Param("typeId")String typeId,@Param("departId")String departId);
}
