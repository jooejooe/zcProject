package com.xzx.dao;

import com.xzx.model.Fy_sp;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-04-24
 */
public interface Fy_spMapper extends BaseMapper<Fy_sp> {
	@Insert({ "insert into fy_sp(fy_ajId, js_tjjacgfkh, js_rmtjxys, js_tjbl,js_sdsx,zh_tjbl,zh_tjjabcgfkh,ssyd_tjbl,ssyd_tjjabcgfkh) "
    		+ "values(#{fy_ajId}, #{js_tjjacgfkh}, #{js_rmtjxys},#{js_tjbl},#{js_sdsx},#{zh_tjbl},#{zh_tjjabcgfkh},#{ssyd_tjbl},#{ssyd_tjjabcgfkh})" })
    @Options(useGeneratedKeys = true, keyProperty = "fy_spId")
	int addSP(Fy_sp fy_sp);
	
	int updateSP(Fy_sp fy_sp);
	
	@Select("select * from fy_sp where fy_ajId=#{caseId}")
	Fy_sp getSPInfoByCaseId(@Param("caseId")String caseId);
}
