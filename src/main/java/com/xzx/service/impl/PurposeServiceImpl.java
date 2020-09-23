package com.xzx.service.impl;

import com.xzx.model.Purpose;
import com.xzx.dao.PurposeMapper;
import com.xzx.service.IPurposeService;
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
public class PurposeServiceImpl extends ServiceImpl<PurposeMapper, Purpose> implements IPurposeService {
	@Autowired
	PurposeMapper purposeMapper;
	
	public List<Purpose> getPurposeList()
	{
		return purposeMapper.getPurposeList();
	}
}
