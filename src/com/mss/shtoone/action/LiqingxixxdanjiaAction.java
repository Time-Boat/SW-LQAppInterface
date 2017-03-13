package com.mss.shtoone.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Liqingxixxdanjia;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.service.LiqingxixxdanjiaService;
import com.mss.shtoone.service.SystemService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Controller
@Namespace("/system")

public class LiqingxixxdanjiaAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6761560279423544825L;

	@Autowired
	private LiqingxixxdanjiaService lqdjService;
	
	@Autowired
	private SystemService sysService;
	
	private List<Liqingxixxdanjia> list = new ArrayList<Liqingxixxdanjia>();
	private Liqingxixxdanjia lqdjxx;
	private String frommater;
	private Map<String, String> listmap;
	private int djid;

	public List<Liqingxixxdanjia> getList() {
		return list;
	}

	public void setList(List<Liqingxixxdanjia> list) {
		this.list = list;
	}

	@Action("lqdjList")
	 public String lqdjList(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		setList(lqdjService.getAll());
		if (null != request.getParameter("frommater")) {
			setFrommater(request.getParameter("frommater"));
		}
		return SUCCESS; 
	 }
	
	@Action("lqdjInput")
	 public String lqdjInput(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		listmap = new LinkedHashMap<String, String>();
		List<TopLiqingView> toplist = sysService.limitlqlist(request, null, null);
		for (TopLiqingView toplqView : toplist) {
			listmap.put(toplqView.getShebeibianhao(), toplqView
					.getBanhezhanminchen());
		}
		if(djid>0){
			setLqdjxx(lqdjService.getBeanById(djid));
		}
		return SUCCESS; 
	 }
	
	@Action(value = "lqdjAdd", results = @Result(name = "lqdjList", type = "redirect", location = "lqdjList?pid=2&record=18&"))
	 public String lqdjAdd(){		
		lqdjService.saveOrUpdate(lqdjxx);
		return "lqdjList"; 
	 }
	
	@Action(value = "lqdjDel", results = @Result(name = "lqdjList", type = "redirect", location = "lqdjList?pid=2&record=18&"))
	 public String lqdjDel(){	
		lqdjService.del(djid);
		return "lqdjList"; 
	 }
	
	@Action(value = "lqdjMoren", results = {@Result(name = "lqdjList", type = "redirect", location = "lqdjList?pid=2&record=18&"),
		@Result(name = "lqmaterial", type = "redirect", location = "/query/lqmaterial?pid=2&record=18&")})
	 public String lqdjMoren(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		lqdjService.moren(djid);
		if (null != request.getParameter("frommater")) {
		  return "lqmaterial";	
		} else {
		  return "lqdjList";
		}
	 }





	public Map<String, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
	}

	public Liqingxixxdanjia getLqdjxx() {
		return lqdjxx;
	}

	public void setLqdjxx(Liqingxixxdanjia lqdjxx) {
		this.lqdjxx = lqdjxx;
	}

	public int getDjid() {
		return djid;
	}

	public void setDjid(int djid) {
		this.djid = djid;
	}

	public String getFrommater() {
		return frommater;
	}

	public void setFrommater(String frommater) {
		this.frommater = frommater;
	}
}
