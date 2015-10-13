package com.vpsmonitor.viewmodel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ViewModelEditAccount
{
	@NotNull(message = "Please enter your Admin ID!")
    private int adminID;
	
	@NotNull(message = "Please enter your Group ID!")
    private int groupID;
	
    @Size(min = 4, max = 128, message = "Your Email must be between 4 and 128 characters!")	
	@NotEmpty(message = "Please enter your email addresss!")
    private String email;
	
	@Size(min = 8, max = 128, message = "Your Password must be between 4 and 128 characters!")
    private String passWord;
	
	@Size(min = 10, max = 20, message = "Your Phone Number must be between 10 and 20 characters!")
    private String phoneNumber;
	
	public void setID(int id) { this.adminID = id; }
	public void setGroupID(int id) { this.groupID = id; }
	public void setEmail(String email) { this.email = email; }
	public void setPassWord(String pwd) { this.passWord = pwd; }
	public void setPhoneNumber(String phone) { this.phoneNumber = phone; }
	
	public int getID() { return this.adminID; }
	public int getGroupID() { return this.groupID; }
	public String getEmail() { return this.email; }
	public String getPassWord() { return this.passWord; }
	public String getPhoneNumber() { return this.phoneNumber; }
}
