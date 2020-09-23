package com.xzx.service;

import com.xzx.model.Tjy_access;
import com.xzx.viewModel.TJYAccessInfo;

import java.io.IOException;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-08-25
 */
public interface ITjy_accessService extends IService<Tjy_access> {
	Boolean insertTJYAccess(TJYAccessInfo tjyAccessInfo);
	
	List<Tjy_access> getTjyAccessList(String caseId,String workerId);
	
	List<Tjy_access> getDsrTjyAccessList(String caseId,String userId);
	
	Boolean addWaitTJYAccess(int accessWaitId,String serverPath,int workId) throws IOException;
}
