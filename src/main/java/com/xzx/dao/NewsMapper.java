package com.xzx.dao;

import com.xzx.model.News;

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
 * @since 2019-10-08
 */
public interface NewsMapper extends BaseMapper<News> {
	
	@Select("SELECT a.* FROM news a left join equipment b on a.AreaId=b.region where NewsTypeId=#{newsType} and b.token=#{token} and IsRecommend=0 and IsAvailable=0 order by DisplayTime desc")
	List<News> getNewsByParam(@Param("newsType")String newsType,@Param("token")String token);
	
	@Select("select * from news where NewsId=#{id} and IsRecommend=0 and IsAvailable=0")
	News getNewsById(@Param("id")String id);
	
	List<News> getPCNewsByParam(@Param("title") String title,@Param("recommendType") int recommendType,@Param("newsType") int newsType,@Param("NewsWorkModel") int NewsWorkModel);
	
	List<News> getLawExamListByParam(@Param("recommendType") int recommendType,@Param("token") String token);
}
