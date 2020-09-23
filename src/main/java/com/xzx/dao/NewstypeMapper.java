package com.xzx.dao;

import com.xzx.model.Newstype;

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
 * @since 2019-10-08
 */
public interface NewstypeMapper extends BaseMapper<Newstype> {
	
	@Select("select * from newstype where NewsWorkModel=#{NewsWorkModel} and state=0 order by NewsTypeId")
	List<Newstype> getNewsType(@Param("NewsWorkModel") String NewsWorkModel);
}
