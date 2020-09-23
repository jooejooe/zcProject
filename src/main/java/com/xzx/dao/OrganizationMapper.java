package com.xzx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.xzx.model.Organization;
import com.xzx.sqlProvider.OrganizationSqlProvider;

@Repository
public interface OrganizationMapper {
	
	//查询，自己写sql
	@SelectProvider(type = OrganizationSqlProvider.class, method = "getOrgListByParamSql")
	List<Organization> getOrgListByParam(@Param("id") String id,@Param("name") String name);

	@Select("select * from t_organization where id=#{id}")
	Organization getOrgById(String id);
}
