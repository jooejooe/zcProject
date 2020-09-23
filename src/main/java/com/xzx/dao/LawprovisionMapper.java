package com.xzx.dao;

import com.xzx.model.Lawprovision;

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
 * @since 2019-12-17
 */
public interface LawprovisionMapper extends BaseMapper<Lawprovision> {
	List<Lawprovision> getLawProvisionList(@Param("modelType") int modelType,@Param("title") String title,@Param("token") String token);
	
	@Select("select * from lawprovision where id=#{id}")
	Lawprovision getLawprovisionById(@Param("id") String id);
}
