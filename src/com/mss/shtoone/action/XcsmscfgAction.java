package com.mss.shtoone.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.HandSet;
import com.mss.shtoone.domain.TopRealSpeeddata1View;
import com.mss.shtoone.domain.TopRealSpeeddataView;
import com.mss.shtoone.domain.TopRealTemperaturedata1View;
import com.mss.shtoone.domain.TopRealTemperaturedataView;
import com.mss.shtoone.domain.Xcsmscfg;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;




@Controller
@Namespace("/system")

public class XcsmscfgAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6968772847342608650L;

	@Autowired
	private SystemService sysService;

	private List<TopRealTemperaturedataView> tmplist;
	
	private List<TopRealTemperaturedata1View> yalutmplist;
	
	private List<TopRealSpeeddataView> speedlist;	
	
	private List<TopRealSpeeddata1View> tanpuspeedlist;

	private Map<Integer, String> handsetlistmap;	
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Integer biaoduan;
	private Integer xiangmubu;	
	private String shebeibianhao;
    private Xcsmscfg xcsmscfg;

	@Action("xcsmscfglist")
	public String xcsmscfglist() {
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
		//压路机测速
		setSpeedlist(sysService.limitSpeedlist(request, biaoduan, xiangmubu));
		//摊铺机测温
		setTmplist(sysService.limitTemperaturelist(request, biaoduan, xiangmubu));
//		setTanpuspeedlist(sysService.limitTanpuSpeedlist(request, biaoduan, xiangmubu));
//		setYalutmplist(sysService.limitYaluTemperaturelist(request, biaoduan, xiangmubu));
		return SUCCESS;
	}
	
	@Action(value = "xcsmscfginput")
	public String xcsmscfginput() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
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
			setXcsmscfg(sysService.getXcsmscfg(shebeibianhao));
		}
		return SUCCESS;
	}

	
	@Action(value = "xcsmscfgdel", results = @Result(name = "list", type = "redirect", location = "xcsmscfglist?pid=4&record=34&"))
	public String xcsmscfgdel() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse)context.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		if (StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0) {
			shebeibianhao = request.getParameter("shebeibianhao");
		}
		Xcsmscfg xmcfg=sysService.getXcsmscfg2(shebeibianhao);
		if(xmcfg!=null){
			//sysService.xcsmscfgdel(shebeibianhao);
		}else{
			try {
				 PrintWriter out=response.getWriter();
				 out.println("<script type='text/javascript'>");
				 out.println("alert('删除不了')");
				 out.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "list";
	}
	
	@Action(value = "xcsmscfgadd", results = @Result(name = "list", type = "redirect", location = "xcsmscfglist?pid=4&record=34&"))
	public String xcsmscfgadd() {
		sysService.xccfgadd(xcsmscfg);
		return "list";
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

	public List<TopRealTemperaturedataView> getTmplist() {
		return tmplist;
	}

	public void setTmplist(List<TopRealTemperaturedataView> tmplist) {
		this.tmplist = tmplist;
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

	public Xcsmscfg getXcsmscfg() {
		return xcsmscfg;
	}

	public void setXcsmscfg(Xcsmscfg xcsmscfg) {
		this.xcsmscfg = xcsmscfg;
	}

	public List<TopRealTemperaturedata1View> getYalutmplist() {
		return yalutmplist;
	}

	public void setYalutmplist(List<TopRealTemperaturedata1View> yalutmplist) {
		this.yalutmplist = yalutmplist;
	}

	public List<TopRealSpeeddata1View> getTanpuspeedlist() {
		return tanpuspeedlist;
	}

	public void setTanpuspeedlist(List<TopRealSpeeddata1View> tanpuspeedlist) {
		this.tanpuspeedlist = tanpuspeedlist;
	}	
}
