package com.xzx.dao;

import com.xzx.model.Examineenotice;

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
public interface ExamineenoticeMapper extends BaseMapper<Examineenotice> {
	@Select("select * from examineenotice where state=0 and isDel=0 order by createTime desc")
	List<Examineenotice> getExamineeNotice();
	
	@Select("select * from examineenotice where id=#{id}")
	Examineenotice getExamineeNoticeById(@Param("id") String id);
}
