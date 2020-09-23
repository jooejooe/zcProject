package com.xzx.dao;

import com.xzx.model.Websitevisitcount;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-02-13
 */
public interface WebsitevisitcountMapper extends BaseMapper<Websitevisitcount> {
	@Select("SELECT * FROM websitevisitcount limit 1")
	Websitevisitcount getWebsiteVisitInfo();
	
	int updateVisitCount(Websitevisitcount websitevisitcount);
}
