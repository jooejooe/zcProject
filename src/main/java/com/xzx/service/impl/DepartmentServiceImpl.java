package com.xzx.service.impl;

import com.xzx.model.Department;
import com.xzx.dao.DepartmentMapper;
import com.xzx.service.IDepartmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
	@Autowired
	DepartmentMapper departmentMapper;
	
	public List<Department> getDepartmentList(String departType,String areaId,String searchText)
	{
		return departmentMapper.getDepartmentList(departType, areaId,searchText);
	}
	
	public Department getDepartById(String id)
	{
		return departmentMapper.getDepartById(id);
	}
	
	public List<Department> getRYDepartList(String areaId)
	{
		return departmentMapper.getRYDepartList(areaId);
	}
	
	public List<Department> getSimpleDepartmentList(String departType,String areaId)
	{
		return departmentMapper.getSimpleDepartmentList(departType, areaId);
	}
}
