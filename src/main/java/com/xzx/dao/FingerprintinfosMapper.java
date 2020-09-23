package com.xzx.dao;

import com.xzx.model.Fingerprintinfos;

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
 * @since 2019-11-04
 */
public interface FingerprintinfosMapper extends BaseMapper<Fingerprintinfos> {
	@Insert({ "insert into fingerprintinfos(fingerPrint,SFZH) "
    		+ "values(#{fingerPrint},#{SFZH})" })
	@Options(useGeneratedKeys = true, keyProperty = "id")
    int insertFingerPhoto(Fingerprintinfos fingerprintinfos);
	
	@Select("select * from fingerprintinfos where id=#{id}")
	Fingerprintinfos getFingerById(@Param("id") int id);
	
	@Delete("delete from fingerprintinfos where id=#{id}")
	int delFingerInfo(@Param("id") int id);
}
