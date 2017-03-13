package com.mss.shtoone.action;

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

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.QueryUserTestService;
import com.mss.shtoone.service.SystemService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/query")
//试验数据查询
public class TestQueryAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8292014906269088884L;

	@Autowired
	private QueryService queryService;

	@Autowired
	private SystemService sysService;
	
	@Autowired
	private QueryUserTestService usertestService;
	
	private GenericPageMode usertestinfo;
	
	private GenericPageMode testQuerywddpgs;
	private GenericPageMode testQueryydpgs;
	
	private String startTime;
	private String endTime;

	private int maxPageItems;
	private Integer pageNo;	

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

	private Map<String, String> shebeilistmap;
	
	@Action("testwddquerylist")
	public String testwddquerylist() {
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
		
		setTestQuerywddpgs(queryService.viewwddlist(shebeibianhao,
				startTime, endTime, pageNo, maxPageItems));	
		shebeilistmap = new LinkedHashMap<String, String>();
		List shebeilist = sysService.getListbyField("Wendingdudata", "shebeibianhao");
		for (int i = 0; i < shebeilist.size(); i++) {
			shebeilistmap.put(String.valueOf(shebeilist.get(i)), String
					.valueOf(shebeilist.get(i)));
		}
		return SUCCESS;
	}
	
	@Action("testydquerylist")
	public String testydquerylist() {
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
		
		setTestQueryydpgs(queryService.viewydlist(shebeibianhao,
				startTime, endTime, pageNo, maxPageItems));	
		shebeilistmap = new LinkedHashMap<String, String>();
		List shebeilist = sysService.getListbyField("Yandudata", "shebeibianhao");
		for (int i = 0; i < shebeilist.size(); i++) {
			shebeilistmap.put(String.valueOf(shebeilist.get(i)), String
					.valueOf(shebeilist.get(i)));
		}
		return SUCCESS;
	}
	
	@Action("usertestinfolist")
	 public String usertestinfolist(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		
		setPageNo(1);
		if (null != request.getParameter("pageNo")) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(20);
		if (null != request.getParameter("maxPageItems")) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		setStartTime(request.getParameter("startTime"));
		setEndTime(request.getParameter("endTime"));
		setShebeibianhao(request.getParameter("shebeibianhao"));
		setUsertestinfo(usertestService.viewlist(startTime,endTime,shebeibianhao,pageNo,maxPageItems));
		shebeilistmap = new LinkedHashMap<String, String>();
		List shebeilist = sysService.getListbyField("UserTestView", "TestName");
		for (int i = 0; i < shebeilist.size(); i++) {
			shebeilistmap.put(String.valueOf(shebeilist.get(i)), String
					.valueOf(shebeilist.get(i)));
		}
		return SUCCESS; 
	 }
	
	@Action("viewtestReport")
	 public String viewtestReport(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		String testno = request.getParameter("testno");
		request.setAttribute("testno", testno);	
		String testdate = request.getParameter("testdate");
		request.setAttribute("testdate", testdate);	
		return SUCCESS; 
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

	public Map<String, String> getShebeilistmap() {
		return shebeilistmap;
	}

	public void setShebeilistmap(Map<String, String> shebeilistmap) {
		this.shebeilistmap = shebeilistmap;
	}

	public GenericPageMode getTestQuerywddpgs() {
		return testQuerywddpgs;
	}

	public void setTestQuerywddpgs(GenericPageMode testQuerywddpgs) {
		this.testQuerywddpgs = testQuerywddpgs;
	}

	public GenericPageMode getTestQueryydpgs() {
		return testQueryydpgs;
	}

	public void setTestQueryydpgs(GenericPageMode testQueryydpgs) {
		this.testQueryydpgs = testQueryydpgs;
	}

	public GenericPageMode getUsertestinfo() {
		return usertestinfo;
	}

	public void setUsertestinfo(GenericPageMode usertestinfo) {
		this.usertestinfo = usertestinfo;
	}
}
