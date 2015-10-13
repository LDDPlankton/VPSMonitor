package com.vpsmonitor.viewmodel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class ViewModelAddMonitor
{
	@NotNull(message = "Please enter a VPS ID")
	private int vpsID;
	
	@Size(min = 4, max = 128, message = "Your monitoring location must be between 4 and 128 characters!")
	private String monitorIPOrUrl;
	
	@Range(min = 1, max = 5, message = "Your port must be between 1-9999!")
	@NotNull(message = "Please enter a port")
	private int monitorPort;
	
	@Range(min = 1, max = 5, message = "Your timeout must be between 1-9999!")
	@NotNull(message = "Please enter a timeout value")
	private int monitorTimeout;
	
	@NotNull(message = "Please enter a require valid content")
	private int isRequireValidContent;
	
	private String validContent;

	public int getVpsID() { return this.vpsID; }
	public String getMonitorIPOrUrl() { return this.monitorIPOrUrl; }
	public int getMonitorPort() { return this.monitorPort; }
	public int getMonitorTimeout() { return this.monitorTimeout; }
	public int getIsRequireValidContent() { return this.isRequireValidContent; }
	public String getValidContent() { return this.validContent; }
	
	public void setVpsID(int k) { this.vpsID = k; }
	public void setMonitorIPOrUrl(String k) { this.monitorIPOrUrl = k; }
	public void setMonitorPort(int k) { this.monitorPort = k; }
	public void setMonitorTimeout(int k) { this.monitorTimeout = k; }
	public void setIsRequireValidContent(int k) { this.isRequireValidContent = k; }
	public void setValidContent(String k) { this.validContent = k; }
}
