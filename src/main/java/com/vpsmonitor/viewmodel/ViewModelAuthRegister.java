package com.vpsmonitor.viewmodel;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ViewModelAuthRegister
{
	@Size(min = 7, max = 64, message = "Your email must be between 7 and 64 characters!")
    @Column(name="email")
    private String email;
	
	@Size(min = 8, max = 64, message = "Your password must be between 8 and 64 characters!")
    private String passWord;
	
	@Size(min = 8, max = 64, message = "Your password must be between 8 and 64 characters!")
    private String passWordConfirm;
	
    @NotNull(message = "Please select your Country Code!")	
	private int phoneCountryCode;
	
    @NotEmpty(message = "Please enter your phone number!")
    private String phoneNumber;
    
    /*
    public ViewModelAuthRegister()
    {
    	this.groupID = 0;
    	this.email = "";
    	this.passWord = "";
    	this.passWordConfirm = "";
    	this.phoneCountryCode = 0;
    	this.phoneNumber = "";
    }
    */
    
	//GET
	//public int getGroupID() { return this.groupID; }
	public String getEmail() { return this.email; }
	public String getPassWord() { return this.passWord; }
	public String getPassWordConfirm() { return this.passWordConfirm; }
	public int getPhoneCountryCode() { return this.phoneCountryCode; }
	public String getPhoneNumber() { return this.phoneNumber; }
	
	//SET
	//public void setGroupID(int id) { this.groupID = id; }
	public void setEmail(String email) { this.email = email; }
	public void setPassWord(String pwd) { this.passWord = pwd; }
	public void setPassWordConfirm(String pwd) { this.passWordConfirm = pwd; }
	public void setPhoneCountryCode(int id) { this.phoneCountryCode = id; }
	public void setPhoneNumber(String phone) { this.phoneNumber = phone; }
}
