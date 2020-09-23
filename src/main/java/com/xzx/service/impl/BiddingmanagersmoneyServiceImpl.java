package com.xzx.service.impl;

import com.xzx.model.Biddingmanagersmoney;
import com.xzx.dao.BiddingmanagersmoneyMapper;
import com.xzx.service.IBiddingmanagersmoneyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
@Service
public class BiddingmanagersmoneyServiceImpl extends ServiceImpl<BiddingmanagersmoneyMapper, Biddingmanagersmoney> implements IBiddingmanagersmoneyService {
	@Autowired
	BiddingmanagersmoneyMapper biddingmanagersmoneyMapper;
	
	public Biddingmanagersmoney getMatterMoneyInfo(@Param("matterId")String matterId)
	{
		return biddingmanagersmoneyMapper.getMatterMoneyInfo(matterId);
	}
}
