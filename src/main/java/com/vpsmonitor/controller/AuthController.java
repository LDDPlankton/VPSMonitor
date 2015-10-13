package com.vpsmonitor.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.vpsmonitor.library.JSONResponse;
import com.vpsmonitor.library.PhoneCountryCode;
import com.vpsmonitor.library.PhoneNumberUtil;
import com.vpsmonitor.manager.AdminManager;
import com.vpsmonitor.viewmodel.ViewModelAuthRegister;

@Controller
@RequestMapping(value = "/auth")
public class AuthController
{
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private AdminManager adminManager;
		
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, 
			@RequestParam(value = "error", required = false) String error, 
			@RequestParam(value = "logout", required = false) String logout)
	{
		if (error != null)
		{
			model.addAttribute("error", "Invalid username and password!");
		}
 
		if (logout != null)
		{
			model.addAttribute("msg", "You've been logged out successfully.");
		}
		
		BCryptPasswordEncoder pass = new BCryptPasswordEncoder(13);
		String passw = pass.encode("password");
		
		//$2a$13$4803EW/hSDTyN3nsxU4OYuy6PhmE6RveZ5dNexRmqNFNByZsZq5He
		System.out.println("PASSWORD===============" + passw);
		

		
		model.addAttribute("page_title", "User Login");
		return "auth/login";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String AuthRegister(Model model)
	{
		model.addAttribute("page_title", "User Registration");
		
		//COUNTRY CODE VALUES
		PhoneCountryCode countryCode = new PhoneCountryCode();
		model.addAttribute("SelectPhoneCountryCode", countryCode.getDict());
		
		//BIND FORM COMMAND [VIEW MODEL]
		model.addAttribute("ViewModelAuthRegister", new ViewModelAuthRegister() );
		
		return "auth/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, headers="Accept=application/json")
	public @ResponseBody JSONResponse AuthRegisterPost(Model model, @ModelAttribute("ViewModelAuthRegister") @Valid ViewModelAuthRegister viewModel, BindingResult result, HttpSession session, BindException errors)
	{
		//SET RESPONSE INFORMATION
		String message = "";
		JSONResponse resp = new JSONResponse();
		resp.setStatus(false);
		resp.setRedirect(false);
		
		//FIX PHONE NUMBER
		String phoneNumber = viewModel.getPhoneNumber();
        phoneNumber = phoneNumber.replace(" ", "");
        phoneNumber = phoneNumber.replace("(", "");
        phoneNumber = phoneNumber.replace(")", "");
        phoneNumber = phoneNumber.replace("-", "");
        
        //IF PHONE NUMBER NOT VALID
        if(!PhoneNumberUtil.isPhoneNumberValid(phoneNumber))
        {
        	errors.reject("PhoneNumberInvalid", "Your phone number is invalid!");
        	
    		//RETURN RESPONSE
    		resp.setMessage("The phone number is invalid");
    		return resp;
        }
		
		if (result.hasErrors())
		{
			logger.info("HOME2p => ERRORS");
			for (FieldError error : result.getFieldErrors())
			{
				String error_msg = this.messageSource.getMessage(error, null);
				message += error_msg + "<br>";
			}
        }
		else
		{			
			//ADD USER		
			try
			{
				this.adminManager.addAdminUser(1, viewModel.getEmail(), viewModel.getPassWord(), viewModel.getPhoneNumber());
				
				//NO ERRORS ALL OK
				resp.setStatus(true);
				resp.setRedirect(true);
			}
			catch(Exception e)
			{
				message = e.getMessage();
			}
		}
		
		//RETURN RESPONSE
		resp.setMessage(message);
		return resp;
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public View AuthWelcome(Model model)
	{
		logger.info("GOT TO AUTHWELCOME()");
		
		//GET ARTIFACT ID
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
		String artid = request.getContextPath();
		
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		logger.info("NAME=" + name);
		
        Set<String> roles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for(String i : roles)
        {
        	System.out.println("ROLE="+i);
        }
        
        if (roles.contains("ROLE_ADMIN"))
        {
            return new RedirectView(artid + "/admin/");
        }
        return new RedirectView(artid + "/auth/login");
	}
}
