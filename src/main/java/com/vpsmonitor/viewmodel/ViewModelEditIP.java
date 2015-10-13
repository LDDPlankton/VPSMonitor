package com.vpsmonitor.viewmodel;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ViewModelEditIP
{
	@NotNull(message = "Please enter a ID!")
	private int vpsipID;
	
	@NotNull(message = "Please enter a VPS ID!")
	private int vpsID;
	
	@NotEmpty(message = "Please enter a IP!")
	private String ipAddress;
	
	public int getID() { return this.vpsipID; }
	public int getVpsID() { return this.vpsID; }
	public String getIPAddress() { return this.ipAddress; }
	
	public void setID(int k) { this.vpsipID = k; }
	public void setVpsID(int k) { this.vpsID = k; }
	public void setIPAddress(String k) { this.ipAddress = k; }
}
