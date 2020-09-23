package com.xzx.dao;

import com.xzx.model.Docapply;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-12-20
 */
public interface DocapplyMapper extends BaseMapper<Docapply> {
	@Insert({ "insert into docapply(name, sfzh, telephone, createUser, createTime , modelType) "
    		+ "values(#{name}, #{sfzh}, #{telephone}, #{createUser},now(),#{modelType})" })
    @Options(useGeneratedKeys = true, keyProperty = "id")
	int addDocApply(Docapply docapply);
}
