package com.xzx.service;

import com.xzx.model.Photoinfos;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-11-01
 */
public interface IPhotoinfosService extends IService<Photoinfos> {
    int insertPhoto(Photoinfos photoinfos);
	
	Photoinfos getPhotoById(int id);
	
	int delPhotosInfo(int id);
}
