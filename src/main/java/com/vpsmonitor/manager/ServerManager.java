package com.vpsmonitor.manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vpsmonitor.dao.DAOException;
import com.vpsmonitor.dao.VPSListDAO;
import com.vpsmonitor.model.VPSListModel;

@Service
public class ServerManager extends ManagementBase<VPSListModel>
{
	@Autowired
	public ServerManager(VPSListDAO dao)
	{
		this.dao = dao;
		this.model = new VPSListModel();
	}
	
	public int addServer(String name, String primaryIP, String sshNormalUser, String sshNormalPass, String sshRootPassword, String sshRootKey, 
			int isNormalUserRequired, int isRootKeyRequired, String restAuthorizationHash) throws Exception
	{
		int server_id = 0;
		VPSListModel model = new VPSListModel();
		model.setName(name);
		model.setPrimaryIP(primaryIP);
		model.setStatus(1);
		model.setCreateDateTime(new Date());
		model.setSshNormalUser(sshNormalUser);
		model.setSshNormalPass(sshNormalPass);
		model.setSshRootPassword(sshRootPassword);
		model.setSshRootKey(sshRootKey);
		model.setSshIsNormalUserRequired(isNormalUserRequired);
		model.setSshIsRootKeyRequired(isRootKeyRequired);
		model.setRestAuthorizationHash(restAuthorizationHash);
		
        try
        {
        	server_id = this.dao.insert(model);
		}
        catch (DAOException e)
        {
			throw new Exception("ServerManager::addServer Exception: " + e.getMessage());
		}
        return server_id;
	}
	
	public VPSListModel findByVPSID(int id)
	{
		VPSListModel model = this.findOneByKeyVal("vps_id", id);
		if(model == null)
			return null;
		else 
			return model;
	}
	
	public VPSListModel findByVPSName(String name)
	{
		VPSListModel model = this.findOneByKeyVal("name", name);
		if(model == null)
			return null;
		else 
			return model;
	}
	

	
}
