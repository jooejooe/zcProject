package com.xzx.dao;

import com.xzx.model.Docupload;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-12-16
 */
public interface DocuploadMapper extends BaseMapper<Docupload> {
	List<Docupload> getDocUploadList(@Param("title") String title,@Param("token") String token,@Param("modelType") int modelType);
	
	@Select("select * from docupload where docId=#{id}")
	Docupload getDocUploadById(@Param("id") String id);
}
