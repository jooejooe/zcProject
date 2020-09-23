package com.xzx.dao;

import com.xzx.model.Popliarizingvideo;

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
 * @since 2019-12-12
 */
public interface PopliarizingvideoMapper extends BaseMapper<Popliarizingvideo> {
	//@Select("SELECT * FROM popliarizingvideo where NewsTypeId=#{newsType} and IsRos=0 order by CreateDate desc")
	List<Popliarizingvideo> getNewsByParam(@Param("newsType")String newsType,@Param("title") String title);
	
	@Select("select * from popliarizingvideo where PopliarizingVideoId=#{id} and IsRos=0")
	Popliarizingvideo getVideoNewsById(@Param("id")String id);
}
