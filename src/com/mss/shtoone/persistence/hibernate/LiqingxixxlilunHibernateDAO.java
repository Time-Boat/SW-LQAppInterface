package com.mss.shtoone.persistence.hibernate;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Liqingxixxlilun;
import com.mss.shtoone.persistence.LiqingxixxlilunDAO;
import com.mss.shtoone.util.DbJdbcUtil;
import com.mss.shtoone.util.StringUtil;


@Repository
public class LiqingxixxlilunHibernateDAO extends GenericHibernateDAO<Liqingxixxlilun, Integer> implements LiqingxixxlilunDAO {

	@Override
	public int lqgetlilunid(String gprsbh, String sjysb,double lowsjysb, double highsjysb) {
		int _returnValue = -1;
		if (StringUtil.Null2Blank(gprsbh).length() > 0 && StringUtil.Null2Blank(sjysb).length() > 0) {
			StringBuffer sql = new StringBuffer();
			sql.append("select llid from Liqingxixxlilun where llmoren='1' and llshebeibianhao = '");		
			sql.append(gprsbh+"'");
			sql.append(" and (CAST((CASE WHEN (llysb IS NULL) OR (llysb = '') THEN ");
			sql.append("'0' ELSE llysb END) AS numeric(38, 2)) * ");
			sql.append(lowsjysb);
			sql.append("<");
			sql.append(sjysb);
			sql.append(") and (CAST((CASE WHEN (llysb IS NULL) OR (llysb = '') THEN ");
			sql.append("'0' ELSE llysb END) AS numeric(38, 2)) * ");
			sql.append(highsjysb);
			sql.append(">");
			sql.append(sjysb);
			sql.append(")");
			ResultSet rs = null;
			Statement st = null;
			Connection con = null;
			try {
				con = getTemplate().getSessionFactory().openSession().connection();
				st = con.createStatement();			
				rs = st.executeQuery(sql.toString());
				if (rs.next()) {
					_returnValue = rs.getInt("llid");
				}
			} catch (SQLException e) {
			} finally {
				DbJdbcUtil.closeAll(rs, st, con);
			}
		}
		return _returnValue;
	}
	
	@Override
	public List<Liqingxixxlilun> getLilunlist(String llbuwei, String llshebeibianhao) {
		return this.find("from Liqingxixxlilun where llbuwei=? and llshebeibianhao=?", llbuwei, llshebeibianhao);
	}
	
}



