package com.mss.shtoone.action;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.ShuiwenmanualphbView;
import com.mss.shtoone.domain.Shuiwenziduancfg;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.domain.WuchaIsShowData;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.domain.YezhuFile;
import com.mss.shtoone.service.GetdataService;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.ShuiwenxixxlilunService;
import com.mss.shtoone.service.SwViewService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.HntExcelUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
public class SwQueryphbAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5938798257809625260L;
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private SwViewService swViewService;
	
	@Autowired
	private ShuiwenxixxlilunService swllService;
	
	@Autowired
	private GetdataService dataService;

	private GenericPageMode swphb;
	
	private GenericPageMode swmanualphb;
	
	private Map<String, String> listmap;
	
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	
	private int maxPageItems;
	private Integer pageNo;
	
	private String shebeibianhao;

	private String bhzname;

	public String excelName;
	private Integer bsid;
	private String startTime;
	private String endTime;
	private Integer usertype;
	private Integer biaoduan;
	private Integer xiangmubu;
	private Integer swxxid;
	private Integer[] wuchaselect;
	private List<WuchaIsShowData> wuchalist;
	private ShuiwenziduancfgView swisShow;	
    private ShuiwenziduancfgView swField;
    private Integer chaobiaolx = 0;
    private Integer cllx=0;  //处理结果
    private String bianhao;
    private ShuiwenziduancfgView swziduancfglow;  //初级下线
    private ShuiwenziduancfgView swziduancfghigh;  //初级上线
    private ShuiwenziduancfgView swziduancfglow1;  //中级下线
    private ShuiwenziduancfgView swziduancfghigh1;  //中级上线
    
    private ShuiwenziduancfgView swziduancfglow3;  //中级负值下线
    private ShuiwenziduancfgView swziduancfghigh3;  //中级负值上线
    private ShuiwenziduancfgView swziduancfglow2;   //高级下线
    private ShuiwenziduancfgView swziduancfghigh2;  //高级上线
    private List<YezhuFile> yezhuFileList;
    
    private Integer id;//业主上传文件ID
    
    
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ShuiwenziduancfgView getSwziduancfglow3() {
		return swziduancfglow3;
	}

	public void setSwziduancfglow3(ShuiwenziduancfgView swziduancfglow3) {
		this.swziduancfglow3 = swziduancfglow3;
	}

	public ShuiwenziduancfgView getSwziduancfghigh3() {
		return swziduancfghigh3;
	}

	public void setSwziduancfghigh3(ShuiwenziduancfgView swziduancfghigh3) {
		this.swziduancfghigh3 = swziduancfghigh3;
	}

	public List<YezhuFile> getYezhuFileList() {
		return yezhuFileList;
	}

	public void setYezhuFileList(List<YezhuFile> yezhuFileList) {
		this.yezhuFileList = yezhuFileList;
	}

	public ShuiwenziduancfgView getSwziduancfglow() {
		return swziduancfglow;
	}

	public void setSwziduancfglow(ShuiwenziduancfgView swziduancfglow) {
		this.swziduancfglow = swziduancfglow;
	}

	public ShuiwenziduancfgView getSwziduancfghigh() {
		return swziduancfghigh;
	}

	public void setSwziduancfghigh(ShuiwenziduancfgView swziduancfghigh) {
		this.swziduancfghigh = swziduancfghigh;
	}

	public ShuiwenziduancfgView getSwziduancfglow1() {
		return swziduancfglow1;
	}

	public void setSwziduancfglow1(ShuiwenziduancfgView swziduancfglow1) {
		this.swziduancfglow1 = swziduancfglow1;
	}

	public ShuiwenziduancfgView getSwziduancfghigh1() {
		return swziduancfghigh1;
	}

	public void setSwziduancfghigh1(ShuiwenziduancfgView swziduancfghigh1) {
		this.swziduancfghigh1 = swziduancfghigh1;
	}

	public ShuiwenziduancfgView getSwziduancfglow2() {
		return swziduancfglow2;
	}

	public void setSwziduancfglow2(ShuiwenziduancfgView swziduancfglow2) {
		this.swziduancfglow2 = swziduancfglow2;
	}

	public ShuiwenziduancfgView getSwziduancfghigh2() {
		return swziduancfghigh2;
	}

	public void setSwziduancfghigh2(ShuiwenziduancfgView swziduancfghigh2) {
		this.swziduancfghigh2 = swziduancfghigh2;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getBsid() {
		return bsid;
	}

	public void setBsid(Integer bsid) {
		this.bsid = bsid;
	}

	public String getBianhao() {
		return bianhao;
	}

	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}

	public Integer getSwxxid() {
		return swxxid;
	}

	public void setSwxxid(Integer swxxid) {
		this.swxxid = swxxid;
	}

	public Integer getCllx() {
		return cllx;
	}

	public void setCllx(Integer cllx) {
		this.cllx = cllx;
	}

	public Integer getChaobiaolx() {
		return chaobiaolx;
	}

	public void setChaobiaolx(Integer chaobiaolx) {
		this.chaobiaolx = chaobiaolx;
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
	
	
	public GenericPageMode getSwphb() {
		return swphb;
	}
	public void setSwphb(GenericPageMode swphb) {
		this.swphb = swphb;
	}
	public GenericPageMode getSwmanualphb() {
		return swmanualphb;
	}
	public void setSwmanualphb(GenericPageMode swmanualphb) {
		this.swmanualphb = swmanualphb;
	}
	public Map<String, String> getListmap() {
		return listmap;
	}
	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
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

	public int getMaxPageItems() {
		return maxPageItems;
	}
	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	
	public String getShebeibianhao() {
		return shebeibianhao;
	}
	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}
	public String getBhzname() {
		return bhzname;
	}
	public void setBhzname(String bhzname) {
		this.bhzname = bhzname;
	}
	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
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
	
	public ShuiwenziduancfgView getSwisShow() {
		return swisShow;
	}

	public void setSwisShow(ShuiwenziduancfgView swisShow) {
		this.swisShow = swisShow;
	}

	public ShuiwenziduancfgView getSwField() {
		return swField;
	}

	public void setSwField(ShuiwenziduancfgView swField) {
		this.swField = swField;
	}

	@Action("swphblist")
	public String swphblist() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		
		setPageNo(1);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length()>0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length()>0) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if(StringUtil.Null2Blank(request.getParameter("startTime")).length()>0){
			setStartTime(request.getParameter("startTime"));
		}
		if(StringUtil.Null2Blank(request.getParameter("endTime")).length()>0){
			setEndTime(request.getParameter("endTime"));
		}
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (StringUtil.Null2Blank(request.getParameter("xiangmubu")).length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		if(StringUtil.Null2Blank(request.getParameter("bianhao")).length()>0){
			setBianhao(request.getParameter("bianhao"));
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
		List<TopRealMaxShuiwenxixxView> toplist = sysService.limitswlist(request, biaoduan, xiangmubu);
		for (TopRealMaxShuiwenxixxView topsw : toplist) {
			listmap.put(topsw.getShebeibianhao(), topsw.getBanhezhanminchen());
		}
		
		setSwisShow(queryService.getSwcfgisShow(shebeibianhao));
		setSwField(queryService.getSwfield(shebeibianhao));
		ShuiwenziduancfgView swzstisShow=queryService.getSwzstcfgisShow(shebeibianhao);
		
		setSwmanualphb(swViewService.swwuchamanualphbviewlist(shebeibianhao, 
				startTime, endTime,  biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		
		if (null != swmanualphb.getDatas() && swmanualphb.getDatas().size() > 0) {
			request.setAttribute("strSwphbXML", queryService.getSwphbwcXml((List<ShuiwenmanualphbView>)swmanualphb.getDatas(),swField,swzstisShow));
		} else {
			request.setAttribute("strSwphbXML", "");
		}
		
		if(null != swmanualphb.getDatas() && swmanualphb.getDatas().size() > 0){
			request.setAttribute("strSwmanualphbXML", queryService.getSwmanualphbwcXml(swmanualphb.getDatas(),swField,swzstisShow));
		} else {
			request.setAttribute("strSwmanualphbXML", "");
		}
		
		String chaxun = request.getParameter("chaxun");
		String xiazai = request.getParameter("xiazai");
		
		if(null==chaxun && null!=xiazai && xiazai.equals("123")
				&& null != this.getSwField() && null != this.getSwisShow()){
				List<String> dataList=new ArrayList<String>();
				List<String> headerList = new ArrayList<String>();				
				
				headerList.add("拌和站名称");
				if(StringUtil.Null2Blank(this.getSwisShow().getShijian()).equalsIgnoreCase("1")){
					headerList.add(StringUtil.Null2Blank(this.getSwField().getShijian()));
				}
				if(StringUtil.Null2Blank(swisShow.getSjgl1()).equalsIgnoreCase("1")){
					headerList.add(StringUtil.Null2Blank(swField.getSjgl1()));
				}
				if(StringUtil.Null2Blank(swisShow.getSjgl2()).equalsIgnoreCase("1")){
					headerList.add(StringUtil.Null2Blank(swField.getSjgl2()));
				}
				if(StringUtil.Null2Blank(swisShow.getSjgl3()).equalsIgnoreCase("1")){
					headerList.add(StringUtil.Null2Blank(swField.getSjgl3()));
				}
				if(StringUtil.Null2Blank(swisShow.getSjgl4()).equalsIgnoreCase("1")){
					headerList.add(StringUtil.Null2Blank(swField.getSjgl4()));
				}
				if(StringUtil.Null2Blank(swisShow.getSjgl5()).equalsIgnoreCase("1")){
					headerList.add(StringUtil.Null2Blank(swField.getSjgl5()));
				}
				if(StringUtil.Null2Blank(swisShow.getSjfl1()).equalsIgnoreCase("1")){
					headerList.add(StringUtil.Null2Blank(swField.getSjfl1()));
				}
				if(StringUtil.Null2Blank(swisShow.getSjfl2()).equalsIgnoreCase("1")){
					headerList.add(StringUtil.Null2Blank(swField.getSjfl2()));
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
					header[j] = (String) iterator.next();
					j++;
				}
				
				if (null != swmanualphb && null != swmanualphb.getDatas() && swmanualphb.getDatas().size() > 0) {
					for (ShuiwenmanualphbView  swmanualphbObj: (List<ShuiwenmanualphbView>)swmanualphb.getDatas()) {
						StringBuilder databuilder = new StringBuilder();
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getBanhezhanminchen()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getShijian()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getSjgl1()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getSjgl2()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getSjgl3()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getSjgl4()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getSjfl1()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getPersjgl1()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getPersjgl2()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getPersjgl3()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getPersjgl4()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getPersjfl1()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getLlg1()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getLlg2()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getLlg3()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getLlg4()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getLlf1()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getManualwgl1()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getManualwgl2()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getManualwgl3()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getManualwgl4()) + "&^&");
						databuilder.append(StringUtil.Null2Blank(swmanualphbObj.getManualwfl1()) + "&^&");
						dataList.add(databuilder.toString());
					}
				}
				HntExcelUtil.importShuiWenPhbWCListExcel(path+"excel/水稳配比误差分析.xls", path+excelName, StringUtil.Null2Blank(bhzname), header, dataList);
				try {
					response.reset();
					response.setContentType("bin");
					response.setHeader("Content-Disposition",
							"attachment; filename=\"" + excelName + "\"");
					java.io.FileInputStream in = new java.io.FileInputStream(path
							+ excelName);
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
	
	
	@Action("shuiwenchaobiaolist")
	public String shuiwenchaobiaolist() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);

		setPageNo(1);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length() > 0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length() > 0) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}

		if (StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length() > 0) {
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		if (StringUtil.Null2Blank(request.getParameter("startTime")).length() > 0) {
			setStartTime(request.getParameter("startTime"));
		}
		if (StringUtil.Null2Blank(request.getParameter("endTime")).length() > 0) {
			setEndTime(request.getParameter("endTime"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		//如果是从首页链接进来的话，那么就进入这里
		if(StringUtil.Null2Blank(request.getParameter("method")).equalsIgnoreCase("swlist")){
			if (null == startTime && null == endTime) {
				Calendar day=Calendar.getInstance(); 
				setEndTime(sdf.format(day.getTime()));
				day.add(Calendar.DATE, -2);
				setStartTime(sdf.format(day.getTime()));
			}
		}
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length() > 0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (StringUtil.Null2Blank(request.getParameter("xiangmubu")).length() > 0) {
			setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		if (StringUtil.Null2Blank(request.getParameter("chaobiaolx")).length() > 0) {
			setChaobiaolx(Integer.parseInt(request.getParameter("chaobiaolx")));
		}
		if(StringUtil.Null2Blank(request.getParameter("cllx")).length()>0){
			setCllx(Integer.parseInt(request.getParameter("cllx")));
		}
		if(StringUtil.Null2Blank(request.getParameter("bianhao")).length()>0){
			setBianhao(request.getParameter("bianhao"));
		}
		if (null != wuchaselect && wuchaselect.length > 0) {
			Shuiwenziduancfg swcfgisshow = queryService.getswcfg40(shebeibianhao);
			if (null == swcfgisshow) {
				swcfgisshow = new Shuiwenziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
					swcfgisshow.setGprsbianhao(shebeibianhao);
					swcfgisshow.setLeixing("41");
				} else {
					swcfgisshow.setGprsbianhao("all");
					swcfgisshow.setLeixing("40");
				}
			}

			List<Integer> selectlist = new ArrayList<Integer>();
			for (int h = 0; h < wuchaselect.length; h++) {
				selectlist.add(wuchaselect[h]);
			}
			if (selectlist.contains(1)) {
				swcfgisshow.setSjgl1("1");
			} else {
				swcfgisshow.setSjgl1("0");
			}
			if (selectlist.contains(2)) {
				swcfgisshow.setSjgl2("1");
			} else {
				swcfgisshow.setSjgl2("0");
			}
			if (selectlist.contains(3)) {
				swcfgisshow.setSjgl3("1");
			} else {
				swcfgisshow.setSjgl3("0");
			}
			if (selectlist.contains(4)) {
				swcfgisshow.setSjgl4("1");
			} else {
				swcfgisshow.setSjgl4("0");
			}
			if (selectlist.contains(5)) {
				swcfgisshow.setSjgl5("1");
			} else {
				swcfgisshow.setSjgl5("0");
			}
			if (selectlist.contains(6)) {
				swcfgisshow.setSjfl1("1");
			} else {
				swcfgisshow.setSjfl1("0");
			}
			if (selectlist.contains(7)) {
				swcfgisshow.setSjfl2("1");
			} else {
				swcfgisshow.setSjfl2("0");
			}
			if (selectlist.contains(8)) {
				swcfgisshow.setSjshui("1");
			} else {
				swcfgisshow.setSjshui("0");
			}
			if (selectlist.contains(9)) {
				swcfgisshow.setShijian("1");
			} else {
				swcfgisshow.setShijian("0");
			}
			if (selectlist.contains(10)) {
				swcfgisshow.setGlchangliang("1");
			} else {
				swcfgisshow.setGlchangliang("0");
			}
			queryService.saveswcfg(swcfgisshow);
		}

		ShuiwenziduancfgView swziduanfield = queryService.getSwfield(shebeibianhao);
		ShuiwenziduancfgView swisshow = queryService.getswcfgisShow40(shebeibianhao);
		if (null == swisshow) {
			Shuiwenziduancfg swcfgisshow = queryService.getswcfg40(shebeibianhao);
			if (null == swcfgisshow) {
				swcfgisshow = new Shuiwenziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
					swcfgisshow.setGprsbianhao(shebeibianhao);
					swcfgisshow.setLeixing("41");
				} else {
					swcfgisshow.setGprsbianhao("all");
					swcfgisshow.setLeixing("40");
				}
			}
			swcfgisshow.setSjgl1("1");
			swcfgisshow.setSjgl2("1");
			swcfgisshow.setSjgl3("1");
			swcfgisshow.setSjgl4("1");
			swcfgisshow.setSjgl5("1");
			swcfgisshow.setSjfl1("1");
			swcfgisshow.setSjfl2("1");
			swcfgisshow.setSjshui("1");
			swcfgisshow.setShijian("1");
			swcfgisshow.setGlchangliang("1");
			queryService.saveswcfg(swcfgisshow);
			swisshow = queryService.getswcfgisShow(shebeibianhao);
		}
		wuchalist = new ArrayList<WuchaIsShowData>();
		wuchaselect = new Integer[10];
		int i = 1;
		WuchaIsShowData wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl1()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl1());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl2()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl2());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl3()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl3());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl4()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl4());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl5()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl5());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjfl1()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjfl1());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjfl2()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjfl2());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjshui()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjshui());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getShijian()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getShijian());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getGlchangliang()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getGlchangliang());
		wuchalist.add(wd);

		setSwField(swziduanfield);
		setSwisShow(swisshow);

		setSwmanualphb(swViewService.swchaobiaomanualviewlist(shebeibianhao, startTime, endTime, biaoduan, xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request), StringUtil.getBiaoshiId(request), pageNo, maxPageItems, chaobiaolx, swisshow,cllx,bianhao));

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
		List<TopRealMaxShuiwenxixxView> toplist = sysService.limitswlist(request, biaoduan, xiangmubu);
		for (TopRealMaxShuiwenxixxView topsw : toplist) {
			listmap.put(topsw.getShebeibianhao(), topsw.getBanhezhanminchen());
		}

		return SUCCESS;
	}
	
	//首页的附属页面
	@Action("querylist")
	public String querylist(){
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		setUsertype(StringUtil.getUserType(request));
		setBsid(StringUtil.getBiaoshiId(request));
		if (1 == usertype) {
			if (null == biaoduan && bsid==0) {
				setBiaoduan(1);
			}
			if(bsid==0){
				setBsid(biaoduan);
			}
		}	
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.limitbdlist(request);
		for (Biaoduanxinxi bdxx : bdlist) {
			biaoduanlistmap.put(bdxx.getId(), bdxx.getBiaoduanminchen());
		}
		if (null!=bdlist && bdlist.size()>0 && null == biaoduan) {
			setBiaoduan(bdlist.get(0).getId());
		}
		request.setAttribute("strXMLList", dataService.getswchaobiaobhzXml(2,StringUtil.getQueryFieldNameByUserType(usertype),bsid));
		setSwField(queryService.getSwfield(shebeibianhao));
		setSwisShow(queryService.getswcfgisShow40(shebeibianhao));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//如果是从首页链接进来的话，那么就进入这里
		if (null == startTime && null == endTime) {
			Calendar day=Calendar.getInstance(); 
			setEndTime(sdf.format(day.getTime()));
			//System.out.println(endTime);
			day.add(Calendar.DATE, -3);
			setStartTime(sdf2.format(day.getTime()));
		}
		if (StringUtil.Null2Blank(request.getParameter("biaoduan")).length() > 0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length() > 0) {
			setShebeibianhao(request.getParameter("shebeibianhao"));
		}
		setPageNo(1);
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length() > 0) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(15);
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length() > 0) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		if (null != wuchaselect && wuchaselect.length > 0) {
			Shuiwenziduancfg swcfgisshow = queryService.getswcfg70(shebeibianhao);
			if (null == swcfgisshow) {
				swcfgisshow = new Shuiwenziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
					swcfgisshow.setGprsbianhao(shebeibianhao);
					swcfgisshow.setLeixing("71");
				} else {
					swcfgisshow.setGprsbianhao("all");
					swcfgisshow.setLeixing("70");
				}
			}

			List<Integer> selectlist = new ArrayList<Integer>();
			for (int h = 0; h < wuchaselect.length; h++) {
				selectlist.add(wuchaselect[h]);
			}
			if (selectlist.contains(1)) {
				swcfgisshow.setSjgl1("1");
			} else {
				swcfgisshow.setSjgl1("0");
			}
			if (selectlist.contains(2)) {
				swcfgisshow.setSjgl2("1");
			} else {
				swcfgisshow.setSjgl2("0");
			}
			if (selectlist.contains(3)) {
				swcfgisshow.setSjgl3("1");
			} else {
				swcfgisshow.setSjgl3("0");
			}
			if (selectlist.contains(4)) {
				swcfgisshow.setSjgl4("1");
			} else {
				swcfgisshow.setSjgl4("0");
			}
			if (selectlist.contains(5)) {
				swcfgisshow.setSjgl5("1");
			} else {
				swcfgisshow.setSjgl5("0");
			}
			if (selectlist.contains(6)) {
				swcfgisshow.setSjfl1("1");
			} else {
				swcfgisshow.setSjfl1("0");
			}
			if (selectlist.contains(7)) {
				swcfgisshow.setSjfl2("1");
			} else {
				swcfgisshow.setSjfl2("0");
			}
			if (selectlist.contains(8)) {
				swcfgisshow.setSjshui("1");
			} else {
				swcfgisshow.setSjshui("0");
			}
			if (selectlist.contains(9)) {
				swcfgisshow.setShijian("1");
			} else {
				swcfgisshow.setShijian("0");
			}
			if (selectlist.contains(10)) {
				swcfgisshow.setGlchangliang("1");
			} else {
				swcfgisshow.setGlchangliang("0");
			}
			queryService.saveswcfg(swcfgisshow);
		}

		ShuiwenziduancfgView swziduanfield = queryService.getSwfield(shebeibianhao);
		ShuiwenziduancfgView swisshow = queryService.getswcfgisShow70(shebeibianhao);
		if (null == swisshow) {
			Shuiwenziduancfg swcfgisshow = queryService.getswcfg70(shebeibianhao);
			if (null == swcfgisshow) {
				swcfgisshow = new Shuiwenziduancfg();
				if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
					swcfgisshow.setGprsbianhao(shebeibianhao);
					swcfgisshow.setLeixing("71");
				} else {
					swcfgisshow.setGprsbianhao("all");
					swcfgisshow.setLeixing("70");
				}
			}
			swcfgisshow.setSjgl1("1");
			swcfgisshow.setSjgl2("1");
			swcfgisshow.setSjgl3("1");
			swcfgisshow.setSjgl4("1");
			swcfgisshow.setSjgl5("1");
			swcfgisshow.setSjfl1("1");
			swcfgisshow.setSjfl2("1");
			swcfgisshow.setSjshui("1");
			swcfgisshow.setShijian("1");
			swcfgisshow.setGlchangliang("1");
			queryService.saveswcfg(swcfgisshow);
			swisshow = queryService.getswcfgisShow(shebeibianhao);
		}
		wuchalist = new ArrayList<WuchaIsShowData>();
		wuchaselect = new Integer[10];
		int i = 1;
		WuchaIsShowData wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl1()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl1());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl2()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl2());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl3()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl3());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl4()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl4());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjgl5()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjgl5());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjfl1()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjfl1());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjfl2()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjfl2());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getSjshui()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getSjshui());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getShijian()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getShijian());
		wuchalist.add(wd);

		wd = new WuchaIsShowData();
		if (StringUtil.Null2Blank(swisshow.getGlchangliang()).equalsIgnoreCase("1")) {
			wuchaselect[i - 1] = i;
		}
		wd.setId(i);
		i++;
		wd.setName(swziduanfield.getGlchangliang());
		wuchalist.add(wd);

		setSwField(swziduanfield);
		setSwisShow(swisshow);
		//从预警配置中提取出对应拌和机的上下线
		setSwziduancfglow(sysService.swsmslowViewfindBybh(shebeibianhao));
		setSwziduancfghigh(sysService.swsmshighViewfindBybh(shebeibianhao));
		setSwziduancfglow1(sysService.swsmslowViewfindBybh2(shebeibianhao));
		setSwziduancfghigh1(sysService.swsmshighViewfindBybh2(shebeibianhao));
		//负值
		setSwziduancfglow3(sysService.swsmslowViewfindBybh4(shebeibianhao));
		setSwziduancfghigh3(sysService.swsmshighViewfindBybh4(shebeibianhao));
		
		setSwziduancfglow2(sysService.swsmslowfindBybh3View(shebeibianhao));
		setSwziduancfghigh2(sysService.swsmshighfindBybh3View(shebeibianhao));

		setSwmanualphb(swViewService.swchaobiaomanualviewlist(shebeibianhao, startTime, endTime, biaoduan, xiangmubu,
				StringUtil.getQueryFieldNameByRequest(request), StringUtil.getBiaoshiId(request), pageNo, maxPageItems, chaobiaolx, swisShow,cllx,bianhao));
		return SUCCESS;
	}
	
	@Action("yezhuFilelist")
	public String yezhuFilelist(){
		//业主上传的文件列表
		setYezhuFileList(sysService.getYezhuFile());
		return SUCCESS;
	}
	//删除业主文件
	@Action(value = "swxiafawenjianDel", results = @Result(name = "yezhuFilelist", type = "redirect", location = "yezhuFilelist?pid=0&record=34&"))
	public String swxiafawenjianDel(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("id")).length()>0){
			setId(Integer.parseInt(request.getParameter("id")));
		}
		if(id>0){
			sysService.del(id);
		}
		return "yezhuFilelist";
	}
	
}
