package com.xzx.dao;

import com.xzx.model.Appraisal;
import com.xzx.viewModel.ItemDetailInfo;

import java.util.List;

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
 * @since 2019-09-26
 */
public interface AppraisalMapper extends BaseMapper<Appraisal> {
	@Insert({ "insert into appraisal(MatterType, NextMatterType, MatterDepart, UserId,OtherPartyId,Summary,Context,CreateDate,RegionId,OtherPartyType) "
    		+ "values(#{MatterType}, #{NextMatterType}, #{MatterDepart}, #{UserId},#{OtherPartyId},#{Summary},#{Context},now(),#{RegionId},#{OtherPartyType})" })
    @Options(useGeneratedKeys = true, keyProperty = "AppraisalId")
	int addAppraisal(Appraisal appraisal);
	
	List<ItemDetailInfo> getItemInfoById(@Param("appraisalId") String appraisalId);//,@Param("userId") String userId
	
	@Select("select count(1) from appraisal a left join speed b on a.AppraisalId=b.ModelId where UserId=#{userId} and MatterType=#{applyType} and b.ModelType=2 and FIND_IN_SET(State,'0,1,3')")
	int getUseCount(@Param("userId")String userId,@Param("applyType")String applyType);
}
