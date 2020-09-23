package com.xzx.service;

import com.xzx.model.Department;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public interface IDepartmentService extends IService<Department> {
	List<Department> getDepartmentList(String departType,String areaId,String searchText);
	
	Department getDepartById(String id);
	
	List<Department> getRYDepartList(String areaId);
	
	List<Department> getSimpleDepartmentList(String departType,String areaId);
}
