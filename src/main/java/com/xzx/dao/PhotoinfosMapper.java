package com.xzx.dao;

import com.xzx.model.Photoinfos;

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
 * @since 2019-11-01
 */
public interface PhotoinfosMapper extends BaseMapper<Photoinfos> {
	@Insert({ "insert into photoinfos(SFZH, imgBase64,sxqmurl) "
    		+ "values(#{SFZH}, #{imgBase64},#{sxqmurl})" })
	@Options(useGeneratedKeys = true, keyProperty = "id")
    int insertPhoto(Photoinfos photoinfos);
	
	@Select("select * from photoinfos where id=#{id}")
	Photoinfos getPhotoById(@Param("id") int id);
	
	@Delete("delete from photoinfos where id=#{id}")
	int delPhotosInfo(@Param("id") int id);
}
