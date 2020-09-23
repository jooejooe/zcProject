package com.xzx.dao;

import com.xzx.model.Useplace;

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
public interface UseplaceMapper extends BaseMapper<Useplace> {
	
	@Select("select * from useplace where State=0 order by CreateDate desc")
	List<Useplace> getUsePlaceList();
}
