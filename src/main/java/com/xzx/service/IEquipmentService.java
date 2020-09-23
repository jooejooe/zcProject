package com.xzx.service;

import com.xzx.model.Equipment;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-29
 */
public interface IEquipmentService extends IService<Equipment> {
	String getCurOrganName(String token);
}
