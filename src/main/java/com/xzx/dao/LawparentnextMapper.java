package com.xzx.dao;

import com.xzx.model.Lawparentnext;

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
public interface LawparentnextMapper extends BaseMapper<Lawparentnext> {
	@Select("select * from lawparentnext where lawParentId=#{parentId}")
	List<Lawparentnext> getOptionsById(@Param("parentId")String parentId);
}
