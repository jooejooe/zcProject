package com.xzx.dao;

import com.xzx.model.Just;
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
 * @since 2019-09-24
 */
public interface JustMapper extends BaseMapper<Just> {
	@Insert({ "insert into just(DepartmentId, FairWorkerId, JustType, Language,PurposeId,UsePlaceId,BiddingMagttersMoneyId,BiddingMattersId,UserId,CreateDate,JustState,IsDoor,regionId) "
    		+ "values(#{DepartmentId}, #{FairWorkerId}, #{JustType}, #{Language},#{PurposeId},#{UsePlaceId},#{BiddingMagttersMoneyId},#{BiddingMattersId},#{UserId},now(),0,#{IsDoor},#{regionId})" })
    @Options(useGeneratedKeys = true, keyProperty = "JustId")
	int addJust(Just just);
	
	List<ItemDetailInfo> getItemInfoById(@Param("justId")String justId);//,@Param("userId")String userId
	
	@Select("select count(1) from just a left join speed b on a.JustId=b.ModelId where UserId=#{userId} and BiddingMattersId=#{applyType} and ModelType=0 and FIND_IN_SET(State,'0,1,3')")
	int getUseCount(@Param("userId") int userId,@Param("applyType") String applyType);
}
