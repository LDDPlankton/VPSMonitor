package com.vpsmonitor.dao;

import org.springframework.stereotype.Repository;

import com.vpsmonitor.model.RESTAPIServerInfoModel;

@Repository
public class RESTAPIServerInfoDAO extends DAOBase <RESTAPIServerInfoModel>
{
	public RESTAPIServerInfoDAO()
	{
		this.setClass(RESTAPIServerInfoModel.class);
		this.setTable("Restapi_serverinfo");
	}
}
