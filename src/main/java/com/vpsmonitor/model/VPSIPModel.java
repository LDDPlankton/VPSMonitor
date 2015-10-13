package com.vpsmonitor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vps_ips")
public class VPSIPModel implements ModelInterface
{
	@Id
	@GeneratedValue
	@Column(name="vps_ip_id")
	private int vpsipID;
	
	@Column(name="vps_id")
	private int vpsID;
	
	@Column(name="ip_address")
	private String ipAddress;
	
	@Column(name="create_datetime")
	private Date createDateTime;
	
	public int getID() { return this.vpsipID; }
	public int getVpsID() { return this.vpsID; }
	public String getIPAddress() { return this.ipAddress; }
	public Date getCreateDateTime() { return this.createDateTime; }
	
	public void setID(int k) { this.vpsipID = k; }
	public void setVpsID(int k) { this.vpsID = k; }
	public void setIPAddress(String k) { this.ipAddress = k; }
	public void setCreateDateTime(Date k) { this.createDateTime = k; }



}
