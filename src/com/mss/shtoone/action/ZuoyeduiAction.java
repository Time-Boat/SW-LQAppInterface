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
import com.mss.shtoone.domain.Zuoyeduixinxi;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.service.ZuoyeduiService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Controller
@Namespace("/system")

public class ZuoyeduiAction extends ActionSupport{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1097109785581221023L;

	@Autowired
	private ZuoyeduiService zydService;
	
	@Autowired
	private SystemService sysService;
	
	private List list = new ArrayList();
	private Zuoyeduixinxi zydxx;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	
	private int zydid;


	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	@Action("zuoyeduiList")
	 public String zuoyeduiList(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		setList(zydService.limitGetzuoyedui(request));
		return SUCCESS; 
	 }
	
	@Action("zuoyeduiInput")
	 public String zuoyeduiInput(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = sysService.limitxmblist(request, null);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}		
		if(zydid>0){
			setZydxx(zydService.getBeanById(zydid));
		}
		return SUCCESS; 
	 }
	
	@Action(value = "zuoyeduiAdd", results = @Result(name = "zuoyeduiList", type = "redirect", location = "zuoyeduiList?pid=4&record=40&"))
	 public String xiangmubuAdd(){		
		zydService.saveOrUpdate(zydxx);
		return "zuoyeduiList"; 
	 }
	
	@Action(value = "zuoyeduiDel", results = @Result(name = "zuoyeduiList", type = "redirect", location = "zuoyeduiList?pid=4&record=40&"))
	 public String xiangmubuDel(){	
		zydService.del(zydid);
		return "zuoyeduiList"; 
	 }



	public Map<Integer, String> getBiaoduanlistmap() {
		return biaoduanlistmap;
	}

	public void setBiaoduanlistmap(Map<Integer, String> biaoduanlistmap) {
		this.biaoduanlistmap = biaoduanlistmap;
	}

	public Zuoyeduixinxi getZydxx() {
		return zydxx;
	}

	public void setZydxx(Zuoyeduixinxi zydxx) {
		this.zydxx = zydxx;
	}



	public int getZydid() {
		return zydid;
	}

	public void setZydid(int zydid) {
		this.zydid = zydid;
	}

	public Map<Integer, String> getXmblistmap() {
		return xmblistmap;
	}

	public void setXmblistmap(Map<Integer, String> xmblistmap) {
		this.xmblistmap = xmblistmap;
	}

}
