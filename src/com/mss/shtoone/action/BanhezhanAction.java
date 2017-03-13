package com.mss.shtoone.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.HandSet;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.domain.Zuoyeduixinxi;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller
@Namespace("/system")

public class BanhezhanAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3859708288861353077L;
	
	@Autowired
	private SystemService sysService;
	
	private List<Banhezhanxinxi> bhzlist;
	private Banhezhanxinxi bhz;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Map<Integer, String> zydlistmap;
	private Map<Integer, String> handsetlistmap;
	private Map<String, String> listmap;
	private Integer biaoduan;
	private Integer xiangmubu;
	private String shebeibianhao;
	private int bhzId;
	
	public Map<String, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
	}

	public Map<Integer, String> getHandsetlistmap() {
		return handsetlistmap;
	}

	public void setHandsetlistmap(Map<Integer, String> handsetlistmap) {
		this.handsetlistmap = handsetlistmap;
	}

	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}

	public Banhezhanxinxi getBhz() {
		return bhz;
	}

	public void setBhz(Banhezhanxinxi bhz) {
		this.bhz = bhz;
	}	

	public int getBhzId() {
		return bhzId;
	}

	public void setBhzId(int bhzId) {
		this.bhzId = bhzId;
	}

	public List<Banhezhanxinxi> getBhzlist() {
		return bhzlist;
	}

	public void setBhzlist(List<Banhezhanxinxi> bhzlist) {
		this.bhzlist = bhzlist;
	}

	@Action("banhezhanlist")
	public String banhezhanlist() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
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
		zydlistmap = new LinkedHashMap<Integer, String>();
		List<Zuoyeduixinxi> zydlist = sysService.limitzydlist(request, biaoduan, xiangmubu);
		for (Zuoyeduixinxi zyd : zydlist) {
			zydlistmap.put(zyd.getId(), zyd.getZuoyeduiminchen());
		}
		setBhzlist(sysService.limitbhzlist(request, biaoduan, xiangmubu,""));
		return SUCCESS;
	}
	
	@Action(value = "banhezhanadd", results = @Result(name = "list", type = "redirect", location = "banhezhanlist?pid=4&record=30&"))
	public String banhezhanadd() {
		if (null != bhz && StringUtil.Null2Blank(bhz.getGprsbianhao()).length()>0) {
			sysService.bhzadd(bhz);
		}
		return "list";
	}
	
	@Action(value = "banhezhaninput")
	public String banhezhaninput() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
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
		zydlistmap = new LinkedHashMap<Integer, String>();
		List<Zuoyeduixinxi> zydlist = sysService.limitzydlist(request, biaoduan, xiangmubu);
		for (Zuoyeduixinxi zyd : zydlist) {
			zydlistmap.put(zyd.getId(), zyd.getZuoyeduiminchen());
		}
		handsetlistmap = new LinkedHashMap<Integer, String>();
		for (HandSet handset : sysService.handsetlist(biaoduan)) {
			handsetlistmap.put(handset.getId(), handset.getName());
		}
		//沥青拌合站
		listmap = new LinkedHashMap<String, String>();
		List<TopLiqingView> toplist = sysService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			listmap.put(toplqView.getShebeibianhao(), toplqView.getBanhezhanminchen());
		}
		if (bhzId > 0) {
			setBhz(sysService.bhzfindById(request, bhzId));
		}
		return SUCCESS;
	}
	
	@Action(value = "banhezhandel", results = @Result(name = "list", type = "redirect", location = "banhezhanlist?pid=4&record=30&"))
	public String banhezhandel() {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		sysService.bhzdel(request, bhzId);
		return "list";
	}
	
	/******清空客户端编号*******/
	@Action(value="clearbiaohao")
	public String clearbiaohao(){
		ActionContext context = ActionContext.getContext(); 
		HttpServletResponse response = (HttpServletResponse)context.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		JSONObject returnJSON=new JSONObject();
		if(StringUtil.Null2Blank(shebeibianhao).length()>0){
			boolean flag=sysService.clearkehuduanbianhao(shebeibianhao);
			if(flag==true){
				returnJSON.put("info", "success");
				try {
					 PrintWriter out = response.getWriter();
					 out.print(returnJSON.toString());
					 out.close();
				  } catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					returnJSON.put("info", "error");
					PrintWriter out = response.getWriter();
					out.print(returnJSON.toString());
					out.close();
				  } catch (IOException e) {
					e.printStackTrace();
				  } 
			}
		}
		return null;
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

	public Map<Integer, String> getZydlistmap() {
		return zydlistmap;
	}

	public void setZydlistmap(Map<Integer, String> zydlistmap) {
		this.zydlistmap = zydlistmap;
	}

}
