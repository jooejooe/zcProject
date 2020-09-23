package com.xzx.service;

import com.baomidou.mybatisplus.service.IService;
import com.xzx.model.Authreginfo;
import com.xzx.model.Zcdw;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public interface IAuthreginfoService extends IService<Authreginfo> {

    int insertinfo(Authreginfo authreginfo);
}
