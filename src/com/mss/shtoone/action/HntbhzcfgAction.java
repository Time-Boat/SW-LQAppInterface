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
import com.mss.shtoone.domain.Hntbhzziduancfg;
import com.mss.shtoone.domain.HntbhzziduancfgView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;




@Controller
@Namespace("/system")

public class HntbhzcfgAction extends ActionSupport {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2986312960022224774L;

	@Autowired
	private SystemService sysService;

	private List<HntbhzziduancfgView> hntcfglist;
	
	private Hntbhzziduancfg hntcfgheader;
	
	private Hntbhzziduancfg hntcfg;
	
	private Hntbhzziduancfg hntisshowcfg;
	
	private Integer biaoduan;
	private Integer xiangmubu;
	
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	
	private Map<String, String> listmap;
	
	public Hntbhzziduancfg getHntisshowcfg() {
		return hntisshowcfg;
	}

	public void setHntisshowcfg(Hntbhzziduancfg hntisshowcfg) {
		this.hntisshowcfg = hntisshowcfg;
	}

	private String leixinbh;
	
	public String getLeixinbh() {
		return leixinbh;
	}

	public void setLeixinbh(String leixinbh) {
		this.leixinbh = leixinbh;
	}

	public Hntbhzziduancfg getHntcfg() {
		return hntcfg;
	}

	public void setHntcfg(Hntbhzziduancfg hntcfg) {
		this.hntcfg = hntcfg;
	}

	private int cfgId;
	private int defaultId;
	
	public int getCfgId() {
		return cfgId;
	}

	public void setCfgId(int cfgId) {
		this.cfgId = cfgId;
	}

	public Hntbhzziduancfg getHntcfgheader() {
		return hntcfgheader;
	}

	public void setHntcfgheader(Hntbhzziduancfg hntcfgheader) {
		this.hntcfgheader = hntcfgheader;
	}

	public List<HntbhzziduancfgView> getHntcfglist() {
		return hntcfglist;
	}

	public void setHntcfglist(List<HntbhzziduancfgView> hntcfglist) {
		this.hntcfglist = hntcfglist;
	}	
	
	@Action("hntbhzcfglist")
	public String hntbhzcfglist() {
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
		setHntcfglist(sysService.limithntcfglist(request, biaoduan, xiangmubu));		
		return SUCCESS;
	}
	
	@Action(value = "hntbhzcfgadd", results = @Result(name = "list", type = "redirect", location = "hntbhzcfglist?pid=4&"))
	public String hntbhzcfgadd() {
		sysService.hntcfgadd(hntcfg);
		if (StringUtil.Null2Blank(hntisshowcfg.getGprsbianhao()).length()==0) {
			hntisshowcfg.setGprsbianhao(hntcfg.getGprsbianhao());
		}
		sysService.hntcfgadd(hntisshowcfg);
		return "list";
	}
	
	@Action(value = "hntbhzcfginput")
	public String hntbhzcfginput() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		setHntcfgheader(sysService.gethntcfgHeader());
		listmap = new LinkedHashMap<String, String>();
		for (Banhezhanxinxi hntbhz : sysService.limitbhzlist(request, biaoduan, xiangmubu,"1")) {
			listmap.put(hntbhz.getGprsbianhao(), hntbhz.getBanhezhanminchen());
		}
		if (cfgId > 0) {
			setHntcfg(sysService.hntcfgfindById(cfgId));
			setHntisshowcfg(sysService.hntisshowcfgBybh(leixinbh));
		} else if (defaultId > 0) {
			setHntcfg(sysService.hntfieldnameBybh(leixinbh));
			setHntisshowcfg(sysService.hntisshowcfgBybh(leixinbh));
		} else {
			setHntcfg(sysService.getDefaultziduancfg());
			setHntisshowcfg(sysService.getDefaultziduanshow());			
		}
		return SUCCESS;
	}
	
	@Action(value = "hntbhzcfgdel", results = @Result(name = "list", type = "redirect", location = "hntbhzcfglist?pid=4&"))
	public String hntbhzcfgdel() {
		sysService.hntcfgdel(cfgId, leixinbh);
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
}
