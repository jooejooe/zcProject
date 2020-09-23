package com.xzx.dao;

import com.xzx.model.Socialrecoder;

import org.apache.ibatis.annotations.Insert;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-10-10
 */
public interface SocialrecoderMapper extends BaseMapper<Socialrecoder> {
	
	@Insert("insert into socialrecoder(socialId,socialIdCard,socialPhone,socialName,recoderDate)"
			+ "values(#{socialId},#{socialIdCard},#{socialPhone},#{socialName},now())")
	int addSocialRecorder(Socialrecoder socialrecoder);
}
