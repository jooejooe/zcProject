package com.xzx.service.impl;

import com.xzx.model.Popliarizingvideo;
import com.xzx.dao.PopliarizingvideoMapper;
import com.xzx.service.IPopliarizingvideoService;
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
public class PopliarizingvideoServiceImpl extends ServiceImpl<PopliarizingvideoMapper, Popliarizingvideo> implements IPopliarizingvideoService {
	@Autowired
	PopliarizingvideoMapper popliarizingvideoMapper;
	public List<Popliarizingvideo> getNewsByParam(String newsType,String title)
	{
		return popliarizingvideoMapper.getNewsByParam(newsType,title);
	}
	
	public Popliarizingvideo getVideoNewsById(String id)
	{
		return popliarizingvideoMapper.getVideoNewsById(id);
	}
}
