package com.mss.shtoone.app.action.lq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.app.action.BaseAction;
import com.mss.shtoone.app.domain.BhzInfoEntity;
import com.mss.shtoone.app.domain.SWChaobiaoItemEntity;
import com.mss.shtoone.app.domain.SWWraningStatisticsEntity;
import com.mss.shtoone.app.domain.lq.LQChaobiaoItemEntity;
import com.mss.shtoone.app.domain.lq.LQWraningStatisticsEntity;
import com.mss.shtoone.app.domain.lq.LQziduancfgItemEntity;
import com.mss.shtoone.app.persistence.hibernate.AppServiceHibernateDAO;
import com.mss.shtoone.app.persistence.hibernate.lq.AppLqServiceHibernateDAO;
import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.LiqingmanualphbView;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.ShuiwenmanualphbView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.QueryService;
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

			String chuli = sw.getChulijieguo();

			String shenhe = sw.getYezhuyijian();

			// 本条数据是否审核
			if (StringUtil.isNotEmpty(shenhe)) {
				sc.setShenhe("1");
			} else {
				sc.setShenhe("0");
			}
			// 本条数据是否处置
			if (StringUtil.isNotEmpty(chuli)) {
				sc.setChuli("1");
			} else {
				sc.setChuli("0");
			}

			simplifylist.add(sc);
		}
		
		LQChaobiaoItemEntity field = lqapField(lqziduanfield);
		
		LQziduancfgItemEntity lqisshow2 = lqapField2(lqisshow);
		
		
		
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
		
}
