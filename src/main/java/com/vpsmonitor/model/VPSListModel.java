package com.vpsmonitor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vps_list")
public class VPSListModel implements ModelInterface
{
	@Id
	@GeneratedValue
	@Column(name="vps_id")
	private int vpsID;
	
	@Column(name="name")
	private String name;
	
	@Column(name="primary_ip")
	private String primaryIP;
	
	@Column(name="status")
	private int status;
	
	@Column(name="create_datetime")
	private Date createDateTime;
	
	@Column(name="ssh_normal_user")
	private String sshNormalUser;
	
	@Column(name="ssh_normal_pass")
	private String sshNormalPass;
	
	@Column(name="ssh_root_password")
	private String sshRootPassword;
	
	@Column(name="ssh_root_key")
	private String sshRootKey;
	
	@Column(name="ssh_is_normal_user_required")
	private int sshIsNormalUserRequired;
	
	@Column(name="ssh_is_root_key_required")
	private int sshIsRootKeyRequired;
	
	@Column(name="rest_authorization_hash")
	private String restAuthorization_Hash;
	
	public int getID() { return this.vpsID; }
	public String getName() { return this.name; }
	public String getPrimaryIP() { return this.primaryIP; }
	public int getStatus() { return this.status; }
	public Date getCreateDateTime() { return this.createDateTime; }
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
	public void setCreateDateTime(Date k) { this.createDateTime = k; }
	public void setSshNormalUser(String k) { this.sshNormalUser = k; }
	public void setSshNormalPass(String k) { this.sshNormalPass = k; }
	public void setSshRootPassword(String k) { this.sshRootPassword = k; }
	public void setSshRootKey(String k) { this.sshRootKey = k; }
	public void setSshIsNormalUserRequired(int k) { this.sshIsNormalUserRequired = k; }
	public void setSshIsRootKeyRequired(int k) { this.sshIsRootKeyRequired = k; }
	public void setRestAuthorizationHash(String k) { this.restAuthorization_Hash = k; }
		
}
