package com.xzx.service.impl;

import com.xzx.model.Meeting;
import com.xzx.dao.MeetingMapper;
import com.xzx.service.IMeetingService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-03-31
 */
@Service
public class MeetingServiceImpl extends ServiceImpl<MeetingMapper, Meeting> implements IMeetingService {
	@Autowired
	MeetingMapper meetingMapper;

	public List<Meeting> getMeetingList()
	{
		return meetingMapper.getMeetingList();
	}
}
