package com.xzx.service;

import com.xzx.model.Examineenotice;

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
public interface IExamineenoticeService extends IService<Examineenotice> {
	List<Examineenotice> getExamineeNotice();
	
	Examineenotice getExamineeNoticeById(String id);
}
