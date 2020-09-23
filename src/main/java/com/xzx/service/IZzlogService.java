package com.xzx.service;

import com.xzx.model.Zzlog;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-08-27
 */
public interface IZzlogService extends IService<Zzlog> {
	Boolean addZzlog(String zzlsbIds);
	
	List<Map<String, Object>> getZZConfirmList();
}
