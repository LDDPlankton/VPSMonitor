package com.vpsmonitor.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.vpsmonitor.model.AdminModel;
import com.vpsmonitor.model.RESTAPIServerInfoModel;
import com.vpsmonitor.model.SettingModel;
import com.vpsmonitor.model.VPSIPModel;
import com.vpsmonitor.model.VPSListModel;
import com.vpsmonitor.model.VPSMonitorModel;
import com.vpsmonitor.model.VPSSshCommandLogModel;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.vpsmonitor.config","com.vpsmonitor.dao", "com.vpsmonitor.controller", "com.vpsmonitor.manager"})
public class AppConfig
{	
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new CustomUserDetailsService();
	}
	
	@Bean
	public MessageSource messageSource()
	{
		//ReloadableResouceBundleMessageSource
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
    @Bean
    public DataSource dataSource() 
    {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/vpsmonitor");
	    dataSource.setUsername("{user}");
	    dataSource.setPassword("{password}");
	    return dataSource;
    }

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource)
	{
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public SessionFactory sessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
	    bean.setDataSource(dataSource);
	    
        Class<?>[] classes = {AdminModel.class, RESTAPIServerInfoModel.class, SettingModel.class, 
        		VPSIPModel.class, VPSListModel.class, VPSMonitorModel.class, VPSSshCommandLogModel.class}; //{ UserModel.class };
        bean.setAnnotatedClasses(classes);
	    
		Properties props = new Properties();
		props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.put("hibernate.show_sql", "false");
		
		bean.setHibernateProperties(props);
		try
		{
			bean.afterPropertiesSet();
		}
		catch (IOException e)
		{
			System.out.println("sessionFactory() IOException: " + e.getMessage() );
		}

        return bean.getObject();
	}
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory)
	{
		return new HibernateTransactionManager(sessionFactory);
	}
}
