package com.hieu.results;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class BrokerResult {
	private int BrokerID;
	private List<Double> LastRT;
    private double PredictedRT;
	
	@Autowired(required = false)
	private List<CloudletResult> Cloudlets;
	
	@Autowired(required = false)
	private List<VmResult> Vms;

	public int getBrokerID() {
		return BrokerID;
	}

	public void setBrokerID(int brokerID) {
		BrokerID = brokerID;
	}

	public List<CloudletResult> getCloudlets() {
		return Cloudlets;
	}

	public void setCloudlets(List<CloudletResult> cloudlets) {
		Cloudlets = cloudlets;
	}

	public List<VmResult> getVms() {
		return Vms;
	}

	public void setVms(List<VmResult> vms) {
		Vms = vms;
	}

	public  List<Double> getLastRT() {
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
}
