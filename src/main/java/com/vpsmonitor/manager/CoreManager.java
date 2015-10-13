package com.vpsmonitor.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vpsmonitor.dao.AdminDAO;
import com.vpsmonitor.dao.DAOException;
import com.vpsmonitor.dao.ParamBuilder;

@Service
public class CoreManager
{
	@Autowired
	private AdminDAO adminDAO;
	
	public CoreManager()
	{
		
	}
	
	public boolean isValidDatabaseConnection()
	{
		try
		{
			this.adminDAO.getBy( new ParamBuilder() );
		}
		catch (DAOException e)
		{
			System.out.println("ERROR="+e.getMessage());
			return false;
		}
		return true;
	}
	
}
