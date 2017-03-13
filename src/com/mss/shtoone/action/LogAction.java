package com.mss.shtoone.action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/system")
public class LogAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4072094238980896906L;
	
	//private static final String df="e:\\document\\MyWork\\质量监控系统\\贵广项目服务端\\";
	
	private List<String> logs;
	private String startTime;
	private String endTime;

	@Action("loglist")
	public String loglist(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		if (null == startTime && null == endTime) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar day=Calendar.getInstance(); 
			setEndTime(sdf.format(day.getTime()));
	    	day.add(Calendar.DATE, -1);
	    	setStartTime(sdf.format(day.getTime()));
		}	
		
		String logpath = getLogpath();
		if (logpath.length() > 0) {
			setLogs(StringUtil.getLogs(logpath, startTime, endTime));
			String chaxun = request.getParameter("chaxun");
			String xiazai = request.getParameter("xiazai");
			
			if(null==chaxun && null!=xiazai && xiazai.equals("123")){
				try {
				String filename = logpath+"log.txt";
				BufferedReader br = null;
				File file = new File(filename);
				if(file.isFile() && file.exists()){      
		            file.delete();      
		        }
				BufferedWriter bw = new BufferedWriter(new FileWriter(filename,true)); 
				for (String fn : logs) {
					br = new BufferedReader(new FileReader((logpath+fn)));
					int ch = 0; 
					while((ch = br.read()) != -1) {
						bw.write(ch); 
					}	
					br.close();
				}
				bw.flush();
				bw.close();
						response.reset();
						response.setContentType("bin");
						response.setHeader("Content-Disposition",
								"attachment; filename=log.txt");
						java.io.FileInputStream in = new java.io.FileInputStream(filename);
						PrintWriter out = response.getWriter();
						int i;
						while ((i = in.read()) != -1) {
							out.write(i);
						}
						in.close();
				        out.flush();
				        out.close();
				        file = new File(filename);
						if(file.isFile() && file.exists()){      
				            file.delete();      
				        }
				} catch (Exception e) {
				}
			}			
		}
		return SUCCESS;
	}
	
	@Action("logdownload")
	public String logdownload(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE); 
		String filename = request.getParameter("filename");
		if (StringUtil.Null2Blank(filename).length()>0) {
			try {
				response.reset();
				response.setContentType("bin");
				response.setHeader("Content-Disposition",
						"attachment; filename=\"" + filename + "\"");
				String logpath = getLogpath();
				if (logpath.length() > 0) {
					java.io.FileInputStream in = new java.io.FileInputStream(logpath + filename);
					PrintWriter out = response.getWriter();
					int i;
					while ((i = in.read()) != -1) {
						out.write(i);
					}
					in.close();
			        out.flush();
			        out.close();
				}
			} catch (Exception e) {
			}
		}		
		return null;
	}
	
	private String getLogpath() {
		 String fname=this.getClass().getResource("/").getPath()+"dbconf.ini";
		 Properties prop=new Properties();
		 File fp = new File(fname);
		 String logpath = "";
		 if(fp.exists()){
			 try {
				prop.load(new FileInputStream(fp));
				logpath = prop.getProperty("logpath", logpath);	
			} catch (FileNotFoundException e) {
			} catch (IOException e) {
			} 
		 }
		 return logpath;
	}


	public List<String> getLogs() {
		return logs;
	}

	public void setLogs(List<String> logs) {
		this.logs = logs;
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
	
}
