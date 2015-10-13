package com.vpsmonitor.viewmodel;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ViewModelAddIP
{
	@NotNull(message = "Please enter a VPS ID!")
	private int vpsID;
	
	@NotEmpty(message = "Please enter a IP!")
	private String IPAddress;
	
	public int getVpsID() { return this.vpsID; }
	public String getIPAddress() { return this.IPAddress; }
	
	public void setVpsID(int k) { this.vpsID = k; }
	public void setIPAddress(String k) { this.IPAddress = k; }
}
