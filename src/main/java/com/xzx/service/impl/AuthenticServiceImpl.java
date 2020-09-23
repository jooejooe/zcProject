package com.xzx.service.impl;

import com.xzx.dao.*;
import com.xzx.model.*;
import com.xzx.service.IAuthenticService;
import com.xzx.viewModel.AllPqInfo;
import com.xzx.viewModel.AuthenticDetailInfo;
import com.xzx.viewModel.CaseAssignInfo;
import com.xzx.viewModel.CaseAssignInfos;
import com.xzx.viewModel.ItemDetailInfo;
import com.xzx.viewModel.ItemsManageInfos;
import com.xzx.viewModel.TJLZInfo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-25
 */
@Service
public class AuthenticServiceImpl extends ServiceImpl<AuthenticMapper, Authentic> implements IAuthenticService {
	@Autowired
	AuthenticMapper authenticMapper;

	@Autowired
	OtherpartyMapper otherpartyMapper;

	@Autowired
	RegisterMapper registerMapper;

	@Autowired
	AccessMapper accessMapper;

	@Autowired
	AppraisalMapper appraisalMapper;

	@Autowired
	LawhelpMapper lawhelpMapper;

	@Autowired
	SpeedMapper speedMapper;

	@Autowired
	SpeedhistoryMapper speedhistoryMapper;

	@Autowired
	LawhelpadditionalMapper lawhelpadditionalMapper;

	@Autowired
	AuthworkerinfoMapper authworkerinfoMapper;

	@Autowired
	PqMapper pqMapper;

	@Transactional
	public Boolean insertAuthentic(ItemsManageInfos itemsManageInfos)
	{
		String itemType=itemsManageInfos.getType();
		Otherparty otherparty=itemsManageInfos.getOtherparty();
		//Register register=itemsManageInfos.getRegister();
		List<Access> listAccess=itemsManageInfos.getListAccess();
		int userId=Integer.parseInt(itemsManageInfos.getUserId());

		int flag1=0;
		int flag2=0;
		//		int flag3=0;
		int flag4=0;

		int flag5=0;
		int flag6=0;

		int flag7=0;

		int otherPartyId=0;

		if(otherparty!=null)
		{
			//添加对方当事人信息
			flag1=otherpartyMapper.addOtherParty(otherparty);

			otherPartyId=otherparty.getOtherpartyId();
		}

		int businessId=0;

		//添加相关业务信息
		switch (itemType) 
		{
		case "1"://人民仲裁
			Authentic authentic=itemsManageInfos.getAuthentic();

			if(otherparty!=null)
				authentic.setOtherPartyId(otherPartyId);

			flag2=authenticMapper.addAuthentic(authentic);

			businessId=authentic.getAuthenticId();
			break;
		case "2"://司法鉴定
			Appraisal appraisal=itemsManageInfos.getAppraisal();

			if(otherparty!=null)
				appraisal.setOtherPartyId(otherPartyId);

			flag2=appraisalMapper.addAppraisal(appraisal);

			businessId=appraisal.getAppraisalId();
			break;
		case "3"://法律援助
			Lawhelp lawhelp=itemsManageInfos.getLawhelp();

			if(otherparty!=null)
				lawhelp.setOtherPartyId(otherPartyId);

			flag2=lawhelpMapper.addLawhelp(lawhelp);

			businessId=lawhelp.getLawHelpId();

			if(!lawhelp.getSelectOptions().equals("")&&lawhelp.getSelectOptions()!=null)
			{
				Lawhelpadditional lawhelpadditional=new Lawhelpadditional();
				lawhelpadditional.setLawhelpId(businessId);
				lawhelpadditional.setOptions(lawhelp.getSelectOptions());

				flag7=lawhelpadditionalMapper.addSelectOptions(lawhelpadditional);
			}

			break;
		default:
			flag2=0;	
		}		

		/*		//更新申请人注册信息
		flag3=registerMapper.updateRegisterUser(register);*/

		//添加相关附件信息
		if(listAccess!=null&&listAccess.size()>0)
			flag4=accessMapper.insertBatchAccess(listAccess,businessId,Integer.parseInt(itemType),0);

		//当前进度初值增加
		Speed speed=new Speed();
		speed.setModelType(Integer.parseInt(itemType));
		speed.setModelId(businessId);
		speed.setState(0);
		speed.setRegisterId(userId);////register.getUserId()

		flag5=speedMapper.addSpeed(speed);

		//进度历史初始化增加
		Speedhistory speedhistory=new Speedhistory();
		speedhistory.setModelType(Integer.parseInt(itemType));
		speedhistory.setModelId(businessId);
		speedhistory.setState(0);
		speedhistory.setRegisterId(userId);//register.getUserId()
		flag6=speedhistoryMapper.addSpeedHistory(speedhistory);

		boolean flag=flag1>=0&&flag2>0&&flag4>=0&&flag5>0&&flag6>0&&flag7>=0;//&&flag3>0

		return flag;
	}

	public List<ItemDetailInfo> getItemInfoById(String authenticId)//,String userId
	{
		return authenticMapper.getItemInfoById(authenticId);//, userId
	}

	public int getUseCount(String userId,String applyType)
	{
		return authenticMapper.getUseCount(userId, applyType);
	}

	public List<CaseAssignInfos> getAssignCaseList(String regionId,String caseType,String spState,String starttime,String endtime,String dsrSFZH,String operateType,int isOnline,String orderType)
	{
		return authenticMapper.getAssignCaseList(regionId,caseType,spState,starttime,endtime,dsrSFZH,operateType,isOnline,orderType);
	}

	public List<CaseAssignInfos> getAssignCaseListZc(String regionId,String caseType,String caseTypeSecond,String spState,String starttime,String endtime,String dsrSFZH,String operateType,int isOnline,String orderType)
	{
		return authenticMapper.getAssignCaseListZc(regionId,caseType,caseTypeSecond,spState,starttime,endtime,dsrSFZH,operateType,isOnline,orderType);
	}

	public AuthenticDetailInfo getAuthenticById(String caseId)
	{
		return authenticMapper.getAuthenticById(caseId);
	}

	@Override
	@Transactional
	public int insertAuthInfo(Authentic authentic)
	{
		return authenticMapper.insertAuthInfo(authentic);
	}

	@Transactional
	public Boolean tjlzAuthentic(TJLZInfo tjlzInfo)
	{
		//更新主表仲裁流转原因，仲裁流转时间
		int flag1=authenticMapper.tjlzAuthentic(tjlzInfo.getTjlzReason(), tjlzInfo.getCaseId());

		//更新最新状态
		Speed speed=new Speed();
		speed.setSpeedId(Integer.parseInt(tjlzInfo.getSpeedId()));
		speed.setState(Integer.parseInt(tjlzInfo.getSpeedState()));

		int flag2=speedMapper.updateSpeed(speed);

		//将新增进度插入历史进度中
		Speed currentSpeed=speedMapper.getSpeedById(tjlzInfo.getSpeedId());

		Speedhistory speedhistory=new Speedhistory();
		speedhistory.setModelType(currentSpeed.getModelType());
		speedhistory.setModelId(currentSpeed.getModelId());
		speedhistory.setState(currentSpeed.getState());
		speedhistory.setRefuseReason(currentSpeed.getRefuseReason());
		speedhistory.setAddReason(currentSpeed.getAddReason());
		speedhistory.setEvaluateType(currentSpeed.getEvaluateType());
		speedhistory.setEvaluateContext(currentSpeed.getEvaluateContext());
		speedhistory.setFairworkerId(currentSpeed.getFairworkerId());
		speedhistory.setRegisterId(currentSpeed.getRegisterId());
		speedhistory.setCancelReason(currentSpeed.getCancelReason());

		int flag3=speedhistoryMapper.addOthersSpeedHistory(speedhistory);

		boolean flag=flag1>0&&flag2>0&&flag3>0;

		return flag;

	}

	@Transactional
	public Boolean caseAssign(CaseAssignInfo caseAssignInfo)
	{
		Integer workerid = null;
				//更新业务主表的工作人员字段
		Authentic authentic = authenticMapper.selectById(caseAssignInfo.getModelId());
		if(authentic!=null){
			workerid = authentic.getFairworkerId()==null?null:Integer.parseInt(authentic.getFairworkerId());
		}
		if(workerid!=null){
			Authworkerinfo authworkerinfo = new Authworkerinfo();
			authworkerinfo.setId(workerid);
			authworkerinfo.setWorkerids(caseAssignInfo.getWorkerId());
			authworkerinfoMapper.updateById(authworkerinfo);
		}else{
			Authworkerinfo authworkerinfo = new Authworkerinfo();
			authworkerinfo.setId(workerid);
			authworkerinfoMapper.insertAuthWorkerInfo(authworkerinfo);
			workerid = authworkerinfo.getId();
		}

		int flag1=authenticMapper.assignAuthentic(workerid.toString(), caseAssignInfo.getModelId(),caseAssignInfo.getZprId());
//		int flag1=authenticMapper.assignAuthenticZc(caseAssignInfo.getModelId(),caseAssignInfo.getZprId());

		//更新当前进度表的工作人员及当前状态字段
		int flag2=speedMapper.updateSpeedFairwork(workerid.toString(), caseAssignInfo.getModelId(), caseAssignInfo.getModelType());
//		int flag2=speedMapper.updateSpeedFairworkZc( caseAssignInfo.getModelId(), caseAssignInfo.getModelType());

		//将新增进度插入历史进度中
		Speed currentSpeed=speedMapper.getSpeedById(caseAssignInfo.getSpeedId());

		Speedhistory speedhistory=new Speedhistory();
		speedhistory.setModelType(currentSpeed.getModelType());
		speedhistory.setModelId(currentSpeed.getModelId());
		speedhistory.setState(currentSpeed.getState());
		speedhistory.setRefuseReason(currentSpeed.getRefuseReason());
		speedhistory.setAddReason(currentSpeed.getAddReason());
		speedhistory.setEvaluateType(currentSpeed.getEvaluateType());
		speedhistory.setEvaluateContext(currentSpeed.getEvaluateContext());
		speedhistory.setFairworkerId(currentSpeed.getFairworkerId());
		speedhistory.setRegisterId(currentSpeed.getRegisterId());
		speedhistory.setCancelReason(currentSpeed.getCancelReason());

		int flag3=speedhistoryMapper.addOthersSpeedHistory(speedhistory);

		//更新历史所有进度的工作人员
		int flag4=speedhistoryMapper.updateSpeedHistoryFairwork(workerid.toString(), caseAssignInfo.getModelId(), caseAssignInfo.getModelType());

		boolean flag=flag1>0&&flag2>0&&flag3>0&&flag4>0;

		return flag;
	}

	@Transactional
	public int casePq(Pq pq) throws ParseException
	{
		int existPqCount=pqMapper.getExistPqCount(Integer.toString(pq.getAjId()),Integer.toString(pq.getFairworkerId()), pq.getPqstart(), pq.getPqend());
		
		//所选排期范围已有其他案件排期
		if(existPqCount>0)
		{
			return -2;
		}
		else
		{
			Pq havePq=pqMapper.getPqByCaseId(Integer.toString(pq.getAjId()));//根据案件id获取原有排期数据

			//若之前已有对应案件的排期则更新排期，否则新增排期
			if(havePq==null)
			{
				//排期数据入库
				int flag1=pqMapper.addPq(pq);

				//更新最新状态
				Speed speed=new Speed();
				speed.setSpeedId(Integer.parseInt(pq.getSpeedId()));
				speed.setState(Integer.parseInt(pq.getSpeedState()));

				int flag2=speedMapper.updateSpeed(speed);

				//将新增进度插入历史进度中
				Speed currentSpeed=speedMapper.getSpeedById(pq.getSpeedId());

				Speedhistory speedhistory=new Speedhistory();
				speedhistory.setModelType(currentSpeed.getModelType());
				speedhistory.setModelId(currentSpeed.getModelId());
				speedhistory.setState(currentSpeed.getState());
				speedhistory.setRefuseReason(currentSpeed.getRefuseReason());
				speedhistory.setAddReason(currentSpeed.getAddReason());
				speedhistory.setEvaluateType(currentSpeed.getEvaluateType());
				speedhistory.setEvaluateContext(currentSpeed.getEvaluateContext());
				speedhistory.setFairworkerId(currentSpeed.getFairworkerId());
				speedhistory.setRegisterId(currentSpeed.getRegisterId());
				speedhistory.setCancelReason(currentSpeed.getCancelReason());

				int flag3=speedhistoryMapper.addOthersSpeedHistory(speedhistory);

				boolean flag=flag1>0&&flag2>0&&flag3>0;

				return flag?1:0;
			}
			else
			{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				Date pqStart=sdf.parse(havePq.getPqstart());
				Date dateNow=new Date();

				if (pqStart.getTime() <= dateNow.getTime()) 
				{
					return -1;
				}
				//			//排期起始时间晚于等于当前时间，不可修改排期
				//			if(havePq.getPqstart().before(new Date())||havePq.getPqstart().equals(new Date()))
				//			{
				//				
				//			}
				else 
				{
					if(pqMapper.updatePq(pq)>0)
						return 1;
					else
						return 0;
				}
			}
		}
	}

	public int tjblAuthentic(String tjbl,String AuthenticId)
	{
		return authenticMapper.tjblAuthentic(tjbl, AuthenticId);
	}
	
	public int caseAssginAgain(int workerId,int caseId,int assignWorkerId)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workerId", workerId);
		map.put("caseId", caseId);
		map.put("assignWorkerId", assignWorkerId);
		return authenticMapper.caseAssginAgain(map);
	}
	
	public Authentic getCaseInfo(String caseId)
	{
		return authenticMapper.getCaseInfo(caseId);
	}
	
	public List<AllPqInfo> getAllPqList(String departId)
	{
		return authenticMapper.getAllPqList(departId);
	}
}
