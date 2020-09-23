package com.xzx.service;

import com.xzx.model.Dictionaries;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
public interface IDictionariesService extends IService<Dictionaries> {
	List<Dictionaries> getDictionaryList(String DictionariesTypeId);
}
