package com.mss.shtoone.action;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.Shuiwenxixxlilun;
import com.mss.shtoone.domain.ShuiwenxixxlilunView;
import com.mss.shtoone.domain.Shuiwenziduancfg;
import com.mss.shtoone.domain.TopRealMaxShuiwenxixxView;
import com.mss.shtoone.domain.WuchaIsShowData;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.ShuiwenxixxlilunService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.GetDate;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


@Controller
@Namespace("/system")

public class ShuiwenxixxlilunAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3805644965382092049L;
	@Autowired
	private ShuiwenxixxlilunService swllService;
	
	@Autowired
	private SystemService sysService;
	
	
	private List<ShuiwenxixxlilunView> list = new ArrayList<ShuiwenxixxlilunView>();
	private Shuiwenxixxlilun swllxx;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Map<String, String> listmap;
	private int llid;//理论配比ID
	private String llbuwei;//使用部位
	private String llshebeibianhao;//设备编号
	private String nowTime;//当前日期
	private Integer[] wuchaselect;
	private List<WuchaIsShowData> wuchalist;
	private Integer biaoduan;
	private Integer xiangmubu;
	private String shebeibianhao;
	private Shuiwenziduancfg swziduancfg;
	public Shuiwenziduancfg getSwziduancfg() {
		return swziduancfg;
	}

	public void setSwziduancfg(Shuiwenziduancfg swziduancfg) {
		this.swziduancfg = swziduancfg;
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
	public List<ShuiwenxixxlilunView> getList() {
		return list;
	}

	public void setList(List<ShuiwenxixxlilunView> list) {
		this.list = list;
	}
	
	public Shuiwenxixxlilun getSwllxx() {
		return swllxx;
	}

	public void setSwllxx(Shuiwenxixxlilun swllxx) {
		this.swllxx = swllxx;
	}

	public int getLlid() {
		return llid;
	}

	public void setLlid(int llid) {
		this.llid = llid;
	}

	public Map<String, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<String, String> listmap) {
		this.listmap = listmap;
	}

	public String getLlbuwei() {
		return llbuwei;
	}

	public void setLlbuwei(String llbuwei) {
		this.llbuwei = llbuwei;
	}

	public String getLlshebeibianhao() {
		return llshebeibianhao;
	}

	public void setLlshebeibianhao(String llshebeibianhao) {
		this.llshebeibianhao = llshebeibianhao;
	}
	
	public String getNowTime() {
		return nowTime;
	}

	public void setNowTime(String nowTime) {
		this.nowTime = nowTime;
	}

	@Action("swllList")
	 public String swllList(){
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
		
		listmap = new LinkedHashMap<String, String>();
		List<TopRealMaxShuiwenxixxView> toplist = sysService.limitswlist(request, biaoduan, xiangmubu);
		for (TopRealMaxShuiwenxixxView topsw : toplist) {
			listmap.put(topsw.getShebeibianhao(), topsw.getBanhezhanminchen());
		}
		setNowTime(GetDate.getNowTime("yyyy-MM-dd"));
		//从字段配置中获取字段名称
		setSwziduancfg(sysService.shuiwenfieldnameBybh(shebeibianhao));
		setList(swllService.getAll(biaoduan,xiangmubu,shebeibianhao,StringUtil.getQueryFieldNameByRequest(request), 
				StringUtil.getBiaoshiId(request)));
		return SUCCESS; 
	 }
	
	@Action("swllInput")
	 public String lqllInput(){	
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("llid")).length()>0){
			System.out.println(request.getParameter("llid"));
			setLlid(Integer.parseInt(request.getParameter("llid")));
		}
		if(StringUtil.Null2Blank(request.getParameter("shebeibianhao")).length()>0){
			setShebeibianhao(request.getParameter("shebeibianhao"));
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
		if(StringUtil.Null2Blank(shebeibianhao).length()>0){
			for (TopRealMaxShuiwenxixxView topsw : toplist) {
				if(StringUtil.Null2Blank(shebeibianhao).equalsIgnoreCase(topsw.getShebeibianhao())){
					listmap.put(topsw.getShebeibianhao(), topsw.getBanhezhanminchen());
				}
			}
		}else{
			for (TopRealMaxShuiwenxixxView topsw : toplist) {
				listmap.put(topsw.getShebeibianhao(), topsw.getBanhezhanminchen());
			}
		}
		//过滤理论配合比列表
		
		if(llid>=0 && StringUtil.Null2Blank(shebeibianhao).length()>0){
			setSwllxx(swllService.getBeanById(llid));
			//从字段配置中获取字段名称
			setSwziduancfg(sysService.shuiwenfieldnameBybh(shebeibianhao));
		}
		return SUCCESS; 
	 }
	
	@Action(value = "swllAdd", results = @Result(name = "swllList", type = "redirect", location = "swllList?pid=6&record=27&"))
	 public String swllAdd(){		
		if(StringUtil.Null2Blank(swllxx.getLllurudate()).length()==0){
			swllxx.setLllurudate(GetDate.getNowTime("yyyy-MM-dd HH:MM:ss"));
		}
		if(StringUtil.Null2Blank(swllxx.getLlmoren()).length()>0){
			if(swllxx.getLlmoren().equals("1")){
				List<Shuiwenxixxlilun> list=swllService.getllPhbBySbbh(swllxx.getLlshebeibianhao());
				for(Shuiwenxixxlilun swllObj:list){
					swllObj.setLlmoren("0");
					swllService.saveOrUpdate(swllObj);
				}
			}
		}
		if(swllxx!=null){
			swllService.saveOrUpdate(swllxx);
		}
		return "swllList"; 
	 }
	
	@Action(value = "swllDel", results = @Result(name = "swllList", type = "redirect", location = "swllList?pid=6&record=27&"))
	public String swllDel(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		if(StringUtil.Null2Blank(request.getParameter("llid")).length()>0){
			setLlid(Integer.parseInt(request.getParameter("llid")));
		}
		if(llid>0){
			swllService.del(llid);
		}
		return "swllList"; 
	}
	
	@Action(value = "swllMoren", results = @Result(name = "swllList", type = "redirect", location = "swllList?pid=6&record=27&"))
	public String swllMoren(){		
		swllService.moren(llid, llbuwei, llshebeibianhao);
		return "swllList";
	}

	
}
