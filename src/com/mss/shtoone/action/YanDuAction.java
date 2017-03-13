package com.mss.shtoone.action;

import java.text.DecimalFormat;
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

import com.mss.shtoone.domain.T_YanDu;
import com.mss.shtoone.domain.T_YanDuPageMode;
import com.mss.shtoone.domain.Yandudata;
import com.mss.shtoone.service.QueryService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
//延度试验
public class YanDuAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6206608991077435723L;


	@Autowired
	private QueryService queryService;
	

	private T_YanDuPageMode t_YanDuPageMode;
	


	private T_YanDu t_YanDu;
	

	private String startTime;
	private String endTime;
	private String biaoDuanId;
	private String testNo;

	private int maxPageItems;
	private Integer pageNo;
	private Map<String, String> yanduIdlistmap1,yanduIdlistmap2,yanduIdlistmap3;
    private String testtime1,testtime2,testtime3;

	@Action("yandulist")
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
		if (null != request.getParameter("startTime")) {
			setStartTime(request.getParameter("startTime"));
		}
		setEndTime(request.getParameter("endTime"));
		
		setT_YanDuPageMode(queryService.viewlist(testNo, startTime, endTime, "", pageNo, maxPageItems));
		return SUCCESS;
	}
	//新建延度试验
	@Action(value = "yandunew", results = @Result(name = "yandulist", type = "redirect", location = "yandulist?pid=2&"))
	public String yandunew() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		yanduIdlistmap1 = new LinkedHashMap<String, String>();
		yanduIdlistmap2 = new LinkedHashMap<String, String>();
		yanduIdlistmap3 = new LinkedHashMap<String, String>();
		List<Yandudata> list = queryService.GetYanDuDataTimeList();
		for (Yandudata bd : list) {
			yanduIdlistmap1.put(bd.getDate_time(), bd.getDate_time());
			yanduIdlistmap2.put(bd.getDate_time(), bd.getDate_time());
			yanduIdlistmap3.put(bd.getDate_time(), bd.getDate_time());
		}
		if((null!=request.getParameter("isget"))&&(request.getParameter("isget").equals("1"))){//提取数据
			Yandudata yanDuData = new Yandudata();
			Double a = 0.0;
			Double b = 0.0;
			Double c = 0.0;
			DecimalFormat fd = new DecimalFormat("0.0"); 
			if(null!=testtime1&&!testtime1.endsWith("0")){
				yanDuData = queryService.getYanDuData(testtime1);
				t_YanDu.setWm16(yanDuData.getLength3_wd());
				t_YanDu.setWm19(yanDuData.getLength1());
				t_YanDu.setWm22(yanDuData.getLength2());
				t_YanDu.setWm25(yanDuData.getLength3());
				try {
					a = Double.parseDouble(yanDuData.getLength1());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					b = Double.parseDouble(yanDuData.getLength2());
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					c = Double.parseDouble(yanDuData.getLength3());
				} catch (Exception e) {
					// TODO: handle exception
				}	
				t_YanDu.setWm28(fd.format((a+b+c)/3));
			}else{
				t_YanDu.setWm16("");
				t_YanDu.setWm19("");
				t_YanDu.setWm22("");
				t_YanDu.setWm25("");
				t_YanDu.setWm28("");
			}
			
			if(null!=testtime2&&!testtime2.endsWith("0")){
				yanDuData = queryService.getYanDuData(testtime2);
				t_YanDu.setWm17(yanDuData.getLength3_wd());
				t_YanDu.setWm20(yanDuData.getLength1());
				t_YanDu.setWm23(yanDuData.getLength2());
				t_YanDu.setWm26(yanDuData.getLength3());
				a = Double.parseDouble(yanDuData.getLength1());
				b = Double.parseDouble(yanDuData.getLength2());
				c = Double.parseDouble(yanDuData.getLength3());
				t_YanDu.setWm29(fd.format((a+b+c)/3));
			}else{
				t_YanDu.setWm17("");
				t_YanDu.setWm20("");
				t_YanDu.setWm23("");
				t_YanDu.setWm26("");
				t_YanDu.setWm29("");
			}
			
			if(null!=testtime3&&!testtime3.endsWith("0")){
				yanDuData = queryService.getYanDuData(testtime3);
				t_YanDu.setWm18(yanDuData.getLength3_wd());
				t_YanDu.setWm21(yanDuData.getLength1());
				t_YanDu.setWm24(yanDuData.getLength2());
				t_YanDu.setWm27(yanDuData.getLength3());
				a = Double.parseDouble(yanDuData.getLength1());
				b = Double.parseDouble(yanDuData.getLength2());
				c = Double.parseDouble(yanDuData.getLength3());
				t_YanDu.setWm30(fd.format((a+b+c)/3));
			}else{
				t_YanDu.setWm18("");
				t_YanDu.setWm21("");
				t_YanDu.setWm24("");
				t_YanDu.setWm27("");
				t_YanDu.setWm30("");
			}
			return SUCCESS;
		}
		if((null!=request.getParameter("isedit"))&&(request.getParameter("isedit").equals("1"))){//编辑
			String testNo = request.getParameter("testNo");
			t_YanDu = queryService.getT_YanDu(testNo);
			return SUCCESS;
		}
		if((null!=request.getParameter("issave"))&&(request.getParameter("issave").equals("1"))){//保存
			queryService.saveOrUpdate(t_YanDu);
			return "yandulist";
		}else{//新建
			t_YanDu =new T_YanDu();
		}
		
		return SUCCESS;
	}
	@Action(value = "yandudel", results = @Result(name = "yandulist", type = "redirect", location = "yandulist?pid=2&"))
	 public String yandudel(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		String testNo = request.getParameter("testNo");
		queryService.deleteByKey(testNo);
		return "yandulist"; 
	 }

	public String getTestNo() {
		return testNo;
	}


	public void setTestNo(String testNo) {
		this.testNo = testNo;
	}


	public QueryService getQueryService() {
		return queryService;
	}


	public void setQueryService(QueryService queryService) {
		this.queryService = queryService;
	}


	public T_YanDuPageMode getT_YanDuPageMode() {
		return t_YanDuPageMode;
	}


	public void setT_YanDuPageMode(T_YanDuPageMode yanDuPageMode) {
		t_YanDuPageMode = yanDuPageMode;
	}


	public T_YanDu getT_YanDu() {
		return t_YanDu;
	}


	public void setT_YanDu(T_YanDu yanDu) {
		t_YanDu = yanDu;
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


	public String getBiaoDuanId() {
		return biaoDuanId;
	}


	public void setBiaoDuanId(String biaoDuanId) {
		this.biaoDuanId = biaoDuanId;
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
	public Map<String, String> getYanduIdlistmap1() {
		return yanduIdlistmap1;
	}
	public void setYanduIdlistmap1(Map<String, String> yanduIdlistmap1) {
		this.yanduIdlistmap1 = yanduIdlistmap1;
	}
	public Map<String, String> getYanduIdlistmap2() {
		return yanduIdlistmap2;
	}
	public void setYanduIdlistmap2(Map<String, String> yanduIdlistmap2) {
		this.yanduIdlistmap2 = yanduIdlistmap2;
	}
	public Map<String, String> getYanduIdlistmap3() {
		return yanduIdlistmap3;
	}
	public void setYanduIdlistmap3(Map<String, String> yanduIdlistmap3) {
		this.yanduIdlistmap3 = yanduIdlistmap3;
	}
	public String getTesttime1() {
		return testtime1;
	}
	public void setTesttime1(String testtime1) {
		this.testtime1 = testtime1;
	}
	public String getTesttime2() {
		return testtime2;
	}
	public void setTesttime2(String testtime2) {
		this.testtime2 = testtime2;
	}
	public String getTesttime3() {
		return testtime3;
	}
	public void setTesttime3(String testtime3) {
		this.testtime3 = testtime3;
	}






}
