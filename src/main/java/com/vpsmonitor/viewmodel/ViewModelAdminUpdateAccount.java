package com.vpsmonitor.viewmodel;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ViewModelAdminUpdateAccount
{
	@Size(min = 4, max = 64, message = "Your email address must be between 7 and 128 characters!")
    @Column(name="email")
	@NotEmpty(message = "Please enter your email addresss!")
    private String email;
	
	@Size(min = 8, max = 128, message = "Your password must be between 8 and 128 characters!")
    @Column(name="password")
    private String passWord;
	
	@Size(min = 8, max = 128)
	@NotEmpty(message = "Please confirm your password!")
    private String passWordConfirm;
	
    @Column(name="phone_number")
    private String phoneNumber;
	
	public void setEmail(String email) { this.email = email; }
	public void setPassWord(String pwd) { this.passWord = pwd; }
	public void setPassWordConfirm(String pwd) { this.passWordConfirm = pwd; }
	public void setPhoneNumber(String phone) { this.phoneNumber = phone; }

	public String getEmail() { return this.email; }
	public String getPassWord() { return this.passWord; }
	public String getPassWordConfirm() { return this.passWordConfirm; }
	public String getPhoneNumber() { return this.phoneNumber; }
}
