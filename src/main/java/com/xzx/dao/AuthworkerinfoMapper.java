package com.xzx.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.xzx.model.Authworkerinfo;
import com.xzx.model.Zcdw;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2020-04-23
 */
public interface AuthworkerinfoMapper extends BaseMapper<Authworkerinfo> {
    int insertAuthWorkerInfo(Authworkerinfo authworkerinfo);

}
