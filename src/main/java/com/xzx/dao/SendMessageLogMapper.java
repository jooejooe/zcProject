package com.xzx.dao;

import com.xzx.model.SendMessageLog;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-10-21
 */
public interface SendMessageLogMapper extends BaseMapper<SendMessageLog> {
	int insertBatchSendMsgLog(@Param("list")List<SendMessageLog> list);
}
