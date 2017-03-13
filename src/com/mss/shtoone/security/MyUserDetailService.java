package com.mss.shtoone.security;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.mss.shtoone.domain.Muser;
import com.mss.shtoone.domain.UserInfo;
import com.mss.shtoone.domain.Userlog;
import com.mss.shtoone.persistence.TophunnintuViewDAO;
import com.mss.shtoone.persistence.UserDAO;
import com.mss.shtoone.persistence.UserlogDAO;


public class MyUserDetailService implements UserDetailsService {
	static Log logger = LogFactory.getLog(MyUserDetailService.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private TophunnintuViewDAO topDAO;	
	
	@Autowired
	private UserlogDAO userlogDAO;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		Collection<GrantedAuthority> auths=new ArrayList<GrantedAuthority>();		
		UserInfo user = null;
		if (logger.isDebugEnabled()) {
			logger.debug(username+"登录系统");
		}
		Muser muser = userDAO.findByName(username);	
		
        if(null != muser){
            auths=new ArrayList<GrantedAuthority>();            
            List list = topDAO.findBySql("select a.name from role a inner join user_role b on a.id=b.role_id where b.user_id="+muser.getId());
            for (Object rolename : list) {
            	GrantedAuthorityImpl auth1=new GrantedAuthorityImpl((String)rolename);
            	auths.add(auth1);
			}
           
/*           Set<Role> roleList = muser.getRoles();
            for (Role role : roleList) {
            	GrantedAuthorityImpl auth1=new GrantedAuthorityImpl(role.getName());
                auths.add(auth1);
			}  */  
           
            user = new UserInfo(username, muser.getPwd(), 
            		(null != muser.getDisabled() && muser.getDisabled().equalsIgnoreCase("1")), 
            		true, true, true, auths);
            user.setUserType(muser.getUsertype());
            user.setUserid(muser.getId());
            user.setBiaoshiid(muser.getBiaoshiid());
            user.setFullname(muser.getFullname());
            user.setFirstlogin(muser.getFirstlogin());
            user.setRemark(muser.getRemark());
            
            //登录日志
            Userlog userlog = new Userlog();
			userlog.setLogname(user.getUsername());
			userlog.setLoguserid(user.getUserid());
			userlog.setLogoperate("登录系统");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			userlog.setLogdate(sdf.format(System.currentTimeMillis()));
			userlogDAO.save(userlog);
        }  		
        return user;
	}

}



