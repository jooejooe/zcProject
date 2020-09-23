package com.xzx.service;

import com.baomidou.mybatisplus.service.IService;
import com.xzx.model.Access;
import com.xzx.model.Zcdw;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public interface IZcdwService extends IService<Zcdw> {

    int insertZcdwInfo(Zcdw zcdw);

}
