package com.xzx.service;

import com.xzx.model.Socialrecoder;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-10-10
 */
public interface ISocialrecoderService extends IService<Socialrecoder> {
	int addSocialRecorder(Socialrecoder socialrecoder);
}
