package com.mss.shtoone.action;

import java.util.Hashtable;
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
import com.mss.shtoone.domain.Hntbhzziduancfg;
import com.mss.shtoone.domain.HntbhzziduancfgView;
import com.mss.shtoone.domain.TopRealMaxhunnintuView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;




@Controller
@Namespace("/system")

public class SmscfgAction extends ActionSupport {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2986312960022224774L;

	@Autowired
	private SystemService sysService;

	private List<TopRealMaxhunnintuView> smscfglist;
	
	private Hntbhzziduancfg smscfgheader;
	
	private HntbhzziduancfgView smscfgfieldname;
	
	private Hntbhzziduancfg hntissms;	
	private Hntbhzziduancfg hntsmslow;	
	private Hntbhzziduancfg hntsmshigh;	
	private Hntbhzziduancfg hntsmsnumber;	
	private Hntbhzziduancfg hntsmscontent;	
	private Hntbhzziduancfg hntsmssheji;
	private Hntbhzziduancfg hntsmssheji2;
	private Hntbhzziduancfg hntsmslow2;	
	private Hntbhzziduancfg hntsmshigh2;	
	private Hntbhzziduancfg hntsmsnumber2;	
	private Hntbhzziduancfg hntsmslow3;	
	private Hntbhzziduancfg hntsmshigh3;	
	private Hntbhzziduancfg hntsmsnumber3;	
	
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
	
	
	public String getLeixinbh() {
		return leixinbh;
	}

	public void setLeixinbh(String leixinbh) {
		this.leixinbh = leixinbh;
	}

	
	public List<TopRealMaxhunnintuView> getSmscfglist() {
		return smscfglist;
	}

	public void setSmscfglist(List<TopRealMaxhunnintuView> smscfglist) {
		this.smscfglist = smscfglist;
	}

	public Hntbhzziduancfg getSmscfgheader() {
		return smscfgheader;
	}

	public void setSmscfgheader(Hntbhzziduancfg smscfgheader) {
		this.smscfgheader = smscfgheader;
	}

	public HntbhzziduancfgView getSmscfgfieldname() {
		return smscfgfieldname;
	}

	public void setSmscfgfieldname(HntbhzziduancfgView smscfgfieldname) {
		this.smscfgfieldname = smscfgfieldname;
	}

	public Hntbhzziduancfg getHntissms() {
		return hntissms;
	}

	public void setHntissms(Hntbhzziduancfg hntissms) {
		this.hntissms = hntissms;
	}

	public Hntbhzziduancfg getHntsmslow() {
		return hntsmslow;
	}

	public void setHntsmslow(Hntbhzziduancfg hntsmslow) {
		this.hntsmslow = hntsmslow;
	}

	public Hntbhzziduancfg getHntsmshigh() {
		return hntsmshigh;
	}

	public void setHntsmshigh(Hntbhzziduancfg hntsmshigh) {
		this.hntsmshigh = hntsmshigh;
	}

	public Hntbhzziduancfg getHntsmsnumber() {
		return hntsmsnumber;
	}

	public void setHntsmsnumber(Hntbhzziduancfg hntsmsnumber) {
		this.hntsmsnumber = hntsmsnumber;
	}

	public Hntbhzziduancfg getHntsmscontent() {
		return hntsmscontent;
	}

	public void setHntsmscontent(Hntbhzziduancfg hntsmscontent) {
		this.hntsmscontent = hntsmscontent;
	}

	public Hntbhzziduancfg getHntsmssheji() {
		return hntsmssheji;
	}

	public void setHntsmssheji(Hntbhzziduancfg hntsmssheji) {
		this.hntsmssheji = hntsmssheji;
	}

	public Hntbhzziduancfg getHntsmslow2() {
		return hntsmslow2;
	}

	public void setHntsmslow2(Hntbhzziduancfg hntsmslow2) {
		this.hntsmslow2 = hntsmslow2;
	}

	public Hntbhzziduancfg getHntsmshigh2() {
		return hntsmshigh2;
	}

	public void setHntsmshigh2(Hntbhzziduancfg hntsmshigh2) {
		this.hntsmshigh2 = hntsmshigh2;
	}

	public Hntbhzziduancfg getHntsmsnumber2() {
		return hntsmsnumber2;
	}

	public void setHntsmsnumber2(Hntbhzziduancfg hntsmsnumber2) {
		this.hntsmsnumber2 = hntsmsnumber2;
	}

	public Hntbhzziduancfg getHntsmslow3() {
		return hntsmslow3;
	}

	public void setHntsmslow3(Hntbhzziduancfg hntsmslow3) {
		this.hntsmslow3 = hntsmslow3;
	}

	public Hntbhzziduancfg getHntsmshigh3() {
		return hntsmshigh3;
	}

	public void setHntsmshigh3(Hntbhzziduancfg hntsmshigh3) {
		this.hntsmshigh3 = hntsmshigh3;
	}

	public Hntbhzziduancfg getHntsmsnumber3() {
		return hntsmsnumber3;
	}

	public void setHntsmsnumber3(Hntbhzziduancfg hntsmsnumber3) {
		this.hntsmsnumber3 = hntsmsnumber3;
	}

	private int cfgId;
	
	public int getCfgId() {
		return cfgId;
	}

	public void setCfgId(int cfgId) {
		this.cfgId = cfgId;
	}
	
	@Action("smssend")
	public String smssend() {
		if (null != PhoneNum && null != Msg && null != apitype) {
			Hashtable recTable = sysService.saveandSendSms("test", PhoneNum, Msg, apitype);
			setMsg((String)recTable.get("mymsg"));
			setMycode((String)recTable.get("mycode"));
			setSuccessphone((String)recTable.get("successphone"));
			setSmsstatus((String)recTable.get("smsstatus"));
		}
		return SUCCESS;
	}

	@Action("smscfglist")
	public String smscfglist() {
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
		setSmscfglist(sysService.limitsmscfglist(request, biaoduan, xiangmubu));		
		return SUCCESS;
	}
	
	@Action(value = "smscfgadd", results = @Result(name = "list", type = "redirect", location = "smscfglist?pid=4&"))
	public String smscfgadd() {
		sysService.hntcfgadd(hntissms);	
		if (StringUtil.Null2Blank(hntsmslow.getGprsbianhao()).length()==0) {
			hntsmslow.setGprsbianhao(hntissms.getGprsbianhao());
		}
		sysService.hntcfgadd(hntsmslow);
		if (StringUtil.Null2Blank(hntsmshigh.getGprsbianhao()).length()==0) {
			hntsmshigh.setGprsbianhao(hntissms.getGprsbianhao());
		}
		sysService.hntcfgadd(hntsmshigh);
		if (StringUtil.Null2Blank(hntsmsnumber.getGprsbianhao()).length()==0) {
			hntsmsnumber.setGprsbianhao(hntissms.getGprsbianhao());
		}
		sysService.hntcfgadd(hntsmsnumber);
		if (StringUtil.Null2Blank(hntsmscontent.getGprsbianhao()).length()==0) {
			hntsmscontent.setGprsbianhao(hntissms.getGprsbianhao());
		}
		sysService.hntcfgadd(hntsmscontent);
		if (StringUtil.Null2Blank(hntsmssheji.getGprsbianhao()).length()==0) {
			hntsmssheji.setGprsbianhao(hntissms.getGprsbianhao());
		}
		sysService.hntcfgadd(hntsmssheji);
		if (StringUtil.Null2Blank(hntsmssheji2.getGprsbianhao()).length()==0) {
			hntsmssheji2.setGprsbianhao(hntissms.getGprsbianhao());
		}
		sysService.hntcfgadd(hntsmssheji2);
		if (StringUtil.Null2Blank(hntsmslow2.getGprsbianhao()).length()==0) {
			hntsmslow2.setGprsbianhao(hntissms.getGprsbianhao());
		}
		sysService.hntcfgadd(hntsmslow2);
		if (StringUtil.Null2Blank(hntsmshigh2.getGprsbianhao()).length()==0) {
			hntsmshigh2.setGprsbianhao(hntissms.getGprsbianhao());
		}
		sysService.hntcfgadd(hntsmshigh2);
		if (StringUtil.Null2Blank(hntsmsnumber2.getGprsbianhao()).length()==0) {
			hntsmsnumber2.setGprsbianhao(hntissms.getGprsbianhao());
		}
		sysService.hntcfgadd(hntsmsnumber2);
		if (StringUtil.Null2Blank(hntsmslow3.getGprsbianhao()).length()==0) {
			hntsmslow3.setGprsbianhao(hntissms.getGprsbianhao());
		}
		sysService.hntcfgadd(hntsmslow3);
		if (StringUtil.Null2Blank(hntsmshigh3.getGprsbianhao()).length()==0) {
			hntsmshigh3.setGprsbianhao(hntissms.getGprsbianhao());
		}
		sysService.hntcfgadd(hntsmshigh3);
		if (StringUtil.Null2Blank(hntsmsnumber3.getGprsbianhao()).length()==0) {
			hntsmsnumber3.setGprsbianhao(hntissms.getGprsbianhao());
		}
		sysService.hntcfgadd(hntsmsnumber3);
		return "list";
	}
	
	@Action(value = "smscfginput")
	public String smscfginput() {
		setSmscfgheader(sysService.gethntcfgHeader());
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			biaoduan = Integer.valueOf(request.getParameter("biaoduan"));
		}
		
		listmap = new LinkedHashMap<String, String>();
		for (TopRealMaxhunnintuView hntcfgView : sysService.limitsmscfglist(request, biaoduan, xiangmubu)) {
			listmap.put(hntcfgView.getShebeibianhao(), hntcfgView.getBanhezhanminchen());
		}
		
		handsetlistmap = new LinkedHashMap<Integer, String>();
		for (HandSet handset : sysService.handsetlist(biaoduan)) {
			handsetlistmap.put(handset.getId(), handset.getName());
		}

		if (cfgId > 0) {
			setSmscfgfieldname(sysService.hntfieldnamefindBybh(leixinbh));
			setHntissms(sysService.hntissmsfindBybh(leixinbh));
			setHntsmslow(sysService.hntsmslowfindBybh(leixinbh));
			setHntsmshigh(sysService.hntsmshighfindBybh(leixinbh));
			setHntsmsnumber(sysService.hntsmsnumberfindBybh(leixinbh));
			setHntsmscontent(sysService.hntsmscontentfindBybh(leixinbh));
			setHntsmssheji(sysService.hntsmsshejifindBybh(leixinbh));
			setHntsmssheji2(sysService.hntsmsshejifindBybh2(leixinbh));
			setHntsmslow2(sysService.hntsmslowfindBybh2(leixinbh));
			setHntsmshigh2(sysService.hntsmshighfindBybh2(leixinbh));
			setHntsmsnumber2(sysService.hntsmsnumberfindBybh2(leixinbh));
			setHntsmslow3(sysService.hntsmslowfindBybh3(leixinbh));
			setHntsmshigh3(sysService.hntsmshighfindBybh3(leixinbh));
			setHntsmsnumber3(sysService.hntsmsnumberfindBybh3(leixinbh));
		}
		return SUCCESS;
	}
	
	@Action(value = "smscfgdel", results = @Result(name = "list", type = "redirect", location = "smscfglist?pid=4&"))
	public String smscfgdel() {
		sysService.smscfgdel(leixinbh);
		return "list";
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

	public Hntbhzziduancfg getHntsmssheji2() {
		return hntsmssheji2;
	}

	public void setHntsmssheji2(Hntbhzziduancfg hntsmssheji2) {
		this.hntsmssheji2 = hntsmssheji2;
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

	
}
