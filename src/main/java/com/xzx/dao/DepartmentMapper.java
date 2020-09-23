package com.xzx.dao;

import com.xzx.model.Department;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Helen
 * @since 2019-09-23
 */
public interface DepartmentMapper extends BaseMapper<Department> {
	
//	@Select("select * from department where State=0 and DepartType=#{departType} and AreaId=#{areaId} order by DepartmentCreateTime desc")
	List<Department> getDepartmentList(@Param("departType")String departType,@Param("areaId")String areaId,@Param("searchText")String searchText);
	
	@Select("select * from department where DepartmentId=#{id} and State=0")
	Department getDepartById(@Param("id") String id);
	
	List<Department> getRYDepartList(@Param("areaId")String areaId);
	
	List<Department> getSimpleDepartmentList(@Param("departType")String departType,@Param("areaId")String areaId);
}
