package com.vpsmonitor.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vpsmonitor.dao.AdminDAO;
import com.vpsmonitor.dao.DAOException;
import com.vpsmonitor.dao.ParamBuilder;
import com.vpsmonitor.model.AdminModel;

//A @Component/@Service cannot be turned into a Bean. This means if we intend to register a Bean, we must ensure neither @'s are here.

@Service
public class AdminManager extends ManagementBase<AdminModel>
{	
	@Autowired
	public AdminManager(AdminDAO dao)
	{
		this.dao = dao;
		this.model = new AdminModel();
	}
	
	public int addAdminUser(int groupID, String email, String password, String phone) throws Exception
	{
		int user_id = 0;
		AdminModel model = new AdminModel();
		model.setGroupID(groupID);
		model.setEmail(email);
		model.setPassWord(password);
		model.setPhoneNumber(phone);
		model.setLastLogin(new Date());
		model.setCreateDate(new Date());
		
        try
        {
			user_id = this.dao.insert(model);
		}
        catch (DAOException e)
        {
        	throw new Exception("AdminManager::addAdminUser Exception: " + e.getMessage());
		}
        return user_id;
	}
	
	public boolean isEmailExist(String email)
	{
		AdminModel model = this.findByEmail(email);
		if(model != null)
			return true;
		else
			return false;
	}
	
	public AdminModel findByID(int id)
	{
		AdminModel model = this.findOneByKeyVal("admin_id", id);
		if(model == null)
			return null;
		else 
			return model;
	}
	
	public AdminModel findByEmail(String email)
	{		
		AdminModel model = this.findOneByKeyVal("email", email);
		if(model == null)
			return null;
		else 
			return model;
	}

	public List<AdminModel> getBy(ParamBuilder pb)
	{
		List<AdminModel> List = new ArrayList<AdminModel>();
		try
		{
			List = this.dao.getBy( pb );
		}
		catch (DAOException e)
		{
			System.out.println("GETBY ERROR! " + e.getMessage());
			return null;
		}
		return List;
	}
	



}
