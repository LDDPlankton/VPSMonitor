package com.vpsmonitor.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vpsmonitor.manager.AdminManager;
import com.vpsmonitor.model.AdminModel;

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService
{
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
    private AdminManager adminManager;
	
	public CustomUserDetailsService()
	{
		
	}
	
    public List<GrantedAuthority> getGrantedAuthorities(List<String> roles)
    {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
	
	public Collection<? extends GrantedAuthority> getAuthorities(Integer role)
	{
        List<GrantedAuthority> authList = this.getGrantedAuthorities(getRoles(role));
        return authList;
    }
	
    public List<String> getRoles(Integer role)
    {
        List<String> roles = new ArrayList<String>();
 
        if (role.intValue() == 1)
        {
            roles.add("ROLE_ADMIN");
        }
        else if (role.intValue() == 2)
        {
            roles.add("ROLE_DISABLED");
        }
        else if(role.intValue() == 3)
        {
        	roles.add("ROLE_USER");
        }
        return roles;
    }

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
	{		
		String salt = BCrypt.gensalt(13);
		String hashed_password = BCrypt.hashpw("password", salt);
		
		logger.debug("==================USER DETAILS SERVICE HERE!");
		
		boolean adminExist = this.adminManager.isEmailExist(email);
		if(adminExist)
		{
			logger.info("EMAIL FOUND!");
			AdminModel myAdmin = this.adminManager.findByEmail(email);

			boolean enabled = true;
			boolean accountNonExpired = true;
	        boolean credentialsNonExpired = true;
	        boolean accountNonLocked = true;
	        
			User newUser = new User(myAdmin.getEmail(), myAdmin.getPassWord(), enabled, accountNonExpired, credentialsNonExpired, 
					accountNonLocked, this.getAuthorities( myAdmin.getGroupID()) );
			return newUser;	
		}
		else
		{
			throw new UsernameNotFoundException("Could not find email="+email);
		}
	}
}
