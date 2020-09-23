package com.xzx.service;

import com.xzx.model.Aj_tjbl;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-08-27
 */
public interface IAj_tjblService extends IService<Aj_tjbl> {
	Boolean addTjbl(Aj_tjbl aj_tjbl);
}
