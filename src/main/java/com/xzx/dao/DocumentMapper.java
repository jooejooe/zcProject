package com.xzx.dao;

import com.xzx.model.Document;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-12-16
 */
public interface DocumentMapper extends BaseMapper<Document> {

	/**
	 * 个人查询
	 * @param sfzh
	 * @return
	 */
	@Select("select * from document where (PartyAIdCard like CONCAT('%',#{sfzh},'%')  or PartyBIdCard like CONCAT('%',#{sfzh},'%')) and DocType=#{modelType} order by CreateDate desc")
	List<Document> getPersonalDocsByParam(@Param("sfzh")String sfzh,@Param("modelType") int modelType);
	
	/**
	 * 个人查询数量
	 */
	@Select("select count(1) from document where (PartyAIdCard like CONCAT('%',#{sfzh},'%')  or PartyBIdCard like CONCAT('%',#{sfzh},'%')) and DocType=#{modelType}")
	int matchPersonalDocCount(@Param("sfzh")String sfzh,@Param("modelType") int modelType);
	
	/**
	 * 人工查询数量
	 * @param sfzh
	 * @param name
	 * @param tel
	 * @return
	 */
	int matchItemCount(@Param("sfzh") String sfzh,@Param("name") String name,@Param("tel") String tel,@Param("caseNo") String caseNo,@Param("modelType") int modelType);
	
	/**
	 * 人工查询列表
	 * @param sfzh
	 * @param name
	 * @param tel
	 * @param caseNo
	 * @param modelType
	 * @return
	 */
	List<Document> getPersonalDocsByManual(@Param("sfzh") String sfzh,@Param("name") String name,@Param("tel") String tel,@Param("caseNo") String caseNo,@Param("modelType") int modelType);
	
	/**
	 * 司法机关查询数量
	 * @param sfzh
	 * @param name
	 * @param tel
	 * @return
	 */
	int matchItemCountByDepartSearch(@Param("sfzh") String sfzh,@Param("name") String name,@Param("tel") String tel,@Param("modelType") int modelType);
	
	/**
	 * 司法机关查询列表
	 * @param sfzh
	 * @param name
	 * @param tel
	 * @param modelType
	 * @return
	 */
	List<Document> getPersonalDocsByDepartment(@Param("sfzh") String sfzh,@Param("name") String name,@Param("tel") String tel,@Param("modelType") int modelType);
	
	/**
	 * 获取某一文书详情
	 * @param id
	 * @return
	 */
	@Select("select * from document where DocId=#{id}")
	Document getDocById(@Param("id") String id);
	
	/**
	 * 添加文书
	 * @param document
	 * @return
	 */
	@Insert({ "insert into document(Title, FileNumber, FilePath, KeyWord,PartyAName,PartyAIdCard,PartyAPhone,PartyBName,PartyBIdCard,PartyBPhone,PartyCName,PartyCIdCard,PartyCPhone,CreateDate,DocType,CaseId) "
    		+ "values(#{Title}, #{FileNumber}, #{FilePath}, #{KeyWord},#{PartyAName},#{PartyAIdCard},#{PartyAPhone},#{PartyBName},#{PartyBIdCard},#{PartyBPhone},#{PartyCName},#{PartyCIdCard},#{PartyCPhone},now(),#{DocType},#{CaseId})" })
    @Options(useGeneratedKeys = true, keyProperty = "DocId")
	int addDoc(Document document);
	
	/**
	 * 获取相应案件编号存在文书的数量
	 */
	@Select("select count(1) from document where FileNumber=#{caseNo} and DocId!=#{DocId}")
	int getCaseNoCount(@Param("caseNo")String caseNo,@Param("DocId")String DocId);
	
	@Update("update document set Title=#{Title},FileNumber=#{FileNumber},KeyWord=#{KeyWord},PartyCName=#{PartyCName},PartyCIdCard=#{PartyCIdCard},PartyCPhone=#{PartyCPhone},FilePath=#{FilePath} where DocId=#{DocId}")
	int updateDoc(Document document);
}
