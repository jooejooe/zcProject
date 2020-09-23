package com.xzx.viewModel;

public class AllPqInfo {
	
	private String WorkerName;
	private String nameA;
	private String nameB;
	private String pqstart;
	private String pqend;
	private String caseType;
	public String getWorkerName() {
		return WorkerName;
	}
	public void setWorkerName(String workerName) {
		WorkerName = workerName;
	}
	public String getNameA() {
		return nameA;
	}
	public void setNameA(String nameA) {
		if(nameA!=null&&!nameA.equals(""))
			this.nameA = nameA.substring(0,1)+"**";
	}
	public String getNameB() {
		return nameB;
	}
	public void setNameB(String nameB) {
		if(nameB!=null&&!nameB.equals(""))
			this.nameB = nameB.substring(0,1)+"**";
	}
	public String getPqstart() {
		return pqstart;
	}
	public void setPqstart(String pqstart) {
		this.pqstart = pqstart;
	}
	public String getPqend() {
		return pqend;
	}
	public void setPqend(String pqend) {
		this.pqend = pqend;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	
	
}
