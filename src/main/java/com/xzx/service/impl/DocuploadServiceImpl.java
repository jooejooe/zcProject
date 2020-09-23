package com.xzx.service.impl;

import com.xzx.model.Docupload;
import com.xzx.dao.DocuploadMapper;
import com.xzx.service.IDocuploadService;
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
 * @since 2019-12-16
 */
@Service
public class DocuploadServiceImpl extends ServiceImpl<DocuploadMapper, Docupload> implements IDocuploadService {
	@Autowired
	DocuploadMapper docuploadMapper;
	
	public List<Docupload> getDocUploadList(String title,String token,int modelType)
	{
		return docuploadMapper.getDocUploadList(title, token,modelType);
	}

	public Docupload getDocUploadById(String id)
	{
		return docuploadMapper.getDocUploadById(id);
	}
}
