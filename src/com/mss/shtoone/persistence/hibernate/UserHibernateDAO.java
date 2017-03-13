package com.mss.shtoone.persistence.hibernate;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.Muser;
import com.mss.shtoone.domain.UserPageMode;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.domain.Zuoyeduixinxi;
import com.mss.shtoone.persistence.BanhezhanDAO;
import com.mss.shtoone.persistence.UserDAO;
import com.mss.shtoone.persistence.XiangmubuDAO;
import com.mss.shtoone.persistence.ZuoyeduiDAO;
import com.mss.shtoone.util.StringUtil;

@Repository
public class UserHibernateDAO extends GenericHibernateDAO<Muser, Integer> implements
		UserDAO {
	static Log logger = LogFactory.getLog(UserHibernateDAO.class);
	
	@Autowired
	private XiangmubuDAO xmbDAO;
	
	@Autowired
	private BanhezhanDAO bhzDAO;
	
	@Autowired
	private ZuoyeduiDAO zydDAO;
	
	@Override
	public Muser findByName(String username) {
		Muser muser = null;
		String queryString = "from muser as model where model.name=?";
		List<Muser> users = find(queryString, username);
		if (users.size() > 0) {
			muser = users.get(0);
		}
		return muser;
	}

	@Override
	public UserPageMode viewlist(String username, Integer usertype, Integer biaoduan,
			Integer xiangmubu, Integer shebeibianhao, int ut, int bsid,
			int offset, int pagesize) {
		List<Muser> userlist = new ArrayList<Muser>();
		UserPageMode userpage = new UserPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "muser";
		StringBuilder bsstr = new StringBuilder(" name <> 'systemmanager' and usertype>=");
		bsstr.append(ut);
		if (StringUtil.Null2Blank(username).length() > 0) {
			bsstr.append(" and name like '%");
			bsstr.append(username);
			bsstr.append("%'");
		}
		
		if (null != usertype) {
			bsstr.append(" and usertype=");
			bsstr.append(usertype);
		}
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
		} else {
			bsstr.append(" or 1=1)");	
		}
		
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "id");
			cs.setString(4, "id");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, bsstr.toString());
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					Muser us = new Muser();
					us.setId(rs.getInt("id"));
					us.setName(rs.getString("name"));
					us.setFullname(rs.getString("fullname"));
					us.setRemark(rs.getString("remark"));
					us.setDisabled(rs.getString("disabled"));
					us.setUsertype(rs.getInt("usertype"));
					us.setBiaoshiid(rs.getInt("biaoshiid"));				
					userlist.add(us);
				}
				userpage.setDatas(userlist);
				userpage.setRecordcount(cs.getInt(7));
				userpage.setPagetotal(cs.getInt(8));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				cs.close();
			} catch (Exception e1) {
			}
			try {
				con.close();
			} catch (Exception e) {
			}
		}
		return userpage;
	}	
}



