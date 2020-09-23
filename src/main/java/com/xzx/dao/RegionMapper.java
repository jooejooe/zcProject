package com.xzx.dao;

import com.xzx.model.Region;

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
 * @since 2019-09-23
 */
public interface RegionMapper extends BaseMapper<Region> {
	List<Region> getRegionList(@Param("ParentId") int ParentId);
	
	@Select("select * from region where RegionId=(SELECT ParentId FROM region where RegionId=#{regionId}  limit 1)")
	Region getTopRegionInfo(@Param("regionId") String regionId);
	
	@Select("select * from region where RegionId=(select Region from equipment where token=#{token} limit 1)")
	Region getRegionByToken(@Param("token") String token);
	
	@Select("select * FROM region where ParentId=#{regionId}")
	List<Region> getSubRegionListById(@Param("regionId") String regionId);
	
	@Select("select * FROM region where RegionId=#{regionId}")
	Region getRegionById(@Param("regionId") String regionId);
	
	//@Select("select * from region where RegionId=(SELECT ParentId FROM region where RegionId=(select Region from equipment where token=#{token} limit 1) limit 1)")
	Region getTopRegionName(@Param("token") String token,@Param("type") String type);
	
	@Select("select * from region where RegionId in (select RegionId from rongworker)")
	List<Region> getRYRegionList();
}
