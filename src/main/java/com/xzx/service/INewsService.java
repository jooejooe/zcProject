package com.xzx.service;

import com.xzx.model.News;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-10-08
 */
public interface INewsService extends IService<News> {
	List<News> getNewsByParam(String newsType,String token);
	
	News getNewsById(String id);
	
	List<News> getPCNewsByParam(String title,int recommendType,int newsType,int NewsWorkModel);
	
	List<News> getLawExamListByParam(int recommendType,String token);
}
