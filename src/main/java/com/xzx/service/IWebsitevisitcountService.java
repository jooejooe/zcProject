package com.xzx.service;

import com.xzx.model.Websitevisitcount;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-02-13
 */
public interface IWebsitevisitcountService extends IService<Websitevisitcount> {
	Websitevisitcount getWebsiteVisitInfo();
	
	int updateVisitCount(Websitevisitcount websitevisitcount);
}
