package com.xzx.service.impl;

import com.xzx.model.Lawparent;
import com.xzx.dao.LawparentMapper;
import com.xzx.service.ILawparentService;
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
public class LawparentServiceImpl extends ServiceImpl<LawparentMapper, Lawparent> implements ILawparentService {
	@Autowired
	LawparentMapper lawparentMapper;
	
	public List<Lawparent> getQuestionsByType(String assistanceId)
	{
		return lawparentMapper.getQuestionsByType(assistanceId);
	}
}
