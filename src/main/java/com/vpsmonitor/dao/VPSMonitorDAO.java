package com.vpsmonitor.dao;

import org.springframework.stereotype.Repository;

import com.vpsmonitor.model.VPSMonitorModel;

@Repository
public class VPSMonitorDAO extends DAOBase <VPSMonitorModel>
{
	public VPSMonitorDAO()
	{
		this.setClass(VPSMonitorModel.class);
		this.setTable("VPSMonitorModel");
	}
}
