package com.xzx.service;

import com.xzx.model.Region;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public interface IRegionService extends IService<Region> {
	List<Region> getRegionList(int ParentId);
	
	Region getTopRegionName(String token,String type);
	
	Region getRegionByToken(String token);

	List<Region> getSubRegionListById(String regionId);
	
	Region getRegionById(String regionId);
	
	int getTopRegionId(String param,int type);
	
	List<Region> getRYRegionList();
}
