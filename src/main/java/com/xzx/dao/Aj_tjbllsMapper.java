package com.xzx.dao;

import com.xzx.model.Aj_tjblls;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-08-27
 */
public interface Aj_tjbllsMapper extends BaseMapper<Aj_tjblls> {
	@Insert({ "insert into aj_tjblls(blnr, anjianId, reciveuserId) "
    		+ "values(#{blnr}, #{anjianId}, #{reciveuserId})" })
    @Options(useGeneratedKeys = true, keyProperty = "aj_tjblls_Id")
	int addTjblTemp(Aj_tjblls aj_tjblls);
	
	@Update("update aj_tjblls set blnr=concat(blnr,#{blnr}) where anjianId=#{anjianId} and reciveuserId=#{reciveuserId}")
	int updateTjblTemp(Aj_tjblls aj_tjblls);
	
	@Select("select * from aj_tjblls where anjianId=#{caseId} and reciveuserId=#{userId} limit 1")
	Aj_tjblls getTjblTemp(@Param("caseId")String caseId,@Param("userId")String userId);
	
	@Select("select blnr from aj_tjblls where aj_tjblls_Id=#{tjblTempId}")
	String getTjblTempById(@Param("tjblTempId")String tjblTempId);
	
	@Delete("delete from aj_tjblls where anjianId=#{caseId} and reciveuserId=#{userId}")
	int delTjblTemp(@Param("caseId")String caseId,@Param("userId")String userId);
}
