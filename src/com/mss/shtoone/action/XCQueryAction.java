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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.Gpsdata;
import com.mss.shtoone.domain.TopChuliaokouTemperaturedataView;
import com.mss.shtoone.domain.TopRealGpsdataView;
import com.mss.shtoone.domain.TopRealSpeeddata1View;
import com.mss.shtoone.domain.TopRealSpeeddataView;
import com.mss.shtoone.domain.TopRealTemperaturedata1View;
import com.mss.shtoone.domain.TopRealTemperaturedataView;
import com.mss.shtoone.domain.TopRealTjjdataView;
import com.mss.shtoone.domain.XCData1;
import com.mss.shtoone.domain.XCData1PageMode;
import com.mss.shtoone.domain.XCData2;
import com.mss.shtoone.domain.XCData2PageMode;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
//现场摊铺数据查询
public class XCQueryAction extends ActionSupport {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6460090460098133790L;

	@Autowired
	private QueryService queryService;

	@Autowired
	private SystemService sysService;

	private XCData1PageMode xcDatatpgs;
	
	private XCData2PageMode xcDatatpgs1;
	
	private GenericPageMode xcDatatpgs3;
	
	private GenericPageMode xcQuerytmppgs;
	private GenericPageMode xcQueryspeedpgs;
	private GenericPageMode xcQuerytjjpgs;

	private XCData1 xcData1;
	
	private XCData2 xcData2;

	private String startTime;
	private String endTime;
	private String biaoDuanId;
	private Integer gprsDeviceId;

	private int maxPageItems;
	private Integer pageNo;
	public GenericPageMode getXcDatatpgs3() {
		return xcDatatpgs3;
	}

	public void setXcDatatpgs3(GenericPageMode xcDatatpgs3) {
		this.xcDatatpgs3 = xcDatatpgs3;
	}

	public int getMaxPageItems() {
		return maxPageItems;
	}

	public void setMaxPageItems(int maxPageItems) {
		this.maxPageItems = maxPageItems;
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
	
	private Integer biaoduan;
	private Integer xiangmubu;
	
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Map<String, String> shebeilistmap;
	
	private Map<String, String> biaoDuanIdlistmap;
	private Map<Integer, String> gprsDeviceIdlistmap;

	public XCData1 getXcData1() {
		return xcData1;
	}

	public void setXcData1(XCData1 xcData1) {
		this.xcData1 = xcData1;
	}

	public String getBiaoDuanId() {
		return biaoDuanId;
	}

	public void setBiaoDuanId(String biaoDuanId) {
		this.biaoDuanId = biaoDuanId;
	}

	public Integer getGprsDeviceId() {
		return gprsDeviceId;
	}

	public void setGprsDeviceId(Integer gprsDeviceId) {
		this.gprsDeviceId = gprsDeviceId;
	}

	public Map<String, String> getBiaoDuanIdlistmap() {
		return biaoDuanIdlistmap;
	}

	public void setBiaoDuanIdlistmap(Map<String, String> biaoDuanIdlistmap) {
		this.biaoDuanIdlistmap = biaoDuanIdlistmap;
	}

	public Map<Integer, String> getGprsDeviceIdlistmap() {
		return gprsDeviceIdlistmap;
	}

	public void setGprsDeviceIdlistmap(Map<Integer, String> gprsDeviceIdlistmap) {
		this.gprsDeviceIdlistmap = gprsDeviceIdlistmap;
	}

	public XCData2 getXcData2() {
		return xcData2;
	}

	public void setXcData2(XCData2 xcData2) {
		this.xcData2 = xcData2;
	}
	
	@Action("xcquerylist")
	public String xcquerylist() {
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

		setShebeibianhao(request.getParameter("shebeibianhao"));
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		setXcQueryspeedpgs(queryService.viewspeedlist(shebeibianhao,
				startTime, endTime, biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		setXcQuerytmppgs(queryService.viewtmplist(shebeibianhao,
				startTime, endTime, biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));		
		if (null != xcQueryspeedpgs && null != xcQuerytmppgs) {
			request.setAttribute("strXML", queryService.getSpeedandTmpXml(xcQuerytmppgs.getDatas(), xcQueryspeedpgs.getDatas()));
		} else {
			request.setAttribute("strXML", "");
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
		
		shebeilistmap = new LinkedHashMap<String, String>();
		List<TopRealSpeeddataView> toplist = sysService.limitSpeedlist(request, biaoduan, xiangmubu);
		for (TopRealSpeeddataView topView : toplist) {
			shebeilistmap.put(topView.getGpsno(), topView.getBanhezhanminchen());
		}	
		List<TopRealTemperaturedataView> toptmplist = sysService.limitTemperaturelist(request, biaoduan, xiangmubu);
		for (TopRealTemperaturedataView topView : toptmplist) {
			shebeilistmap.put(topView.getTmpno(), topView.getBanhezhanminchen());
		}	
		return SUCCESS;
	}
	
	@Action("tjjlist")
	public String tjjlist() {
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

		setShebeibianhao(request.getParameter("shebeibianhao"));
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		setXcQuerytjjpgs(queryService.viewtjjlist(shebeibianhao,
				startTime, endTime, biaoduan, xiangmubu, 
				"all", 
				0,pageNo, maxPageItems));		
		if (null != xcQuerytjjpgs) {
			request.setAttribute("strXML", queryService.getTjjXml(xcQuerytjjpgs.getDatas()));
		} else {
			request.setAttribute("strXML", "");
		}

		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = sysService.bdlist();
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = sysService.xmblist(biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
		
		shebeilistmap = new LinkedHashMap<String, String>();
		List<TopRealTjjdataView> toptmplist = sysService.limitTjjlist(biaoduan, xiangmubu);
		for (TopRealTjjdataView topView : toptmplist) {
			shebeilistmap.put(topView.getTjjno(), topView.getBanhezhanminchen());
		}	
		/*List<TopRealTjjdataView> toptmplist = sysService.limitTjjlist(request, biaoduan, xiangmubu);
		for (TopRealTjjdataView topView : toptmplist) {
			shebeilistmap.put(topView.getTjjno(), topView.getBanhezhanminchen());
		}	*/
		return SUCCESS;
	}
	
	@Action("maintpjxcquery")
	public String maintpjxcquery() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			List<TopRealGpsdataView> toplist = sysService.limitTpSpeedlist(request);
			   	StringBuilder outstr = new StringBuilder();
	    		for (TopRealGpsdataView topView : toplist) {
	    			Gpsdata gps=sysService.getTpGpsdata(topView.getGpsno());
	    			if (null != gps) {
	    				outstr.append(topView.getId());
	    				outstr.append(",");
		    			outstr.append(Double.parseDouble(gps.getDonjin().substring(0,3))+Double.parseDouble(gps.getDonjin().substring(3))/60);
	    				outstr.append(",");
		    			outstr.append(Double.parseDouble(gps.getBeiwei().substring(0,2))+Double.parseDouble(gps.getBeiwei().substring(2))/60);	
		    			outstr.append(",");
		    			outstr.append(topView.getShijian());
		    			outstr.append(",");
		    			outstr.append(topView.getSudu());	    			
		    			TopRealTemperaturedataView tptmpdata = sysService.limitTpTemperaturedata(request, topView.getGpsno());
		    			if (null != tptmpdata) {
		    				outstr.append(",");
			    			outstr.append(tptmpdata.getTmpdata());
						} else {
							outstr.append(",0");
						}	
		    			outstr.append(",");
		    			outstr.append(topView.getJianchen()); 
		    			outstr.append("|");
					}
	    		}
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("mainyljxcquery")
	public String mainyljxcquery() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			List<TopRealGpsdataView> toplist = sysService.limitYlSpeedlist(request);
			StringBuilder outstr = new StringBuilder();
	    		for (TopRealGpsdataView topView : toplist) {
	    				outstr.append(topView.getId());
	    				outstr.append(",");
	    				outstr.append(Double.parseDouble(topView.getDonjin().substring(0,3))+Double.parseDouble(topView.getDonjin().substring(3))/60);
	    				outstr.append(",");
	    				outstr.append(Double.parseDouble(topView.getBeiwei().substring(0,2))+Double.parseDouble(topView.getBeiwei().substring(2))/60);
		    			outstr.append(",");
		    			outstr.append(topView.getShijian()); 
		    			outstr.append(",");
		    			outstr.append(topView.getSudu());
		    			outstr.append(",");		    			
		    			outstr.append(topView.getJianchen()); 
		    			outstr.append("|");	
	    		}
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("xcchangebhzTanputemp")
	public String xcchangebhzTanputemp() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String biaoduan = request.getParameter("biaoduan");
			Integer bd = null;
	        if(StringUtil.Null2Blank(biaoduan).length() > 0) {
	        	bd = Integer.valueOf(biaoduan);
	        }
	        StringBuilder outstr = new StringBuilder();
	    	List<TopRealTemperaturedataView> toptmplist = sysService.limitTemperaturelist(request, bd, null);
	    	for (TopRealTemperaturedataView tmpdata : toptmplist) {
	    		outstr.append(tmpdata.getTmpno());
	    		outstr.append(",");
	    		outstr.append(tmpdata.getBanhezhanminchen());
	    		outstr.append("|");	
	    	}	
	    	if (outstr.length() > 0) {
	    		outstr.deleteCharAt(outstr.length()-1);
	    		out.print(outstr.toString());
	    		out.flush();
			}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("xcchangebhzYalutemp")
	public String xcchangebhzYalutemp() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String biaoduan = request.getParameter("biaoduan");
			Integer bd = null;
	        if(StringUtil.Null2Blank(biaoduan).length() > 0) {
	        	bd = Integer.valueOf(biaoduan);
	        }
	        StringBuilder outstr = new StringBuilder();
	    	List<TopRealTemperaturedata1View> toptmplist = sysService.limitYaluTemperaturelist(request, bd, null);
	    	for (TopRealTemperaturedata1View tmpdata : toptmplist) {
	    		outstr.append(tmpdata.getTmpno());
	    		outstr.append(",");
	    		outstr.append(tmpdata.getBanhezhanminchen());
	    		outstr.append("|");	
	    	}	
	    	if (outstr.length() > 0) {
	    		outstr.deleteCharAt(outstr.length()-1);
	    		out.print(outstr.toString());
	    		out.flush();
			}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	
	@Action("xcchangebhzYaluspeed")
	public String xcchangebhzYaluspeed() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String biaoduan = request.getParameter("biaoduan");
			Integer bd = null;
	        if(StringUtil.Null2Blank(biaoduan).length() > 0) {
	        	bd = Integer.valueOf(biaoduan);
	        }
	        StringBuilder outstr = new StringBuilder();
	        List<TopRealSpeeddataView> toplist = sysService.limitSpeedlist(request, bd, null);			
	    	for (TopRealSpeeddataView speeddata : toplist) {
	    		outstr.append(speeddata.getGpsno());
	    		outstr.append(",");
	    		outstr.append(speeddata.getBanhezhanminchen());
	    		outstr.append("|");	    			
	    	}
	    	if (outstr.length() > 0) {
	    		outstr.deleteCharAt(outstr.length()-1);
	    		out.print(outstr.toString());
	    		out.flush();
			}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("xcchangebhzTanpuspeed")
	public String xcchangebhzTanpuspeed() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String biaoduan = request.getParameter("biaoduan");
			Integer bd = null;
	        if(StringUtil.Null2Blank(biaoduan).length() > 0) {
	        	bd = Integer.valueOf(biaoduan);
	        }
	        StringBuilder outstr = new StringBuilder();
	        List<TopRealSpeeddata1View> toplist = sysService.limitTanpuSpeedlist(request, bd, null);			
	    	for (TopRealSpeeddata1View speeddata : toplist) {
	    		outstr.append(speeddata.getGpsno());
	    		outstr.append(",");
	    		outstr.append(speeddata.getBanhezhanminchen());
	    		outstr.append("|");	    			
	    	}
	    	if (outstr.length() > 0) {
	    		outstr.deleteCharAt(outstr.length()-1);
	    		out.print(outstr.toString());
	    		out.flush();
			}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("xcxmbchangebhzTanputemp")
	public String xcxmbchangebhzTanputemp() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String xmbmc = request.getParameter("xmbmc");
			Integer xmb = null;
	        if(StringUtil.Null2Blank(xmbmc).length() > 0) {
	        	xmb = Integer.valueOf(xmbmc);
	        }
	        StringBuilder outstr = new StringBuilder();
    		List<TopRealTemperaturedataView> toptmplist = sysService.limitTemperaturelist(request, null, xmb);
    		for (TopRealTemperaturedataView tmpdata : toptmplist) {
    			outstr.append(tmpdata.getTmpno());
    			outstr.append(",");
    			outstr.append(tmpdata.getBanhezhanminchen());
    			outstr.append("|");	
    		}	
    		if (outstr.length() > 0) {
    			outstr.deleteCharAt(outstr.length()-1);
    			out.print(outstr.toString());
    			out.flush();
			}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("xcxmbchangebhzYalutemp")
	public String xcxmbchangebhzYalutemp() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String xmbmc = request.getParameter("xmbmc");
			Integer xmb = null;
	        if(StringUtil.Null2Blank(xmbmc).length() > 0) {
	        	xmb = Integer.valueOf(xmbmc);
	        }
	        StringBuilder outstr = new StringBuilder();
    		List<TopRealTemperaturedata1View> toptmplist = sysService.limitYaluTemperaturelist(request, null, xmb);
    		for (TopRealTemperaturedata1View tmpdata : toptmplist) {
    			outstr.append(tmpdata.getTmpno());
    			outstr.append(",");
    			outstr.append(tmpdata.getBanhezhanminchen());
    			outstr.append("|");	
    		}	
    		if (outstr.length() > 0) {
    			outstr.deleteCharAt(outstr.length()-1);
    			out.print(outstr.toString());
    			out.flush();
			}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("xcxmbchangebhzYaluspeed")
	public String xcxmbchangebhzYaluspeed() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String xmbmc = request.getParameter("xmbmc");
			Integer xmb = null;
	        if(StringUtil.Null2Blank(xmbmc).length() > 0) {
	        	xmb = Integer.valueOf(xmbmc);
	        }
	        StringBuilder outstr = new StringBuilder();
	        List<TopRealSpeeddataView> toplist = sysService.limitSpeedlist(request, null, xmb);			
    		for (TopRealSpeeddataView speeddata : toplist) {
    			outstr.append(speeddata.getGpsno());
    			outstr.append(",");
    			outstr.append(speeddata.getBanhezhanminchen());
    			outstr.append("|");	    			
    		}
    		if (outstr.length() > 0) {
    			outstr.deleteCharAt(outstr.length()-1);
    			out.print(outstr.toString());
    			out.flush();
			}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("xcxmbchangebhzTanpuspeed")
	public String xcxmbchangebhzTanpuspeed() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String xmbmc = request.getParameter("xmbmc");
			Integer xmb = null;
	        if(StringUtil.Null2Blank(xmbmc).length() > 0) {
	        	xmb = Integer.valueOf(xmbmc);
	        }
	        StringBuilder outstr = new StringBuilder();
	        List<TopRealSpeeddata1View> toplist = sysService.limitTanpuSpeedlist(request, null, xmb);			
    		for (TopRealSpeeddata1View speeddata : toplist) {
    			outstr.append(speeddata.getGpsno());
    			outstr.append(",");
    			outstr.append(speeddata.getBanhezhanminchen());
    			outstr.append("|");	    			
    		}
    		if (outstr.length() > 0) {
    			outstr.deleteCharAt(outstr.length()-1);
    			out.print(outstr.toString());
    			out.flush();
			}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("tjjchangebhz")
	public String tjjchangebhz() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String biaoduan = request.getParameter("biaoduan");
			Integer bd = null;
	        if(StringUtil.Null2Blank(biaoduan).length() > 0) {
	        	bd = Integer.valueOf(biaoduan);
	        }
	        List<TopRealTjjdataView> toplist = sysService.limitTjjlist(bd, null);			
	        	StringBuilder outstr = new StringBuilder();
	    		for (TopRealTjjdataView tjjdata : toplist) {
	    			outstr.append(tjjdata.getTjjno());
	    			outstr.append(",");
	    			outstr.append(tjjdata.getBanhezhanminchen());
	    			outstr.append("|");	    			
	    		}
	    		if (outstr.length() > 0) {
	    			outstr.deleteCharAt(outstr.length()-1);
	    			out.print(outstr.toString());
	    			out.flush();
				}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}
	
	@Action("xmbtjjchangebhz")
	public String xmbtjjchangebhz() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String xmbmc = request.getParameter("xmbmc");
			Integer xmb = null;
	        if(StringUtil.Null2Blank(xmbmc).length() > 0) {
	        	xmb = Integer.valueOf(xmbmc);
	        }
	        List<TopRealTjjdataView> toplist = sysService.limitTjjlist(null, xmb);			
        	StringBuilder outstr = new StringBuilder();
    		for (TopRealTjjdataView tjjdata : toplist) {
    			outstr.append(tjjdata.getTjjno());
    			outstr.append(",");
    			outstr.append(tjjdata.getBanhezhanminchen());
    			outstr.append("|");	    			
    		}
    		if (outstr.length() > 0) {
    			outstr.deleteCharAt(outstr.length()-1);
    			out.print(outstr.toString());
    			out.flush();
			}
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}

	@Action("xc1list")
	public String xc1list() {
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
		if (null != request.getParameter("gprsDeviceId")) {
			setGprsDeviceId(Integer.parseInt(request.getParameter("gprsDeviceId")));
		}
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		day.add(Calendar.DATE, -3);
		setStartTime(sdf.format(day.getTime()));
		if (null != request.getParameter("startTime")) {
			String startTimeOne = request.getParameter("startTime");
			try {
				if (sdf.parse(startTimeOne).after(day.getTime())) {
					setStartTime(request.getParameter("startTime"));
				}
			} catch (Exception e) {
			}
		}*/
		if (null != request.getParameter("startTime")) {
			setStartTime(request.getParameter("startTime"));
		}
		setEndTime(request.getParameter("endTime"));
		/*if (null != request.getParameter("biaoDuanId") && request.getParameter("biaoDuanId").length()>0) {
			setBiaoDuanId(String.valueOf(request.getParameter("biaoDuanId")));
		}*/
		gprsDeviceIdlistmap = new LinkedHashMap<Integer, String>();
		gprsDeviceIdlistmap.put(11, "一标1号摊铺机");//----摊铺机id
		gprsDeviceIdlistmap.put(12, "一标2号摊铺机");
		
		gprsDeviceIdlistmap.put(21, "二标1号摊铺机");//----摊铺机id
		gprsDeviceIdlistmap.put(22, "二标2号摊铺机");

		
		setXcDatatpgs(queryService.xcdata1viewlist(gprsDeviceId, startTime, endTime, biaoDuanId, pageNo, maxPageItems));
		return SUCCESS;
	}
	
	@Action("xc2list")
	public String xc2list() {
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
		if (null != request.getParameter("gprsDeviceId")) {
			setGprsDeviceId(Integer.parseInt(request.getParameter("gprsDeviceId")));
		}
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		day.add(Calendar.DATE, -3);
		setStartTime(sdf.format(day.getTime()));
		if (null != request.getParameter("startTime")) {
			String startTimeOne = request.getParameter("startTime");
			try {
				if (sdf.parse(startTimeOne).after(day.getTime())) {
					setStartTime(request.getParameter("startTime"));
				}
			} catch (Exception e) {
			}
		}*/
		if (null != request.getParameter("startTime")) {
			setStartTime(request.getParameter("startTime"));
		}
		setEndTime(request.getParameter("endTime"));
		/*if (null != request.getParameter("biaoDuanId") && request.getParameter("biaoDuanId").length()>0) {
			setBiaoDuanId(String.valueOf(request.getParameter("biaoDuanId")));
		}*/
		gprsDeviceIdlistmap = new LinkedHashMap<Integer, String>();
		gprsDeviceIdlistmap.put(11, "一标1号手持");//----摊铺机id
	
		gprsDeviceIdlistmap.put(21, "二标1号手持");//----摊铺机id
	
		setXcDatatpgs1(queryService.xcdata2viewlist(gprsDeviceId, startTime, endTime, biaoDuanId, pageNo, maxPageItems));
		return SUCCESS;
	}

	@Action("xc3list")
	public String xc3list() {
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


		setShebeibianhao(request.getParameter("shebeibianhao"));
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		

		shebeilistmap = new LinkedHashMap<String, String>();
		List<TopRealGpsdataView> toplist = sysService.limitGpslist(request);
		for (TopRealGpsdataView topView : toplist) {
			shebeilistmap.put(topView.getGpsno(), topView.getBanhezhanminchen());
		}		
		setXcDatatpgs3(queryService.xcdata3viewlist(shebeibianhao,
				startTime, endTime, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));
		return SUCCESS;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public XCData1PageMode getXcDatatpgs() {
		return xcDatatpgs;
	}

	public void setXcDatatpgs(XCData1PageMode xcDatatpgs) {
		this.xcDatatpgs = xcDatatpgs;
	}

	public XCData2PageMode getXcDatatpgs1() {
		return xcDatatpgs1;
	}

	public void setXcDatatpgs1(XCData2PageMode xcDatatpgs1) {
		this.xcDatatpgs1 = xcDatatpgs1;
	}

	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
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

	public Map<String, String> getShebeilistmap() {
		return shebeilistmap;
	}

	public void setShebeilistmap(Map<String, String> shebeilistmap) {
		this.shebeilistmap = shebeilistmap;
	}

	public GenericPageMode getXcQuerytmppgs() {
		return xcQuerytmppgs;
	}

	public void setXcQuerytmppgs(GenericPageMode xcQuerytmppgs) {
		this.xcQuerytmppgs = xcQuerytmppgs;
	}

	public GenericPageMode getXcQueryspeedpgs() {
		return xcQueryspeedpgs;
	}

	public void setXcQueryspeedpgs(GenericPageMode xcQueryspeedpgs) {
		this.xcQueryspeedpgs = xcQueryspeedpgs;
	}

	public GenericPageMode getXcQuerytjjpgs() {
		return xcQuerytjjpgs;
	}

	public void setXcQuerytjjpgs(GenericPageMode xcQuerytjjpgs) {
		this.xcQuerytjjpgs = xcQuerytjjpgs;
	}



	private GenericPageMode ccQuerytmppgs;
	
	public GenericPageMode getCcQuerytmppgs() {
		return ccQuerytmppgs;
	}

	public void setCcQuerytmppgs(GenericPageMode ccQuerytmppgs) {
		this.ccQuerytmppgs = ccQuerytmppgs;
	}
	
	@Action("ccquerylist")
	public String ccquerylist() {
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

		setShebeibianhao(request.getParameter("shebeibianhao"));
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		if (null != request.getParameter("biaoduan") && request.getParameter("biaoduan").length()>0) {
			setBiaoduan(Integer.valueOf(request.getParameter("biaoduan")));
		}
		if (null != request.getParameter("xiangmubu") && request.getParameter("xiangmubu").length()>0) {
		    setXiangmubu(Integer.valueOf(request.getParameter("xiangmubu")));
		}
		
		setCcQuerytmppgs(queryService.viewtemplist(shebeibianhao,
				startTime, endTime, biaoduan, xiangmubu, 
				StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request),pageNo, maxPageItems));	
		
		if ( null != ccQuerytmppgs) {
			request.setAttribute("strXML", queryService.getTempXml(ccQuerytmppgs.getDatas(),shebeibianhao));
		} else {
			request.setAttribute("strXML", "");
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
		
		shebeilistmap = new LinkedHashMap<String, String>();
		List<TopChuliaokouTemperaturedataView> toplist = sysService.chuliaokouTemperaturelist(request, biaoduan, xiangmubu);
		for (TopChuliaokouTemperaturedataView topView : toplist) {
			shebeilistmap.put(topView.getTmpno(), topView.getBanhezhanminchen());
		}	
		return SUCCESS;
	}
	
	

}
