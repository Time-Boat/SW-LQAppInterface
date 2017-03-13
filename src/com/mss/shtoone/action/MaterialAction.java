package com.mss.shtoone.action;

import java.text.SimpleDateFormat;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.mss.shtoone.domain.HunnintuView;
import com.mss.shtoone.domain.TophunnintuView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.GetdataService;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class MaterialAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2422818359966271113L;

	@Autowired
	private QueryService queryService;

	@Autowired
	private GetdataService getdataService;
	
	@Autowired
	private SystemService sysService;

	
	private HunnintuView hntviews;


	private HntbhzziduancfgView hntbhzField;



	private String startTime;
	private String endTime;

	private String jiaozhubuwei;
	
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Integer biaoduan;
	private Integer xiangmubu;


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
	private String gongchengmingcheng;
	private String xiangmubuminchen;





	
	public String excelName;


	public String getExcelName() {
		return excelName;
	}



	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	
	private Map<String, String> listmap;

	private Map<String, String> gcmcmap;

	
	private String testbhz;
	private String bhztext;





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


	public HntbhzziduancfgView getHntbhzField() {
		return hntbhzField;
	}

	public void setHntbhzField(HntbhzziduancfgView hntbhzField) {
		this.hntbhzField = hntbhzField;
	}

	

	@Action("material")
	public String material() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null == startTime && null == endTime) {
		Calendar day=Calendar.getInstance(); 
		setEndTime(sdf.format(day.getTime()));
    	day.add(Calendar.MONTH, -1);
    	setStartTime(sdf.format(day.getTime()));
		}

		if (null == request.getParameter("frommenu")) {
		setHntviews(queryService.materiallist(gongchengmingcheng, jiaozhubuwei, startTime, endTime, 
				testbhz,biaoduan,xiangmubu,StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request)));
		}
		HntbhzziduancfgView hbfield = queryService.gethntcfgfield(shebeibianhao);
		
    	if (null != hntviews) {
    		request.setAttribute("strXML", getdataService.gethntmaterialhsXml(hntviews, hbfield));
    	} else {
    		request.setAttribute("strXML","");
    	}
		setHntbhzField(hbfield);
		
/*		if (null != hunnintus && null != hunnintus.getDatas() && hunnintus.getDatas().size() > 0) {
			HunnintuView hv = hunnintus.getDatas().get(0);
			setSha1_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getSha1_shijizhi()) - Double.parseDouble(hv.getSha1_lilunzhi())));
			setSha2_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getSha2_shijizhi()) - Double.parseDouble(hv.getSha2_lilunzhi())));
			setShi1_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getShi1_shijizhi()) - Double.parseDouble(hv.getShi1_lilunzhi())));
			setShi2_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getShi2_shijizhi()) - Double.parseDouble(hv.getShi2_lilunzhi())));
			setShuini1_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getShuini1_shijizhi()) - Double.parseDouble(hv.getShuini1_lilunzhi())));
			setShuini2_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getShuini2_shijizhi()) - Double.parseDouble(hv.getShuini2_lilunzhi())));
			setKuangfen3_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getKuangfen3_shijizhi()) - Double.parseDouble(hv.getKuangfen3_lilunzhi())));
			setFeimeihui4_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getFeimeihui4_shijizhi()) - Double.parseDouble(hv.getFeimeihui4_lilunzhi())));
			setFenliao5_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getFenliao5_shijizhi()) - Double.parseDouble(hv.getFenliao5_lilunzhi())));
			setShui1_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getShui1_shijizhi()) - Double.parseDouble(hv.getShui1_lilunzhi())));
			setShui2_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getShui2_shijizhi()) - Double.parseDouble(hv.getShui2_lilunzhi())));
			setWaijiaji1_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getWaijiaji1_shijizhi()) - Double.parseDouble(hv.getWaijiaji1_lilunzhi())));
			setWaijiaji2_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getWaijiaji2_shijizhi()) - Double.parseDouble(hv.getWaijiaji2_lilunzhi())));
			setWaijiaji3_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getWaijiaji3_shijizhi()) - Double.parseDouble(hv.getWaijiaji3_lilunzhi())));
			setWaijiaji4_chazhi(String.format("%1$.2f",Double.parseDouble(hv.getWaijiaji4_shijizhi()) - Double.parseDouble(hv.getWaijiaji4_lilunzhi())));
		}*/
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
				
		gcmcmap = new LinkedHashMap<String, String>();
		List gdhtoplist = sysService.limitgetListbyMulField(request, "gongchengmingcheng", biaoduan, xiangmubu, testbhz);
		for (int i = 0; i < gdhtoplist.size(); i++) {
			gcmcmap.put(String.valueOf(gdhtoplist.get(i)), String
					.valueOf(gdhtoplist.get(i)));
		}

		jzbwlistmap = new LinkedHashMap<String, String>();
		List jzbwtoplist = sysService.limitgetListbyMulField(request, "jiaozuobuwei", biaoduan, xiangmubu, testbhz);
		for (int i = 0; i < jzbwtoplist.size(); i++) {
			jzbwlistmap.put(String.valueOf(jzbwtoplist.get(i)), String
					.valueOf(jzbwtoplist.get(i)));
		}
		
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123") && null != hbfield){
			List<String> dataList = new ArrayList<String>();
			List<String> headerList = new ArrayList<String>();
			if (null != hntviews) {
				headerList.add(hbfield.getShuini1_shijizhi());
				headerList.add(hbfield.getShuini2_shijizhi());
				headerList.add(hbfield.getSha1_shijizhi());
				headerList.add(hbfield.getShi1_shijizhi());
				headerList.add(hbfield.getShi2_shijizhi());
				headerList.add(hbfield.getSha2_shijizhi());
				headerList.add(hbfield.getKuangfen3_shijizhi());
				headerList.add(hbfield.getFeimeihui4_shijizhi());
				headerList.add(hbfield.getFenliao5_shijizhi());
				headerList.add(hbfield.getWaijiaji1_shijizhi());
				headerList.add(hbfield.getWaijiaji2_shijizhi());
				headerList.add(hbfield.getWaijiaji3_shijizhi());
				headerList.add(hbfield.getWaijiaji4_shijizhi());				
		
				StringBuilder databuilder = new StringBuilder();
				databuilder.append(hntviews.getShuini1_shijizhi() + "&^&");
				databuilder.append(hntviews.getShuini1_lilunzhi() + "&^&");
				databuilder.append(hntviews.getShuini1chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				databuilder.append(hntviews.getShuini2_shijizhi() + "&^&");
				databuilder.append(hntviews.getShuini2_lilunzhi() + "&^&");
				databuilder.append(hntviews.getShuini2chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				databuilder.append(hntviews.getSha1_shijizhi() + "&^&");
				databuilder.append(hntviews.getSha1_lilunzhi() + "&^&");
				databuilder.append(hntviews.getSha1chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				databuilder.append(hntviews.getShi1_shijizhi() + "&^&");
				databuilder.append(hntviews.getShi1_lilunzhi() + "&^&");
				databuilder.append(hntviews.getShi1chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				databuilder.append(hntviews.getShi2_shijizhi() + "&^&");
				databuilder.append(hntviews.getShi2_lilunzhi() + "&^&");
				databuilder.append(hntviews.getShi2chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				databuilder.append(hntviews.getSha2_shijizhi() + "&^&");
				databuilder.append(hntviews.getSha2_lilunzhi() + "&^&");
				databuilder.append(hntviews.getSha2chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				databuilder.append(hntviews.getKuangfen3_shijizhi() + "&^&");
				databuilder.append(hntviews.getKuangfen3_lilunzhi() + "&^&");
				databuilder.append(hntviews.getKuangfen3chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				databuilder.append(hntviews.getFeimeihui4_shijizhi() + "&^&");
				databuilder.append(hntviews.getFeimeihui4_lilunzhi() + "&^&");
				databuilder.append(hntviews.getFeimeihui4chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				databuilder.append(hntviews.getFenliao5_shijizhi() + "&^&");
				databuilder.append(hntviews.getFenliao5_lilunzhi() + "&^&");
				databuilder.append(hntviews.getFenliao5chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				databuilder.append(hntviews.getWaijiaji1_shijizhi() + "&^&");
				databuilder.append(hntviews.getWaijiaji1_lilunzhi() + "&^&");
				databuilder.append(hntviews.getWaijiaji1chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				databuilder.append(hntviews.getWaijiaji2_shijizhi() + "&^&");
				databuilder.append(hntviews.getWaijiaji2_lilunzhi() + "&^&");
				databuilder.append(hntviews.getWaijiaji2chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				databuilder.append(hntviews.getWaijiaji3_shijizhi() + "&^&");
				databuilder.append(hntviews.getWaijiaji3_lilunzhi() + "&^&");
				databuilder.append(hntviews.getWaijiaji3chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				databuilder.append(hntviews.getWaijiaji4_shijizhi() + "&^&");
				databuilder.append(hntviews.getWaijiaji4_lilunzhi() + "&^&");
				databuilder.append(hntviews.getWaijiaji4chazhi());
				dataList.add(databuilder.toString());
				databuilder.delete(0, databuilder.length());
				String path = request.getSession().getServletContext()
						.getRealPath("/");
				String excelName = "excel/" + System.currentTimeMillis()
						+ ".xls";
				setExcelName(excelName);
				File file = new File(path + "excel");
				if (!file.exists()) {
					file.mkdir();
				}
				
				String[] header = new String[headerList.size()];
				int j = 0;
				for (Iterator iterator = headerList.iterator(); iterator
						.hasNext();) {
					header[j] = StringUtil.Null2Blank((String)iterator.next());
					j++;
				}
				if (null != gongchengmingcheng && gongchengmingcheng.length() > 0) {
					HntExcelUtil.importdanweigongchenExcel(path+"excel/单位工程材料消耗表.xls", path + excelName, 
							startTime+"～"+endTime,
							header, dataList, gongchengmingcheng.trim());
				} else if (null != xiangmubuminchen && xiangmubuminchen.length() > 0) {
					HntExcelUtil.importxiangmubuExcel(path+"excel/项目部材料消耗表.xls", path + excelName, 
							startTime+"～"+endTime,
							header, dataList, xiangmubuminchen.trim());
				} else {
					HntExcelUtil.importListSumExcel(path+"excel/材料消耗表.xls", path + excelName, 
							StringUtil.Null2Blank(bhztext),startTime+"～"+endTime,
							header, dataList);
				}
				
				try {
					response.reset();
					response.setContentType("bin");
					response.setHeader("Content-Disposition",
							"attachment; filename=\"" + excelName + "\"");
					java.io.FileInputStream in = new java.io.FileInputStream(
							path + excelName);
					// response.flushBuffer();
					PrintWriter out = response.getWriter();
					int i;
					while ((i = in.read()) != -1) {
						out.write(i);
					}
					in.close();
			        out.flush();
			        out.close();
				} catch (Exception e) {
				}
			}
		}

		return SUCCESS;
	}

	

	public String getGongchengmingcheng() {
		return gongchengmingcheng;
	}

	public void setGongchengmingcheng(String gongchengmingcheng) {
		this.gongchengmingcheng = gongchengmingcheng;
	}

	public String getXiangmubuminchen() {
		return xiangmubuminchen;
	}

	public void setXiangmubuminchen(String xiangmubuminchen) {
		this.xiangmubuminchen = xiangmubuminchen;
	}

	public Map<String, String> getGcmcmap() {
		return gcmcmap;
	}

	public void setGcmcmap(Map<String, String> gcmcmap) {
		this.gcmcmap = gcmcmap;
	}

	private Map<String, String> jzbwlistmap;

	public String getTestbhz() {
		return testbhz;
	}

	public void setTestbhz(String testbhz) {
		this.testbhz = testbhz;
	}

	
	public String getBhztext() {
		return bhztext;
	}

	public void setBhztext(String bhztext) {
		this.bhztext = bhztext;
	}

	public HunnintuView getHntviews() {
		return hntviews;
	}

	public void setHntviews(HunnintuView hntviews) {
		this.hntviews = hntviews;
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

	public Map<String, String> getJzbwlistmap() {
		return jzbwlistmap;
	}

	public void setJzbwlistmap(Map<String, String> jzbwlistmap) {
		this.jzbwlistmap = jzbwlistmap;
	}
}
