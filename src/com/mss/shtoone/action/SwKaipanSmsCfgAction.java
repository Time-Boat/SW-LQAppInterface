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
import com.mss.shtoone.domain.HandSet;
import com.mss.shtoone.domain.Shuiwenziduancfg;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.TopRealMaxLiqingView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/system")

public class SwKaipanSmsCfgAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3153611999153536225L;

	@Autowired
	private SystemService sysService;

	private List<TopRealMaxShuiwenxixxView> swsmscfglist;
	
	private Shuiwenziduancfg smscfgheader;
	
	private ShuiwenziduancfgView smscfgfieldname;
	
	private Shuiwenziduancfg swissms;	
	private Shuiwenziduancfg swsmslow;	
	private Shuiwenziduancfg swsmshigh;	
	private Shuiwenziduancfg swsmsnumber;
	
	private Map<String, String> listmap;
	private Map<Integer, String> handsetlistmap;

	private String leixinbh;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Integer biaoduan;
	private Integer xiangmubu;	
	private Integer cfgId;
	public Map<String, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
	}

	public Map<Integer, String> getHandsetlistmap() {
		return handsetlistmap;
	}

	public void setHandsetlistmap(Map<Integer, String> handsetlistmap) {
		this.handsetlistmap = handsetlistmap;
	}

	public Map<Integer, String> getBiaoduanlistmap() {
		return biaoduanlistmap;
	}

	public void setBiaoduanlistmap(Map<Integer, String> biaoduanlistmap) {
		this.biaoduanlistmap = biaoduanlistmap;
	}

	public Map<Integer, String> getXmblistmap() {
		return xmblistmap;
	}

	public void setXmblistmap(Map<Integer, String> xmblistmap) {
		this.xmblistmap = xmblistmap;
	}

	public Integer getBiaoduan() {
		return biaoduan;
	}

	public void setBiaoduan(Integer biaoduan) {
		this.biaoduan = biaoduan;
	}

	public Integer getXiangmubu() {
		return xiangmubu;
	}

	public void setXiangmubu(Integer xiangmubu) {
		this.xiangmubu = xiangmubu;
	}

	public List<TopRealMaxShuiwenxixxView> getSwsmscfglist() {
		return swsmscfglist;
	}

	public void setSwsmscfglist(List<TopRealMaxShuiwenxixxView> swsmscfglist) {
		this.swsmscfglist = swsmscfglist;
	}

	public Shuiwenziduancfg getSmscfgheader() {
		return smscfgheader;
	}

	public void setSmscfgheader(Shuiwenziduancfg smscfgheader) {
		this.smscfgheader = smscfgheader;
	}

	public ShuiwenziduancfgView getSmscfgfieldname() {
		return smscfgfieldname;
	}

	public void setSmscfgfieldname(ShuiwenziduancfgView smscfgfieldname) {
		this.smscfgfieldname = smscfgfieldname;
	}

	public Shuiwenziduancfg getSwissms() {
		return swissms;
	}

	public void setSwissms(Shuiwenziduancfg swissms) {
		this.swissms = swissms;
	}

	public Shuiwenziduancfg getSwsmslow() {
		return swsmslow;
	}

	public void setSwsmslow(Shuiwenziduancfg swsmslow) {
		this.swsmslow = swsmslow;
	}

	public Shuiwenziduancfg getSwsmshigh() {
		return swsmshigh;
	}

	public void setSwsmshigh(Shuiwenziduancfg swsmshigh) {
		this.swsmshigh = swsmshigh;
	}

	public Shuiwenziduancfg getSwsmsnumber() {
		return swsmsnumber;
	}

	public void setSwsmsnumber(Shuiwenziduancfg swsmsnumber) {
		this.swsmsnumber = swsmsnumber;
	}

	public String getLeixinbh() {
		return leixinbh;
	}

	public void setLeixinbh(String leixinbh) {
		this.leixinbh = leixinbh;
	}
		
	public Integer getCfgId() {
		return cfgId;
	}

	public void setCfgId(Integer cfgId) {
		this.cfgId = cfgId;
	}


	@Action("kaipanswsmscfglist")
	public String swsmscfglist() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = sysService.limitxmblist(request, biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
		setSwsmscfglist(sysService.limitSwsmscfglist(request, biaoduan, xiangmubu));		
		return SUCCESS;
	}
	
	@Action(value = "kaipanswsmscfgadd", results = @Result(name = "list", type = "redirect", location = "kaipanswsmscfglist?pid=4&record=20&"))
	public String kaipanswsmscfgadd() {
		sysService.swcfgadd(swissms);	
		if (StringUtil.Null2Blank(swsmslow.getGprsbianhao()).length()==0) {
			swsmslow.setGprsbianhao(swissms.getGprsbianhao());
		}
		sysService.swcfgadd(swsmslow);
		if (StringUtil.Null2Blank(swsmshigh.getGprsbianhao()).length()==0) {
			swsmshigh.setGprsbianhao(swissms.getGprsbianhao());
		}
		sysService.swcfgadd(swsmshigh);
		if (StringUtil.Null2Blank(swsmsnumber.getGprsbianhao()).length()==0) {
			swsmsnumber.setGprsbianhao(swissms.getGprsbianhao());
		}
		sysService.swcfgadd(swsmsnumber);
		return "list";
	}
	
	@Action(value = "kaipanswsmscfginput")
	public String kaipanswsmscfginput() {
		setSmscfgheader(sysService.getshuiwencfgHeader());
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			biaoduan = Integer.valueOf(request.getParameter("biaoduan"));
		}
		
		listmap = new LinkedHashMap<String, String>();
		for (TopRealMaxLiqingView lqcfgView : sysService.limitlqsmscfglist(request, biaoduan, xiangmubu)) {
			listmap.put(lqcfgView.getShebeibianhao(), lqcfgView.getBanhezhanminchen());
		}
		
		handsetlistmap = new LinkedHashMap<Integer, String>();
		for (HandSet handset : sysService.handsetlist(biaoduan)) {
			handsetlistmap.put(handset.getId(), handset.getName());
		}

		if (cfgId > 0) {
			setSmscfgfieldname(sysService.swfieldnamefindBybh(leixinbh));
			setSwissms(sysService.swkaipanissmsfindBybh(leixinbh));
			setSwsmslow(sysService.swkaipansmslowfindBybh(leixinbh));
			setSwsmshigh(sysService.swkaipansmshighfindBybh(leixinbh));
			setSwsmsnumber(sysService.swkaipansmsnumberfindBybh(leixinbh));
		}
		return SUCCESS;
	}
	
	@Action(value = "kaipanswsmscfgdel", results = @Result(name = "list", type = "redirect", location = "kaipanswsmscfglist?pid=4&record=20&"))
	public String kaipanswsmscfgdel() {
		sysService.swsmscfgdel(leixinbh);
		return "list";
	}
}
