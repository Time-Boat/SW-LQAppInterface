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

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.service.XiangmubuService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Controller
@Namespace("/system")

public class XiangmubuAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1395324963890653708L;
	@Autowired
	private XiangmubuService xmbService;
	
	@Autowired
	private SystemService sysService;
	
	private List list = new ArrayList();
	private Xiangmubuxinxi xmbxx;
	private Map<Integer, String> biaoduanlistmap;
	
	private int xmbid;
	public int getXmbid() {
		return xmbid;
	}

	public void setXmbid(int xmbid) {
		this.xmbid = xmbid;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	@Action("xiangmubuList")
	 public String xiangmubuList(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		setList(xmbService.limitGetxmb(request));
		return SUCCESS; 
	 }
	
	@Action("xiangmubuInput")
	 public String xiangmubuInput(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		if(xmbid>0){
			setXmbxx(xmbService.getBeanById(xmbid));
		}
		return SUCCESS; 
	 }
	
	@Action(value = "xiangmubuAdd", results = @Result(name = "xiangmubuList", type = "redirect", location = "xiangmubuList?pid=4&record=39&"))
	 public String xiangmubuAdd(){		
		xmbService.saveOrUpdate(xmbxx);
		return "xiangmubuList"; 
	 }
	
	@Action(value = "xiangmubuDel", results = @Result(name = "xiangmubuList", type = "redirect", location = "xiangmubuList?pid=4&record=39&"))
	 public String xiangmubuDel(){	
		xmbService.del(xmbid);
		return "xiangmubuList"; 
	 }

	public Xiangmubuxinxi getXmbxx() {
		return xmbxx;
	}

	public void setXmbxx(Xiangmubuxinxi xmbxx) {
		this.xmbxx = xmbxx;
	}

	public Map<Integer, String> getBiaoduanlistmap() {
		return biaoduanlistmap;
	}

	public void setBiaoduanlistmap(Map<Integer, String> biaoduanlistmap) {
		this.biaoduanlistmap = biaoduanlistmap;
	}

}
