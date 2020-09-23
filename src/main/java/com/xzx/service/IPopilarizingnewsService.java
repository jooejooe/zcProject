package com.xzx.service;

import com.xzx.model.Popilarizingnews;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-12-12
 */
public interface IPopilarizingnewsService extends IService<Popilarizingnews> {
	List<Popilarizingnews> getPopNews(String title);
	
	Popilarizingnews getNewsById(String id);
}
