package com.vpsmonitor.viewmodel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ViewModelEditServer
{
	@NotNull(message = "Please enter your VPS ID!")
	private int vpsID;
	
	@Size(min = 4, max = 64, message = "Your VPS Name must be between 4 and 64 characters!")	
	private String name;
	
	@Size(min = 7, message = "Your Primary IP must be at least 7 characters!")
	private String primaryIP;
	
	@NotNull(message = "Please select a status!")
	private int status;
	
	private String sshNormalUser;
	private String sshNormalPass;
	private String sshRootPassword;
	private String sshRootKey;
	
	@NotNull(message = "Please select if a Wheel User is Required!")
	private int sshIsNormalUserRequired;
	
	@NotNull(message = "Please select if a Root Key is Required!")
	private int sshIsRootKeyRequired;
	
	private String restAuthorization_Hash;
	
	public int getID() { return this.vpsID; }
	public String getName() { return this.name; }
	public String getPrimaryIP() { return this.primaryIP; }
	public int getStatus() { return this.status; }
	public String getSshNormalUser() { return this.sshNormalUser; }
	public String getSshNormalPass() { return this.sshNormalPass; }
	public String getSshRootPassword() { return this.sshRootPassword; }
	public String getSshRootKey() { return this.sshRootKey; }
	public int getSshIsNormalUserRequired() { return this.sshIsNormalUserRequired; }
	public int getSshIsRootKeyRequired() { return this.sshIsRootKeyRequired; }
	public String getRestAuthorizationHash() { return this.restAuthorization_Hash; }
	
	public void setID(int k) { this.vpsID = k; }
	public void setName(String k) { this.name = k; }
	public void setPrimaryIP(String k) { this.primaryIP = k; }
	public void setStatus(int k) { this.status = k; }
	public void setSshNormalUser(String k) { this.sshNormalUser = k; }
	public void setSshNormalPass(String k) { this.sshNormalPass = k; }
	public void setSshRootPassword(String k) { this.sshRootPassword = k; }
	public void setSshRootKey(String k) { this.sshRootKey = k; }
	public void setSshIsNormalUserRequired(int k) { this.sshIsNormalUserRequired = k; }
	public void setSshIsRootKeyRequired(int k) { this.sshIsRootKeyRequired = k; }
	public void setRestAuthorizationHash(String k) { this.restAuthorization_Hash = k; }
}
