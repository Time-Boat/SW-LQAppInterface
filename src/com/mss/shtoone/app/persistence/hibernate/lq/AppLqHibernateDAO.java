package com.mss.shtoone.app.persistence.hibernate.lq;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.app.domain.AppLoginLogEntity;
import com.mss.shtoone.app.persistence.AppLqDAO;
import com.mss.shtoone.persistence.hibernate.GenericHibernateDAO;
import com.mss.shtoone.util.DbJdbcUtil;
import com.mss.shtoone.util.StringUtil;

@Repository
public class AppLqHibernateDAO extends GenericHibernateDAO<AppLoginLogEntity, Integer> implements AppLqDAO{

	@Override
	public Map<String, String> getCbcz(String startTime, String endTime,
			Integer biaoduan, Integer xiangmubu, String shebeibianhao) {
		Map<String, String> map = new HashMap<String,String>();
		
		StringBuffer sql = new StringBuffer(" select SUM(CASE WHEN (leixing = '1') OR (leixing = '2') OR (leixing = '3') then 1 else 0 end) as a, "
				+ " SUM(CASE WHEN (leixing = '2') OR (leixing = '3') then 1 else 0 end) as b, " 
				+ " SUM(CASE WHEN (leixing = '3') then 1 else 0 end) as c from LiqingmanualchaobiaoView where (filepath is NOT NULL or  ISNULL(chulijieguo,'')<>'') ");
		
		if (StringUtil.Null2Blank(startTime).length() > 0 && StringUtil.Null2Blank(endTime).length() > 0) {
			sql.append(" and (convert(datetime,baocunshijian,121) between '" + startTime + "' and '" + endTime + "') ");
		}
		
		if (null != biaoduan) {
			sql.append(" and biaoduanid=" + biaoduan );
		}
		
		if (null != xiangmubu) {
			sql.append(" and xiangmubuid=" + xiangmubu );
		}
		
		if (null != shebeibianhao) {
			sql.append(" and shebeibianhao='" + shebeibianhao + "' ");
		}
		
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		try {
			con = getCon();
			st = con.createStatement();
			rs = st.executeQuery(sql.toString());
			if (rs.next()) {
				String a = rs.getString("a");
				String b = rs.getString("b");
				String c = rs.getString("c");
				map.put("a", a == null ? "0" : a);
				map.put("b", b == null ? "0" : b);
				map.put("c", c == null ? "0" : c);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DbJdbcUtil.closeAll(rs, st, con);
		}
		return map;
	}

	private void closeCon(ResultSet rs, Statement st, Connection con) {
		DbJdbcUtil.closeAll(rs,st,con);
	}

	private Connection getCon() {
		return getTemplate().getSessionFactory().openSession().connection();
	}

}
