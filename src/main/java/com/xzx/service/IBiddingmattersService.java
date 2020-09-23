package com.xzx.service;

import com.xzx.model.Biddingmatters;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public interface IBiddingmattersService extends IService<Biddingmatters> {
	List<Map<String, Object>> getBiddingMatterList(int userId);
}
