package com.xzx.service.impl;

import com.xzx.model.Applicanttype;
import com.xzx.dao.ApplicanttypeMapper;
import com.xzx.service.IApplicanttypeService;
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
 * @since 2019-09-26
 */
@Service
public class ApplicanttypeServiceImpl extends ServiceImpl<ApplicanttypeMapper, Applicanttype> implements IApplicanttypeService {
	@Autowired
	ApplicanttypeMapper applicanttypeMapper;
	
	public Applicanttype getApplicanttypeInfo(String Title)
	{
		return applicanttypeMapper.getApplicanttypeInfo(Title);
	}
	
	public List<Applicanttype> getApplicanttypeList()
	{
		return applicanttypeMapper.getApplicanttypeList();
	}
}
