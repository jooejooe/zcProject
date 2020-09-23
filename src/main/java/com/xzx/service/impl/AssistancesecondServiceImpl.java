package com.xzx.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.xzx.dao.AssistancesecondMapper;
import com.xzx.dao.ZcdwMapper;
import com.xzx.model.Assistancesecond;
import com.xzx.model.Zcdw;
import com.xzx.service.IAssistancesecondService;
import com.xzx.service.IZcdwService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-24
 */
@Service
public class AssistancesecondServiceImpl extends ServiceImpl<AssistancesecondMapper, Assistancesecond> implements IAssistancesecondService {
	@Autowired
	AssistancesecondMapper assistancesecondMapper;

	@Override
	public List<Assistancesecond> getSecondAssistanceList(int assistanceId)
	{
		return assistancesecondMapper.getSecondAssistanceList(assistanceId);
	}

}
