package com.vpsmonitor.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public abstract class DAOBase <Entity> implements DAOInterface <Entity>
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private SessionFactory sessionFactory;
	
	private Class< Entity > classname;
	private String table;
	
	public DAOBase()
	{
		
	}
	
	public void setClass(Class< Entity > classname)
	{
		this.classname = classname;
	}
	
	public void setTable(String tbl)
	{
		this.table = tbl;
	}
	
	public String getTable()
	{
		return this.table;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	public int insert(Entity entity) throws DAOException
    {
		Session session = this.sessionFactory.openSession();  
		Transaction tx = session.beginTransaction();
		try
		{
			session.saveOrUpdate(entity);
			tx.commit();
		}
		catch(HibernateException e)
		{
			tx.rollback();
			throw new DAOException("[" + this.getClass().getName() + "::insert()]: " + e.getCause().getMessage() );
		}		
		Serializable id = session.getIdentifier(entity);  
		session.close();  
		return (Integer) id;    	
    }
  
    public void update(Entity entity) throws DAOException
    {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try
		{
			session.saveOrUpdate(entity);
			tx.commit();
		}
		catch(HibernateException e)
		{
			tx.rollback();
			throw new DAOException("[" + this.getClass().getName() + "::update()]: " + e.getCause().getMessage() );
		}
		session.close();
    }
    
    public void deleteByID(int id) throws DAOException
    {
		Session session = this.sessionFactory.openSession();  
		Transaction tx = session.beginTransaction();
		Entity tmpEntity = (Entity) session.load(this.classname, id);
		try
		{
			session.delete(tmpEntity); 
			tx.commit(); 
		}
		catch(HibernateException e)
		{
			tx.rollback();
			throw new DAOException("[" + this.getClass().getName() + "::deleteByID()]: " + e.getCause().getMessage() );
		}

		session.close();  	
    }
    
    public Entity getByID(int id) throws DAOException
    {
		Session session = this.sessionFactory.openSession();  
		Entity tmpEntity = null;
		try
		{
			tmpEntity = (Entity) session.load(this.classname, id);
		}
		catch(HibernateException e)
		{
			throw new DAOException("[" + this.getClass().getName() + "::getByID()]: " + e.getCause().getMessage() );
		}
		
		session.close();
		return tmpEntity; 
    }
    
    public List<Entity> getBy(ParamBuilder pb) throws DAOException
    {
		Session session = sessionFactory.openSession();
		List<Entity> newList = null;
		String sql = pb.getSQL();
		Map<String,Object> param_map = pb.getParameters();
		try
		{
			Query newquery = session.createQuery("FROM " + this.table + " " + sql);
			for(Map.Entry<String,Object> i : param_map.entrySet())
			{
				newquery.setParameter(i.getKey(), i.getValue());
			}
			newList = (List<Entity>) newquery.list();
		}
		catch(HibernateException e)
		{
			throw new DAOException("[" + this.getClass().getName() + "::getBy()]: " + e.getCause().getMessage() );
		}
		return newList; 
    }
    
    public List<Entity> getList()
    {
    	//GET SESSION
    	Session session = this.sessionFactory.getCurrentSession();

    	List<Entity> entityList = null;
    	try
    	{
    		Query query = session.createQuery("FROM " + this.classname.getName() );
    		entityList = (List<Entity>)query.list();
    	}
    	catch(HibernateException e)
    	{
    		System.out.println(e.getMessage());
    		System.exit(1);
    	}
    	
    	session.close();
    	return entityList;
    }
	
	
}
