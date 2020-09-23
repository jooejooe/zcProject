package com.xzx.service;

import com.xzx.model.Lawparentnext;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-10-09
 */
public interface ILawparentnextService extends IService<Lawparentnext> {
	List<Lawparentnext> getOptionsById(String parentId);
}
