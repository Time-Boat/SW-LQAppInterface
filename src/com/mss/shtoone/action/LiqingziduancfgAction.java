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


import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;




@Controller
@Namespace("/system")

public class LiqingziduancfgAction extends ActionSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1322024835606321808L;

	@Autowired
	private SystemService sysService;

	private List<LiqingziduancfgView> liqingcfglist;
	
	private Liqingziduancfg liqingcfgheader;
	
	private Liqingziduancfg liqingcfg;
	
	private Liqingziduancfg liqingisshowcfg;
	
	private Integer biaoduan;
	private Integer xiangmubu;
	
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	
	private Map<String, String> listmap;
	


	private String leixinbh;
	
	public String getLeixinbh() {
		return leixinbh;
	}

	public void setLeixinbh(String leixinbh) {
		this.leixinbh = leixinbh;
	}



	private int cfgId;
	private int defaultId;
	
	public int getCfgId() {
		return cfgId;
	}

	public void setCfgId(int cfgId) {
		this.cfgId = cfgId;
	}

	
	@Action("liqingcfglist")
	public String liqingcfglist() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
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
		setLiqingcfglist(sysService.limitliqingcfglist(request, biaoduan, xiangmubu));		
		return SUCCESS;
	}
	
	@Action(value = "liqingcfgadd", results = @Result(name = "list", type = "redirect", location = "liqingcfglist?pid=4&record=31&"))
	public String liqingcfgadd() {
		sysService.liqingcfgadd(liqingcfg);
		if (StringUtil.Null2Blank(liqingisshowcfg.getGprsbianhao()).length()==0) {
			liqingisshowcfg.setGprsbianhao(liqingcfg.getGprsbianhao());
		}
		sysService.liqingcfgadd(liqingisshowcfg);
		return "list";
	}
	
	@Action(value = "liqingcfginput")
	public String liqingcfginput() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		setLiqingcfgheader(sysService.getliqingcfgHeader());
		listmap = new LinkedHashMap<String, String>();
		for (Banhezhanxinxi hntbhz : sysService.limitbhzlist(request, biaoduan, xiangmubu,"2")) {
			listmap.put(hntbhz.getGprsbianhao(), hntbhz.getBanhezhanminchen());
		}
		if (cfgId > 0) {
			setLiqingcfg(sysService.liqingcfgfindById(cfgId));
			setLiqingisshowcfg(sysService.liqingisshowcfgBybh(leixinbh));
		} else if (defaultId > 0) {
			setLiqingcfg(sysService.liqingfieldnameBybh(leixinbh));
			setLiqingisshowcfg(sysService.liqingisshowcfgBybh(leixinbh));
		} else {
			setLiqingcfg(sysService.getDefaultLiqingziduancfg());
			setLiqingisshowcfg(sysService.getDefaultLiqingziduanshow());			
		}
		return SUCCESS;
	}
	
	@Action(value = "liqingcfgdel", results = @Result(name = "list", type = "redirect", location = "liqingcfglist?pid=4&record=31&"))
	public String liqingcfgdel() {
		sysService.liqingcfgdel(cfgId, leixinbh);
		return "list";
	}

	public Map<String, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
	}

	public int getDefaultId() {
		return defaultId;
	}

	public void setDefaultId(int defaultId) {
		this.defaultId = defaultId;
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

	public List<LiqingziduancfgView> getLiqingcfglist() {
		return liqingcfglist;
	}

	public void setLiqingcfglist(List<LiqingziduancfgView> liqingcfglist) {
		this.liqingcfglist = liqingcfglist;
	}

	public Liqingziduancfg getLiqingcfgheader() {
		return liqingcfgheader;
	}

	public void setLiqingcfgheader(Liqingziduancfg liqingcfgheader) {
		this.liqingcfgheader = liqingcfgheader;
	}

	public Liqingziduancfg getLiqingcfg() {
		return liqingcfg;
	}

	public void setLiqingcfg(Liqingziduancfg liqingcfg) {
		this.liqingcfg = liqingcfg;
	}

	public Liqingziduancfg getLiqingisshowcfg() {
		return liqingisshowcfg;
	}

	public void setLiqingisshowcfg(Liqingziduancfg liqingisshowcfg) {
		this.liqingisshowcfg = liqingisshowcfg;
	}

	
}
