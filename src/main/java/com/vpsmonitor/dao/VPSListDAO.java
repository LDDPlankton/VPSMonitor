package com.vpsmonitor.dao;

import org.springframework.stereotype.Repository;

import com.vpsmonitor.model.VPSListModel;

@Repository
public class VPSListDAO extends DAOBase <VPSListModel>
{
	public VPSListDAO()
	{
		this.setClass(VPSListModel.class);
		this.setTable("VPSListModel");
	}
}
