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

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.Smsrecharge;
import com.mss.shtoone.domain.Smsrecord;
import com.mss.shtoone.service.SmsinfoService;
import com.mss.shtoone.service.SystemService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;



@Controller
@Namespace("/system")

public class SmsrecordAction extends ActionSupport{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4393315469090426393L;

	@Autowired
	private SmsinfoService smsService;
	
	@Autowired
	private SystemService systemService;
	
	private Integer biaoduan;
	private Map<Integer, String> biaoduanlistmap;
	
	private List<Smsrecord> recordlist;
	private List<Smsrecharge> rechargelist;
	
	private Smsrecord smsrecord;
	private Smsrecharge smsrecharge;
	
	private Integer recordId;
	private Integer rechargeId;

	@Action("smsrecordlist")
	 public String smsrecordlist(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		setRecordlist(smsService.limitrecordList(request));		
		setRechargelist(smsService.limitrechargeList(request));		
		return SUCCESS; 
	 }
	
	@Action("smsrecord")
	 public String smsrecord(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = systemService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}		
		if (recordId > 0) {
			setSmsrecord(smsService.limitSmsrecordByid(request, recordId));
		}
		return SUCCESS; 
	 }
	
	@Action("editsmsalarm")
	 public String editsmsalarm(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);			
		if (recordId > 0) {
			setSmsrecord(smsService.limitSmsrecordByid(request, recordId));
		}
		return SUCCESS; 
	 }
	
	@Action(value="completeeditsmsalarm", results = @Result(name = "list", type = "redirect", location = "smsrecordlist?pid=4&record=37&"))
	 public String completeeditsmsalarm(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		Smsrecord record = smsService.limitSmsrecordByid(request, smsrecord.getId());
		if (null != record) {
			record.setIsalarm(smsrecord.getIsalarm());
			record.setAlarmcount(smsrecord.getAlarmcount());
			record.setAlarmnumber(smsrecord.getAlarmnumber());
			if (null != record.getSmscount() && null != record.getIsalarm()
					&& record.getIsalarm() == 1 && 
					(null == record.getAlarmcount() || record.getSmscount()>record.getAlarmcount())) {
				record.setCompletealarm(0);
			}
			smsService.saveOrUpdateSmsrecord(record);
		}
		return "list";
	 }
	
	@Action(value="addsmsrecord", results = @Result(name = "list", type = "redirect", location = "smsrecordlist?pid=4&record=37&"))
	 public String addsmsrecord(){
		if (null != smsrecord.getSmscount() && null != smsrecord.getIsalarm()
				&& smsrecord.getIsalarm() == 1 && 
				(null == smsrecord.getAlarmcount() || smsrecord.getSmscount()>smsrecord.getAlarmcount())) {
			smsrecord.setCompletealarm(0);
		}
		
		smsService.saveOrUpdateSmsrecord(smsrecord);
		return "list";
	 }
	
	@Action(value="delsmsrecord", results = @Result(name = "list", type = "redirect", location = "smsrecordlist?pid=4&record=37&"))
	 public String delsmsrecord(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);		
		smsService.delSmsrecord(request, recordId);
		return "list";
	 }
	
	
	@Action("smsrecharge")
	 public String smsrecharge(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = systemService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}		
		if (rechargeId > 0) {
			setSmsrecharge(smsService.limitSmsrechargeByid(request, rechargeId));
		}
		return SUCCESS; 
	 }
	
	@Action("dorecharge")
	 public String dorecharge(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		if (rechargeId > 0) {
			setSmsrecharge(smsService.limitSmsrechargeByid(request, rechargeId));
		}
		return SUCCESS; 
	 }
	
	@Action(value="delsmsrecharge", results = @Result(name = "list", type = "redirect", location = "smsrecordlist?pid=4&record=37&"))
	 public String delsmsrecharge(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		smsService.delSmsrecharge(request, rechargeId);
		return "list";
	 }
	
	@Action(value="addsmsrecharge", results = @Result(name = "list", type = "redirect", location = "smsrecordlist?pid=4&record=37&"))
	 public String addsmsrecharge(){
		smsService.addsmsrecharge(smsrecharge);
		return "list";
	 }
	
	@Action(value="addandsubmitsmsrecharge", results = @Result(name = "list", type = "redirect", location = "smsrecordlist?pid=4&record=37&"))
	 public String addandsubmitsmsrecharge(){
		smsService.addandsubmitsmsrecharge(smsrecharge);
		return "list";
	 }
	
	
	@Action(value="submitrecharge", results = @Result(name = "list", type = "redirect", location = "smsrecordlist?pid=4&record=37&"))
	 public String submitrecharge(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
					.get(ServletActionContext.HTTP_REQUEST);
		smsService.submitrecharge(request, rechargeId);
		return "list";
	 }
	
	@Action(value="returnrecharge", results = @Result(name = "list", type = "redirect", location = "smsrecordlist?pid=4&record=37&"))
	 public String returnrecharge(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
					.get(ServletActionContext.HTTP_REQUEST);
		smsService.returnrecharge(request, rechargeId);
		return "list";
	 }
	
	@Action(value="completerecharge", results = @Result(name = "list", type = "redirect", location = "smsrecordlist?pid=4&record=37&"))
	 public String completerecharge(){
		smsService.completerecharge(smsrecharge);
		return "list";
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

	public List<Smsrecord> getRecordlist() {
		return recordlist;
	}

	public void setRecordlist(List<Smsrecord> recordlist) {
		this.recordlist = recordlist;
	}

	public List<Smsrecharge> getRechargelist() {
		return rechargelist;
	}

	public void setRechargelist(List<Smsrecharge> rechargelist) {
		this.rechargelist = rechargelist;
	}

	public Smsrecord getSmsrecord() {
		return smsrecord;
	}

	public void setSmsrecord(Smsrecord smsrecord) {
		this.smsrecord = smsrecord;
	}

	public Smsrecharge getSmsrecharge() {
		return smsrecharge;
	}

	public void setSmsrecharge(Smsrecharge smsrecharge) {
		this.smsrecharge = smsrecharge;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getRechargeId() {
		return rechargeId;
	}

	public void setRechargeId(Integer rechargeId) {
		this.rechargeId = rechargeId;
	}

}
