package com.xzx.dao;

import com.xzx.model.Zzlsb;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
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
 * @since 2020-08-27
 */
public interface ZzlsbMapper extends BaseMapper<Zzlsb> {
	@Insert({ "insert into zzlsb(anjianId, tjyaccessId, fairworkerid, state,reason,realname,bfuserId,zzsj) "
    		+ "values(#{anjianId}, #{tjyaccessId}, #{fairworkerid},#{state},#{reason},#{realname},#{bfuserId},now())" })
    @Options(useGeneratedKeys = true, keyProperty = "zzlsbId")
	int addZzlsb(Zzlsb zzlsb);
	
	@Select("SELECT count(1) as count,GROUP_CONCAT(zzlsbid) as ids FROM `zzlsb` where anjianid=#{caseId} and fairworkerId=#{workerId} and tjyaccessId=#{tjyAccessId}")
	Map<String, String> getZzlsbInfo(@Param("caseId")int caseId,@Param("workerId")int workerId,@Param("tjyAccessId")int tjyAccessId);

	@Delete("delete from zzlsb where FIND_IN_SET(zzlsbid,#{ids})")
	int deleteZzlsb(@Param("ids")String ids);
	
	@Select("select * from zzlsb where FIND_IN_SET(zzlsbid,#{ids})")
	List<Zzlsb> getLsbListByIds(@Param("ids")String ids);
}
