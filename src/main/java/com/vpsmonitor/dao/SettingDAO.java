package com.vpsmonitor.dao;

import org.springframework.stereotype.Repository;

import com.vpsmonitor.model.SettingModel;

@Repository
public class SettingDAO extends DAOBase <SettingModel>
{
	public SettingDAO()
	{
		this.setClass(SettingModel.class);
		this.setTable("Setting");
	}
}
