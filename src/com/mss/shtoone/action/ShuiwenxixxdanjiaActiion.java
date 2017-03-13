package com.mss.shtoone.action;

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

import com.mss.shtoone.domain.Shuiwenxixxdanjia;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class ShuiwenxixxdanjiaActiion extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private SystemService sysService;

	private List<Shuiwenxixxdanjia> swdjlist;
	private Integer djid; 
	private Map<String,String> listmap;
	private Shuiwenxixxdanjia swdanjia;
	private String frommater;
	public Shuiwenxixxdanjia getSwdanjia() {
		return swdanjia;
	}
	public void setSwdanjia(Shuiwenxixxdanjia swdanjia) {
		this.swdanjia = swdanjia;
	}
	public Integer getDjid() {
		return djid;
	}
	public void setDjid(Integer djid) {
		this.djid = djid;
	}
	public Map<String, String> getListmap() {
		return listmap;
	}
	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
	}
	public List<Shuiwenxixxdanjia> getSwdjlist() {
		return swdjlist;
	}
	public void setSwdjlist(List<Shuiwenxixxdanjia> swdjlist) {
		this.swdjlist = swdjlist;
	}
	public String getFrommater() {
		return frommater;
	}
	public void setFrommater(String frommater) {
		this.frommater = frommater;
	}
	
	
	@Action("swdjList")
	public String swdjList(){
		this.setSwdjlist(queryService.getDanjiaAll());		
		return SUCCESS;
	}
	
	@Action("swdjInput")
	 public String swdjInput(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		listmap = new LinkedHashMap<String, String>();
		List<TopRealMaxShuiwenxixxView> toplist = sysService.limitswlist(request,null,null);
		for (TopRealMaxShuiwenxixxView topsw : toplist) {
			listmap.put(topsw.getShebeibianhao(), topsw.getBanhezhanminchen());
		}
		if(djid>0){
			setSwdanjia(queryService.getBeanById(djid));
		}
		return SUCCESS; 
	 }
	
	@Action(value = "swdjAdd", results = @Result(name = "swdjList", type = "redirect", location = "swdjList?pid=7&record=33&"))
	 public String swdjAdd(){	
		if(swdanjia!=null){
			queryService.saveDanjia(swdanjia);
		}
		return "swdjList"; 
	 }
	
	@Action(value = "swdjDel", results = @Result(name = "swdjList", type = "redirect", location = "swdjList?pid=7&record=33&"))
	 public String swdjDel(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("djid")).length()>0){
			this.setDjid(Integer.parseInt(request.getParameter("djid")));
		}
		if(djid>0){
			queryService.delswDanjia(djid);
		}
		return "swdjList"; 
	 }
	
	@Action(value = "swdjMoren", results = {@Result(name = "swdjList", type = "redirect", location = "swdjList?pid=7&record=33&"),
											@Result(name = "swmaterial", type = "redirect", location = "swmaterial?pid=7&record=33&")})
	public String swdjMoren(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("djid")).length()>0){
			this.setDjid(Integer.parseInt(request.getParameter("djid")));
		}
		if(djid>0){
			queryService.moren(djid);
		}
		if (null != request.getParameter("frommater")) {
			return "swmaterial";	
		} else {
			return "swdjList";
		}
	}
	
}
