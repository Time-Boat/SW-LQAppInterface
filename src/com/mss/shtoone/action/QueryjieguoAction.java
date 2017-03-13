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
import com.mss.shtoone.domain.HntbhzziduancfgView;
import com.mss.shtoone.domain.HunnintujieguoPageMode;
import com.mss.shtoone.domain.HunnintujieguoView;
import com.mss.shtoone.domain.TophunnintuView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class QueryjieguoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5500216519873396174L;

	@Autowired
	private QueryService queryService;

	@Autowired
	private SystemService sysService;

	private HunnintujieguoPageMode hunnintus;

	private HntbhzziduancfgView hntbhzisShow;

	private HntbhzziduancfgView hntbhzField;


	private String startTime;
	private String endTime;
	
	private Integer biaoduan;
	private Integer xiangmubu;

	private String jiaozhubuwei;
	
	private String bhzname;

	private int maxPageItems;
	private Integer pageNo;	

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

	public HunnintujieguoPageMode getHunnintus() {
		return hunnintus;
	}

	public void setHunnintus(HunnintujieguoPageMode hunnintus) {
		this.hunnintus = hunnintus;
	}
	
	@Action("hntcljieguolist")
	public String hntcljieguolist() {
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

		String gongchenghao = request.getParameter("gongdanhao");
		String jiaozuobuwei = request.getParameter("jiaozhubuwei");			

		setJiaozhubuwei(jiaozuobuwei);
		setGongdanhao(gongchenghao);
		String startTimeOne = request.getParameter("startTime");
		String endTimeOne = request.getParameter("endTime");
		setStartTime(startTimeOne);
		setEndTime(endTimeOne);
		setHunnintus(queryService.viewjieguolist(shebeibianhao, gongchenghao,
				startTimeOne, endTimeOne, jiaozhubuwei,biaoduan,xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request), pageNo, maxPageItems));
		HntbhzziduancfgView hbfield = queryService.gethntcfgfield(shebeibianhao);
		HntbhzziduancfgView hbisshow = queryService.gethntcfgisShow(shebeibianhao);		
		setHntbhzField(hbfield);
		setHntbhzisShow(hbisshow);
		
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
			headerList.add("拌和站名称");
			if (StringUtil.Null2Blank(hbisshow.getGongchengmingcheng()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getGongchengmingcheng());
			}
			if (StringUtil.Null2Blank(hbisshow.getSigongdidian()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getSigongdidian());
			}
			if (StringUtil.Null2Blank(hbisshow.getJiaozuobuwei()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getJiaozuobuwei());
			}
			if (StringUtil.Null2Blank(hbisshow.getChuliaoshijian()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getChuliaoshijian());
			}
			if (StringUtil.Null2Blank(hbisshow.getBaocunshijian()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getBaocunshijian());
			}
			headerList.add(hbfield.getJiaobanshijian());
			if (StringUtil.Null2Blank(hbisshow.getShui1_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getShui1_shijizhi());
			}
			if (StringUtil.Null2Blank(hbisshow.getShui2_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getShui2_shijizhi());
			}
				
			if (StringUtil.Null2Blank(hbisshow.getShuini1_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getShuini1_shijizhi());
			}
				
			if (StringUtil.Null2Blank(hbisshow.getShuini2_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getShuini2_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getSha1_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getSha1_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getShi1_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getShi1_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getShi2_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getShi2_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getSha2_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getSha2_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getGuliao5_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getGuliao5_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getKuangfen3_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getKuangfen3_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getFeimeihui4_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getFeimeihui4_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getFenliao5_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getFenliao5_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getFenliao6_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getFenliao6_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getWaijiaji1_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getWaijiaji1_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getWaijiaji2_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getWaijiaji2_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getWaijiaji3_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getWaijiaji3_shijizhi());
			}
			
			if (StringUtil.Null2Blank(hbisshow.getWaijiaji4_shijizhi()).equalsIgnoreCase("1")) {
				headerList.add(hbfield.getWaijiaji4_shijizhi());
			}		
			
			
			for (HunnintujieguoView  hnt: hunnintus.getDatas()) {
				StringBuilder databuilder = new StringBuilder();
				databuilder.append(hnt.getBanhezhanminchen());
				databuilder.append("&^&");
				if (StringUtil.Null2Blank(hbisshow.getGongchengmingcheng()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getGongchengmingcheng()));
					databuilder.append("&^&");	
				}
				if (StringUtil.Null2Blank(hbisshow.getSigongdidian()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getSigongdidian()));
					databuilder.append("&^&");	
				}
				if (StringUtil.Null2Blank(hbisshow.getJiaozuobuwei()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getJiaozuobuwei()));
					databuilder.append("&^&");	
				}
				if (StringUtil.Null2Blank(hbisshow.getChuliaoshijian()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getChuliaoshijian()));
					databuilder.append("&^&");	
				}
				if (StringUtil.Null2Blank(hbisshow.getBaocunshijian()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getBaocunshijian()));
					databuilder.append("&^&");	
				}
				databuilder.append(StringUtil.Null2Blank(hnt.getJiaobanshijian()));
				databuilder.append("&^&");	
				if (StringUtil.Null2Blank(hbisshow.getShui1_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getShui1_shijizhi()));
					databuilder.append("&^&");			
				}
				if (StringUtil.Null2Blank(hbisshow.getShui2_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getShui2_shijizhi()));
					databuilder.append("&^&");	
				}
				if (StringUtil.Null2Blank(hbisshow.getShuini1_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getShuini1_shijizhi()));
					databuilder.append("&^&");	
				}
				if (StringUtil.Null2Blank(hbisshow.getShuini2_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getShuini2_shijizhi()));
					databuilder.append("&^&");		
				}
				if (StringUtil.Null2Blank(hbisshow.getSha1_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getSha1_shijizhi()));
					databuilder.append("&^&");			
				}
				if (StringUtil.Null2Blank(hbisshow.getShi1_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getShi1_shijizhi()));
					databuilder.append("&^&");			
				}
				if (StringUtil.Null2Blank(hbisshow.getShi2_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getShi2_shijizhi()));
					databuilder.append("&^&");			
				}
				if (StringUtil.Null2Blank(hbisshow.getSha2_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getSha2_shijizhi()));
					databuilder.append("&^&");			
				}
				if (StringUtil.Null2Blank(hbisshow.getGuliao5_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getGuliao5_shijizhi()));
					databuilder.append("&^&");			
				}
				if (StringUtil.Null2Blank(hbisshow.getKuangfen3_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getKuangfen3_shijizhi()));
					databuilder.append("&^&");				
				}
				if (StringUtil.Null2Blank(hbisshow.getFeimeihui4_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getFeimeihui4_shijizhi()));
					databuilder.append("&^&");					
				}
				if (StringUtil.Null2Blank(hbisshow.getFenliao5_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getFenliao5_shijizhi()));
					databuilder.append("&^&");					
				}
				if (StringUtil.Null2Blank(hbisshow.getFenliao6_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getFenliao6_shijizhi()));
					databuilder.append("&^&");			
				}
				if (StringUtil.Null2Blank(hbisshow.getWaijiaji1_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji1_shijizhi()));
					databuilder.append("&^&");				
				}
				if (StringUtil.Null2Blank(hbisshow.getWaijiaji2_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji2_shijizhi()));
					databuilder.append("&^&");	
				}
				if (StringUtil.Null2Blank(hbisshow.getWaijiaji3_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji3_shijizhi()));
					databuilder.append("&^&");	
				}
				if (StringUtil.Null2Blank(hbisshow.getWaijiaji4_shijizhi()).equalsIgnoreCase("1")) {
					databuilder.append(StringUtil.Null2Blank(hnt.getWaijiaji4_shijizhi()));
				}
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
			HntExcelUtil.importjieguoListExcel(path+"excel/预警统计.xls", path+excelName,
					StringUtil.Null2Blank(bhzname), header, dataList);
			try {
				response.reset();
				response.setContentType("bin");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + excelName + "\"");
				java.io.FileInputStream in = new java.io.FileInputStream(path
						+ excelName);
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
