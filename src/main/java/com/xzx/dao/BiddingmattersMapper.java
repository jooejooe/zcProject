package com.xzx.dao;

import com.xzx.model.Biddingmatters;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public interface BiddingmattersMapper extends BaseMapper<Biddingmatters> {
	
	//@Select("select * from biddingmatters where State=0 order by CreateDate desc")
	List<Map<String, Object>> getBiddingMatterList(@Param("userId") int userId);
}
