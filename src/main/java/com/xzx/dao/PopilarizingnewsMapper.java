package com.xzx.dao;

import com.xzx.model.Popilarizingnews;

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
public interface PopilarizingnewsMapper extends BaseMapper<Popilarizingnews> {
	//@Select("select * from popilarizingnews where IsRos=0 order by CreateDate desc")
	List<Popilarizingnews> getPopNews(@Param("title")String title);
	
	@Select("select * from popilarizingnews where PopularizingNewsId=#{id} and IsRos=0")
	Popilarizingnews getNewsById(@Param("id")String id);
}
