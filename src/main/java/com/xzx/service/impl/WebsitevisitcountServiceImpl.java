package com.xzx.service.impl;

import com.xzx.model.Websitevisitcount;
import com.xzx.dao.WebsitevisitcountMapper;
import com.xzx.service.IWebsitevisitcountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-02-13
 */
@Service
public class WebsitevisitcountServiceImpl extends ServiceImpl<WebsitevisitcountMapper, Websitevisitcount> implements IWebsitevisitcountService {
	@Autowired
	WebsitevisitcountMapper websitevisitcountMapper;
	
	public Websitevisitcount getWebsiteVisitInfo()
	{
		return websitevisitcountMapper.getWebsiteVisitInfo();
	}
	
	public int updateVisitCount(Websitevisitcount websitevisitcount) 
	{
		return websitevisitcountMapper.updateVisitCount(websitevisitcount);
	}
}
