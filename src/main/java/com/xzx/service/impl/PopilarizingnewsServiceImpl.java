package com.xzx.service.impl;

import com.xzx.model.Popilarizingnews;
import com.xzx.dao.PopilarizingnewsMapper;
import com.xzx.service.IPopilarizingnewsService;
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
 * @since 2019-12-12
 */
@Service
public class PopilarizingnewsServiceImpl extends ServiceImpl<PopilarizingnewsMapper, Popilarizingnews> implements IPopilarizingnewsService {
	@Autowired
	PopilarizingnewsMapper popilarizingnewsMapper;
	
	public List<Popilarizingnews> getPopNews(String title)
	{
		return popilarizingnewsMapper.getPopNews(title);
	}
	
	public Popilarizingnews getNewsById(String id)
	{
		return popilarizingnewsMapper.getNewsById(id);
	}
}
