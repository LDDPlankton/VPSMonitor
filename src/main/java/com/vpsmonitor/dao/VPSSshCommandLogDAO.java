package com.vpsmonitor.dao;

import org.springframework.stereotype.Repository;

import com.vpsmonitor.model.VPSSshCommandLogModel;

@Repository
public class VPSSshCommandLogDAO extends DAOBase <VPSSshCommandLogModel>
{
	public VPSSshCommandLogDAO()
	{
		this.setClass(VPSSshCommandLogModel.class);
		this.setTable("VPSSshCommandLogModel");
	}
}
