package com.vpsmonitor.viewmodel;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ViewModelMonitorList
{
	@NotNull(message = "Please enter a monitor ID")
	private int monitorID;
	
	@NotEmpty(message = "Please enter a VPS Name")
	private String vpsName;
	
	@NotEmpty(message = "Please enter a monitor location")
	private String monitorIPOrUrl;
	
	@NotNull(message = "Please enter a port")
	private int monitorPort;
	
	@NotNull(message = "Please enter a timeout value")
	private int monitorTimeout;
	
	@NotNull(message = "Please enter a require valid content")
	private int isRequireValidContent;
	
	private String validContent;

	public int getID() { return this.monitorID; }
	public String getVpsID() { return this.vpsName; }
	public String getMonitorIPOrUrl() { return this.monitorIPOrUrl; }
	public int getMonitorPort() { return this.monitorPort; }
	public int getMonitorTimeout() { return this.monitorTimeout; }
	public int getIsRequireValidContent() { return this.isRequireValidContent; }
	public String getValidContent() { return this.validContent; }
	
	public void setID(int k) { this.monitorID = k; }
	public void setVpsName(String k) { this.vpsName = k; }
	public void setMonitorIPOrUrl(String k) { this.monitorIPOrUrl = k; }
	public void setMonitorPort(int k) { this.monitorPort = k; }
	public void setMonitorTimeout(int k) { this.monitorTimeout = k; }
	public void setIsRequireValidContent(int k) { this.isRequireValidContent = k; }
	public void setValidContent(String k) { this.validContent = k; }
}
