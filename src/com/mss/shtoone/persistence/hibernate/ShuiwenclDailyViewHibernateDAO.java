package com.mss.shtoone.persistence.hibernate;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.ShuiwenclDaily;
import com.mss.shtoone.domain.ShuiwenclDailyView;
import com.mss.shtoone.persistence.ShuiwenclDailyViewDAO;
import com.mss.shtoone.util.StringUtil;



@Repository
public class ShuiwenclDailyViewHibernateDAO extends GenericHibernateDAO<ShuiwenclDailyView, Integer> implements
ShuiwenclDailyViewDAO {
	static Log logger = LogFactory.getLog(ShuiwenclDailyViewHibernateDAO.class);
	
	@Override
	public GenericPageMode limitdailylist(String shebeibianhao, 
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize) {
		List<ShuiwenclDailyView> _returnValue = new ArrayList<ShuiwenclDailyView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "ShuiwenclDailyView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		day.add(Calendar.DATE, -30);
		String strstartTime = sdf.format(day.getTime());		
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {	
			strstartTime = startTimeOne;
	
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}

		
		String queryCondition =  " (convert(datetime,dailyrq,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and dailysbbh='" + shebeibianhao + "'";
		}
		
		if (null != biaoduan) {
			queryCondition += " and biaoduanid=" + biaoduan;
		}
		if (null != xiangmubu) {
			queryCondition += " and xiangmubuid=" + xiangmubu;
		}
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "dailyid");
			cs.setString(4, "dailyid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					ShuiwenclDailyView lqdailyview = new ShuiwenclDailyView();
					lqdailyview.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					lqdailyview.setJianchen(rs.getString("jianchen"));
					lqdailyview.setDailycl(rs.getString("dailycl"));
					lqdailyview.setDailyps(rs.getString("dailyps"));
					lqdailyview.setDailyid(rs.getInt("dailyid"));
					lqdailyview.setDailyrq(rs.getString("dailyrq"));
					lqdailyview.setDailysbbh(rs.getString("dailysbbh"));
					lqdailyview.setDailyxzcl(rs.getString("dailyxzcl"));					
					_returnValue.add(lqdailyview);
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
	
	@Override
	public List<ShuiwenclDaily> getSwdailycl(String startTime, String endTime) {
		List<ShuiwenclDaily> _returnValue = new ArrayList<ShuiwenclDaily>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT shebeibianhao,SUM(CAST((CASE WHEN (dbo.GET_NUMBER2(glchangliang) IS NULL) OR (dbo.GET_NUMBER2(glchangliang) = '') ");
		sql.append("THEN '0' ELSE glchangliang END) AS numeric(38, 2))) AS glchangliang,COUNT(bianhao) as pangshu");
		sql.append(" FROM ShuiwenxixxView");
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()>0){
			sql.append(" where (baocunshijian between '"+startTime+"' and '"+endTime+"') ");
		}
		sql.append(" group by shebeibianhao");
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();			
			rs = st.executeQuery(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while (rs.next()) {
				ShuiwenclDaily hv = new ShuiwenclDaily();
				try {
					hv.setDailyrq(sdf.format(sdf.parse(startTime)));
				} catch (ParseException e) {}
				hv.setDailycl(String.format("%1$.2f",rs.getDouble("glchangliang")));
				hv.setDailyps(rs.getString("pangshu"));
				hv.setDailysbbh(rs.getString("shebeibianhao"));
				_returnValue.add(hv);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {}
			try {				
				st.close();
			} catch (SQLException e1) {}
			try {
				con.close();
			} catch (SQLException e) {}
		}
		return _returnValue;
	}
	
	@Override
	public String oneSwdailycl(String sbbh, String startTime, String endTime) {
		StringBuffer rd = new StringBuffer();;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(CAST((CASE WHEN (glchangliang IS NULL) OR (glchangliang = '') ");
		sql.append("THEN '0' ELSE glchangliang END) AS numeric(38, 2))) AS glchangliang,COUNT(bianhao) as pangshu");
		sql.append(" FROM ShuiwenxixxView");
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()>0) 
		{
			sql.append(" where (baocunshijian between '"+startTime+"' and '"+endTime+"') ");
			sql.append(" and shebeibianhao='"+sbbh+"'");
			ResultSet rs = null;
			Statement st = null;
			Connection con = null;
			String qsql = sql.toString();
			
			try {
				con = getTemplate().getSessionFactory().openSession().connection();
				st = con.createStatement();			
			
				if (true) {
					rs = st.executeQuery(qsql);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					if (rs.next()) {						
						rd.append(String.format("%1$.2f",rs.getDouble("glchangliang")));
						rd.append(",");
						rd.append(rs.getString("pangshu"));
					}
					
				}
			} catch (SQLException e) {
				logger.error(e.getMessage());
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
		return rd.toString();
	}
}



