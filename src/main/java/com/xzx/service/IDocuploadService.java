package com.xzx.service;

import com.xzx.model.Docupload;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-12-16
 */
public interface IDocuploadService extends IService<Docupload> {
	List<Docupload> getDocUploadList(String title,String token,int modelType);

	Docupload getDocUploadById(String id);
}
