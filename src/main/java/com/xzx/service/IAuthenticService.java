package com.xzx.service;


import com.xzx.model.Authentic;
import com.xzx.model.Pq;
import com.xzx.viewModel.AllPqInfo;
import com.xzx.viewModel.AuthenticDetailInfo;
import com.xzx.viewModel.CaseAssignInfo;
import com.xzx.viewModel.CaseAssignInfos;
import com.xzx.viewModel.ItemDetailInfo;
import com.xzx.viewModel.ItemsManageInfos;
import com.xzx.viewModel.TJLZInfo;

import java.text.ParseException;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
public interface IAuthenticService extends IService<Authentic> {
	Boolean insertAuthentic(ItemsManageInfos itemsManageInfos);

	List<ItemDetailInfo> getItemInfoById(String authenticId);//,String userId

	int getUseCount(String userId,String applyType);

	List<CaseAssignInfos> getAssignCaseList(String regionId,String caseType,String spState,String starttime,String endtime,String dsrSFZH,String operateType,int isOnline,String orderType);
	List<CaseAssignInfos> getAssignCaseListZc(String regionId,String caseType,String caseTypeSecond,String spState,String starttime,String endtime,String dsrSFZH,String operateType,int isOnline,String orderType);

	AuthenticDetailInfo getAuthenticById(String caseId);

	int insertAuthInfo(Authentic authentic);

	Boolean caseAssign(CaseAssignInfo caseAssignInfo);

	int casePq(Pq pq) throws ParseException;

	int tjblAuthentic(String tjbl,String AuthenticId);

	Boolean tjlzAuthentic(TJLZInfo tjlzInfo);

	int caseAssginAgain(int workerId,int caseId,int assignWorkerId);

	Authentic getCaseInfo(String caseId);

	List<AllPqInfo> getAllPqList(String departId);
}
