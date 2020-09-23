package com.xzx.service;

import com.xzx.model.Access_tjyupload;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-08-26
 */
public interface IAccess_tjyuploadService extends IService<Access_tjyupload> {
	List<Access_tjyupload> getUploadAccess(String caseId,String fairworkerId);
}
