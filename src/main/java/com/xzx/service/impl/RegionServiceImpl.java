package com.xzx.service.impl;

import com.xzx.model.Region;
import com.xzx.dao.RegionMapper;
import com.xzx.service.IRegionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {
	@Autowired
	private RegionMapper regionMapper;
	
	@Value("${regionTopCode}")
	int regionTopCode;
	
	public List<Region> getRegionList(int ParentId)
	{
		return regionMapper.getRegionList(ParentId);
	}
	
	public Region getTopRegionName(String token,String type)
	{
		return regionMapper.getTopRegionName(token,type);
	}
	
	public Region getRegionByToken(String token)
	{
		return regionMapper.getRegionByToken(token);
	}

	public List<Region> getSubRegionListById(String regionId)
	{
		return regionMapper.getSubRegionListById(regionId);
	}
	
	public Region getRegionById(String regionId)
	{
		return regionMapper.getRegionById(regionId);
	}
	
	public int getTopRegionId(String param,int type)
	{
		int regionId=0;
		
		if(type==0)//token
		{
			Region region=regionMapper.getRegionByToken(param);
			
			if(region!=null)
			{
				if(region.getRegionCode().length()==regionTopCode)
				{
					regionId=region.getRegionId();
				}
				else
				{
					Region topRegion=regionMapper.getTopRegionInfo(Integer.toString(region.getRegionId()));
					
					if(topRegion.getRegionCode().length()==regionTopCode)
						regionId=topRegion.getRegionId();
				}
			}
		}
		else
		{
			Region region=regionMapper.getRegionById(param);
			
			if(region!=null)
			{
				if(region.getRegionCode().length()==regionTopCode)
					regionId=region.getRegionId();
				else
				{
					Region topRegion=regionMapper.getTopRegionInfo(Integer.toString(region.getRegionId()));
					regionId=topRegion.getRegionId();
				}
			}			
		}
		
		return regionId;
	}
	
	public List<Region> getRYRegionList()
	{
		return regionMapper.getRYRegionList();
	}
}
