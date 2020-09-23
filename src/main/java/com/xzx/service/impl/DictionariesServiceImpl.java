package com.xzx.service.impl;

import com.xzx.model.Dictionaries;
import com.xzx.dao.DictionariesMapper;
import com.xzx.service.IDictionariesService;
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
 * @since 2019-09-25
 */
@Service
public class DictionariesServiceImpl extends ServiceImpl<DictionariesMapper, Dictionaries> implements IDictionariesService {
	@Autowired
	DictionariesMapper dictionariesMapper;
	public List<Dictionaries> getDictionaryList(String DictionariesTypeId)
	{
		return dictionariesMapper.getDictionaryList(DictionariesTypeId);
	}
}
