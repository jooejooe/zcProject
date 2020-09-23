package com.xzx.service;

import com.xzx.model.SendMessageLog;
import com.xzx.viewModel.MessageSendInfo;

import org.json.JSONException;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-10-21
 */
public interface ISendMessageLogService extends IService<SendMessageLog> {
	JSONObject addSendMsgLog(MessageSendInfo messageSendInfo) throws JSONException;
}
