package com.xzx.service.impl;

import com.xzx.model.Photoinfos;
import com.xzx.dao.PhotoinfosMapper;
import com.xzx.service.IPhotoinfosService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-11-01
 */
@Service
public class PhotoinfosServiceImpl extends ServiceImpl<PhotoinfosMapper, Photoinfos> implements IPhotoinfosService {
    @Autowired
    PhotoinfosMapper photoinfosMapper;
    
	public int insertPhoto(Photoinfos photoinfos)
    {
		return photoinfosMapper.insertPhoto(photoinfos);
    }
	
	public Photoinfos getPhotoById(int id)
	{
		return photoinfosMapper.getPhotoById(id);
	}
	
	public int delPhotosInfo(int id)
	{
		return photoinfosMapper.delPhotosInfo(id);
	}
}
