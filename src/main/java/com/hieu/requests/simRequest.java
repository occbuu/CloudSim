package com.hieu.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class simRequest {	
	private int VmsQty;
	
	private int CloudletQty;
	
	public int getVmsQty() {
		return VmsQty;
	}
	public void setVmsQty(int vmsQty) {
		VmsQty = vmsQty;
	}
	public int getCloudletQty() {
		return CloudletQty;
	}
	public void setCloudletQty(int cloudletQty) {
		CloudletQty = cloudletQty;
	}
	
	
	
}
