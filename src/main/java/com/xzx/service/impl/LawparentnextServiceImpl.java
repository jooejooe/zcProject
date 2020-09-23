package com.xzx.service.impl;

import com.xzx.model.Lawparentnext;
import com.xzx.dao.LawparentnextMapper;
import com.xzx.service.ILawparentnextService;
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
 * @since 2019-10-09
 */
@Service
public class LawparentnextServiceImpl extends ServiceImpl<LawparentnextMapper, Lawparentnext> implements ILawparentnextService {
	@Autowired
	LawparentnextMapper lawparentnextMapper;
	
	public List<Lawparentnext> getOptionsById(String parentId)
	{
		return lawparentnextMapper.getOptionsById(parentId);
	}
}
