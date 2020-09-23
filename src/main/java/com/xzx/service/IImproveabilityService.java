package com.xzx.service;

import com.xzx.model.Improveability;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2020-05-16
 */
public interface IImproveabilityService extends IService<Improveability> {
	int addImproveAbility(Improveability improveability);
	
	List<Improveability> getImproveAbilityDocList(String docType,String docTitle);
	
	int updateImproveAbility(Improveability improveability);
	
	Improveability getInfoById(String id);
	
	int getTitleCount(String title,int id);
	
	int delImproveAbility(Improveability improveability);
}
