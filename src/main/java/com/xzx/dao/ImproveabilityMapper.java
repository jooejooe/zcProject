package com.xzx.dao;

import com.xzx.model.Improveability;

import java.util.List;

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
 * @since 2020-05-16
 */
public interface ImproveabilityMapper extends BaseMapper<Improveability> {
	@Insert({ "insert into improveability(title, docType, docUrl, createUser,createTime,isdel,filename) "
    		+ "values(#{title}, #{docType}, #{docUrl},#{createUser},now(),0,#{filename})" })
    @Options(useGeneratedKeys = true, keyProperty = "id")
	int addImproveAbility(Improveability improveability);
	
	List<Improveability> getImproveAbilityDocList(@Param("docType")String docType,@Param("docTitle")String docTitle);
	
	int updateImproveAbility(Improveability improveability);
	
	@Select("select * from improveability where id=#{id}")
	Improveability getInfoById(@Param("id")String id);
	
	@Select("select count(1) from improveability where title=#{title} and id!=#{id} and isdel=0")
	int getTitleCount(@Param("title")String title,@Param("id")int id);
	
	@Update("update improveability set isdel=1 where id=#{id}")
	int delImproveAbility(Improveability improveability);
}
