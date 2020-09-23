package com.xzx.service.impl;

import com.xzx.model.News;
import com.xzx.dao.NewsMapper;
import com.xzx.service.INewsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-10-08
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {
	@Autowired
	NewsMapper newsMapper;
	
	public List<News> getNewsByParam(String newsType,String token)
	{
		return newsMapper.getNewsByParam(newsType, token);
	}
	
	public News getNewsById(String id)
	{
		return newsMapper.getNewsById(id);
	}
	
	public List<News> getPCNewsByParam(String title,int recommendType,int newsType,int NewsWorkModel)
	{
		return newsMapper.getPCNewsByParam(title, recommendType, newsType,NewsWorkModel);
	}
	
	public List<News> getLawExamListByParam(int recommendType,String token)
	{
		return newsMapper.getLawExamListByParam(recommendType, token);
	}
}
