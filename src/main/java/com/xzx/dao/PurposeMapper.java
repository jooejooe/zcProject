package com.xzx.dao;

import com.xzx.model.Purpose;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public interface PurposeMapper extends BaseMapper<Purpose> {
	
	@Select("select * from purpose where State=0 order by CreateDate desc")
	List<Purpose> getPurposeList();
}
