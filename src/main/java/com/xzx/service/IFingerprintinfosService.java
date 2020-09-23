package com.xzx.service;

import com.xzx.model.Fingerprintinfos;
import com.xzx.model.Register;
import com.xzx.model.Social;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-11-04
 */
public interface IFingerprintinfosService extends IService<Fingerprintinfos> {
	int insertFingerPhoto(Fingerprintinfos fingerprintinfos,Register register,Social social);
	
	Fingerprintinfos getFingerById(int id);
	
	int delFingerInfo(int id);
}
