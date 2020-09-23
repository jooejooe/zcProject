package com.xzx.service.impl;

import com.xzx.model.Equipment;
import com.xzx.dao.EquipmentMapper;
import com.xzx.service.IEquipmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-29
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {
	@Autowired
	EquipmentMapper equipmentMapper;
	
	public String getCurOrganName(String token)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("token", token);
		return equipmentMapper.getCurOrganName(map);
	}
}
