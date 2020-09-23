package com.xzx.service;

import com.xzx.model.Biddingmanagersmoney;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
public interface IBiddingmanagersmoneyService extends IService<Biddingmanagersmoney> {
	Biddingmanagersmoney getMatterMoneyInfo(@Param("matterId")String matterId);
}
