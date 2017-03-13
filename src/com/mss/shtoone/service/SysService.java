package com.mss.shtoone.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Muser;
import com.mss.shtoone.domain.Resource;
import com.mss.shtoone.domain.Role;
import com.mss.shtoone.domain.UserPageMode;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.domain.Zuoyeduixinxi;
import com.mss.shtoone.persistence.BanhezhanDAO;
import com.mss.shtoone.persistence.BiaoduanDAO;
import com.mss.shtoone.persistence.ResourceDAO;
import com.mss.shtoone.persistence.RoleDAO;
import com.mss.shtoone.persistence.UserDAO;
import com.mss.shtoone.persistence.XiangmubuDAO;
import com.mss.shtoone.persistence.ZuoyeduiDAO;
import com.mss.shtoone.util.StringUtil;

@Service
public class SysService {
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ResourceDAO resourceDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private XiangmubuDAO xmbDAO;
	
	@Autowired
	private BanhezhanDAO bhzDAO;
	
	@Autowired
	private ZuoyeduiDAO zydDAO;
	
	@Autowired
	private BiaoduanDAO biaoduanDAO;

	
	public List<Muser> getAllUser(){
		return userDAO.loadAll();
	}
	
	public UserPageMode limitgetViewUser(HttpServletRequest request, String username, Integer usertype, Integer biaoduan, 
			Integer xiangmubu, Integer shebeibianhao, int offset, int pagesize){
		int ut = StringUtil.getUserType(request);	
		int bsid = StringUtil.getBiaoshiId(request);
		return userDAO.viewlist(username, usertype, biaoduan, xiangmubu, shebeibianhao, ut, bsid, offset, pagesize);
	}
	
	public List<Muser> limitgetAllUser(HttpServletRequest request, Integer biaoduan, Integer xiangmubu, Integer shebeibianhao){
		int ut = StringUtil.getUserType(request);	
		List<Muser> userlist = new ArrayList<Muser>();
		int bsid = StringUtil.getBiaoshiId(request);
		StringBuilder bsstr = new StringBuilder("from muser where name <> 'systemmanager' and usertype>=");
		bsstr.append(ut);
		bsstr.append(" and (1=2");
		Integer zuoyedui=null;
		switch (ut) {	
		case 2:
			if (null == biaoduan) {
				biaoduan = bsid;
			}
			break;
		case 3:
			if (null == xiangmubu) {
				xiangmubu = bsid;
			}
			break;
		case 4:
			zuoyedui = bsid;
			break;
		case 5:
			if (null == shebeibianhao) {
				shebeibianhao = bsid;
			}
			break;
		default:
			break;
		}
		
		if (null != shebeibianhao) {			
			bsstr.append(" or (biaoshiid=");
			bsstr.append(shebeibianhao);
			bsstr.append(" and usertype=5))");
			userlist = userDAO.find(bsstr.toString());
		} 
		else if (null != zuoyedui) {
			bsstr.append(" or (biaoshiid=");
			bsstr.append(zuoyedui);
			bsstr.append(" and usertype=4)");
			List<Banhezhanxinxi> bhzlist = (List<Banhezhanxinxi>)bhzDAO.find("from Banhezhanxinxi where zuoyeduiid=?", zuoyedui);
			for (Banhezhanxinxi bhz : bhzlist) {
				bsstr.append(" or (biaoshiid=");
				bsstr.append(bhz.getId());
				bsstr.append(" and usertype=5)");
			}
			bsstr.append(")");
			userlist = userDAO.find(bsstr.toString());
		}
		else if (null != xiangmubu) {
			bsstr.append(" or (biaoshiid=");
			bsstr.append(xiangmubu);
			bsstr.append(" and usertype=3)");
			List<Zuoyeduixinxi> zydlist = (List<Zuoyeduixinxi>)zydDAO.find("from Zuoyeduixinxi where xiangmubuid=?", xiangmubu);
			for (Zuoyeduixinxi zyd : zydlist) {
				bsstr.append(" or (biaoshiid=");
				bsstr.append(zyd.getId());
				bsstr.append(" and usertype=4)");
			}
			List<Banhezhanxinxi> bhzlist = (List<Banhezhanxinxi>)bhzDAO.find("from Banhezhanxinxi where xiangmubuid=?", xiangmubu);
			for (Banhezhanxinxi bhz : bhzlist) {
				bsstr.append(" or (biaoshiid=");
				bsstr.append(bhz.getId());
				bsstr.append(" and usertype=5)");
			}
			bsstr.append(")");
			userlist = userDAO.find(bsstr.toString());
		} else if (null != biaoduan) {
			bsstr.append(" or (biaoshiid=");
			bsstr.append(biaoduan);
			bsstr.append(" and usertype=2)");
			List<Xiangmubuxinxi> xmblist = (List<Xiangmubuxinxi>)xmbDAO.find("from Xiangmubuxinxi where biaoduanid=?", biaoduan);
			for (Xiangmubuxinxi xmb : xmblist) {
				bsstr.append(" or (biaoshiid=");
				bsstr.append(xmb.getId());
				bsstr.append(" and usertype=3)");
			}
			List<Zuoyeduixinxi> zydlist = (List<Zuoyeduixinxi>)zydDAO.find("from Zuoyeduixinxi where biaoduanid=?", biaoduan);
			for (Zuoyeduixinxi zyd : zydlist) {
				bsstr.append(" or (biaoshiid=");
				bsstr.append(zyd.getId());
				bsstr.append(" and usertype=4)");
			}
			List<Banhezhanxinxi> bhzlist = (List<Banhezhanxinxi>)bhzDAO.find("from Banhezhanxinxi where biaoduanid=?", biaoduan);
			for (Banhezhanxinxi bhz : bhzlist) {
				bsstr.append(" or (biaoshiid=");
				bsstr.append(bhz.getId());
				bsstr.append(" and usertype=5)");
			}
			bsstr.append(")");	
			userlist = userDAO.find(bsstr.toString());
		}  else {				
			userlist = userDAO.find("from muser where name <> 'systemmanager'");
		}
		return userlist;
	}
	
	public int getDefaultBsidbyUsertype(Integer sourceut, Integer selectut, Integer bsid) {
		int i = 1;
		switch (sourceut) {
		case 2:
			switch (selectut) {
			case 2:
				i = bsid;
				break;
			case 3:
				List<Xiangmubuxinxi> xmblist = xmbDAO.find("from Xiangmubuxinxi where biaoduanid=?", bsid);
				if (xmblist.size() > 0) {
					i = xmblist.get(0).getId();
				}
				break;
			case 4:
				List<Zuoyeduixinxi> zydlist = zydDAO.find("from Zuoyeduixinxi where biaoduanid=?", bsid);
				if (zydlist.size() > 0) {
					i = zydlist.get(0).getId();
				}
				break;
			case 5:
				List<Banhezhanxinxi> bhzlist = bhzDAO.find("from Banhezhanxinxi where biaoduanid=?", bsid);
				if (bhzlist.size() > 0) {
					i = bhzlist.get(0).getId();
				}
				break;
			default:
				break;
			}
			break;
		case 3:
			switch (selectut) {
			case 3:
				i = bsid;
				break;
			case 4:
				List<Zuoyeduixinxi> zydlist = zydDAO.find("from Zuoyeduixinxi where xiangmubuid=?", bsid);
				if (zydlist.size() > 0) {
					i = zydlist.get(0).getId();
				}
				break;
			case 5:
				List<Banhezhanxinxi> bhzlist = bhzDAO.find("from Banhezhanxinxi where xiangmubuid=?", bsid);
				if (bhzlist.size() > 0) {
					i = bhzlist.get(0).getId();
				}
				break;
			default:
				break;
			}
			break;
		case 4:
			switch (selectut) {
			case 4:
				i = bsid;
				break;
			case 5:
				List<Banhezhanxinxi> bhzlist = bhzDAO.find("from Banhezhanxinxi where zuoyeduiid=?", bsid);
				if (bhzlist.size() > 0) {
					i = bhzlist.get(0).getId();
				}
				break;
			default:
				break;
			}
			break;
		case 5:
			switch (selectut) {
			case 5:
				i = bsid;
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		return i;
	}
	
	public Muser limitgetOneUser(HttpServletRequest request, int id){
		int ut = StringUtil.getUserType(request);	
		List<Muser> userlist = new ArrayList<Muser>();
		int bsid = StringUtil.getBiaoshiId(request);
		List<Xiangmubuxinxi> xmblist = null;
		List<Zuoyeduixinxi> zydlist = null;
		List<Banhezhanxinxi> bhzlist = null;
		StringBuilder bsstr = new StringBuilder("from muser where usertype>=");
		bsstr.append(ut);
		bsstr.append(" and id=");
		bsstr.append(id);
		bsstr.append(" and (1=2");
		switch (ut) {
		case 1:
			return userDAO.get(id);
		case 2:
			bsstr.append(" or (biaoshiid=");
			bsstr.append(bsid);
			bsstr.append(" and usertype=2)");
			xmblist = (List<Xiangmubuxinxi>)xmbDAO.find("from Xiangmubuxinxi where biaoduanid=?", bsid);
			for (Xiangmubuxinxi xmb : xmblist) {
				bsstr.append(" or (biaoshiid=");
				bsstr.append(xmb.getId());
				bsstr.append(" and usertype=3)");
			}
			zydlist = (List<Zuoyeduixinxi>)zydDAO.find("from Zuoyeduixinxi where biaoduanid=?", bsid);
			for (Zuoyeduixinxi zyd : zydlist) {
				bsstr.append(" or (biaoshiid=");
				bsstr.append(zyd.getId());
				bsstr.append(" and usertype=4)");
			}
			bhzlist = (List<Banhezhanxinxi>)bhzDAO.find("from Banhezhanxinxi where biaoduanid=?", bsid);
			for (Banhezhanxinxi bhz : bhzlist) {
				bsstr.append(" or (biaoshiid=");
				bsstr.append(bhz.getId());
				bsstr.append(" and usertype=5)");
			}
			bsstr.append(")");	
			userlist = userDAO.find(bsstr.toString());
			break;
		case 3:
			bsstr.append(" or (biaoshiid=");
			bsstr.append(bsid);
			bsstr.append(" and usertype=3)");
			zydlist = (List<Zuoyeduixinxi>)zydDAO.find("from Zuoyeduixinxi where xiangmubuid=?", bsid);
			for (Zuoyeduixinxi zyd : zydlist) {
				bsstr.append(" or (biaoshiid=");
				bsstr.append(zyd.getId());
				bsstr.append(" and usertype=4)");
			}
			bhzlist = (List<Banhezhanxinxi>)bhzDAO.find("from Banhezhanxinxi where xiangmubuid=?", bsid);
			for (Banhezhanxinxi bhz : bhzlist) {
				bsstr.append(" or (biaoshiid=");
				bsstr.append(bhz.getId());
				bsstr.append(" and usertype=5)");
			}
			bsstr.append(")");
			userlist = userDAO.find(bsstr.toString());
			break;
		case 4:
			bsstr.append(" or (biaoshiid=");
			bsstr.append(bsid);
			bsstr.append(" and usertype=4)");
			bhzlist = (List<Banhezhanxinxi>)bhzDAO.find("from Banhezhanxinxi where zuoyeduiid=?", bsid);
			for (Banhezhanxinxi bhz : bhzlist) {
				bsstr.append(" or (biaoshiid=");
				bsstr.append(bhz.getId());
				bsstr.append(" and usertype=5)");
			}
			bsstr.append(")");
			userlist = userDAO.find(bsstr.toString());
			break;
		case 5:
			bsstr.append(" or (biaoshiid=");
			bsstr.append(bsid);
			bsstr.append(" and usertype=5))");
			userlist = userDAO.find(bsstr.toString());
			break;
		default:
			break;
		}	
		if (userlist.size()>0) {
			return userlist.get(0);
		} else {
			return null;
		}
	}
	
	public void saveOrUpdate(Muser user){
		userDAO.saveOrUpdate(user);
	}
	
	public void del(HttpServletRequest request, int userId){
		Muser user = limitgetOneUser(request, userId);
		if (null != user) {
			if (null != user.getName() && !user.getName().equalsIgnoreCase("systemmanager")) {
				userDAO.delete(user);
			}
		}
	}
	
	public Muser getMuserById(HttpServletRequest request, int id){
		return limitgetOneUser(request, id);
	}
	
	public List<Role> getAllRole(){
		return roleDAO.loadAll();
	}
	
	public List<Role> limitgetRole(){
		return roleDAO.find("from Role where issuper='0'");
	}
	
	public void saveOrUpdate(Role role){
		roleDAO.saveOrUpdate(role);		
	}
	
	public void delrole(int roleId){
		roleDAO.deleteByKey(roleId);
	}
	
	public Role getRoleById(int id){
		return roleDAO.get(id);
	}
	
	
	
	public List<Resource> getAllResource(){
		return resourceDAO.loadAll();
	}
	public void saveOrUpdate(Resource resource){
		resourceDAO.saveOrUpdate(resource);		
	}
	
	public void delresource(int resourceId){
		resourceDAO.deleteByKey(resourceId);
	}
	
	public Resource getResourceById(int id){
		return resourceDAO.get(id);
	}
	

	/**
	 * 清空指定拌和站数据的客户端编号信息---沥青
	 * @param shebeibianhao
	 */
	public void updateKehuduanbianhao(String shebeibianhao){
		try{
			String sql="update Liqingxixx set kehuduanbianhao='' where shebeibianhao='"+shebeibianhao+"' and kehuduanbianhao!=''";
			biaoduanDAO.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
