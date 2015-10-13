package com.vpsmonitor.viewmodel;

import org.hibernate.validator.constraints.NotEmpty;

public class ViewModelSettingList
{
	@NotEmpty(message = "Please enter a Version!")
	public String version;
	
	public String getVersion() { return this.version; }
	
	public void setVersion(String k) { this.version = k; }
}
