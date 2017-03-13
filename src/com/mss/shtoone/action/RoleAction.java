package com.mss.shtoone.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Resource;
import com.mss.shtoone.domain.Role;
import com.mss.shtoone.service.SysService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/system")
public class RoleAction extends ActionSupport{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8379733586074055490L;
	private List<Role> rolelist;
	private List<Resource> resourcelist;	
	

	private int roleId;
	private Role role;
	
	
	private Integer[] roleresource; 	
	

	@Autowired
	private SysService sysService;	

	
	
	@Action("rolelist")
	public String rolelist(){		
		setRolelist(sysService.getAllRole());	
		return SUCCESS;
	}
	
	@Action("role")
	public String role(){
		resourcelist = sysService.getAllResource();
		if (roleId > 0) {
			setRole(sysService.getRoleById(roleId));
			Set<Resource> resources = role.getResources();
			if (resources.size() > 0) {
				roleresource = new Integer[resources.size()];
				int i = 0;				
				for (Resource resource : resources) {
					roleresource[i] = resource.getId();
					i++;
				}
			}	
		}
		return SUCCESS;
	}
	
	@Action(value="roleadd", results = @Result(name = "list", type = "redirect", location = "rolelist?pid=3&record=23&"))
	public String roleadd(){
		Set<Resource> resources = new HashSet<Resource>();		
		if (null != roleresource && roleresource.length > 0) {
			for (int i = 0; i < roleresource.length; i++) {
				Resource rsl = sysService.getResourceById(roleresource[i]);
				resources.add(rsl);
			}
		} else {
			resources.add(sysService.getResourceById(1));
		}
		role.setResources(resources);
		sysService.saveOrUpdate(role);
		return "list";
	}
	
	@Action(value="roledel", results = @Result(name = "list", type = "redirect", location = "rolelist?pid=3&record=23&"))
	public String roledel(){			
		sysService.delrole(roleId);
		return "list";
	}
	
	

	
	public List<Role> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<Role> rolelist) {
		this.rolelist = rolelist;
	}

	public List<Resource> getResourcelist() {
		return resourcelist;
	}

	public void setResourcelist(List<Resource> resourcelist) {
		this.resourcelist = resourcelist;
	}

	

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	


	

	public Integer[] getRoleresource() {
		return roleresource;
	}

	public void setRoleresource(Integer[] roleresource) {
		this.roleresource = roleresource;
	}

}
