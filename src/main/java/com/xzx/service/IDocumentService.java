package com.xzx.service;

import com.xzx.model.Document;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-12-16
 */
public interface IDocumentService extends IService<Document> {
	int matchItemCount(String sfzh,String name,String tel,String caseNo,int modelType);
	
	List<Document> getPersonalDocsByParam(String sfzh,int modelType);
	
	List<Document> getPersonalDocsByManual(String sfzh,String name,String tel,String caseNo,int modelType);
	
	int matchItemCountByDepartSearch(String sfzh,String name,String tel,int modelType);
	
	List<Document> getPersonalDocsByDepartment(String sfzh,String name,String tel,int modelType);
	
	int matchPersonalDocCount(String sfzh,int modelType);
	
	Document getDocById(String id);
	
	int addDoc(Document document);
	
	/**
	 * 获取相应案件编号存在文书的数量
	 */
	int getCaseNoCount(String caseNo,String DocId);
	
	int updateDoc(Document document);
}
