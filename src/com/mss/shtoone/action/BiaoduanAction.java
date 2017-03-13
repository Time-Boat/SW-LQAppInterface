package com.mss.shtoone.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.service.BiaoduanService;
import com.opensymphony.xwork2.ActionSupport;



@Controller
@Namespace("/system")

public class BiaoduanAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6870786428960341318L;
	@Autowired
	private BiaoduanService bdService;
	private List<Biaoduanxinxi> list = new ArrayList<Biaoduanxinxi>();
	private Biaoduanxinxi bdxx;
	
	private int bdid;
	public int getBdid() {
		return bdid;
	}

	public void setBdid(int bdid) {
		this.bdid = bdid;
	}

	public List<Biaoduanxinxi> getList() {
		return list;
	}

	public void setList(List<Biaoduanxinxi> list) {
		this.list = list;
	}

	@Action("biaoduanList")
	 public String biaoduanList(){		
		setList(bdService.getAll());
		return SUCCESS; 
	 }
	
	@Action("biaoduanInput")
	 public String biaoduanInput(){	
		if(bdid>0){
			setBdxx(bdService.getBeanById(bdid));
		}
		return SUCCESS; 
	 }
	
	@Action(value = "biaoduanAdd", results = @Result(name = "biaoduanList", type = "redirect", location = "biaoduanList?pid=4&record=38&"))
	 public String biaoduanAdd(){		
		bdService.saveOrUpdate(bdxx);
		return "biaoduanList"; 
	 }
	
	@Action(value = "biaoduanDel", results = @Result(name = "biaoduanList", type = "redirect", location = "biaoduanList?pid=4&record=38&"))
	 public String biaoduanDel(){	
		bdService.del(bdid);
		return "biaoduanList"; 
	 }

	public Biaoduanxinxi getBdxx() {
		return bdxx;
	}

	public void setBdxx(Biaoduanxinxi bdxx) {
		this.bdxx = bdxx;
	}
}
