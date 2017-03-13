package com.mss.shtoone.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Resource;
import com.mss.shtoone.service.SysService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/system")
public class ResourceAction extends ActionSupport{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8379733586074055490L;
	private List<Resource> resourcelist;		

	
	private int resourceId;
	private Resource resource;
	
	

	@Autowired
	private SysService sysService;	

	
		
	@Action("resourcelist")
	public String resourcelist(){		
		setResourcelist(sysService.getAllResource());	
		return SUCCESS;
	}
	
	@Action("resource")
	public String resource(){			
		if (resourceId > 0) {
			setResource(sysService.getResourceById(resourceId));
		}
		return SUCCESS;
	}
	
	@Action(value="resourceadd", results = @Result(name = "list", type = "redirect", location = "resourcelist?pid=3&record=24&"))
	public String resourceadd(){
		sysService.saveOrUpdate(resource);
		return "list";
	}
	
	@Action(value="resourcedel", results = @Result(name = "list", type = "redirect", location = "resourcelist?pid=3&record=24&"))
	public String resourcedel(){			
		sysService.delresource(resourceId);
		return "list";
	}
	

	
	public List<Resource> getResourcelist() {
		return resourcelist;
	}

	public void setResourcelist(List<Resource> resourcelist) {
		this.resourcelist = resourcelist;
	}	


	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
	

}
