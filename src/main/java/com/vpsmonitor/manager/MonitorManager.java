package com.vpsmonitor.manager;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vpsmonitor.dao.DAOException;
import com.vpsmonitor.dao.VPSMonitorDAO;
import com.vpsmonitor.model.VPSMonitorModel;

@Service
public class MonitorManager extends ManagementBase<VPSMonitorModel>
{
	@Autowired
	public MonitorManager(VPSMonitorDAO dao)
	{
		this.dao = dao;
		this.model = new VPSMonitorModel();
	}
	
	public int addMonitor(int vps_id, String monitorIPOrUrl, int port, int timeout, int isRequireValidContent, String validContent)
	{
		int monitor_id = 0;
		VPSMonitorModel model = new VPSMonitorModel();
		model.setVpsID(vps_id);
		model.setMonitorIPOrUrl(monitorIPOrUrl);
		model.setMonitorPort(port);
		model.setMonitorTimeout(timeout);
		model.setIsRequireValidContent(isRequireValidContent);
		model.setValidContent(validContent);
		model.setCreateDateTime(new Date());
		model.setLastMonitoredDateTime(new Date());
		model.setStatus(1);
		
        try
        {
        	monitor_id = this.dao.insert(model);
		}
        catch (DAOException e)
        {
			return 0;
		}
        return monitor_id;
	}
	
	public VPSMonitorModel findByVPSID(int id)
	{
		VPSMonitorModel model = this.findOneByKeyVal("monitorID", id);
		if(model == null)
			return null;
		else 
			return model;
	}
	
}
