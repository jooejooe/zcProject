package com.xzx.service.impl;

import com.xzx.model.Document;
import com.xzx.dao.DocumentMapper;
import com.xzx.service.IDocumentService;
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
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements IDocumentService {
	@Autowired
	DocumentMapper documentMapper;
	
	public List<Document> getPersonalDocsByParam(String sfzh,int modelType)
	{
		return documentMapper.getPersonalDocsByParam(sfzh,modelType);
	}
	
	public int matchItemCount(String sfzh,String name,String tel,String caseNo,int modelType)
	{
		return documentMapper.matchItemCount(sfzh, name, tel,caseNo,modelType);
	}
	
	public List<Document> getPersonalDocsByManual(String sfzh,String name,String tel,String caseNo,int modelType)
	{
		return documentMapper.getPersonalDocsByManual(sfzh, name, tel, caseNo,modelType);
	}
	
	public Document getDocById(String id)
	{
		return documentMapper.getDocById(id);
	}
	
	public int matchItemCountByDepartSearch(String sfzh,String name,String tel,int modelType)
	{
		return documentMapper.matchItemCountByDepartSearch(sfzh, name, tel,modelType);
	}
	
	public List<Document> getPersonalDocsByDepartment(String sfzh,String name,String tel,int modelType)
	{
		return documentMapper.getPersonalDocsByDepartment(sfzh, name, tel,modelType);
	}
	
	public int matchPersonalDocCount(String sfzh,int modelType)
	{
		return documentMapper.matchPersonalDocCount(sfzh, modelType);
	}
	
	public int addDoc(Document document)
	{
		return documentMapper.addDoc(document);
	}
	
	/**
	 * 获取相应案件编号存在文书的数量
	 */
	public int getCaseNoCount(String caseNo,String DocId)
	{
		return documentMapper.getCaseNoCount(caseNo,DocId);
	}
	
	public int updateDoc(Document document)
	{
		return documentMapper.updateDoc(document);
	}
}
