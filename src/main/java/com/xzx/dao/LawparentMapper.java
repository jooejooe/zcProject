package com.xzx.dao;

import com.xzx.model.Lawparent;

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
 * @since 2019-10-09
 */
public interface LawparentMapper extends BaseMapper<Lawparent> {
	
	@Select("SELECT * FROM lawparent where assistanceId=#{assistanceId} and IsDel=0 order by helpSort")
	List<Lawparent> getQuestionsByType(@Param("assistanceId")String assistanceId);
}
