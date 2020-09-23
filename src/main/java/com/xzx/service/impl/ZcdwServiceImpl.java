package com.xzx.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xzx.dao.AccessMapper;
import com.xzx.dao.ZcdwMapper;
import com.xzx.model.Access;
import com.xzx.model.Register;
import com.xzx.model.Zcdw;
import com.xzx.service.IAccessService;
import com.xzx.service.IZcdwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
@Service
public class ZcdwServiceImpl extends ServiceImpl<ZcdwMapper, Zcdw> implements IZcdwService {
	@Autowired
	ZcdwMapper zcdwMapper;

	public int insertZcdwInfo(Zcdw zcdw)
	{
		return zcdwMapper.insertZcdwInfo(zcdw);
	}
}
