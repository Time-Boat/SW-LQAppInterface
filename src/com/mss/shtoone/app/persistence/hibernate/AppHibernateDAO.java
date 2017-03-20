package com.mss.shtoone.app.persistence.hibernate;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mss.shtoone.app.domain.AppInterfaceChaobiaoEntity;
import com.mss.shtoone.app.domain.AppLoginLogEntity;
import com.mss.shtoone.app.persistence.AppDAO;
import com.mss.shtoone.persistence.hibernate.GenericHibernateDAO;
import com.mss.shtoone.util.DbJdbcUtil;
import com.mss.shtoone.util.StringUtil;

@Repository
public class AppHibernateDAO extends GenericHibernateDAO<AppLoginLogEntity, Integer> implements AppDAO {

	// public void departTree(List list, String fn, String biaoshiid) {
	// StringBuffer sql = new StringBuffer();
	// sql.append(" FROM ShuiwenmanualphbView WHERE 1=1 ");
	// if (!fn.equalsIgnoreCase("all")) {
	// sql.append(" and "+fn+"=" + biaoshiid);
	// }
	// }

	// 拼查询条件（where语句）
	private String getSqlWhere(String startTime, String endTime, String sbbh, String biaoduan, String xmb) {
		// 拼出条件语句
		String sqlWhere = "";
		if (StringUtil.isNotEmpty(startTime) && StringUtil.isNotEmpty(endTime)) {
			sqlWhere += " and (convert(datetime,shijian,121) between '" + startTime + "' and '" + endTime + "') ";
		}

		if (StringUtil.isNotEmpty(biaoduan)) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and ";
			}
			sqlWhere += " biaoduanid = " + biaoduan + " ";

		}

		if (StringUtil.isNotEmpty(xmb)) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and ";
			}
			sqlWhere += " xiangmubuid = " + xmb + " ";
		}

		if (StringUtil.isNotEmpty(sbbh)) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and ";
			}
			sqlWhere += " shebeibianhao = '" + sbbh + "'";
		}

		return sqlWhere;
	}

	@Override
	public Map<String, String> getCbcz(String startTime, String endTime, Integer biaoduan, Integer xiangmubu,
			String shebeibianhao) {

		Map<String, String> map = new HashMap<String, String>();

		StringBuffer sql = new StringBuffer(
				" select SUM(CASE WHEN (leixing = '1') OR (leixing = '2') OR (leixing = '3') then 1 else 0 end) as a, "
						+ " SUM(CASE WHEN (leixing = '2') OR (leixing = '3') then 1 else 0 end) as b, "
						+ " SUM(CASE WHEN (leixing = '3') then 1 else 0 end) as c from ShuiwenmanaualchaobiaoView where (filepath is NOT NULL or  ISNULL(chulijieguo,'')<>'') ");

		if (StringUtil.Null2Blank(startTime).length() > 0 && StringUtil.Null2Blank(endTime).length() > 0) {
			sql.append(" and (convert(datetime,baocunshijian,121) between '" + startTime + "' and '" + endTime + "') ");
		}

		if (null != biaoduan) {
			sql.append(" and biaoduanid=" + biaoduan);
		}

		if (null != xiangmubu) {
			sql.append(" and xiangmubuid=" + xiangmubu);
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbJdbcUtil.closeAll(rs, st, con);
		}
		return map;
	}

	@Override
	public Integer executeUpdate(String sql) throws Exception {
		Integer b = 0;
		Statement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			con.setAutoCommit(false);
			cs = con.createStatement();
			b = cs.executeUpdate(sql);
			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			con.rollback();
			throw new Exception(sql + ":" + e.getMessage());
		} finally {
			DbJdbcUtil.closeAll(cs, con);
		}
		return b;
	}

	private void closeCon(ResultSet rs, Statement st, Connection con) {
		DbJdbcUtil.closeAll(rs, st, con);
	}

	private Connection getCon() {
		return getTemplate().getSessionFactory().openSession().connection();
	}

}
