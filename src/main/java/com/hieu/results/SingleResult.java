package com.hieu.results;

public class SingleResult {
	private String status;
	private String message;
	private String messageEng;
	private String log;
	private Object result;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public String getMessageEng() {
		return messageEng;
	}
	public void setMessageEng(String messageEng) {
		this.messageEng = messageEng;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	
}
