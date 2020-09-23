package com.xzx.dao;

import com.xzx.model.Appraisalmaterial;

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
public interface AppraisalmaterialMapper extends BaseMapper<Appraisalmaterial> {
	@Select("select * from appraisalmaterial where State=0 and assistanceId=#{assistanceId} order by CreateDate desc limit 1")
	Appraisalmaterial getAppraisalmaterialInfo(@Param("assistanceId")String assistanceId);
}
