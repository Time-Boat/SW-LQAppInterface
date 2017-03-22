package com.mss.shtoone.persistence.hibernate;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.LiqingmanualphbView;
import com.mss.shtoone.domain.LiqingphbView;
import com.mss.shtoone.domain.LiqingxixxjieguoView;
import com.mss.shtoone.domain.LiqingziduancfgView;
import com.mss.shtoone.domain.RunstateView;
import com.mss.shtoone.persistence.LiqingViewDAO;
import com.mss.shtoone.util.DbJdbcUtil;
import com.mss.shtoone.util.StringUtil;


@Repository
public class LiqingViewHibernateDAO extends GenericHibernateDAO<LiqingView, Integer> implements
LiqingViewDAO {
	static Log logger = LogFactory.getLog(HunnintuViewHibernateDAO.class);
	@Override
	public GenericPageMode lqviewlist(String shebeibianhao, 
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize, int queryalldata,String peifan) {
		List<LiqingView> _returnValue = new ArrayList<LiqingView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LiqingView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		day.add(Calendar.DATE, -3);
		String strstartTime = sdf.format(day.getTime());
		if (queryalldata == 1) {
			strstartTime = "2011-01-01 00:00:00";
		}
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {	
			if (queryalldata == 1) {
				strstartTime = startTimeOne;
			} else {
                try {
					if (sdf.parse(startTimeOne).after(day.getTime())) {
						strstartTime = startTimeOne;
					}
				} catch (Exception e) {
				}
			}
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}

		
		String queryCondition =  " (convert(datetime,shijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and shebeibianhao='" + shebeibianhao + "'";
		}
		if (StringUtil.Null2Blank(peifan).length() > 0) {
			queryCondition += " and peifan='" + peifan + "'";
		}
		
		/*if (null != biaoduan) {
			queryCondition += " and biaoduanid=" + biaoduan;
		}*/
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
			cs.setString(3, "bianhao");
			cs.setString(4, "bianhao DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingView hv = new LiqingView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));	
					hv.setShijian(rs.getString("shijian"));
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					hv.setCaijishijian(rs.getString("caijishijian"));
					hv.setJbsj(rs.getString("jbsj"));
					hv.setYonghu(rs.getString("yonghu"));
					hv.setPeifan(rs.getString("peifan"));
					hv.setBeiy1(rs.getString("beiy1"));
					hv.setBeiy2(rs.getString("beiy2"));
					hv.setBeiy3(rs.getString("beiy3"));
					try {
						hv.setLqwd(String.format("%1$.1f", rs
								.getFloat("lqwd")));
					} catch (Exception e) {						
					}
					try {
						hv.setGlwd(String.format("%1$.1f", rs
								.getFloat("glwd")));
					} catch (Exception e) {						
					}
					try {
						hv.setClwd(String.format("%1$.1f", rs
								.getFloat("clwd")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setChangliang(String.format("%1$.1f", rs
								.getFloat("changliang")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setSjg1(String.format("%1$.1f", rs
								.getFloat("sjg1")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg2(String.format("%1$.1f", rs
								.getFloat("sjg2")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg3(String.format("%1$.1f", rs
								.getFloat("sjg3")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg4(String.format("%1$.1f", rs
								.getFloat("sjg4")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg5(String.format("%1$.1f", rs
								.getFloat("sjg5")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg6(String.format("%1$.1f", rs
								.getFloat("sjg6")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg7(String.format("%1$.1f", rs
								.getFloat("sjg7")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjf1(String.format("%1$.1f", rs
								.getFloat("sjf1")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjf2(String.format("%1$.1f", rs
								.getFloat("sjf2")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjlq(String.format("%1$.1f", rs
								.getFloat("sjlq")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjtjj(String.format("%1$.1f", rs
								.getFloat("sjtjj")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjysb(String.format("%1$.2f", rs
								.getFloat("sjysb")));
					} catch (Exception e) {						
					}
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
	
	private void getChaobiaops(LiqingView hv,String startTime, String endTime,
			String shebeibianhao, Integer biaoduan, Integer xiangmubu, 
			int cnfxlx, String fn, int bsid, String xa, String xb) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(CASE WHEN (leixing ='1') or (leixing ='2') or (leixing ='3') THEN 1 ELSE 0 END) AS cbps,");
		sql.append("SUM(CASE WHEN (leixing ='2') or (leixing ='3') THEN 1 ELSE 0 END) AS midcbps,");
		sql.append("SUM(CASE WHEN (leixing ='3') THEN 1 ELSE 0 END) AS highcbps  From LiqingxixxjieguoView where 1=1");
	    switch (cnfxlx) {
		case 1://季度
			sql.append(" and datename(year, shijian)=");
			sql.append(xa);
			sql.append(" and datename(quarter, shijian)=");
			sql.append(xb);
			break;
		case 2://月份
			sql.append(" and datename(year, shijian)=");
			sql.append(xa);
			sql.append(" and datename(month, shijian)=");
			sql.append(xb);
			break;
		case 3://周
			sql.append(" and datename(year, shijian)=");
			sql.append(xa);
			sql.append(" and datename(week, shijian)=");
			sql.append(xb);
			break;			
		case 4://天
			sql.append(" and datename(month, shijian)=");
			sql.append(xa);
			sql.append(" and datename(day, shijian)=");
			sql.append(xb);
			break;
		case 5://小时
			sql.append(" and datename(day, shijian)=");
			sql.append(xa);
			sql.append(" and datename(hour, shijian)=");
			sql.append(xb);
			break;
		default:
			sql.append(" and datename(month, shijian)=");
			sql.append(xa);
			sql.append(" and datename(day, shijian)=");
			sql.append(xb);
			break;
		}
	    
	    if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and gprsbianhao = '"+shebeibianhao+"'");
		}
		
		if (null != biaoduan) {
			sql.append(" and biaoduanid=" + biaoduan);
		}
		if (null != xiangmubu) {
			sql.append(" and xiangmubuid=" + xiangmubu);
		}
		
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (shijian between '"+startTime+"' and '"+endTime+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()==0)
		{
			sql.append(" and (shijian between '"+startTime+"' and '"+newDate+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()==0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (shijian between '1900-01-01' and '"+endTime+"')");
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
				if (rs.next()) {
					if("".equals(rs.getString("cbps")) || rs.getString("cbps")==null){  
						hv.setAmbegin("0");
					}else{
						hv.setAmbegin(rs.getString("cbps"));
					}
					if("".equals(rs.getString("midcbps")) || rs.getString("midcbps")==null){  
						hv.setPmbegin("0");
					}else{
						hv.setPmbegin(rs.getString("midcbps"));
					}
					if("".equals(rs.getString("highcbps")) || rs.getString("highcbps")==null){  
						hv.setBeizhu("0");
					}else{
						hv.setBeizhu(rs.getString("highcbps"));
					}
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
	
	private String getHouduavg(String startTime, String endTime,
			String shebeibianhao, Integer biaoduan, Integer xiangmubu, 
			int cnfxlx, String fn, int bsid, String xa, String xb) {
		String strhdavg = "0";
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT Avg(CAST((CASE WHEN (dailyhd IS NULL) OR (dailyhd = '')");
		sql.append(" THEN '0' ELSE dailyhd END) AS numeric(38,2))) as hdavg From LiqingclDailyView where 1=1");
	    switch (cnfxlx) {
		case 1://季度
			sql.append(" and datename(year, dailyrq)=");
			sql.append(xa);
			sql.append(" and datename(quarter, dailyrq)=");
			sql.append(xb);
			break;
		case 2://月份
			sql.append(" and datename(year, dailyrq)=");
			sql.append(xa);
			sql.append(" and datename(month, dailyrq)=");
			sql.append(xb);
			break;
		case 3://周
			sql.append(" and datename(year, dailyrq)=");
			sql.append(xa);
			sql.append(" and datename(week, dailyrq)=");
			sql.append(xb);
			break;			
		case 4://天
			sql.append(" and datename(month, dailyrq)=");
			sql.append(xa);
			sql.append(" and datename(dd, dailyrq)=");
			sql.append(xb);
			break;	
		default:
			break;
		}
	    
	    if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and gprsbianhao = '"+shebeibianhao+"'");
		}
		
		if (null != biaoduan) {
			sql.append(" and biaoduanid=" + biaoduan);
		}
		if (null != xiangmubu) {
			sql.append(" and xiangmubuid=" + xiangmubu);
		}
		
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (dailyrq between '"+startTime+"' and '"+endTime+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()==0)
		{
			sql.append(" and (dailyrq between '"+startTime+"' and '"+newDate+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()==0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (dailyrq between '1900-01-01' and '"+endTime+"')");
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
				if (rs.next()) {
					strhdavg = String.format("%1$.2f",rs.getDouble("hdavg"));
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
		return strhdavg;
	}
	
	@Override
	public List<LiqingView> lqzhfxlist(String startTime, String endTime,
			String shebeibianhao, Integer biaoduan, Integer xiangmubu, int cnfxlx, String fn, int bsid) {
		List<LiqingView> _returnValue = new ArrayList<LiqingView>();
		StringBuffer sql = new StringBuffer();
		appendFangshuSql(sql);	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		String nowtime = sdf.format(day.getTime());
		String begintime = sdf.format(day.getTime());
	    switch (cnfxlx) {
		case 1://季度
			sql.append(",year(shijian) as xa, datename(quarter, shijian) as xb");
			sql.append(" FROM LiqingView");
	    	day.add(Calendar.MONTH, -9);
	    	begintime = sdf.format(day.getTime());
			break;
		case 2://月份
			sql.append(",year(shijian) as xa, month(shijian) as xb");
			sql.append(" FROM LiqingView");
			day.add(Calendar.MONTH, -3);
	    	begintime = sdf.format(day.getTime());
			break;
		case 3://周
			sql.append(",year(shijian) as xa, datename(week, shijian) as xb");
			sql.append(" FROM LiqingView");
			day.add(Calendar.WEEK_OF_YEAR, -4);
	    	begintime = sdf.format(day.getTime());
			break;
		case 4://天
			sql.append(",month(shijian) as xa, day(shijian) as xb");
			sql.append(" FROM LiqingView");
			day.add(Calendar.DATE, -6);
	    	begintime = sdf.format(day.getTime());
			break;		
		default:
			sql.append(",month(shijian) as xa, day(shijian) as xb");
			sql.append(" FROM LiqingView");
			day.add(Calendar.DATE, -6);
	    	begintime = sdf.format(day.getTime());
			break;
		}
		if(StringUtil.Null2Blank(startTime).length()>0) 
		{
			begintime = startTime;
		}
		if(StringUtil.Null2Blank(endTime).length()>0)
		{
			nowtime = endTime;
		}
		
		sql.append(" where (shijian between '"+begintime+"' and '"+nowtime+"') ");
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and shebeibianhao = '"+shebeibianhao+"'");
		}
		if (null != xiangmubu) {
			sql.append(" and xiangmubuid=" + xiangmubu);
		}
		

		
		switch (cnfxlx) {
		case 1:
			sql.append(" group by year(shijian), datename(quarter, shijian) order by datename(quarter, shijian)");
			break;
		case 2:
			sql.append(" group by year(shijian), month(shijian) order by month(shijian)");
			break;
		case 3:
			sql.append(" group by year(shijian), datename(week, shijian) order by datename(week, shijian)");
			break;
		case 4://天
			sql.append(" group by year(shijian), month(shijian), ");
			sql.append(" day(shijian) order by month(shijian),day(shijian)");			
			break;		
		default:
			sql.append(" group by year(shijian), month(shijian), ");
			sql.append(" day(shijian) order by month(shijian),day(shijian)");	
			break;
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
					LiqingView hv = new LiqingView();
					hv.setXa(rs.getString("xa"));
					hv.setXb(rs.getString("xb"));
					hv.setPangshu(rs.getString("pangshu"));
					switch (cnfxlx) {
					case 1:
						hv.setChangliang(String.format("%1$.1f",rs.getDouble("changliang")/1000));
						break;
					case 2:
						hv.setChangliang(String.format("%1$.1f",rs.getDouble("changliang")/1000));
						break;
					case 3:
						hv.setChangliang(String.format("%1$.1f",rs.getDouble("changliang")/1000));
						break;
					case 4:
						hv.setChangliang(String.format("%1$.1f",rs.getDouble("changliang")));
						break;
					default:
						hv.setChangliang(String.format("%1$.1f",rs.getDouble("changliang")/1000));
						break;						
					}
					getChaobiaops(hv,startTime, endTime, shebeibianhao,
							biaoduan, xiangmubu, cnfxlx, fn, bsid, hv.getXa(), hv.getXb());
					try {
						if (Double.parseDouble(hv.getPangshu())>0) {
						 hv.setAmend(String.format("%1$.2f",
								 Double.parseDouble(hv.getAmbegin())*100/Double.parseDouble(hv.getPangshu())));
						 hv.setPmend(String.format("%1$.2f",
								 Double.parseDouble(hv.getPmbegin())*100/Double.parseDouble(hv.getPangshu())));
						 hv.setBiaoshi(String.format("%1$.2f",
								 Double.parseDouble(hv.getBeizhu())*100/Double.parseDouble(hv.getPangshu())));
						}
					} catch (Exception e) {
					}
					hv.setHoudu(getHouduavg(startTime, endTime, shebeibianhao, 
							biaoduan, xiangmubu, cnfxlx, fn, bsid, hv.getXa(), hv.getXb()));
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
		
		return _returnValue;
	}
	
	@Override
	public LiqingView lqstatisticsinfo() {
		LiqingView _returnValue=new LiqingView();
		
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		String qsql = "SELECT SUM(CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') " +
		              "THEN '0' ELSE changliang END) AS numeric(38, 2))) AS changliang,COUNT(bianhao) as pangshu FROM LiqingView";		
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();			
				rs = st.executeQuery(qsql);
				if (rs.next()) {
					_returnValue.setPangshu(rs.getString("pangshu"));
					_returnValue.setChangliang(String.format("%1$.1f",rs.getDouble("changliang")/1000));
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
		
			qsql = "SELECT Count(jieguoid) as cbps From LiqingxixxjieguoView";
			
			try {
				con = getTemplate().getSessionFactory().openSession().connection();
				st = con.createStatement();			
			
					rs = st.executeQuery(qsql);
					if (rs.next()) {
						_returnValue.setChaobiaops(rs.getString("cbps"));
					} else {
						_returnValue.setChaobiaops("0");
					}
						try {
							if (Double.parseDouble(_returnValue.getPangshu())>0) {
								_returnValue.setChaobiaobfl(String.format("%1$.2f",
									 Double.parseDouble(_returnValue.getChaobiaops())*100/Double.parseDouble(_returnValue.getPangshu())));
							}
						} catch (Exception e) {
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
	public GenericPageMode lqphbviewlist(String shebeibianhao, 
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize,String peifan) {
		List<LiqingphbView> _returnValue = new ArrayList<LiqingphbView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LiqingphbView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}

		
		String queryCondition =  " llysb is not null  and llysb <> '' and (convert(datetime,shijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and shebeibianhao='" + shebeibianhao + "'";
		}
		if (StringUtil.Null2Blank(peifan).length() > 0) {
			queryCondition += " and peifan='" + peifan + "'";
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
			cs.setString(3, "bianhao");
			cs.setString(4, "bianhao DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingphbView hv = new LiqingphbView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));	
					hv.setShijian(rs.getString("shijian"));
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					hv.setCaijishijian(rs.getString("caijishijian"));
					hv.setJbsj(rs.getString("jbsj"));
					hv.setYonghu(rs.getString("yonghu"));
					hv.setPeifan(rs.getString("peifan"));
					hv.setLqwd(rs.getString("lqwd"));
					hv.setGlwd(rs.getString("glwd"));
					hv.setChangliang(rs.getString("changliang"));
					hv.setBeiy1(rs.getString("beiy1"));
					hv.setBeiy2(rs.getString("beiy2"));
					hv.setBeiy3(rs.getString("beiy3"));
					
					try {
						hv.setSjg1(String.format("%1$.1f", rs
								.getFloat("sjg1")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg2(String.format("%1$.1f", rs
								.getFloat("sjg2")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg3(String.format("%1$.1f", rs
								.getFloat("sjg3")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg4(String.format("%1$.1f", rs
								.getFloat("sjg4")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg5(String.format("%1$.1f", rs
								.getFloat("sjg5")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg6(String.format("%1$.1f", rs
								.getFloat("sjg6")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg7(String.format("%1$.1f", rs
								.getFloat("sjg7")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjf1(String.format("%1$.1f", rs
								.getFloat("sjf1")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjf2(String.format("%1$.1f", rs
								.getFloat("sjf2")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjlq(String.format("%1$.1f", rs
								.getFloat("sjlq")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjtjj(String.format("%1$.1f", rs
								.getFloat("sjtjj")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjysb(String.format("%1$.2f", rs
								.getFloat("sjysb")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setLlg1(String.format("%1$.1f", rs
								.getFloat("llg1")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlg2(String.format("%1$.1f", rs
								.getFloat("llg2")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlg3(String.format("%1$.1f", rs
								.getFloat("llg3")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlg4(String.format("%1$.1f", rs
								.getFloat("llg4")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlg5(String.format("%1$.1f", rs
								.getFloat("llg5")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlg6(String.format("%1$.1f", rs
								.getFloat("llg6")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlg7(String.format("%1$.1f", rs
								.getFloat("llg7")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlf1(String.format("%1$.1f", rs
								.getFloat("llf1")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlf2(String.format("%1$.1f", rs
								.getFloat("llf2")));
					} catch (Exception e) {						
					}
					try {
						hv.setLllq(String.format("%1$.1f", rs
								.getFloat("lllq")));
					} catch (Exception e) {						
					}
					try {
						hv.setLltjj(String.format("%1$.1f", rs
								.getFloat("lltjj")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlysb(String.format("%1$.2f", rs
								.getFloat("llysb")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setPersjg1(String.format("%1$.1f", rs
								.getFloat("persjg1")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjg2(String.format("%1$.1f", rs
								.getFloat("persjg2")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjg3(String.format("%1$.1f", rs
								.getFloat("persjg3")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjg4(String.format("%1$.1f", rs
								.getFloat("persjg4")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjg5(String.format("%1$.1f", rs
								.getFloat("persjg5")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjg6(String.format("%1$.1f", rs
								.getFloat("persjg6")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjg7(String.format("%1$.1f", rs
								.getFloat("persjg7")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjf1(String.format("%1$.1f", rs
								.getFloat("persjf1")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjf2(String.format("%1$.1f", rs
								.getFloat("persjf2")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjlq(String.format("%1$.1f", rs
								.getFloat("persjlq")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjtjj(String.format("%1$.1f", rs
								.getFloat("persjtjj")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setWsjg1(String.format("%1$.1f", rs
								.getFloat("wsjg1")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjg2(String.format("%1$.1f", rs
								.getFloat("wsjg2")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjg3(String.format("%1$.1f", rs
								.getFloat("wsjg3")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjg4(String.format("%1$.1f", rs
								.getFloat("wsjg4")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjg5(String.format("%1$.1f", rs
								.getFloat("wsjg5")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjg6(String.format("%1$.1f", rs
								.getFloat("wsjg6")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjg7(String.format("%1$.1f", rs
								.getFloat("wsjg7")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjf1(String.format("%1$.1f", rs
								.getFloat("wsjf1")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjf2(String.format("%1$.1f", rs
								.getFloat("wsjf2")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjlq(String.format("%1$.1f", rs
								.getFloat("wsjlq")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjtjj(String.format("%1$.1f", rs
								.getFloat("wsjtjj")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjysb(String.format("%1$.2f", rs
								.getFloat("wsjysb")));
					} catch (Exception e) {						
					}
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
	public GenericPageMode lqmanualphbviewlist(String shebeibianhao, 
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize,String peifan) {
		List<LiqingmanualphbView> _returnValue = new ArrayList<LiqingmanualphbView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LiqingmanualphbView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition =  " (convert(datetime,shijian,121) between '" + strstartTime+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and shebeibianhao='" + shebeibianhao + "'";
		}
		if (StringUtil.Null2Blank(peifan).length() > 0) {
			queryCondition += " and peifan='" + peifan + "'";
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
			cs.setString(3, "bianhao");
			cs.setString(4, "bianhao DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingmanualphbView hv = new LiqingmanualphbView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));	
					hv.setShijian(rs.getString("shijian"));
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					hv.setCaijishijian(rs.getString("caijishijian"));
					hv.setJbsj(rs.getString("jbsj"));
					hv.setYonghu(rs.getString("yonghu"));
					hv.setPeifan(rs.getString("peifan"));
					hv.setLqwd(rs.getString("lqwd"));
					hv.setGlwd(rs.getString("glwd"));
					hv.setClwd(rs.getString("clwd"));
					hv.setChangliang(rs.getString("changliang"));
					hv.setBeiy1(rs.getString("beiy1"));
					hv.setBeiy2(rs.getString("beiy2"));
					hv.setBeiy3(rs.getString("beiy3"));
					//实际值
					try {
						hv.setSjg1(String.format("%1$.1f", rs.getFloat("sjg1")));
					} catch (Exception e) {}
					try {
						hv.setSjg2(String.format("%1$.1f", rs.getFloat("sjg2")));
					} catch (Exception e) {}
					try {
						hv.setSjg3(String.format("%1$.1f", rs.getFloat("sjg3")));
					} catch (Exception e) {}
					try {
						hv.setSjg4(String.format("%1$.1f", rs.getFloat("sjg4")));
					} catch (Exception e) {}
					try {
						hv.setSjg5(String.format("%1$.1f", rs.getFloat("sjg5")));
					} catch (Exception e) {}
					try {
						hv.setSjg6(String.format("%1$.1f", rs.getFloat("sjg6")));
					} catch (Exception e) {}
					try {
						hv.setSjg7(String.format("%1$.1f", rs.getFloat("sjg7")));
					} catch (Exception e) {}
					try {
						hv.setSjf1(String.format("%1$.2f", rs.getFloat("sjf1")));
					} catch (Exception e) {}
					try {
						hv.setSjf2(String.format("%1$.2f", rs.getFloat("sjf2")));
					} catch (Exception e) {}
					try {
						hv.setSjlq(String.format("%1$.2f", rs.getFloat("sjlq")));
					} catch (Exception e) {}
					try {
						hv.setSjtjj(String.format("%1$.2f", rs.getFloat("sjtjj")));
					} catch (Exception e) {}
					try {
						hv.setSjysb(String.format("%1$.2f", rs.getFloat("sjysb")));
					} catch (Exception e) {}
					//实际配合比
					try {
						hv.setPersjg1(String.format("%1$.1f", rs.getFloat("persjg1")));
					} catch (Exception e) {}
					try {
						hv.setPersjg2(String.format("%1$.1f", rs.getFloat("persjg2")));
					} catch (Exception e) {}
					try {
						hv.setPersjg3(String.format("%1$.1f", rs.getFloat("persjg3")));
					} catch (Exception e) {}
					try {
						hv.setPersjg4(String.format("%1$.1f", rs.getFloat("persjg4")));
					} catch (Exception e) {}
					try {
						hv.setPersjg5(String.format("%1$.1f", rs.getFloat("persjg5")));
					} catch (Exception e) {}
					try {
						hv.setPersjg6(String.format("%1$.1f", rs.getFloat("persjg6")));
					} catch (Exception e) {}
					try {
						hv.setPersjg7(String.format("%1$.1f", rs.getFloat("persjg7")));
					} catch (Exception e) {}
					try {
						hv.setPersjf1(String.format("%1$.2f", rs.getFloat("persjf1")));
					} catch (Exception e) {}
					try {
						hv.setPersjf2(String.format("%1$.2f", rs.getFloat("persjf2")));
					} catch (Exception e) {}
					try {
						hv.setPersjlq(String.format("%1$.2f", rs.getFloat("persjlq")));
					} catch (Exception e) {}
					try {
						hv.setPersjtjj(String.format("%1$.2f", rs.getFloat("persjtjj")));
					} catch (Exception e) {}
					//关联理论配合比
					if(StringUtil.Null2Blank(rs.getString("biaoshi")).length()>0){
						//关联理论配合比
						hv.setLlid(rs.getInt("llid"));
						hv.setLlbuwei(rs.getString("llbuwei"));
						hv.setLilunname(rs.getString("lilunname"));
						hv.setLlmoren(rs.getString("llmoren"));
						
						try {
							hv.setLlg1(String.format("%1$.1f", rs.getFloat("perllg1")));
						} catch (Exception e) {}
						try {
							hv.setLlg2(String.format("%1$.1f", rs.getFloat("perllg2")));
						} catch (Exception e) {}
						try {
							hv.setLlg3(String.format("%1$.1f", rs.getFloat("perllg3")));
						} catch (Exception e) {}
						try {
							hv.setLlg4(String.format("%1$.1f", rs.getFloat("perllg4")));
						} catch (Exception e) {}
						try {
							hv.setLlg5(String.format("%1$.1f", rs.getFloat("perllg5")));
						} catch (Exception e) {}
						try {
							hv.setLlg6(String.format("%1$.1f", rs.getFloat("perllg6")));
						} catch (Exception e) {}
						try {
							hv.setLlg7(String.format("%1$.1f", rs.getFloat("perllg7")));
						} catch (Exception e) {}
						try {
							hv.setLlf1(String.format("%1$.2f", rs.getFloat("perllf1")));
						} catch (Exception e) {}
						try {
							hv.setLlf2(String.format("%1$.2f", rs.getFloat("perllf2")));
						} catch (Exception e) {}
						try {
							hv.setLllq(String.format("%1$.2f", rs.getFloat("perlllq")));
						} catch (Exception e) {}
						try {
							hv.setLltjj(String.format("%1$.2f", rs.getFloat("perlltjj")));
						} catch (Exception e) {}
						try {
							hv.setLlysb(String.format("%1$.2f", rs.getFloat("perllysb")));
						} catch (Exception e) {}
						
						try {
							hv.setWsjg1(String.format("%1$.2f", rs.getFloat("manualwsjg1")));
						} catch (Exception e) {}
						try {
							hv.setWsjg2(String.format("%1$.2f", rs.getFloat("manualwsjg2")));
						} catch (Exception e) {}
						try {
							hv.setWsjg3(String.format("%1$.2f", rs.getFloat("manualwsjg3")));
						} catch (Exception e) {}
						try {
							hv.setWsjg4(String.format("%1$.2f", rs.getFloat("manualwsjg4")));
						} catch (Exception e) {}
						try {
							hv.setWsjg5(String.format("%1$.2f", rs.getFloat("manualwsjg5")));
						} catch (Exception e) {}
						try {
							hv.setWsjg6(String.format("%1$.2f", rs.getFloat("manualwsjg6")));
						} catch (Exception e) {}
						try {
							hv.setWsjg7(String.format("%1$.2f", rs.getFloat("manualwsjg7")));
						} catch (Exception e) {}
						try {
							hv.setWsjf1(String.format("%1$.2f", rs.getFloat("manualwsjf1")));
						} catch (Exception e) {}
						try {
							hv.setWsjf2(String.format("%1$.2f", rs.getFloat("manualwsjf2")));
						} catch (Exception e) {}
						try {
							hv.setWsjlq(String.format("%1$.2f", rs.getFloat("manualwsjlq")));
						} catch (Exception e) {}
						try {
							hv.setWsjtjj(String.format("%1$.2f", rs.getFloat("manualwsjtjj")));
						} catch (Exception e) {}
						try {
							hv.setWsjysb(String.format("%1$.2f", rs.getFloat("manualwsjysb")));
						} catch (Exception e) {}
						
					}else{
						try {
							hv.setLlg1(String.format("%1$.1f", rs.getFloat("llg1")));
						} catch (Exception e) {}
						try {
							hv.setLlg2(String.format("%1$.1f", rs.getFloat("llg2")));
						} catch (Exception e) {}
						try {
							hv.setLlg3(String.format("%1$.1f", rs.getFloat("llg3")));
						} catch (Exception e) {}
						try {
							hv.setLlg4(String.format("%1$.1f", rs.getFloat("llg4")));
						} catch (Exception e) {}
						try {
							hv.setLlg5(String.format("%1$.1f", rs.getFloat("llg5")));
						} catch (Exception e) {}
						try {
							hv.setLlg6(String.format("%1$.1f", rs.getFloat("llg6")));
						} catch (Exception e) {}
						try {
							hv.setLlg7(String.format("%1$.1f", rs.getFloat("llg7")));
						} catch (Exception e) {}
						try {
							hv.setLlf1(String.format("%1$.2f", rs.getFloat("llf1")));
						} catch (Exception e) {}
						try {
							hv.setLlf2(String.format("%1$.2f", rs.getFloat("llf2")));
						} catch (Exception e) {}
						try {
							hv.setLllq(String.format("%1$.2f", rs.getFloat("lllq")));
						} catch (Exception e) {}
						try {
							hv.setLltjj(String.format("%1$.2f", rs.getFloat("lltjj")));
						} catch (Exception e) {}
						try {
							hv.setLlysb(String.format("%1$.2f", rs.getFloat("llysb")));
						} catch (Exception e) {}
						
						try {
							hv.setWsjg1(String.format("%1$.2f", rs.getFloat("wsjg1")));
						} catch (Exception e) {}
						try {
							hv.setWsjg2(String.format("%1$.2f", rs.getFloat("wsjg2")));
						} catch (Exception e) {}
						try {
							hv.setWsjg3(String.format("%1$.2f", rs.getFloat("wsjg3")));
						} catch (Exception e) {}
						try {
							hv.setWsjg4(String.format("%1$.2f", rs.getFloat("wsjg4")));
						} catch (Exception e) {}
						try {
							hv.setWsjg5(String.format("%1$.2f", rs.getFloat("wsjg5")));
						} catch (Exception e) {}
						try {
							hv.setWsjg6(String.format("%1$.2f", rs.getFloat("wsjg6")));
						} catch (Exception e) {}
						try {
							hv.setWsjg7(String.format("%1$.2f", rs.getFloat("wsjg7")));
						} catch (Exception e) {}
						try {
							hv.setWsjf1(String.format("%1$.2f", rs.getFloat("wsjf1")));
						} catch (Exception e) {}
						try {
							hv.setWsjf2(String.format("%1$.2f", rs.getFloat("wsjf2")));
						} catch (Exception e) {}
						try {
							hv.setWsjlq(String.format("%1$.2f", rs.getFloat("wsjlq")));
						} catch (Exception e) {}
						try {
							hv.setWsjtjj(String.format("%1$.2f", rs.getFloat("wsjtjj")));
						} catch (Exception e) {}
						try {
							hv.setWsjysb(String.format("%1$.2f", rs.getFloat("wsjysb")));
						} catch (Exception e) {}
					}
					_returnValue.add(hv);
				}
				pagemode.setDatas(_returnValue);
				pagemode.setRecordcount(cs.getInt(7));
				pagemode.setPagetotal(cs.getInt(8));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			DbJdbcUtil.closeAll(rs, cs, con);
		}
		return pagemode;
	}
	
	@Override
	public GenericPageMode lqchaobiaoviewlist(LiqingziduancfgView lqisshow,Integer chaobiaolx, String shebeibianhao, 
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize) {
		List<LiqingphbView> _returnValue = new ArrayList<LiqingphbView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LiqingchaobiaoView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}

		StringBuilder queryCondition = new StringBuilder("");
		queryCondition.append(" llysb is not null and llysb <> '' and (convert(datetime,shijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')");
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition.append(" and "+fn+"=" + bsid);
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition.append(" and shebeibianhao='" + shebeibianhao + "'");
		}
		
		if (null != biaoduan) {
			queryCondition.append(" and biaoduanid=" + biaoduan);
		}
		if (null != xiangmubu) {
			queryCondition.append(" and xiangmubuid=" + xiangmubu);
		}
		
		if (null != lqisshow) {
			StringBuilder tempCondition = new StringBuilder("1=2");
			switch (chaobiaolx) {
			case 0:
				if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjysb is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg1 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg2 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg3 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg4 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg5 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg6 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg7 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf1 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf2 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjtjj is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjlq is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getGlwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbglwd is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getLqwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cblqwd is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getBeiy1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbbeiy1 is not null");
				}
				break;
			case 1:
				if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjysb ='1' or cbsjysb ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg1 ='1' or cbsjg1 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg2 ='1' or cbsjg2 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg3 ='1' or cbsjg3 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg4 ='1' or cbsjg4 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg5 ='1' or cbsjg5 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg6 ='1' or cbsjg6 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg7 ='1' or cbsjg7 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf1 ='1' or cbsjf1 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf2 ='1' or cbsjf2 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjtjj ='1' or cbsjtjj ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjlq ='1' or cbsjlq ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getGlwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbglwd ='1' or cbsjlq ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getLqwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cblqwd ='1' or cbsjlq ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getBeiy1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbbeiy1 ='1' or cbsjlq ='4'");
				}
				break;
			case 2:
				if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjysb ='2' or cbsjysb ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg1 ='2' or cbsjg1 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg2 ='2' or cbsjg2 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg3 ='2' or cbsjg3 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg4 ='2' or cbsjg4 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg5 ='2' or cbsjg5 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg6 ='2' or cbsjg6 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg7 ='2' or cbsjg7 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf1 ='2' or cbsjf1 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf2 ='2' or cbsjf2 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjtjj ='2' or cbsjtjj ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjlq ='2' or cbsjlq ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getGlwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbglwd ='2' or cbsjlq ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getLqwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cblqwd ='2' or cbsjlq ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getBeiy1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbbeiy1 ='2' or cbsjlq ='5'");
				}
				break;
			case 3:
				if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjysb ='3' or cbsjysb ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg1 ='3' or cbsjg1 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg2 ='3' or cbsjg2 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg3 ='3' or cbsjg3 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg4 ='3' or cbsjg4 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg5 ='3' or cbsjg5 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg6 ='3' or cbsjg6 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg7 ='3' or cbsjg7 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf1 ='3' or cbsjf1 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf2 ='3' or cbsjf2 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjtjj ='3' or cbsjtjj ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjlq ='3' or cbsjlq ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getGlwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbglwd ='3' or cbsjlq ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getLqwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cblqwd ='3' or cbsjlq ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getBeiy1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbbeiy1 ='3' or cbsjlq ='6'");
				}
				break;
			}
			
			if (tempCondition.toString().length()>6) {
				queryCondition.append(" and ("+tempCondition.toString()+")");
			}
		}

		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "bianhao");
			cs.setString(4, "bianhao DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition.toString());
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingphbView hv = new LiqingphbView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));	
					hv.setShijian(rs.getString("shijian"));
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					hv.setCaijishijian(rs.getString("caijishijian"));
					hv.setJbsj(rs.getString("jbsj"));
					hv.setYonghu(rs.getString("yonghu"));
					hv.setPeifan(rs.getString("peifan"));
					try{
						hv.setLqwd(String.format("%1$.1f", rs.getFloat("lqwd")));
					}catch(Exception ex){}
					try{
						hv.setGlwd(String.format("%1$.1f", rs.getFloat("glwd")));
					}catch(Exception ex){}
					try{
						hv.setClwd(String.format("%1$.1f", rs.getFloat("clwd")));
					}catch(Exception ex){}
					hv.setChangliang(rs.getString("changliang"));
					
					hv.setBeiy1(rs.getString("beiy1"));
					hv.setBeiy2(rs.getString("beiy2"));
					hv.setBeiy3(rs.getString("beiy3"));
					
					if(rs.getString("cbsjysb")==null||rs.getString("cbsjysb").equals("")){
						hv.setCbsjysb("abc");
					}else{
						hv.setCbsjysb(rs.getString("cbsjysb"));
					}
					if(rs.getString("cbsjg1")==null||rs.getString("cbsjg1").equals("")){
						hv.setCbsjg1("abc");
					}else{
						hv.setCbsjg1(rs.getString("cbsjg1"));
					}
					
					if(rs.getString("cbsjg2")==null||rs.getString("cbsjg2").equals("")){
						hv.setCbsjg2("abc");
					}else{
						hv.setCbsjg2(rs.getString("cbsjg2"));
					}
				
					if(rs.getString("cbsjg3")==null||rs.getString("cbsjg3").equals("")){
						hv.setCbsjg3("abc");
					}else{
						hv.setCbsjg3(rs.getString("cbsjg3"));
					}
					
					if(rs.getString("cbsjg4")==null||rs.getString("cbsjg4").equals("")){
						hv.setCbsjg4("abc");
					}else{
						hv.setCbsjg4(rs.getString("cbsjg4"));
					}
					
					if(rs.getString("cbsjg5")==null||rs.getString("cbsjg5").equals("")){
						hv.setCbsjg5("abc");
					}else{
						hv.setCbsjg5(rs.getString("cbsjg5"));
					}
					
					if(rs.getString("cbsjg6")==null||rs.getString("cbsjg6").equals("")){
						hv.setCbsjg6("abc");
					}else{
						hv.setCbsjg6(rs.getString("cbsjg6"));
					}
					
					if(rs.getString("cbsjg7")==null||rs.getString("cbsjg7").equals("")){
						hv.setCbsjg7("abc");
					}else{
						hv.setCbsjg7(rs.getString("cbsjg7"));
					}
					
					if(rs.getString("cbsjf1")==null||rs.getString("cbsjf1").equals("")){
						hv.setCbsjf1("abc");
					}else{
						hv.setCbsjf1(rs.getString("cbsjf1"));
					}
					
					if(rs.getString("cbsjf2")==null||rs.getString("cbsjf2").equals("")){
						hv.setCbsjf2("abc");
					}else{
						hv.setCbsjf2(rs.getString("cbsjf2"));
					}
					
					if(rs.getString("cbsjtjj")==null||rs.getString("cbsjtjj").equals("")){
						hv.setCbsjtjj("abc");
					}else{
						hv.setCbsjtjj(rs.getString("cbsjtjj"));
					}
					
					if(rs.getString("cbsjlq")==null||rs.getString("cbsjlq").equals("")){
						hv.setCbsjlq("abc");
					}else{
						hv.setCbsjlq(rs.getString("cbsjlq"));
					}
					
					if(rs.getString("cbglwd")==null||rs.getString("cbglwd").equals("")){
						hv.setCbglwd("abc");
					}else{
						hv.setCbglwd(rs.getString("cbglwd"));
					}
					
					if(rs.getString("cblqwd")==null||rs.getString("cblqwd").equals("")){
						hv.setCblqwd("abc");
					}else{
						hv.setCblqwd(rs.getString("cblqwd"));
					}
				    
					
					try {
						hv.setSjg1(String.format("%1$.1f", rs
								.getFloat("sjg1")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg2(String.format("%1$.1f", rs
								.getFloat("sjg2")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg3(String.format("%1$.1f", rs
								.getFloat("sjg3")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg4(String.format("%1$.1f", rs
								.getFloat("sjg4")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg5(String.format("%1$.1f", rs
								.getFloat("sjg5")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg6(String.format("%1$.1f", rs
								.getFloat("sjg6")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg7(String.format("%1$.1f", rs
								.getFloat("sjg7")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjf1(String.format("%1$.1f", rs
								.getFloat("sjf1")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjf2(String.format("%1$.1f", rs
								.getFloat("sjf2")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjlq(String.format("%1$.1f", rs
								.getFloat("sjlq")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjtjj(String.format("%1$.1f", rs
								.getFloat("sjtjj")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjysb(String.format("%1$.2f", rs
								.getFloat("sjysb")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setLlg1(String.format("%1$.1f", rs
								.getFloat("llg1")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlg2(String.format("%1$.1f", rs
								.getFloat("llg2")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlg3(String.format("%1$.1f", rs
								.getFloat("llg3")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlg4(String.format("%1$.1f", rs
								.getFloat("llg4")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlg5(String.format("%1$.1f", rs
								.getFloat("llg5")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlg6(String.format("%1$.1f", rs
								.getFloat("llg6")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlg7(String.format("%1$.1f", rs
								.getFloat("llg7")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlf1(String.format("%1$.1f", rs
								.getFloat("llf1")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlf2(String.format("%1$.1f", rs
								.getFloat("llf2")));
					} catch (Exception e) {						
					}
					try {
						hv.setLllq(String.format("%1$.1f", rs
								.getFloat("lllq")));
					} catch (Exception e) {						
					}
					try {
						hv.setLltjj(String.format("%1$.1f", rs
								.getFloat("lltjj")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlysb(String.format("%1$.2f", rs
								.getFloat("llysb")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setPersjg1(String.format("%1$.1f", rs
								.getFloat("persjg1")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjg2(String.format("%1$.1f", rs
								.getFloat("persjg2")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjg3(String.format("%1$.1f", rs
								.getFloat("persjg3")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjg4(String.format("%1$.1f", rs
								.getFloat("persjg4")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjg5(String.format("%1$.1f", rs
								.getFloat("persjg5")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjg6(String.format("%1$.1f", rs
								.getFloat("persjg6")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjg7(String.format("%1$.1f", rs
								.getFloat("persjg7")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjf1(String.format("%1$.1f", rs
								.getFloat("persjf1")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjf2(String.format("%1$.1f", rs
								.getFloat("persjf2")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjlq(String.format("%1$.1f", rs
								.getFloat("persjlq")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjtjj(String.format("%1$.1f", rs
								.getFloat("persjtjj")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setWsjg1(String.format("%1$.1f", rs
								.getFloat("wsjg1")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjg2(String.format("%1$.1f", rs
								.getFloat("wsjg2")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjg3(String.format("%1$.1f", rs
								.getFloat("wsjg3")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjg4(String.format("%1$.1f", rs
								.getFloat("wsjg4")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjg5(String.format("%1$.1f", rs
								.getFloat("wsjg5")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjg6(String.format("%1$.1f", rs
								.getFloat("wsjg6")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjg7(String.format("%1$.1f", rs
								.getFloat("wsjg7")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjf1(String.format("%1$.1f", rs
								.getFloat("wsjf1")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjf2(String.format("%1$.1f", rs
								.getFloat("wsjf2")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjlq(String.format("%1$.1f", rs
								.getFloat("wsjlq")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjtjj(String.format("%1$.1f", rs
								.getFloat("wsjtjj")));
					} catch (Exception e) {						
					}
					try {
						hv.setWsjysb(String.format("%1$.1f", rs
								.getFloat("wsjysb")));
					} catch (Exception e) {						
					}
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
	public GenericPageMode lqchaobiaomanualviewlist(LiqingziduancfgView lqisshow,Integer chaobiaolx, String shebeibianhao, 
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize ,Integer cllx,String bianhao) {
		List<LiqingmanualphbView> _returnValue = new ArrayList<LiqingmanualphbView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LiqingmanualchaobiaoView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}

		String queryCondition =
		" (convert(datetime,shijian,121) between '" + strstartTime+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition +=" and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition +=" and shebeibianhao='" + shebeibianhao + "'";
		}
		
		if (null != biaoduan) {
			queryCondition +=" and biaoduanid=" + biaoduan;
		}
		if (null != xiangmubu) {
			queryCondition +=" and xiangmubuid=" + xiangmubu;
		}
		//超标处理类型
		if (cllx == 1) {
			queryCondition += " and  filepath is NULL  and ISNULL(chulijieguo,'')='' ";
		} else if (cllx >= 2) {
			queryCondition += " and (filepath <> '' or  ISNULL(chulijieguo,'')<>'') ";
			if (cllx == 3) {
				queryCondition += " and  yezhuyijian is null ";
			} else if (cllx == 4) {
				queryCondition += " and  yezhuyijian <> '' ";
			}
		}
		// 检索超标内容
		if (StringUtil.Null2Blank(bianhao).length() > 0) {
			queryCondition +=" and bianhao=" + Integer.parseInt(bianhao);
		}
		
		
		if (null != lqisshow) {
			StringBuilder tempCondition = new StringBuilder("1=2");
			switch (chaobiaolx) {
			case 0:
				if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjysb is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg1 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg2 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg3 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg4 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg5 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg6 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg7 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf1 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf2 is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjtjj is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjlq is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getGlwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbglwd is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getLqwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cblqwd is not null");
				}
				if (StringUtil.Null2Blank(lqisshow.getBeiy1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbbeiy1 is not null");
				}
				break;
			case 1:
				if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjysb ='1' or cbsjysb ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg1 ='1' or cbsjg1 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg2 ='1' or cbsjg2 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg3 ='1' or cbsjg3 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg4 ='1' or cbsjg4 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg5 ='1' or cbsjg5 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg6 ='1' or cbsjg6 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg7 ='1' or cbsjg7 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf1 ='1' or cbsjf1 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf2 ='1' or cbsjf2 ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjtjj ='1' or cbsjtjj ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjlq ='1' or cbsjlq ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getGlwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbglwd ='1' or cbglwd ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getLqwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cblqwd ='1' or cblqwd ='4'");
				}
				if (StringUtil.Null2Blank(lqisshow.getBeiy1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbbeiy1 ='1' or cbbeiy1 ='4'");
				}
				break;
			case 2:
				if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjysb ='2' or cbsjysb ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg1 ='2' or cbsjg1 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg2 ='2' or cbsjg2 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg3 ='2' or cbsjg3 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg4 ='2' or cbsjg4 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg5 ='2' or cbsjg5 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg6 ='2' or cbsjg6 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg7 ='2' or cbsjg7 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf1 ='2' or cbsjf1 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf2 ='2' or cbsjf2 ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjtjj ='2' or cbsjtjj ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjlq ='2' or cbsjlq ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getGlwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbglwd ='2' or cbglwd ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getLqwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cblqwd ='2' or cblqwd ='5'");
				}
				if (StringUtil.Null2Blank(lqisshow.getBeiy1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbbeiy1 ='2' or cbbeiy1 ='5'");
				}
				break;
			case 3:
				if (StringUtil.Null2Blank(lqisshow.getSjysb()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjysb ='3' or cbsjysb ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg1 ='3' or cbsjg1 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg2 ='3' or cbsjg2 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg3()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg3 ='3' or cbsjg3 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg4()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg4 ='3' or cbsjg4 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg5()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg5 ='3' or cbsjg5 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg6()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg6 ='3' or cbsjg6 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjg7()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjg7 ='3' or cbsjg7 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf1 ='3' or cbsjf1 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjf2()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjf2 ='3' or cbsjf2 ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjtjj()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjtjj ='3' or cbsjtjj ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getSjlq()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbsjlq ='3' or cbsjlq ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getGlwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbglwd ='3' or cbglwd ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getLqwd()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cblqwd ='3' or cblqwd ='6'");
				}
				if (StringUtil.Null2Blank(lqisshow.getBeiy1()).equalsIgnoreCase("1")) {
					tempCondition.append(" or cbbeiy1 ='3' or cbbeiy1 ='6'");
				}
				break;
			}
			
			if (tempCondition.toString().length()>6) {
				queryCondition+=" and ("+tempCondition.toString()+")";
			}
		}
		
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "bianhao");
			cs.setString(4, "bianhao DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition.toString());
			
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingmanualphbView hv = new LiqingmanualphbView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));	
					hv.setShijian(rs.getString("shijian"));
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					hv.setCaijishijian(rs.getString("caijishijian"));
					hv.setJbsj(rs.getString("jbsj"));
					hv.setYonghu(rs.getString("yonghu"));
					hv.setLeixing(rs.getString("leixing"));
					hv.setChulijieguo(rs.getString("chulijieguo"));
					hv.setFilepath(rs.getString("filepath"));
					hv.setYezhuyijian(rs.getString("yezhuyijian"));
					try{
						hv.setLqwd(String.format("%1$.1f", rs.getFloat("lqwd")));
					}catch(Exception ex){}
					try{
						hv.setGlwd(String.format("%1$.1f", rs.getFloat("glwd")));
					}catch(Exception ex){}
					try{
						hv.setClwd(String.format("%1$.1f", rs.getFloat("clwd")));
					}catch(Exception ex){}
					try{
						hv.setChangliang(String.format("%1$.2f",rs.getFloat("changliang")));
					}catch(Exception ex){}
					hv.setBeiy1(rs.getString("beiy1"));
					hv.setBeiy2(rs.getString("beiy2"));
					hv.setBeiy3(rs.getString("beiy3"));
					//实际值
					try{
						hv.setSjg1(String.format("%1$.2f", rs.getFloat("sjg1")));
					}catch(Exception ex){}
					try{
						hv.setSjg2(String.format("%1$.2f", rs.getFloat("sjg2")));
					}catch(Exception ex){}
					try{
						hv.setSjg3(String.format("%1$.2f", rs.getFloat("sjg3")));
					}catch(Exception ex){}
					try{
						hv.setSjg4(String.format("%1$.2f", rs.getFloat("sjg4")));
					}catch(Exception ex){}
					try{
						hv.setSjg5(String.format("%1$.2f", rs.getFloat("sjg5")));
					}catch(Exception ex){}
					try{
						hv.setSjg6(String.format("%1$.2f", rs.getFloat("sjg6")));
					}catch(Exception ex){}
					try{
						hv.setSjg7(String.format("%1$.2f", rs.getFloat("sjg7")));
					}catch(Exception ex){}
					try{
						hv.setSjf1(String.format("%1$.2f", rs.getFloat("sjf1")));
					}catch(Exception ex){}
					try{
						hv.setSjf2(String.format("%1$.2f", rs.getFloat("sjf2")));
					}catch(Exception ex){}
					try{
						hv.setSjlq(String.format("%1$.2f", rs.getFloat("sjlq")));
					}catch(Exception ex){}
					try{
						hv.setSjtjj(String.format("%1$.2f", rs.getFloat("sjtjj")));
					}catch(Exception ex){}
					//实际配比
					//实际配合比
					try {
						hv.setPersjg1(String.format("%1$.1f", rs.getFloat("persjg1")));
					} catch (Exception e) {}
					try {
						hv.setPersjg2(String.format("%1$.1f", rs.getFloat("persjg2")));
					} catch (Exception e) {}
					try {
						hv.setPersjg3(String.format("%1$.1f", rs.getFloat("persjg3")));
					} catch (Exception e) {}
					try {
						hv.setPersjg4(String.format("%1$.1f", rs.getFloat("persjg4")));
					} catch (Exception e) {}
					try {
						hv.setPersjg5(String.format("%1$.1f", rs.getFloat("persjg5")));
					} catch (Exception e) {}
					try {
						hv.setPersjg6(String.format("%1$.1f", rs.getFloat("persjg6")));
					} catch (Exception e) {}
					try {
						hv.setPersjg7(String.format("%1$.1f", rs.getFloat("persjg7")));
					} catch (Exception e) {}
					try {
						hv.setPersjf1(String.format("%1$.1f", rs.getFloat("persjf1")));
					} catch (Exception e) {}
					try {
						hv.setPersjf2(String.format("%1$.1f", rs.getFloat("persjf2")));
					} catch (Exception e) {}
					try {
						hv.setPersjlq(String.format("%1$.2f", rs.getFloat("persjlq")));
					} catch (Exception e) {}
					try {
						hv.setPersjtjj(String.format("%1$.1f", rs.getFloat("persjtjj")));
					} catch (Exception e) {}
					//关联理论配合比
					if(StringUtil.Null2Blank(rs.getString("biaoshi")).length()>0){
						//关联理论配合比
						hv.setLlid(rs.getInt("llid"));
						hv.setLlbuwei(rs.getString("llbuwei"));
						hv.setLilunname(rs.getString("lilunname"));
						hv.setLlmoren(rs.getString("llmoren"));
						
						try {
							hv.setLlg1(String.format("%1$.1f", rs.getFloat("perllg1")));
						} catch (Exception e) {}
						try {
							hv.setLlg2(String.format("%1$.1f", rs.getFloat("perllg2")));
						} catch (Exception e) {}
						try {
							hv.setLlg3(String.format("%1$.1f", rs.getFloat("perllg3")));
						} catch (Exception e) {}
						try {
							hv.setLlg4(String.format("%1$.1f", rs.getFloat("perllg4")));
						} catch (Exception e) {}
						try {
							hv.setLlg5(String.format("%1$.1f", rs.getFloat("perllg5")));
						} catch (Exception e) {}
						try {
							hv.setLlg6(String.format("%1$.1f", rs.getFloat("perllg6")));
						} catch (Exception e) {}
						try {
							hv.setLlg7(String.format("%1$.1f", rs.getFloat("perllg7")));
						} catch (Exception e) {}
						try {
							hv.setLlf1(String.format("%1$.1f", rs.getFloat("perllf1")));
						} catch (Exception e) {}
						try {
							hv.setLlf2(String.format("%1$.1f", rs.getFloat("perllf2")));
						} catch (Exception e) {}
						try {
							hv.setLllq(String.format("%1$.2f", rs.getFloat("perlllq")));
						} catch (Exception e) {}
						try {
							hv.setLltjj(String.format("%1$.1f", rs.getFloat("perlltjj")));
						} catch (Exception e) {}
						try {
							hv.setLlysb(String.format("%1$.2f", rs.getFloat("perllysb")));
						} catch (Exception e) {}
						
						try {
							hv.setWsjg1(String.format("%1$.1f", rs.getFloat("manualwsjg1")));
						} catch (Exception e) {}
						try {
							hv.setWsjg2(String.format("%1$.1f", rs.getFloat("manualwsjg2")));
						} catch (Exception e) {}
						try {
							hv.setWsjg3(String.format("%1$.1f", rs.getFloat("manualwsjg3")));
						} catch (Exception e) {}
						try {
							hv.setWsjg4(String.format("%1$.1f", rs.getFloat("manualwsjg4")));
						} catch (Exception e) {}
						try {
							hv.setWsjg5(String.format("%1$.1f", rs.getFloat("manualwsjg5")));
						} catch (Exception e) {}
						try {
							hv.setWsjg6(String.format("%1$.1f", rs.getFloat("manualwsjg6")));
						} catch (Exception e) {}
						try {
							hv.setWsjg7(String.format("%1$.1f", rs.getFloat("manualwsjg7")));
						} catch (Exception e) {}
						try {
							hv.setWsjf1(String.format("%1$.1f", rs.getFloat("manualwsjf1")));
						} catch (Exception e) {}
						try {
							hv.setWsjf2(String.format("%1$.1f", rs.getFloat("manualwsjf2")));
						} catch (Exception e) {}
						try {
							hv.setWsjlq(String.format("%1$.2f", rs.getFloat("manualwsjlq")));
						} catch (Exception e) {}
						try {
							hv.setWsjtjj(String.format("%1$.1f", rs.getFloat("manualwsjtjj")));
						} catch (Exception e) {}
						try {
							hv.setWsjysb(String.format("%1$.2f", rs.getFloat("manualwsjysb")));
						} catch (Exception e) {}
						
					}else{
						try {
							hv.setLlg1(String.format("%1$.1f", rs.getFloat("llg1")));
						} catch (Exception e) {}
						try {
							hv.setLlg2(String.format("%1$.1f", rs.getFloat("llg2")));
						} catch (Exception e) {}
						try {
							hv.setLlg3(String.format("%1$.1f", rs.getFloat("llg3")));
						} catch (Exception e) {}
						try {
							hv.setLlg4(String.format("%1$.1f", rs.getFloat("llg4")));
						} catch (Exception e) {}
						try {
							hv.setLlg5(String.format("%1$.1f", rs.getFloat("llg5")));
						} catch (Exception e) {}
						try {
							hv.setLlg6(String.format("%1$.1f", rs.getFloat("llg6")));
						} catch (Exception e) {}
						try {
							hv.setLlg7(String.format("%1$.1f", rs.getFloat("llg7")));
						} catch (Exception e) {}
						try {
							hv.setLlf1(String.format("%1$.1f", rs.getFloat("llf1")));
						} catch (Exception e) {}
						try {
							hv.setLlf2(String.format("%1$.1f", rs.getFloat("llf2")));
						} catch (Exception e) {}
						try {
							hv.setLllq(String.format("%1$.2f", rs.getFloat("lllq")));
						} catch (Exception e) {}
						try {
							hv.setLltjj(String.format("%1$.1f", rs.getFloat("lltjj")));
						} catch (Exception e) {}
						try {
							hv.setLlysb(String.format("%1$.2f", rs.getFloat("llysb")));
						} catch (Exception e) {}
						
						try {
							hv.setWsjg1(String.format("%1$.1f", rs.getFloat("wsjg1")));
						} catch (Exception e) {}
						try {
							hv.setWsjg2(String.format("%1$.1f", rs.getFloat("wsjg2")));
						} catch (Exception e) {}
						try {
							hv.setWsjg3(String.format("%1$.1f", rs.getFloat("wsjg3")));
						} catch (Exception e) {}
						try {
							hv.setWsjg4(String.format("%1$.1f", rs.getFloat("wsjg4")));
						} catch (Exception e) {}
						try {
							hv.setWsjg5(String.format("%1$.1f", rs.getFloat("wsjg5")));
						} catch (Exception e) {}
						try {
							hv.setWsjg6(String.format("%1$.1f", rs.getFloat("wsjg6")));
						} catch (Exception e) {}
						try {
							hv.setWsjg7(String.format("%1$.1f", rs.getFloat("wsjg7")));
						} catch (Exception e) {}
						try {
							hv.setWsjf1(String.format("%1$.1f", rs.getFloat("wsjf1")));
						} catch (Exception e) {}
						try {
							hv.setWsjf2(String.format("%1$.1f", rs.getFloat("wsjf2")));
						} catch (Exception e) {}
						try {
							hv.setWsjlq(String.format("%1$.2f", rs.getFloat("wsjlq")));
						} catch (Exception e) {}
						try {
							hv.setWsjtjj(String.format("%1$.1f", rs.getFloat("wsjtjj")));
						} catch (Exception e) {}
						try {
							hv.setWsjysb(String.format("%1$.2f", rs.getFloat("wsjysb")));
						} catch (Exception e) {}
					}
					//超标率
					hv.setCbsjysb(rs.getString("cbsjysb"));
					if(StringUtil.Null2Blank(hv.getCbsjysb()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbsjysb()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbsjysb()).equalsIgnoreCase("4")){
							hv.setCbsjysb_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbsjysb()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbsjysb()).equalsIgnoreCase("5")){
							hv.setCbsjysb_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbsjysb()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbsjysb()).equalsIgnoreCase("6")){
							hv.setCbsjysb_high("3");
						}
					}
					
					hv.setCbsjg1(rs.getString("cbsjg1"));
					if(StringUtil.Null2Blank(hv.getCbsjg1()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbsjg1()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbsjg1()).equalsIgnoreCase("4")){
							hv.setCbsjg1_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbsjg1()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbsjg1()).equalsIgnoreCase("5")){
							hv.setCbsjg1_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbsjg1()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbsjg1()).equalsIgnoreCase("6")){
							hv.setCbsjg1_high("3");
						}
					}
					
					hv.setCbsjg2(rs.getString("cbsjg2"));
					if(StringUtil.Null2Blank(hv.getCbsjg2()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbsjg2()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbsjg2()).equalsIgnoreCase("4")){
							hv.setCbsjg2_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbsjg2()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbsjg2()).equalsIgnoreCase("5")){
							hv.setCbsjg2_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbsjg2()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbsjg2()).equalsIgnoreCase("6")){
							hv.setCbsjg2_high("3");
						}
					}
					
					hv.setCbsjg3(rs.getString("cbsjg3"));
					if(StringUtil.Null2Blank(hv.getCbsjg3()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbsjg3()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbsjg3()).equalsIgnoreCase("4")){
							hv.setCbsjg3_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbsjg3()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbsjg3()).equalsIgnoreCase("5")){
							hv.setCbsjg3_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbsjg3()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbsjg3()).equalsIgnoreCase("6")){
							hv.setCbsjg3_high("3");
						}
					}
					
					hv.setCbsjg4(rs.getString("cbsjg4"));
					if(StringUtil.Null2Blank(hv.getCbsjg4()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbsjg4()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbsjg4()).equalsIgnoreCase("4")){
							hv.setCbsjg4_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbsjg4()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbsjg4()).equalsIgnoreCase("5")){
							hv.setCbsjg4_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbsjg4()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbsjg4()).equalsIgnoreCase("6")){
							hv.setCbsjg4_high("3");
						}
					}
					
					hv.setCbsjg5(rs.getString("cbsjg5"));
					if(StringUtil.Null2Blank(hv.getCbsjg5()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbsjg5()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbsjg5()).equalsIgnoreCase("4")){
							hv.setCbsjg5_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbsjg5()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbsjg5()).equalsIgnoreCase("5")){
							hv.setCbsjg5_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbsjg5()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbsjg5()).equalsIgnoreCase("6")){
							hv.setCbsjg5_high("3");
						}
					}
					
					hv.setCbsjg6(rs.getString("cbsjg6"));
					if(StringUtil.Null2Blank(hv.getCbsjg6()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbsjg6()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbsjg6()).equalsIgnoreCase("4")){
							hv.setCbsjg6_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbsjg6()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbsjg6()).equalsIgnoreCase("5")){
							hv.setCbsjg6_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbsjg6()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbsjg6()).equalsIgnoreCase("6")){
							hv.setCbsjg6_high("3");
						}
					}
					
					hv.setCbsjg7(rs.getString("cbsjg7"));
					if(StringUtil.Null2Blank(hv.getCbsjg7()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbsjg7()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbsjg7()).equalsIgnoreCase("4")){
							hv.setCbsjg7_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbsjg7()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbsjg7()).equalsIgnoreCase("5")){
							hv.setCbsjg7_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbsjg7()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbsjg7()).equalsIgnoreCase("6")){
							hv.setCbsjg7_high("3");
						}
					}
					
					hv.setCbsjf1(rs.getString("cbsjf1"));
					if(StringUtil.Null2Blank(hv.getCbsjf1()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbsjf1()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbsjf1()).equalsIgnoreCase("4")){
							hv.setCbsjf1_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbsjf1()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbsjf1()).equalsIgnoreCase("5")){
							hv.setCbsjf1_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbsjf1()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbsjf1()).equalsIgnoreCase("6")){
							hv.setCbsjf1_high("3");
						}
					}
					
					hv.setCbsjf2(rs.getString("cbsjf2"));
					if(StringUtil.Null2Blank(hv.getCbsjf2()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbsjf2()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbsjf2()).equalsIgnoreCase("4")){
							hv.setCbsjf2_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbsjf2()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbsjf2()).equalsIgnoreCase("5")){
							hv.setCbsjf2_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbsjf2()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbsjf2()).equalsIgnoreCase("6")){
							hv.setCbsjf2_high("3");
						}
					}
					
					hv.setCbsjlq(rs.getString("cbsjlq"));
					if(StringUtil.Null2Blank(hv.getCbsjlq()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbsjlq()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbsjlq()).equalsIgnoreCase("4")){
							hv.setCbsjlq_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbsjlq()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbsjlq()).equalsIgnoreCase("5")){
							hv.setCbsjlq_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbsjlq()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbsjlq()).equalsIgnoreCase("6")){
							hv.setCbsjlq_high("3");
						}
					}
					
					hv.setCbsjtjj(rs.getString("cbsjtjj"));
					if(StringUtil.Null2Blank(hv.getCbsjtjj()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbsjtjj()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbsjtjj()).equalsIgnoreCase("4")){
							hv.setCbsjtjj_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbsjtjj()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbsjtjj()).equalsIgnoreCase("5")){
							hv.setCbsjtjj_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbsjtjj()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbsjtjj()).equalsIgnoreCase("6")){
							hv.setCbsjtjj_high("3");
						}
					}
					
					hv.setCbglwd(rs.getString("cbglwd"));
					if(StringUtil.Null2Blank(hv.getCbglwd()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbglwd()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbglwd()).equalsIgnoreCase("4")){
							hv.setCbglwd_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbglwd()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbglwd()).equalsIgnoreCase("5")){
							hv.setCbglwd_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbglwd()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbglwd()).equalsIgnoreCase("6")){
							hv.setCbglwd_high("3");
						}
					}
					
					hv.setCblqwd(rs.getString("cblqwd"));
					if(StringUtil.Null2Blank(hv.getCblqwd()).length()>0){
						if(StringUtil.Null2Blank(hv.getCblqwd()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCblqwd()).equalsIgnoreCase("4")){
							hv.setCblqwd_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCblqwd()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCblqwd()).equalsIgnoreCase("5")){
							hv.setCblqwd_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCblqwd()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCblqwd()).equalsIgnoreCase("6")){
							hv.setCblqwd_high("3");
						}
					}
					
					
					hv.setCbbeiy1(rs.getString("cbbeiy1"));
					if(StringUtil.Null2Blank(hv.getCbbeiy1()).length()>0){
						if(StringUtil.Null2Blank(hv.getCbbeiy1()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getCbbeiy1()).equalsIgnoreCase("4")){
							hv.setCbbeiy1_primary("1");
						}else if(StringUtil.Null2Blank(hv.getCbbeiy1()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getCbbeiy1()).equalsIgnoreCase("5")){
							hv.setCbbeiy1_middle("2");
						}else if(StringUtil.Null2Blank(hv.getCbbeiy1()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getCbbeiy1()).equalsIgnoreCase("6")){
							hv.setCbbeiy1_high("3");
						}
					}
					
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
	
	private void appendFangshuSql(StringBuffer sql) {
		sql.append("SELECT SUM(CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS changliang,COUNT(bianhao) as pangshu");
	}

	@Override
	public List<LiqingView> lqcnfxlist(String startTime, String endTime,
			String shebeibianhao, Integer biaoduan, Integer xiangmubu, int cnfxlx, String fn, int bsid) {
		List<LiqingView> _returnValue = new ArrayList<LiqingView>();
		StringBuffer sql = new StringBuffer();
		appendFangshuSql(sql);	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		String nowtime = sdf.format(day.getTime());
		String begintime = sdf.format(day.getTime());
	    switch (cnfxlx) {
		case 1://季度
			sql.append(",datename(year,shijian) as xa, datename(quarter, shijian) as xb");
			sql.append(" FROM LiqingView");
	    	day.add(Calendar.MONTH, -9);
	    	begintime = sdf.format(day.getTime());
			break;
		case 2://月份
			sql.append(",datename(year, shijian) as xa, datename(month, shijian) as xb");
			sql.append(" FROM LiqingView");
			day.add(Calendar.MONTH, -3);
	    	begintime = sdf.format(day.getTime());
			break;
		case 3://周
			sql.append(",datename(year, shijian) as xa, datename(week, shijian) as xb");
			sql.append(" FROM LiqingView");
			day.add(Calendar.WEEK_OF_YEAR, -4);
	    	begintime = sdf.format(day.getTime());
			break;
		case 4://天
			sql.append(",month(shijian) as xa, day(shijian) as xb");
			sql.append(" FROM LiqingView");
			day.add(Calendar.DATE, -6);
	    	begintime = sdf.format(day.getTime());
			break;
		case 5://小时
			sql.append(",datename(day, shijian) as xa, datename(hour, shijian) as xb");
			sql.append(" FROM LiqingView");
			day.add(Calendar.HOUR, -6);
	    	begintime = sdf.format(day.getTime());
			break;
		default:
			break;
		}
		if(StringUtil.Null2Blank(startTime).length()>0) 
		{
			begintime = startTime;
		}
		if(StringUtil.Null2Blank(endTime).length()>0)
		{
			nowtime = endTime;
		}
		
		sql.append(" where (shijian between '"+begintime+"' and '"+nowtime+"') ");
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and shebeibianhao = '"+shebeibianhao+"'");
		}
		
		if (null != biaoduan) {
			sql.append(" and biaoduanid=" + biaoduan);
		}
		if (null != xiangmubu) {
			sql.append(" and xiangmubuid=" + xiangmubu);
		}
		

		
		switch (cnfxlx) {
		case 1:
			sql.append(" group by datename(year, shijian), datename(quarter, shijian) order by datename(quarter, shijian)");
			break;
		case 2:
			sql.append(" group by datename(year, shijian), datename(month, shijian) order by datename(month, shijian)");
			break;
		case 3:
			sql.append(" group by datename(year, shijian), datename(week, shijian) order by datename(week, shijian)");
			break;
		case 4://天
			sql.append(" group by datename(year, shijian), month(shijian), ");
			sql.append(" day(shijian) order by month(shijian),day(shijian)");			
			break;
		case 5://小时
			sql.append(" group by datename(year, shijian), datename(month, shijian), ");
			sql.append(" datename(day, shijian), datename(hour, shijian) order by");
			sql.append(" datename(year, shijian), datename(month, shijian),datename(day, shijian), datename(hour, shijian)");			
			break;
		default:
			break;
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
					LiqingView hv = new LiqingView();
					hv.setXa(rs.getString("xa"));
					hv.setXb(rs.getString("xb"));
					hv.setPangshu(rs.getString("pangshu"));
					getChaobiaops(hv,startTime, endTime, shebeibianhao,
							biaoduan, xiangmubu, cnfxlx, fn, bsid, hv.getXa(), hv.getXb());
					try {
						if (Double.parseDouble(hv.getPangshu())>0) {
						 hv.setAmend(String.format("%1$.2f",
								 Double.parseDouble(hv.getAmbegin())*100/Double.parseDouble(hv.getPangshu())));
						 hv.setPmend(String.format("%1$.2f",
								 Double.parseDouble(hv.getPmbegin())*100/Double.parseDouble(hv.getPangshu())));
						 hv.setBiaoshi(String.format("%1$.2f",
								 Double.parseDouble(hv.getBeizhu())*100/Double.parseDouble(hv.getPangshu())));
						}
					} catch (Exception e) {
					}
					hv.setChangliang(String.format("%1$.1f",rs.getDouble("changliang")));
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
		
		return _returnValue;
	}
	
	private void appendShijizhiSql(StringBuffer sql) {
		sql.append("SELECT SUM(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS glsj1,");
		sql.append("SUM(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS glsj2,");
		sql.append("SUM(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS glsj3,");
		sql.append("SUM(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS glsj4,");		
		sql.append("SUM(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS glsj5,");
		sql.append("SUM(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS glsj6,");
		sql.append("SUM(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS glsj7,");
		sql.append("SUM(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS flsj1,");		
		sql.append("SUM(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS flsj2,");
		sql.append("SUM(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS lqsj");	
	}
	
	@Override
	public LiqingView lqcnfxdetail(String startTime, String endTime,
			String shebeibianhao, String biaod, String xiangmb, String myvar1, String myvar2, int cnfxlx, String fn, int bsid) {
		LiqingView lqv = null;
		StringBuffer sql = new StringBuffer();
		appendShijizhiSql(sql);	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		String nowtime = sdf.format(day.getTime());
		String begintime = sdf.format(day.getTime());
	    switch (cnfxlx) {
		case 1://季度
			sql.append(",datename(year, baocunshijian) as xa, datename(quarter, baocunshijian) as xb");
			sql.append(" FROM LiqingView");
	    	day.add(Calendar.MONTH, -9);
	    	begintime = sdf.format(day.getTime());
			break;
		case 2://月份
			sql.append(",datename(year, baocunshijian) as xa, datename(month, baocunshijian) as xb");
			sql.append(" FROM LiqingView");
	    	day.add(Calendar.MONTH, -3);
	    	begintime = sdf.format(day.getTime());
			break;
		case 3://周
			sql.append(",datename(year, baocunshijian) as xa, datename(week, baocunshijian) as xb");
			sql.append(" FROM LiqingView");
			day.add(Calendar.WEEK_OF_YEAR, -4);
	    	begintime = sdf.format(day.getTime());
			break;
		case 4://天
			sql.append(",datename(month, baocunshijian) as xa, datename(dd, baocunshijian) as xb");
			sql.append(" FROM LiqingView");
			day.add(Calendar.DATE, -6);
	    	begintime = sdf.format(day.getTime());
			break;
		case 5://小时
			sql.append(",datename(dd, baocunshijian) as xa, datename(hh, baocunshijian) as xb");
			sql.append(" FROM LiqingView");
			day.add(Calendar.HOUR, -6);
	    	begintime = sdf.format(day.getTime());
			break;
		default:
			break;
		}
		if(StringUtil.Null2Blank(startTime).length()>0) 
		{
			begintime = startTime;
		}
		if(StringUtil.Null2Blank(endTime).length()>0)
		{
			nowtime = endTime;
		}
		
		sql.append(" where (baocunshijian between '"+begintime+"' and '"+nowtime+"') ");
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and shebeibianhao = '"+shebeibianhao+"'");
		}
		
		if (StringUtil.Null2Blank(biaod).length()>0) {			
			sql.append(" and biaoduanid ="+biaod);
		}
		
		if (StringUtil.Null2Blank(xiangmb).length()>0) {			
			sql.append(" and xiangmubuid ="+xiangmb);
		}	
		
		
		switch (cnfxlx) {
		case 1:
			sql.append(" group by datename(year, baocunshijian), datename(quarter, baocunshijian)");
			sql.append(" having datename(year, baocunshijian)=");
			sql.append(myvar1);
			sql.append(" and datename(quarter, baocunshijian)=");
			sql.append(myvar2);
			sql.append(" order by datename(quarter, baocunshijian)");
			break;
		case 2:
			sql.append(" group by datename(year, baocunshijian), datename(month, baocunshijian)");
			sql.append(" having datename(year, baocunshijian)=");
			sql.append(myvar1);
			sql.append(" and datename(month, baocunshijian)=");
			sql.append(myvar2);
			sql.append(" order by datename(month, baocunshijian)");
			break;
		case 3:
			sql.append(" group by datename(year, baocunshijian), datename(week, baocunshijian)");
			sql.append(" having datename(year, baocunshijian)=");
			sql.append(myvar1);
			sql.append(" and datename(week, baocunshijian)=");
			sql.append(myvar2);
			sql.append(" order by datename(week, baocunshijian)");
			break;
		case 4://天
			sql.append(" group by datename(year, baocunshijian), datename(month, baocunshijian),datename(dd, baocunshijian)");
			sql.append(" having datename(month, baocunshijian)=");
			sql.append(myvar1);
			sql.append(" and datename(dd, baocunshijian)=");
			sql.append(myvar2);
			sql.append(" order by datename(month, baocunshijian),datename(dd, baocunshijian)");			
			break;
		case 5://小时
			sql.append(" group by datename(year, baocunshijian), datename(month, baocunshijian), ");
			sql.append(" datename(dd, baocunshijian), datename(hh, baocunshijian)");
			sql.append(" having datename(dd, baocunshijian)=");
			sql.append(myvar1);
			sql.append(" and datename(hh, baocunshijian)=");
			sql.append(myvar2);
			sql.append(" order by datename(dd, baocunshijian), datename(hh, baocunshijian)");			
			break;
		default:
			break;
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
				if (rs.next()) {
					lqv = new LiqingView();
					lqv.setXa(rs.getString("xa"));
					lqv.setXb(rs.getString("xb"));
					lqv.setSjg1(String.format("%1$.1f",rs.getDouble("glsj1")));
					lqv.setSjg2(String.format("%1$.1f",rs.getDouble("glsj2")));
					lqv.setSjg3(String.format("%1$.1f",rs.getDouble("glsj3")));
					lqv.setSjg4(String.format("%1$.1f",rs.getDouble("glsj4")));
					lqv.setSjg5(String.format("%1$.1f",rs.getDouble("glsj5")));
					lqv.setSjg6(String.format("%1$.1f",rs.getDouble("glsj6")));
					lqv.setSjg7(String.format("%1$.1f",rs.getDouble("glsj7")));
					lqv.setSjf1(String.format("%1$.2f",rs.getDouble("flsj1")));				
					lqv.setSjf2(String.format("%1$.2f",rs.getDouble("flsj2")));
					lqv.setSjlq(String.format("%1$.2f",rs.getDouble("lqsj")));										
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
		
		return lqv;
	}
	
	private void appendlilunSql(StringBuffer sql) {
		sql.append("SELECT * FROM Liqingxixxlilun");
	}
	
	@Override
	public LiqingView lqcnfxlilundetail(String startTime, String endTime,
			String shebeibianhao, String biaod, String xiangmb, String myvar1, String myvar2, int cnfxlx, String fn, int bsid) {
		LiqingView lqv = null;
		StringBuffer sql = new StringBuffer();
		appendlilunSql(sql);
	    sql.append(" where llmoren='1' ");
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and llshebeibianhao = '"+shebeibianhao+"'");
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
				if (rs.next()) {
					lqv = new LiqingView();
					lqv.setLlg1(rs.getString("llg1"));
					lqv.setLlg2(rs.getString("llg2"));
					lqv.setLlg3(rs.getString("llg3"));
					lqv.setLlg4(rs.getString("llg4"));
					lqv.setLlg5(rs.getString("llg5"));
					lqv.setLlf1(rs.getString("llf1"));				
					lqv.setLlf2(rs.getString("llf2"));
					lqv.setLllq(rs.getString("lllq"));					
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
		
		return lqv;
	}
	
	@Override
	public GenericPageMode lqviewjieguolist(String shebeibianhao,
			 String startTimeOne, String endTimeOne,
			 Integer biaoduan, Integer xiangmubu,
			String fn, int bsid, int offset, int pagesize) {
		List<LiqingxixxjieguoView> _returnValue = new ArrayList<LiqingxixxjieguoView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LiqingxixxjieguoView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}

		String queryCondition =  " (convert(datetime,baocunshijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and gprsbianhao='" + shebeibianhao + "'";
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
			cs.setString(3, "jieguoid");
			cs.setString(4, "jieguoid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingxixxjieguoView hv = new LiqingxixxjieguoView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setJbsj(rs.getString("jbsj"));
					hv.setJieguoid(rs.getInt("jieguoid"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));					
					hv.setShijian(rs.getString("shijian"));					
					hv.setSjysb(rs.getString("sjysb"));
					hv.setGlwd(rs.getString("glwd"));
					hv.setLqwd(rs.getString("lqwd"));
					hv.setSjg1(rs.getString("sjg1"));
					hv.setSjg2(rs.getString("sjg2"));
					hv.setSjg3(rs.getString("sjg3"));
					hv.setSjg4(rs.getString("sjg4"));
					hv.setSjg5(rs.getString("sjg5"));
					hv.setSjg6(rs.getString("sjg6"));
					hv.setSjg7(rs.getString("sjg7"));
					hv.setSjf1(rs.getString("sjf1"));
					hv.setSjf2(rs.getString("sjf2"));
					hv.setSjtjj(rs.getString("sjtjj"));
					hv.setBeiy1(rs.getString("beiy1"));
					hv.setBeiy2(rs.getString("beiy2"));
					hv.setBeiy3(rs.getString("beiy3"));
					hv.setJieguoshijian(rs.getString("jieguoshijian"));
					hv.setChulijieguo(rs.getString("chulijieguo"));
					
					hv.setGprsbianhao(rs.getString("gprsbianhao"));
					hv.setLqbianhao(rs.getInt("lqbianhao"));
					hv.setLeiji(rs.getInt("leiji"));								
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
	public GenericPageMode lqviewwuchalist(String shebeibianhao,
			 String startTimeOne, String endTimeOne,
			 Integer biaoduan, Integer xiangmubu,
			String fn, int bsid, int offset, int pagesize,String peifan) {
		List<LiqingphbView> _returnValue = new ArrayList<LiqingphbView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LiqingphbView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}

		String queryCondition =  " llysb is not null  and llysb <> '' and (convert(datetime,shijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and shebeibianhao='" + shebeibianhao + "'";
		}
		if (StringUtil.Null2Blank(peifan).length() > 0) {
			queryCondition += " and peifan='" + peifan + "'";
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
			cs.setString(3, "bianhao");
			cs.setString(4, "bianhao DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingphbView hv = new LiqingphbView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setJbsj(rs.getString("jbsj"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));					
					hv.setShijian(rs.getString("shijian"));
					try{
						hv.setSjysb(String.format("%1$.2f",rs.getFloat("sjysb")));
					}catch(Exception ex){}
					try{
						hv.setGlwd(String.format("%1$.1f",rs.getFloat("glwd")));
					}catch(Exception ex){}
					try{
						hv.setLqwd(String.format("%1$.1f",rs.getFloat("lqwd")));
					}catch(Exception ex){}
					try{
						hv.setSjg1(String.format("%1$.1f",rs.getFloat("sjg1")));
					}catch(Exception ex){}
					try{
						hv.setSjg2(String.format("%1$.1f",rs.getFloat("sjg2")));
					}catch(Exception ex){}
					try{
						hv.setSjg3(String.format("%1$.1f",rs.getFloat("sjg3")));
					}catch(Exception ex){}
					try{
						hv.setSjg4(String.format("%1$.1f",rs.getFloat("sjg4")));
					}catch(Exception ex){}
					try{
						hv.setSjg5(String.format("%1$.1f",rs.getFloat("sjg5")));
					}catch(Exception ex){}
					try{
						hv.setSjg6(String.format("%1$.1f",rs.getFloat("sjg6")));
					}catch(Exception ex){}
					try{
						hv.setSjg7(String.format("%1$.1f",rs.getFloat("sjg7")));
					}catch(Exception ex){}
					try{
						hv.setSjf1(String.format("%1$.1f",rs.getFloat("sjf1")));
					}catch(Exception ex){}
					try{
						hv.setSjf2(String.format("%1$.1f",rs.getFloat("sjf2")));
					}catch(Exception ex){}
					try{
						hv.setSjtjj(String.format("%1$.1f",rs.getFloat("sjtjj")));
					}catch(Exception ex){}
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					try{
						hv.setWsjysb(String.format("%1$.2f",rs.getFloat("wsjysb")));
					}catch(Exception ex){}
					try{
						hv.setWsjg1(String.format("%1$.1f",rs.getFloat("wsjg1")));		
					}catch(Exception ex){}
					try{
						hv.setWsjg2(String.format("%1$.1f",rs.getFloat("wsjg2")));		
					}catch(Exception ex){}
					try{
						hv.setWsjg3(String.format("%1$.1f",rs.getFloat("wsjg3")));		
					}catch(Exception ex){}
					try{
						hv.setWsjg4(String.format("%1$.1f",rs.getFloat("wsjg4")));	
					}catch(Exception ex){}
					try{
						hv.setWsjg5(String.format("%1$.1f",rs.getFloat("wsjg5")));	
					}catch(Exception ex){}
					try{
						hv.setWsjg6(String.format("%1$.1f",rs.getFloat("wsjg6")));	
					}catch(Exception ex){}
					try{
						hv.setWsjg7(String.format("%1$.1f",rs.getFloat("wsjg7")));	
					}catch(Exception ex){}
					try{
						hv.setWsjf1(String.format("%1$.1f",rs.getFloat("wsjf1")));	
					}catch(Exception ex){}
					try{
						hv.setWsjf2(String.format("%1$.1f",rs.getFloat("wsjf2")));	
					}catch(Exception ex){}
					try{
						hv.setWsjlq(String.format("%1$.1f",rs.getFloat("wsjlq")));		
					}catch(Exception ex){}
					try{
						hv.setWsjtjj(String.format("%1$.1f",rs.getFloat("wsjtjj")));	
					}catch(Exception ex){}
					try{
						hv.setPersjg1(String.format("%1$.1f",rs.getFloat("persjg1")));
					}catch(Exception ex){}
					try{
						hv.setPersjg2(String.format("%1$.1f",rs.getFloat("persjg2")));
					}catch(Exception ex){}
					try{
						hv.setPersjg3(String.format("%1$.1f",rs.getFloat("persjg3")));
					}catch(Exception ex){}
					try{
						hv.setPersjg4(String.format("%1$.1f",rs.getFloat("persjg4")));
					}catch(Exception ex){}
					try{
						hv.setPersjg5(String.format("%1$.1f",rs.getFloat("persjg5")));
					}catch(Exception ex){}
					try{
						hv.setPersjg6(String.format("%1$.1f",rs.getFloat("persjg6")));
					}catch(Exception ex){}
					try{
						hv.setPersjg7(String.format("%1$.1f",rs.getFloat("persjg7")));
					}catch(Exception ex){}
					try{
						hv.setPersjf1(String.format("%1$.1f",rs.getFloat("persjf1")));
					}catch(Exception ex){}
					try{
						hv.setPersjf2(String.format("%1$.1f",rs.getFloat("persjf2")));
					}catch(Exception ex){}
					try{
						hv.setPersjlq(String.format("%1$.1f",rs.getFloat("persjlq")));
					}catch(Exception ex){}
					try{
						hv.setPersjtjj(String.format("%1$.1f",rs.getFloat("persjtjj")));	
					}catch(Exception ex){}
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
		
		
		cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		tablename = "LiqingmanualphbView";	

		queryCondition =  " llysb is not null  and llysb <> '' and (convert(datetime,baocunshijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and shebeibianhao='" + shebeibianhao + "'";
		}
		
		if (null != biaoduan) {
			queryCondition += " and biaoduanid=" + biaoduan;
		}
		if (null != xiangmubu) {
			queryCondition += " and xiangmubuid=" + xiangmubu;
		}
		rs = null;
		cs = null;
		con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "bianhao");
			cs.setString(4, "bianhao DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingphbView hv = new LiqingphbView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setJbsj(rs.getString("jbsj"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));					
					hv.setShijian(rs.getString("shijian"));					
//					hv.setSjysb(rs.getString("sjysb"));
//					hv.setGlwd(rs.getString("glwd"));
//					hv.setLqwd(rs.getString("lqwd"));
//					hv.setSjg1(rs.getString("sjg1"));
//					hv.setSjg2(rs.getString("sjg2"));
//					hv.setSjg3(rs.getString("sjg3"));
//					hv.setSjg4(rs.getString("sjg4"));
//					hv.setSjg5(rs.getString("sjg5"));
//					hv.setSjg6(rs.getString("sjg6"));
//					hv.setSjg7(rs.getString("sjg7"));
//					hv.setSjf1(rs.getString("sjf1"));
//					hv.setSjf2(rs.getString("sjf2"));
//					hv.setSjtjj(rs.getString("sjtjj"));
//					hv.setShebeibianhao(rs.getString("shebeibianhao"));
//					hv.setWsjysb(rs.getString("wsjysb"));
//					hv.setWsjg1(rs.getString("wsjg1"));							
//					hv.setWsjg2(rs.getString("wsjg2"));							
//					hv.setWsjg3(rs.getString("wsjg3"));							
//					hv.setWsjg4(rs.getString("wsjg4"));							
//					hv.setWsjg5(rs.getString("wsjg5"));							
//					hv.setWsjg6(rs.getString("wsjg6"));							
//					hv.setWsjg7(rs.getString("wsjg7"));							
//					hv.setWsjf1(rs.getString("wsjf1"));							
//					hv.setWsjf2(rs.getString("wsjf2"));							
//					hv.setWsjlq(rs.getString("wsjlq"));							
//					hv.setWsjtjj(rs.getString("wsjtjj"));	
//					hv.setPersjg1(rs.getString("persjg1"));
//					hv.setPersjg2(rs.getString("persjg2"));
//					hv.setPersjg3(rs.getString("persjg3"));
//					hv.setPersjg4(rs.getString("persjg4"));
//					hv.setPersjg5(rs.getString("persjg5"));
//					hv.setPersjg6(rs.getString("persjg6"));
//					hv.setPersjg7(rs.getString("persjg7"));
//					hv.setPersjf1(rs.getString("persjf1"));
//					hv.setPersjf2(rs.getString("persjf2"));
//					hv.setPersjlq(rs.getString("persjlq"));
//					hv.setPersjtjj(rs.getString("persjtjj"));
					try{
						hv.setSjysb(String.format("%1$.2f",rs.getFloat("sjysb")));
					}catch(Exception ex){}
					try{
						hv.setGlwd(String.format("%1$.1f",rs.getFloat("glwd")));
					}catch(Exception ex){}
					try{
						hv.setLqwd(String.format("%1$.1f",rs.getFloat("lqwd")));
					}catch(Exception ex){}
					try{
						hv.setSjg1(String.format("%1$.1f",rs.getFloat("sjg1")));
					}catch(Exception ex){}
					try{
						hv.setSjg2(String.format("%1$.1f",rs.getFloat("sjg2")));
					}catch(Exception ex){}
					try{
						hv.setSjg3(String.format("%1$.1f",rs.getFloat("sjg3")));
					}catch(Exception ex){}
					try{
						hv.setSjg4(String.format("%1$.1f",rs.getFloat("sjg4")));
					}catch(Exception ex){}
					try{
						hv.setSjg5(String.format("%1$.1f",rs.getFloat("sjg5")));
					}catch(Exception ex){}
					try{
						hv.setSjg6(String.format("%1$.1f",rs.getFloat("sjg6")));
					}catch(Exception ex){}
					try{
						hv.setSjg7(String.format("%1$.1f",rs.getFloat("sjg7")));
					}catch(Exception ex){}
					try{
						hv.setSjf1(String.format("%1$.1f",rs.getFloat("sjf1")));
					}catch(Exception ex){}
					try{
						hv.setSjf2(String.format("%1$.1f",rs.getFloat("sjf2")));
					}catch(Exception ex){}
					try{
						hv.setSjtjj(String.format("%1$.1f",rs.getFloat("sjtjj")));
					}catch(Exception ex){}
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					try{
						hv.setWsjysb(String.format("%1$.2f",rs.getFloat("wsjysb")));
					}catch(Exception ex){}
					try{
						hv.setWsjg1(String.format("%1$.1f",rs.getFloat("wsjg1")));		
					}catch(Exception ex){}
					try{
						hv.setWsjg2(String.format("%1$.1f",rs.getFloat("wsjg2")));		
					}catch(Exception ex){}
					try{
						hv.setWsjg3(String.format("%1$.1f",rs.getFloat("wsjg3")));		
					}catch(Exception ex){}
					try{
						hv.setWsjg4(String.format("%1$.1f",rs.getFloat("wsjg4")));	
					}catch(Exception ex){}
					try{
						hv.setWsjg5(String.format("%1$.1f",rs.getFloat("wsjg5")));	
					}catch(Exception ex){}
					try{
						hv.setWsjg6(String.format("%1$.1f",rs.getFloat("wsjg6")));	
					}catch(Exception ex){}
					try{
						hv.setWsjg7(String.format("%1$.1f",rs.getFloat("wsjg7")));	
					}catch(Exception ex){}
					try{
						hv.setWsjf1(String.format("%1$.1f",rs.getFloat("wsjf1")));	
					}catch(Exception ex){}
					try{
						hv.setWsjf2(String.format("%1$.1f",rs.getFloat("wsjf2")));	
					}catch(Exception ex){}
					try{
						hv.setWsjlq(String.format("%1$.1f",rs.getFloat("wsjlq")));		
					}catch(Exception ex){}
					try{
						hv.setWsjtjj(String.format("%1$.1f",rs.getFloat("wsjtjj")));	
					}catch(Exception ex){}
					try{
						hv.setPersjg1(String.format("%1$.1f",rs.getFloat("persjg1")));
					}catch(Exception ex){}
					try{
						hv.setPersjg2(String.format("%1$.1f",rs.getFloat("persjg2")));
					}catch(Exception ex){}
					try{
						hv.setPersjg3(String.format("%1$.1f",rs.getFloat("persjg3")));
					}catch(Exception ex){}
					try{
						hv.setPersjg4(String.format("%1$.1f",rs.getFloat("persjg4")));
					}catch(Exception ex){}
					try{
						hv.setPersjg5(String.format("%1$.1f",rs.getFloat("persjg5")));
					}catch(Exception ex){}
					try{
						hv.setPersjg6(String.format("%1$.1f",rs.getFloat("persjg6")));
					}catch(Exception ex){}
					try{
						hv.setPersjg7(String.format("%1$.1f",rs.getFloat("persjg7")));
					}catch(Exception ex){}
					try{
						hv.setPersjf1(String.format("%1$.1f",rs.getFloat("persjf1")));
					}catch(Exception ex){}
					try{
						hv.setPersjf2(String.format("%1$.1f",rs.getFloat("persjf2")));
					}catch(Exception ex){}
					try{
						hv.setPersjlq(String.format("%1$.1f",rs.getFloat("persjlq")));
					}catch(Exception ex){}
					try{
						hv.setPersjtjj(String.format("%1$.1f",rs.getFloat("persjtjj")));	
					}catch(Exception ex){}
					_returnValue.add(hv);
				}
				pagemode.setDatas(_returnValue);
				pagemode.setRecordcount(pagemode.getRecordcount()+cs.getInt(7));
				pagemode.setPagetotal(pagemode.getPagetotal()+cs.getInt(8));
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
	
	private void appendSql(StringBuffer sql) {
		sql.append("SELECT Max(shebeibianhao) as shebeibianhao,SUM(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS sjg1,");
		sql.append("SUM(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS sjg2,");
		sql.append("SUM(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS sjg3,");
		sql.append("SUM(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS sjg4,");		
		sql.append("SUM(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS sjg5,");
		sql.append("SUM(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS sjg6,");		
		sql.append("SUM(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS sjg7,");
		sql.append("SUM(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS sjf1,");	
		sql.append("SUM(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS sjf2,");	
		sql.append("SUM(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS sjlq,");		
		sql.append("SUM(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS sjtjj,");
		//理论值
		sql.append("SUM(CAST((CASE WHEN (llg1 IS NULL) OR (llg1 = '')");
		sql.append("THEN '0' ELSE llg1 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS llg1,");
		
		sql.append("SUM(CAST((CASE WHEN (llg2 IS NULL) OR (llg2 = '')");
		sql.append("THEN '0' ELSE llg2 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS llg2,");
		
		sql.append("SUM(CAST((CASE WHEN (llg3 IS NULL) OR (llg3 = '')");
		sql.append("THEN '0' ELSE llg3 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS llg3,");
		
		sql.append("SUM(CAST((CASE WHEN (llg4 IS NULL) OR (llg4 = '')");
		sql.append("THEN '0' ELSE llg4 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS llg4,");
		
		sql.append("SUM(CAST((CASE WHEN (llg5 IS NULL) OR (llg5 = '')");
		sql.append("THEN '0' ELSE llg5 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS llg5,");
		
		sql.append("SUM(CAST((CASE WHEN (llg6 IS NULL) OR (llg6 = '')");
		sql.append("THEN '0' ELSE llg6 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS llg6,");
		
		sql.append("SUM(CAST((CASE WHEN (llg7 IS NULL) OR (llg7 = '')");
		sql.append("THEN '0' ELSE llg7 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS llg7,");
		
		sql.append("SUM(CAST((CASE WHEN (llf1 IS NULL) OR (llf1 = '')");
		sql.append("THEN '0' ELSE llf1 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS llf1,");
		
		sql.append("SUM(CAST((CASE WHEN (llf2 IS NULL) OR (llf2 = '')");
		sql.append("THEN '0' ELSE llf2 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS llf2,");
		
		sql.append("SUM(CAST((CASE WHEN (lllq IS NULL) OR (lllq = '')");
		sql.append("THEN '0' ELSE lllq END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS lllq,");
		
		sql.append("SUM(CAST((CASE WHEN (lltjj IS NULL) OR (lltjj = '')");
		sql.append("THEN '0' ELSE lltjj END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS lltjj,");
		
		sql.append("SUM(CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS changliang");
	}
	
	@Override
	public LiqingphbView lqmateriallist(String startTime, String endTime, String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu, String fn, int bsid) {
		LiqingphbView hv = null;
		StringBuffer sql = new StringBuffer();
		appendSql(sql);
		sql.append(" FROM LiqingView");
		sql.append(" where 1=1 ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());
		
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		if (StringUtil.Null2Blank(shebeibianhao).length()>0 && !shebeibianhao.equalsIgnoreCase("''")) {			
			sql.append(" and shebeibianhao in ("+shebeibianhao+")");
		}
		
		if (null != biaoduan) {			
			sql.append(" and biaoduanid ="+biaoduan);
		}
		
		if (null != xiangmubu) {			
			sql.append(" and xiangmubuid ="+xiangmubu);
		}
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (baocunshijian between '"+startTime+"' and '"+endTime+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()==0)
		{
			sql.append(" and (baocunshijian between '"+startTime+"' and '"+newDate+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()==0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (baocunshijian between '1900-01-01' and '"+endTime+"')");
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
				if (rs.next()) {
					hv = new LiqingphbView();			
					hv.setSjg1(String.format("%1$.1f",rs.getDouble("sjg1")/1000));
					hv.setSjg2(String.format("%1$.1f",rs.getDouble("sjg2")/1000));
					hv.setSjg3(String.format("%1$.1f",rs.getDouble("sjg3")/1000));
					hv.setSjg4(String.format("%1$.1f",rs.getDouble("sjg4")/1000));
					hv.setSjg5(String.format("%1$.1f",rs.getDouble("sjg5")/1000));
					hv.setSjg6(String.format("%1$.1f",rs.getDouble("sjg6")/1000));
					hv.setSjg7(String.format("%1$.1f",rs.getDouble("sjg7")/1000));
					hv.setSjf1(String.format("%1$.1f",rs.getDouble("sjf1")/1000));
					hv.setSjf2(String.format("%1$.1f",rs.getDouble("sjf2")/1000));
					hv.setSjlq(String.format("%1$.1f",rs.getDouble("sjlq")/1000));
					hv.setSjtjj(String.format("%1$.1f",rs.getDouble("sjtjj")/1000));					
					hv.setChangliang(rs.getString("changliang"));
					hv.setLlg1(String.format("%1$.1f",rs.getDouble("llg1")/1000));
					hv.setLlg2(String.format("%1$.1f",rs.getDouble("llg2")/1000));
					hv.setLlg3(String.format("%1$.1f",rs.getDouble("llg3")/1000));
					hv.setLlg4(String.format("%1$.1f",rs.getDouble("llg4")/1000));
					hv.setLlg5(String.format("%1$.1f",rs.getDouble("llg5")/1000));
					hv.setLlg6(String.format("%1$.1f",rs.getDouble("llg6")/1000));
					hv.setLlg7(String.format("%1$.1f",rs.getDouble("llg7")/1000));
					hv.setLlf1(String.format("%1$.1f",rs.getDouble("llf1")/1000));
					hv.setLlf2(String.format("%1$.1f",rs.getDouble("llf2")/1000));
					hv.setLllq(String.format("%1$.1f",rs.getDouble("lllq")/1000));
					hv.setLltjj(String.format("%1$.1f",rs.getDouble("lltjj")/1000));
					
					//偏差百分比
					if(StringUtil.Null2Blank(rs.getString("llg1")).length()>0 && rs.getDouble("llg1")>0){
						hv.setPersjg1(String.format("%1$.2f",(Double.parseDouble(hv.getSjg1())-Double.parseDouble(hv.getLlg1()))*100/Double.parseDouble(hv.getLlg1())));
					}else{
						hv.setPersjg1("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llg2")).length()>0 && rs.getDouble("llg2")>0){
						hv.setPersjg2(String.format("%1$.2f",(Double.parseDouble(hv.getSjg2())-Double.parseDouble(hv.getLlg2()))*100/Double.parseDouble(hv.getLlg2())));
					}else{
						hv.setPersjg2("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llg3")).length()>0 && rs.getDouble("llg3")>0){
						hv.setPersjg3(String.format("%1$.2f",(Double.parseDouble(hv.getSjg3())-Double.parseDouble(hv.getLlg3()))*100/Double.parseDouble(hv.getLlg3())));
					}else{
						hv.setPersjg3("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llg4")).length()>0 && rs.getDouble("llg4")>0){
						hv.setPersjg4(String.format("%1$.2f",(Double.parseDouble(hv.getSjg4())-Double.parseDouble(hv.getLlg4()))*100/Double.parseDouble(hv.getLlg4())));
					}else{
						hv.setPersjg4("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llg5")).length()>0 && rs.getDouble("llg5")>0){
						hv.setPersjg5(String.format("%1$.2f",(Double.parseDouble(hv.getSjg5())-Double.parseDouble(hv.getLlg5()))*100/Double.parseDouble(hv.getLlg5())));
					}else{
						hv.setPersjg5("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llg6")).length()>0 && rs.getDouble("llg6")>0){
						hv.setPersjg6(String.format("%1$.2f",(Double.parseDouble(hv.getSjg6())-Double.parseDouble(hv.getLlg6()))*100/Double.parseDouble(hv.getLlg6())));
					}else{
						hv.setPersjg6("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llg7")).length()>0 && rs.getDouble("llg7")>0){
						hv.setPersjg7(String.format("%1$.2f",(Double.parseDouble(hv.getSjg7())-Double.parseDouble(hv.getLlg7()))*100/Double.parseDouble(hv.getLlg7())));
					}else{
						hv.setPersjg7("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llf1")).length()>0 && rs.getDouble("llf1")>0){
						hv.setPersjf1(String.format("%1$.2f",(Double.parseDouble(hv.getSjf1())-Double.parseDouble(hv.getLlf1()))*100/Double.parseDouble(hv.getLlf1())));
					}else{
						hv.setPersjf1("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llf2")).length()>0 && rs.getDouble("llf2")>0){
						hv.setPersjf2(String.format("%1$.2f",(Double.parseDouble(hv.getSjf2())-Double.parseDouble(hv.getLlf2()))*100/Double.parseDouble(hv.getLlf2())));
					}else{
						hv.setPersjf2("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("lllq")).length()>0 && rs.getDouble("lllq")>0){
						hv.setPersjlq(String.format("%1$.2f",(Double.parseDouble(hv.getSjlq())-Double.parseDouble(hv.getLllq()))*100/Double.parseDouble(hv.getLllq())));
					}else{
						hv.setPersjlq("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("lltjj")).length()>0 && rs.getDouble("lltjj")>0){
						hv.setPersjtjj(String.format("%1$.2f",(Double.parseDouble(hv.getSjtjj())-Double.parseDouble(hv.getLltjj()))*100/Double.parseDouble(hv.getLltjj())));
					}else{
						hv.setPersjtjj("0.00");
					}
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
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
		
		return hv;
		
	}
	
	public LiqingphbView appLqmateriallist(String startTime, String endTime, String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu, String fn, int bsid) {
		LiqingphbView hv = null;
		StringBuffer sql = new StringBuffer();
		appendSql(sql);
		sql.append(" FROM LiqingView");
		sql.append(" where 1=1 ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());
		
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and shebeibianhao = '"+shebeibianhao+"' ");
		}	
		
		if (null != biaoduan) {			
			sql.append(" and biaoduanid ="+biaoduan);
		}
		
		if (null != xiangmubu) {			
			sql.append(" and xiangmubuid ="+xiangmubu);
		}
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (baocunshijian between '"+startTime+"' and '"+endTime+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()==0)
		{
			sql.append(" and (baocunshijian between '"+startTime+"' and '"+newDate+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()==0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (baocunshijian between '1900-01-01' and '"+endTime+"')");
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
				if (rs.next()) {
					hv = new LiqingphbView();			
					hv.setSjg1(String.format("%1$.1f",rs.getDouble("sjg1")/1000));
					hv.setSjg2(String.format("%1$.1f",rs.getDouble("sjg2")/1000));
					hv.setSjg3(String.format("%1$.1f",rs.getDouble("sjg3")/1000));
					hv.setSjg4(String.format("%1$.1f",rs.getDouble("sjg4")/1000));
					hv.setSjg5(String.format("%1$.1f",rs.getDouble("sjg5")/1000));
					hv.setSjg6(String.format("%1$.1f",rs.getDouble("sjg6")/1000));
					hv.setSjg7(String.format("%1$.1f",rs.getDouble("sjg7")/1000));
					hv.setSjf1(String.format("%1$.1f",rs.getDouble("sjf1")/1000));
					hv.setSjf2(String.format("%1$.1f",rs.getDouble("sjf2")/1000));
					hv.setSjlq(String.format("%1$.1f",rs.getDouble("sjlq")/1000));
					hv.setSjtjj(String.format("%1$.1f",rs.getDouble("sjtjj")/1000));					
					hv.setChangliang(rs.getString("changliang"));
					hv.setLlg1(String.format("%1$.1f",rs.getDouble("llg1")/1000));
					hv.setLlg2(String.format("%1$.1f",rs.getDouble("llg2")/1000));
					hv.setLlg3(String.format("%1$.1f",rs.getDouble("llg3")/1000));
					hv.setLlg4(String.format("%1$.1f",rs.getDouble("llg4")/1000));
					hv.setLlg5(String.format("%1$.1f",rs.getDouble("llg5")/1000));
					hv.setLlg6(String.format("%1$.1f",rs.getDouble("llg6")/1000));
					hv.setLlg7(String.format("%1$.1f",rs.getDouble("llg7")/1000));
					hv.setLlf1(String.format("%1$.1f",rs.getDouble("llf1")/1000));
					hv.setLlf2(String.format("%1$.1f",rs.getDouble("llf2")/1000));
					hv.setLllq(String.format("%1$.1f",rs.getDouble("lllq")/1000));
					hv.setLltjj(String.format("%1$.1f",rs.getDouble("lltjj")/1000));
					
					//偏差百分比
					if(StringUtil.Null2Blank(rs.getString("llg1")).length()>0 && rs.getDouble("llg1")>0){
						hv.setPersjg1(String.format("%1$.2f",(Double.parseDouble(hv.getSjg1())-Double.parseDouble(hv.getLlg1()))*100/Double.parseDouble(hv.getLlg1())));
					}else{
						hv.setPersjg1("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llg2")).length()>0 && rs.getDouble("llg2")>0){
						hv.setPersjg2(String.format("%1$.2f",(Double.parseDouble(hv.getSjg2())-Double.parseDouble(hv.getLlg2()))*100/Double.parseDouble(hv.getLlg2())));
					}else{
						hv.setPersjg2("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llg3")).length()>0 && rs.getDouble("llg3")>0){
						hv.setPersjg3(String.format("%1$.2f",(Double.parseDouble(hv.getSjg3())-Double.parseDouble(hv.getLlg3()))*100/Double.parseDouble(hv.getLlg3())));
					}else{
						hv.setPersjg3("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llg4")).length()>0 && rs.getDouble("llg4")>0){
						hv.setPersjg4(String.format("%1$.2f",(Double.parseDouble(hv.getSjg4())-Double.parseDouble(hv.getLlg4()))*100/Double.parseDouble(hv.getLlg4())));
					}else{
						hv.setPersjg4("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llg5")).length()>0 && rs.getDouble("llg5")>0){
						hv.setPersjg5(String.format("%1$.2f",(Double.parseDouble(hv.getSjg5())-Double.parseDouble(hv.getLlg5()))*100/Double.parseDouble(hv.getLlg5())));
					}else{
						hv.setPersjg5("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llg6")).length()>0 && rs.getDouble("llg6")>0){
						hv.setPersjg6(String.format("%1$.2f",(Double.parseDouble(hv.getSjg6())-Double.parseDouble(hv.getLlg6()))*100/Double.parseDouble(hv.getLlg6())));
					}else{
						hv.setPersjg6("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llg7")).length()>0 && rs.getDouble("llg7")>0){
						hv.setPersjg7(String.format("%1$.2f",(Double.parseDouble(hv.getSjg7())-Double.parseDouble(hv.getLlg7()))*100/Double.parseDouble(hv.getLlg7())));
					}else{
						hv.setPersjg7("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llf1")).length()>0 && rs.getDouble("llf1")>0){
						hv.setPersjf1(String.format("%1$.2f",(Double.parseDouble(hv.getSjf1())-Double.parseDouble(hv.getLlf1()))*100/Double.parseDouble(hv.getLlf1())));
					}else{
						hv.setPersjf1("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("llf2")).length()>0 && rs.getDouble("llf2")>0){
						hv.setPersjf2(String.format("%1$.2f",(Double.parseDouble(hv.getSjf2())-Double.parseDouble(hv.getLlf2()))*100/Double.parseDouble(hv.getLlf2())));
					}else{
						hv.setPersjf2("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("lllq")).length()>0 && rs.getDouble("lllq")>0){
						hv.setPersjlq(String.format("%1$.2f",(Double.parseDouble(hv.getSjlq())-Double.parseDouble(hv.getLllq()))*100/Double.parseDouble(hv.getLllq())));
					}else{
						hv.setPersjlq("0.00");
					}
					if(StringUtil.Null2Blank(rs.getString("lltjj")).length()>0 && rs.getDouble("lltjj")>0){
						hv.setPersjtjj(String.format("%1$.2f",(Double.parseDouble(hv.getSjtjj())-Double.parseDouble(hv.getLltjj()))*100/Double.parseDouble(hv.getLltjj())));
					}else{
						hv.setPersjtjj("0.00");
					}
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
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
		
		return hv;
		
	}
	
	
	private void appenShuliangSql(StringBuffer sql) {		
		sql.append("select banhezhanminchen,Max(chuliaoshijian) as chuliaoshijian,");
		sql.append("SUM(CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS changliang ");
		sql.append("FROM LiqingView");
	}
	
	@Override
	public List<LiqingView> lqsclhslist(String startTime, String endTime,
			String shebeibianhao, Integer biaoduan, Integer xiangmubu, String fn, int bsid,String peifan) {
		List<LiqingView> _returnValue = new ArrayList<LiqingView>();
		StringBuffer sql = new StringBuffer();
		appenShuliangSql(sql);	
		sql.append(" where 1=1 ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());
		
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append("and shebeibianhao = '"+shebeibianhao+"'");
		}
		if (StringUtil.Null2Blank(peifan).length()>0) {			
			sql.append("and peifan = '"+peifan+"'");
		}
	
		if (null != biaoduan) {			
			sql.append(" and biaoduanid ="+biaoduan);
		}
		
		if (null != xiangmubu) {			
			sql.append(" and xiangmubuid ="+xiangmubu);
		}
		
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (baocunshijian between '"+startTime+"' and '"+endTime+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()==0)
		{
			sql.append(" and (baocunshijian between '"+startTime+"' and '"+newDate+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()==0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (baocunshijian between '1900-01-01' and '"+endTime+"')");
		}
		
		sql.append(" group by banhezhanminchen");
		
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
					LiqingView hv = new LiqingView();		
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setChangliang(String.format("%1$.2f",rs.getDouble("changliang")));
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
		
		return _returnValue;
	}
	
	@Override
	public GenericPageMode viewrunstatelist(String shebeibianhao,
			String startTimeOne, String endTimeOne,
			Integer biaoduan, Integer xiangmubu,
			String fn, int bsid, int offset, int pagesize) {
		List<RunstateView> _returnValue = new ArrayList<RunstateView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "RunstateView";
		String queryCondition = " 1=1";
		if (StringUtil.Null2Blank(startTimeOne).length() > 0 && StringUtil.Null2Blank(endTimeOne).length() > 0) {
			queryCondition +=  " and (convert(datetime,statextsj,121) between '" + startTimeOne
			+ "' and '" + endTimeOne + "')";
		} else if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			queryCondition +=  " and (convert(datetime,statextsj,121) < '" + endTimeOne + "')";
		} else if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			queryCondition +=  " and (convert(datetime,statextsj,121) > '" + startTimeOne + "')";
		}
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and statesbbh='" + shebeibianhao + "'";
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
			cs.setString(3, "statesbbh");
			cs.setString(4, "statesbbh ASC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					RunstateView hv = new RunstateView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setStatesbbh(rs.getString("statesbbh"));
					hv.setStatebcsj(rs.getString("statebcsj"));
					hv.setStatecjsj(rs.getString("statecjsj"));
					hv.setStateclsj(rs.getString("stateclsj"));
					hv.setStatextsj(rs.getString("statextsj"));
					hv.setStatexxbh(rs.getString("statexxbh"));		
					_returnValue.add(hv);
				}
				pagemode.setDatas(_returnValue);
				pagemode.setRecordcount(cs.getInt(7));
				pagemode.setPagetotal(cs.getInt(8));
			}
		} catch (SQLException e) {
		  e.printStackTrace();
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
	
	private void appenLqshuliangsqlphb(StringBuffer sql) {		
		sql.append("select banhezhanminchen,llysb,");
		sql.append("SUM(CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS changliang,");
		sql.append("SUM(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS sjg1,");
		sql.append("SUM(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS sjg2,");
		sql.append("SUM(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS sjg3,");
		sql.append("SUM(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS sjg4,");
		sql.append("SUM(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS sjg5,");
		sql.append("SUM(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS sjg6,");
		sql.append("SUM(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS sjg7,");
		sql.append("SUM(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS sjf1,");
		sql.append("SUM(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS sjf2,");
		sql.append("SUM(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS sjtjj,");
		sql.append("SUM(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS sjlq,");
		sql.append("MAX(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS maxsjg1,");
		sql.append("MAX(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS maxsjg2,");
		sql.append("MAX(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS maxsjg3,");
		sql.append("MAX(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS maxsjg4,");
		sql.append("MAX(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS maxsjg5,");
		sql.append("MAX(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS maxsjg6,");
		sql.append("MAX(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS maxsjg7,");
		sql.append("MAX(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS maxsjf1,");
		sql.append("MAX(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS maxsjf2,");
		sql.append("MAX(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS maxsjtjj,");
		sql.append("MAX(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS maxsjlq,");
		sql.append("MIN(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS minsjg1,");
		sql.append("MIN(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS minsjg2,");
		sql.append("MIN(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS minsjg3,");
		sql.append("MIN(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS minsjg4,");
		sql.append("MIN(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS minsjg5,");
		sql.append("MIN(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS minsjg6,");
		sql.append("MIN(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS minsjg7,");
		sql.append("MIN(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS minsjf1,");
		sql.append("MIN(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS minsjf2,");
		sql.append("MIN(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS minsjtjj,");
		sql.append("MIN(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS minsjlq,");
		sql.append("MAX(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS jichasjg1,");
		sql.append("MAX(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS jichasjg2,");
		sql.append("MAX(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS jichasjg3,");
		sql.append("MAX(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS jichasjg4,");
		sql.append("MAX(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS jichasjg5,");
		sql.append("MAX(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS jichasjg6,");
		sql.append("MAX(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS jichasjg7,");
		sql.append("MAX(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS jichasjf1,");
		sql.append("MAX(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS jichasjf2,");
		sql.append("MAX(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS jichasjtjj,");
		sql.append("MAX(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS jichasjlq,");
		sql.append("AVG(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS avgsjg1,");
		sql.append("AVG(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS avgsjg2,");
		sql.append("AVG(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS avgsjg3,");
		sql.append("AVG(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS avgsjg4,");
		sql.append("AVG(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS avgsjg5,");
		sql.append("AVG(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS avgsjg6,");
		sql.append("AVG(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS avgsjg7,");
		sql.append("AVG(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS avgsjf1,");
		sql.append("AVG(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS avgsjf2,");
		sql.append("AVG(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS avgsjtjj,");
		sql.append("AVG(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS avgsjlq,");
		sql.append("STDEV(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS stdevsjg1,");
		sql.append("STDEV(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS stdevsjg2,");
		sql.append("STDEV(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS stdevsjg3,");
		sql.append("STDEV(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS stdevsjg4,");
		sql.append("STDEV(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS stdevsjg5,");
		sql.append("STDEV(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS stdevsjg6,");
		sql.append("STDEV(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS stdevsjg7,");
		sql.append("STDEV(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS stdevsjf1,");
		sql.append("STDEV(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS stdevsjf2,");
		sql.append("STDEV(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS stdevsjtjj,");
		sql.append("STDEV(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS stdevsjlq,");
		sql.append("STDEV(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '')"); 
		sql.append("THEN '1' ELSE sjg1 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '')");
		sql.append("THEN '1' ELSE sjg1 END) AS numeric(38, 2)))) END) AS bianyisjg1,");
		sql.append("STDEV(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '')"); 
		sql.append("THEN '1' ELSE sjg2 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '')");
		sql.append("THEN '1' ELSE sjg2 END) AS numeric(38, 2)))) END) AS bianyisjg2,");
		sql.append("STDEV(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '')"); 
		sql.append("THEN '1' ELSE sjg3 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '')");
		sql.append("THEN '1' ELSE sjg3 END) AS numeric(38, 2)))) END) AS bianyisjg3,");
		sql.append("STDEV(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '')"); 
		sql.append("THEN '1' ELSE sjg4 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '')");
		sql.append("THEN '1' ELSE sjg4 END) AS numeric(38, 2)))) END) AS bianyisjg4,");
		sql.append("STDEV(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '')"); 
		sql.append("THEN '1' ELSE sjg5 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '')");
		sql.append("THEN '1' ELSE sjg5 END) AS numeric(38, 2)))) END) AS bianyisjg5,");
		sql.append("STDEV(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '')"); 
		sql.append("THEN '1' ELSE sjg6 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '')");
		sql.append("THEN '1' ELSE sjg6 END) AS numeric(38, 2)))) END) AS bianyisjg6,");
		sql.append("STDEV(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '')"); 
		sql.append("THEN '1' ELSE sjg7 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '')");
		sql.append("THEN '1' ELSE sjg7 END) AS numeric(38, 2)))) END) AS bianyisjg7,"); 
		sql.append("STDEV(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '')"); 
		sql.append("THEN '1' ELSE sjf1 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '')");
		sql.append("THEN '1' ELSE sjf1 END) AS numeric(38, 2)))) END) AS bianyisjf1,"); 
		sql.append("STDEV(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '')"); 
		sql.append("THEN '1' ELSE sjf2 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '')");
		sql.append("THEN '1' ELSE sjf2 END) AS numeric(38, 2)))) END) AS bianyisjf2,"); 
		sql.append("STDEV(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '')"); 
		sql.append("THEN '1' ELSE sjtjj END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '')");
		sql.append("THEN '1' ELSE sjtjj END) AS numeric(38, 2)))) END) AS bianyisjtjj,"); 
		sql.append("STDEV(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '')"); 
		sql.append("THEN '1' ELSE sjlq END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '')");
		sql.append("THEN '1' ELSE sjlq END) AS numeric(38, 2)))) END) AS bianyisjlq,");
		sql.append("MAX(CAST((CASE WHEN (llg1 IS NULL) OR (llg1 = '') ");
		sql.append("THEN '0' ELSE llg1 END) AS numeric(38, 2))) AS llg1,");
		sql.append("MAX(CAST((CASE WHEN (llg2 IS NULL) OR (llg2 = '') ");
		sql.append("THEN '0' ELSE llg2 END) AS numeric(38, 2))) AS llg2,");
		sql.append("MAX(CAST((CASE WHEN (llg3 IS NULL) OR (llg3 = '') ");
		sql.append("THEN '0' ELSE llg3 END) AS numeric(38, 2))) AS llg3,");
		sql.append("MAX(CAST((CASE WHEN (llg4 IS NULL) OR (llg4 = '') ");
		sql.append("THEN '0' ELSE llg4 END) AS numeric(38, 2))) AS llg4,");
		sql.append("MAX(CAST((CASE WHEN (llg5 IS NULL) OR (llg5 = '') ");
		sql.append("THEN '0' ELSE llg5 END) AS numeric(38, 2))) AS llg5,");
		sql.append("MAX(CAST((CASE WHEN (llg6 IS NULL) OR (llg6 = '') ");
		sql.append("THEN '0' ELSE llg6 END) AS numeric(38, 2))) AS llg6,");
		sql.append("MAX(CAST((CASE WHEN (llg7 IS NULL) OR (llg7 = '') ");
		sql.append("THEN '0' ELSE llg7 END) AS numeric(38, 2))) AS llg7,");
		sql.append("MAX(CAST((CASE WHEN (llf1 IS NULL) OR (llf1 = '') ");
		sql.append("THEN '0' ELSE llf1 END) AS numeric(38, 2))) AS llf1,");
		sql.append("MAX(CAST((CASE WHEN (llf2 IS NULL) OR (llf2 = '') ");
		sql.append("THEN '0' ELSE llf2 END) AS numeric(38, 2))) AS llf2,");
		sql.append("MAX(CAST((CASE WHEN (lltjj IS NULL) OR (lltjj = '') ");
		sql.append("THEN '0' ELSE lltjj END) AS numeric(38, 2))) AS lltjj,");
		sql.append("MAX(CAST((CASE WHEN (lllq IS NULL) OR (lllq = '') ");
		sql.append("THEN '0' ELSE lllq END) AS numeric(38, 2))) AS lllq,");
		sql.append("AVG(wsjg1) AS wsjg1,");
		sql.append("AVG(wsjg2) AS wsjg2,");
		sql.append("AVG(wsjg3) AS wsjg3,");
		sql.append("AVG(wsjg4) AS wsjg4,");
		sql.append("AVG(wsjg5) AS wsjg5,");
		sql.append("AVG(wsjg6) AS wsjg6,");
		sql.append("AVG(wsjg7) AS wsjg7,");
		sql.append("AVG(wsjf1) AS wsjf1,");
		sql.append("AVG(wsjf2) AS wsjf2,");
		sql.append("AVG(wsjtjj) AS wsjtjj,");
		sql.append("AVG(wsjlq) AS wsjlq,");
		sql.append("MAX(persjg1) AS maxpersjg1,");
		sql.append("MAX(persjg2) AS maxpersjg2,");
		sql.append("MAX(persjg3) AS maxpersjg3,");
		sql.append("MAX(persjg4) AS maxpersjg4,");
		sql.append("MAX(persjg5) AS maxpersjg5,");
		sql.append("MAX(persjg6) AS maxpersjg6,");
		sql.append("MAX(persjg7) AS maxpersjg7,");
		sql.append("MAX(persjf1) AS maxpersjf1,");
		sql.append("MAX(persjf2) AS maxpersjf2,");
		sql.append("MAX(persjtjj) AS maxpersjtjj,");
		sql.append("MAX(persjlq) AS maxpersjlq,");
		sql.append("MIN(persjg1) AS minpersjg1,");
		sql.append("MIN(persjg2) AS minpersjg2,");
		sql.append("MIN(persjg3) AS minpersjg3,");
		sql.append("MIN(persjg4) AS minpersjg4,");
		sql.append("MIN(persjg5) AS minpersjg5,");
		sql.append("MIN(persjg6) AS minpersjg6,");
		sql.append("MIN(persjg7) AS minpersjg7,");
		sql.append("MIN(persjf1) AS minpersjf1,");
		sql.append("MIN(persjf2) AS minpersjf2,");
		sql.append("MIN(persjtjj) AS minpersjtjj,");
		sql.append("MIN(persjlq) AS minpersjlq,");
		sql.append("AVG(persjg1) AS avgpersjg1,");
		sql.append("AVG(persjg2) AS avgpersjg2,");
		sql.append("AVG(persjg3) AS avgpersjg3,");
		sql.append("AVG(persjg4) AS avgpersjg4,");
		sql.append("AVG(persjg5) AS avgpersjg5,");
		sql.append("AVG(persjg6) AS avgpersjg6,");
		sql.append("AVG(persjg7) AS avgpersjg7,");
		sql.append("AVG(persjf1) AS avgpersjf1,");
		sql.append("AVG(persjf2) AS avgpersjf2,");
		sql.append("AVG(persjtjj) AS avgpersjtjj,");
		sql.append("AVG(persjlq) AS avgpersjlq");
		sql.append(" FROM LiqingphbView");
	}

	@Override
	public List<LiqingView> lqscsjjcphb(String startTime, String endTime,
			String shebeibianhao, Integer biaoduan, Integer xiangmubu,
			String fn, int bsid,String peifan) {
		List<LiqingView> _returnValue = new ArrayList<LiqingView>();
		StringBuffer sql = new StringBuffer();
		appenLqshuliangsqlphb(sql);	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar day=Calendar.getInstance(); 
		String nowtime = sdf.format(day.getTime());
		day.add(Calendar.DATE, -1);
		String begintime = sdf.format(day.getTime());
		if(StringUtil.Null2Blank(startTime).length()>0) 
		{
			begintime = startTime;
		}
		if(StringUtil.Null2Blank(endTime).length()>0)
		{
			nowtime = endTime;
		}
		sql.append(" where llysb is not null and llysb <> '' and llysb <> '0' and (baocunshijian between '"+begintime+"' and '"+nowtime+"') ");
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append("and shebeibianhao = '"+shebeibianhao+"'");
		}
	
		if (StringUtil.Null2Blank(peifan).length()>0) {			
			sql.append("and peifan = '"+peifan+"'");
		}
		if (null != biaoduan) {			
			sql.append(" and biaoduanid ="+biaoduan);
		}
		
		if (null != xiangmubu) {			
			sql.append(" and xiangmubuid ="+xiangmubu);
		}
		
		sql.append(" group by banhezhanminchen,llysb");
		
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
					LiqingView lqv = new LiqingView();		
					lqv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					if (StringUtil.Null2Blank(rs.getString("llysb")).length()>0) {
						try {
							lqv.setLlysb(String.format("%1$.2f",rs.getDouble("llysb")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("changliang")).length()>0) {
						try {
							lqv.setChangliang(String.format("%1$.2f",rs.getDouble("changliang")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("sjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("sjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("sjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("sjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("sjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("sjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("sjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("sjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("sjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("sjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("sjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("理论用量(%)");
					if (StringUtil.Null2Blank(rs.getString("llg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("llg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("llg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("llg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("llg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("llg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("llg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("llg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("llf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("llf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("lltjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("lltjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("lllq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("lllq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("平均用量(%)");
					if (StringUtil.Null2Blank(rs.getString("avgpersjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("avgpersjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("avgpersjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("avgpersjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("avgpersjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("avgpersjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("avgpersjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("avgpersjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("avgpersjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("avgpersjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("avgpersjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("avgpersjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("平均误差(%)");
					if (StringUtil.Null2Blank(rs.getString("wsjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("wsjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("wsjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("wsjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("wsjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("wsjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("wsjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("wsjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("wsjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("wsjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("wsjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("wsjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("最大用量(%)");
					if (StringUtil.Null2Blank(rs.getString("maxpersjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("maxpersjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("maxpersjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("maxpersjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("maxpersjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("maxpersjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("maxpersjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("maxpersjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("maxpersjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("maxpersjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("maxpersjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("maxpersjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("最小用量(%)");
					if (StringUtil.Null2Blank(rs.getString("minpersjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("minpersjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("minpersjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("minpersjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("minpersjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("minpersjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("minpersjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("minpersjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("minpersjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("minpersjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("minpersjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("minpersjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("最大用量(kg)");
					if (StringUtil.Null2Blank(rs.getString("maxsjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("maxsjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("maxsjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("maxsjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("maxsjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("maxsjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("maxsjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("maxsjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("maxsjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("maxsjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("maxsjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("maxsjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					
					lqv = new LiqingView();	
					lqv.setChangliang("最小用量(kg)");
					if (StringUtil.Null2Blank(rs.getString("minsjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("minsjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("minsjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("minsjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("minsjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("minsjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("minsjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("minsjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("minsjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("minsjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("minsjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("minsjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					
					lqv = new LiqingView();	
					lqv.setChangliang("极差(kg)");
					if (StringUtil.Null2Blank(rs.getString("jichasjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("jichasjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("jichasjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("jichasjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("jichasjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("jichasjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("jichasjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("jichasjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("jichasjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("jichasjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("jichasjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("jichasjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("平均用量(kg)");
					if (StringUtil.Null2Blank(rs.getString("avgsjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("avgsjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("avgsjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("avgsjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("avgsjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("avgsjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("avgsjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("avgsjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("avgsjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("avgsjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("avgsjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("avgsjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					
					lqv = new LiqingView();	
					lqv.setChangliang("标准差(kg)");
					if (StringUtil.Null2Blank(rs.getString("stdevsjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("stdevsjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("stdevsjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("stdevsjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("stdevsjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("stdevsjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("stdevsjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("stdevsjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("stdevsjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("stdevsjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("stdevsjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("stdevsjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("变异系数(%)");
					if (StringUtil.Null2Blank(rs.getString("bianyisjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("bianyisjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("bianyisjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("bianyisjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("bianyisjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("bianyisjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("bianyisjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("bianyisjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("bianyisjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("bianyisjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("bianyisjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("bianyisjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
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
		
		return _returnValue;
	}
	
	private void appenLqshuliangsqlmanualphb(StringBuffer sql) {		
		sql.append("select banhezhanminchen,llysb,");
		sql.append("SUM(CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS changliang,");
		sql.append("SUM(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS sjg1,");
		sql.append("SUM(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS sjg2,");
		sql.append("SUM(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS sjg3,");
		sql.append("SUM(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS sjg4,");
		sql.append("SUM(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS sjg5,");
		sql.append("SUM(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS sjg6,");
		sql.append("SUM(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS sjg7,");
		sql.append("SUM(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS sjf1,");
		sql.append("SUM(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS sjf2,");
		sql.append("SUM(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS sjtjj,");
		sql.append("SUM(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS sjlq,");
		sql.append("MAX(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS maxsjg1,");
		sql.append("MAX(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS maxsjg2,");
		sql.append("MAX(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS maxsjg3,");
		sql.append("MAX(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS maxsjg4,");
		sql.append("MAX(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS maxsjg5,");
		sql.append("MAX(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS maxsjg6,");
		sql.append("MAX(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS maxsjg7,");
		sql.append("MAX(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS maxsjf1,");
		sql.append("MAX(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS maxsjf2,");
		sql.append("MAX(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS maxsjtjj,");
		sql.append("MAX(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS maxsjlq,");
		sql.append("MIN(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS minsjg1,");
		sql.append("MIN(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS minsjg2,");
		sql.append("MIN(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS minsjg3,");
		sql.append("MIN(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS minsjg4,");
		sql.append("MIN(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS minsjg5,");
		sql.append("MIN(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS minsjg6,");
		sql.append("MIN(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS minsjg7,");
		sql.append("MIN(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS minsjf1,");
		sql.append("MIN(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS minsjf2,");
		sql.append("MIN(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS minsjtjj,");
		sql.append("MIN(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS minsjlq,");
		sql.append("MAX(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS jichasjg1,");
		sql.append("MAX(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS jichasjg2,");
		sql.append("MAX(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS jichasjg3,");
		sql.append("MAX(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS jichasjg4,");
		sql.append("MAX(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS jichasjg5,");
		sql.append("MAX(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS jichasjg6,");
		sql.append("MAX(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS jichasjg7,");
		sql.append("MAX(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS jichasjf1,");
		sql.append("MAX(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS jichasjf2,");
		sql.append("MAX(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS jichasjtjj,");
		sql.append("MAX(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2)))-");
		sql.append("MIN(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS jichasjlq,");
		sql.append("AVG(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS avgsjg1,");
		sql.append("AVG(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS avgsjg2,");
		sql.append("AVG(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS avgsjg3,");
		sql.append("AVG(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS avgsjg4,");
		sql.append("AVG(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS avgsjg5,");
		sql.append("AVG(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS avgsjg6,");
		sql.append("AVG(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS avgsjg7,");
		sql.append("AVG(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS avgsjf1,");
		sql.append("AVG(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS avgsjf2,");
		sql.append("AVG(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS avgsjtjj,");
		sql.append("AVG(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS avgsjlq,");
		sql.append("STDEV(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2))) AS stdevsjg1,");
		sql.append("STDEV(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2))) AS stdevsjg2,");
		sql.append("STDEV(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2))) AS stdevsjg3,");
		sql.append("STDEV(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2))) AS stdevsjg4,");
		sql.append("STDEV(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2))) AS stdevsjg5,");
		sql.append("STDEV(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2))) AS stdevsjg6,");
		sql.append("STDEV(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2))) AS stdevsjg7,");
		sql.append("STDEV(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2))) AS stdevsjf1,");
		sql.append("STDEV(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2))) AS stdevsjf2,");
		sql.append("STDEV(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2))) AS stdevsjtjj,");
		sql.append("STDEV(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2))) AS stdevsjlq,");
		sql.append("STDEV(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE sjg1 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '')"); 
		sql.append("THEN '1' ELSE sjg1 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg1 IS NULL) OR (sjg1 = '')");
		sql.append("THEN '1' ELSE sjg1 END) AS numeric(38, 2)))) END) AS bianyisjg1,");
		sql.append("STDEV(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE sjg2 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '')"); 
		sql.append("THEN '1' ELSE sjg2 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg2 IS NULL) OR (sjg2 = '')");
		sql.append("THEN '1' ELSE sjg2 END) AS numeric(38, 2)))) END) AS bianyisjg2,");
		sql.append("STDEV(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE sjg3 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '')"); 
		sql.append("THEN '1' ELSE sjg3 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg3 IS NULL) OR (sjg3 = '')");
		sql.append("THEN '1' ELSE sjg3 END) AS numeric(38, 2)))) END) AS bianyisjg3,");
		sql.append("STDEV(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE sjg4 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '')"); 
		sql.append("THEN '1' ELSE sjg4 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg4 IS NULL) OR (sjg4 = '')");
		sql.append("THEN '1' ELSE sjg4 END) AS numeric(38, 2)))) END) AS bianyisjg4,");
		sql.append("STDEV(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE sjg5 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '')"); 
		sql.append("THEN '1' ELSE sjg5 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg5 IS NULL) OR (sjg5 = '')");
		sql.append("THEN '1' ELSE sjg5 END) AS numeric(38, 2)))) END) AS bianyisjg5,");
		sql.append("STDEV(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE sjg6 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '')"); 
		sql.append("THEN '1' ELSE sjg6 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg6 IS NULL) OR (sjg6 = '')");
		sql.append("THEN '1' ELSE sjg6 END) AS numeric(38, 2)))) END) AS bianyisjg6,");
		sql.append("STDEV(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE sjg7 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '')"); 
		sql.append("THEN '1' ELSE sjg7 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjg7 IS NULL) OR (sjg7 = '')");
		sql.append("THEN '1' ELSE sjg7 END) AS numeric(38, 2)))) END) AS bianyisjg7,"); 
		sql.append("STDEV(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE sjf1 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '')"); 
		sql.append("THEN '1' ELSE sjf1 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjf1 IS NULL) OR (sjf1 = '')");
		sql.append("THEN '1' ELSE sjf1 END) AS numeric(38, 2)))) END) AS bianyisjf1,"); 
		sql.append("STDEV(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE sjf2 END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '')"); 
		sql.append("THEN '1' ELSE sjf2 END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjf2 IS NULL) OR (sjf2 = '')");
		sql.append("THEN '1' ELSE sjf2 END) AS numeric(38, 2)))) END) AS bianyisjf2,"); 
		sql.append("STDEV(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE sjtjj END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '')"); 
		sql.append("THEN '1' ELSE sjtjj END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjtjj IS NULL) OR (sjtjj = '')");
		sql.append("THEN '1' ELSE sjtjj END) AS numeric(38, 2)))) END) AS bianyisjtjj,"); 
		sql.append("STDEV(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '') ");
		sql.append("THEN '0' ELSE sjlq END) AS numeric(38, 2)))*100/");
		sql.append("(CASE WHEN (AVG(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '')"); 
		sql.append("THEN '1' ELSE sjlq END) AS numeric(38, 2))))=0 THEN 1 ELSE (AVG(CAST((CASE WHEN (sjlq IS NULL) OR (sjlq = '')");
		sql.append("THEN '1' ELSE sjlq END) AS numeric(38, 2)))) END) AS bianyisjlq,");
		sql.append("MAX(CAST((CASE WHEN (llg1 IS NULL) OR (llg1 = '') ");
		sql.append("THEN '0' ELSE llg1 END) AS numeric(38, 2))) AS llg1,");
		sql.append("MAX(CAST((CASE WHEN (llg2 IS NULL) OR (llg2 = '') ");
		sql.append("THEN '0' ELSE llg2 END) AS numeric(38, 2))) AS llg2,");
		sql.append("MAX(CAST((CASE WHEN (llg3 IS NULL) OR (llg3 = '') ");
		sql.append("THEN '0' ELSE llg3 END) AS numeric(38, 2))) AS llg3,");
		sql.append("MAX(CAST((CASE WHEN (llg4 IS NULL) OR (llg4 = '') ");
		sql.append("THEN '0' ELSE llg4 END) AS numeric(38, 2))) AS llg4,");
		sql.append("MAX(CAST((CASE WHEN (llg5 IS NULL) OR (llg5 = '') ");
		sql.append("THEN '0' ELSE llg5 END) AS numeric(38, 2))) AS llg5,");
		sql.append("MAX(CAST((CASE WHEN (llg6 IS NULL) OR (llg6 = '') ");
		sql.append("THEN '0' ELSE llg6 END) AS numeric(38, 2))) AS llg6,");
		sql.append("MAX(CAST((CASE WHEN (llg7 IS NULL) OR (llg7 = '') ");
		sql.append("THEN '0' ELSE llg7 END) AS numeric(38, 2))) AS llg7,");
		sql.append("MAX(CAST((CASE WHEN (llf1 IS NULL) OR (llf1 = '') ");
		sql.append("THEN '0' ELSE llf1 END) AS numeric(38, 2))) AS llf1,");
		sql.append("MAX(CAST((CASE WHEN (llf2 IS NULL) OR (llf2 = '') ");
		sql.append("THEN '0' ELSE llf2 END) AS numeric(38, 2))) AS llf2,");
		sql.append("MAX(CAST((CASE WHEN (lltjj IS NULL) OR (lltjj = '') ");
		sql.append("THEN '0' ELSE lltjj END) AS numeric(38, 2))) AS lltjj,");
		sql.append("MAX(CAST((CASE WHEN (lllq IS NULL) OR (lllq = '') ");
		sql.append("THEN '0' ELSE lllq END) AS numeric(38, 2))) AS lllq,");
		sql.append("AVG(wsjg1) AS wsjg1,");
		sql.append("AVG(wsjg2) AS wsjg2,");
		sql.append("AVG(wsjg3) AS wsjg3,");
		sql.append("AVG(wsjg4) AS wsjg4,");
		sql.append("AVG(wsjg5) AS wsjg5,");
		sql.append("AVG(wsjg6) AS wsjg6,");
		sql.append("AVG(wsjg7) AS wsjg7,");
		sql.append("AVG(wsjf1) AS wsjf1,");
		sql.append("AVG(wsjf2) AS wsjf2,");
		sql.append("AVG(wsjtjj) AS wsjtjj,");
		sql.append("AVG(wsjlq) AS wsjlq,");
		sql.append("MAX(persjg1) AS maxpersjg1,");
		sql.append("MAX(persjg2) AS maxpersjg2,");
		sql.append("MAX(persjg3) AS maxpersjg3,");
		sql.append("MAX(persjg4) AS maxpersjg4,");
		sql.append("MAX(persjg5) AS maxpersjg5,");
		sql.append("MAX(persjg6) AS maxpersjg6,");
		sql.append("MAX(persjg7) AS maxpersjg7,");
		sql.append("MAX(persjf1) AS maxpersjf1,");
		sql.append("MAX(persjf2) AS maxpersjf2,");
		sql.append("MAX(persjtjj) AS maxpersjtjj,");
		sql.append("MAX(persjlq) AS maxpersjlq,");
		sql.append("MIN(persjg1) AS minpersjg1,");
		sql.append("MIN(persjg2) AS minpersjg2,");
		sql.append("MIN(persjg3) AS minpersjg3,");
		sql.append("MIN(persjg4) AS minpersjg4,");
		sql.append("MIN(persjg5) AS minpersjg5,");
		sql.append("MIN(persjg6) AS minpersjg6,");
		sql.append("MIN(persjg7) AS minpersjg7,");
		sql.append("MIN(persjf1) AS minpersjf1,");
		sql.append("MIN(persjf2) AS minpersjf2,");
		sql.append("MIN(persjtjj) AS minpersjtjj,");
		sql.append("MIN(persjlq) AS minpersjlq,");
		sql.append("AVG(persjg1) AS avgpersjg1,");
		sql.append("AVG(persjg2) AS avgpersjg2,");
		sql.append("AVG(persjg3) AS avgpersjg3,");
		sql.append("AVG(persjg4) AS avgpersjg4,");
		sql.append("AVG(persjg5) AS avgpersjg5,");
		sql.append("AVG(persjg6) AS avgpersjg6,");
		sql.append("AVG(persjg7) AS avgpersjg7,");
		sql.append("AVG(persjf1) AS avgpersjf1,");
		sql.append("AVG(persjf2) AS avgpersjf2,");
		sql.append("AVG(persjtjj) AS avgpersjtjj,");
		sql.append("AVG(persjlq) AS avgpersjlq");
		sql.append(" FROM LiqingmanualphbView");
	}
	@Override
	public List<LiqingView> lqscsjjcmanualphb(String startTime, String endTime,
			String shebeibianhao, Integer biaoduan, Integer xiangmubu,
			String fn, int bsid,String peifan) {
		List<LiqingView> _returnValue = new ArrayList<LiqingView>();
		StringBuffer sql = new StringBuffer();
		appenLqshuliangsqlmanualphb(sql);	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar day=Calendar.getInstance(); 
		String nowtime = sdf.format(day.getTime());
		day.add(Calendar.DATE, -1);
		String begintime = sdf.format(day.getTime());
		if(StringUtil.Null2Blank(startTime).length()>0) 
		{
			begintime = startTime;
		}
		if(StringUtil.Null2Blank(endTime).length()>0)
		{
			nowtime = endTime;
		}
		sql.append(" where llysb is not null and llysb <> '' and llysb <> '0' and (baocunshijian between '"+begintime+"' and '"+nowtime+"') ");
		
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append("and shebeibianhao = '"+shebeibianhao+"'");
		}
		if (StringUtil.Null2Blank(peifan).length()>0) {			
			sql.append("and peifan = '"+peifan+"'");
		}
		if (null != biaoduan) {			
			sql.append(" and biaoduanid ="+biaoduan);
		}
		
		if (null != xiangmubu) {			
			sql.append(" and xiangmubuid ="+xiangmubu);
		}
		
		sql.append(" group by banhezhanminchen,llysb");
		
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
					LiqingView lqv = new LiqingView();		
					lqv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					if (StringUtil.Null2Blank(rs.getString("llysb")).length()>0) {
						try {
							lqv.setLlysb(String.format("%1$.2f",rs.getDouble("llysb")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("changliang")).length()>0) {
						try {
							lqv.setChangliang(String.format("%1$.2f",rs.getDouble("changliang")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("sjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("sjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("sjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("sjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("sjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("sjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("sjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("sjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("sjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("sjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("sjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("sjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("理论用量(%)");
					if (StringUtil.Null2Blank(rs.getString("llg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("llg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("llg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("llg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("llg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("llg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("llg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("llg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("llf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("llf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("llf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("lltjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("lltjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("lllq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("lllq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("平均用量(%)");
					if (StringUtil.Null2Blank(rs.getString("avgpersjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("avgpersjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("avgpersjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("avgpersjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("avgpersjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("avgpersjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("avgpersjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("avgpersjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("avgpersjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("avgpersjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("avgpersjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgpersjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("avgpersjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("平均误差(%)");
					if (StringUtil.Null2Blank(rs.getString("wsjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("wsjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("wsjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("wsjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("wsjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("wsjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("wsjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("wsjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("wsjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("wsjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("wsjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("wsjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("wsjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("最大用量(%)");
					if (StringUtil.Null2Blank(rs.getString("maxpersjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("maxpersjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("maxpersjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("maxpersjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("maxpersjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("maxpersjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("maxpersjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("maxpersjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("maxpersjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("maxpersjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("maxpersjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxpersjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("maxpersjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("最小用量(%)");
					if (StringUtil.Null2Blank(rs.getString("minpersjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("minpersjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("minpersjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("minpersjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("minpersjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("minpersjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("minpersjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("minpersjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("minpersjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("minpersjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("minpersjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minpersjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("minpersjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("最大用量(kg)");
					if (StringUtil.Null2Blank(rs.getString("maxsjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("maxsjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("maxsjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("maxsjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("maxsjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("maxsjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("maxsjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("maxsjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("maxsjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("maxsjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("maxsjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("maxsjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("maxsjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					
					lqv = new LiqingView();	
					lqv.setChangliang("最小用量(kg)");
					if (StringUtil.Null2Blank(rs.getString("minsjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("minsjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("minsjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("minsjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("minsjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("minsjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("minsjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("minsjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("minsjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("minsjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("minsjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("minsjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("minsjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					
					lqv = new LiqingView();	
					lqv.setChangliang("极差(kg)");
					if (StringUtil.Null2Blank(rs.getString("jichasjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("jichasjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("jichasjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("jichasjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("jichasjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("jichasjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("jichasjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("jichasjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("jichasjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("jichasjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("jichasjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("jichasjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("jichasjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("平均用量(kg)");
					if (StringUtil.Null2Blank(rs.getString("avgsjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("avgsjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("avgsjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("avgsjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("avgsjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("avgsjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("avgsjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("avgsjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("avgsjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("avgsjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("avgsjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("avgsjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("avgsjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					
					lqv = new LiqingView();	
					lqv.setChangliang("标准差(kg)");
					if (StringUtil.Null2Blank(rs.getString("stdevsjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("stdevsjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("stdevsjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("stdevsjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("stdevsjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("stdevsjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("stdevsjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("stdevsjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("stdevsjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("stdevsjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("stdevsjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("stdevsjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("stdevsjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
					lqv = new LiqingView();	
					lqv.setChangliang("变异系数(%)");
					if (StringUtil.Null2Blank(rs.getString("bianyisjg1")).length()>0) {
						try {
							lqv.setSjg1(String.format("%1$.2f",rs.getDouble("bianyisjg1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjg2")).length()>0) {
						try {
							lqv.setSjg2(String.format("%1$.2f",rs.getDouble("bianyisjg2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjg3")).length()>0) {
						try {
							lqv.setSjg3(String.format("%1$.2f",rs.getDouble("bianyisjg3")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjg4")).length()>0) {
						try {
							lqv.setSjg4(String.format("%1$.2f",rs.getDouble("bianyisjg4")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjg5")).length()>0) {
						try {
							lqv.setSjg5(String.format("%1$.2f",rs.getDouble("bianyisjg5")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjg6")).length()>0) {
						try {
							lqv.setSjg6(String.format("%1$.2f",rs.getDouble("bianyisjg6")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjg7")).length()>0) {
						try {
							lqv.setSjg7(String.format("%1$.2f",rs.getDouble("bianyisjg7")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjf1")).length()>0) {
						try {
							lqv.setSjf1(String.format("%1$.2f",rs.getDouble("bianyisjf1")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjf2")).length()>0) {
						try {
							lqv.setSjf2(String.format("%1$.2f",rs.getDouble("bianyisjf2")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjtjj")).length()>0) {
						try {
							lqv.setSjtjj(String.format("%1$.2f",rs.getDouble("bianyisjtjj")));
						} catch (Exception e) {
						}
					}
					if (StringUtil.Null2Blank(rs.getString("bianyisjlq")).length()>0) {
						try {
							lqv.setSjlq(String.format("%1$.2f",rs.getDouble("bianyisjlq")));
						} catch (Exception e) {
						}
					}
					_returnValue.add(lqv);
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
		
		return _returnValue;
	}
	
	//沥青拌和时间查询
	public GenericPageMode lqsjviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize){
		GenericPageMode pagemode=new GenericPageMode();
		List<LiqingView> _returnValue = new ArrayList<LiqingView>();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LiqingView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition =  " (convert(datetime,baocunshijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and shebeibianhao='" + shebeibianhao + "'";
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
			cs.setString(3, "bianhao");
			cs.setString(4, "bianhao DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingView hv = new LiqingView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));	
					hv.setShijian(rs.getString("shijian"));
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					hv.setCaijishijian(rs.getString("caijishijian"));
					hv.setJbsj(rs.getString("jbsj"));
					hv.setYonghu(rs.getString("yonghu"));
					hv.setPeifan(rs.getString("peifan"));
					hv.setBeiy1(rs.getString("beiy1"));
					hv.setBeiy2(rs.getString("beiy2"));
					hv.setBeiy3(rs.getString("beiy3"));
					try {
						hv.setLqwd(String.format("%1$.1f", rs
								.getFloat("lqwd")));
					} catch (Exception e) {						
					}
					try {
						hv.setGlwd(String.format("%1$.1f", rs
								.getFloat("glwd")));
					} catch (Exception e) {						
					}
					try {
						hv.setClwd(String.format("%1$.1f", rs
								.getFloat("clwd")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setChangliang(String.format("%1$.1f", rs
								.getFloat("changliang")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setSjg1(String.format("%1$.1f", rs
								.getFloat("sjg1")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg2(String.format("%1$.1f", rs
								.getFloat("sjg2")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg3(String.format("%1$.1f", rs
								.getFloat("sjg3")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg4(String.format("%1$.1f", rs
								.getFloat("sjg4")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg5(String.format("%1$.1f", rs
								.getFloat("sjg5")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg6(String.format("%1$.1f", rs
								.getFloat("sjg6")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg7(String.format("%1$.1f", rs
								.getFloat("sjg7")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjf1(String.format("%1$.1f", rs
								.getFloat("sjf1")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjf2(String.format("%1$.1f", rs
								.getFloat("sjf2")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjlq(String.format("%1$.1f", rs
								.getFloat("sjlq")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjtjj(String.format("%1$.1f", rs
								.getFloat("sjtjj")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjysb(String.format("%1$.2f", rs
								.getFloat("sjysb")));
					} catch (Exception e) {						
					}
				
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
	
	
	//沥青拌和时间查询
	public GenericPageMode lqllviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize){
		GenericPageMode pagemode=new GenericPageMode();
		List<LiqingView> _returnValue = new ArrayList<LiqingView>();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LiqingView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition =  " (convert(datetime,baocunshijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and shebeibianhao='" + shebeibianhao + "'";
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
			cs.setString(3, "bianhao");
			cs.setString(4, "bianhao DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingView hv = new LiqingView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));	
					hv.setShijian(rs.getString("shijian"));
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					hv.setCaijishijian(rs.getString("caijishijian"));
					hv.setJbsj(rs.getString("jbsj"));
					hv.setYonghu(rs.getString("yonghu"));
					hv.setPeifan(rs.getString("peifan"));
					hv.setBeiy1(rs.getString("beiy1"));
					hv.setBeiy2(rs.getString("beiy2"));
					hv.setBeiy3(rs.getString("beiy3"));
					try {
						hv.setLqwd(String.format("%1$.1f", rs
								.getFloat("lqwd")));
					} catch (Exception e) {						
					}
					try {
						hv.setGlwd(String.format("%1$.1f", rs
								.getFloat("glwd")));
					} catch (Exception e) {						
					}
					try {
						hv.setClwd(String.format("%1$.1f", rs
								.getFloat("clwd")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setChangliang(String.format("%1$.1f", rs
								.getFloat("changliang")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setSjg1(String.format("%1$.1f", rs
								.getFloat("sjg1")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg2(String.format("%1$.1f", rs
								.getFloat("sjg2")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg3(String.format("%1$.1f", rs
								.getFloat("sjg3")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg4(String.format("%1$.1f", rs
								.getFloat("sjg4")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg5(String.format("%1$.1f", rs
								.getFloat("sjg5")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg6(String.format("%1$.1f", rs
								.getFloat("sjg6")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjg7(String.format("%1$.1f", rs
								.getFloat("sjg7")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjf1(String.format("%1$.1f", rs
								.getFloat("sjf1")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjf2(String.format("%1$.1f", rs
								.getFloat("sjf2")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjlq(String.format("%1$.1f", rs
								.getFloat("sjlq")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjtjj(String.format("%1$.1f", rs
								.getFloat("sjtjj")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjysb(String.format("%1$.2f", rs
								.getFloat("sjysb")));
					} catch (Exception e) {						
					}
				
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
	public List<LiqingView> findTop10(String shebeibianhao) {
		Query query = getTemplate().getSessionFactory().openSession().createQuery("from LiqingView where shebeibianhao='"+shebeibianhao+"' ORDER BY bianhao DESC");
		query.setFirstResult(0);
		query.setMaxResults(10);	
		return query.list();
	}
	
	@Override
	public GenericPageMode lqmanualphbviewwuchalist(String shebeibianhao,String startTimeOne,String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize,String peifan){
		List<LiqingmanualphbView> _returnValue = new ArrayList<LiqingmanualphbView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LiqingmanualphbView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}

		String queryCondition = " (convert(datetime,baocunshijian,121) between '" + strstartTime+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and shebeibianhao='" + shebeibianhao + "'";
		}
		
		if (null != biaoduan) {
			queryCondition += " and biaoduanid=" + biaoduan;
		}
		if (null != xiangmubu) {
			queryCondition += " and xiangmubuid=" + xiangmubu;
		}
		if (StringUtil.Null2Blank(peifan).length() > 0) {
			queryCondition += " and peifan='" + peifan + "'";
		}
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "bianhao");
			cs.setString(4, "bianhao DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingmanualphbView hv = new LiqingmanualphbView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));	
					hv.setShijian(rs.getString("shijian"));
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					hv.setCaijishijian(rs.getString("caijishijian"));
					hv.setJbsj(rs.getString("jbsj"));
					hv.setYonghu(rs.getString("yonghu"));
					hv.setPeifan(rs.getString("peifan"));
					hv.setLqwd(rs.getString("lqwd"));
					hv.setGlwd(rs.getString("glwd"));
					hv.setClwd(rs.getString("clwd"));
					hv.setChangliang(rs.getString("changliang"));
					hv.setBeiy1(rs.getString("beiy1"));
					hv.setBeiy2(rs.getString("beiy2"));
					hv.setBeiy3(rs.getString("beiy3"));
					//实际值
					try {
						hv.setSjg1(String.format("%1$.1f", rs.getFloat("sjg1")));
					} catch (Exception e) {}
					try {
						hv.setSjg2(String.format("%1$.1f", rs.getFloat("sjg2")));
					} catch (Exception e) {}
					try {
						hv.setSjg3(String.format("%1$.1f", rs.getFloat("sjg3")));
					} catch (Exception e) {}
					try {
						hv.setSjg4(String.format("%1$.1f", rs.getFloat("sjg4")));
					} catch (Exception e) {}
					try {
						hv.setSjg5(String.format("%1$.1f", rs.getFloat("sjg5")));
					} catch (Exception e) {}
					try {
						hv.setSjg6(String.format("%1$.1f", rs.getFloat("sjg6")));
					} catch (Exception e) {}
					try {
						hv.setSjg7(String.format("%1$.1f", rs.getFloat("sjg7")));
					} catch (Exception e) {}
					try {
						hv.setSjf1(String.format("%1$.2f", rs.getFloat("sjf1")));
					} catch (Exception e) {}
					try {
						hv.setSjf2(String.format("%1$.2f", rs.getFloat("sjf2")));
					} catch (Exception e) {}
					try {
						hv.setSjlq(String.format("%1$.2f", rs.getFloat("sjlq")));
					} catch (Exception e) {}
					try {
						hv.setSjtjj(String.format("%1$.2f", rs.getFloat("sjtjj")));
					} catch (Exception e) {}
					try {
						hv.setSjysb(String.format("%1$.2f", rs.getFloat("sjysb")));
					} catch (Exception e) {}
					//实际配合比
					try {
						hv.setPersjg1(String.format("%1$.1f", rs.getFloat("persjg1")));
					} catch (Exception e) {}
					try {
						hv.setPersjg2(String.format("%1$.1f", rs.getFloat("persjg2")));
					} catch (Exception e) {}
					try {
						hv.setPersjg3(String.format("%1$.1f", rs.getFloat("persjg3")));
					} catch (Exception e) {}
					try {
						hv.setPersjg4(String.format("%1$.1f", rs.getFloat("persjg4")));
					} catch (Exception e) {}
					try {
						hv.setPersjg5(String.format("%1$.1f", rs.getFloat("persjg5")));
					} catch (Exception e) {}
					try {
						hv.setPersjg6(String.format("%1$.1f", rs.getFloat("persjg6")));
					} catch (Exception e) {}
					try {
						hv.setPersjg7(String.format("%1$.1f", rs.getFloat("persjg7")));
					} catch (Exception e) {}
					try {
						hv.setPersjf1(String.format("%1$.2f", rs.getFloat("persjf1")));
					} catch (Exception e) {}
					try {
						hv.setPersjf2(String.format("%1$.2f", rs.getFloat("persjf2")));
					} catch (Exception e) {}
					try {
						hv.setPersjlq(String.format("%1$.2f", rs.getFloat("persjlq")));
					} catch (Exception e) {}
					try {
						hv.setPersjtjj(String.format("%1$.2f", rs.getFloat("persjtjj")));
					} catch (Exception e) {}
					//关联理论配合比
					if(StringUtil.Null2Blank(rs.getString("biaoshi")).length()>0){
						//关联理论配合比
						hv.setLlid(rs.getInt("llid"));
						hv.setLlbuwei(rs.getString("llbuwei"));
						hv.setLilunname(rs.getString("lilunname"));
						hv.setLlmoren(rs.getString("llmoren"));
						
						try {
							hv.setLlg1(String.format("%1$.1f", rs.getFloat("perllg1")));
						} catch (Exception e) {}
						try {
							hv.setLlg2(String.format("%1$.1f", rs.getFloat("perllg2")));
						} catch (Exception e) {}
						try {
							hv.setLlg3(String.format("%1$.1f", rs.getFloat("perllg3")));
						} catch (Exception e) {}
						try {
							hv.setLlg4(String.format("%1$.1f", rs.getFloat("perllg4")));
						} catch (Exception e) {}
						try {
							hv.setLlg5(String.format("%1$.1f", rs.getFloat("perllg5")));
						} catch (Exception e) {}
						try {
							hv.setLlg6(String.format("%1$.1f", rs.getFloat("perllg6")));
						} catch (Exception e) {}
						try {
							hv.setLlg7(String.format("%1$.1f", rs.getFloat("perllg7")));
						} catch (Exception e) {}
						try {
							hv.setLlf1(String.format("%1$.2f", rs.getFloat("perllf1")));
						} catch (Exception e) {}
						try {
							hv.setLlf2(String.format("%1$.2f", rs.getFloat("perllf2")));
						} catch (Exception e) {}
						try {
							hv.setLllq(String.format("%1$.2f", rs.getFloat("perlllq")));
						} catch (Exception e) {}
						try {
							hv.setLltjj(String.format("%1$.2f", rs.getFloat("perlltjj")));
						} catch (Exception e) {}
						try {
							hv.setLlysb(String.format("%1$.2f", rs.getFloat("perllysb")));
						} catch (Exception e) {}
						
						try {
							hv.setWsjg1(String.format("%1$.2f", rs.getFloat("manualwsjg1")));
						} catch (Exception e) {}
						try {
							hv.setWsjg2(String.format("%1$.2f", rs.getFloat("manualwsjg2")));
						} catch (Exception e) {}
						try {
							hv.setWsjg3(String.format("%1$.2f", rs.getFloat("manualwsjg3")));
						} catch (Exception e) {}
						try {
							hv.setWsjg4(String.format("%1$.2f", rs.getFloat("manualwsjg4")));
						} catch (Exception e) {}
						try {
							hv.setWsjg5(String.format("%1$.2f", rs.getFloat("manualwsjg5")));
						} catch (Exception e) {}
						try {
							hv.setWsjg6(String.format("%1$.2f", rs.getFloat("manualwsjg6")));
						} catch (Exception e) {}
						try {
							hv.setWsjg7(String.format("%1$.2f", rs.getFloat("manualwsjg7")));
						} catch (Exception e) {}
						try {
							hv.setWsjf1(String.format("%1$.2f", rs.getFloat("manualwsjf1")));
						} catch (Exception e) {}
						try {
							hv.setWsjf2(String.format("%1$.2f", rs.getFloat("manualwsjf2")));
						} catch (Exception e) {}
						try {
							hv.setWsjlq(String.format("%1$.2f", rs.getFloat("manualwsjlq")));
						} catch (Exception e) {}
						try {
							hv.setWsjtjj(String.format("%1$.2f", rs.getFloat("manualwsjtjj")));
						} catch (Exception e) {}
						try {
							hv.setWsjysb(String.format("%1$.2f", rs.getFloat("manualwsjysb")));
						} catch (Exception e) {}
						
					}else{
						try {
							hv.setLlg1(String.format("%1$.1f", rs.getFloat("llg1")));
						} catch (Exception e) {}
						try {
							hv.setLlg2(String.format("%1$.1f", rs.getFloat("llg2")));
						} catch (Exception e) {}
						try {
							hv.setLlg3(String.format("%1$.1f", rs.getFloat("llg3")));
						} catch (Exception e) {}
						try {
							hv.setLlg4(String.format("%1$.1f", rs.getFloat("llg4")));
						} catch (Exception e) {}
						try {
							hv.setLlg5(String.format("%1$.1f", rs.getFloat("llg5")));
						} catch (Exception e) {}
						try {
							hv.setLlg6(String.format("%1$.1f", rs.getFloat("llg6")));
						} catch (Exception e) {}
						try {
							hv.setLlg7(String.format("%1$.1f", rs.getFloat("llg7")));
						} catch (Exception e) {}
						try {
							hv.setLlf1(String.format("%1$.2f", rs.getFloat("llf1")));
						} catch (Exception e) {}
						try {
							hv.setLlf2(String.format("%1$.2f", rs.getFloat("llf2")));
						} catch (Exception e) {}
						try {
							hv.setLllq(String.format("%1$.2f", rs.getFloat("lllq")));
						} catch (Exception e) {}
						try {
							hv.setLltjj(String.format("%1$.2f", rs.getFloat("lltjj")));
						} catch (Exception e) {}
						try {
							hv.setLlysb(String.format("%1$.2f", rs.getFloat("llysb")));
						} catch (Exception e) {}
						
						try {
							hv.setWsjg1(String.format("%1$.2f", rs.getFloat("wsjg1")));
						} catch (Exception e) {}
						try {
							hv.setWsjg2(String.format("%1$.2f", rs.getFloat("wsjg2")));
						} catch (Exception e) {}
						try {
							hv.setWsjg3(String.format("%1$.2f", rs.getFloat("wsjg3")));
						} catch (Exception e) {}
						try {
							hv.setWsjg4(String.format("%1$.2f", rs.getFloat("wsjg4")));
						} catch (Exception e) {}
						try {
							hv.setWsjg5(String.format("%1$.2f", rs.getFloat("wsjg5")));
						} catch (Exception e) {}
						try {
							hv.setWsjg6(String.format("%1$.2f", rs.getFloat("wsjg6")));
						} catch (Exception e) {}
						try {
							hv.setWsjg7(String.format("%1$.2f", rs.getFloat("wsjg7")));
						} catch (Exception e) {}
						try {
							hv.setWsjf1(String.format("%1$.2f", rs.getFloat("wsjf1")));
						} catch (Exception e) {}
						try {
							hv.setWsjf2(String.format("%1$.2f", rs.getFloat("wsjf2")));
						} catch (Exception e) {}
						try {
							hv.setWsjlq(String.format("%1$.2f", rs.getFloat("wsjlq")));
						} catch (Exception e) {}
						try {
							hv.setWsjtjj(String.format("%1$.2f", rs.getFloat("wsjtjj")));
						} catch (Exception e) {}
						try {
							hv.setWsjysb(String.format("%1$.2f", rs.getFloat("wsjysb")));
						} catch (Exception e) {}
					}
					_returnValue.add(hv);
				}
				pagemode.setDatas(_returnValue);
				pagemode.setRecordcount(cs.getInt(7));
				pagemode.setPagetotal(cs.getInt(8));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			DbJdbcUtil.closeAll(rs, cs,con);
		}
		return pagemode;
	}
}