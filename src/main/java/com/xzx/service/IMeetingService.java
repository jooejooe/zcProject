package com.xzx.service;

import com.xzx.model.Meeting;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-03-31
 */
public interface IMeetingService extends IService<Meeting> {
	List<Meeting> getMeetingList();
}
