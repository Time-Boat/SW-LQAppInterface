package com.mss.shtoone.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class HntsclhsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2422818359966271113L;

	@Autowired
	private QueryService queryService;

	
	@Autowired
	private SystemService sysService;

	private List<HunnintuView> hntviews;

	private HntbhzziduancfgView hntbhzField;

	private String startTime;
	private String endTime;

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
	private String qiangdudengji;
	private String gongchengmingcheng;
	private String jiaozuobuwei;
	
	public String excelName;
	
	private String bhzname;
	
	private Integer biaoduan;
	private Integer xiangmubu;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Map<String, String> gcmclistmap;
	private Map<String, String> jzbwlistmap;
	private Map<String, String> qddjlistmap;

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	
	private Map<String, String> listmap;

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

	@Action("hntsclhs")
	public String hntsclhs() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		if (null == startTime && null == endTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		setEndTime(sdf.format(day.getTime()));
    	day.add(Calendar.DATE, -7);
    	setStartTime(sdf.format(day.getTime()));
		}
    	setHntviews(queryService.hntsclhslist(startTime, endTime, shebeibianhao,gongchengmingcheng,
    			jiaozuobuwei,qiangdudengji,biaoduan,xiangmubu,
    			StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request)));
		
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
		gcmclistmap = new LinkedHashMap<String, String>();
		List<Object> gdhtoplist = sysService.limitgetListbyField(request, "gongchengmingcheng", biaoduan, xiangmubu, shebeibianhao);
		for (int i = 0; i < gdhtoplist.size(); i++) {
			gcmclistmap.put(String.valueOf(gdhtoplist.get(i)), String
					.valueOf(gdhtoplist.get(i)));
		}

		jzbwlistmap = new LinkedHashMap<String, String>();
		List<Object> jzbwtoplist = sysService.limitgetListbyField(request, "jiaozuobuwei", biaoduan, xiangmubu, shebeibianhao);
		for (int i = 0; i < jzbwtoplist.size(); i++) {
			jzbwlistmap.put(String.valueOf(jzbwtoplist.get(i)), String
					.valueOf(jzbwtoplist.get(i)));
		}
		
		qddjlistmap = new LinkedHashMap<String, String>();
		List<Object> qddjlist = sysService.limitgetListbyField(request, "qiangdudengji", biaoduan, xiangmubu, shebeibianhao);
		for (int i = 0; i < qddjlist.size(); i++) {
			qddjlistmap.put(String.valueOf(qddjlist.get(i)), String
					.valueOf(qddjlist.get(i)));
		}
		
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")){
			List<String> dataList = new ArrayList<String>();
			if (null != hntviews && hntviews.size() > 0) {
				for (HunnintuView  hnt: hntviews) {
					StringBuilder databuilder = new StringBuilder();
					databuilder.append(hnt.getChuliaoshijian() + "&^&");
					databuilder.append(hnt.getGongchengmingcheng() + "&^&");
					databuilder.append(hnt.getSigongdidian() + "&^&");
					databuilder.append(hnt.getJiaozuobuwei() + "&^&");					
					databuilder.append(hnt.getQiangdudengji() + "&^&");					
					databuilder.append(hnt.getPeifanghao() + "&^&");
					databuilder.append(hnt.getGujifangshu());
					dataList.add(databuilder.toString());
				}
				}
				String path = request.getSession().getServletContext()
						.getRealPath("/");
				String excelName = "excel/" + System.currentTimeMillis()
						+ ".xls";
				setExcelName(excelName);
				File file = new File(path + "excel");
				if (!file.exists()) {
					file.mkdir();
				}				
				HntExcelUtil.importshengchanlExcel(path+"excel/生产量统计.xls", path + excelName, 
						StringUtil.Null2Blank(bhzname),startTime+"～"+endTime, dataList);
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

		return SUCCESS;
	}

	public List<HunnintuView> getHntviews() {
		return hntviews;
	}

	public void setHntviews(List<HunnintuView> hntviews) {
		this.hntviews = hntviews;
	}

	public String getBhzname() {
		return bhzname;
	}

	public void setBhzname(String bhzname) {
		this.bhzname = bhzname;
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

	public Map<String, String> getGcmclistmap() {
		return gcmclistmap;
	}

	public void setGcmclistmap(Map<String, String> gcmclistmap) {
		this.gcmclistmap = gcmclistmap;
	}

	public Map<String, String> getQddjlistmap() {
		return qddjlistmap;
	}

	public void setQddjlistmap(Map<String, String> qddjlistmap) {
		this.qddjlistmap = qddjlistmap;
	}

	public Map<String, String> getJzbwlistmap() {
		return jzbwlistmap;
	}

	public void setJzbwlistmap(Map<String, String> jzbwlistmap) {
		this.jzbwlistmap = jzbwlistmap;
	}

	public String getQiangdudengji() {
		return qiangdudengji;
	}

	public void setQiangdudengji(String qiangdudengji) {
		this.qiangdudengji = qiangdudengji;
	}

	public String getGongchengmingcheng() {
		return gongchengmingcheng;
	}

	public void setGongchengmingcheng(String gongchengmingcheng) {
		this.gongchengmingcheng = gongchengmingcheng;
	}

	public String getJiaozuobuwei() {
		return jiaozuobuwei;
	}

	public void setJiaozuobuwei(String jiaozuobuwei) {
		this.jiaozuobuwei = jiaozuobuwei;
	}
}
