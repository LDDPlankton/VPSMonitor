package com.vpsmonitor.dao;

import org.springframework.stereotype.Repository;

import com.vpsmonitor.model.VPSIPModel;

@Repository
public class VPSIPDAO extends DAOBase <VPSIPModel>
{
	public VPSIPDAO()
	{
		this.setClass(VPSIPModel.class);
		this.setTable("VPSIPModel");
	}
}
