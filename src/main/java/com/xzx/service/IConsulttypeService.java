package com.xzx.service;

import com.xzx.model.Consulttype;

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
public interface IConsulttypeService extends IService<Consulttype> {
	List<Consulttype> getConsulttypeList(int regionId);
}
