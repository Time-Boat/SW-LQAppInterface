package com.mss.shtoone.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.Muser;
import com.mss.shtoone.domain.Role;
import com.mss.shtoone.domain.TopLiqingView;
import com.mss.shtoone.domain.UserPageMode;
import com.mss.shtoone.domain.Usertypexinxi;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.domain.Zuoyeduixinxi;
import com.mss.shtoone.service.SysService;
import com.mss.shtoone.service.SystemService;
import com.mss.shtoone.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/system")
public class LimitUserAction extends ActionSupport{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8379733586074055490L;
	private UserPageMode userpage;
	private List<Role> rolelist;
	private Map<Integer, String> listmap;
	private Map<Integer, String> usertypelistmap;
	private int userId;
	private Muser muser;
	private Integer[] userrole; 
	private String newpwd;
	private String username;
	private Integer biaoduan;
	private Integer xiangmubu;
	private Integer shebeibianhao;
	private Integer usertype;
	private Integer maxPageItems;
	private Integer pageNo;
	private Map<Integer, String> biaoduanlistmap;
	private Map<Integer, String> xmblistmap;
	private Map<Integer, String> bhzlistmap;

	@Autowired
	private SysService sysService;	
	
	@Autowired
	private SystemService systemService;
	
	@Action("limitmuserlist")
	public String limitmuserlist(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		setPageNo(1);
		if (null != request.getParameter("pageNo")) {
			setPageNo(Integer.parseInt(request.getParameter("pageNo")));
		}
		setMaxPageItems(20);
		if (null != request.getParameter("maxPageItems")) {
			setMaxPageItems(Integer.parseInt(request.getParameter("maxPageItems")));
		}
		biaoduanlistmap = new LinkedHashMap<Integer, String>();
		List<Biaoduanxinxi> bdlist = systemService.limitbdlist(request);
		for (Biaoduanxinxi bd : bdlist) {
			biaoduanlistmap.put(bd.getId(), bd.getBiaoduanminchen());
		}
		xmblistmap = new LinkedHashMap<Integer, String>();
		List<Xiangmubuxinxi> xmblist = systemService.limitxmblist(request, biaoduan);
		for (Xiangmubuxinxi xmb : xmblist) {
			xmblistmap.put(xmb.getId(), xmb.getXiangmubuminchen());
		}
		
		bhzlistmap = new LinkedHashMap<Integer, String>();
		List<TopLiqingView> toplist = systemService.limitlqlist(request, biaoduan, xiangmubu);
		for (TopLiqingView toplqView : toplist) {
			bhzlistmap.put(toplqView.getId(), toplqView
					.getBanhezhanminchen());
		}
		
		usertypelistmap = new LinkedHashMap<Integer, String>();	
		List<Usertypexinxi> utlist = systemService.limitutlist(request);
		for (Usertypexinxi ut : utlist) {
			usertypelistmap.put(ut.getId(), ut.getMinchen());
		}
		
		setUserpage(sysService.limitgetViewUser(request, username, usertype, biaoduan, xiangmubu, shebeibianhao, pageNo, maxPageItems));	
		return SUCCESS;
	}
	
	@Action("limitmuser")
	public String limitmuser(){
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		rolelist = sysService.limitgetRole();	
		listmap = new LinkedHashMap<Integer, String>();	
		usertypelistmap = new LinkedHashMap<Integer, String>();	
		List<Usertypexinxi> utlist = systemService.limitutlist(request);
		for (Usertypexinxi ut : utlist) {
			usertypelistmap.put(ut.getId(), ut.getMinchen());
		}
		if (userId > 0) {			
			setMuser(sysService.getMuserById(request, userId));
			if (null != muser) {
				Set<Role> roles = muser.getRoles();
				if (roles.size() > 0) {
					userrole = new Integer[roles.size()];
					int i = 0;				
					for (Role role : roles) {
						userrole[i] = role.getId();
						i++;
					}
				}	
				Integer utype = muser.getUsertype();
				if (null != utype) {
					switch (utype) {
					case 1:					
						break;
					case 2:
						List<Biaoduanxinxi> bdlist = systemService.limitbdlist(request);
						for (Biaoduanxinxi bd : bdlist) {
							listmap.put(bd.getId(), bd.getBiaoduanminchen());
						}
						break;
					case 3:
						List<Xiangmubuxinxi> xmblist = systemService.limitxmblist(request, biaoduan);
						for (Xiangmubuxinxi xmb : xmblist) {
							listmap.put(xmb.getId(), xmb.getXiangmubuminchen());
						}
						break;
					case 4:
						List<Zuoyeduixinxi> zydlist = systemService.limitzydlist(request, biaoduan, xiangmubu);
						for (Zuoyeduixinxi zyd : zydlist) {
							listmap.put(zyd.getId(), zyd.getZuoyeduiminchen());
						}
						break;
					case 5:
						List<Banhezhanxinxi> bhzlist = systemService.limitbhzlist(request, biaoduan, xiangmubu,"");
						for (Banhezhanxinxi bhz : bhzlist) {
							listmap.put(bhz.getId(), bhz.getBanhezhanminchen());
						}
						break;
					default:
						break;
					}
				}
			}
		}
		return SUCCESS;
	}
	
	@Action(value="limitmuseradd", results = @Result(name = "list", type = "redirect", location = "limitmuserlist?pid=3&record=20&"))
	public String limitmuseradd(){		
		if (null != muser && StringUtil.Null2Blank(muser.getName()).length()>0) {
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		Set<Role> roles = new HashSet<Role>();		
		if (null != userrole && userrole.length > 0) {
			for (int i = 0; i < userrole.length; i++) {
				Role rl = sysService.getRoleById(userrole[i]);
				roles.add(rl);
			}
		} else {
			if (null != muser.getIsmanager() && muser.getIsmanager().equalsIgnoreCase("1")) {
				List<Role> comroles = sysService.limitgetRole();
				for (Role role : comroles) {
					roles.add(role);
				}
			} else {
				roles.add(sysService.getRoleById(1));
			}
		}
		
		Muser us = sysService.getMuserById(request, muser.getId());
		if (null != us) {
			if (us.getFirstlogin() != null && us.getFirstlogin()==1) {
				muser.setFirstlogin(1);
			}
			if (StringUtil.Null2Blank(newpwd).length() > 0) {
				muser.setPwd(StringUtil.Null2Blank(newpwd));
			} else {
				muser.setPwd(us.getPwd());
			}
			Set<Role> oldroles = us.getRoles();
			for (Role role : oldroles) {
				if (role.getId() == 3) {
					roles.add(role);
					break;
				}
			}
		} else {
			muser.setFirstlogin(1);
		}		
		muser.setRoles(roles);			
		sysService.saveOrUpdate(muser);
		}
		
		return "list";
	}
	
	@Action(value="limitmuserdel", results = @Result(name = "list", type = "redirect", location = "limitmuserlist?pid=3&record=20&"))
	public String limitmuserdel(){	
		ActionContext context = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		sysService.del(request, userId);
		return "list";
	}	

	
	@Action("limitusertypechange")
	public String limitusertypechange() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out;
		try {
			out = response.getWriter();
			String usertype = request.getParameter("usertype");		
			Integer utype = null;
	        if(StringUtil.Null2Blank(usertype).length() > 0) {
	        	utype = Integer.valueOf(usertype);
				if (null != utype) {
					StringBuilder outstr = new StringBuilder();
					switch (utype) {
					case 1:					
						break;
					case 2:
						List<Biaoduanxinxi> bdlist = systemService.limitbdlist(request);
						for (Biaoduanxinxi bd : bdlist) {
							outstr.append(bd.getId());
			    			outstr.append(",");
			    			outstr.append(bd.getBiaoduanminchen());
			    			outstr.append("|");	
						}
						break;
					case 3:
						List<Xiangmubuxinxi> xmblist = systemService.limitxmblist(request, biaoduan);
						for (Xiangmubuxinxi xmb : xmblist) {
							outstr.append(xmb.getId());
			    			outstr.append(",");
			    			outstr.append(xmb.getXiangmubuminchen());
			    			outstr.append("|");	
						}
						break;
					case 4:
						List<Zuoyeduixinxi> zydlist = systemService.limitzydlist(request, biaoduan, xiangmubu);
						for (Zuoyeduixinxi zyd : zydlist) {
							outstr.append(zyd.getId());
			    			outstr.append(",");
			    			outstr.append(zyd.getZuoyeduiminchen());
			    			outstr.append("|");	
						}
						break;
					case 5:
						List<Banhezhanxinxi> bhzlist = systemService.limitbhzlist(request, biaoduan, xiangmubu,"");
						for (Banhezhanxinxi bhz : bhzlist) {
							outstr.append(bhz.getId());
			    			outstr.append(",");
			    			outstr.append(bhz.getBanhezhanminchen());
			    			outstr.append("|");	
						}
						break;
					default:
						break;
					}
					if (outstr.length() > 0) {
		    			outstr.deleteCharAt(outstr.length()-1);
		    			out.print(outstr.toString());
		    			out.flush();
					}
				}
	        }     	
	        out.close();
		} catch (IOException e) {
		}
		return null;
	}	



	public List<Role> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<Role> rolelist) {
		this.rolelist = rolelist;
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Muser getMuser() {
		return muser;
	}

	public void setMuser(Muser muser) {
		this.muser = muser;
	}

	


	public Integer[] getUserrole() {
		return userrole;
	}

	public void setUserrole(Integer[] userrole) {
		this.userrole = userrole;
	}

	public Map<Integer, String> getListmap() {
		return listmap;
	}

	public void setListmap(Map<Integer, String> listmap) {
		this.listmap = listmap;
	}

	public Map<Integer, String> getUsertypelistmap() {
		return usertypelistmap;
	}

	public void setUsertypelistmap(Map<Integer, String> usertypelistmap) {
		this.usertypelistmap = usertypelistmap;
	}

	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
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

	public Integer getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(Integer shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
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

	public Map<Integer, String> getBhzlistmap() {
		return bhzlistmap;
	}

	public void setBhzlistmap(Map<Integer, String> bhzlistmap) {
		this.bhzlistmap = bhzlistmap;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public UserPageMode getUserpage() {
		return userpage;
	}

	public void setUserpage(UserPageMode userpage) {
		this.userpage = userpage;
	}

	public Integer getMaxPageItems() {
		return maxPageItems;
	}

	public void setMaxPageItems(Integer maxPageItems) {
		this.maxPageItems = maxPageItems;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}	

}
