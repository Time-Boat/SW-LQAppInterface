package com.mss.shtoone.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.mss.shtoone.domain.DiuqishujuPageMode;
import com.mss.shtoone.service.DiuqishujuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Controller
@Namespace("/system")

public class DiuqishujuAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7334974649736593717L;
	
	@Autowired
	private DiuqishujuService dqService;
	private DiuqishujuPageMode dqsj;
	private int maxPageItems;


	@Action("diuqishujulist")
	 public String diuqishujuList(){
		int offset = 1;
		int pagesize = 15;
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		if (null != request.getParameter("pageNo")) {
			offset = Integer.parseInt(request.getParameter("pageNo"));
		}
		if (null != request.getParameter("maxPageItems")) {
			pagesize = Integer.parseInt(request.getParameter("maxPageItems"));
		}
		setMaxPageItems(pagesize);
		setDqsj(dqService.getAll(offset, pagesize));
		return SUCCESS; 
	 }

	public int getMaxPageItems() {
		return maxPageItems;
	}

	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
	}

	public DiuqishujuPageMode getDqsj() {
		return dqsj;
	}

	public void setDqsj(DiuqishujuPageMode dqsj) {
		this.dqsj = dqsj;
	}
	

}
