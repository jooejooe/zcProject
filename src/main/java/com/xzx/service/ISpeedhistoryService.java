package com.xzx.service;

import com.xzx.model.Speedhistory;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-30
 */
public interface ISpeedhistoryService extends IService<Speedhistory> {
	int addSpeedHistory(Speedhistory speedhistory);
}
