package com.xzx.viewModel;

import java.util.List;
import java.util.Map;

import com.xzx.model.Access_tjyupload;
import com.xzx.model.Tjy_access;

public class TJYAccessInfo {
	private List<Tjy_access> list;
	
	private String caseId;
	
	private String fairworkerId;

	private List<Map<String,String>> fileList;
	
	private List<Access_tjyupload> listUploadAccess;

	public List<Tjy_access> getList() {
		return list;
	}

	public void setList(List<Tjy_access> list) {
		this.list = list;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	public List<Map<String,String>> getFileList() {
		return fileList;
	}

	public void setFileList(List<Map<String,String>> fileList) {
		this.fileList = fileList;
	}
	
	public String getFairworkerId() {
		return fairworkerId;
	}

	public void setFairworkerId(String fairworkerId) {
		this.fairworkerId = fairworkerId;
	}
	
	public List<Access_tjyupload> getListUploadAccess() {
		return listUploadAccess;
	}

	public void setListUploadAccess(List<Access_tjyupload> listUploadAccess) {
		this.listUploadAccess = listUploadAccess;
	}
}
