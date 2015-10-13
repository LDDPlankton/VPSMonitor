package com.vpsmonitor.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="admins")
public class AdminModel implements ModelInterface
{
	@Id
	@GeneratedValue
	@Column(name="admin_id")
    private int adminID;
	
    @Column(name="group_id")
    private int groupID;
	
	@Size(min = 7, max = 128)
    @Column(name="email")
	@NotEmpty(message = "Please enter your email addresss!")
    private String email;
	
	@Size(min = 8, max = 128)
    @Column(name="password")
    private String passWord;
	
    @Column(name="phone_number")
    private String phoneNumber;
	
    @DateTimeFormat(iso=ISO.DATE)
    @Column(name="last_login")
    private Date lastLogin;
	
	@DateTimeFormat(pattern="MM/dd/yyyy")
    @Column(name="create_date")
    private Date createDate;
	
	public void setID(int id) { this.adminID = id; }
	public void setGroupID(int id) { this.groupID = id; }
	public void setEmail(String email) { this.email = email; }
	public void setPassWord(String pwd) { this.passWord = pwd; }
	public void setPhoneNumber(String phone) { this.phoneNumber = phone; }
	public void setLastLogin(Date last) { this.lastLogin = last; }
	public void setCreateDate(Date create) { this.createDate = create; }
	
	public int getID() { return this.adminID; }
	public int getGroupID() { return this.groupID; }
	public String getEmail() { return this.email; }
	public String getPassWord() { return this.passWord; }
	public String getPhoneNumber() { return this.phoneNumber; }
	public Date getLastLogin() { return this.lastLogin; }
	public Date getCreateDate() { return this.createDate; }
	
}
