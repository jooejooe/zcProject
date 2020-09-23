package com.xzx.service.impl;

import com.xzx.model.Fy_sp;
import com.xzx.model.Speed;
import com.xzx.model.Speedhistory;
import com.xzx.dao.AuthenticMapper;
import com.xzx.dao.Fy_spMapper;
import com.xzx.dao.SpeedMapper;
import com.xzx.dao.SpeedhistoryMapper;
import com.xzx.service.IFy_spService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-04-24
 */
@Service
public class Fy_spServiceImpl extends ServiceImpl<Fy_spMapper, Fy_sp> implements IFy_spService {
	@Autowired
	Fy_spMapper fy_spMapper;
	
	@Autowired
	SpeedMapper speedMapper;
	
	@Autowired
	SpeedhistoryMapper speedhistoryMapper;
	
	@Autowired
	AuthenticMapper authenticMapper;

	@Transactional
	public Boolean operateSP(Fy_sp fy_sp)
	{
		int flag1=0;
		int flag2=0;
		int flag3=0;
		int flag4=0;
		
		String caseId=Integer.toString(fy_sp.getFy_ajId());
		
		if(fy_spMapper.getSPInfoByCaseId(caseId)!=null)
		{
			if(fy_spMapper.updateSP(fy_sp)>0)
				return true;
			else
				return false;
		}
		else
		{
			String speedId=fy_sp.getSpeedId();
			String State=fy_sp.getState();
			
			//更新最新状态
			Speed speed=new Speed();
			speed.setSpeedId(Integer.parseInt(speedId));
			speed.setState(Integer.parseInt(State));
			
			flag1=speedMapper.updateSpeed(speed);
			
			//将新增进度插入历史进度中
			Speed currentSpeed=speedMapper.getSpeedById(speedId);

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

			flag2=speedhistoryMapper.addOthersSpeedHistory(speedhistory);
			
			//添加审批记录入库
			flag3=fy_spMapper.addSP(fy_sp);
			
			//修改审批时间
			flag4=authenticMapper.spAuthentic(caseId);
			
			boolean flag=flag1>0&&flag2>0&&flag3>0&&flag4>0;
			
			return flag;
		}
	}

	public Fy_sp getSPInfoByCaseId(String caseId)
	{
		return fy_spMapper.getSPInfoByCaseId(caseId);
	}
}
