package com.mss.shtoone.app.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.app.domain.AppInterfaceChaobiaoEntity;
import com.mss.shtoone.app.domain.SWWraningStatisticsEntity;
import com.mss.shtoone.app.persistence.hibernate.AppServiceHibernateDAO;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.util.GetDate;
import com.mss.shtoone.util.JsonUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

import net.sf.json.JSONObject;

@Controller
@Namespace("/lqApp")
public class LqAppInterfaceAction extends BaseAction {

	@Autowired
	private AppServiceHibernateDAO appSystemService;

	/**
	 * 沥青预警统计
	 * 
	 * @param request
	 * @param response
	 */
//	@Action("lqWarningStatistics")
//	public void lqWarningStatistics() {
//
//		ActionContext context = ActionContext.getContext();
//		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
//		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
//
//		JsonUtil.responseUTF8(response);
//		JSONObject returnJsonObj = new JSONObject();
//		try {
//			String userType = request.getParameter("userType");
//			String biaoduan = request.getParameter("biaoduan");// 标段
//			String xmb = request.getParameter("xmb");// 项目部
//			String startTime = request.getParameter("startTime");// 开始时间(时间戳)
//			String endTime = request.getParameter("endTime");// 结束时间(时间戳)
//			String shebeibianhao = request.getParameter("shebeibianhao");
//
//			if (null == startTime && null == endTime) {
//				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//				Calendar day = Calendar.getInstance();
//				endTime = sdf.format(day.getTime());
//				day.add(Calendar.MONTH, -1);
//				startTime = sdf.format(day.getTime());
//			} else {
//				startTime = GetDate.TimetmpConvetDateTime(startTime);// 开始时间
//				endTime = GetDate.TimetmpConvetDateTime(endTime);// 终止时间
//			}
//
//			Integer a = biaoduan == null ? null : Integer.valueOf(biaoduan);
//			Integer b = xmb == null ? null : Integer.valueOf(xmb);
//
//			List<AppInterfaceChaobiaoEntity> lqxxList = null;
//
//			List<SWWraningStatisticsEntity> list = new ArrayList<SWWraningStatisticsEntity>();
//
//			if ("1".equals(userType)) {
//				List<Biaoduanxinxi> bdList = appSystemService.bdList();
//				for (int i = 0; i < bdList.size(); i++) {
//					Integer bdId = bdList.get(i).getId();
//
//					lqxxList = appSystemService.lqsmstongji(startTime, endTime, bdId, null, shebeibianhao,
//							StringUtil.getQueryFieldNameByUserType(Integer.valueOf(userType)), bdId, 0);
//
//					Map<String, String> map = appSystemService.getLqCbcz(startTime, endTime, bdId, null);
//
//					String cczplv = "0.00";
//					String mczplv = "0.00";
//					String hczplv = "0.00";
//
//					SWWraningStatisticsEntity lqs = new SWWraningStatisticsEntity();
//
//					lqs.setCbpanshu("0");
//					lqs.setMcbpanshu("0");
//					lqs.setHcbpanshu("0");
//					lqs.setCblv("0.00");
//					lqs.setMcblv("0.00");
//					lqs.setHcblv("0.00");
//					lqs.setCczpanshu("0");
//					lqs.setMczpanshu("0");
//					lqs.setHczpanshu("0");
//					lqs.setCzlv("0.00");
//					lqs.setMczlv("0.00");
//					lqs.setHczlv("0.00");
//					lqs.setTotalFangliang("0");
//					lqs.setTotalPanshu("0");
//
//					lqs.setBanhezhanminchen(bdList.get(i).getBiaoduanminchen());
//
//					// 取第2 3 4 条数据 (初 中 高)
//					for (int j = 0; j < lqxxList.size(); j++) {
//
//						AppInterfaceChaobiaoEntity s = lqxxList.get(j);
//
//						if (s.getChangliang() == null || s.getChangliang().equals("0")) {
//							break;
//						}
//
//						switch (j) {
//						// 初级
//						case 1:
//
//							if (map.get("a") != null) {
//								cczplv = formatFloat(
//										Float.parseFloat(map.get("a")) * 100 / Float.parseFloat(s.getCbps()), "#0.00");
//							}
//
//							lqs.setCzlv(cczplv);
//							lqs.setCczpanshu(map.get("a"));
//							lqs.setCblv(s.getCblv());
//							lqs.setCbpanshu(s.getCbps());
//							lqs.setRemark(s.getDengji());
//							lqs.setBiaoDuanId(bdId + "");
//							lqs.setXmbId("-1");
//							lqs.setTotalFangliang(s.getChangliang());
//							lqs.setTotalPanshu(s.getPanshu());
//							break;
//						// 中级
//						case 2:
//
//							if (map.get("b") != null) {
//								mczplv = formatFloat(
//										Float.parseFloat(map.get("b")) * 100 / Float.parseFloat(s.getCbps()), "#0.00");
//							}
//
//							lqs.setMczlv(mczplv);
//							lqs.setMczpanshu(map.get("b"));
//							lqs.setMcblv(s.getCblv());
//							lqs.setMcbpanshu(s.getCbps());
//							break;
//						// 高级
//						case 3:
//
//							if (map.get("c") != null) {
//								hczplv = formatFloat(
//										Float.parseFloat(map.get("c")) * 100 / Float.parseFloat(s.getCbps()), "#0.00");
//							}
//
//							lqs.setHczlv(hczplv);
//							lqs.setHczpanshu(map.get("c"));
//							lqs.setHcblv(s.getCblv());
//							lqs.setHcbpanshu(s.getCbps());
//							break;
//						default:
//							break;
//						}
//					}
//
//					list.add(lqs);
//				}
//			} else if ("2".equals(userType)) {
//				List<Xiangmubuxinxi> xmbList = appSystemService.getXmbByBD(a + "");
//				for (int i = 0; i < xmbList.size(); i++) {
//
//					Integer xmbId = xmbList.get(i).getId();
//
//					lqxxList = appSystemService.lqsmstongji(startTime, endTime, a, xmbId, shebeibianhao,
//							StringUtil.getQueryFieldNameByUserType(Integer.valueOf(userType)), a, 0);
//
//					Map<String, String> map = appSystemService.getLqCbcz(startTime, endTime, a, xmbId);
//
//					String cczplv = "0.00";
//					String mczplv = "0.00";
//					String hczplv = "0.00";
//
//					SWWraningStatisticsEntity lqs = new SWWraningStatisticsEntity();
//
//					lqs.setCbpanshu("0");
//					lqs.setMcbpanshu("0");
//					lqs.setHcbpanshu("0");
//					lqs.setCblv("0.00");
//					lqs.setMcblv("0.00");
//					lqs.setHcblv("0.00");
//					lqs.setCczpanshu("0");
//					lqs.setMczpanshu("0");
//					lqs.setHczpanshu("0");
//					lqs.setCzlv("0.00");
//					lqs.setMczlv("0.00");
//					lqs.setHczlv("0.00");
//					lqs.setTotalFangliang("0");
//					lqs.setTotalPanshu("0");
//
//					lqs.setBanhezhanminchen(xmbList.get(i).getXiangmubuminchen());
//
//					// 取第2 3 4 条数据 (初 中 高)
//					for (int j = 0; j < lqxxList.size(); j++) {
//
//						AppInterfaceChaobiaoEntity s = lqxxList.get(j);
//
//						if (s.getChangliang() == null || s.getChangliang().equals("0")) {
//							break;
//						}
//
//						switch (j) {
//						// 初级
//						case 1:
//							if (map.get("a") != null) {
//								cczplv = formatFloat(
//										Float.parseFloat(map.get("a")) * 100 / Float.parseFloat(s.getCbps()), "#0.00");
//							}
//
//							lqs.setCzlv(cczplv);
//							lqs.setCczpanshu(map.get("a"));
//							lqs.setCblv(s.getCblv());
//							lqs.setCbpanshu(s.getCbps());
//							lqs.setRemark(s.getDengji());
//							lqs.setBiaoDuanId(a + "");
//							lqs.setXmbId(xmbId + "");
//							lqs.setTotalFangliang(s.getChangliang());
//							lqs.setTotalPanshu(s.getPanshu());
//							break;
//						// 中级
//						case 2:
//							if (map.get("b") != null) {
//								mczplv = formatFloat(
//										Float.parseFloat(map.get("b")) * 100 / Float.parseFloat(s.getCbps()), "#0.00");
//							}
//
//							lqs.setMczlv(mczplv);
//							lqs.setMczpanshu(map.get("b"));
//							lqs.setMcblv(s.getCblv());
//							lqs.setMcbpanshu(s.getCbps());
//							break;
//						// 高级
//						case 3:
//							if (map.get("c") != null) {
//								hczplv = formatFloat(
//										Float.parseFloat(map.get("c")) * 100 / Float.parseFloat(s.getCbps()), "#0.00");
//							}
//
//							lqs.setHczlv(hczplv);
//							lqs.setHczpanshu(map.get("c"));
//							lqs.setHcblv(s.getCblv());
//							lqs.setHcbpanshu(s.getCbps());
//							break;
//						default:
//							break;
//						}
//					}
//
//					list.add(lqs);
//				}
//			}
//
//			if (list.size() > 0) {
//				returnJsonObj.put("data", list);
//				returnJsonObj.put("success", true);
//			} else {
//				returnJsonObj.put("data", "[]");
//				returnJsonObj.put("success", false);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			System.out.println("获取水稳超标数据失败");
//			returnJsonObj.put("data", "[]");
//			returnJsonObj.put("success", false);
//		}
//		responseOutWrite(response, returnJsonObj);
//	}

}
