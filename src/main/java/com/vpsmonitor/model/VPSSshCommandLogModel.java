package com.vpsmonitor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vps_ssh_command_logs")
public class VPSSshCommandLogModel implements ModelInterface
{
	@Id
	@GeneratedValue
	@Column(name="command_log_id")
	private int commandLogID;
	
	@Column(name="vps_id")
	private int vpsID;
	
	@Column(name="admin_id")
	private int adminID;
	
	@Column(name="ssh_command")
	private String sshCommand;
	
	@Column(name="ssh_output")
	private String sshOutput;
	
	@Column(name="log_datetime")
	private Date logDateTime;

	public int getID() { return this.commandLogID; }
	public int getVpsId() { return this.vpsID; }
	public int getAdminId() { return this.adminID; }
	public String getSshCommand() { return this.sshCommand; }
	public String getSshOutput() { return this.sshOutput; }
	public Date getLogDateTime() { return this.logDateTime; }
	
	public void setID(int k) { this.commandLogID = k; }
	public void setVpsID(int k) { this.vpsID = k; }
	public void setAdminID(int k) { this.adminID = k; }
	public void setSshCommand(String k) { this.sshCommand = k; }
	public void setSshOutput(String k) { this.sshOutput = k; }
	public void setLogDateTime(Date k) { this.logDateTime = k; }

}
