package com.mss.shtoone.persistence.hibernate;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.mss.shtoone.domain.Shuiwenxixxlilun;
import com.mss.shtoone.persistence.ShuiwenxixxlilunDAO;
import com.mss.shtoone.util.StringUtil;


@Repository
public class ShuiwenxixxlilunHibernateDAO extends GenericHibernateDAO<Shuiwenxixxlilun, Integer> implements
ShuiwenxixxlilunDAO {
	@Override
	public Integer swgetlilunid(String gprsbh,String persjf1,String persjg1,String persjg2,double lowsjg1, double highsjg1,double lowsjf1,double highsjf1) {
		int _returnValue = -1;
		if (StringUtil.Null2Blank(gprsbh).length() > 0 && StringUtil.Null2Blank(persjg1).length() > 0 && 
				StringUtil.Null2Blank(persjg1).length()> 0 && StringUtil.Null2Blank(persjf1).length()>0) {
			StringBuffer sql = new StringBuffer();
			sql.append("select llid from Shuiwenxixxlilun where llmoren='1' and llshebeibianhao = '");		
			sql.append(gprsbh+"'");
			  sql.append(" and (CAST((CASE WHEN (llf1 IS NULL) OR (llf1 = '') THEN ");
			  sql.append("'0' ELSE llf1 END) AS numeric(38, 2)) * ");
			  sql.append(lowsjf1);
			  sql.append("<");
			  sql.append(persjf1);
			  sql.append(") and (CAST((CASE WHEN (llf1 IS NULL) OR (llf1 = '') THEN ");
			  sql.append("'0' ELSE llf1 END) AS numeric(38, 2)) * ");
			  sql.append(highsjf1);
			  sql.append(">");
			  sql.append(persjf1);
			  if (Double.valueOf(persjg1)>0) {
				  sql.append(") and (CAST((CASE WHEN (llg1 IS NULL) OR (llg1 = '') THEN ");
				  sql.append("'0' ELSE llg1 END) AS numeric(38, 2)) * ");
				  sql.append(lowsjg1);
				  sql.append("<");
				  sql.append(persjg1);
				  sql.append(") and (CAST((CASE WHEN (llg1 IS NULL) OR (llg1 = '') THEN ");
				  sql.append("'0' ELSE llg1 END) AS numeric(38, 2)) * ");
				  sql.append(highsjg1);
				  sql.append(">");
				  sql.append(persjg1);
				  
			} else if (Double.valueOf(persjg2)>0){
				sql.append(") and (CAST((CASE WHEN (llg2 IS NULL) OR (llg2 = '') THEN ");
				  sql.append("'0' ELSE llg2 END) AS numeric(38, 2)) * ");
				  sql.append(lowsjg1);
				  sql.append("<");
				  sql.append(persjg2);
				  sql.append(") and (CAST((CASE WHEN (llg2 IS NULL) OR (llg2 = '') THEN ");
				  sql.append("'0' ELSE llg2 END) AS numeric(38, 2)) * ");
				  sql.append(highsjg1);
				  sql.append(">");
				  sql.append(persjg2);
			}
			  sql.append(")");
			ResultSet rs = null;
			Statement st = null;
			Connection con = null;
			String qsql = sql.toString();
			try {
				con = getTemplate().getSessionFactory().openSession().connection();
				st = con.createStatement();			
				rs = st.executeQuery(qsql);
				if (rs.next()) {
					_returnValue = rs.getInt("llid");
				}
			} catch (SQLException e) {
			} finally {
				try {
					rs.close();
				} catch (SQLException e) {
				}
				try {				
					st.close();
				} catch (SQLException e1) {
				}
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return _returnValue;
	}
	
	@Override
	public List<Shuiwenxixxlilun> getLilunlist(String llbuwei, String llshebeibianhao) {
		return this.find("from Shuiwenxixxlilun where llshebeibianhao=?", llshebeibianhao);
	}

	@Override
	public List<Shuiwenxixxlilun> getLilunBySbbhAndTime(String llshebeibianhao,
			String nowDate) {
		// TODO Auto-generated method stub
		return this.find("from Shuiwenxixxlilun where llshebeibianhao='"+llshebeibianhao+"' and substring(llshigongdateS,1,11)='"+nowDate+"'");
	}
	
}



