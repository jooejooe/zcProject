package com.xzx.service;

import com.xzx.model.Purpose;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public interface IPurposeService extends IService<Purpose> {
	List<Purpose> getPurposeList();
}
