package com.mss.shtoone.app.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.app.domain.AppLoginLogEntity;
import com.mss.shtoone.app.domain.AppSWMaterialEntity;
import com.mss.shtoone.app.domain.BhzInfoEntity;
import com.mss.shtoone.app.domain.SWChaobiaoCZSHInfo;
import com.mss.shtoone.app.domain.SWChaobiaoItemEntity;
import com.mss.shtoone.app.domain.SWMaterialStatisticsData;
import com.mss.shtoone.app.domain.SWWraningStatisticsEntity;
import com.mss.shtoone.app.domain.SWXQHeadInfoEntity;
import com.mss.shtoone.app.persistence.hibernate.AppServiceHibernateDAO;
import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.Muser;
import com.mss.shtoone.domain.Role;
import com.mss.shtoone.domain.ShuiwenmanualphbView;
import com.mss.shtoone.domain.ShuiwenphbView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.Shuiwenxixxdanjia;
import com.mss.shtoone.domain.Shuiwenxixxjieguo;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.service.QueryService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.service.UserService;
import com.mss.shtoone.util.GetDate;
import com.mss.shtoone.util.JsonUtil;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

/**
 * APP数据接口处理类
 * 
 * @author
 * 
 */
@Controller
@Namespace("/app")
public class AppInterfaceAction extends BaseAction {

	@Autowired
	private UserService userService;

	@Autowired
	private AppServiceHibernateDAO appSystemService;

	@Autowired
	private QueryService queryService;

	@Autowired
	private SystemService sysService;

	@Action("AppLogin")
	public void AppLogin() {

		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

		JsonUtil.responseUTF8(response);
		JSONObject returnJsonObj = new JSONObject();
		try {
			String userName = request.getParameter("userName");// 用户名
			userName = new String(userName.getBytes("iso-8859-1"), "utf-8"); // android
																				// 中文用户名登录乱码
			String userPwd = request.getParameter("userPwd");// 密码
			String OSType = request.getParameter("OSType");// 当前登录手机类型 1:手机短信
															// 2:安卓3:苹果
			System.out.println("APP登录:用户信息>>登录名【" + userName + "】密码【" + userPwd + "】手机类型(2:安卓3:苹果)【" + OSType + "】");
			if (StringUtil.Null2Blank(userName).length() > 0 && StringUtil.Null2Blank(userPwd).length() > 0) {
				Muser user = userService.getUserByName(userName);
				if (null != user && user.getPwd() != null) {
					// 加密后密码
					String pwd = user.getPwd();
					if (user != null && pwd.equals(userPwd)) {
						returnJsonObj.put("userFullName", user.getFullname());// 当前登录用户名称
						Integer userType = user.getUsertype();
						returnJsonObj.put("userType", userType + "");// 获取用户类型
																		// 业主：1
																		// 标段：2
																		// 项目部：3
																		// 拌合站：5

						if (userType != 1) {
							returnJsonObj.put("biaoshi", user.getBiaoshiid() + "");// 所属类型的id
						} else {
							returnJsonObj.put("biaoshi", "");
						}

						// app登录日志记录
						String message = "用户: " + user.getName() + "[" + user.getUsertype() + "]" + "登录成功";
						AppLoginLogEntity log = new AppLoginLogEntity();
						log.setLoginTime(GetDate.getCurrentTime());
						log.setOSType(OSType);
						log.setUserName(userName);
						log.setMessage(message);
						appSystemService.saveLog(log);

						StringBuffer sbf = new StringBuffer();
						Set<Role> list = user.getRoles();
						for (Role r : list) {
							sbf.append(r.getId() + ",");
						}
						// 2 16 17 18 19
						boolean a = sbf.toString().contains(",2");
						boolean b = sbf.toString().contains(",16");
						boolean c = sbf.toString().contains(",17");
						boolean d = sbf.toString().contains(",18");
						boolean e = sbf.toString().contains(",19");

						// 0:没有审核全选 1:有审核权限
						returnJsonObj.put("shenehe", 0);
						if (a && b && c && d && e) {
							returnJsonObj.put("shenehe", 1);
						}

						returnJsonObj.put("success", true);
					} else {
						System.out.println("用户【" + userName + "】密码【" + userPwd + "】错误！");
						returnJsonObj.put("data", "[]");
						returnJsonObj.put("success", false);
					}
				} else {
					System.out.println("用户【" + userName + "】不存在！");
					returnJsonObj.put("data", "[]");
					returnJsonObj.put("success", false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("登陆异常");
			returnJsonObj.put("data", "[]");
			returnJsonObj.put("success", false);
		}
		responseOutWrite(response, returnJsonObj);
	}

	@Action("departTree")
	public void departTree() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

		JsonUtil.responseUTF8(response);
		JSONObject returnJsonObj = new JSONObject();

		// 用户类型
		String userType = request.getParameter("userType");
		// 标识 相关类型id
		String biaoshiid = request.getParameter("biaoshiid");

		// 模块类型 沥青2 水稳6
		String modelType = request.getParameter("modelType");

		List<Biaoduanxinxi> bdList = new ArrayList();
		List<Xiangmubuxinxi> xmbList = new ArrayList();
		List<Banhezhanxinxi> bhzList = new ArrayList();
		StringBuffer sbf;

		try {
			// 当前用户所属的类型，根据当前类型再往下面找一级
			if ("1".equals(userType)) { // 业主 能看到所有标段
				// 标段信息
				bdList = appSystemService.bdList();
				// 项目部信息
				xmbList = appSystemService.xmbList();
				// 拌合站信息
				bhzList = appSystemService.bhzList(modelType);
			} else if ("2".equals(userType)) { // 标段 能看到所属标段下的组织机构
				xmbList = appSystemService.getXmbByBD(biaoshiid);
				sbf = new StringBuffer();
				for (Xiangmubuxinxi x : xmbList) {
					sbf.append(x.getId() + ",");
				}
				bhzList = appSystemService.getBhzByXmb(sbf.substring(0, sbf.length() - 1), modelType);
			} else if ("3".equals(userType)) { // 项目部 只能看到所属项目部下的所有组织机构
				Xiangmubuxinxi xmb = appSystemService.getXmbByid(biaoshiid);
				xmbList.add(xmb);
				bhzList = appSystemService.getBhzByXmb(xmb.getId() + "", modelType);
			} else if ("5".equals(userType)) { // 拌合站 只能看到指定的拌合站
				Banhezhanxinxi bhz = appSystemService.getBhzById(biaoshiid);
				bhzList.add(bhz);
			}

			// List list = new ArrayList();
			// appSystemService.departTree(list,StringUtil.getQueryFieldNameByUserType(Integer.parseInt(userType)),biaoshiid);

			List<BhzInfoEntity> bzList = new ArrayList();
			for (Banhezhanxinxi b : bhzList) {
				BhzInfoEntity bz = new BhzInfoEntity();
				bz.setBanhezhanminchen(b.getBanhezhanminchen());
				bz.setBiaoduanid(b.getBiaoduanid() + "");
				bz.setGprsbianhao(b.getGprsbianhao());
				bz.setId(b.getId() + "");
				bz.setJianchen(b.getJianchen());
				bz.setShebeileixin(b.getShebeileixin());
				bz.setXiangmubuid(b.getXiangmubuid() + "");
				bzList.add(bz);
			}

			returnJsonObj.put("biaoduan", bdList);
			returnJsonObj.put("xmb", xmbList);
			returnJsonObj.put("bhz", bzList);
			returnJsonObj.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			returnJsonObj.put("data", "[]");
			returnJsonObj.put("success", false);
		}
		responseOutWrite(response, returnJsonObj);
	}

	// 机器列表
	@Action("machineList")
	public void machinelist() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

		JsonUtil.responseUTF8(response);
		JSONObject returnJsonObj = new JSONObject();

		// 组织机构类型
		String departType = request.getParameter("departType");
		// 标识 相关类型id
		String biaoshiid = request.getParameter("biaoshiid");
		// 拌合机类型
		String machineType = request.getParameter("machineType");

		List<Banhezhanxinxi> bhzList = null;
		List<BhzInfoEntity> bhzInfoList = new ArrayList<BhzInfoEntity>();

		String fn = StringUtil.getQueryFieldNameByUserType(Integer.valueOf(departType));

		try {
			// 设备信息
			bhzList = appSystemService.getMachine(fn, biaoshiid, machineType);

			if (bhzList.size() > 0) {
				for (Banhezhanxinxi bhz : bhzList) {
					BhzInfoEntity b = new BhzInfoEntity();
					b.setId(bhz.getId() + "");
					b.setBanhezhanminchen(bhz.getBanhezhanminchen());
					b.setBiaoduanid(bhz.getBiaoduanid() + "");
					b.setJianchen(bhz.getJianchen());
					b.setShebeileixin(bhz.getShebeileixin());
					b.setXiangmubuid(bhz.getXiangmubuid() + "");
					b.setGprsbianhao(bhz.getGprsbianhao());
					bhzInfoList.add(b);
				}
				returnJsonObj.put("data", bhzInfoList);
			} else {
				returnJsonObj.put("data", "[]");
			}
			returnJsonObj.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			returnJsonObj.put("data", "[]");
			returnJsonObj.put("success", false);
		}
		responseOutWrite(response, returnJsonObj);
	}

	/**
	 * 水稳预警统计
	 * 
	 * @param request
	 * @param response
	 */
	@Action("warningStatistics")
	public void warningStatistics() {

		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

		JsonUtil.responseUTF8(response);
		JSONObject returnJsonObj = new JSONObject();
		try {
			String departType = request.getParameter("departType");
			String biaoshiid = request.getParameter("biaoshiid");//
			String startTime = request.getParameter("startTime");// 开始时间(时间戳)
			String endTime = request.getParameter("endTime");// 结束时间(时间戳)
			String shebeibianhao = request.getParameter("shebeibianhao");

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

			Integer a = biaoshiid == "" || biaoshiid == null ? null : Integer.valueOf(biaoshiid);
			// Integer b = xmb == "" || xmb == null ? null :
			// Integer.valueOf(xmb);

			List<SWWraningStatisticsEntity> list = new ArrayList<SWWraningStatisticsEntity>();

			if ("1".equals(departType)) {
				List<Biaoduanxinxi> bdList = appSystemService.bdList();
				for (int i = 0; i < bdList.size(); i++) {
					Integer bdId = bdList.get(i).getId();

					List<ShuiwenxixxView> sList = appSystemService.swsmstongji(startTime, endTime, bdId, null,
							shebeibianhao, StringUtil.getQueryFieldNameByUserType(Integer.valueOf(departType)), bdId,
							0);

					Map<String, String> map = appSystemService.getCbcz(startTime, endTime, bdId, null, shebeibianhao);

					String cczplv = "0.00";
					String mczplv = "0.00";
					String hczplv = "0.00";

					SWWraningStatisticsEntity sws = new SWWraningStatisticsEntity();

					sws.setBsId(bdId + "");

					sws.setBanhezhanminchen(bdList.get(i).getBiaoduanminchen());

					// 取第1 3 5 7条数据
					for (int j = 2; j < sList.size(); j += 2) {

						ShuiwenxixxView s = sList.get(j);

						if (s.getPanshu() == null || s.getPanshu().equals("0")) {
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
							sws.setTotalFangliang(s.getGlchangliang());
							sws.setTotalPanshu(s.getPanshu());
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
							sws.setTotalFangliang(s.getGlchangliang());
							sws.setTotalPanshu(s.getPanshu());
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
							sws.setTotalFangliang(s.getGlchangliang());
							sws.setTotalPanshu(s.getPanshu());
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

					List<ShuiwenxixxView> sList = appSystemService.swsmstongji(startTime, endTime, a, xmbId,
							shebeibianhao, StringUtil.getQueryFieldNameByUserType(Integer.valueOf(departType)), a, 0);

					Map<String, String> map = appSystemService.getCbcz(startTime, endTime, a, xmbId, shebeibianhao);

					String cczplv = "0.00";
					String mczplv = "0.00";
					String hczplv = "0.00";

					SWWraningStatisticsEntity sws = new SWWraningStatisticsEntity();

					sws.setBsId(xmbId + "");

					sws.setBanhezhanminchen(xmbList.get(i).getXiangmubuminchen());

					// 取第1 3 5 7条数据
					for (int j = 2; j < sList.size(); j += 2) {
						ShuiwenxixxView s = sList.get(j);

						if (s.getPanshu() == null || s.getPanshu().equals("0")) {
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
							sws.setTotalFangliang(s.getGlchangliang());
							sws.setTotalPanshu(s.getPanshu());
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
							sws.setTotalFangliang(s.getGlchangliang());
							sws.setTotalPanshu(s.getPanshu());
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
							sws.setTotalFangliang(s.getGlchangliang());
							sws.setTotalPanshu(s.getPanshu());
							break;
						default:
							break;
						}
					}

					list.add(sws);
				}
			} else if ("3".equals(departType)) {
				List<Banhezhanxinxi> bhzList = appSystemService.getBhzByXmb(a + "", "6");
				for (int i = 0; i < bhzList.size(); i++) {

					int id = bhzList.get(i).getId();

					List<ShuiwenxixxView> sList = appSystemService.swsmstongji(startTime, endTime, null, a,
							shebeibianhao, StringUtil.getQueryFieldNameByUserType(Integer.valueOf(departType)), a, 0);

					Map<String, String> map = appSystemService.getCbcz(startTime, endTime, null, null,
							bhzList.get(i).getGprsbianhao());

					String cczplv = "0.00";
					String mczplv = "0.00";
					String hczplv = "0.00";

					SWWraningStatisticsEntity sws = new SWWraningStatisticsEntity();

					sws.setBsId(id + "");

					sws.setBanhezhanminchen(bhzList.get(i).getBanhezhanminchen());

					// 取第1 3 5 7条数据
					for (int j = 2; j < sList.size(); j += 2) {
						ShuiwenxixxView s = sList.get(j);

						if (s.getPanshu() == null || s.getPanshu().equals("0")) {
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
							sws.setTotalFangliang(s.getGlchangliang());
							sws.setTotalPanshu(s.getPanshu());
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
							sws.setTotalFangliang(s.getGlchangliang());
							sws.setTotalPanshu(s.getPanshu());
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
							sws.setTotalFangliang(s.getGlchangliang());
							sws.setTotalPanshu(s.getPanshu());
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
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("获取水稳超标数据失败");
			returnJsonObj.put("data", "[]");
			returnJsonObj.put("success", false);
		}
		responseOutWrite(response, returnJsonObj);
	}

	// 超标查询
	@Action("chaoBiaoList")
	public void chaoBiaoList() {

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
		String cllx = request.getParameter("cllx"); // 处理类型 0 全部 1 未处理 2 已处理 3
													// 未审批 4 已审批

		if (!StringUtil.isNotEmpty(departType) && !StringUtil.isNotEmpty(biaoshiid)) {
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

		ShuiwenziduancfgView swisshow = queryService.getswcfgisShow40(shebeibianhao);
		if (swisshow == null) {
			swisshow = queryService.getswcfgisShow(shebeibianhao);
		}
		ShuiwenziduancfgView swziduanfield = queryService.getSwfield(shebeibianhao);

		Integer c = biaoshiid == "" || biaoshiid == null ? null : Integer.valueOf(biaoshiid);
		Integer d = chaobiaolx == "" || chaobiaolx == null ? null : Integer.valueOf(chaobiaolx);
		Integer e = cllx == "" || cllx == null ? null : Integer.valueOf(cllx);

		GenericPageMode s = appSystemService.swchaobiaomanualviewlist(shebeibianhao, startTime, endTime, null, null,
				StringUtil.getQueryFieldNameByUserType(Integer.parseInt(departType)), c, pageNo, maxPageItems, d,
				swisshow, e, "");

		List<ShuiwenmanualphbView> list = s.getDatas();

		List<SWChaobiaoItemEntity> simplifylist = new ArrayList();

		for (ShuiwenmanualphbView sw : list) {
			SWChaobiaoItemEntity sc = new SWChaobiaoItemEntity();
			sc.setBianhao(sw.getBianhao() + "");
			sc.setBzhName(sw.getBanhezhanminchen());
			sc.setClTime(sw.getShijian());
			sc.setSjf1(sw.getSjfl1());
			sc.setSjf2(sw.getSjfl2());
			sc.setSjg1(sw.getSjgl1());
			sc.setSjg2(sw.getSjgl2());
			sc.setSjg3(sw.getSjgl3());
			sc.setSjg4(sw.getSjgl4());
			sc.setSjg5(sw.getSjgl5());
			sc.setZcl(sw.getGlchangliang());

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

		SWChaobiaoItemEntity field = swapField(swziduanfield);
		SWChaobiaoItemEntity show = swapField(swisshow);
		show.setZcl(swisshow.getGlchangliang());
		
		field.setZcl("总产量");
		field.setBianhao("编号");
		field.setBzhName("拌合站名称");

		try {
			returnJsonObj.put("isShow", show);
			returnJsonObj.put("field", field);
			returnJsonObj.put("data", simplifylist);
			returnJsonObj.put("success", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			returnJsonObj.put("data", "[]");
			returnJsonObj.put("success", false);
		}
		responseOutWrite(response, returnJsonObj);
	}

	@Action("swmaterial")
	public void swmaterial() {
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
		Integer b = biaoshiid == "" || biaoshiid == null ? null : Integer.valueOf(biaoshiid); // 不赋值的话会报错

		ShuiwenphbView swp = appSystemService.appSwmateriallist(startTime, endTime, shebeibianhao, null, null,
				StringUtil.getQueryFieldNameByUserType(a), b);

		ShuiwenziduancfgView swziduanfield = queryService.getSwfield(shebeibianhao);

		List<AppSWMaterialEntity> asw = bean2List1(swp, swziduanfield, -1);

		try {
			returnJsonObj.put("data", asw);
			returnJsonObj.put("success", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			returnJsonObj.put("data", "[]");
			returnJsonObj.put("success", false);
		}
		responseOutWrite(response, returnJsonObj);
	}

	// 超标查询
	@Action("chaoBiaoXQ")
	public void chaoBiaoXQ() {

		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);

		JsonUtil.responseUTF8(response);
		JSONObject returnJsonObj = new JSONObject();

		String bianhao = request.getParameter("bianhao");
		String shebeibianhao = request.getParameter("shebeibianhao");

		ShuiwenmanualphbView sw = queryService.swmanualphbfindById(Integer.parseInt(bianhao));

		Shuiwenxixxjieguo swjg = sysService.getSwjieguobybh(Integer.parseInt(bianhao));

		SWChaobiaoCZSHInfo simpleJg = new SWChaobiaoCZSHInfo();
		// 监理
		simpleJg.setChulifangshi(swjg.getChulifangshi());
		simpleJg.setChulijieguo(swjg.getChulijieguo());
		simpleJg.setChuliren(swjg.getJianliren());
		simpleJg.setShenpidate(swjg.getShenpidate());
		simpleJg.setWentiyuanyin(swjg.getWentiyuanyin());
		// 业主
		simpleJg.setConfirmdate(swjg.getConfirmdate());
		simpleJg.setYezhuren(swjg.getYezhuren());
		simpleJg.setYezhuyijian(swjg.getYezhuyijian());
		simpleJg.setBeizhu(swjg.getBeizhu());

		// 头信息
		SWXQHeadInfoEntity swHead = new SWXQHeadInfoEntity();
		swHead.setBaocunshijian(sw.getCaijishijian());
		swHead.setBhjName(sw.getBanhezhanminchen());
		swHead.setChuliaoshijian(sw.getShijian());
		swHead.setZcl(sw.getGlchangliang());

		ShuiwenziduancfgView swziduanfield = queryService.getSwfield(shebeibianhao);
		// SWChaobiaoItemEntity field = swapField(swziduanfield);

		try {
			List<AppSWMaterialEntity> asw = bean2List(sw, swziduanfield, -1);
			returnJsonObj.put("swHead", swHead);
			returnJsonObj.put("swData", asw);
			returnJsonObj.put("swjg", simpleJg);
			returnJsonObj.put("success", true);
		} catch (Exception ex) {
			ex.printStackTrace();
			returnJsonObj.put("data", "[]");
			returnJsonObj.put("success", false);
		}
		responseOutWrite(response, returnJsonObj);
	}

	// 获取指定实体类中的指定数据
	public <T> List<AppSWMaterialEntity> bean2List(T t, ShuiwenziduancfgView hbfield, int objType) {

		List<AppSWMaterialEntity> bciList = new ArrayList<AppSWMaterialEntity>();

		String[] cfg = { "fl1", "fl2", "gl1", "gl2", "gl3", "gl4", "gl5" };

		String[] cfg1 = { "Llf1", "Llf2", "Llg1", "Llg2", "Llg3", "Llg4", "Llg5" };

		for (int i = 0; i < cfg1.length; i++) {
			AppSWMaterialEntity swm = new AppSWMaterialEntity();
			try {
				String name = (String) hbfield.getClass().getMethod("get" + "Sj" + cfg[i], new Class[] {})
						.invoke(hbfield, new Object[] {});
				String yongliang = (String) t.getClass().getMethod("get" + "Sj" + cfg[i], new Class[] {}).invoke(t,
						new Object[] {});
				String scpeibi = (String) t.getClass().getMethod("get" + "Persj" + cfg[i], new Class[] {}).invoke(t,
						new Object[] {});
				String sgpeibi = (String) t.getClass().getMethod("get" + "Perll" + cfg[i], new Class[] {}).invoke(t,
						new Object[] {});
				String mbpeibi = (String) t.getClass().getMethod("get" + cfg1[i], new Class[] {}).invoke(t,
						new Object[] {});
				String wucha = (String) t.getClass().getMethod("get" + "Manualw" + cfg[i], new Class[] {}).invoke(t,
						new Object[] {});

				swm.setName(name);
				swm.setYongliang(yongliang);
				swm.setScpeibi(scpeibi);
				swm.setSgpeibi(sgpeibi);
				swm.setMbpeibi(mbpeibi);
				swm.setWucha(wucha);

				bciList.add(swm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bciList;
	}

	//
	public <T> List<AppSWMaterialEntity> bean2List1(T t, ShuiwenziduancfgView hbfield, int objType) {

		List<AppSWMaterialEntity> bciList = new ArrayList<AppSWMaterialEntity>();

		String[] cfg = { "fl1", "fl2", "gl1", "gl2", "gl3", "gl4", "gl5" };

		for (int i = 0; i < cfg.length; i++) {
			AppSWMaterialEntity swm = new AppSWMaterialEntity();
			try {
				String name = (String) hbfield.getClass().getMethod("get" + "Sj" + cfg[i], new Class[] {})
						.invoke(hbfield, new Object[] {});
				String yongliang = (String) t.getClass().getMethod("get" + "Sj" + cfg[i], new Class[] {}).invoke(t,
						new Object[] {});
				String mbpeibi = (String) t.getClass().getMethod("get" + "Ll" + cfg[i], new Class[] {}).invoke(t,
						new Object[] {});
				String wucha = (String) t.getClass().getMethod("get" + "Persj" + cfg[i], new Class[] {}).invoke(t,
						new Object[] {});

				swm.setName(name);
				swm.setYongliang(yongliang);
				swm.setMbpeibi(mbpeibi);
				swm.setWucha(wucha);

				bciList.add(swm);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bciList;
	}

	// 简化字段名对象
	private SWChaobiaoItemEntity swapField(ShuiwenziduancfgView swziduanfield) {
		SWChaobiaoItemEntity field = new SWChaobiaoItemEntity();
		field.setClTime(swziduanfield.getShijian());
		field.setSjf1(swziduanfield.getSjfl1());
		field.setSjf2(swziduanfield.getSjfl2());
		field.setSjg1(swziduanfield.getSjgl1());
		field.setSjg2(swziduanfield.getSjgl2());
		field.setSjg3(swziduanfield.getSjgl3());
		field.setSjg4(swziduanfield.getSjgl4());
		field.setSjg5(swziduanfield.getSjgl5());
		return field;
	}

}
