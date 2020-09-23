package com.xzx.service.impl;

import com.xzx.model.Newstype;
import com.xzx.dao.NewstypeMapper;
import com.xzx.service.INewstypeService;
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
public class NewstypeServiceImpl extends ServiceImpl<NewstypeMapper, Newstype> implements INewstypeService {
	@Autowired
	NewstypeMapper newstypeMapper;
	public List<Newstype> getNewsType(String NewsWorkModel)
	{
		return newstypeMapper.getNewsType(NewsWorkModel);
	}
}
