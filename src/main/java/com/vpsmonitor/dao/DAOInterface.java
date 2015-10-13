package com.vpsmonitor.dao;

import java.util.List;

public interface DAOInterface <Entity>
{
	//CORE
	public void setClass(Class< Entity > classname);
	public void setTable(String tbl);
    public int insert(Entity entity) throws DAOException;
    public void update(Entity entity) throws DAOException;
    public void deleteByID(int id) throws DAOException;

    //NON-CORE
    public Entity getByID(int id) throws DAOException;
    public List<Entity> getBy(ParamBuilder pb) throws DAOException;
    public String getTable();
}
