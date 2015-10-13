package com.vpsmonitor.library;

public class JSONResponse
{
	private boolean status;
	private boolean redirect;
	private String message;
	
	public JSONResponse()
	{
		
	}
	
	public void setStatus(boolean stat) { this.status = stat; }
	public void setRedirect(boolean redir) { this.redirect = redir; }
	public void setMessage(String msg) { this.message = msg; }
	
	public boolean getStatus() { return this.status; }
	public boolean getRedirect() { return this.redirect; }
	public String getMessage() { return this.message; }
	
}
