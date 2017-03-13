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
import com.mss.shtoone.domain.Liqingziduancfg;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.TopRealMaxLiqingView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;




@Controller
@Namespace("/system")

public class LqSmscfgAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3153611999153536225L;

	@Autowired
	private SystemService sysService;

	private List<TopRealMaxLiqingView> lqsmscfglist;
	private Liqingziduancfg smscfgheader;
	private LiqingziduancfgView smscfgfieldname;
	private Liqingziduancfg lqissms;	
	private Liqingziduancfg lqsmslow;	
	private Liqingziduancfg lqsmshigh;	
	private Liqingziduancfg lqsmsnumber;	
	private Liqingziduancfg lqsmscontent;	
	private Liqingziduancfg lqsmssheji;
	private Liqingziduancfg lqsmslow2;	
	private Liqingziduancfg lqsmshigh2;	
	private Liqingziduancfg lqsmslowRight2;
	private Liqingziduancfg lqsmshighRight2;
	private Liqingziduancfg lqsmsnumber2;	
	private Liqingziduancfg lqsmslow3;	
	private Liqingziduancfg lqsmshigh3;	
	private Liqingziduancfg lqsmsnumber3;	
	private Map<String, String> listmap;
	private Map<Integer, String> handsetlistmap;
	private String leixinbh;
	private String PhoneNum;
	private String Msg;
	private String apitype;
	private String mycode;
	private String mymsg;
	private String smsstatus;
	private String successphone;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Integer biaoduan;
	private Integer xiangmubu;
	private int cfgId;
	
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

	public String getPhoneNum() {
		return PhoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public String getApitype() {
		return apitype;
	}

	public void setApitype(String apitype) {
		this.apitype = apitype;
	}


	public String getMycode() {
		return mycode;
	}

	public void setMycode(String mycode) {
		this.mycode = mycode;
	}

	public String getMymsg() {
		return mymsg;
	}

	public void setMymsg(String mymsg) {
		this.mymsg = mymsg;
	}

	public String getSmsstatus() {
		return smsstatus;
	}

	public void setSmsstatus(String smsstatus) {
		this.smsstatus = smsstatus;
	}

	public String getSuccessphone() {
		return successphone;
	}

	public void setSuccessphone(String successphone) {
		this.successphone = successphone;
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

	public List<TopRealMaxLiqingView> getLqsmscfglist() {
		return lqsmscfglist;
	}

	public void setLqsmscfglist(List<TopRealMaxLiqingView> lqsmscfglist) {
		this.lqsmscfglist = lqsmscfglist;
	}

	public Liqingziduancfg getSmscfgheader() {
		return smscfgheader;
	}

	public void setSmscfgheader(Liqingziduancfg smscfgheader) {
		this.smscfgheader = smscfgheader;
	}

	public LiqingziduancfgView getSmscfgfieldname() {
		return smscfgfieldname;
	}

	public void setSmscfgfieldname(LiqingziduancfgView smscfgfieldname) {
		this.smscfgfieldname = smscfgfieldname;
	}

	public Liqingziduancfg getLqissms() {
		return lqissms;
	}

	public void setLqissms(Liqingziduancfg lqissms) {
		this.lqissms = lqissms;
	}

	public Liqingziduancfg getLqsmslow() {
		return lqsmslow;
	}

	public void setLqsmslow(Liqingziduancfg lqsmslow) {
		this.lqsmslow = lqsmslow;
	}

	public Liqingziduancfg getLqsmshigh() {
		return lqsmshigh;
	}

	public void setLqsmshigh(Liqingziduancfg lqsmshigh) {
		this.lqsmshigh = lqsmshigh;
	}

	public Liqingziduancfg getLqsmsnumber() {
		return lqsmsnumber;
	}

	public void setLqsmsnumber(Liqingziduancfg lqsmsnumber) {
		this.lqsmsnumber = lqsmsnumber;
	}

	public Liqingziduancfg getLqsmscontent() {
		return lqsmscontent;
	}

	public void setLqsmscontent(Liqingziduancfg lqsmscontent) {
		this.lqsmscontent = lqsmscontent;
	}

	public Liqingziduancfg getLqsmssheji() {
		return lqsmssheji;
	}

	public void setLqsmssheji(Liqingziduancfg lqsmssheji) {
		this.lqsmssheji = lqsmssheji;
	}

	public Liqingziduancfg getLqsmslow2() {
		return lqsmslow2;
	}

	public void setLqsmslow2(Liqingziduancfg lqsmslow2) {
		this.lqsmslow2 = lqsmslow2;
	}

	public Liqingziduancfg getLqsmshigh2() {
		return lqsmshigh2;
	}

	public void setLqsmshigh2(Liqingziduancfg lqsmshigh2) {
		this.lqsmshigh2 = lqsmshigh2;
	}

	public Liqingziduancfg getLqsmsnumber2() {
		return lqsmsnumber2;
	}

	public void setLqsmsnumber2(Liqingziduancfg lqsmsnumber2) {
		this.lqsmsnumber2 = lqsmsnumber2;
	}

	public Liqingziduancfg getLqsmslow3() {
		return lqsmslow3;
	}

	public void setLqsmslow3(Liqingziduancfg lqsmslow3) {
		this.lqsmslow3 = lqsmslow3;
	}

	public Liqingziduancfg getLqsmshigh3() {
		return lqsmshigh3;
	}

	public void setLqsmshigh3(Liqingziduancfg lqsmshigh3) {
		this.lqsmshigh3 = lqsmshigh3;
	}

	public Liqingziduancfg getLqsmsnumber3() {
		return lqsmsnumber3;
	}

	public void setLqsmsnumber3(Liqingziduancfg lqsmsnumber3) {
		this.lqsmsnumber3 = lqsmsnumber3;
	}

	public Liqingziduancfg getLqsmslowRight2() {
		return lqsmslowRight2;
	}

	public void setLqsmslowRight2(Liqingziduancfg lqsmslowRight2) {
		this.lqsmslowRight2 = lqsmslowRight2;
	}

	public Liqingziduancfg getLqsmshighRight2() {
		return lqsmshighRight2;
	}

	public void setLqsmshighRight2(Liqingziduancfg lqsmshighRight2) {
		this.lqsmshighRight2 = lqsmshighRight2;
	}

	@Action("lqsmscfglist")
	public String lqsmscfglist() {
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
		setLqsmscfglist(sysService.limitlqsmscfglist(request, biaoduan, xiangmubu));		
		return SUCCESS;
	}
	
	@Action(value = "lqsmscfgadd", results = @Result(name = "list", type = "redirect", location = "lqsmscfglist?pid=4&record=33&"))
	public String lqsmscfgadd() {
		sysService.lqcfgadd(lqissms);	
		if (StringUtil.Null2Blank(lqsmslow.getGprsbianhao()).length()==0) {
			lqsmslow.setGprsbianhao(lqissms.getGprsbianhao());
		}
		sysService.lqcfgadd(lqsmslow);
		if (StringUtil.Null2Blank(lqsmshigh.getGprsbianhao()).length()==0) {
			lqsmshigh.setGprsbianhao(lqissms.getGprsbianhao());
		}
		sysService.lqcfgadd(lqsmshigh);
		if (StringUtil.Null2Blank(lqsmsnumber.getGprsbianhao()).length()==0) {
			lqsmsnumber.setGprsbianhao(lqissms.getGprsbianhao());
		}
		sysService.lqcfgadd(lqsmsnumber);
		if (StringUtil.Null2Blank(lqsmscontent.getGprsbianhao()).length()==0) {
			lqsmscontent.setGprsbianhao(lqissms.getGprsbianhao());
		}
		sysService.lqcfgadd(lqsmscontent);
		if (StringUtil.Null2Blank(lqsmssheji.getGprsbianhao()).length()==0) {
			lqsmssheji.setGprsbianhao(lqissms.getGprsbianhao());
		}
		sysService.lqcfgadd(lqsmssheji);
		if (StringUtil.Null2Blank(lqsmslow2.getGprsbianhao()).length()==0) {
			lqsmslow2.setGprsbianhao(lqissms.getGprsbianhao());
		}
		sysService.lqcfgadd(lqsmslow2);
		if (StringUtil.Null2Blank(lqsmshigh2.getGprsbianhao()).length()==0) {
			lqsmshigh2.setGprsbianhao(lqissms.getGprsbianhao());
		}
		sysService.lqcfgadd(lqsmshigh2);
		if (StringUtil.Null2Blank(lqsmsnumber2.getGprsbianhao()).length()==0) {
			lqsmsnumber2.setGprsbianhao(lqissms.getGprsbianhao());
		}
		//在油石比报警中在增加一级
		sysService.lqcfgadd(lqsmslowRight2);
		if(StringUtil.Null2Blank(lqsmslowRight2.getGprsbianhao()).length()==0){
			lqsmslowRight2.setGprsbianhao(lqissms.getGprsbianhao());
		}
		sysService.lqcfgadd(lqsmshighRight2);
		if(StringUtil.Null2Blank(lqsmshighRight2.getGprsbianhao()).length()==0){
			lqsmshighRight2.setGprsbianhao(lqissms.getGprsbianhao());
		}
		
		sysService.lqcfgadd(lqsmsnumber2);
		if (StringUtil.Null2Blank(lqsmslow3.getGprsbianhao()).length()==0) {
			lqsmslow3.setGprsbianhao(lqissms.getGprsbianhao());
		}
		sysService.lqcfgadd(lqsmslow3);
		if (StringUtil.Null2Blank(lqsmshigh3.getGprsbianhao()).length()==0) {
			lqsmshigh3.setGprsbianhao(lqissms.getGprsbianhao());
		}
		sysService.lqcfgadd(lqsmshigh3);
		if (StringUtil.Null2Blank(lqsmsnumber3.getGprsbianhao()).length()==0) {
			lqsmsnumber3.setGprsbianhao(lqissms.getGprsbianhao());
		}
		sysService.lqcfgadd(lqsmsnumber3);
		return "list";
	}
	
	@Action(value = "lqsmscfginput")
	public String lqsmscfginput() {
		setSmscfgheader(sysService.getliqingcfgHeader());
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
			setSmscfgfieldname(sysService.lqfieldnamefindBybh(leixinbh));
			setLqissms(sysService.lqissmsfindBybh(leixinbh));
			setLqsmslow(sysService.lqsmslowfindBybh(leixinbh));
			setLqsmshigh(sysService.lqsmshighfindBybh(leixinbh));
			setLqsmsnumber(sysService.lqsmsnumberfindBybh(leixinbh));
			setLqsmscontent(sysService.lqsmscontentfindBybh(leixinbh));
			setLqsmssheji(sysService.lqsmsshejifindBybh(leixinbh));
			setLqsmslow2(sysService.lqsmslowfindBybh2(leixinbh));
			setLqsmshigh2(sysService.lqsmshighfindBybh2(leixinbh));
			//油石比的报警添加一级
			setLqsmslowRight2(sysService.lqsmslowRightfindBybh2(leixinbh));
			setLqsmshighRight2(sysService.lqsmshighRightfindBybh2(leixinbh));
			
			setLqsmsnumber2(sysService.lqsmsnumberfindBybh2(leixinbh));
			setLqsmslow3(sysService.lqsmslowfindBybh3(leixinbh));
			setLqsmshigh3(sysService.lqsmshighfindBybh3(leixinbh));
			setLqsmsnumber3(sysService.lqsmsnumberfindBybh3(leixinbh));
		}
		return SUCCESS;
	}
	
	@Action(value = "lqsmscfgdel", results = @Result(name = "list", type = "redirect", location = "lqsmscfglist?pid=4&record=33&"))
	public String lqsmscfgdel() {
		sysService.lqsmscfgdel(leixinbh);
		return "list";
	}
	
}
