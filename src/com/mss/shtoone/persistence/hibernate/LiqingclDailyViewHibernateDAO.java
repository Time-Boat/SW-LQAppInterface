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
import com.mss.shtoone.domain.LiqingclDaily;
import com.mss.shtoone.domain.LiqingclDailyView;
import com.mss.shtoone.persistence.LiqingclDailyViewDAO;
import com.mss.shtoone.util.StringUtil;



@Repository
public class LiqingclDailyViewHibernateDAO extends GenericHibernateDAO<LiqingclDailyView, Integer> implements
LiqingclDailyViewDAO {
	static Log logger = LogFactory.getLog(HunnintuViewHibernateDAO.class);
	
	@Override
	public GenericPageMode limitdailylist(String shebeibianhao, 
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize) {
		List<LiqingclDailyView> _returnValue = new ArrayList<LiqingclDailyView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LiqingclDailyView";
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
			cs.setString(3, "dailyrq");
			cs.setString(4, "dailyrq DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingclDailyView lqdailyview = new LiqingclDailyView();
					lqdailyview.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					lqdailyview.setJianchen(rs.getString("jianchen"));					
					lqdailyview.setDailybuwei(rs.getString("dailybuwei"));
					lqdailyview.setDailyrq(rs.getString("dailyrq"));
					lqdailyview.setDailysbbh(rs.getString("dailysbbh"));
					lqdailyview.setDailyid(rs.getInt("dailyid"));
					lqdailyview.setDailyxh(rs.getString("dailyxh"));
					try{
						lqdailyview.setDailycd(String.format("%1$.1f",rs.getFloat("dailycd")));
						lqdailyview.setDailycl(String.format("%1$.1f",rs.getFloat("dailycl")));
						lqdailyview.setDailyps(String.format("%1$.1f",rs.getFloat("dailyps")));
						lqdailyview.setDailyhd(String.format("%1$.1f",rs.getFloat("dailyhd")));
						lqdailyview.setDailykd(String.format("%1$.1f",rs.getFloat("dailykd")));						
						lqdailyview.setDailymd(String.format("%1$.1f",rs.getFloat("dailymd")));						
						lqdailyview.setDailyxzcl(String.format("%1$.1f",rs.getFloat("dailyxzcl")));							
						lqdailyview.setDailysjhd(String.format("%1$.1f",rs.getFloat("dailysjhd")));						
					}catch(Exception ex){}
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
	public List<LiqingclDaily> getlqdailycl(String startTime, String endTime) {
		List<LiqingclDaily> _returnValue = new ArrayList<LiqingclDaily>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT shebeibianhao, SUM(CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS changliang,COUNT(bianhao) as pangshu");
		sql.append(" FROM LiqingView");
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()>0) 
		{
			sql.append(" where (shijian between '"+startTime+"' and '"+endTime+"') ");
			sql.append(" group by shebeibianhao");
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
					while (rs.next()) {
						LiqingclDaily hv = new LiqingclDaily();
						try {
							hv.setDailyrq(sdf.format(sdf.parse(startTime)));
						} catch (ParseException e) {
						}
						hv.setDailycl(String.format("%1$.2f",rs.getDouble("changliang")));
						hv.setDailyps(rs.getString("pangshu"));
						hv.setDailysbbh(rs.getString("shebeibianhao"));
						_returnValue.add(hv);
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
		return _returnValue;
	}
	
	@Override
	public String onelqdailycl(String sbbh, String startTime, String endTime) {
		StringBuffer rd = new StringBuffer();;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS changliang,COUNT(bianhao) as pangshu");
		sql.append(" FROM LiqingView");
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
						rd.append(String.format("%1$.2f",rs.getDouble("changliang")));
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



