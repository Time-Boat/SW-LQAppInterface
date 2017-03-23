package com.mss.shtoone.app.action.lq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mss.shtoone.app.action.BaseAction;
import com.mss.shtoone.app.domain.AppSWMaterialEntity;
import com.mss.shtoone.app.domain.BhzInfoEntity;
import com.mss.shtoone.app.domain.SWChaobiaoCZSHInfo;
import com.mss.shtoone.app.domain.SWChaobiaoItemEntity;
import com.mss.shtoone.app.domain.SWDataQueryChartEntity;
import com.mss.shtoone.app.domain.SWWraningStatisticsEntity;
import com.mss.shtoone.app.domain.SWXQHeadInfoEntity;
import com.mss.shtoone.app.domain.lq.AppLQMaterialEntity;
import com.mss.shtoone.app.domain.lq.LQChaobiaoCZSHInfo;
import com.mss.shtoone.app.domain.lq.LQChaobiaoItemEntity;
import com.mss.shtoone.app.domain.lq.LQDataQueryChartEntity;
import com.mss.shtoone.app.domain.lq.LQWraningStatisticsEntity;
import com.mss.shtoone.app.domain.lq.LQXQHeadInfoEntity;
import com.mss.shtoone.app.domain.lq.LQdailylistItemEntity;
import com.mss.shtoone.app.domain.lq.LQgallclItemEntity;
import com.mss.shtoone.app.domain.lq.LQziduancfgItem2Entity;
import com.mss.shtoone.app.domain.lq.LQziduancfgItemEntity;
import com.mss.shtoone.app.persistence.hibernate.AppServiceHibernateDAO;
import com.mss.shtoone.app.persistence.hibernate.lq.AppLqServiceHibernateDAO;
import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.LiqingclDailyView;
import com.mss.shtoone.domain.LiqingmanualphbView;
import com.mss.shtoone.domain.LiqingphbView;
import com.mss.shtoone.domain.Liqingxixx;
import com.mss.shtoone.domain.Liqingxixxjieguo;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.LqshaifenjieguoView;
import com.mss.shtoone.domain.ShaifenjieguoView;
import com.mss.shtoone.domain.Shaifenziduancfg;
import com.mss.shtoone.domain.ShuiwenmanualphbView;
import com.mss.shtoone.domain.ShuiwenphbView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.Shuiwenxixxjieguo;
import com.mss.shtoone.domain.ShuiwenxixxlilunView;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.LqshaifenshiyanService;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.GetDate;
import com.mss.shtoone.util.JsonUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Namespace("/appLq")
public class AppLqInterfaceAction extends BaseAction{
	@Autowired
	private AppLqServiceHibernateDAO appLqHibernateDAO;
	
	@Autowired
	private AppServiceHibernateDAO appSystemService;
	
	@Autowired
	private QueryService queryService;
	
	@Autowired
	private SystemService sysService;
	
	@Autowired
	private LqshaifenshiyanService shaifenService;
	
	@Action("lqWarningStatistics")
	public void lqWarningStatistics(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

		JsonUtil.responseUTF8(response);
		JSONObject returnJsonObj = new JSONObject();
		try{
			String departType = request.getParameter("departType");
			String biaoshiid = request.getParameter("biaoshiid");//
			String startTime = request.getParameter("startTime");// 开始时间(时间戳)
			String endTime = request.getParameter("endTime");// 结束时间(时间戳)
			String shebeibianhao = request.getParameter("shebeibianhao");
			
			if (null == startTime && null == endTime) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar day = Calendar.getInstance();
				endTime = sdf.format(day.getTime());
				day.add(Calendar.MONTH, -1);
				startTime = sdf.format(day.getTime());
			} else {
				startTime = GetDate.TimetmpConvetDateTime(request.getParameter("startTime"));// 开始时间
				endTime = GetDate.TimetmpConvetDateTime(request.getParameter("endTime"));// 终止时间
			}
			Integer a = biaoshiid == "" || biaoshiid == null ? null : Integer.valueOf(biaoshiid);
			List<LQWraningStatisticsEntity> list = new ArrayList<LQWraningStatisticsEntity>();

			if ("1".equals(departType)) {
				List<Biaoduanxinxi> bdList = appSystemService.bdList();
				for (int i = 0; i < bdList.size(); i++) {
					Integer bdId = bdList.get(i).getId();

					List<LiqingView> sList = appLqHibernateDAO.smstongji(startTime, endTime, bdId, null,
							shebeibianhao, StringUtil.getQueryFieldNameByUserType(Integer.valueOf(departType)), bdId,
							0,0);

					Map<String, String> map = appLqHibernateDAO.getCbcz(startTime, endTime, bdId, null, shebeibianhao);

					String cczplv = "0.00";
					String mczplv = "0.00";
					String hczplv = "0.00";

					LQWraningStatisticsEntity sws = new LQWraningStatisticsEntity();
					
					sws.setBsId(bdId + "");
					sws.setBanhezhanminchen(bdList.get(i).getBiaoduanminchen());

					// 取第1 3 5 7条数据
					for (int j = 2; j < sList.size(); j += 2) {

						LiqingView s = sList.get(j);
						System.out.println(s.getPangshu());
						if (s.getPangshu() == null || s.getPangshu().equals("0")) {
							sws.setCbpanshu("0");
							sws.setMcbpanshu("0");
							sws.setHcbpanshu("0");
							sws.setCblv("0.00");
							sws.setMcblv("0.00");
							sws.setHcblv("0.00");
							sws.setCczpanshu("0");
							sws.setMczpanshu("0");
							sws.setHczpanshu("0");
							sws.setCzlv("0.00");
							sws.setMczlv("0.00");
							sws.setHczlv("0.00");
							sws.setTotalFangliang("0");
							sws.setTotalPanshu("0");
							break;
						}

						switch (j) {
						case 2:

							if (map.get("a") != null) {
								cczplv = formatFloat(
										Float.parseFloat(map.get("a")) * 100 / Float.parseFloat(s.getAmbegin()),
										"#0.00");
							}

							sws.setCzlv(cczplv);
							sws.setCczpanshu(map.get("a"));
							sws.setCblv(s.getAmend());
							sws.setCbpanshu(s.getAmbegin());
							sws.setRemark(s.getBeizhu());
							
							sws.setTotalFangliang(s.getChangliang());
							sws.setTotalPanshu(s.getPangshu());
							break;
						case 4:

							if (map.get("b") != null) {
								mczplv = formatFloat(
										Float.parseFloat(map.get("b")) * 100 / Float.parseFloat(s.getAmbegin()),
										"#0.00");
							}

							sws.setMczlv(mczplv);
							sws.setMczpanshu(map.get("b"));
							sws.setMcblv(s.getAmend());
							sws.setMcbpanshu(s.getAmbegin());
							sws.setRemark(s.getBeizhu());
							
							sws.setTotalFangliang(s.getChangliang());
							sws.setTotalPanshu(s.getPangshu());
							break;
						case 6:

							if (map.get("c") != null) {
								hczplv = formatFloat(
										Float.parseFloat(map.get("c")) * 100 / Float.parseFloat(s.getAmbegin()),
										"#0.00");
							}

							sws.setHczlv(hczplv);
							sws.setHczpanshu(map.get("c"));
							sws.setHcblv(s.getAmend());
							sws.setHcbpanshu(s.getAmbegin());
							sws.setRemark(s.getBeizhu());
							
							sws.setTotalFangliang(s.getChangliang());
							sws.setTotalPanshu(s.getPangshu());
							break;
						default:
							break;
						}
					}

					list.add(sws);
				}
			} else if ("2".equals(departType)) {
				List<Xiangmubuxinxi> xmbList = appSystemService.getXmbByBD(a + "");
				for (int i = 0; i < xmbList.size(); i++) {

					Integer xmbId = xmbList.get(i).getId();

					List<LiqingView> sList = appLqHibernateDAO.smstongji(startTime, endTime, xmbId, null,
							shebeibianhao, StringUtil.getQueryFieldNameByUserType(Integer.valueOf(departType)), a,
							0,0);

					Map<String, String> map = appLqHibernateDAO.getCbcz(startTime, endTime, a, xmbId, shebeibianhao);

					String cczplv = "0.00";
					String mczplv = "0.00";
					String hczplv = "0.00";

					LQWraningStatisticsEntity sws = new LQWraningStatisticsEntity();
					
					sws.setBsId(xmbId + "");
					
					sws.setBanhezhanminchen(xmbList.get(i).getXiangmubuminchen());

					// 取第1 3 5 7条数据
					for (int j = 2; j < sList.size(); j += 2) {
						LiqingView s = sList.get(j);

						if (s.getPangshu() == null || s.getPangshu().equals("0")) {
							sws.setCbpanshu("0");
							sws.setMcbpanshu("0");
							sws.setHcbpanshu("0");
							sws.setCblv("0.00");
							sws.setMcblv("0.00");
							sws.setHcblv("0.00");
							sws.setCczpanshu("0");
							sws.setMczpanshu("0");
							sws.setHczpanshu("0");
							sws.setCzlv("0.00");
							sws.setMczlv("0.00");
							sws.setHczlv("0.00");
							sws.setTotalFangliang("0");
							sws.setTotalPanshu("0");
							
							break;
						}

						switch (j) {
						case 2:

							if (map.get("a") != null) {
								cczplv = formatFloat(
										Float.parseFloat(map.get("a")) * 100 / Float.parseFloat(s.getAmbegin()),
										"#0.00");
							}

							sws.setCzlv(cczplv);
							sws.setCczpanshu(map.get("a"));
							sws.setCblv(s.getAmend());
							sws.setCbpanshu(s.getAmbegin());
							sws.setRemark(s.getBeizhu());
							
							sws.setTotalFangliang(s.getChangliang());
							sws.setTotalPanshu(s.getPangshu());
							break;
						case 4:

							if (map.get("b") != null) {
								mczplv = formatFloat(
										Float.parseFloat(map.get("b")) * 100 / Float.parseFloat(s.getAmbegin()),
										"#0.00");
							}

							sws.setMczlv(mczplv);
							sws.setMczpanshu(map.get("b"));
							sws.setMcblv(s.getAmend());
							sws.setMcbpanshu(s.getAmbegin());
							sws.setRemark(s.getBeizhu());
							
							sws.setTotalFangliang(s.getChangliang());
							sws.setTotalPanshu(s.getPangshu());
							break;
						case 6:

							if (map.get("c") != null) {
								hczplv = formatFloat(
										Float.parseFloat(map.get("c")) * 100 / Float.parseFloat(s.getAmbegin()),
										"#0.00");
							}

							sws.setHczlv(hczplv);
							sws.setHczpanshu(map.get("c"));
							sws.setHcblv(s.getAmend());
							sws.setHcbpanshu(s.getAmbegin());
							sws.setRemark(s.getBeizhu());
							
							sws.setTotalFangliang(s.getChangliang());
							sws.setTotalPanshu(s.getPangshu());
							break;
						default:
							break;
						}
					}

					list.add(sws);
				}
			} else if ("3".equals(departType)) {
				List<Banhezhanxinxi> bhzList = appSystemService.getBhzByXmb(a + "","2");
				for (int i = 0; i < bhzList.size(); i++) {

					String sbbh = bhzList.get(i).getGprsbianhao();

					List<LiqingView> sList = appLqHibernateDAO.smstongji(startTime, endTime, null, a,
							shebeibianhao, StringUtil.getQueryFieldNameByUserType(Integer.valueOf(departType)), a,
							0,0);

					Map<String, String> map = appLqHibernateDAO.getCbcz(startTime, endTime, null, null, sbbh);

					String cczplv = "0.00";
					String mczplv = "0.00";
					String hczplv = "0.00";

					LQWraningStatisticsEntity sws = new LQWraningStatisticsEntity();
					
					sws.setBsId(sbbh + "");

					sws.setBanhezhanminchen(bhzList.get(i).getBanhezhanminchen());

					// 取第1 3 5 7条数据
					for (int j = 2; j < sList.size(); j += 2) {
						LiqingView s = sList.get(j);

						if (s.getPangshu() == null || s.getPangshu().equals("0")) {
							sws.setCbpanshu("0");
							sws.setMcbpanshu("0");
							sws.setHcbpanshu("0");
							sws.setCblv("0.00");
							sws.setMcblv("0.00");
							sws.setHcblv("0.00");
							sws.setCczpanshu("0");
							sws.setMczpanshu("0");
							sws.setHczpanshu("0");
							sws.setCzlv("0.00");
							sws.setMczlv("0.00");
							sws.setHczlv("0.00");
							sws.setTotalFangliang("0");
							sws.setTotalPanshu("0");
							
							break;
						}

						switch (j) {
						case 2:

							if (map.get("a") != null) {
								cczplv = formatFloat(
										Float.parseFloat(map.get("a")) * 100 / Float.parseFloat(s.getAmbegin()),
										"#0.00");
							}

							sws.setCzlv(cczplv);
							sws.setCczpanshu(map.get("a"));
							sws.setCblv(s.getAmend());
							sws.setCbpanshu(s.getAmbegin());
							sws.setRemark(s.getBeizhu());
							
							sws.setTotalFangliang(s.getChangliang());
							sws.setTotalPanshu(s.getPangshu());
							break;
						case 4:

							if (map.get("b") != null) {
								mczplv = formatFloat(
										Float.parseFloat(map.get("b")) * 100 / Float.parseFloat(s.getAmbegin()),
										"#0.00");
							}

							sws.setMczlv(mczplv);
							sws.setMczpanshu(map.get("b"));
							sws.setMcblv(s.getAmend());
							sws.setMcbpanshu(s.getAmbegin());
							sws.setRemark(s.getBeizhu());
							
							sws.setTotalFangliang(s.getChangliang());
							sws.setTotalPanshu(s.getPangshu());
							break;
						case 6:

							if (map.get("c") != null) {
								hczplv = formatFloat(
										Float.parseFloat(map.get("c")) * 100 / Float.parseFloat(s.getAmbegin()),
										"#0.00");
							}

							sws.setHczlv(hczplv);
							sws.setHczpanshu(map.get("c"));
							sws.setHcblv(s.getAmend());
							sws.setHcbpanshu(s.getAmbegin());
							sws.setRemark(s.getBeizhu());
							
							sws.setTotalFangliang(s.getChangliang());
							sws.setTotalPanshu(s.getPangshu());
							break;
						default:
							break;
						}
					}

					list.add(sws);
				}
			}

			returnJsonObj.put("data", list);
			returnJsonObj.put("success", true);
			
		}catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("获取沥青预警统计数据失败");
			returnJsonObj.put("data", "[]");
			returnJsonObj.put("success", false);
		}
		responseOutWrite(response, returnJsonObj);
	}
	
	// 沥青超标查询
	@Action("lqchaoBiaoList")
	public void lqchaoBiaoList() {

		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

		JsonUtil.responseUTF8(response);
		JSONObject returnJsonObj = new JSONObject();

		String departType = request.getParameter("departType");
		String biaoshiid = request.getParameter("biaoshiid");// 标识
		String startTime = request.getParameter("startTime");// 开始时间(时间戳)
		String endTime = request.getParameter("endTime");// 结束时间(时间戳)
		String shebeibianhao = request.getParameter("shebeibianhao");
		String chaobiaolx = request.getParameter("chaobiaolx"); // 0 全部 1 初级 2
																// 中级 3 高级
		String cllx = request.getParameter("cllx"); // 0 全部 1 未处理 2 已处理

		if (!StringUtil.isNotEmpty(departType) || !StringUtil.isNotEmpty(biaoshiid)) {
			returnJsonObj.put("description", "departType或者biaoshiid为空");
			returnJsonObj.put("success", false);
			responseOutWrite(response, returnJsonObj);
			return;
		}

		if (!StringUtil.isNotEmpty(cllx)) {
			cllx = "0";
		}

		if (!StringUtil.isNotEmpty(chaobiaolx)) {
			chaobiaolx = "0";
		}

		if (!StringUtil.isNotEmpty(startTime) && !StringUtil.isNotEmpty(endTime)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar day = Calendar.getInstance();
			endTime = sdf.format(day.getTime());
			day.add(Calendar.MONTH, -1);
			startTime = sdf.format(day.getTime());
		} else {
			startTime = GetDate.TimetmpConvetDateTime(request.getParameter("startTime"));// 开始时间
			endTime = GetDate.TimetmpConvetDateTime(request.getParameter("endTime"));// 终止时间
		}

		int pageNo = 1;
		if (StringUtil.Null2Blank(request.getParameter("pageNo")).length() > 0) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		int maxPageItems = 15;
		if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length() > 0) {
			maxPageItems = Integer.parseInt(request.getParameter("maxPageItems"));
		}

		LiqingziduancfgView lqisshow = queryService.getlqcfgisShow40(shebeibianhao);
		if (lqisshow == null) {
			lqisshow = queryService.getlqcfgisShow2(shebeibianhao);
			
		}
		LiqingziduancfgView lqziduanfield = queryService.getLqfield(shebeibianhao);

		Integer c = biaoshiid == "" || biaoshiid == null ? null : Integer.valueOf(biaoshiid);
		Integer d = chaobiaolx == "" || chaobiaolx == null ? null : Integer.valueOf(chaobiaolx);
		Integer e = cllx == "" || cllx == null ? null : Integer.valueOf(cllx);

		GenericPageMode s = appLqHibernateDAO.lqchaobiaomanualviewlist(lqisshow,d,shebeibianhao, 
				startTime, endTime,  null, null, 
				StringUtil.getQueryFieldNameByUserType(Integer.parseInt(departType)), 
				c, pageNo, maxPageItems,e, "");
				
		/*GenericPageMode s = appSystemService.swchaobiaomanualviewlist(shebeibianhao, startTime, endTime, null, null,
				StringUtil.getQueryFieldNameByUserType(Integer.parseInt(departType)), c, pageNo, maxPageItems, d,
				lqisshow, e, "");*/
		

		List<LiqingmanualphbView> list = s.getDatas();

		List<LQChaobiaoItemEntity> simplifylist = new ArrayList();

		for (LiqingmanualphbView sw : list) {
			LQChaobiaoItemEntity sc = new LQChaobiaoItemEntity();
			sc.setBianhao(sw.getBianhao() + "");
			sc.setBhzName(sw.getBanhezhanminchen());
			sc.setClTime(sw.getShijian());
			sc.setSjg1(sw.getSjg1());
			sc.setSjg2(sw.getSjg2());
			sc.setSjg3(sw.getSjg3());
			sc.setSjg4(sw.getSjg4());
			sc.setSjg5(sw.getSjg5());
			sc.setSjg6(sw.getSjg6());
			sc.setSjg7(sw.getSjg7());
			sc.setSjysb(sw.getSjysb());
			sc.setClwd(sw.getClwd());
			sc.setGlwd(sw.getGlwd());
			sc.setLqwd(sw.getLqwd());
			sc.setSjtjj(sw.getSjtjj());
			sc.setSjf1(sw.getSjf1());
			sc.setSjf2(sw.getSjf2());
			
			sc.setSjlq(sw.getSjlq());
			sc.setSbbh(sw.getShebeibianhao());
			String chuli = sw.getChulijieguo();

			String shenhe = sw.getYezhuyijian();

			
			// 本条数据是否处置
			if (StringUtil.isNotEmpty(chuli)) {
				sc.setChuli("1");
			} else {
				sc.setChuli("0");
			}
			
			// 本条数据是否审核
			if (StringUtil.isNotEmpty(shenhe)) {
				sc.setShenhe("1");
			} else {
				sc.setShenhe("0");
			}			

			simplifylist.add(sc);
		}
		
		LQChaobiaoItemEntity field = lqapField(lqziduanfield);
		
		LQziduancfgItemEntity lqisshow2 = lqapField2(lqisshow);
		
		if(lqisshow2.getClwd() == null || "0".equals(lqisshow2.getClwd())){
			lqisshow2.setClwd("1");
		}
		if(lqisshow2.getGlwd() == null || "0".equals(lqisshow2.getGlwd())){
			lqisshow2.setGlwd("1");
		}
		if(lqisshow2.getLqwd() == null || "0".equals(lqisshow2.getLqwd())){
			lqisshow2.setLqwd("1");
		}
		if(lqisshow2.getSjysb() == null || "0".equals(lqisshow2.getSjysb())){
			lqisshow2.setSjysb("1");
		}
		
		field.setBianhao("编号");
		field.setBhzName("拌合站名称");
		

		try {
			returnJsonObj.put("field", field);
			returnJsonObj.put("lqisshow", lqisshow2);
			returnJsonObj.put("data", simplifylist);
			returnJsonObj.put("success", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			returnJsonObj.put("data", "[]");
			returnJsonObj.put("success", false);
		}
		responseOutWrite(response, returnJsonObj);
	}
	
	// 简化字段名对象
		private LQChaobiaoItemEntity lqapField(LiqingziduancfgView lqziduanfield) {
			LQChaobiaoItemEntity field = new LQChaobiaoItemEntity();
			field.setClTime(lqziduanfield.getShijian());
			field.setSjg1(lqziduanfield.getSjg1());
			field.setSjg2(lqziduanfield.getSjg2());
			field.setSjg3(lqziduanfield.getSjg3());
			field.setSjg4(lqziduanfield.getSjg4());
			field.setSjg5(lqziduanfield.getSjg5());
			field.setSjg6(lqziduanfield.getSjg6());
			field.setSjg7(lqziduanfield.getSjg7());
			field.setClwd(lqziduanfield.getClwd());
			field.setGlwd(lqziduanfield.getGlwd());
			field.setLqwd(lqziduanfield.getLqwd());
			field.setSjtjj(lqziduanfield.getSjtjj());
			field.setSjf1(lqziduanfield.getSjf1());
			field.setSjysb(lqziduanfield.getSjysb());
			
			field.setSjf2(lqziduanfield.getSjf2());
			
			field.setSjlq(lqziduanfield.getSjlq());
			return field;
		}
		
	//简化沥青是否显示字段
		private LQziduancfgItemEntity lqapField2(LiqingziduancfgView lqisshow) {
			LQziduancfgItemEntity field = new LQziduancfgItemEntity();
			
			field.setSjg1(lqisshow.getSjg1());
			field.setSjg2(lqisshow.getSjg2());
			field.setSjg3(lqisshow.getSjg3());
			field.setSjg4(lqisshow.getSjg4());
			field.setSjg5(lqisshow.getSjg5());
			field.setSjg6(lqisshow.getSjg6());
			field.setSjg7(lqisshow.getSjg7());
			field.setClwd(lqisshow.getClwd());
			field.setGlwd(lqisshow.getGlwd());
			field.setLqwd(lqisshow.getLqwd());
			field.setSjtjj(lqisshow.getSjtjj());
			field.setSjf1(lqisshow.getSjf1());
			field.setSjysb(lqisshow.getSjysb());
			
			field.setSjf2(lqisshow.getSjf2());
			
			field.setSjlq(lqisshow.getSjlq());
			return field;
		}
		
		
		//简化沥青是否显示字段
				private LQziduancfgItem2Entity lqapField3(LiqingziduancfgView lqisshow) {
					LQziduancfgItem2Entity field = new LQziduancfgItem2Entity();
					
					field.setSjg1(lqisshow.getSjg1());
					field.setSjg2(lqisshow.getSjg2());
					field.setSjg3(lqisshow.getSjg3());
					field.setSjg4(lqisshow.getSjg4());
					field.setSjg5(lqisshow.getSjg5());
					field.setSjg6(lqisshow.getSjg6());
					field.setSjg7(lqisshow.getSjg7());
					
					field.setSjtjj(lqisshow.getSjtjj());
					field.setSjf1(lqisshow.getSjf1());
					
					
					field.setSjf2(lqisshow.getSjf2());
					
					field.setSjlq(lqisshow.getSjlq());
					return field;
				}
		
		// 沥青超标查询
		@Action("lqchaoBiaoXQ")
		public void lqchaoBiaoXQ() {

			ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

			JsonUtil.responseUTF8(response);
			JSONObject returnJsonObj = new JSONObject();

			String bianhao = request.getParameter("bianhao");
			String shebeibianhao = request.getParameter("shebeibianhao");

			LiqingmanualphbView lq = queryService.lqmanualphbfindById(Integer.parseInt(bianhao));

			Liqingxixxjieguo lqjg = sysService.getLljieguobybh(Integer.parseInt(bianhao));

			LQChaobiaoCZSHInfo simpleJg = new LQChaobiaoCZSHInfo();
			// 监理
			simpleJg.setChulifangshi(lqjg.getChulifangshi());
			simpleJg.setChulijieguo(lqjg.getChulijieguo());
			simpleJg.setChuliren(lqjg.getJianliren());
			simpleJg.setShenpidate(lqjg.getShenpidate());
			simpleJg.setWentiyuanyin(lqjg.getWentiyuanyin());
			// 业主
			simpleJg.setConfirmdate(lqjg.getConfirmdate());
			simpleJg.setYezhuren(lqjg.getYezhuren());
			simpleJg.setYezhuyijian(lqjg.getYezhuyijian());
			simpleJg.setBeizhu(lqjg.getBeizhu());

			// 头信息
			LQXQHeadInfoEntity lqHead = new LQXQHeadInfoEntity();
			lqHead.setCaijishijian(lq.getCaijishijian());
			lqHead.setCl(lq.getChangliang());
			lqHead.setChuliaoshijian(lq.getShijian());
			lqHead.setBhjName(lq.getBanhezhanminchen());

			LiqingziduancfgView lqziduanfield = queryService.getLqfield(shebeibianhao);
			// SWChaobiaoItemEntity field = swapField(swziduanfield);

			try {
				List<AppLQMaterialEntity> alq = bean2List(lq, lqziduanfield, -1);
				
				
				returnJsonObj.put("lqHead", lqHead);
				returnJsonObj.put("lqData", alq);
				returnJsonObj.put("lqjg", simpleJg);
				returnJsonObj.put("success", true);
			} catch (Exception ex) {
				ex.printStackTrace();
				returnJsonObj.put("data", "[]");
				returnJsonObj.put("success", false);
			}
			responseOutWrite(response, returnJsonObj);
		}
		
		//沥青材料用量核算
		@Action("lqmaterial")
		public void lqmaterial() {
			ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

			JsonUtil.responseUTF8(response);
			JSONObject returnJsonObj = new JSONObject();

			String departType = request.getParameter("departType");
			String biaoshiid = request.getParameter("biaoshiid");// 标识
			String startTime = request.getParameter("startTime");// 开始时间(时间戳)
			String endTime = request.getParameter("endTime");// 结束时间(时间戳)
			String shebeibianhao = request.getParameter("shebeibianhao");

			if (!StringUtil.isNotEmpty(departType) && !StringUtil.isNotEmpty(biaoshiid)) {
				returnJsonObj.put("description", "departType或者biaoshiid为空");
				returnJsonObj.put("success", false);
				responseOutWrite(response, returnJsonObj);
				return;
			}

			if (!StringUtil.isNotEmpty(startTime) && !StringUtil.isNotEmpty(endTime)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar day = Calendar.getInstance();
				endTime = sdf.format(day.getTime());
				day.add(Calendar.MONTH, -1);
				startTime = sdf.format(day.getTime());
			} else {
				startTime = GetDate.TimetmpConvetDateTime(request.getParameter("startTime"));// 开始时间
				endTime = GetDate.TimetmpConvetDateTime(request.getParameter("endTime"));// 终止时间
			}

			Integer a = departType == "" || departType == null ? null : Integer.valueOf(departType);
			Integer b = biaoshiid == "" || biaoshiid == null ? -1 : Integer.valueOf(biaoshiid);     //不赋值的话会报错

			LiqingphbView lqviews = appLqHibernateDAO.appLqmateriallist(startTime, endTime, shebeibianhao, null, null,
					StringUtil.getQueryFieldNameByUserType(a), b);

			LiqingziduancfgView lqziduanfield = queryService.getLqfield(shebeibianhao);

			
			
			LiqingziduancfgView lqisshow = queryService.getlqcfgisShow40(shebeibianhao);
			if (lqisshow == null) {
				lqisshow = queryService.getlqcfgisShow2(shebeibianhao);
				
			}
			
			List<AppLQMaterialEntity> alq = bean2List1(lqviews, lqziduanfield,lqisshow, -1);
			
			//LQziduancfgItem2Entity lqisshow2=lqapField3(lqisshow);

			try {
				returnJsonObj.put("data", alq);
				//returnJsonObj.put("lqisshow", lqisshow2);
				returnJsonObj.put("success", true);
			} catch (Exception ex) {
				ex.printStackTrace();
				returnJsonObj.put("data", "[]");
				returnJsonObj.put("success", false);
			}
			responseOutWrite(response, returnJsonObj);
		}
		
		
		//沥青历史数据查询
		@Action("lqgallclList")
		public void lqgallclList() {
			ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

			JsonUtil.responseUTF8(response);
			JSONObject returnJsonObj = new JSONObject();

			String departType = request.getParameter("departType");
			String biaoshiid = request.getParameter("biaoshiid");// 标识
			String startTime = request.getParameter("startTime");// 开始时间(时间戳)
			String endTime = request.getParameter("endTime");// 结束时间(时间戳)
			String shebeibianhao = request.getParameter("shebeibianhao");
			String peifan = request.getParameter("peifan"); //沥青混合料型号
			
			if (!StringUtil.isNotEmpty(departType) || !StringUtil.isNotEmpty(biaoshiid)) {
				returnJsonObj.put("description", "departType或者biaoshiid为空");
				returnJsonObj.put("success", false);
				responseOutWrite(response, returnJsonObj);
				return;
			}

			
			if (!StringUtil.isNotEmpty(startTime) && !StringUtil.isNotEmpty(endTime)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar day = Calendar.getInstance();
				endTime = sdf.format(day.getTime());
				day.add(Calendar.MONTH, -1);
				startTime = sdf.format(day.getTime());
			} else {
				startTime = GetDate.TimetmpConvetDateTime(request.getParameter("startTime"));// 开始时间
				endTime = GetDate.TimetmpConvetDateTime(request.getParameter("endTime"));// 终止时间
			}

			int pageNo = 1;
			if (StringUtil.Null2Blank(request.getParameter("pageNo")).length() > 0) {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			int maxPageItems = 15;
			if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length() > 0) {
				maxPageItems = Integer.parseInt(request.getParameter("maxPageItems"));
			}

			LiqingziduancfgView lqisshow = queryService.getlqcfgisShow40(shebeibianhao);
			if (lqisshow == null) {
				lqisshow = queryService.getlqcfgisShow2(shebeibianhao);
				
			}
			LiqingziduancfgView lqziduanfield = queryService.getLqfield(shebeibianhao);

			Integer c = biaoshiid == "" || biaoshiid == null ? null : Integer.valueOf(biaoshiid);
			
			

			GenericPageMode s = appLqHibernateDAO.lqviewlist(shebeibianhao,startTime,endTime, 
					 null, null, 
					StringUtil.getQueryFieldNameByUserType(Integer.parseInt(departType)), 
					c, pageNo, maxPageItems,1,peifan );
					
			/*GenericPageMode s = appSystemService.swchaobiaomanualviewlist(shebeibianhao, startTime, endTime, null, null,
					StringUtil.getQueryFieldNameByUserType(Integer.parseInt(departType)), c, pageNo, maxPageItems, d,
					lqisshow, e, "");*/
			

			List<LiqingView> list = s.getDatas();

			List<LQgallclItemEntity> dataList = new ArrayList();

			for (LiqingView  sw : list) {
				LQgallclItemEntity sc = new LQgallclItemEntity();
				sc.setBianhao(sw.getBianhao() + "");
				sc.setBhzName(sw.getBanhezhanminchen());
				sc.setClTime(sw.getShijian());
				sc.setSjg1(sw.getSjg1());
				sc.setSjg2(sw.getSjg2());
				sc.setSjg3(sw.getSjg3());
				sc.setSjg4(sw.getSjg4());
				sc.setSjg5(sw.getSjg5());
				sc.setSjg6(sw.getSjg6());
				sc.setSjg7(sw.getSjg7());
				sc.setSjysb(sw.getSjysb());
				sc.setClwd(sw.getClwd());
				sc.setGlwd(sw.getGlwd());
				sc.setLqwd(sw.getLqwd());
				sc.setSjtjj(sw.getSjtjj());
				sc.setSjf1(sw.getSjf1());
				sc.setSjf2(sw.getSjf2());
				
				sc.setSjlq(sw.getSjlq());
				sc.setSbbh(sw.getShebeibianhao());
				dataList.add(sc);
			}
			
			LQgallclItemEntity field = lqapField5(lqziduanfield);
			
			LQziduancfgItemEntity lqisshow2 = lqapField2(lqisshow);
			
			if(lqisshow2.getClwd() == null || "0".equals(lqisshow2.getClwd())){
				lqisshow2.setClwd("1");
			}
			if(lqisshow2.getGlwd() == null || "0".equals(lqisshow2.getGlwd())){
				lqisshow2.setGlwd("1");
			}
			if(lqisshow2.getLqwd() == null || "0".equals(lqisshow2.getLqwd())){
				lqisshow2.setLqwd("1");
			}
			if(lqisshow2.getSjysb() == null || "0".equals(lqisshow2.getSjysb())){
				lqisshow2.setSjysb("1");
			}
			
			
			field.setBianhao("编号");
			field.setBhzName("拌合站名称");
			

			try {
				returnJsonObj.put("field", field);
				returnJsonObj.put("lqisshow", lqisshow2);
				returnJsonObj.put("description", "超标处置成功！");
				returnJsonObj.put("data", dataList);
				returnJsonObj.put("success", true);
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("混凝土APP超标处置错误！");
				returnJsonObj.put("data", "[]");
				returnJsonObj.put("success", false);
			}
			responseOutWrite(response, returnJsonObj);
		}
		
		// 超标处置
		@Action("appLQChaobiaoChuzhi")
		public String appLQChaobiaoChuzhi() {
			ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

			JsonUtil.responseUTF8(response);
			JSONObject returnJsonObj = new JSONObject();
			String sql = "";
			try {
				request.setCharacterEncoding("UTF-8");

				String bianhaoStr = request.getParameter("jieguobianhao");// 数据编号
				if (StringUtil.Null2Blank(bianhaoStr).length() > 0) {
					String chaobiaoyuanyin = StringUtil.Null2Blank(request.getParameter("chaobiaoyuanyin"));// 超标原因
					String chuzhifangshi = StringUtil.Null2Blank(request.getParameter("chuzhifangshi"));// 处置方式
					String chuzhijieguo = StringUtil.Null2Blank(request.getParameter("chuzhijieguo"));// 处置结果
					String chuzhiren = StringUtil.Null2Blank(request.getParameter("chuzhiren"));// 处置人
					String chuzhishijian = GetDate
							.TimetmpConvetDateTime(StringUtil.Null2Blank(request.getParameter("chuzhishijian")));// 处置时间

					String isIos = request.getParameter("isIos");

					// -----代码片段 spingMVC上传文件
					MultiPartRequestWrapper mRequest = null;
					File[] file = null;
					mRequest = (MultiPartRequestWrapper) request;// request强制转换注意
					file = mRequest.getFiles("file");
					if ("1".equals(isIos)) {
						// android和ios文件上传后，在后台接受方式不一样
						// mRequest = (MultipartHttpServletRequest) request;//
						// request强制转换注意
						// file = mRequest.getFile("file");
					} /*else {
						// 解决android乱码问题
						chaobiaoyuanyin = new String(chaobiaoyuanyin.getBytes("ISO-8859-1"), "utf-8");
						chuzhifangshi = new String(chuzhifangshi.getBytes("ISO-8859-1"), "utf-8");
						chuzhijieguo = new String(chuzhijieguo.getBytes("ISO-8859-1"), "utf-8");
						chuzhiren = new String(chuzhiren.getBytes("ISO-8859-1"), "utf-8");
					}*/
					if(isMessyCode(chaobiaoyuanyin)||isMessyCode(chuzhifangshi)||isMessyCode(chuzhijieguo)||isMessyCode(chuzhiren)){
						chaobiaoyuanyin = new String(chaobiaoyuanyin.getBytes("ISO-8859-1"), "utf-8");
						chuzhifangshi = new String(chuzhifangshi.getBytes("ISO-8859-1"), "utf-8");
						chuzhijieguo = new String(chuzhijieguo.getBytes("ISO-8859-1"), "utf-8");
						chuzhiren = new String(chuzhiren.getBytes("ISO-8859-1"), "utf-8");
					}

					if (StringUtil.Null2Blank(chuzhishijian).length() <= 0) {
						chuzhishijian = GetDate.getNowTime("yyyy-MM-dd HH:MM:ss");
					}

					InputStream input = request.getInputStream();// 读取二进制图片流

					if (file != null) {
						input = new FileInputStream(file[0]);
					}

					if (null != input) {
						int a = input.available();
						// 保存文件的物理根地址
						StringBuffer savepath = new StringBuffer(request.getSession().getServletContext().getRealPath("/"));
						savepath.append("\\" + "lqChaobiaoAttachment");
						// 保存数据库表中路径
						StringBuffer sqlsavepath = new StringBuffer("lqChaobiaoAttachment/");

						// 更改文件名称
						String uploadFileidFileName = bianhaoStr + "-" + System.currentTimeMillis() + ".png";
						// 保存到文件物理地址各类型文件目录
						StringBuffer savepath_photofileid = new StringBuffer(savepath);
						savepath_photofileid.append("\\" + uploadFileidFileName);
						sqlsavepath.append(uploadFileidFileName);// 数据库表中的名称

						File savedir = new File(savepath.toString());
						if (!savedir.exists()) {
							savedir.mkdirs();
						}

						// String tupianName = "APP_" + bianhaoStr + "_" +
						// System.currentTimeMillis() + ".jpg";
						// System.out.println(tupianName);
						FileOutputStream fos = new FileOutputStream(savepath_photofileid.toString());
						int size = 0;
						byte[] buffer = new byte[1024];
						while ((size = input.read(buffer, 0, 1024)) != -1) {
							fos.write(buffer, 0, size);
						}
						fos.close();
						input.close();
						sql = "update Liqingxixxjieguo set shenpidate='" + chuzhishijian + "',wentiyuanyin='"
								+ chaobiaoyuanyin + "',chulifangshi='" + chuzhifangshi + "' " + ",jianliren='" + chuzhiren
								+ "',filepath='" + sqlsavepath.toString() + "',chulijieguo='" + chuzhijieguo + "' where "
								+ " lqbianhao=" + bianhaoStr;
					}
					System.out.println(sql);
					int a = appSystemService.updateBySql(sql);
					if (a >= 1) {
						returnJsonObj.put("description", "超标处置成功！");
						returnJsonObj.put("success", true);
					} else {
						returnJsonObj.put("description", "沥青APP超标处置错误！");
						returnJsonObj.put("success", false);
					}
				} else {
					returnJsonObj.put("description", "编号id为空");
					returnJsonObj.put("success", false);
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnJsonObj.put("description", "沥青APP超标处置错误！");
				returnJsonObj.put("success", false);
			}
			JsonUtil.outPrint(response, returnJsonObj.toString());
			return null;
		}

		// 超标审批
		@Action("appLqChaobiaoShenpi")
		public void appLqChaobiaoShenpi() {

			ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

			JsonUtil.responseUTF8(response);
			JSONObject returnJsonObj = new JSONObject();
			try {
				request.setCharacterEncoding("UTF-8");
				String bianhaoStr = request.getParameter("jieguobianhao");// 数据编号
				if (StringUtil.Null2Blank(bianhaoStr).length() > 0) {
					String yezhuyijian = StringUtil.Null2Blank(request.getParameter("yezhuyijian"));// 业主意见
					String confirmdate = GetDate
							.TimetmpConvetDateTime(StringUtil.Null2Blank(request.getParameter("confirmdate")));// 确认日期
					String shenpiren = StringUtil.Null2Blank(request.getParameter("shenpiren"));// 审批人
					String shenpidate = GetDate
							.TimetmpConvetDateTime(StringUtil.Null2Blank(request.getParameter("shenpidate")));// 审批日期
					if (StringUtil.Null2Blank(shenpidate).length() <= 0) {
						shenpidate = GetDate.getNowTime("yyyy-MM-dd HH:MM:ss");
					}
					
					if(isMessyCode(yezhuyijian)||isMessyCode(shenpiren)){
					yezhuyijian = new String(yezhuyijian.getBytes("ISO-8859-1"), "utf-8");
					shenpiren = new String(shenpiren.getBytes("ISO-8859-1"), "utf-8");
					}
					
					String sql = "update Liqingxixxjieguo set yezhuyijian='"
							+ yezhuyijian + "'," + "confirmdate='" + confirmdate
							+ "',yezhuren='" + shenpiren + "' where " + " swbianhao=" + bianhaoStr;

					System.out.println(sql);
					int a = appSystemService.updateBySql(sql);
					if (a >= 1) {
						returnJsonObj.put("description", "超标处置成功！");
						returnJsonObj.put("success", true);
					} else {
						returnJsonObj.put("description", "APP超标处置错误！");
						returnJsonObj.put("success", false);
					}
				} else {
					returnJsonObj.put("description", "编号id为空");
					returnJsonObj.put("success", false);
				}
			} catch (Exception e) {
				e.printStackTrace();
				returnJsonObj.put("description", "APP超标处置错误！");
				returnJsonObj.put("success", false);
			}
			responseOutWrite(response, returnJsonObj);
		}
		
		
		// 沥青历史数据查询详情页
		@Action("liqingxixx")
		public void liqingxixx() {
	
			ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) context
					.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) context
					.get(ServletActionContext.HTTP_RESPONSE);
	
			JsonUtil.responseUTF8(response);
			JSONObject returnJsonObj = new JSONObject();
	
			String bianhao = request.getParameter("bianhao");
			String shebeibianhao = request.getParameter("shebeibianhao");
	
			LiqingView lq = queryService.lqxxfindById(Integer
					.parseInt(bianhao));
	
			// 头信息
			LQXQHeadInfoEntity lqHead = new LQXQHeadInfoEntity();
			lqHead.setCaijishijian(lq.getCaijishijian());
			lqHead.setCl(lq.getChangliang());
			lqHead.setChuliaoshijian(lq.getShijian());
			lqHead.setBhjName(lq.getBanhezhanminchen());
	
			LiqingziduancfgView lqziduanfield = queryService
					.getLqfield(shebeibianhao);
			// SWChaobiaoItemEntity field = swapField(swziduanfield);
	
			try {
				List<AppLQMaterialEntity> alq = bean2List2(lq, lqziduanfield,bianhao, -1);
				
				LqshaifenjieguoView sfjieguoView=shaifenService.getLqshaifenjieguoViewBylqId(Integer.parseInt(bianhao));
				if(sfjieguoView!=null){
					List<LQDataQueryChartEntity> lqChartDataList=bean2List3(sfjieguoView);
					returnJsonObj.put("lqChartDataList", lqChartDataList);
				}else{
					returnJsonObj.put("lqChartDataList", "[]");
				}
				
				returnJsonObj.put("lqHead", lqHead);
				returnJsonObj.put("lqData", alq);
				
				returnJsonObj.put("success", true);
			} catch (Exception ex) {
				ex.printStackTrace();
				returnJsonObj.put("data", "[]");
				returnJsonObj.put("success", false);
			}
			responseOutWrite(response, returnJsonObj);
		}
				
		// 沥青历史数据混合料型号
		@Action("usePosition")
		public void usePosition() {
			ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

			JsonUtil.responseUTF8(response);
			JSONObject returnJsonObj = new JSONObject();

			/*// 组织机构类型
			String departType = request.getParameter("departType");
			// 标识 相关类型id
			String biaoshiid = request.getParameter("biaoshiid");*/
			
			
			
			
			//传入null值会报错，所以这里传一个-1
			/*Integer a = biaoshiid == "" || biaoshiid == null ? -1 : Integer.valueOf(biaoshiid);*/
			
			
			
			Map<String, String> peifanmap=new LinkedHashMap<String, String>();
			
			Map<String, String> peifanmap1 = new LinkedHashMap<String, String>();
			List peifanlist=queryService.getlqxinghao();
			for(int m=0;m<peifanlist.size();m++){
				String str=(String)peifanlist.get(m);
				peifanmap.put(str,str);
			}
			
			int i = 0;
			for(String str : peifanmap.keySet()){
				peifanmap1.put("a"+i++, str);
			}
			
			try {
				returnJsonObj.put("data", peifanmap1);
				returnJsonObj.put("success", true);
			} catch (Exception e) {
				e.printStackTrace();
				returnJsonObj.put("data", "[]");
				returnJsonObj.put("success", false);
			}
			responseOutWrite(response, returnJsonObj);
		}
		
		//沥青日产量统计查询
		@Action("lqdailylist")
		public void lqdailylist() {
			ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

			JsonUtil.responseUTF8(response);
			JSONObject returnJsonObj = new JSONObject();

			String departType = request.getParameter("departType");
			String biaoshiid = request.getParameter("biaoshiid");// 标识
			String startTime = request.getParameter("startTime");// 开始时间(时间戳)
			String endTime = request.getParameter("endTime");// 结束时间(时间戳)
			String shebeibianhao = request.getParameter("shebeibianhao");
			
			if (!StringUtil.isNotEmpty(departType) && !StringUtil.isNotEmpty(biaoshiid)) {
				returnJsonObj.put("description", "departType或者biaoshiid为空");
				returnJsonObj.put("success", false);
				responseOutWrite(response, returnJsonObj);
				return;
			}
			
			if (!StringUtil.isNotEmpty(startTime) && !StringUtil.isNotEmpty(endTime)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar day = Calendar.getInstance();
				endTime = sdf.format(day.getTime());
				day.add(Calendar.MONTH, -1);
				startTime = sdf.format(day.getTime());
			} else {
				startTime = GetDate.TimetmpConvetDateTime(request.getParameter("startTime"));// 开始时间
				endTime = GetDate.TimetmpConvetDateTime(request.getParameter("endTime"));// 终止时间
			}

			int pageNo = 1;
			if (StringUtil.Null2Blank(request.getParameter("pageNo")).length() > 0) {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			int maxPageItems = 15;
			if (StringUtil.Null2Blank(request.getParameter("maxPageItems")).length() > 0) {
				maxPageItems = Integer.parseInt(request.getParameter("maxPageItems"));
			}
			
			Integer c = biaoshiid == "" || biaoshiid == null ? null : Integer.valueOf(biaoshiid);
			
			GenericPageMode s = appLqHibernateDAO.limitdailylist(shebeibianhao, startTime, endTime, null, null,
					StringUtil.getQueryFieldNameByUserType(Integer.parseInt(departType)),c, pageNo, maxPageItems 
					);
			
			List<LiqingclDailyView> dailyViews=s.getDatas();
			
			List<LQdailylistItemEntity> itemEntities=new ArrayList();
			
			for (LiqingclDailyView lqd : dailyViews) {
				LQdailylistItemEntity itemEntity =new LQdailylistItemEntity();
				itemEntity.setBanhezhanminchen(lqd.getBanhezhanminchen());
				itemEntity.setDailyrq(lqd.getDailyrq());
				itemEntity.setDailycl(lqd.getDailycl());
				itemEntity.setDailyps(lqd.getDailyps());
				itemEntity.setDailyxzcl(lqd.getDailyxzcl());
				itemEntity.setDailymd(lqd.getDailymd());
				itemEntity.setDailybuwei(lqd.getDailybuwei());
				itemEntity.setDailycd(lqd.getDailycd());
				itemEntity.setDailykd(lqd.getDailykd());
				itemEntity.setDailyhd(lqd.getDailyhd());
				itemEntity.setDailysjhd(lqd.getDailysjhd());
				itemEntity.setDailyxh(lqd.getDailyxh());
				itemEntities.add(itemEntity);
			}
			
			try {
				
				returnJsonObj.put("data", itemEntities);
				returnJsonObj.put("success", true);
			} catch (Exception ex) {
				ex.printStackTrace();
				returnJsonObj.put("data", "[]");
				returnJsonObj.put("success", false);
			}
			responseOutWrite(response, returnJsonObj);
		}
		
		// 获取指定实体类中的指定数据
		public <T> List<AppLQMaterialEntity> bean2List(T t, LiqingziduancfgView hbfield, int objType) {

			List<AppLQMaterialEntity> bciList = new ArrayList<AppLQMaterialEntity>();

			String[] cfg = { "g1", "g2", "g3", "g4", "g5", "g6","g7" ,"f1","f2","lq","tjj","ysb"};
			
			try {
					for (int i = 0; i < cfg.length; i++) {
						
						AppLQMaterialEntity lqm = new AppLQMaterialEntity();
					String name = (String) hbfield.getClass().getMethod("get" + "Sj" + cfg[i], new Class[] {})
							.invoke(hbfield, new Object[] {});
					String yongliang = (String) t.getClass().getMethod("get" + "Sj" + cfg[i], new Class[] {}).invoke(t,
							new Object[] {});
					String scpeibi="";
					if(i == cfg.length-1){
						 scpeibi = (String) t.getClass().getMethod("get" + "Persjg1" , new Class[] {}).invoke(t,
								new Object[] {});
					}else{
						 scpeibi = (String) t.getClass().getMethod("get" + "Persj" + cfg[i], new Class[] {}).invoke(t,
								new Object[] {});
					}
					
					String sgpeibi = (String) t.getClass().getMethod("get" + "Ll" + cfg[i], new Class[] {}).invoke(t,
							new Object[] {});
					String wucha = (String) t.getClass().getMethod("get" + "Manualwsj" + cfg[i], new Class[] {}).invoke(t,
							new Object[] {});

					lqm.setName(name);
					lqm.setYongliang(yongliang);
					lqm.setScpeibi(scpeibi);
					lqm.setSgpeibi(sgpeibi);

					lqm.setWucha(wucha);

					bciList.add(lqm);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			return bciList;
		}
		
		// 获取指定实体类中的指定数据
		public <T> List<AppLQMaterialEntity> bean2List1(T t, LiqingziduancfgView hbfield,LiqingziduancfgView lqisshow, int objType) {

			List<AppLQMaterialEntity> bciList = new ArrayList<AppLQMaterialEntity>();

			String[] cfg = { "g1", "g2", "g3", "g4", "g5", "g6","g7" ,"f1","f2","lq","tjj", };

			for (int i = 0; i < cfg.length; i++) {
				AppLQMaterialEntity lqm = new AppLQMaterialEntity();
				try {
					if ("1".equals(lqisshow.getClass().getMethod("get" + "Sj" + cfg[i], new Class[] {}).invoke(lqisshow,
							new Object[] {}))) {
					String name = (String) hbfield.getClass().getMethod("get" + "Sj" + cfg[i], new Class[] {})
							.invoke(hbfield, new Object[] {});
					String yongliang = (String) t.getClass().getMethod("get" + "Sj" + cfg[i], new Class[] {}).invoke(t,
							new Object[] {});
					String mbpeibi = (String) t.getClass().getMethod("get" + "Ll" + cfg[i], new Class[] {}).invoke(t,
							new Object[] {});
					String wucha = (String) t.getClass().getMethod("get" + "Persj" + cfg[i], new Class[] {}).invoke(t,
							new Object[] {});

					lqm.setName(name);
					lqm.setYongliang(yongliang);
					lqm.setMbpeibi(mbpeibi);
					lqm.setWucha(wucha);

					bciList.add(lqm);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return bciList;
		}
		
		// 获取指定实体类中的指定数据
		public <T> List<AppLQMaterialEntity> bean2List2(T t,
				LiqingziduancfgView hbfield,String   bianhao, int objType) {
	
			List<AppLQMaterialEntity> bciList = new ArrayList<AppLQMaterialEntity>();
	
			String[] cfg = { "g1", "g2", "g3", "g4", "g5", "g6", "g7", "f1", "f2",
					"lq", "tjj" };
	
			try {
				for (int i = 0; i < cfg.length; i++) {
					LiqingmanualphbView liqingmanualphbView=queryService.lqmanualphbfindById(Integer.parseInt(bianhao));
					AppLQMaterialEntity lqm = new AppLQMaterialEntity();
					String name = (String) hbfield.getClass()
							.getMethod("get" + "Sj" + cfg[i], new Class[] {})
							.invoke(hbfield, new Object[] {});
					String yongliang = (String) t.getClass()
							.getMethod("get" + "Sj" + cfg[i], new Class[] {})
							.invoke(t, new Object[] {});
					String scpeibi = "";
					
						scpeibi = (String) t
								.getClass()
								.getMethod("get" + "Persj" + cfg[i], new Class[] {})
								.invoke(t, new Object[] {});
					
	
					String sgpeibi = (String) t.getClass()
							.getMethod("get" + "Ll" + cfg[i], new Class[] {})
							.invoke(t, new Object[] {});
					String wucha = (String) liqingmanualphbView
							.getClass()
							.getMethod("get" + "Manualwsj" + cfg[i], new Class[] {})
							.invoke(liqingmanualphbView, new Object[] {});
	
					lqm.setName(name);
					lqm.setYongliang(yongliang);
					lqm.setScpeibi(scpeibi);
					lqm.setSgpeibi(sgpeibi);
	
					lqm.setWucha(wucha);
	
					bciList.add(lqm);
				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}
	
			return bciList;
		}
		
		// 获取指定实体类中的指定数据
		public <T> List<LQDataQueryChartEntity> bean2List3(LqshaifenjieguoView sfjieguoView) {

			List<LQDataQueryChartEntity> bciList = new ArrayList<LQDataQueryChartEntity>();

			String[] cfgValue = { "0.075", "0.15", "0.3", "0.6", "1.18" , "2.36" , "4.75" , "9.5" , "13.2" 
					, "16" , "19" , "26.5" , "31.5" , "37.5" , "53" };
			String[] max ={"7","8","9","13","16","23","33","49","59","66","72","90","100","100","100"};
			String[] min ={"3","4","5","7","10","15","25","39","49","57","63","82","90","100","100"};
			DecimalFormat df = new DecimalFormat("#0.00");
			
			try {
				for (int i = 1; i <= cfgValue.length; i++) {
					LQDataQueryChartEntity swm = new LQDataQueryChartEntity();
						
						String name = cfgValue[i-1];
						String maxPassper = max[i-1];
						String minPassper = min[i-1];
						String standPassper = (String) sfjieguoView.getClass().getMethod("getStandPassper" + i, new Class[] {}).invoke(sfjieguoView,
								new Object[] {});
						
						String passper = (String) sfjieguoView.getClass().getMethod("getPassper" + i, new Class[] {}).invoke(sfjieguoView,
								new Object[] {});
						
				
						
						
						swm.setName(name);
						swm.setMaxPassper(maxPassper);
						swm.setMinPassper(minPassper);
						swm.setStandPassper(standPassper);
						swm.setPassper(passper);
						
					
						bciList.add(swm);
					}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return bciList;
		}

		//简化沥青历史查询字段
		private LQgallclItemEntity lqapField4(LiqingziduancfgView lqisshow) {
			LQgallclItemEntity field = new LQgallclItemEntity();
			
			field.setSjg1(lqisshow.getSjg1());
			field.setSjg2(lqisshow.getSjg2());
			field.setSjg3(lqisshow.getSjg3());
			field.setSjg4(lqisshow.getSjg4());
			field.setSjg5(lqisshow.getSjg5());
			field.setSjg6(lqisshow.getSjg6());
			field.setSjg7(lqisshow.getSjg7());
			
			field.setSjtjj(lqisshow.getSjtjj());
			field.setSjf1(lqisshow.getSjf1());
			
			
			field.setSjf2(lqisshow.getSjf2());
			
			field.setSjlq(lqisshow.getSjlq());
			return field;
		}
		
		// 简化字段名对象
		private LQgallclItemEntity lqapField5(LiqingziduancfgView lqziduanfield) {
			LQgallclItemEntity field = new LQgallclItemEntity();
			field.setClTime(lqziduanfield.getShijian());
			field.setSjg1(lqziduanfield.getSjg1());
			field.setSjg2(lqziduanfield.getSjg2());
			field.setSjg3(lqziduanfield.getSjg3());
			field.setSjg4(lqziduanfield.getSjg4());
			field.setSjg5(lqziduanfield.getSjg5());
			field.setSjg6(lqziduanfield.getSjg6());
			field.setSjg7(lqziduanfield.getSjg7());
			field.setClwd(lqziduanfield.getClwd());
			field.setGlwd(lqziduanfield.getGlwd());
			field.setLqwd(lqziduanfield.getLqwd());
			field.setSjtjj(lqziduanfield.getSjtjj());
			field.setSjf1(lqziduanfield.getSjf1());
			field.setSjysb(lqziduanfield.getSjysb());
	
			field.setSjf2(lqziduanfield.getSjf2());
			field.setSbbh("设备编号");
			field.setSjlq(lqziduanfield.getSjlq());
			return field;
		}
}
