package com.xzx.dao;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xzx.model.Equipment;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-09-29
 */
public interface EquipmentMapper extends BaseMapper<Equipment> {
	String getCurOrganName(Map<String, Object> param);
}
