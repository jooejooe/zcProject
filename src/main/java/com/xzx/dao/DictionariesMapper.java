package com.xzx.dao;

import com.xzx.model.Dictionaries;

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
 * @since 2019-09-25
 */
public interface DictionariesMapper extends BaseMapper<Dictionaries> {
	@Select("select * from dictionaries where DictionariesTypeId=#{DictionariesTypeId}")
	List<Dictionaries> getDictionaryList(@Param("DictionariesTypeId")String DictionariesTypeId);
}
