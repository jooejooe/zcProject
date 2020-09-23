package com.xzx.dao;

import com.xzx.model.Applicanttype;

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
 * @since 2019-09-26
 */
public interface ApplicanttypeMapper extends BaseMapper<Applicanttype> {
	@Select("select * from applicanttype where State=0 and ApplicantTypeId=#{Title} order by CreateDate desc limit 1")//Title=#{Title}
	Applicanttype getApplicanttypeInfo(@Param("Title") String Title);
	
	@Select("SELECT * FROM (SELECT * FROM applicanttype WHERE State=0 ORDER BY CreateDate DESC limit 100000000)  a  where State=0 GROUP BY Title ORDER BY CreateDate desc")
	List<Applicanttype> getApplicanttypeList();
}
