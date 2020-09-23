package com.xzx.service;

import com.xzx.model.Lawprovision;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-12-17
 */
public interface ILawprovisionService extends IService<Lawprovision> {
	List<Lawprovision> getLawProvisionList(int modelType,String title,String token);

	Lawprovision getLawprovisionById(String id);
}
