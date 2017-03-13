package com.mss.shtoone.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;

import com.mss.shtoone.service.DatasubmitService;
import com.mss.shtoone.service.SysService;
import com.mss.shtoone.util.DIUtils;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/system")
public class SystemAction extends ActionSupport{
	
	static Log logger = LogFactory.getLog(SystemAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -8748741644548038332L;
	
	@Autowired
	private DatasubmitService submitservice;
	
	@Autowired
	private SysService sysService;
	
	private String smstype;
	private String sendtype;
	private String ambegin;
	private String amend;
	private String pmbegin;
	private String pmend;
	private String panshu;
	private String sendtime;
	private String frequency;
	private String onefrequency;
	private String apitype;
	private String meipan;
	private String huizong;
	private String lowsjysb;
	private String highsjysb;
	private String lowsjg1;
	private String highsjg1;
	private String lowwd;
	private String highwd;
	private String lowsd;
	private String highsd;
	private String manualmeipan;
	private Integer panduan=0;
	
	// 代表上传文件的File对象

	   private File upload;

	   // 上传文件名

	   private String uploadFileName;

	   // 上传文件的MIME类型

	   private String uploadContentType;

	   // 上传文件的描述信息

	   private String description;
	   
	   private String username;
	   
	   
	   

	
	public Integer getPanduan() {
		return panduan;
	}

	public void setPanduan(Integer panduan) {
		this.panduan = panduan;
	}

	@Action("systemmanager")
	public String systemmanager(){
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST); 
		String filename=request.getSession().getServletContext().getRealPath("/")+"WEB-INF"+File.separator
		+"classes"+File.separator+"system.ini";			
		Properties prop=new Properties();
		File fp = new File(filename);			
		if(!fp.exists()){
			try {
				fp.createNewFile();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		setPanduan(panduan);
		if(fp.exists()){
			try {
				prop.load(new FileInputStream(fp));
				if (smstype == null) {
					smstype = prop.getProperty("smstype", "0");		
				}
				if (ambegin == null) {
					ambegin = prop.getProperty("ambegin", "09:00");		
				}
				if (amend == null) {
					amend = prop.getProperty("amend", "12:00");			
				}
				if (pmbegin == null) {
					pmbegin = prop.getProperty("pmbegin", "14:00");			
				}
				if (pmend == null) {
					pmend = prop.getProperty("pmend", "17:00");	
				}
				if (sendtype == null) {
					sendtype = prop.getProperty("sendtype", "1");	
				}
				if (panshu == null) {
					panshu = prop.getProperty("panshu", "100");	
				}
				if (frequency == null) {
					frequency = prop.getProperty("frequency", "0");	
				}
				if (sendtime == null) {
					sendtime = prop.getProperty("sendtime", "");	
				}
				if (onefrequency == null) {
					onefrequency = prop.getProperty("onefrequency", "1");	
				}
				if (apitype == null) {
					apitype = prop.getProperty("apitype", "97");	
				}
/*				if (meipan == null) {
					meipan = prop.getProperty("meipan", "[%s]%s:%s实际值%s理论值%s已经%s%s%%");	
				}
				if (manualmeipan == null) {
					manualmeipan = prop.getProperty("manualmeipan", "[%s]%s:%s实际%s%%理论%s%%%s%s%%");	
				}*/
				if (huizong == null) {
					huizong = prop.getProperty("huizong", "[%s]%s:已搅拌盘数%s累计超标准次数%s");	
				}
				if (lowsjysb == null) {
					lowsjysb = prop.getProperty("lowsjysb", "0.92");	
				}
				if (highsjysb == null) {
					highsjysb = prop.getProperty("highsjysb", "1.06");	
				}
				if (lowsjg1 == null) {
					lowsjg1 = prop.getProperty("lowsjg1", "0.75");	
				}
				if (highsjg1 == null) {
					highsjg1 = prop.getProperty("highsjg1", "1.20");
				}
				if(lowwd == null){
					lowwd=prop.getProperty("lowwd", "0");
				}
				if(highwd == null){
					highwd=prop.getProperty("highwd", "0");
				}
				if(lowsd == null){
					lowsd=prop.getProperty("lowsd", "0");
				}
				if(highsd == null){
					highsd=prop.getProperty("highsd", "0");
				}
				FileOutputStream fos = new FileOutputStream(fp);
				prop.setProperty("smstype", smstype);
				prop.setProperty("ambegin", ambegin);
				prop.setProperty("amend", amend);
				prop.setProperty("pmbegin", pmbegin);
				prop.setProperty("pmend", pmend);
				prop.setProperty("sendtype", sendtype);
				prop.setProperty("panshu", panshu);
				prop.setProperty("frequency", frequency);
				prop.setProperty("sendtime", sendtime);
				prop.setProperty("onefrequency", onefrequency);
				prop.setProperty("apitype", apitype);
/*				prop.setProperty("meipan", meipan);
				prop.setProperty("manualmeipan", manualmeipan);*/
				prop.setProperty("huizong", huizong);
				prop.setProperty("lowsjysb", lowsjysb);
				prop.setProperty("highsjysb", highsjysb);
				prop.setProperty("lowsjg1", lowsjg1);
				prop.setProperty("highsjg1", highsjg1);
				prop.store(fos, "system");
				fos.close();
			} catch (FileNotFoundException e) {
				logger.error(e.getMessage());
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return SUCCESS;
	}
	
	//接收每日提交数据
	@Action("submiteveryday")
	public String submiteveryday() {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=gbk");  
        response.setHeader("Cache-Control", "no-cache");
        PrintWriter out;
        try {
        	if (StringUtil.Null2Blank(username).equalsIgnoreCase("shtoone") && null != upload) {
        		InputStream in = new FileInputStream(upload);
				byte[]  b1   =  new   byte[in.available()];       
		        in.read(b1,0,b1.length);
		        ByteArrayInputStream bout = new ByteArrayInputStream((StringUtil.decompressData(b1)));
		        DIUtils.initialize();
				Document doc = DIUtils.loadDocument(bout);
				out = response.getWriter();
	            out.print(submitservice.submit(doc.getDocumentElement()));
	            out.close();
			}
        }
        catch (Exception ex) {
        	ex.printStackTrace();
        }
		return null;
	}
	
	public String getAmbegin() {
		return ambegin;
	}

	public void setAmbegin(String ambegin) {
		this.ambegin = ambegin;
	}

	public String getAmend() {
		return amend;
	}

	public void setAmend(String amend) {
		this.amend = amend;
	}

	public String getPmbegin() {
		return pmbegin;
	}

	public void setPmbegin(String pmbegin) {
		this.pmbegin = pmbegin;
	}

	public String getPmend() {
		return pmend;
	}

	public void setPmend(String pmend) {
		this.pmend = pmend;
	}



	public String getSmstype() {
		return smstype;
	}



	public void setSmstype(String smstype) {
		this.smstype = smstype;
	}



	public String getSendtype() {
		return sendtype;
	}



	public void setSendtype(String sendtype) {
		this.sendtype = sendtype;
	}



	public String getPanshu() {
		return panshu;
	}



	public void setPanshu(String panshu) {
		this.panshu = panshu;
	}



	public String getSendtime() {
		return sendtime;
	}



	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}



	public String getFrequency() {
		return frequency;
	}



	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}



	public String getOnefrequency() {
		return onefrequency;
	}



	public void setOnefrequency(String onefrequency) {
		this.onefrequency = onefrequency;
	}



	public String getApitype() {
		return apitype;
	}



	public void setApitype(String apitype) {
		this.apitype = apitype;
	}



	public String getMeipan() {
		return meipan;
	}



	public void setMeipan(String meipan) {
		this.meipan = meipan;
	}



	public String getHuizong() {
		return huizong;
	}



	public void setHuizong(String huizong) {
		this.huizong = huizong;
	}



	public String getLowsjysb() {
		return lowsjysb;
	}



	public void setLowsjysb(String lowsjysb) {
		this.lowsjysb = lowsjysb;
	}



	public String getHighsjysb() {
		return highsjysb;
	}



	public void setHighsjysb(String highsjysb) {
		this.highsjysb = highsjysb;
	}



	public String getLowsjg1() {
		return lowsjg1;
	}



	public void setLowsjg1(String lowsjg1) {
		this.lowsjg1 = lowsjg1;
	}



	public String getHighsjg1() {
		return highsjg1;
	}



	public void setHighsjg1(String highsjg1) {
		this.highsjg1 = highsjg1;
	}
     
	


	public String getLowwd() {
		return lowwd;
	}

	public void setLowwd(String lowwd) {
		this.lowwd = lowwd;
	}

	public String getHighwd() {
		return highwd;
	}

	public void setHighwd(String highwd) {
		this.highwd = highwd;
	}

	public String getLowsd() {
		return lowsd;
	}

	public void setLowsd(String lowsd) {
		this.lowsd = lowsd;
	}

	public String getHighsd() {
		return highsd;
	}

	public void setHighsd(String highsd) {
		this.highsd = highsd;
	}

	public String getManualmeipan() {
		return manualmeipan;
	}



	public void setManualmeipan(String manualmeipan) {
		this.manualmeipan = manualmeipan;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
private String shebeibianhao;
	
	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}

	@Action("updateKehuduanbianhao")
	public String updateKehuduanbianhao() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse)context.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		JSONObject returnJSON=new JSONObject();
		try{
			if(StringUtil.Null2Blank(shebeibianhao)!=""){
				sysService.updateKehuduanbianhao(shebeibianhao);
				
				returnJSON.put("info", "success");
				try {
					 PrintWriter out = response.getWriter();
					 out.print(returnJSON.toString());
					 out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			try {
				returnJSON.put("info", "error");
				PrintWriter out = response.getWriter();
				out.print(returnJSON.toString());
				out.close();
			  } catch (IOException e1) {
				e1.printStackTrace();
			  } 
		}
		return null;
	}
	
}
