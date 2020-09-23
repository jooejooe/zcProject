package com.xzx.service;

import com.xzx.model.Consultregion;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-12-26
 */
public interface IConsultregionService extends IService<Consultregion> {
	List<Consultregion> getConsultregionList();
}
