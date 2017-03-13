package com.mss.shtoone.action;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.Hntbhzziduancfg;
import com.mss.shtoone.domain.HntbhzziduancfgView;
import com.mss.shtoone.domain.HunnintuwuchaPageMode;
import com.mss.shtoone.domain.HunnintuwuchaView;
import com.mss.shtoone.domain.TophunnintuView;
import com.mss.shtoone.domain.WuchaIsShowData;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.QuerywuchaService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class QuerywuchaAction extends ActionSupport {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6249284108916613673L;

	@Autowired
	private QuerywuchaService queryService;

	@Autowired
	private SystemService sysService;

	private HunnintuwuchaPageMode hunnintus;

	private HntbhzziduancfgView hntbhzisShow;

	private HntbhzziduancfgView hntbhzField;

	private HunnintuwuchaView xiangxixx;

	private String startTime;
	private String endTime;
	
	private Integer biaoduan;
	private Integer xiangmubu;

	private String jiaozhubuwei;
	
	private String bhzname;

	private int maxPageItems;
	private Integer pageNo;	
	private Integer[] wuchaselect;
	private List<WuchaIsShowData> wuchalist;

	public int getMaxPageItems() {
		return maxPageItems;
	}

	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
	}

	public String getJiaozhubuwei() {
		return jiaozhubuwei;
	}

	public void setJiaozhubuwei(String jiaozhubuwei) {
		this.jiaozhubuwei = jiaozhubuwei;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public HunnintuwuchaView getXiangxixx() {
		return xiangxixx;
	}

	public void setXiangxixx(HunnintuwuchaView xiangxixx) {
		this.xiangxixx = xiangxixx;
	}

	private String shebeibianhao;

	private String gongdanhao;

	public String getGongdanhao() {
		return gongdanhao;
	}

	public void setGongdanhao(String gongdanhao) {
		this.gongdanhao = gongdanhao;
	}
	
	public String excelName;


	public String getExcelName() {
		return excelName;
	}



	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	
	private Map<String, String> listmap;

	private Map<String, String> gdhlistmap;
	
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;

	public Map<String, String> getJzbwlistmap() {
		return jzbwlistmap;
	}

	public void setJzbwlistmap(Map<String, String> jzbwlistmap) {
		this.jzbwlistmap = jzbwlistmap;
	}

	private Map<String, String> jzbwlistmap;

	public Map<String, String> getGdhlistmap() {
		return gdhlistmap;
	}

	public void setGdhlistmap(Map<String, String> gdhlistmap) {
		this.gdhlistmap = gdhlistmap;
	}

	public Map<String, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
	}

	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}

	public HntbhzziduancfgView getHntbhzisShow() {
		return hntbhzisShow;
	}

	public void setHntbhzisShow(HntbhzziduancfgView hntbhzisShow) {
		this.hntbhzisShow = hntbhzisShow;
	}

	public HntbhzziduancfgView getHntbhzField() {
		return hntbhzField;
	}

	public void setHntbhzField(HntbhzziduancfgView hntbhzField) {
		this.hntbhzField = hntbhzField;
	}

	public HunnintuwuchaPageMode getHunnintus() {
		return hunnintus;
	}

	public void setHunnintus(HunnintuwuchaPageMode hunnintus) {
		this.hunnintus = hunnintus;
	}
	
	private void setHeaderField(List<String> hList, String fieldname) {
		hList.add(fieldname+"误差");
	}
	
	@Action("hntclwuchalist")
	public String hntclwuchalist() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		setPageNo(1);
		if (null != request.getParameter("pageNo")) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (null != request.getParameter("maxPageItems")) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		String shebeibianhao = request.getParameter("shebeibianhao");

		setShebeibianhao(shebeibianhao);
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}		
		if (null != wuchaselect && wuchaselect.length > 0) {
			Hntbhzziduancfg hbcfgisshow;
			hbcfgisshow = queryService.gethntcfg(shebeibianhao);
			if (null == hbcfgisshow) {
				hbcfgisshow = new Hntbhzziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
					hbcfgisshow.setGprsbianhao(shebeibianhao);
					hbcfgisshow.setLeixin("21");
				} else {
					hbcfgisshow.setGprsbianhao("all");
					hbcfgisshow.setLeixin("20");
				}
			}
			
			List<Integer> selectlist = new ArrayList<Integer>();
			for (int h = 0; h < wuchaselect.length; h++) {
				selectlist.add(wuchaselect[h]);
			}
			if (selectlist.contains(1)) {
				hbcfgisshow.setShui1_shijizhi("1");
			} else {
				hbcfgisshow.setShui1_shijizhi("0");
			}
			if (selectlist.contains(2)) {
				hbcfgisshow.setShui2_shijizhi("1");
			} else {
				hbcfgisshow.setShui2_shijizhi("0");
			}
			if (selectlist.contains(3)) {
				hbcfgisshow.setShuini1_shijizhi("1");
			} else {
				hbcfgisshow.setShuini1_shijizhi("0");
			}
			if (selectlist.contains(4)) {
				hbcfgisshow.setShuini2_shijizhi("1");
			} else {
				hbcfgisshow.setShuini2_shijizhi("0");
			}
			if (selectlist.contains(5)) {
				hbcfgisshow.setKuangfen3_shijizhi("1");
			} else {
				hbcfgisshow.setKuangfen3_shijizhi("0");
			}
			if (selectlist.contains(6)) {
				hbcfgisshow.setFeimeihui4_shijizhi("1");
			} else {
				hbcfgisshow.setFeimeihui4_shijizhi("0");
			}
			if (selectlist.contains(7)) {
				hbcfgisshow.setFenliao5_shijizhi("1");
			} else {
				hbcfgisshow.setFenliao5_shijizhi("0");
			}
			if (selectlist.contains(8)) {
				hbcfgisshow.setFenliao6_shijizhi("1");
			} else {
				hbcfgisshow.setFenliao6_shijizhi("0");
			} 
			if (selectlist.contains(9)) {
				hbcfgisshow.setSha1_shijizhi("1");
			} else {
				hbcfgisshow.setSha1_shijizhi("0");
			}
			if (selectlist.contains(10)) {
				hbcfgisshow.setShi1_shijizhi("1");
			} else {
				hbcfgisshow.setShi1_shijizhi("0");
			}
			if (selectlist.contains(11)) {
				hbcfgisshow.setShi2_shijizhi("1");
			} else {
				hbcfgisshow.setShi2_shijizhi("0");
			}
			if (selectlist.contains(12)) {
				hbcfgisshow.setSha2_shijizhi("1");
			} else {
				hbcfgisshow.setSha2_shijizhi("0");
			}
			if (selectlist.contains(13)) {
				hbcfgisshow.setGuliao5_shijizhi("1");
			} else {
				hbcfgisshow.setGuliao5_shijizhi("0");
			}
			if (selectlist.contains(14)) {
				hbcfgisshow.setWaijiaji1_shijizhi("1");
			} else {
				hbcfgisshow.setWaijiaji1_shijizhi("0");
			}
			if (selectlist.contains(15)) {
				hbcfgisshow.setWaijiaji2_shijizhi("1");
			} else {
				hbcfgisshow.setWaijiaji2_shijizhi("0");
			}
			if (selectlist.contains(16)) {
				hbcfgisshow.setWaijiaji3_shijizhi("1");
			} else {
				hbcfgisshow.setWaijiaji3_shijizhi("0");
			}
			if (selectlist.contains(17)) {
				hbcfgisshow.setWaijiaji4_shijizhi("1");
			} else  {
				hbcfgisshow.setWaijiaji4_shijizhi("0");
			}
			queryService.saveOrupdate(hbcfgisshow);
		}

		String gongchenghao = request.getParameter("gongdanhao");
		String jiaozuobuwei = request.getParameter("jiaozhubuwei");			

		setJiaozhubuwei(jiaozuobuwei);
		setGongdanhao(gongchenghao);
		String startTimeOne = request.getParameter("startTime");
		String endTimeOne = request.getParameter("endTime");
		setStartTime(startTimeOne);
		setEndTime(endTimeOne);
		setHunnintus(queryService.viewlist(shebeibianhao, gongchenghao,
				startTimeOne, endTimeOne, jiaozhubuwei,biaoduan,xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request), pageNo, maxPageItems));
		HntbhzziduancfgView hbfield = queryService.gethntcfgfield(shebeibianhao);
		HntbhzziduancfgView hbisshow = queryService.gethntcfgisShow(shebeibianhao);		
		if (null == hbisshow) {
			Hntbhzziduancfg hbcfgisshow;
			hbcfgisshow = queryService.gethntcfg(shebeibianhao);
			if (null == hbcfgisshow) {
				hbcfgisshow = new Hntbhzziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
					hbcfgisshow.setGprsbianhao(shebeibianhao);
					hbcfgisshow.setLeixin("21");
				} else {
					hbcfgisshow.setGprsbianhao("all");
					hbcfgisshow.setLeixin("20");
				}
			}
			hbcfgisshow.setShui1_shijizhi("1");
			hbcfgisshow.setShui2_shijizhi("1");
			hbcfgisshow.setShuini1_shijizhi("1");
			hbcfgisshow.setShuini2_shijizhi("1");
			hbcfgisshow.setKuangfen3_shijizhi("1");
			hbcfgisshow.setFeimeihui4_shijizhi("1");
			hbcfgisshow.setFenliao5_shijizhi("1");
			hbcfgisshow.setFenliao6_shijizhi("1");
			hbcfgisshow.setSha1_shijizhi("1");
			hbcfgisshow.setShi1_shijizhi("1");
			hbcfgisshow.setShi2_shijizhi("1");
			hbcfgisshow.setSha2_shijizhi("1");
			hbcfgisshow.setGuliao5_shijizhi("1");
			hbcfgisshow.setWaijiaji1_shijizhi("1");
			hbcfgisshow.setWaijiaji2_shijizhi("1");
			hbcfgisshow.setWaijiaji3_shijizhi("1");
			hbcfgisshow.setWaijiaji4_shijizhi("1");
			queryService.saveOrupdate(hbcfgisshow);
			hbisshow = queryService.gethntcfgisShow(shebeibianhao);	
		}		
		wuchalist = new ArrayList<WuchaIsShowData>();
		wuchaselect = new Integer[17];
		int i = 1;
		WuchaIsShowData wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getShui1_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getShui1_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getShui2_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getShui2_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getShuini1_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getShuini1_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getShuini2_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getShuini2_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getKuangfen3_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getKuangfen3_shijizhi());
		wuchalist.add(wd);
		
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getFeimeihui4_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getFeimeihui4_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getFenliao5_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getFenliao5_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getFenliao6_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getFenliao6_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getSha1_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getSha1_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getShi1_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getShi1_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getShi2_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getShi2_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getSha2_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getSha2_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getGuliao5_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getGuliao5_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getWaijiaji1_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getWaijiaji1_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getWaijiaji2_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getWaijiaji2_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getWaijiaji3_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getWaijiaji3_shijizhi());
		wuchalist.add(wd);
		
		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(hbisshow.getWaijiaji4_shijizhi()).equalsIgnoreCase("1")) {
			wuchaselect[i-1] = i;			
		} 
		wd.setId(i);
		i++;
		wd.setName(hbfield.getWaijiaji4_shijizhi());
		wuchalist.add(wd);
		
		setHntbhzField(hbfield);
		setHntbhzisShow(hbisshow);
		
		if (null != hunnintus && null != hunnintus.getDatas() && hunnintus.getDatas().size() > 0) {
			request.setAttribute("strXML", queryService.getHntCailiaoXml(hunnintus.getDatas(), hntbhzField, hntbhzisShow));
			request.setAttribute("strXMLWucha", queryService.getHntCailiaoWuchaXml(hunnintus.getDatas(), hntbhzField, hntbhzisShow));
		} else {
			request.setAttribute("strXML", "");
			request.setAttribute("strXMLWucha", "");
		}
		
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
		
		listmap = new LinkedHashMap<String, String>();
		List<TophunnintuView> toplist = sysService.limithntlist(request, biaoduan, xiangmubu);
		for (TophunnintuView tophunnintuView : toplist) {
			listmap.put(tophunnintuView.getShebeibianhao(), tophunnintuView
					.getBanhezhanminchen());
		}
		gdhlistmap = new LinkedHashMap<String, String>();
		List gdhtoplist = sysService.limitgetListbyField(request, "gongchengmingcheng", biaoduan, xiangmubu, shebeibianhao);
		for (int j = 0; j < gdhtoplist.size(); j++) {
			gdhlistmap.put(String.valueOf(gdhtoplist.get(j)), String
					.valueOf(gdhtoplist.get(j)));
		}

		jzbwlistmap = new LinkedHashMap<String, String>();
		List jzbwtoplist = sysService.limitgetListbyField(request, "jiaozuobuwei", biaoduan, xiangmubu, shebeibianhao);
		for (int k = 0; k < jzbwtoplist.size(); k++) {
			jzbwlistmap.put(String.valueOf(jzbwtoplist.get(k)), String
					.valueOf(jzbwtoplist.get(k)));
		}
		
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")
				&& (null != hbfield && null != hbisshow)){
			List<String> dataList=new ArrayList<String>();
			List<String> headerList = new ArrayList<String>();
			//headerList.add(hbfield.getQiangdudengji());
			//headerList.add(hbfield.getPeifanghao());
			//headerList.add(hbfield.getGujifangshu());
				
			/*if (StringUtil.Null2Blank(hbisshow.getShui1_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getShui1_shijizhi());
				headerList.add(hbfield.getShui1_lilunzhi());
				setHeaderField(headerList, hbfield.getShui1_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getShui2_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getShui2_shijizhi());
				headerList.add(hbfield.getShui2_lilunzhi());
				setHeaderField(headerList, hbfield.getShui2_shijizhi());
			}*/
				
			//if (StringUtil.Null2Blank(hbisshow.getShuini1_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getShuini1_shijizhi());
				//headerList.add(hbfield.getShuini1_lilunzhi());
				//setHeaderField(headerList, hbfield.getShuini1_shijizhi());
			//}
				
			//if (StringUtil.Null2Blank(hbisshow.getShuini2_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getShuini2_shijizhi());
				//headerList.add(hbfield.getShuini2_lilunzhi());
				//setHeaderField(headerList, hbfield.getShuini2_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getSha1_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getSha1_shijizhi());
				//headerList.add(hbfield.getSha1_lilunzhi());
				//setHeaderField(headerList, hbfield.getSha1_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getShi1_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getShi1_shijizhi());
				//headerList.add(hbfield.getShi1_lilunzhi());
				//setHeaderField(headerList, hbfield.getShi1_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getShi2_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getShi2_shijizhi());
				//headerList.add(hbfield.getShi2_lilunzhi());
				//setHeaderField(headerList, hbfield.getShi2_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getSha2_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getSha2_shijizhi());
				//headerList.add(hbfield.getSha2_lilunzhi());
				//setHeaderField(headerList, hbfield.getSha2_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getGuliao5_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getGuliao5_shijizhi());
				//headerList.add(hbfield.getGuliao5_lilunzhi());
				//setHeaderField(headerList, hbfield.getGuliao5_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getKuangfen3_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getKuangfen3_shijizhi());
				//headerList.add(hbfield.getKuangfen3_lilunzhi());
				//setHeaderField(headerList, hbfield.getKuangfen3_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getFeimeihui4_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getFeimeihui4_shijizhi());
				//headerList.add(hbfield.getFeimeihui4_lilunzhi());
				//setHeaderField(headerList, hbfield.getFeimeihui4_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getFenliao5_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getFenliao5_shijizhi());
				//headerList.add(hbfield.getFenliao5_lilunzhi());
				//setHeaderField(headerList, hbfield.getFenliao5_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getFenliao6_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getFenliao6_shijizhi());
				//headerList.add(hbfield.getFenliao6_lilunzhi());
				//setHeaderField(headerList, hbfield.getFenliao6_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getWaijiaji1_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getWaijiaji1_shijizhi());
				//headerList.add(hbfield.getWaijiaji1_lilunzhi());
				//setHeaderField(headerList, hbfield.getWaijiaji1_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getWaijiaji2_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getWaijiaji2_shijizhi());
				//headerList.add(hbfield.getWaijiaji2_lilunzhi());
				//setHeaderField(headerList, hbfield.getWaijiaji2_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getWaijiaji3_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getWaijiaji3_shijizhi());
				//headerList.add(hbfield.getWaijiaji3_lilunzhi());
				//setHeaderField(headerList, hbfield.getWaijiaji3_shijizhi());
			//}
			
			//if (StringUtil.Null2Blank(hbisshow.getWaijiaji4_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getWaijiaji4_shijizhi());
				//headerList.add(hbfield.getWaijiaji4_lilunzhi());
				//setHeaderField(headerList, hbfield.getWaijiaji4_shijizhi());
			//}		
			
			
			for (HunnintuwuchaView  hnt: hunnintus.getDatas()) {
				StringBuilder databuilder = new StringBuilder();
				databuilder.append(StringUtil.Null2Blank(hnt.getChuliaoshijian()));
				databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hnt.getQiangdudengji()));
					databuilder.append("&^&");			
					databuilder.append(StringUtil.Null2Blank(hnt.getPeifanghao()));
					databuilder.append("&^&");			
					databuilder.append(StringUtil.Null2Blank(hnt.getGujifangshu()));
					databuilder.append("&^&");			
				/*if (StringUtil.Null2Blank(hbisshow.getShui1_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(hnt.getShui1_shijizhi());
					databuilder.append("&^&");			
					databuilder.append(hnt.getShui1_lilunzhi());
					databuilder.append("&^&");
					databuilder.append(hnt.getShw1());
					databuilder.append("&^&");
				}
				if (StringUtil.Null2Blank(hbisshow.getShui2_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(hnt.getShui2_shijizhi());
					databuilder.append("&^&");	
					databuilder.append(hnt.getShui2_lilunzhi());
					databuilder.append("&^&");	
					databuilder.append(hnt.getShw2());
					databuilder.append("&^&");	
				}*/
				//if (StringUtil.Null2Blank(hbisshow.getShuini1_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getShuini1_shijizhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getShuini1_lilunzhi()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hnt.getFlw1()));
					databuilder.append("&^&");	
				//}
				//if (StringUtil.Null2Blank(hbisshow.getShuini2_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getShuini2_shijizhi()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hnt.getShuini2_lilunzhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getFlw2()));
					databuilder.append("&^&");
				//}
				//if (StringUtil.Null2Blank(hbisshow.getSha1_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getSha1_shijizhi()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hnt.getSha1_lilunzhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getGlw1()));
					databuilder.append("&^&");
				//}
				//if (StringUtil.Null2Blank(hbisshow.getShi1_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getShi1_shijizhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getShi1_lilunzhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getGlw2()));
					databuilder.append("&^&");
				//}
				//if (StringUtil.Null2Blank(hbisshow.getShi2_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getShi2_shijizhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getShi2_lilunzhi()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hnt.getGlw3()));
					databuilder.append("&^&");
				//}
				//if (StringUtil.Null2Blank(hbisshow.getSha2_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getSha2_shijizhi()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hnt.getSha2_lilunzhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getGlw4()));
					databuilder.append("&^&");
				//}
				//if (StringUtil.Null2Blank(hbisshow.getGuliao5_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getGuliao5_shijizhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getGuliao5_lilunzhi()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hnt.getGlw5()));
					databuilder.append("&^&");	
				//}
				//if (StringUtil.Null2Blank(hbisshow.getKuangfen3_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getKuangfen3_shijizhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getKuangfen3_lilunzhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getFlw3()));
					databuilder.append("&^&");
				//}
				//if (StringUtil.Null2Blank(hbisshow.getFeimeihui4_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getFeimeihui4_shijizhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getFeimeihui4_lilunzhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getFlw4()));
					databuilder.append("&^&");
				//}
				//if (StringUtil.Null2Blank(hbisshow.getFenliao5_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getFenliao5_shijizhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getFenliao5_lilunzhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getFlw5()));
					databuilder.append("&^&");
				//}
				//if (StringUtil.Null2Blank(hbisshow.getFenliao6_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getFenliao6_shijizhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getFenliao6_lilunzhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getFlw6()));
					databuilder.append("&^&");
				//}
				//if (StringUtil.Null2Blank(hbisshow.getWaijiaji1_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji1_shijizhi()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji1_lilunzhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getWjw1()));
					databuilder.append("&^&");
				//}
				//if (StringUtil.Null2Blank(hbisshow.getWaijiaji2_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji2_shijizhi()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji2_lilunzhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getWjw2()));
					databuilder.append("&^&");
				//}
				//if (StringUtil.Null2Blank(hbisshow.getWaijiaji3_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji3_shijizhi()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji3_lilunzhi()));
					databuilder.append("&^&");	
					databuilder.append(StringUtil.Null2Blank(hnt.getWjw3()));
					databuilder.append("&^&");
				//}
				//if (StringUtil.Null2Blank(hbisshow.getWaijiaji4_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji4_shijizhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji4_lilunzhi()));
					databuilder.append("&^&");
					databuilder.append(StringUtil.Null2Blank(hnt.getWjw4()));
				//}
				dataList.add(databuilder.toString());
			}
			String path=request.getSession().getServletContext().getRealPath("/");
			String excelName="excel/"+System.currentTimeMillis()+".xls";
			setExcelName(excelName);
			File file=new File(path+"excel");
			if(!file.exists()){
				file.mkdir();
			}
			String[] header = new String[headerList.size()];
			int j = 0;
			for (Iterator iterator = headerList.iterator(); iterator
					.hasNext();) {
				header[j] = StringUtil.Null2Blank((String)iterator.next());
				j++;
			}
			HntExcelUtil.importWuchaExcel(path+"excel/配合比消耗.xls", path+excelName,
					StringUtil.Null2Blank(bhzname),startTime+"～"+endTime, header, dataList);
			try {
				response.reset();
				response.setContentType("bin");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + excelName + "\"");
				java.io.FileInputStream in = new java.io.FileInputStream(path
						+ excelName);
				// response.flushBuffer();
				PrintWriter out = response.getWriter();
				int k;
				while ((k = in.read()) != -1) {
					out.write(k);
				}
				in.close();
		        out.flush();
		        out.close();
			} catch (Exception e) {
			}
		}
		
		return SUCCESS;
	}

	@Action(value = "xiangxiinput")
	public String xiangxiinput() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		String xxid = request.getParameter("xxid");
		if (null != xxid) {
			HunnintuwuchaView xx = queryService.xxfindById(Integer.parseInt(xxid));
			setXiangxixx(xx);
		}
		return SUCCESS;
	}

	public Integer[] getWuchaselect() {
		return wuchaselect;
	}

	public void setWuchaselect(Integer[] wuchaselect) {
		this.wuchaselect = wuchaselect;
	}

	public List<WuchaIsShowData> getWuchalist() {
		return wuchalist;
	}

	public void setWuchalist(List<WuchaIsShowData> wuchalist) {
		this.wuchalist = wuchalist;
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

	public String getBhzname() {
		return bhzname;
	}

	public void setBhzname(String bhzname) {
		this.bhzname = bhzname;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

}
