package com.xzx.dao;

import com.xzx.model.Dzjz;

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
 * @since 2020-04-24
 */
public interface DzjzMapper extends BaseMapper<Dzjz> {
	@Insert({ "insert into dzjz(type, ajId, jzbh, cjr,cjsj) "
    		+ "values(#{type}, #{ajId}, #{jzbh},#{cjr},now())" })
    @Options(useGeneratedKeys = true, keyProperty = "jzId")
	int addJZ(Dzjz dzjz);
	
	@Select("select count(1) from dzjz where jzbh=#{jzbh} and jzId!=#{jzId}")
	int getCaseNoCount(@Param("jzbh")String jzbh,@Param("jzId")String jzId);
	
	@Update("update dzjz set jzbh=#{jzbh} where jzId=#{jzId}")
	int updateJZ(Dzjz dzjz);
	
	@Select("select * from dzjz where type=#{type} and ajId=#{caseId} and jztyle=#{docType} order by cjsj desc limit 1")
	Dzjz getJZDocList(@Param("type")String type,@Param("caseId")String caseId,@Param("docType")String docType);
}
