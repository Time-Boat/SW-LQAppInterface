package com.mss.shtoone.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserInfo extends User{	
	private static final long serialVersionUID = 3611165254060937378L;
	
	private Integer userType;
	
	private Integer userid;
	
	private Integer biaoshiid; 
	
	private String fullname;
	
	private Integer firstlogin; 
	
	private String remark;
	
	public UserInfo(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getBiaoshiid() {
		return biaoshiid;
	}

	public void setBiaoshiid(Integer biaoshiid) {
		this.biaoshiid = biaoshiid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Integer getFirstlogin() {
		return firstlogin;
	}

	public void setFirstlogin(Integer firstlogin) {
		this.firstlogin = firstlogin;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}



	

}



