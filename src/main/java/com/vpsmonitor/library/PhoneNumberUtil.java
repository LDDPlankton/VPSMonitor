package com.vpsmonitor.library;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberUtil
{
	public PhoneNumberUtil()
	{
		
	}
	
	public static boolean isPhoneNumberValid(String phoneNumber)
	{
		/*
		Phone Number formats: (nnn)nnn-nnnn; nnnnnnnnnn; nnn-nnn-nnnn 
	    ^\\(? : May start with an option "(" . 
	    (\\d{3}): Followed by 3 digits. 
	    \\)? : May have an optional ")"  
	    [- ]? : May have an optional "-" after the first 3 digits or after optional ) character.  
	    (\\d{3}) : Followed by 3 digits.  
	     [- ]? : May have another optional "-" after numeric digits. 
	     (\\d{4})$ : ends with four digits. 
	 
	         Examples: Matches following phone numbers: 
	         (123)456-7890, 123-456-7890, 1234567890, (123)-456-7890 
		*/ 
		
		boolean isValid = false;
		String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";  
		CharSequence inputStr = phoneNumber;  
		Pattern pattern = Pattern.compile(expression);  
		Matcher matcher = pattern.matcher(inputStr);  
		if(matcher.matches())
		{  
			isValid = true;  
		}  
		return isValid;
	}
}
