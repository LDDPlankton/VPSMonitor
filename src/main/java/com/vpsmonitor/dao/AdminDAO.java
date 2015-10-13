package com.vpsmonitor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vpsmonitor.model.AdminModel;

@Repository
public class AdminDAO extends DAOBase <AdminModel>
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public AdminDAO()
	{
		this.setClass(AdminModel.class);
		this.setTable("AdminModel");
	}
}
