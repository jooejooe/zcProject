package com.xzx.service.impl;

import com.xzx.model.Useplace;
import com.xzx.dao.UseplaceMapper;
import com.xzx.service.IUseplaceService;
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
 * @since 2019-09-23
 */
@Service
public class UseplaceServiceImpl extends ServiceImpl<UseplaceMapper, Useplace> implements IUseplaceService {
	@Autowired
	UseplaceMapper useplaceMapper;
	
	public List<Useplace> getUsePlaceList()
	{
		return useplaceMapper.getUsePlaceList();
	}
}
