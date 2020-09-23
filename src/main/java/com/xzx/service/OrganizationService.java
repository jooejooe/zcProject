package com.xzx.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzx.dao.OrganizationMapper;
import com.xzx.model.Organization;

@Service
public class OrganizationService {
	@Autowired
	OrganizationMapper organizationMapper;
	
	public List<Organization> getOrgListByParam(String id,String name)
	{
		return organizationMapper.getOrgListByParam(id, name);
	}

	public Organization getOrgById(String id)
	{
		return organizationMapper.getOrgById(id);
	}
}
