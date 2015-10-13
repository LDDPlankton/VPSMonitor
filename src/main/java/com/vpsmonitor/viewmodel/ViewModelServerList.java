package com.vpsmonitor.viewmodel;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ViewModelServerList
{
	@NotNull(message = "Please enter your VPS ID!")
	private int vpsID;
	
	@NotEmpty(message = "Please enter your VPS Name!")
	private String name;
	
	@NotEmpty(message = "Please enter your Primary IP!")
	private String primaryIP;
	
	@NotEmpty(message = "Please enter your VPS Status!")
	private String status;
	
	public int getID() { return this.vpsID; }
	public String getName() { return this.name; }
	public String getPrimaryIP() { return this.primaryIP; }
	public String getStatus() { return this.status; }
	
	public void setID(int k) { this.vpsID = k; }
	public void setName(String k) { this.name = k; }
	public void setPrimaryIP(String k) { this.primaryIP = k; }
	public void setStatus(String k) { this.status = k; }
}
