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
import com.mss.shtoone.domain.HandSet;
import com.mss.shtoone.service.HandSetService;
import com.mss.shtoone.service.SystemService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Controller
@Namespace("/system")

public class HandSetAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5641731254777023821L;

	@Autowired
	private HandSetService handSetService;
	
	@Autowired
	private SystemService sysService;
	
	private Integer biaoduan;
	private String ownername;
	private String phone;
	private Map<Integer, String> biaoduanlistmap;
	private List<HandSet> list = new ArrayList<HandSet>();
	private HandSet handset;
	private int hsid;
	public HandSet getHandset() {
		return handset;
	}

	public String getOwnername() {
		return ownername;
	}


	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getHsid() {
		return hsid;
	}


	public void setHsid(int hsid) {
		this.hsid = hsid;
	}


	public void setHandset(HandSet handset) {
		this.handset = handset;
	}


	public List<HandSet> getList() {
		return list;
	}


	public void setList(List<HandSet> list) {
		this.list = list;
	}

	public Integer getBiaoduan() {
		return biaoduan;
	}


	public void setBiaoduan(Integer biaoduan) {
		this.biaoduan = biaoduan;
	}


	public Map<Integer, String> getBiaoduanlistmap() {
		return biaoduanlistmap;
	}


	public void setBiaoduanlistmap(Map<Integer, String> biaoduanlistmap) {
		this.biaoduanlistmap = biaoduanlistmap;
	}

	@Action("handSetList")
	 public String handSetList(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		setList(handSetService.limitlist(request, biaoduan,ownername,phone));
		return SUCCESS; 
	 }
	
	@Action("handSetInput")
	 public String handSetInput(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		if(hsid>0){
			setHandset(handSetService.getBeanById(hsid));
		}
		return SUCCESS; 
	 }
	
	@Action(value = "handSetAdd", results = @Result(name = "handSetList", type = "redirect", location = "handSetList?pid=4&record=32&"))
	 public String handSetAdd(){		
		handSetService.saveOrUpdate(handset);
		return "handSetList"; 
	 }
	
	@Action(value = "handSetDel", results = @Result(name = "handSetList", type = "redirect", location = "handSetList?pid=4&record=32&"))
	 public String handSetDel(){	
		handSetService.del(hsid);
		return "handSetList"; 
	 }
}
