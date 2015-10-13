package com.vpsmonitor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vps_monitor")
public class VPSMonitorModel implements ModelInterface
{
	@Id
	@GeneratedValue
	@Column(name="monitor_id")
	private int monitorID;
	
	@Column(name="vps_id")
	private int vpsID;
	
	@Column(name="monitor_ip_or_url")
	private String monitorIPOrUrl;
	
	@Column(name="monitor_port")
	private int monitorPort;
	
	@Column(name="monitor_timeout")
	private int monitorTimeout;
	
	@Column(name="is_require_valid_content")
	private int isRequireValidContent;
	
	@Column(name="valid_content")
	private String validContent;
	
	@Column(name="create_datetime")
	private Date createDateTime;
	
	@Column(name="last_monitored_datetime")
	private Date lastMonitoredDateTime;

	@Column(name="status")
	private int status;
	
	public int getID() { return this.monitorID; }
	public int getVpsID() { return this.vpsID; }
	public String getMonitorIPOrUrl() { return this.monitorIPOrUrl; }
	public int getMonitorPort() { return this.monitorPort; }
	public int getMonitorTimeout() { return this.monitorTimeout; }
	public int getIsRequireValidContent() { return this.isRequireValidContent; }
	public String getValidContent() { return this.validContent; }
	public Date getCreateDateTime() { return this.createDateTime; }
	public Date getLastMonitoredDateTime() { return this.lastMonitoredDateTime; }
	public int getStatus() { return this.status; }
	
	public void setID(int k) { this.monitorID = k; }
	public void setVpsID(int k) { this.vpsID = k; }
	public void setMonitorIPOrUrl(String k) { this.monitorIPOrUrl = k; }
	public void setMonitorPort(int k) { this.monitorPort = k; }
	public void setMonitorTimeout(int k) { this.monitorTimeout = k; }
	public void setIsRequireValidContent(int k) { this.isRequireValidContent = k; }
	public void setValidContent(String k) { this.validContent = k; }
	public void setCreateDateTime(Date k) { this.createDateTime = k; }
	public void setLastMonitoredDateTime(Date k) { this.lastMonitoredDateTime = k; }
	public void setStatus(int k) { this.status = k; }
}
