package com.mss.shtoone.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;


import com.mss.shtoone.domain.Muser;
import com.mss.shtoone.domain.UserInfo;
import com.mss.shtoone.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/main")

public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 5950508911877846142L;

	@Autowired
	private UserService userservice;
	
	private String pwd;
	private String newpwd;
	private String username;
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	
	@Action(value = "login", results = {@Result(name = "list", type = "redirect", location = "swlist?pid=0&record=22&"),
			@Result(name = "firstlogin", type = "redirect", location = "firstlogin?pid=3&record=22&")})
	public String login() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		SecurityContext sc = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication auth = null;
		UserInfo pcp = null;
		if (null != sc) {
			auth = sc.getAuthentication();
			if (null != auth) {
				pcp = (UserInfo) auth.getPrincipal();
				if (null != pcp) {
					if (null != pcp.getFirstlogin() && pcp.getFirstlogin() == 1) {		
						return "firstlogin";
					}
				}
			}
		}	
		return "list";
	}
	
	@Action(value = "firstlogin")
	public String firstlogin() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		SecurityContext sc = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication auth = null;
		UserInfo pcp = null;
		if (null != sc) {
			auth = sc.getAuthentication();
			if (null != auth) {
				pcp = (UserInfo) auth.getPrincipal();
				if (null != pcp) {
					setPwd(pcp.getPassword());
					setUsername(pcp.getUsername());
				}
			}
		}	
		return SUCCESS;
	}
	
	@Action(value = "loginadd", results = @Result(name = "list", type = "redirect", location = "list?pid=0&record=0&"))
	public String loginadd() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		SecurityContext sc = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication auth = null;
		UserInfo pcp = null;
		if (null != sc) {
			auth = sc.getAuthentication();
			if (null != auth) {
				pcp = (UserInfo) auth.getPrincipal();
				if (null != pcp) {
					Muser user = userservice.getUserByName(pcp.getUsername());
					if (null != user && null != pwd && null != user.getPwd() 
							&& pwd.equalsIgnoreCase(user.getPwd())) {
						user.setPwd(newpwd);
						user.setFirstlogin(0);
						userservice.saveOrUpdate(user);
					}
				}
			}
		}	
		return "list";
	}	
}
