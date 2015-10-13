package com.vpsmonitor.viewmodel;

import org.hibernate.validator.constraints.NotEmpty;

public class ViewModelEditSetting
{
	@NotEmpty(message = "Please enter a Version!")
	private String version;
	
    public String getVersion() { return this.version; }
    
    public void setVersion(String k) { this.version = k; }
}
