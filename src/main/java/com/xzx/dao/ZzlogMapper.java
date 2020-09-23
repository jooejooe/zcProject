package com.xzx.dao;

import com.xzx.model.Zzlog;

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
 * @since 2020-08-27
 */
public interface ZzlogMapper extends BaseMapper<Zzlog> {

	int addZzlog(@Param("zzlsbIds")String zzlsbIds);
	
	@Select("select sum(zjstate) confirmCount,posttime,tjy_accessId from zzlog  GROUP BY posttime,tjy_accessId order by posttime")
	List<Map<String, Object>> getZZConfirmList();
}
