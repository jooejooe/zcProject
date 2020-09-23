package com.xzx.dao;

import com.xzx.model.Lawhelp;
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
public interface LawhelpMapper extends BaseMapper<Lawhelp> {
	@Insert({ "insert into lawhelp(LawDepartment, ApplicantTypeId, assistanceId, UserId,OtherPartyId,LawState,Summary,Context,CreateDate,RegionId) "
    		+ "values(#{LawDepartment}, #{ApplicantTypeId}, #{assistanceId}, #{UserId},#{OtherPartyId},#{LawState},#{Summary},#{Context},now(),#{RegionId})" })
    @Options(useGeneratedKeys = true, keyProperty = "LawHelpId")
	int addLawhelp(Lawhelp lawhelp);
	
	List<ItemDetailInfo> getItemInfoById(@Param("lawHelpId") String lawHelpId);//,@Param("userId") String userId

	@Select("select count(1) from lawhelp a left join speed b on a.LawHelpId=b.ModelId where UserId=#{userId} and assistanceId=#{applyType} and ModelType=3 and FIND_IN_SET(State,'0,1,3')")
	int getUseCount(@Param("userId")String userId,@Param("applyType")String applyType);
}
