package com.vpsmonitor.manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vpsmonitor.dao.DAOException;
import com.vpsmonitor.dao.VPSIPDAO;
import com.vpsmonitor.model.VPSIPModel;

@Service
public class IPManager extends ManagementBase<VPSIPModel>
{
	@Autowired
	public IPManager(VPSIPDAO dao)
	{
		this.dao = dao;
		this.model = new VPSIPModel();
	}
	
	public int addIP(int vps_id, String ip) throws Exception
	{
		int ip_id = 0;
		VPSIPModel model = new VPSIPModel();
		model.setVpsID(vps_id);
		model.setIPAddress(ip);
		model.setCreateDateTime(new Date());
		
        try
        {
			ip_id = this.dao.insert(model);
		}
        catch (DAOException e)
        {
        	throw new Exception("IPManager::addIP Exception: " + e.getMessage());
		}
        return ip_id;
	}
	
	public boolean isIPExist(String ip)
	{
		VPSIPModel model = this.findByIP(ip);
		if(model == null)
			return false;
		else
			return true;
	}
	
	public VPSIPModel findByID(int id)
	{
		VPSIPModel model = this.findOneByKeyVal("vps_ip_id", id);
		if(model == null)
			return null;
		else 
			return model;
	}
	
	public VPSIPModel findByIP(String ip)
	{
		VPSIPModel model = this.findOneByKeyVal("ip_address", ip);
		if(model == null)
			return null;
		else 
			return model;
	}
}
