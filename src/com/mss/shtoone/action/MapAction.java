package com.mss.shtoone.action;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/map")

public class MapAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -653458215407715046L;

	@Action(value = "map")
	public String map() {		
		return SUCCESS;
	}
	
	@Action(value = "position")
	public String position() {		
		return SUCCESS;
	}
	
	@Action(value = "videomonitor")
	public String videomonitor() {		
		return SUCCESS;
	}
	
	
}
