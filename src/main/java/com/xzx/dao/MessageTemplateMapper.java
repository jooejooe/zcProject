package com.xzx.dao;

import com.xzx.model.MessageTemplate;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-10-22
 */
public interface MessageTemplateMapper extends BaseMapper<MessageTemplate> {
	
	@Select("select * from messagetemplate where RegionId=#{regionId} and FIND_IN_SET(#{workerType},workerType) and FIND_IN_SET(#{state},state) and messageType=0 and itemType=#{itemType} and modelType=#{modelType} and is_delete=0")
	MessageTemplate getTemplateInfo(@Param("regionId") int regionId,@Param("state") int state,@Param("workerType") int workerType,@Param("itemType") int itemType,@Param("modelType") int modelType);
	
	@Select("select templateId from messagetemplate where FIND_IN_SET(#{workerType},workerType) and messageType=1 and is_delete=0 and regionId=#{regionId} limit 1")
	int getVerifyTemplateId(@Param("workerType") String workerType,@Param("regionId") int regionId);
}
