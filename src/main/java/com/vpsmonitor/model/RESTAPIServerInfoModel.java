package com.vpsmonitor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="restapi_serverinfo")
public class RESTAPIServerInfoModel implements ModelInterface
{
	@Id
	@GeneratedValue
	@Column(name="restapi_serverinfo_id")
	private int restapiServerInfoID;
	
	@Column(name="vps_id")
	private int vpsID;
	
	@Column(name="create_datetime")
	private Date createDateTime;
	
	public int getID() { return this.restapiServerInfoID; }
	public int getVpsID() { return this.vpsID; }
	public Date getCreateDateTime() { return this.createDateTime; }	
	
	public void setID(int k) { this.restapiServerInfoID = k; }
	public void setVpsID(int k) { this.vpsID = k; }
	public void setCreateDateTime(Date k) { this.createDateTime = k; }
	
	
	
}
