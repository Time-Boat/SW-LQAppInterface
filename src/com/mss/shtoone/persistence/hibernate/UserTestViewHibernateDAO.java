package com.mss.shtoone.persistence.hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.UserTestView;
import com.mss.shtoone.persistence.UserTestViewDAO;
import com.mss.shtoone.util.StringUtil;


@Repository
public class UserTestViewHibernateDAO extends GenericHibernateDAO<UserTestView, Integer> implements
UserTestViewDAO {
	static Log logger = LogFactory.getLog(UserTestViewHibernateDAO.class);
	@Override
	public GenericPageMode viewlist(String startTimeOne, String endTimeOne, String testname, int offset, int pagesize) {
		List<UserTestView> _returnValue = new ArrayList<UserTestView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "UserTestView";	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strstartTime = "2010-01-01";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition =  " (convert(varchar,TestDate,23) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (StringUtil.Null2Blank(testname).length() > 0) {
			queryCondition += " and TestName like'%" + testname + "%'";
		}
		
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "TestNo");
			cs.setString(4, "TestNo DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					UserTestView utv = new UserTestView();
					utv.setOperator(rs.getString("Operator"));
					try {
						utv.setTestDate(sdf.format(sdf.parse(rs.getString("TestDate"))));
					} catch (ParseException e) {
					}				
					utv.setTestName(rs.getString("TestName"));
					utv.setTestNo(rs.getString("TestNo"));
					utv.setUserTestID(rs.getString("UserTestID"));
					_returnValue.add(utv);
				}
				pagemode.setDatas(_returnValue);
				pagemode.setRecordcount(cs.getInt(7));
				pagemode.setPagetotal(cs.getInt(8));
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
		return pagemode;
	}
}



