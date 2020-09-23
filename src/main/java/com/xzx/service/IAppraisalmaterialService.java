package com.xzx.service;

import com.xzx.model.Appraisalmaterial;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-26
 */
public interface IAppraisalmaterialService extends IService<Appraisalmaterial> {
	Appraisalmaterial getAppraisalmaterialInfo(String assistanceId);
}
