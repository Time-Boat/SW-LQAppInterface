package com.mss.shtoone.persistence.hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingclDailyView;
import com.mss.shtoone.domain.Tanpujihuaxinxi;
import com.mss.shtoone.persistence.TanpujihuaxinxiDAO;
import com.mss.shtoone.util.StringUtil;

@Repository
public class TanpujihuaxinxiHibernateDAO extends GenericHibernateDAO<Tanpujihuaxinxi, Integer>
		implements TanpujihuaxinxiDAO {
	static Log logger = LogFactory.getLog(TanpujihuaxinxiHibernateDAO.class);
	@Override
	public List<Tanpujihuaxinxi> getTanpujihuaxinxiBybianduan(Integer biaoduan) {
		List<Tanpujihuaxinxi> _returnValue=new ArrayList<Tanpujihuaxinxi>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM tanpujihuaxinxi");
		if(StringUtil.Null2Blank(String.valueOf(biaoduan)).length()>0){
			sql.append(" where biaoduanid= '"+biaoduan+"' order by jihuastarttime asc");
		}else{
			sql.append(" order by jihuastarttime asc");
		}
		
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		String qsql = sql.toString();
		
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();			
		
			if (true) {
				rs = st.executeQuery(qsql);
				while (rs.next()) {
					Tanpujihuaxinxi hv = new Tanpujihuaxinxi();
					hv.setId(rs.getInt("id"));
					hv.setBiaoduanid(rs.getString("biaoduanid"));
					hv.setJihuamingcheng(rs.getString("jihuamingcheng"));
					hv.setJihuastarttime(rs.getString("jihuastarttime"));
					hv.setJihuaendtime(rs.getString("jihuaendtime"));
					hv.setJihuashengchanliang(rs.getString("jihuashengchanliang"));
					hv.setJihuatanpulicheng(rs.getString("jihuatanpulicheng"));
					hv.setJihuatanpucengmian(rs.getString("jihuatanpucengmian"));
					hv.setJihuabianjiren(rs.getString("jihuabianjiren"));
					hv.setJihuatianxieriqi(rs.getString("jihuatianxieriqi"));
					hv.setRemark(rs.getString("remark"));

					_returnValue.add(hv);
				}
				return _returnValue;
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
		return null;
	}
	@Override
	public List<Tanpujihuaxinxi> getTanpujihuaYearBybianduan(Integer biaoduan,String startTime,String endTime) {
		List<Tanpujihuaxinxi> _returnValue=new ArrayList<Tanpujihuaxinxi>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT min(jihuastarttime) as jihuastarttime,max(jihuaendtime) as jihuaendtime,"+
					"substring(jihuastarttime,1,4) as startyear,substring(jihuaendtime,1,4) as endyear FROM tanpujihuaxinxi");
		
		String whereStr =" where 1=1 "; 
		if(StringUtil.Null2Blank(String.valueOf(biaoduan)).length()>0){
			whereStr +=" and biaoduanid="+biaoduan;
		}
		if(StringUtil.Null2Blank(startTime).length()>0&&StringUtil.Null2Blank(endTime).length()>0){
			whereStr += " and ((jihuastarttime between '"+startTime+"' and '"+endTime+"') or (jihuaendtime between '"+startTime+"' and '"+endTime+"'))";
		}
		
		String groupbyStr=" group by substring(jihuastarttime,1,4),substring(jihuaendtime,1,4) order by endyear asc";
		
		sql.append(whereStr+groupbyStr);
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		String qsql = sql.toString();
		
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();			
		
			if (true) {
				rs = st.executeQuery(qsql);
				while (rs.next()) {
					Tanpujihuaxinxi hv = new Tanpujihuaxinxi();
					hv.setJihuastarttime(rs.getString("jihuastarttime"));
					hv.setJihuaendtime(rs.getString("jihuaendtime"));
					hv.setJihuashengchanliang(rs.getString("startyear"));
					hv.setJihuatanpulicheng(rs.getString("endyear"));
					_returnValue.add(hv);
				}
				return _returnValue;
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
		return _returnValue;
	}
	
	
	
	
	@Override
	public GenericPageMode getTanpujihuaxinxitongji(
			 String startTimeOne, String endTimeOne,
			 Integer biaoduan,
			String fn, int bsid, int offset, int pagesize) {
		List<Tanpujihuaxinxi> _returnValue = new ArrayList<Tanpujihuaxinxi>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "tanpujihuaxinxi";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition =" 1=1 ";

		if (null != biaoduan) {
			queryCondition += " and biaoduanid=" + biaoduan;
		}

		queryCondition +=  " and ((jihuastarttime between '" + strstartTime
		+ "' and '" + strendTime + "') or (jihuaendtime between '"+strstartTime+"' and '"+strendTime+"')) ";
		
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
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
			cs.setString(4, "jihuaendtime asc");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					Tanpujihuaxinxi hv = new Tanpujihuaxinxi();
					hv.setId(rs.getInt("id"));
					hv.setBiaoduanid(rs.getString("biaoduanid"));
					hv.setJihuamingcheng(rs.getString("jihuamingcheng"));
					hv.setJihuastarttime(rs.getString("jihuastarttime"));
					hv.setJihuaendtime(rs.getString("jihuaendtime"));
					hv.setJihuashengchanliang(rs.getString("jihuashengchanliang"));
					hv.setJihuatanpulicheng(rs.getString("jihuatanpulicheng"));
					hv.setJihuatanpucengmian(rs.getString("jihuatanpucengmian"));
					hv.setJihuabianjiren(rs.getString("jihuabianjiren"));
					hv.setJihuatianxieriqi(rs.getString("jihuatianxieriqi"));
					hv.setRemark(rs.getString("remark"));

					_returnValue.add(hv);
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
	public LiqingclDailyView getSumLiqingCLByDate(String starttime, String endtime,
			String biaoduan) {
		LiqingclDailyView _returnValue=new LiqingclDailyView();;
		
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		String qsql = "SELECT SUM(CAST((CASE WHEN (dailycl IS NULL) OR (dailycl = '') " +
		              "THEN '0' ELSE dailycl END) AS numeric(38, 2))) AS dailycl,"+
		              "SUM(CAST((CASE WHEN (dailyps IS NULL) OR (dailyps = '') " +
		              "THEN '0' ELSE dailyps END) AS int)) AS dailyps "+
				      "FROM LiqingclDailyView where biaoduanid='"+biaoduan+"' and dailyrq between '"+starttime+"' and '"+endtime+"'";
		
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();			
				rs = st.executeQuery(qsql);
				if (rs.next()) {
					_returnValue.setDailyps(rs.getString("dailyps"));
					_returnValue.setDailycl(String.format("%1$.2f",rs.getDouble("dailycl")));
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
		return _returnValue;
	}

}
