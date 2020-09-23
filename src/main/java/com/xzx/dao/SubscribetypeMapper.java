package com.xzx.dao;

import com.xzx.model.Subscribetype;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-10-30
 */
public interface SubscribetypeMapper extends BaseMapper<Subscribetype> {
	//@Select("select * from subscribetype where state=0")
	@Select("select *,(select count(1) from subscribe a left join speed b on a.subscribeId=b.ModelId where UserId=#{userId} and subscribetype=aa.id and a.ModelType=#{subscribeModelType} and b.ModelType=#{speedModelType} and FIND_IN_SET(State,'0,3,4')) as useState from subscribetype aa  where state=0")
	List<Map<String, String>> getSubscribeType(@Param("userId") String userId,@Param("subscribeModelType") String subscribeModelType,@Param("speedModelType") String speedModelType);
}
