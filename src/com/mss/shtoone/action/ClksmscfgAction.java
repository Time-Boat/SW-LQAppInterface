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
import com.mss.shtoone.domain.Clksmscfg;
import com.mss.shtoone.domain.HandSet;
import com.mss.shtoone.domain.TopRealChuliaokouTemperaturedataView;
import com.mss.shtoone.domain.TopRealSpeeddataView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller
@Namespace("/system")

public class ClksmscfgAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6968772847342608650L;

	@Autowired
	private SystemService sysService;
	
	private List<TopRealChuliaokouTemperaturedataView> clktemplist;
	private List<TopRealSpeeddataView> speedlist;	
	private Map<Integer, String> handsetlistmap;	
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Integer biaoduan;
	private Integer xiangmubu;	
	private String shebeibianhao;
    private Clksmscfg xcsmscfglow;  //下面层
    private Clksmscfg xcsmscfgmid;  //中面层
    private Clksmscfg xcsmscfghigh;  //上面层

	public Clksmscfg getXcsmscfglow() {
		return xcsmscfglow;
	}

	public void setXcsmscfglow(Clksmscfg xcsmscfglow) {
		this.xcsmscfglow = xcsmscfglow;
	}

	public Clksmscfg getXcsmscfgmid() {
		return xcsmscfgmid;
	}

	public void setXcsmscfgmid(Clksmscfg xcsmscfgmid) {
		this.xcsmscfgmid = xcsmscfgmid;
	}

	public Clksmscfg getXcsmscfghigh() {
		return xcsmscfghigh;
	}

	public void setXcsmscfghigh(Clksmscfg xcsmscfghigh) {
		this.xcsmscfghigh = xcsmscfghigh;
	}

	public List<TopRealChuliaokouTemperaturedataView> getClktemplist() {
		return clktemplist;
	}

	public void setClktemplist(
			List<TopRealChuliaokouTemperaturedataView> clktemplist) {
		this.clktemplist = clktemplist;
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

	public List<TopRealSpeeddataView> getSpeedlist() {
		return speedlist;
	}

	public void setSpeedlist(List<TopRealSpeeddataView> speedlist) {
		this.speedlist = speedlist;
	}

	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}
	
	//出料口
	@Action("clksmscfglist")
	public String clksmscfglist() {
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
		setClktemplist(sysService.limitChuliaokouTemperaturelist(request, biaoduan, xiangmubu));		
		return SUCCESS;
	}
	
	//出料口
	@Action(value = "clksmscfginput")
	public String clksmscfginput() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if (StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0) {
			shebeibianhao = request.getParameter("shebeibianhao");
		}		

		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			biaoduan = Integer.valueOf(request.getParameter("biaoduan"));
		}
		
		handsetlistmap = new LinkedHashMap<Integer, String>();
		for (HandSet handset : sysService.handsetlist(biaoduan)) {
			handsetlistmap.put(handset.getId(), handset.getName());
		}

		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
			setXcsmscfglow(sysService.getClksmscfglow(shebeibianhao));
			setXcsmscfgmid(sysService.getClksmscfgmid(shebeibianhao));
			setXcsmscfghigh(sysService.getClksmscfghigh(shebeibianhao));
		}
		return SUCCESS;
	}

	//出料口
	@Action(value = "clksmscfgdel", results = @Result(name = "clklist", type = "redirect", location = "clksmscfglist?pid=4&record=35&"))
	public String clksmscfgdel() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if (StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0) {
			shebeibianhao = request.getParameter("shebeibianhao");
		}
		if(StringUtil.Null2Blank(shebeibianhao).length()>0){
			sysService.clksmscfgdel(shebeibianhao);
		}
		return "clklist";
	}
	
	//出料口
	@Action(value = "clksmscfgadd", results = @Result(name = "clklist", type = "redirect", location = "clksmscfglist?pid=4&record=35&"))
	public String clksmscfgadd() {
		if(xcsmscfglow!=null){
			sysService.clkcfgadd(xcsmscfglow);
		}
		if(xcsmscfgmid!=null){
			sysService.clkcfgadd(xcsmscfgmid);
		}
		if(xcsmscfghigh!=null){
			sysService.clkcfgadd(xcsmscfghigh);
		}
		return "clklist";
	}
}
