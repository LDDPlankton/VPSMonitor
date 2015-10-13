package com.vpsmonitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity //@EnableWebSecurity SWITCHED DUE TO CSRF TOKEN
@ComponentScan(basePackages = {"com.vpsmonitor.config","com.vpsmonitor.dao", "com.vpsmonitor.controller", "com.vpsmonitor.manager"})
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserDetailsService userDetailsService;
	
	public SecurityConfig()
	{
		
	}
	
    //OTHER OPTION: SHAPasswordEncoder == Require passed salt value, this though generates internal random salt.
    @Bean
    public PasswordEncoder passwordEncoder()
    {
    	return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider()
    {
    	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    	authProvider.setUserDetailsService(userDetailsService);
    	authProvider.setPasswordEncoder(passwordEncoder());
    	return authProvider;
    }
    
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(authProvider());
		
		//auth.jdbcAuthentication()
		//.dataSource(dataSource())
		//.usersByUsernameQuery("select email,password,status from admins where email=?")		
		//.authoritiesByUsernameQuery("select admins.email, groups.name FROM admins, groups WHERE admins.group_id=groups.group_id AND admins.email=?");	//USER | ADMIN/ROLE
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		System.out.println("SecurityConfig configure...");
		
		http.authorizeRequests().antMatchers("/resources/**", "/css/**", "/js/**", "/img/**").permitAll().anyRequest().authenticated();
		
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
		.and()
		.formLogin()
			.loginPage("/auth/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/auth/welcome")
			.usernameParameter("username")
			.passwordParameter("password")
			.permitAll()
		.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl( "/auth/login" )
			.invalidateHttpSession(true)
		.and()
		.exceptionHandling().accessDeniedPage( "/WEB-INF/views/error/403.jsp" )
		.and()
        .csrf();
		
		//RESTRICT URL TO USER WITH ROLE
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").anyRequest().authenticated();
		
		AntPathRequestMatcher pathRequestMatcher = new AntPathRequestMatcher("/logout");
		http.logout().logoutRequestMatcher(pathRequestMatcher);
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/css/**")
		.antMatchers("/js/**")
		.antMatchers("/img/**");
	}
}
