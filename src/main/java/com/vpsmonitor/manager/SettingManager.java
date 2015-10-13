package com.vpsmonitor.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vpsmonitor.dao.SettingDAO;
import com.vpsmonitor.model.SettingModel;

@Service
public class SettingManager extends ManagementBase<SettingModel>
{
	@Autowired
	public SettingManager(SettingDAO dao)
	{
		this.dao = dao;
		this.model = new SettingModel();
	}
	
	public SettingModel findByID(int id)
	{
		SettingModel model = this.findOneByKeyVal("setting_id", id);
		if(model == null)
			return null;
		else 
			return model;
	}
	
	public SettingModel findByKey(String key)
	{
		SettingModel model = this.findOneByKeyVal("key", key);
		if(model == null)
			return null;
		else 
			return model;
	}

}
