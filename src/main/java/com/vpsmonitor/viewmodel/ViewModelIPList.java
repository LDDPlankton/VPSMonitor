package com.vpsmonitor.viewmodel;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ViewModelIPList
{
	@NotNull(message = "Please enter a IP!")
	private int vpsipID;
	
	@NotEmpty(message = "Please enter a VPS Name!")
	private String vpsName;
	
	@NotEmpty(message = "Please enter a IP!")
	private String ipAddress;
	
	public int getID() { return this.vpsipID; }
	public String getVpsName() { return this.vpsName; }
	public String getIPAddress() { return this.ipAddress; }
	
	public void setID(int k) { this.vpsipID = k; }
	public void setVpsName(String k) { this.vpsName = k; }
	public void setIPAddress(String k) { this.ipAddress = k; }
}
