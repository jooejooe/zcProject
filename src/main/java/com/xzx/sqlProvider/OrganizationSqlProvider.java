package com.xzx.sqlProvider;

import java.util.Map;

public class OrganizationSqlProvider {
	
	public String getOrgListByParamSql(Map<String, Object> para)
	{
		String sql= "select * from t_organization "
				+ "where 1=1";
				//+ "id=" + para.get("id")
				//+" and organizationName like concat('%','"+para.get("name")+"','%')";
		
		if(para.get("id")!=null&&!para.get("id").equals(""))
			sql+=" and id=" + para.get("id");
		
		if(para.get("name")!=null&&!para.get("name").equals(""))
			sql+=" and organizationName like concat('%','"+para.get("name")+"','%')";
		
		return sql;
	}
}
