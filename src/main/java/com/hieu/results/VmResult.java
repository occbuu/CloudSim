package com.hieu.results;

import java.util.List;


public class VmResult {
	private int no;
	private int VmID;
	private String vmm;
	private int ram;
	private int totalServedRequest;
	private int numberOfPes;
	private long bw;
	private double mips;
	private List<Double> LastRT;
	private double PredictedRT;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getVmID() {
		return VmID;
	}
	public void setVmID(int vmID) {
		VmID = vmID;
	}
	public String getVmm() {
		return vmm;
	}
	public void setVmm(String vmm) {
		this.vmm = vmm;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	public int getNumberOfPes() {
		return numberOfPes;
	}
	public void setNumberOfPes(int numberOfPes) {
		this.numberOfPes = numberOfPes;
	}
	public long getBw() {
		return bw;
	}
	public void setBw(long bw) {
		this.bw = bw;
	}
	public double getMips() {
		return mips;
	}
	public void setMips(double mips) {
		this.mips = mips;
	}
	public List<Double> getLastRT() {
		return LastRT;
	}
	public void setLastRT(List<Double> lastRT) {
		LastRT = lastRT;
	}
	public double getPredictedRT() {
		return PredictedRT;
	}
	public void setPredictedRT(double predictedRT) {
		PredictedRT = predictedRT;
	}
	public int getTotalServedRequest() {
		return totalServedRequest;
	}
	public void setTotalServedRequest(int totalServedRequest) {
		this.totalServedRequest = totalServedRequest;
	}	
}
