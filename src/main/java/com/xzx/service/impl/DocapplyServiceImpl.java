package com.xzx.service.impl;

import com.xzx.model.Docapply;
import com.xzx.dao.DocapplyMapper;
import com.xzx.service.IDocapplyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-12-20
 */
@Service
public class DocapplyServiceImpl extends ServiceImpl<DocapplyMapper, Docapply> implements IDocapplyService {
	@Autowired
	DocapplyMapper docapplyMapper;
	
	public Boolean addDocApply(Docapply docapply)
	{
		int flag=docapplyMapper.addDocApply(docapply);
		
		if(docapply.getId()>0&&flag>0)
			return true;
		else
			return false;
	}
}
