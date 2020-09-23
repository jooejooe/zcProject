package com.xzx.service.impl;

import com.xzx.model.Appraisalmaterial;
import com.xzx.dao.AppraisalmaterialMapper;
import com.xzx.service.IAppraisalmaterialService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-26
 */
@Service
public class AppraisalmaterialServiceImpl extends ServiceImpl<AppraisalmaterialMapper, Appraisalmaterial> implements IAppraisalmaterialService {
	
	@Autowired
	AppraisalmaterialMapper appraisalmaterialMapper;
	public Appraisalmaterial getAppraisalmaterialInfo(String assistanceId)
	{
		return appraisalmaterialMapper.getAppraisalmaterialInfo(assistanceId);
	}
}
