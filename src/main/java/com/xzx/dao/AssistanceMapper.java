package com.xzx.dao;

import com.xzx.model.Assistance;
import com.xzx.sqlProvider.AssistanceSqlProvider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
public interface AssistanceMapper extends BaseMapper<Assistance> {
	//@Select("select * from assistance where state=0 and assistancetype=#{assistancetype} order by createdate desc")
	@SelectProvider(type = AssistanceSqlProvider.class, method = "getAssistanceList")
	List<Map<String, String>> getAssistanceList(@Param("assistancetype")String assistancetype,@Param("userId") String userId,@Param("modelType") String modelType,@Param("isSubscribe") String isSubscribe);
	
	@Select("select * from assistance where state=0 and assistancetype=#{assistancetype} order by createdate desc")
	List<Assistance> getAllAssistanceList(@Param("assistancetype")String assistancetype);
}
