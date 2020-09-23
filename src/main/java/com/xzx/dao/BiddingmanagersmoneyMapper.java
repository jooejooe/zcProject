package com.xzx.dao;

import com.xzx.model.Biddingmanagersmoney;

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
public interface BiddingmanagersmoneyMapper extends BaseMapper<Biddingmanagersmoney> {
	
	@Select("select * from biddingmanagersmoney where State=0 and BiddingMattersId=#{matterId} order by CreateDate desc limit 1")
	Biddingmanagersmoney getMatterMoneyInfo(@Param("matterId")String matterId);
}
