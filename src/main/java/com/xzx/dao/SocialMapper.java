package com.xzx.dao;

import com.xzx.model.Social;
import com.xzx.viewModel.FingersInfo;

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
 * @since 2019-09-27
 */
public interface SocialMapper extends BaseMapper<Social> {
	@Select("select count(1) from social where State=0 and SocialIdCard =(select SFZH from register where UserId=#{userId} limit 1)")
	int getSocialByUserId(@Param("userId") String userId);
	
	@Select("select * from social where SocialIdCard=#{SFZH}")
	Social getSocialBySFZH(@Param("SFZH")String SFZH);
	
	List<FingersInfo> getAllSocialFinger(@Param("SFZH") String SFZH);
	
	int updateSocialFinger(Social social);
	
	int updateSocialFingerById(@Param("photoId") String photoId,@Param("SocialIdCard") String SocialIdCard);
}
