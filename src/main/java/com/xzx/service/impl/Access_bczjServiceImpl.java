package com.xzx.service.impl;

import com.xzx.model.Access_bczj;
import com.xzx.dao.Access_bczjMapper;
import com.xzx.dao.AuthenticMapper;
import com.xzx.service.IAccess_bczjService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-04-23
 */
@Service
public class Access_bczjServiceImpl extends ServiceImpl<Access_bczjMapper, Access_bczj> implements IAccess_bczjService {
	@Autowired
	Access_bczjMapper access_bczjMapper;
	
	@Autowired
	AuthenticMapper authenticMapper;

	public List<Access_bczj> getAccessByItemId(String ModelId,String ModelType)
	{
		return access_bczjMapper.getAccessByItemId(ModelId, ModelType);
	}

	@Transactional
	public int insertBatchAccessZJ(List<Access_bczj> list,String ModelId,String ModelType)
	{
		int flag1=access_bczjMapper.delAccessZJ(ModelId, ModelType);
		int flag2=access_bczjMapper.insertBatchAccessZJ(list,ModelId,ModelType);
		int flag3=authenticMapper.qzAuthentic(ModelId);

		if(flag1>=0&&flag2>0&&flag3>0)
			return 1;
		else
			return 0;
	}
}
