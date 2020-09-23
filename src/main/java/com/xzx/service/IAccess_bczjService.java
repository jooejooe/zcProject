package com.xzx.service;

import com.xzx.model.Access_bczj;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-04-23
 */
public interface IAccess_bczjService extends IService<Access_bczj> {
	List<Access_bczj> getAccessByItemId(String ModelId,String ModelType);
	
	int insertBatchAccessZJ(List<Access_bczj> list,String ModelId,String ModelType);
}
