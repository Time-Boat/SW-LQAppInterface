package com.mss.shtoone.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

@Entity(name="muser")
@Proxy(lazy = false)
public class Muser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1609003434648007435L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String name;	
	private String fullname;
	private String pwd;
	private String remark; 
	private Integer usertype; 
	private Integer biaoshiid; 
	private Integer firstlogin; 

	
	private String disabled;  
	private String ismanager;  



	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)  
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))  
    @Fetch(FetchMode.SELECT)
    private Set<Role> roles; 
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}



	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getBiaoshiid() {
		return biaoshiid;
	}

	public void setBiaoshiid(Integer biaoshiid) {
		this.biaoshiid = biaoshiid;
	}

	public Integer getFirstlogin() {
		return firstlogin;
	}

	public void setFirstlogin(Integer firstlogin) {
		this.firstlogin = firstlogin;
	}

	public String getIsmanager() {
		return ismanager;
	}

	public void setIsmanager(String ismanager) {
		this.ismanager = ismanager;
	}

}
