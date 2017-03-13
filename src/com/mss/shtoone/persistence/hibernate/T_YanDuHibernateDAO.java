package com.mss.shtoone.persistence.hibernate;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.T_YanDu;
import com.mss.shtoone.domain.T_YanDuPageMode;
import com.mss.shtoone.persistence.T_YanDuDAO;
import com.mss.shtoone.util.StringUtil;

@Repository
public class T_YanDuHibernateDAO extends GenericHibernateDAO<T_YanDu, String> implements
T_YanDuDAO {
	static Log logger = LogFactory.getLog(T_YanDuHibernateDAO.class);
	@Override
	public T_YanDu getT_YanDu(String testNo){
		String queryString = "from T_YanDu as t where t.TestNo='"+testNo+"'";
		List<T_YanDu> list = find(queryString);
		T_YanDu xcData = null;
		if(!list.isEmpty()){
			xcData = (T_YanDu)list.get(0);
		}
        return xcData;
	}
	@Override
	public T_YanDuPageMode viewlist(String testNo,
			String startTimeOne, String endTimeOne,
			String BiaoDuanId,int offset, int pagesize) {
		List<T_YanDu> _returnValue = new ArrayList<T_YanDu>();
		T_YanDuPageMode pagemode = new T_YanDuPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "T_YanDu";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		String queryCondition = " 1 = 1";
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
			queryCondition += " and convert(datetime,wm8,121) >= '" + strstartTime + "'";
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
			queryCondition += " and convert(datetime,wm8,121) <= '" + strendTime + "'";
		}
		
		/*if (StringUtil.Null2Blank(BiaoDuanId).length() > 0) {
			queryCondition += " and BiaoDuanId='" + BiaoDuanId + "'";
		}*/
		if (StringUtil.Null2Blank(testNo).length() > 0) {
			queryCondition += " and testNo like '%" + testNo +"%'";
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
					T_YanDu x1 = new T_YanDu();
					x1.setTestNo(rs.getString("TestNo"));
					x1.setWm(rs.getString("wm"));
					x1.setWm1(rs.getString("wm1"));
					x1.setWm2(rs.getString("wm2"));
					x1.setWm3(rs.getString("wm3"));
					x1.setWm4(rs.getString("wm4"));
					x1.setWm5(rs.getString("wm5"));
					x1.setWm6(rs.getString("wm6"));
					x1.setWm7(rs.getString("wm7"));
					x1.setWm8(rs.getString("wm8"));
					x1.setWm9(rs.getString("wm9"));
					x1.setWm10(rs.getString("wm10"));
					x1.setWm11(rs.getString("wm11"));
					x1.setWm12(rs.getString("wm12"));
					x1.setWm13(rs.getString("wm13"));
					x1.setWm14(rs.getString("wm14"));
					x1.setWm15(rs.getString("wm15"));
					x1.setWm16(rs.getString("wm16"));
					x1.setWm17(rs.getString("wm17"));
					x1.setWm18(rs.getString("wm18"));
					x1.setWm19(rs.getString("wm19"));
					x1.setWm20(rs.getString("wm20"));
					x1.setWm21(rs.getString("wm21"));
					x1.setWm22(rs.getString("wm22"));
					x1.setWm23(rs.getString("wm23"));
					x1.setWm24(rs.getString("wm24"));
					x1.setWm25(rs.getString("wm25"));
					x1.setWm26(rs.getString("wm26"));
					x1.setWm27(rs.getString("wm27"));
					x1.setWm28(rs.getString("wm28"));
					x1.setWm29(rs.getString("wm29"));
					x1.setWm30(rs.getString("wm30"));
					x1.setWm31(rs.getString("wm31"));
					x1.setWm32(rs.getString("wm32"));
					x1.setWm33(rs.getString("wm33"));
					x1.setWm34(rs.getString("wm34"));
					x1.setWm35(rs.getString("wm35"));
					x1.setWm36(rs.getString("wm36"));
					x1.setWm37(rs.getString("wm37"));
					x1.setWm38(rs.getString("wm38"));
					x1.setWm39(rs.getString("wm39"));
					x1.setWm40(rs.getString("wm40"));
					x1.setWm41(rs.getString("wm41"));
					x1.setWm42(rs.getString("wm42"));
					x1.setWm43(rs.getString("wm43"));
					x1.setWm44(rs.getString("wm44"));
					x1.setWm45(rs.getString("wm45"));
					x1.setWm46(rs.getString("wm46"));
					x1.setWm47(rs.getString("wm47"));
					x1.setWm48(rs.getString("wm48"));
					x1.setWm49(rs.getString("wm49"));
					x1.setWm50(rs.getString("wm50"));
					x1.setWm51(rs.getString("wm51"));
					_returnValue.add(x1);
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



