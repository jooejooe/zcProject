package com.xzx.service.impl;

import com.xzx.model.Biddingmatters;
import com.xzx.dao.BiddingmattersMapper;
import com.xzx.service.IBiddingmattersService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
@Service
public class BiddingmattersServiceImpl extends ServiceImpl<BiddingmattersMapper, Biddingmatters> implements IBiddingmattersService {
	@Autowired
	BiddingmattersMapper biddingmattersMapper;
	public List<Map<String, Object>> getBiddingMatterList(int userId)
	{
		return biddingmattersMapper.getBiddingMatterList(userId);
	}
}
