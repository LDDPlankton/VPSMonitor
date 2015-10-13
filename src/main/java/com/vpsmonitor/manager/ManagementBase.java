package com.vpsmonitor.manager;

import java.util.ArrayList;
import java.util.List;

import com.vpsmonitor.dao.DAOException;
import com.vpsmonitor.dao.DAOInterface;
import com.vpsmonitor.dao.ParamBuilder;
import com.vpsmonitor.model.ModelInterface;

public class ManagementBase <MT>
{
	protected DAOInterface<MT> dao = null;
	protected ModelInterface model = null;
	
	public void setModel(DAOInterface<MT> model)
	{
		this.dao = model;
	}
	
	public MT findOneByKeyVal(String key, Object value)
	{
		if(key == null || value == null)
			return null;
		
        MT tmpUser = null;
        List<MT> userList = new ArrayList<MT>();
        ParamBuilder p1 = new ParamBuilder();
        p1.addParam(key, value);
        try
        {
			userList = this.dao.getBy(p1);
            if( userList.size() == 0)
            	return null;
            tmpUser = userList.get(0);
		}
        catch (DAOException e)
        {
        	return null;
		}
        return tmpUser;
	}
	
	public List<MT> getBy(ParamBuilder pb)
	{
		List<MT> List = new ArrayList<MT>();
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
	
	public boolean update(MT model)
	{
		try
		{
			this.dao.update(model);
		}
		catch (DAOException e)
		{
			return false;
		}
		return true;
	}
	
	public boolean delete(ModelInterface model)
	{
		try
		{
			this.dao.deleteByID(model.getID());
		}
		catch (DAOException e)
		{
			return false;
		}
		return true;
	}
	
}
