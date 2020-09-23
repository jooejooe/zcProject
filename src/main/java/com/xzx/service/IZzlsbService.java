package com.xzx.service;

import com.xzx.model.Zzlsb;

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
public interface IZzlsbService extends IService<Zzlsb> {
	int addZzlsb(Zzlsb zzlsb);
	
	Map<String, String> getZzlsbInfo(int caseId,int workerId,int tjyAccessId);
	
	List<Zzlsb> getLsbListByIds(String ids);
}
