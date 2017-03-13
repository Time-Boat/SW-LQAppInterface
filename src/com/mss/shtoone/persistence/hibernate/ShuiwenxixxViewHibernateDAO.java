package com.mss.shtoone.persistence.hibernate;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.persistence.ShuiwenxixxViewDAO;
import com.mss.shtoone.service.BiaoduanService;
import com.mss.shtoone.service.XiangmubuService;
import com.mss.shtoone.util.DbJdbcUtil;
import com.mss.shtoone.util.StringUtil;

@Repository
public class ShuiwenxixxViewHibernateDAO extends GenericHibernateDAO<ShuiwenxixxView,Integer> implements ShuiwenxixxViewDAO{
	
	private static Log logger = LogFactory.getLog(ShuiwenxixxViewHibernateDAO.class);
		
	@Autowired
	private BiaoduanService bdService;
	
	@Autowired
	private XiangmubuService xmbService;
	
	private void getChaobiaops(ShuiwenxixxView hv,String startTime, String endTime,
			String shebeibianhao, Integer biaoduan, Integer xiangmubu, 
			int cnfxlx, String fn, int bsid, String xa, String xb) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(CASE WHEN (leixing <>'') or (leixing IS NOT NULL)  THEN 1 ELSE 0 END) AS cbps,");
		sql.append("SUM(CASE WHEN (leixing ='2') or (leixing ='3') THEN 1 ELSE 0 END) AS midcbps,");
		sql.append("SUM(CASE WHEN (leixing ='3') THEN 1 ELSE 0 END) AS highcbps  From ShuiwenxixxjieguoView where 1=1");
	    switch (cnfxlx) {
		case 1://季度
			sql.append(" and datename(year, shijianS)=");
			sql.append(xa);
			sql.append(" and datename(quarter, shijianS)=");
			sql.append(xb);
			break;
		case 2://月份
			sql.append(" and datename(year, shijianS)=");
			sql.append(xa);
			sql.append(" and datename(month, shijianS)=");
			sql.append(xb);
			break;
		case 3://周
			sql.append(" and datename(year, shijianS)=");
			sql.append(xa);
			sql.append(" and datename(week, shijianS)=");
			sql.append(xb);
			break;			
		case 4://天
			sql.append(" and datename(month, shijianS)=");
			sql.append(xa);
			sql.append(" and datename(dd, shijianS)=");
			sql.append(xb);
			break;	
		default:
			sql.append(" and datename(month, shijianS)=");
			sql.append(xa);
			sql.append(" and datename(dd, shijianS)=");
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
			sql.append(" and (shijianS between '"+startTime+"' and '"+endTime+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()==0)
		{
			sql.append(" and (shijianS between '"+startTime+"' and '"+newDate+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()==0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (shijianS between '1900-01-01' and '"+endTime+"')");
		}
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();	
			if (true) {
				rs = st.executeQuery(sql.toString());
				if (rs.next()) {
					if(StringUtil.Null2Blank(rs.getString("cbps")).length()==0){  
						hv.setAmbegin("0");
					}else{
						hv.setAmbegin(rs.getString("cbps"));
					}
					if(StringUtil.Null2Blank(rs.getString("midcbps")).length()==0){  
						hv.setPmbegin("0");
					}else{
						hv.setPmbegin(rs.getString("midcbps"));
					}
					if(StringUtil.Null2Blank(rs.getString("highcbps")).length()==0){  
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
	
	//水稳产能分析
	@Override
	public List<ShuiwenxixxView> swcnfxlist(String startTime,String endTime,String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu,int cnfxlx, String fn, int bsid){
		List<ShuiwenxixxView> _returnValue = new ArrayList<ShuiwenxixxView>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(glchangliang) AS glchangliang, COUNT(bianhao) as pangshu ");	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		String nowtime = sdf.format(day.getTime());
		String begintime = sdf.format(day.getTime());
	    switch (cnfxlx) {
			case 1://季度
				sql.append(",datename(year, shijianS) as xa, datename(quarter, shijianS) as xb");
				sql.append(" FROM ShuiwenxixxView");
		    	day.add(Calendar.MONTH, -9);
		    	begintime = sdf.format(day.getTime());
				break;
			case 2://月份
				sql.append(",datename(year, shijianS) as xa, datename(month, shijianS) as xb");
				sql.append(" FROM ShuiwenxixxView");
				day.add(Calendar.MONTH, -3);
		    	begintime = sdf.format(day.getTime());
				break;
			case 3://周
				sql.append(",datename(year, shijianS) as xa, datename(week, shijianS) as xb");
				sql.append(" FROM ShuiwenxixxView");
				day.add(Calendar.WEEK_OF_YEAR, -4);
		    	begintime = sdf.format(day.getTime());
				break;
			case 4://天
				sql.append(",month(shijianS) as xa, day(shijianS) as xb");
				sql.append(" FROM ShuiwenxixxView");
				day.add(Calendar.DATE, -6);
		    	begintime = sdf.format(day.getTime());
				break;
			case 5://小时
				sql.append(",datename(dd, shijianS) as xa, datename(hh, shijianS) as xb");
				sql.append(" FROM ShuiwenxixxView");
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
		
		sql.append(" where (shijianS between '"+begintime+"' and '"+nowtime+"') ");
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
			sql.append(" group by datename(year, shijianS), datename(quarter, shijianS) order by datename(quarter, shijianS)");
			break;
		case 2:
			sql.append(" group by datename(year, shijianS), datename(month, shijianS) order by datename(month, shijianS)");
			break;
		case 3:
			sql.append(" group by datename(year, shijianS), datename(week, shijianS) order by datename(week, shijianS)");
			break;
		case 4://天
			sql.append(" group by datename(year, shijianS), month(shijianS), ");
			sql.append(" day(shijianS) order by datename(year, shijianS),month(shijianS),day(shijianS)");			
			break;
		case 5://小时
			sql.append(" group by datename(year, shijianS), datename(month, shijianS), ");
			sql.append(" datename(dd, shijianS), datename(hh, shijianS) order by");
			sql.append(" datename(year, shijianS),datename(month, shijianS),datename(dd, shijianS), datename(hh, shijianS)");			
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
					ShuiwenxixxView hv = new ShuiwenxixxView();
					hv.setSmstype(rs.getString("xa"));
					hv.setSendtype(rs.getString("xb"));
					hv.setPanshu(rs.getString("pangshu"));
					getChaobiaops(hv,startTime, endTime, shebeibianhao,
							biaoduan, xiangmubu, cnfxlx, fn, bsid, hv.getSmstype(), hv.getSendtype());
					try {
						if (Double.parseDouble(hv.getPanshu())>0) {
						 hv.setAmend(String.format("%1$.2f",
								 Double.parseDouble(hv.getAmbegin())*100/Double.parseDouble(hv.getPanshu())));
						 hv.setPmend(String.format("%1$.2f",
								 Double.parseDouble(hv.getPmbegin())*100/Double.parseDouble(hv.getPanshu())));
						 hv.setBiaoshi(String.format("%1$.2f",
								 Double.parseDouble(hv.getBeizhu())*100/Double.parseDouble(hv.getPanshu())));
						}
					} catch (Exception e) {
					}
					hv.setGlchangliang(String.format("%1$.1f",rs.getDouble("glchangliang")));
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
	
	public List<ShuiwenxixxView> swsmstongji(String startTime,String endTime,Integer biaoduan,Integer xiangmubu,String shebeibianhao,
			String fn,Integer bsid,int fzlx){
		List<ShuiwenxixxView> _returnValue=new ArrayList<ShuiwenxixxView>();
		StringBuffer sql = new StringBuffer();
		sql.append("select ");
		switch (fzlx) {
		case 1:
			sql.append("biaoduanid,");
			break;
		case 2:
			sql.append("biaoduanid,xiangmubuid,");
			break;
		case 3:
			sql.append("biaoduanid,xiangmubuid,banhezhanminchen,Max(id) as id,");
			break;
		}
			sql.append("SUM(CASE WHEN (sjg1 is not null) ");
			sql.append("OR (sjg2 is not null) ");
			sql.append("OR (sjg3 is not null) ");
			sql.append("OR (sjg4 is not null) ");
			sql.append("OR (sjg5 is not null) ");
			sql.append("OR (sjf1 is not null) ");
			sql.append("OR (sjf2 is not null) ");
			sql.append("THEN 1 ELSE 0 END) AS cbps,");
			
			
			sql.append("SUM(CASE WHEN (sjg1 = '3') OR (sjg1 = '6') ");
			sql.append("OR (sjg2 = '3') OR (sjg2 = '6') ");
			sql.append("OR (sjg3 = '3') OR (sjg3 = '6') ");
			sql.append("OR (sjg4 = '3') OR (sjg4 = '6') ");
			sql.append("OR (sjg5 = '3') OR (sjg5 = '6') ");
			sql.append("OR (sjf1 = '3') OR (sjf1 = '6') ");
			sql.append("OR (sjf2 = '3') OR (sjf2 = '6') ");
			
			sql.append("OR (sjg1 = '2') OR (sjg1 = '5') ");
			sql.append("OR (sjg2 = '2') OR (sjg2 = '5') ");
			sql.append("OR (sjg3 = '2') OR (sjg3 = '5') ");
			sql.append("OR (sjg4 = '2') OR (sjg4 = '5') ");
			sql.append("OR (sjg5 = '2') OR (sjg5 = '5') ");
			sql.append("OR (sjf1 = '2') OR (sjf1 = '5') ");
			sql.append("OR (sjf2 = '2') OR (sjf2 = '5') ");
			
			sql.append("OR (sjg1 = '0') OR (sjg1 = '7') ");
			sql.append("OR (sjg2 = '0') OR (sjg2 = '7') ");
			sql.append("OR (sjg3 = '0') OR (sjg3 = '7') ");
			sql.append("OR (sjg4 = '0') OR (sjg4 = '7') ");
			sql.append("OR (sjg5 = '0') OR (sjg5 = '7') ");
			sql.append("OR (sjf1 = '0') OR (sjf1 = '7') ");
			sql.append("OR (sjf2 = '0') OR (sjf2 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS midcbps,");
			
			sql.append("SUM(CASE WHEN (sjg1 = '3') OR (sjg1 = '6') OR (sjg1 = '0') OR (sjg1 = '7') ");
			sql.append("OR (sjg2 = '3') OR (sjg2 = '6') OR (sjg2 = '0') OR (sjg2 = '7') ");
			sql.append("OR (sjg3 = '3') OR (sjg3 = '6') OR (sjg3 = '0') OR (sjg3 = '7') ");
			sql.append("OR (sjg4 = '3') OR (sjg4 = '6') OR (sjg4 = '0') OR (sjg4 = '7') ");
			sql.append("OR (sjg5 = '3') OR (sjg5 = '6') OR (sjg5 = '0') OR (sjg5 = '7') ");
			sql.append("OR (sjf1 = '3') OR (sjf1 = '6') OR (sjf1 = '0') OR (sjf1 = '7') ");
			sql.append("OR (sjf2 = '3') OR (sjf2 = '6') OR (sjf2 = '0') OR (sjf2 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS highcbps,");
			
			
			//总（正）
			sql.append("SUM(CASE WHEN  (sjg1 = '4') OR (sjg1 = '5') OR (sjg1 = '6') OR (sjg1 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS sjg1,");
			sql.append("SUM(CASE WHEN  (sjg2 = '4') OR (sjg2 = '5') OR (sjg2 = '6') OR (sjg2 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS sjg2,");
			sql.append("SUM(CASE WHEN  (sjg3 = '4') OR (sjg3 = '5') OR (sjg3 = '6') OR (sjg3 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS sjg3,");
			sql.append("SUM(CASE WHEN  (sjg4 = '4') OR (sjg4 = '5') OR (sjg4 = '6') OR (sjg4 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS sjg4,");
			sql.append("SUM(CASE WHEN  (sjg5 = '4') OR (sjg5 = '5') OR (sjg5 = '6') OR (sjg5 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS sjg5,");
			sql.append("SUM(CASE WHEN  (sjf1 = '4') OR (sjf1 = '5') OR (sjf1 = '6') OR (sjf1 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS sjf1,");
			sql.append("SUM(CASE WHEN  (sjf2 = '4') OR (sjf2 = '5') OR (sjf2 = '6') OR (sjf2 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS sjf2,");
			
			//总（负）
			sql.append("SUM(CASE WHEN  (sjg1 = '0') OR (sjg1 = '1') OR (sjg1 = '2') OR (sjg1 = '3') ");
			sql.append("THEN 1 ELSE 0 END) AS fsjg1,");
			sql.append("SUM(CASE WHEN  (sjg2 = '0') OR (sjg2 = '1') OR (sjg2 = '2') OR (sjg2 = '3') ");
			sql.append("THEN 1 ELSE 0 END) AS fsjg2,");
			sql.append("SUM(CASE WHEN  (sjg3 = '0') OR (sjg3 = '1') OR (sjg3 = '2') OR (sjg3 = '3') ");
			sql.append("THEN 1 ELSE 0 END) AS fsjg3,");
			sql.append("SUM(CASE WHEN  (sjg4 = '0') OR (sjg4 = '1') OR (sjg4 = '2') OR (sjg4 = '3') ");
			sql.append("THEN 1 ELSE 0 END) AS fsjg4,");
			sql.append("SUM(CASE WHEN  (sjg5 = '0') OR (sjg5 = '1') OR (sjg5 = '2') OR (sjg5 = '3') ");
			sql.append("THEN 1 ELSE 0 END) AS fsjg5,");
			sql.append("SUM(CASE WHEN  (sjf1 = '0') OR (sjf1 = '1') OR (sjf1 = '2')  OR (sjf1 = '3') ");
			sql.append("THEN 1 ELSE 0 END) AS fsjf1,");
			sql.append("SUM(CASE WHEN  (sjf2 = '0') OR (sjf2 = '1') OR (sjf2 = '2') OR (sjf2 = '3') ");
			sql.append("THEN 1 ELSE 0 END) AS fsjf2,");		
			//初中高级（正）
			sql.append("SUM(CASE WHEN  (sjg1 = '4') ");
			sql.append("THEN 1 ELSE 0 END) AS lowsjg1,");
			sql.append("SUM(CASE WHEN  (sjg2 = '4') ");
			sql.append("THEN 1 ELSE 0 END) AS lowsjg2,");
			sql.append("SUM(CASE WHEN  (sjg3 = '4') ");
			sql.append("THEN 1 ELSE 0 END) AS lowsjg3,");
			sql.append("SUM(CASE WHEN  (sjg4 = '4') ");
			sql.append("THEN 1 ELSE 0 END) AS lowsjg4,");
			sql.append("SUM(CASE WHEN  (sjg5 = '4') ");
			sql.append("THEN 1 ELSE 0 END) AS lowsjg5,");
			sql.append("SUM(CASE WHEN  (sjg6 = '4') ");
			sql.append("THEN 1 ELSE 0 END) AS lowsjf1,");
			sql.append("SUM(CASE WHEN  (sjf2 = '4') ");
			sql.append("THEN 1 ELSE 0 END) AS lowsjf2,");
					
			sql.append("SUM(CASE WHEN  (sjg1 = '5') ");
			sql.append("THEN 1 ELSE 0 END) AS midsjg1,");
			sql.append("SUM(CASE WHEN  (sjg2 = '5') ");
			sql.append("THEN 1 ELSE 0 END) AS midsjg2,");
			sql.append("SUM(CASE WHEN  (sjg3 = '5') ");
			sql.append("THEN 1 ELSE 0 END) AS midsjg3,");
			sql.append("SUM(CASE WHEN  (sjg4 = '5') ");
			sql.append("THEN 1 ELSE 0 END) AS midsjg4,");
			sql.append("SUM(CASE WHEN  (sjg5 = '5') ");
			sql.append("THEN 1 ELSE 0 END) AS midsjg5,");
			sql.append("SUM(CASE WHEN  (sjg6 = '5') ");
			sql.append("THEN 1 ELSE 0 END) AS midsjf1,");
			sql.append("SUM(CASE WHEN  (sjf2 = '5') ");
			sql.append("THEN 1 ELSE 0 END) AS midsjf2,");
			
			sql.append("SUM(CASE WHEN  (sjg1 = '6') OR (sjg1 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS highsjg1,");
			sql.append("SUM(CASE WHEN  (sjg2 = '6') OR (sjg2 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS highsjg2,");
			sql.append("SUM(CASE WHEN  (sjg3 = '6') OR (sjg3 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS highsjg3,");
			sql.append("SUM(CASE WHEN (sjg4 = '6')  OR (sjg4 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS highsjg4,");
			sql.append("SUM(CASE WHEN (sjg5 = '6')  OR (sjg5 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS highsjg5,");
			sql.append("SUM(CASE WHEN (sjf1 = '6')  OR (sjf1 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS highsjf1,");
			sql.append("SUM(CASE WHEN (sjf2 = '6')  OR (sjf2 = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS highsjf2,");
			//初中高级（负）
			sql.append("SUM(CASE WHEN (sjg1 = '1')  ");
			sql.append("THEN 1 ELSE 0 END) AS flowsjg1,");
			sql.append("SUM(CASE WHEN (sjg2 = '1')  ");
			sql.append("THEN 1 ELSE 0 END) AS flowsjg2,");
			sql.append("SUM(CASE WHEN (sjg3 = '1')  ");
			sql.append("THEN 1 ELSE 0 END) AS flowsjg3,");
			sql.append("SUM(CASE WHEN (sjg4 = '1')  ");
			sql.append("THEN 1 ELSE 0 END) AS flowsjg4,");
			sql.append("SUM(CASE WHEN (sjg5 = '1')  ");
			sql.append("THEN 1 ELSE 0 END) AS flowsjg5,");
			sql.append("SUM(CASE WHEN (sjf1 = '1')  ");
			sql.append("THEN 1 ELSE 0 END) AS flowsjf1,");
			sql.append("SUM(CASE WHEN (sjf2 = '1') ");
			sql.append("THEN 1 ELSE 0 END) AS flowsjf2,");
			
			sql.append("SUM(CASE WHEN (sjg1 = '2')  ");
			sql.append("THEN 1 ELSE 0 END) AS fmidsjg1,");
			sql.append("SUM(CASE WHEN (sjg2 = '2')  ");
			sql.append("THEN 1 ELSE 0 END) AS fmidsjg2,");
			sql.append("SUM(CASE WHEN (sjg3 = '2')  ");
			sql.append("THEN 1 ELSE 0 END) AS fmidsjg3,");
			sql.append("SUM(CASE WHEN (sjg4 = '2')  ");
			sql.append("THEN 1 ELSE 0 END) AS fmidsjg4,");
			sql.append("SUM(CASE WHEN (sjg5 = '2')  ");
			sql.append("THEN 1 ELSE 0 END) AS fmidsjg5,");
			sql.append("SUM(CASE WHEN (sjf1 = '2') ");
			sql.append("THEN 1 ELSE 0 END) AS fmidsjf1,");
			sql.append("SUM(CASE WHEN (sjf2 = '2')  ");
			sql.append("THEN 1 ELSE 0 END) AS fmidsjf2,");
			
			sql.append("SUM(CASE WHEN (sjg1 = '3') OR (sjg1 = '0')  ");
			sql.append("THEN 1 ELSE 0 END) AS fhighsjg1,");
			sql.append("SUM(CASE WHEN (sjg2 = '3') OR (sjg2 = '0')  ");
			sql.append("THEN 1 ELSE 0 END) AS fhighsjg2,");
			sql.append("SUM(CASE WHEN (sjg3 = '3') OR (sjg3 = '0')  ");
			sql.append("THEN 1 ELSE 0 END) AS fhighsjg3,");
			sql.append("SUM(CASE WHEN (sjg4 = '3') OR (sjg4 = '0')  ");
			sql.append("THEN 1 ELSE 0 END) AS fhighsjg4,");
			sql.append("SUM(CASE WHEN (sjg5 = '3') OR (sjg5 = '0')  ");
			sql.append("THEN 1 ELSE 0 END) AS fhighsjg5,");
			sql.append("SUM(CASE WHEN (sjf1 = '3') OR (sjf1 = '0')  ");
			sql.append("THEN 1 ELSE 0 END) AS fhighsjf1,");
			sql.append("SUM(CASE WHEN (sjf2 = '3') OR (sjf2 = '0') ");
			sql.append("THEN 1 ELSE 0 END) AS fhighsjf2");
		
		
		sql.append(" FROM ShuiwenxixxjieguoTjView");	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTime).length() > 0) {
			strstartTime = startTime;
		}
		if (StringUtil.Null2Blank(endTime).length() > 0) {
			strendTime = endTime;
		}
		sql.append(" where (convert(datetime,shijianS,121) between '" + strstartTime
				+ "' and '" + strendTime + "')");	
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		if (null != biaoduan) {
			sql.append(" and biaoduanid=" + biaoduan);
		}
		if (null != xiangmubu) {
			sql.append(" and xiangmubuid=" + xiangmubu);
		}

		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and shebeibianhao = '"+shebeibianhao+"'");
		}
		
		switch (fzlx) {
			case 1:
				sql.append(" group by biaoduanid");
				break;
			case 2:
				sql.append(" group by biaoduanid,xiangmubuid");
				break;
			case 3:
				sql.append(" group by biaoduanid,xiangmubuid,banhezhanminchen");
				break;
		}	
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		String qsql = sql.toString();	
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();
			rs=st.executeQuery(qsql);
			while(rs.next()){
				ShuiwenxixxView shuiwen=new ShuiwenxixxView();
				String bhzmc="";
				switch (fzlx) {
				case 0:
					bhzmc = "全线";
					getpangshu(shuiwen, strstartTime, strendTime, biaoduan, xiangmubu, shebeibianhao, fn, bsid);
					break;
				case 1:
					int bdid = 0;
					try {
						bdid = Integer.parseInt(rs.getString("biaoduanid"));
					} catch (Exception e) {
						logger.debug(e.getMessage());
					}
					if (bdid>0) {
						Biaoduanxinxi bd = bdService.getBeanById(bdid);
						if (null != bd) {
							bhzmc = bd.getBiaoduanminchen();
						}
						getpangshu(shuiwen, strstartTime, strendTime, bdid, xiangmubu, shebeibianhao, fn, bsid);
					}
					break;
				case 2:
					int bdxmbid = 0;
					try {
						bdxmbid = Integer.parseInt(rs.getString("biaoduanid"));
					} catch (Exception e) {
						logger.info(e.getMessage());
					}
					String bdmc ="";
					if (bdxmbid>0) {
						Biaoduanxinxi bd = bdService.getBeanById(bdxmbid);
						if (null != bd) {
							bdmc = bd.getBiaoduanminchen()+"/";
						}
					}
					int xmbid = 0;
					try {
						xmbid = Integer.parseInt(rs.getString("xiangmubuid"));
					} catch (Exception e) {
						logger.info(e.getMessage());
					}
					if (xmbid>0) {
						Xiangmubuxinxi xmb = xmbService.getBeanById(xmbid);
						getpangshu(shuiwen, strstartTime, strendTime, biaoduan, xmbid, shebeibianhao, fn, bsid);
						if (null != xmb) {
							bhzmc = bdmc+xmb.getXiangmubuminchen();
						}
					}
					break;
				case 3:
					int bdbhzid = 0;
					try {
						bdbhzid = Integer.parseInt(rs.getString("biaoduanid"));
					} catch (Exception e) {
						logger.info(e.getMessage());
					}
					String bdbhzmc ="";
					if (bdbhzid>0) {
						Biaoduanxinxi bd = bdService.getBeanById(bdbhzid);
						if (null != bd) {
							bdbhzmc = bd.getBiaoduanminchen()+"/";
						}
					}
					
					int xmbbhzid = 0;
					try {
						xmbbhzid = Integer.parseInt(rs.getString("xiangmubuid"));
					} catch (Exception e) {
						logger.info(e.getMessage());
					}
					String xmbbhzmc = "";
					if (xmbbhzid>0) {
						Xiangmubuxinxi xmb = xmbService.getBeanById(xmbbhzid);
						if (null != xmb) {
							xmbbhzmc = xmb.getXiangmubuminchen()+"/";
						}
					}
					
					bhzmc = bdbhzmc+xmbbhzmc+rs.getString("banhezhanminchen");
					String bhzid="";
					try {
						bhzid =rs.getString("shebeibianhao");
					} catch (Exception e) {
						logger.info(e.getMessage());
					}
					if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
						getpangshu(shuiwen, strstartTime, strendTime, biaoduan, xiangmubu, bhzid, fn, bsid);
					}
					break;
				}	
				shuiwen.setBanhezhanminchen(bhzmc+"预警总数");
				shuiwen.setBeizhu("总正");
				String fangshu = shuiwen.getGlchangliang();
				String pangshu = shuiwen.getPanshu();
				int zongpangshu = 0;
				shuiwen.setAmbegin(rs.getString("cbps"));
				try {
					zongpangshu = Integer.parseInt(shuiwen.getPanshu());
					if (zongpangshu>0) {
						shuiwen.setAmend(String.format("%1$.2f",
							 Double.parseDouble(shuiwen.getAmbegin())*100/zongpangshu));
					} 
				} catch (Exception e) {
					logger.debug(e.getMessage());
				}
				
				shuiwen.setSjgl1(rs.getString("sjg1"));
				shuiwen.setSjgl2(rs.getString("sjg2"));
				shuiwen.setSjgl3(rs.getString("sjg3"));
				shuiwen.setSjgl4(rs.getString("sjg4"));
				shuiwen.setSjgl5(rs.getString("sjg5"));
				shuiwen.setSjfl1(rs.getString("sjf1"));
				shuiwen.setSjfl2(rs.getString("sjf2"));
				_returnValue.add(shuiwen);
				
				shuiwen = new ShuiwenxixxView();		
				shuiwen.setBanhezhanminchen(bhzmc+"预警总数");
				shuiwen.setBeizhu("总负");
				shuiwen.setSjgl1(rs.getString("fsjg1"));
				shuiwen.setSjgl2(rs.getString("fsjg2"));
				shuiwen.setSjgl3(rs.getString("fsjg3"));
				shuiwen.setSjgl4(rs.getString("fsjg4"));
				shuiwen.setSjgl5(rs.getString("fsjg5"));
				shuiwen.setSjfl1(rs.getString("fsjf1"));
				shuiwen.setSjfl2(rs.getString("fsjf2"));
				_returnValue.add(shuiwen);
				
				shuiwen = new ShuiwenxixxView();		
				shuiwen.setBanhezhanminchen(bhzmc+"初级");
				shuiwen.setBeizhu("正初级");
				shuiwen.setGlchangliang(fangshu);
				shuiwen.setPanshu(pangshu);
				shuiwen.setAmbegin(rs.getString("cbps"));
				try {
					if (zongpangshu>0) {
						shuiwen.setAmend(String.format("%1$.2f",Double.parseDouble(shuiwen.getAmbegin())*100/zongpangshu));
					}
				} catch (Exception e) {
					logger.debug(e.getMessage());
				}
				shuiwen.setSjgl1(rs.getString("lowsjg1"));
				shuiwen.setSjgl2(rs.getString("lowsjg2"));
				shuiwen.setSjgl3(rs.getString("lowsjg3"));
				shuiwen.setSjgl4(rs.getString("lowsjg4"));
				shuiwen.setSjgl5(rs.getString("lowsjg5"));
				shuiwen.setSjfl1(rs.getString("lowsjf1"));
				shuiwen.setSjfl2(rs.getString("lowsjf2"));
				_returnValue.add(shuiwen);
				
				shuiwen = new ShuiwenxixxView();	
				shuiwen.setBanhezhanminchen(bhzmc+"初级");
				shuiwen.setBeizhu("负初级");
				shuiwen.setSjgl1(rs.getString("flowsjg1"));
				shuiwen.setSjgl2(rs.getString("flowsjg2"));
				shuiwen.setSjgl3(rs.getString("flowsjg3"));
				shuiwen.setSjgl4(rs.getString("flowsjg4"));
				shuiwen.setSjgl5(rs.getString("flowsjg5"));
				shuiwen.setSjfl1(rs.getString("flowsjf1"));
				shuiwen.setSjfl2(rs.getString("flowsjf2"));
				_returnValue.add(shuiwen);
				
				shuiwen = new ShuiwenxixxView();		
				shuiwen.setBanhezhanminchen(bhzmc+"中级");
				shuiwen.setBeizhu("正中级");
				shuiwen.setGlchangliang(fangshu);
				shuiwen.setPanshu(pangshu);
				shuiwen.setAmbegin(rs.getString("midcbps"));
				try {
					if (zongpangshu>0) {
						shuiwen.setAmend(String.format("%1$.2f",Double.parseDouble(shuiwen.getAmbegin())*100/zongpangshu));
					}
				} catch (Exception e) {
				}
				shuiwen.setSjgl1(rs.getString("midsjg1"));
				shuiwen.setSjgl2(rs.getString("midsjg2"));
				shuiwen.setSjgl3(rs.getString("midsjg3"));
				shuiwen.setSjgl4(rs.getString("midsjg4"));
				shuiwen.setSjgl5(rs.getString("midsjg5"));
				shuiwen.setSjfl1(rs.getString("midsjf1"));
				shuiwen.setSjfl2(rs.getString("midsjf2"));
				_returnValue.add(shuiwen);
				
				shuiwen = new ShuiwenxixxView();
				shuiwen.setBanhezhanminchen(bhzmc+"中级");
				shuiwen.setBeizhu("负中级");
				shuiwen.setSjgl1(rs.getString("fmidsjg1"));
				shuiwen.setSjgl2(rs.getString("fmidsjg2"));
				shuiwen.setSjgl3(rs.getString("fmidsjg3"));
				shuiwen.setSjgl4(rs.getString("fmidsjg4"));
				shuiwen.setSjgl5(rs.getString("fmidsjg5"));
				shuiwen.setSjfl1(rs.getString("fmidsjf1"));
				shuiwen.setSjfl2(rs.getString("fmidsjf2"));
				_returnValue.add(shuiwen);
				
				shuiwen = new ShuiwenxixxView();		
				shuiwen.setBanhezhanminchen(bhzmc+"高级");
				shuiwen.setBeizhu("正高级");
				shuiwen.setGlchangliang(fangshu);
				shuiwen.setPanshu(pangshu);
				shuiwen.setAmbegin(rs.getString("highcbps"));
				try {
					if (zongpangshu>0) {
						shuiwen.setAmend(String.format("%1$.2f",Double.parseDouble(shuiwen.getAmbegin())*100/zongpangshu));
					}
				} catch (Exception e) {
				}
				shuiwen.setSjgl1(rs.getString("highsjg1"));
				shuiwen.setSjgl2(rs.getString("highsjg2"));
				shuiwen.setSjgl3(rs.getString("highsjg3"));
				shuiwen.setSjgl4(rs.getString("highsjg4"));
				shuiwen.setSjgl5(rs.getString("highsjg5"));
				shuiwen.setSjfl1(rs.getString("highsjf1"));
				shuiwen.setSjfl2(rs.getString("highsjf2"));
				_returnValue.add(shuiwen);
				
				shuiwen = new ShuiwenxixxView();		
				shuiwen.setBanhezhanminchen(bhzmc+"高级");
				shuiwen.setBeizhu("负高级");
				shuiwen.setSjgl1(rs.getString("fhighsjg1"));
				shuiwen.setSjgl2(rs.getString("fhighsjg2"));
				shuiwen.setSjgl3(rs.getString("fhighsjg3"));
				shuiwen.setSjgl4(rs.getString("fhighsjg4"));
				shuiwen.setSjgl5(rs.getString("fhighsjg5"));
				shuiwen.setSjfl1(rs.getString("fhighsjf1"));
				shuiwen.setSjfl2(rs.getString("fhighsjf2"));
				_returnValue.add(shuiwen);
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}finally{
			DbJdbcUtil.closeAll(rs, st, con);
		}
		return _returnValue;
	}
	
	public void getpangshu(ShuiwenxixxView shuiwen, String startTime, String endTime,Integer biaoduan, 
			Integer xiangmubu, String bhzid,String fn, int bsid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(glchangliang) AS changliang,COUNT(bianhao) as pangshu");	
		sql.append(" from ShuiwenxixxView");
		sql.append(" where (shijianS between '"+startTime+"' and '"+endTime+"')");
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		if (null != biaoduan) {
			sql.append(" and biaoduanid=" + biaoduan);
		}
		if (null != xiangmubu) {
			sql.append(" and xiangmubuid=" + xiangmubu);
		}

		if (StringUtil.Null2Blank(bhzid).length()>0) {			
			sql.append("and shebeibianhao = '"+bhzid+"'");
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
					shuiwen.setGlchangliang(String.format("%1$.1f",rs.getFloat("changliang")/1000));
					shuiwen.setPanshu(rs.getString("pangshu"));
				}
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			DbJdbcUtil.closeAll(rs, st, con);
		}		
	}
	
	@Override
	public List<ShuiwenxixxView> swzhfxlist(String startTime, String endTime,
			String shebeibianhao, Integer biaoduan, Integer xiangmubu, int cnfxlx, String fn, int bsid) {
		List<ShuiwenxixxView> _returnValue = new ArrayList<ShuiwenxixxView>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(glchangliang) as changliang,COUNT(bianhao) as pangshu");	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar day=Calendar.getInstance(); 
		String nowtime = sdf.format(day.getTime());
		String begintime = sdf.format(day.getTime());
	    switch (cnfxlx) {
			case 1://季度
				sql.append(",year(shijianS) as xa, datename(quarter, shijianS) as xb");
				sql.append(" FROM ShuiwenxixxView");
		    	day.add(Calendar.MONTH, -9);
		    	begintime = sdf.format(day.getTime());
				break;
			case 2://月份
				sql.append(",year(shijianS) as xa, month(shijianS) as xb");
				sql.append(" FROM ShuiwenxixxView");
				day.add(Calendar.MONTH, -3);
		    	begintime = sdf.format(day.getTime());
				break;
			case 3://周
				sql.append(",year(shijianS) as xa, datename(week, shijianS) as xb");
				sql.append(" FROM ShuiwenxixxView");
				day.add(Calendar.WEEK_OF_YEAR, -4);
		    	begintime = sdf.format(day.getTime());
				break;
			case 4://天
				sql.append(",month(shijianS) as xa, day(shijianS) as xb");
				sql.append(" FROM ShuiwenxixxView");
				day.add(Calendar.DATE, -6);
		    	begintime = sdf.format(day.getTime());
				break;		
			default:
				sql.append(",month(shijianS) as xa, day(shijianS) as xb");
				sql.append(" FROM ShuiwenxixxView");
				day.add(Calendar.DATE, -6);
		    	begintime = sdf.format(day.getTime());
				break;
		}
		if(StringUtil.Null2Blank(startTime).length()>0){
			begintime = startTime;
		}
		if(StringUtil.Null2Blank(endTime).length()>0){
			nowtime = endTime;
		}
		
		sql.append(" where (shijianS between '"+begintime+"' and '"+nowtime+"') ");
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
				sql.append(" group by year(shijianS), datename(quarter, shijianS) order by datename(quarter, shijianS)");
				break;
			case 2:
				sql.append(" group by year(shijianS), month(shijianS) order by month(shijianS)");
				break;
			case 3:
				sql.append(" group by year(shijianS), datename(week, shijianS) order by datename(week, shijianS)");
				break;
			case 4://天
				sql.append(" group by year(shijianS), month(shijianS), ");
				sql.append(" day(shijianS) order by year(shijianS),month(shijianS),day(shijianS)");			
				break;		
			default:
				sql.append(" group by year(shijianS), month(shijianS), ");
				sql.append(" day(shijianS) order by year(shijianS),month(shijianS),day(shijianS)");	
				break;
		}	
		
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();			
			if (true) {
				rs = st.executeQuery(sql.toString());
				while (rs.next()) {
					ShuiwenxixxView hv = new ShuiwenxixxView();
					hv.setSmstype(rs.getString("xa"));
					hv.setSendtype(rs.getString("xb"));
					hv.setPanshu(rs.getString("pangshu"));
					switch (cnfxlx) {
						case 1:
							hv.setGlchangliang(String.format("%1$.1f",rs.getDouble("changliang")/1000));
							break;
						case 2:
							hv.setGlchangliang(String.format("%1$.1f",rs.getDouble("changliang")/1000));
							break;
						case 3:
							hv.setGlchangliang(String.format("%1$.1f",rs.getDouble("changliang")/1000));
							break;
						case 4:
							hv.setGlchangliang(String.format("%1$.1f",rs.getDouble("changliang")));
							break;
						default:
							hv.setGlchangliang(String.format("%1$.1f",rs.getDouble("changliang")/1000));
							break;						
					}
					
					getChaobiaops(hv,startTime, endTime, shebeibianhao,
							biaoduan, xiangmubu, cnfxlx, fn, bsid, hv.getSmstype(), hv.getSendtype());
					try {
						if (Double.parseDouble(hv.getPanshu())>0) {
						 hv.setAmend(String.format("%1$.2f",
								 Double.parseDouble(hv.getAmbegin())*100/Double.parseDouble(hv.getPanshu())));
						 hv.setPmend(String.format("%1$.2f",
								 Double.parseDouble(hv.getPmbegin())*100/Double.parseDouble(hv.getPanshu())));
						 hv.setBiaoshi(String.format("%1$.2f",
								 Double.parseDouble(hv.getBeizhu())*100/Double.parseDouble(hv.getPanshu())));
						}
					} catch (Exception e) {
					}
					_returnValue.add(hv);
				}
				
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			DbJdbcUtil.closeAll(rs, st, con);
		}
		
		return _returnValue;
	}
	
	//首页的饼状图
	public ShuiwenxixxView swstatisticsinfo(){
		ShuiwenxixxView _returnValue=new ShuiwenxixxView();;
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		String qsql = "SELECT SUM(glchangliang) AS glchangliang," +
		              "COUNT(bianhao) as pangshu FROM ShuiwenxixxView";		
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();			
			rs = st.executeQuery(qsql);
			if (rs.next()) {
				_returnValue.setPangshu(rs.getString("pangshu"));
				try{
					_returnValue.setGlchangliang(String.format("%1$.1f",rs.getDouble("glchangliang")/1000));
				}catch(Exception ex){}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			DbJdbcUtil.closeAll(rs, st, con);
		}
		
		qsql = "SELECT Count(jieguoid) as cbps From ShuiwenxixxjieguoView";
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
					_returnValue.setChaobiaobfl(String.format("%1$.2f",Double.parseDouble(_returnValue.getChaobiaops())*100/Double.parseDouble(_returnValue.getPangshu())));
				}
			} catch (Exception e) {}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			DbJdbcUtil.closeAll(rs, st, con);
		}
		return _returnValue;
	}
}
