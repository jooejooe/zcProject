package com.xzx.dao;

import com.xzx.model.Consultregion;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-12-26
 */
public interface ConsultregionMapper extends BaseMapper<Consultregion> {
	@Select("select * from consultregion where State=0 order by ConsultRegionId desc")
	List<Consultregion> getConsultregionList();
}
