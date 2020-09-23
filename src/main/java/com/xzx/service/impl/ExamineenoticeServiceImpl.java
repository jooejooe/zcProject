package com.xzx.service.impl;

import com.xzx.model.Examineenotice;
import com.xzx.dao.ExamineenoticeMapper;
import com.xzx.service.IExamineenoticeService;
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
 * @since 2019-12-17
 */
@Service
public class ExamineenoticeServiceImpl extends ServiceImpl<ExamineenoticeMapper, Examineenotice> implements IExamineenoticeService {
	@Autowired
	ExamineenoticeMapper examineenoticeMapper;
	
	public List<Examineenotice> getExamineeNotice()
	{
		return examineenoticeMapper.getExamineeNotice();
	}
	
	public Examineenotice getExamineeNoticeById(String id)
	{
		return examineenoticeMapper.getExamineeNoticeById(id);
	}
}
