package com.hieu.results;

public class CloudletResult {
	private int no;
	private int CloudletID;
	private String Status;
	private int DataCenterID;
	private int VmID;
	private double Time;
	private double StartTime;
	private double FinishTime;
	
	private double responseTime;
	private double executionTime;
	
	public int getCloudletID() {
		return CloudletID;
	}
	public void setCloudletID(int id) {
		this.CloudletID = id;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getDataCenterID() {
		return DataCenterID;
	}
	public void setDataCenterID(int dataCenterID) {
		DataCenterID = dataCenterID;
	}
	public int getVmID() {
		return VmID;
	}
	public void setVmID(int vmID) {
		VmID = vmID;
	}
	public double getTime() {
		return Time;
	}
	public void setTime(double time) {
		Time = time;
	}
	public double getStartTime() {
		return StartTime;
	}
	public void setStartTime(double startTime) {
		StartTime = startTime;
	}
	public double getFinishTime() {
		return FinishTime;
	}
	public void setFinishTime(double finishTime) {
		FinishTime = finishTime;
	}
	public double getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(double responseTime) {
		this.responseTime = responseTime;
	}
	public double getExecutionTime() {
		return executionTime;
	}
	public void setExecutionTime(double executionTime) {
		this.executionTime = executionTime;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
}

