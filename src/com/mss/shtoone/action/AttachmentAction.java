package com.mss.shtoone.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Attachment;
import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.UserInfo;
import com.mss.shtoone.domain.YezhuFile;
import com.mss.shtoone.service.AttachmentService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.GetDate;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.*;
import java.net.URLDecoder;

/**
 * <p>Title: 上海同望工程质量控制系统</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: shtoone</p>
 *
 * @author not attributable
 * @version 1.0
 */

@Controller
@Namespace("/query")

public class AttachmentAction extends ActionSupport {
	private static Log logger = LogFactory.getLog(AttachmentAction.class);
	
	private static final long serialVersionUID = -3859708288861353077L;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private SystemService sysService;
	
	private List<Attachment> attachementList;//附件列表
	private Attachment attachmentpoObj;//上传附件对象
	private int bhzid;//拌和站编号
	private int attachmentid;//指定附件的id
	private Banhezhanxinxi bhzObj;//拌和站对象
	private String FileType;//附件类型
  	private String swxxid;//超标数据编号
  	private String lqxxid;
  	private List<File> fileName;//这里的"fileName"一定要与表单中的文件域名相同  
    private List<String> fileNameContentType;//格式同上"fileName"+ContentType  
    private List<String> fileNameFileName;//格式同上"fileName"+FileName
  	private File uploadFileid;
  	private String uploadFileidFileName;//文件名  uploadFile+FileName
  	private String uploadFileidContentType;//文件类型

	public String getSwxxid() {
		return swxxid;
	}

	public void setSwxxid(String swxxid) {
		this.swxxid = swxxid;
	}

	public List<File> getFileName() {
		return fileName;
	}

	public void setFileName(List<File> fileName) {
		this.fileName = fileName;
	}

	public List<String> getFileNameContentType() {
		return fileNameContentType;
	}

	public void setFileNameContentType(List<String> fileNameContentType) {
		this.fileNameContentType = fileNameContentType;
	}

	public List<String> getFileNameFileName() {
		return fileNameFileName;
	}

	public void setFileNameFileName(List<String> fileNameFileName) {
		this.fileNameFileName = fileNameFileName;
	}
    public Attachment getAttachmentpoObj() {
		return attachmentpoObj;
	}
    
	public void setAttachmentpoObj(Attachment attachmentpoObj) {
		this.attachmentpoObj = attachmentpoObj;
	}

	public int getAttachmentid() {
		return attachmentid;
	}

	public void setAttachmentid(int attachmentid) {
		this.attachmentid = attachmentid;
	}

	public Banhezhanxinxi getBhzObj() {
		return bhzObj;
	}

	public void setBhzObj(Banhezhanxinxi bhzObj) {
		this.bhzObj = bhzObj;
	}

	public String getFileType() {
		return FileType;
	}

	public void setFileType(String fileType) {
		FileType = fileType;
	}

	public List<Attachment> getAttachementList() {
		return attachementList;
	}

	public void setAttachementList(List<Attachment> attachementList) {
		this.attachementList = attachementList;
	}

	public int getBhzid() {
		return bhzid;
	}
	public void setBhzid(int bhzid) {
		this.bhzid = bhzid;
	}
	public File getUploadFileid() {
		return uploadFileid;
	}
	public void setUploadFileid(File uploadFileid) {
		this.uploadFileid = uploadFileid;
	}
	public String getUploadFileidFileName() {
		return uploadFileidFileName;
	}
	public void setUploadFileidFileName(String uploadFileidFileName) {
		this.uploadFileidFileName = uploadFileidFileName;
	}
	public String getUploadFileidContentType() {
		return uploadFileidContentType;
	}
	public void setUploadFileidContentType(String uploadFileidContentType) {
		this.uploadFileidContentType = uploadFileidContentType;
	}

	/**
	 *
	 * @param mapping the ActionMapping
	 * @param form the ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return the ActionForward
	 * @throws GenericException
	 * @todo Implement this com.shtoone.pub.framework.GenericAction method
   */
	@Action("attachmentList")
	public String attachmentList(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		setBhzObj(sysService.bhzfindById(request, bhzid)); //设置拌和站对象
		String where=" where 1=1 ";
		if((bhzid+"")!=null&&!(bhzid+"").equals("")){
			where += " and bhzid="+bhzid;
		}
		if(FileType!=null&&!FileType.equals("")){
			where += " and FileType='"+FileType+"'";
		}
		where += " order by caozuoriqi desc";
		setAttachementList(attachmentService.getAttachmentList(where)); //设置拌和站附件列表
		return SUCCESS;
	}
  
	@Action(value = "attachmentdel", results ={@Result(name = "list", type = "redirect", location = "attachmentList?pid=4&")}) 
	public String attachmentdel() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response= (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		try {
			//保存文件的物理根地址
			String savepath = request.getSession().getServletContext().getRealPath("/");
			Attachment att=attachmentService.getBean(attachmentid);//获取附件对象
			delFile(savepath+att.getUploadPath()+"/"+att.getFileName());
			attachmentService.delAttachment(attachmentid);
			response.getWriter().print("succ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		//return "list";
	}
  
	@Action(value="attachmentinput")
	public String attachmentinput(){
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		setBhzObj(sysService.bhzfindById(request, bhzid));//设置拌和站对象
		if (attachmentid > 0) {
			setAttachmentpoObj(attachmentService.getBean(attachmentid));
		}
		return SUCCESS;
	}
 
	@Action("attachmentUpload")
	public String attachmentUpload(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response= (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		  

		String bhzid=urlDecUtf8(request.getParameter("bhzid"));
		String bhzNo=urlDecUtf8(request.getParameter("bhzNo"));
		String bhzName=urlDecUtf8(request.getParameter("bhzName"));
//		String attachmentid=urlDecUtf8(request.getParameter("attachmentid"));
		String caozuoyuan=urlDecUtf8(request.getParameter("caozuoyuan"));
		String caozuoyuandianhua=urlDecUtf8(request.getParameter("caozuoyuandianhua"));
		String fileType=urlDecUtf8(request.getParameter("fileType"));
		String remark=urlDecUtf8(request.getParameter("remark"));

		response.setContentType("utf-8");
		//保存文件的物理根地址
		StringBuffer savepath = new StringBuffer(request.getSession().getServletContext().getRealPath("/"));
		savepath.append("\\"+"bhzAttachment"+"\\"+bhzNo);

		//保存数据库表中路径
		StringBuffer sqlsavepath = new StringBuffer("bhzAttachment"+"/"+bhzNo);
	
		boolean flag = false;
		if(uploadFileid!=null){
			//更改文件名称
			uploadFileidFileName=bhzName+"-"+System.currentTimeMillis()+uploadFileidFileName.substring(uploadFileidFileName.indexOf("."));
			//System.out.println("文件名："+uploadFileidFileName);
			//保存到文件物理地址各类型文件目录
			StringBuffer savepath_photofileid = new StringBuffer(savepath);
			savepath_photofileid.append("\\"+uploadFileidFileName);
			
			//System.out.println("完整地址："+savepath_photofileid);
			
			//保存数据库表中各类型路径
			//StringBuffer sqlsavepath_photofileid = new StringBuffer(sqlsavepath);
			//sqlsavepath_photofileid.append("/"+uploadFileidFileName);
			try {
				flag = savefile(uploadFileid, savepath_photofileid.toString());
				//System.out.println(flag);
			    if(flag){
			    	
			    	Attachment att=new Attachment();
			    	att.setBhzid(new Integer(bhzid));
			    	att.setBhzNo(bhzNo);
			    	att.setBhzName(bhzName);
			    	att.setFileName(uploadFileidFileName);
			    	att.setUploadPath(sqlsavepath.toString());
			    	att.setCaozuoyuan(caozuoyuan);
			    	att.setCaozuoyuandianhua(caozuoyuandianhua);
			    	att.setCaozuoriqi(GetDate.getNowTime("yyyy-MM-dd HH:mm:ss"));
			    	att.setFileType(fileType);
			    	att.setRemark(remark);
			    	
			    	attachmentService.addAttachment(att);
			    	//testservice.savefilepath(uploadtesterid,sqlsavepath_photofileid.toString(),"photofileid");
					response.getWriter().print("success");
			    }else{
				response.getWriter().print("fail");
			    }
			} catch (Exception e) {
			logger.error(e.getMessage());
			}
			
			return null;
		}
		
		return SUCCESS;	
	}
	
	
	public static String urlDecUtf8(String urlString){
		try {
			return URLDecoder.decode(urlString,"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "Error:"+e;
		}
	}
	
	
	/*
     * 保存上传文件到指定的路径
     *<param name="uploadfile">上传的文件</param>
     *<param name="savepath">保存文件路径</param>
     *<returns>是否保存成功</returns>
     */
    public static boolean savefile(File uploadfile,String savepath) throws Exception{
    	 File savefile = null;
    	 if (uploadfile != null){
    		 InputStream is = new FileInputStream(uploadfile);
			 savefile = new File(savepath.toString());  
			 if(!savefile.getParentFile().exists()){
				savefile.getParentFile().mkdir();
			 }
			 OutputStream os = new FileOutputStream(savefile); 
			//设置缓存   
		     byte[] buffer = new byte[1024];   
		  
		     int length = 0;   
		  
		     //读取myFile文件输出到savefile文件中   
		     while ((length = is.read(buffer)) > 0) {   
				    os.write(buffer, 0, length);   
			}
		     is.close();
		     os.close();
		     return true;
    	 }
		return false;
    }

	
    
    /**
     * 删除指定的文件或文件夹
     * @param dirPath 文件完整绝对路径
     */
    public static void delFile(String dirPath){
    	
    	///dirPath=dirPath.replaceAll("\\\\", "//");
    	System.out.println("dirPath:"+dirPath);
    	File dirFile;
    	boolean bFile;
    	bFile = false;
    	try{
    		dirFile = new File(dirPath);
    		bFile = dirFile.exists();
    		if( bFile == true ){
    			/*if(dirFile.isDirectory()){//如果指定文件为文件夹
    				System.out.println("文件存在！");
	    			System.gc();//垃圾回收 资源释放
    				delAllFile(dirPath);//如果要删除的文件为文件夹先删除该文件夹下的所有文件
        			System.gc();//垃圾回收 资源释放
	    			dirFile.delete();//删除指定的文件或文件夹
	    			System.gc();
    			}else{*/
    				System.gc();
    				dirFile.delete();//删除指定的文件或文件夹
	    			System.gc();
    			//}
    		}else{
    			System.out.println("文件不存在");
    		}
    		
    	}catch(Exception e){
    		System.out.println("删除"+dirPath+"文件失败！");
    	}
    }
    
    @Action(value="swchaobiaoAttachmentinput")
    public String swchaobiaoAttachmentinput(){
  	  return SUCCESS;
    }
    
    @Action(value="lqchaobiaoAttachmentinput")
    public String lqchaobiaoAttachmentinput(){
  	  return SUCCESS;
    }
    
    @Action(value = "swchaobiaoUpload")
    public String swchaobiaoUpload(){
    	ActionContext context = ActionContext.getContext();
    	HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response= (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		String xxid=urlDecUtf8(StringUtil.Null2Blank(request.getParameter("swxxid")));
		response.setContentType("utf-8");
		//保存文件的物理根地址
		StringBuffer savepath = new StringBuffer(request.getSession().getServletContext().getRealPath("/"));
		savepath.append("\\"+"hntChaobiaoAttachment");
		//保存数据库表中路径
		StringBuffer sqlsavepath = new StringBuffer("hntChaobiaoAttachment/");
		boolean flag = false;
		if(uploadFileid!=null){
			//更改文件名称
			uploadFileidFileName=xxid+"-"+System.currentTimeMillis()+uploadFileidFileName.substring(uploadFileidFileName.indexOf("."));
			//保存到文件物理地址各类型文件目录
			StringBuffer savepath_photofileid = new StringBuffer(savepath);
			savepath_photofileid.append("\\"+uploadFileidFileName);
			sqlsavepath.append(uploadFileidFileName);//数据库表中的名称
			try {
				flag = savefile(uploadFileid, savepath_photofileid.toString());
			    if(flag){
			    	if(null!=xxid&&!xxid.equals("")){
			    		sysService.updateSwclAttachment(new Integer(xxid),sqlsavepath.toString());
			    	}
					response.getWriter().print("success");
			    }else{
			    	response.getWriter().print("fail");
			    }
			} catch (Exception e) {
			logger.error(e.getMessage());
			}finally{
				
			}
			
			return null;
		}
		
		return SUCCESS;
    }
    
    @Action(value = "lqchaobiaoUpload")
    public String lqchaobiaoUpload(){
    	ActionContext context = ActionContext.getContext();
    	HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response= (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		String xxid=urlDecUtf8(StringUtil.Null2Blank(request.getParameter("lqxxid")));
		response.setContentType("utf-8");
		//保存文件的物理根地址
		StringBuffer savepath = new StringBuffer(request.getSession().getServletContext().getRealPath("/"));
		savepath.append("\\"+"hntChaobiaoAttachment");
		//保存数据库表中路径
		StringBuffer sqlsavepath = new StringBuffer("hntChaobiaoAttachment/");
		boolean flag = false;
		if(uploadFileid!=null){
			//更改文件名称
			uploadFileidFileName=xxid+"-"+System.currentTimeMillis()+uploadFileidFileName.substring(uploadFileidFileName.indexOf("."));
			//保存到文件物理地址各类型文件目录
			StringBuffer savepath_photofileid = new StringBuffer(savepath);
			savepath_photofileid.append("\\"+uploadFileidFileName);
			sqlsavepath.append(uploadFileidFileName);//数据库表中的名称
			try {
				flag = savefile(uploadFileid, savepath_photofileid.toString());
			    if(flag){
			    	if(null!=xxid&&!xxid.equals("")){
			    		sysService.updateLqclAttachment(new Integer(xxid),sqlsavepath.toString());
			    	}
					response.getWriter().print("success");
			    }else{
			    	response.getWriter().print("fail");
			    }
			} catch (Exception e) {
			logger.error(e.getMessage());
			}finally{
				
			}
			
			return null;
		}
		
		return SUCCESS;
    }
    
    @Action(value="yezhuFile")
    public String yezhuFile(){
  	  return SUCCESS;
    }
    
    @Action(value = "yezhuFileUpload")
    public String yezhuFileUpload(){
    	ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response= (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		HttpSession session =request.getSession();
		//得到提交的处理人
		SecurityContext sc = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
		Authentication auth = sc.getAuthentication();
		String userName=((UserInfo)auth.getPrincipal()).getUsername();
		response.setContentType("utf-8");
		//保存文件的物理根地址
		StringBuffer savepath = new StringBuffer(request.getSession().getServletContext().getRealPath("/"));
		savepath.append("\\"+"yezhuFile");
		String yezhuFileName="";
		//保存数据库表中路径
		StringBuffer sqlsavepath = new StringBuffer("yezhuFile/");
		boolean flag = false;
		if(uploadFileid!=null){
			yezhuFileName=uploadFileidFileName;
			uploadFileidFileName=System.currentTimeMillis()+uploadFileidFileName.substring(uploadFileidFileName.indexOf("."));
			//保存到文件物理地址各类型文件目录
			StringBuffer savepath_photofileid = new StringBuffer(savepath);
			savepath_photofileid.append("\\"+uploadFileidFileName);
			sqlsavepath.append(uploadFileidFileName);//数据库表中的名称
			try {
				flag = savefile(uploadFileid, savepath_photofileid.toString());
			    if(flag){
			    	if(StringUtil.Null2Blank(sqlsavepath.toString()).length()>0){
			    		YezhuFile yezhuFile=new YezhuFile();
			    		yezhuFile.setFileName(yezhuFileName);
			    		yezhuFile.setFileUrl(sqlsavepath.toString());
			    		yezhuFile.setUploadName(userName);
			    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    		yezhuFile.setUploadDate(sdf.format(System.currentTimeMillis()));
			    		sysService.saveYezhuFile(yezhuFile);
			    	}
					response.getWriter().print("success");
			    }else{
			    	response.getWriter().print("fail");
			    }
			} catch (Exception e) {
			logger.error(e.getMessage());
			}finally{
				
			}
			
			return null;
		}
		
		return SUCCESS;
    }

	public String getLqxxid() {
		return lqxxid;
	}

	public void setLqxxid(String lqxxid) {
		this.lqxxid = lqxxid;
	}
}
