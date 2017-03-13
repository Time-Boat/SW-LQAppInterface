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
import com.mss.shtoone.domain.Shuiwenziduancfg;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/system")

public class ShuiwenziduancfgAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3678130970182503061L;
	
	@Autowired
	private SystemService sysService;
	
	private List<ShuiwenziduancfgView> shuiwencfglist;
	
	private Shuiwenziduancfg shuiwencfgheader;
	
	private Shuiwenziduancfg shuiwencfg;
	
	private Shuiwenziduancfg shuiwenisshowcfg;
	private Shuiwenziduancfg shuiwenzstisshowcfg;
	private Integer biaoduan;
	private Integer xiangmubu;
	
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	
	private Map<String, String> listmap;
	


	private String leixinbh;
	private int cfgId;
	private int defaultId;
	
	public Shuiwenziduancfg getShuiwenzstisshowcfg() {
		return shuiwenzstisshowcfg;
	}
	public void setShuiwenzstisshowcfg(Shuiwenziduancfg shuiwenzstisshowcfg) {
		this.shuiwenzstisshowcfg = shuiwenzstisshowcfg;
	}
	public List<ShuiwenziduancfgView> getShuiwencfglist() {
		return shuiwencfglist;
	}
	public void setShuiwencfglist(List<ShuiwenziduancfgView> shuiwencfglist) {
		this.shuiwencfglist = shuiwencfglist;
	}
	public Shuiwenziduancfg getShuiwencfgheader() {
		return shuiwencfgheader;
	}
	public void setShuiwencfgheader(Shuiwenziduancfg shuiwencfgheader) {
		this.shuiwencfgheader = shuiwencfgheader;
	}
	public Shuiwenziduancfg getShuiwencfg() {
		return shuiwencfg;
	}
	public void setShuiwencfg(Shuiwenziduancfg shuiwencfg) {
		this.shuiwencfg = shuiwencfg;
	}
	public Shuiwenziduancfg getShuiwenisshowcfg() {
		return shuiwenisshowcfg;
	}
	public void setShuiwenisshowcfg(Shuiwenziduancfg shuiwenisshowcfg) {
		this.shuiwenisshowcfg = shuiwenisshowcfg;
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
	public Map<String, String> getListmap() {
		return listmap;
	}
	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
	}
	public String getLeixinbh() {
		return leixinbh;
	}
	public void setLeixinbh(String leixinbh) {
		this.leixinbh = leixinbh;
	}
	public int getCfgId() {
		return cfgId;
	}
	public void setCfgId(int cfgId) {
		this.cfgId = cfgId;
	}
	public int getDefaultId() {
		return defaultId;
	}
	public void setDefaultId(int defaultId) {
		this.defaultId = defaultId;
	}
	
	@Action("shuiwencfglist")
	public String shuiwencfglist(){
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
		setShuiwencfglist(sysService.limitshuiwencfglist(request, biaoduan, xiangmubu));		
		return SUCCESS;
	}
	
	@Action(value = "shuiwencfgadd", results = @Result(name = "list", type = "redirect", location = "shuiwencfglist?pid=4&record=42&"))
	public String shuiwencfgadd() {
		sysService.shuiwencfgadd(shuiwencfg);
		if (StringUtil.Null2Blank(shuiwenisshowcfg.getGprsbianhao()).length()==0) {
			shuiwenisshowcfg.setGprsbianhao(shuiwencfg.getGprsbianhao());
		}
		if(StringUtil.Null2Blank(shuiwenzstisshowcfg.getGprsbianhao()).length()==0){
			shuiwenzstisshowcfg.setGprsbianhao(shuiwencfg.getGprsbianhao());
		}
		sysService.shuiwencfgadd(shuiwenzstisshowcfg);
		sysService.shuiwencfgadd(shuiwenisshowcfg);
		return "list";
	}
	
	@Action(value = "shuiwencfginput")
	public String shuiwencfginput() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		setShuiwencfgheader(sysService.getshuiwencfgHeader());

		listmap = new LinkedHashMap<String, String>();
		List<TopRealMaxShuiwenxixxView> toplist = sysService.limitswlist(request, biaoduan, xiangmubu);
		for (TopRealMaxShuiwenxixxView topsw : toplist) {
			listmap.put(topsw.getShebeibianhao(), topsw.getBanhezhanminchen());
		}
		if (cfgId > 0) {
			setShuiwencfg(sysService.shuiwencfgfindById(cfgId));
			setShuiwenisshowcfg(sysService.shuiwenisshowcfgBybh(leixinbh));
			setShuiwenzstisshowcfg(sysService.shuiwenzstisshowcfgBybh(leixinbh));
		} else if (defaultId > 0) {
			setShuiwencfg(sysService.shuiwenfieldnameBybh(leixinbh));
			setShuiwenisshowcfg(sysService.shuiwenisshowcfgBybh(leixinbh));
			setShuiwenzstisshowcfg(sysService.shuiwenzstisshowcfgBybh(leixinbh));
		} else {
			setShuiwencfg(sysService.getDefaultShuiwenziduancfg());
			setShuiwenisshowcfg(sysService.getDefaultShuiwenziduanshow());	
			setShuiwenzstisshowcfg(sysService.getDefaultShuiwenzstziduanshow());
		}
		return SUCCESS;
	}
	
	@Action(value = "shuiwencfgdel", results = @Result(name = "list", type = "redirect", location = "shuiwencfglist?pid=4&record=42&"))
	public String shuiwencfgdel() {
		sysService.shuiwencfgdel(cfgId, leixinbh);
		return "list";
	}

}
