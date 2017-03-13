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

public class SwSmsCfgAction extends ActionSupport {

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
	private Shuiwenziduancfg swsmscontent;	
	private Shuiwenziduancfg swsmssheji;
	private Shuiwenziduancfg swsmslow2;	
	private Shuiwenziduancfg swsmshigh2;
	private Shuiwenziduancfg swsmslow4;	//骨料粉料有多个区间报警
	private Shuiwenziduancfg swsmshigh4;//骨料粉料有多个区间报警
	
	
	private Shuiwenziduancfg swsmsnumber2;	
	private Shuiwenziduancfg swsmslow3;	
	private Shuiwenziduancfg swsmshigh3;	
	private Shuiwenziduancfg swsmsnumber3;	
	
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
	private Integer cfgId;
	
	
	public Shuiwenziduancfg getSwsmslow4() {
		return swsmslow4;
	}

	public void setSwsmslow4(Shuiwenziduancfg swsmslow4) {
		this.swsmslow4 = swsmslow4;
	}

	public Shuiwenziduancfg getSwsmshigh4() {
		return swsmshigh4;
	}

	public void setSwsmshigh4(Shuiwenziduancfg swsmshigh4) {
		this.swsmshigh4 = swsmshigh4;
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

	public Shuiwenziduancfg getSwsmscontent() {
		return swsmscontent;
	}

	public void setSwsmscontent(Shuiwenziduancfg swsmscontent) {
		this.swsmscontent = swsmscontent;
	}

	public Shuiwenziduancfg getSwsmssheji() {
		return swsmssheji;
	}

	public void setSwsmssheji(Shuiwenziduancfg swsmssheji) {
		this.swsmssheji = swsmssheji;
	}

	public Shuiwenziduancfg getSwsmslow2() {
		return swsmslow2;
	}

	public void setSwsmslow2(Shuiwenziduancfg swsmslow2) {
		this.swsmslow2 = swsmslow2;
	}

	public Shuiwenziduancfg getSwsmshigh2() {
		return swsmshigh2;
	}

	public void setSwsmshigh2(Shuiwenziduancfg swsmshigh2) {
		this.swsmshigh2 = swsmshigh2;
	}

	public Shuiwenziduancfg getSwsmsnumber2() {
		return swsmsnumber2;
	}

	public void setSwsmsnumber2(Shuiwenziduancfg swsmsnumber2) {
		this.swsmsnumber2 = swsmsnumber2;
	}

	public Shuiwenziduancfg getSwsmslow3() {
		return swsmslow3;
	}

	public void setSwsmslow3(Shuiwenziduancfg swsmslow3) {
		this.swsmslow3 = swsmslow3;
	}

	public Shuiwenziduancfg getSwsmshigh3() {
		return swsmshigh3;
	}

	public void setSwsmshigh3(Shuiwenziduancfg swsmshigh3) {
		this.swsmshigh3 = swsmshigh3;
	}

	public Shuiwenziduancfg getSwsmsnumber3() {
		return swsmsnumber3;
	}

	public void setSwsmsnumber3(Shuiwenziduancfg swsmsnumber3) {
		this.swsmsnumber3 = swsmsnumber3;
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


	@Action("swsmscfglist")
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
	
	@Action(value = "swsmscfgadd", results = @Result(name = "list", type = "redirect", location = "swsmscfglist?pid=4&record=20&"))
	public String swsmscfgadd() {
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
		if (StringUtil.Null2Blank(swsmscontent.getGprsbianhao()).length()==0) {
			swsmscontent.setGprsbianhao(swissms.getGprsbianhao());
		}
		sysService.swcfgadd(swsmscontent);
		if (StringUtil.Null2Blank(swsmssheji.getGprsbianhao()).length()==0) {
			swsmssheji.setGprsbianhao(swissms.getGprsbianhao());
		}
		sysService.swcfgadd(swsmssheji);
		if (StringUtil.Null2Blank(swsmslow2.getGprsbianhao()).length()==0) {
			swsmslow2.setGprsbianhao(swissms.getGprsbianhao());
		}
		sysService.swcfgadd(swsmslow2);
		if (StringUtil.Null2Blank(swsmshigh2.getGprsbianhao()).length()==0) {
			swsmshigh2.setGprsbianhao(swissms.getGprsbianhao());
		}
		sysService.swcfgadd(swsmshigh2);
		
		
		//多加个区间报警
		if (StringUtil.Null2Blank(swsmslow4.getGprsbianhao()).length()==0) {
			swsmslow4.setGprsbianhao(swissms.getGprsbianhao());
			swsmslow4.setLeixing("18");
		}
		
		sysService.swcfgadd(swsmslow4);
		if (StringUtil.Null2Blank(swsmshigh4.getGprsbianhao()).length()==0) {
			swsmshigh4.setGprsbianhao(swissms.getGprsbianhao());
			swsmshigh4.setLeixing("19");
		}
		sysService.swcfgadd(swsmshigh4);		
		if (StringUtil.Null2Blank(swsmsnumber2.getGprsbianhao()).length()==0) {
			swsmsnumber2.setGprsbianhao(swissms.getGprsbianhao());
		}
		
		sysService.swcfgadd(swsmsnumber2);
		if (StringUtil.Null2Blank(swsmslow3.getGprsbianhao()).length()==0) {
			swsmslow3.setGprsbianhao(swissms.getGprsbianhao());
		}
		sysService.swcfgadd(swsmslow3);
		if (StringUtil.Null2Blank(swsmshigh3.getGprsbianhao()).length()==0) {
			swsmshigh3.setGprsbianhao(swissms.getGprsbianhao());
		}
		sysService.swcfgadd(swsmshigh3);
		if (StringUtil.Null2Blank(swsmsnumber3.getGprsbianhao()).length()==0) {
			swsmsnumber3.setGprsbianhao(swissms.getGprsbianhao());
		}
		sysService.swcfgadd(swsmsnumber3);
		return "list";
	}
	
	@Action(value = "swsmscfginput")
	public String swsmscfginput() {
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
			setSwissms(sysService.swissmsfindBybh(leixinbh));
			setSwsmslow(sysService.swsmslowfindBybh(leixinbh));
			setSwsmshigh(sysService.swsmshighfindBybh(leixinbh));
			setSwsmsnumber(sysService.swsmsnumberfindBybh(leixinbh));
			setSwsmscontent(sysService.swsmscontentfindBybh(leixinbh));
			setSwsmssheji(sysService.swsmsshejifindBybh(leixinbh));
			//区间1-2
			setSwsmslow2(sysService.swsmslowfindBybh2(leixinbh));
			setSwsmshigh2(sysService.swsmshighfindBybh2(leixinbh));
			//区间3-4
			setSwsmslow4(sysService.swsmslowfindBybh4(leixinbh));
			setSwsmshigh4(sysService.swsmshighfindBybh4(leixinbh));
			setSwsmsnumber2(sysService.swsmsnumberfindBybh2(leixinbh));
			setSwsmslow3(sysService.swsmslowfindBybh3(leixinbh));
			setSwsmshigh3(sysService.swsmshighfindBybh3(leixinbh));
			setSwsmsnumber3(sysService.swsmsnumberfindBybh3(leixinbh));
		}
		return SUCCESS;
	}
	
	@Action(value = "swsmscfgdel", results = @Result(name = "list", type = "redirect", location = "swsmscfglist?pid=4&record=20&"))
	public String swsmscfgdel() {
		sysService.swsmscfgdel(leixinbh);
		return "list";
	}
}
