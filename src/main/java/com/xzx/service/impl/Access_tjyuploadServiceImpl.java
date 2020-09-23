package com.xzx.service.impl;

import com.xzx.model.Access_tjyupload;
import com.xzx.dao.Access_tjyuploadMapper;
import com.xzx.service.IAccess_tjyuploadService;
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
 * @since 2020-08-26
 */
@Service
public class Access_tjyuploadServiceImpl extends ServiceImpl<Access_tjyuploadMapper, Access_tjyupload> implements IAccess_tjyuploadService {
	@Autowired
	Access_tjyuploadMapper access_tjyuploadMapper;
	
	public List<Access_tjyupload> getUploadAccess(String caseId,String fairworkerId)
	{
		return access_tjyuploadMapper.getUploadAccess(caseId, fairworkerId);
	}
}
