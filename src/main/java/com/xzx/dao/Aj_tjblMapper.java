package com.xzx.dao;

import com.xzx.model.Aj_tjbl;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-08-27
 */
public interface Aj_tjblMapper extends BaseMapper<Aj_tjbl> {
	@Insert({ "insert into aj_tjbl(anjianId, userId, tjblinfo,createdate,fairworkerId,state,reason) "
    		+ "values(#{anjianId}, #{userId}, #{tjblinfo},now(),#{fairworkerId},#{state},#{reason})" })
    @Options(useGeneratedKeys = true, keyProperty = "anjian_tjbl_Id")
	int addTjbl(Aj_tjbl aj_tjbl);
}
