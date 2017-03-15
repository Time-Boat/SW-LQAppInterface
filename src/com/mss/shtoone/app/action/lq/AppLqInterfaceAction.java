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
import com.mss.shtoone.app.domain.LQWraningStatisticsEntity;
import com.mss.shtoone.app.domain.SWWraningStatisticsEntity;
import com.mss.shtoone.app.persistence.hibernate.AppLqServiceHibernateDAO;
import com.mss.shtoone.app.persistence.hibernate.AppServiceHibernateDAO;
import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
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
				List<Banhezhanxinxi> bhzList = appSystemService.getBhzByXmb(a + "");
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
}
