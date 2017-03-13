package com.mss.shtoone.persistence.hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Biaoduanxinxi;
import com.mss.shtoone.domain.LiqingView;
import com.mss.shtoone.domain.Smsinfo;
import com.mss.shtoone.domain.SmsinfoPageMode;
import com.mss.shtoone.domain.SmsinfoView;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.persistence.SmsinfoDAO;
import com.mss.shtoone.service.BiaoduanService;
import com.mss.shtoone.service.XiangmubuService;
import com.mss.shtoone.util.StringUtil;

@Repository
public class SmsinfoHibernateDAO extends GenericHibernateDAO<Smsinfo, Integer> implements SmsinfoDAO {
	private  static Log logger = LogFactory.getLog(SmsinfoHibernateDAO.class);
	
	@Autowired
	private BiaoduanService bdService;
	
	@Autowired
	private XiangmubuService xmbService;
	
	@Override
	public SmsinfoPageMode viewlist(String startTimeOne,String endTimeOne,Integer biaoduan, 
			Integer xiangmubu,String bhzid, String fn, int bsid, int offset, int pagesize) {
		List<SmsinfoView> _returnValue = new ArrayList<SmsinfoView>();
		SmsinfoPageMode pagemode = new SmsinfoPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "SmsinfoView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		String queryCondition = " (convert(datetime,shijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		if (null != biaoduan) {
			queryCondition += " and biaoduanid=" + biaoduan;
		}
		if (null != xiangmubu) {
			queryCondition += " and xiangmubuid=" + xiangmubu;
		}
		if (StringUtil.Null2Blank(bhzid).length()>0) {
			queryCondition += " and shebeibianhao='" + bhzid+"'";
		}
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "smsid");
			cs.setString(4, "smsid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					SmsinfoView hv = new SmsinfoView();
					hv.setSmsid(rs.getInt("smsid"));
					hv.setShijian(rs.getString("shijian"));
					hv.setSuccessphone(rs.getString("successphone"));
					hv.setSuccesscount(rs.getInt("successcount"));
					hv.setFasongcount(rs.getInt("fasongcount"));
					hv.setFasongphone(rs.getString("fasongphone"));				
					hv.setFasongcontent(rs.getString("fasongcontent"));				
					hv.setFasongstatus(rs.getString("fasongstatus"));				
					hv.setReturnmsg(rs.getString("returnmsg"));				
					
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
	public SmsinfoPageMode viewsuccesslist(String startTimeOne,String endTimeOne,Integer biaoduan, 
			Integer xiangmubu, String bhzid,String fn, int bsid, int offset, int pagesize) {
		List<SmsinfoView> _returnValue = new ArrayList<SmsinfoView>();
		SmsinfoPageMode pagemode = new SmsinfoPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "SmsinfoView";	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		String queryCondition = "successcount > '0' and (convert(datetime,shijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		if (null != biaoduan) {
			queryCondition += " and biaoduanid=" + biaoduan;
		}
		if (null != xiangmubu) {
			queryCondition += " and xiangmubuid=" + xiangmubu;
		}
		if (StringUtil.Null2Blank(bhzid).length()>0) {
			queryCondition += " and shebeibianhao='"+bhzid+"'";
		}
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "smsid");
			cs.setString(4, "smsid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					SmsinfoView hv = new SmsinfoView();
					hv.setSmsid(rs.getInt("smsid"));
					hv.setShijian(rs.getString("shijian"));
					hv.setSuccessphone(rs.getString("successphone"));
					hv.setSuccesscount(rs.getInt("successcount"));
					hv.setFasongcount(rs.getInt("fasongcount"));
					hv.setFasongphone(rs.getString("fasongphone"));				
					hv.setFasongcontent(rs.getString("fasongcontent"));				
					hv.setFasongstatus(rs.getString("fasongstatus"));				
					hv.setReturnmsg(rs.getString("returnmsg"));				
					hv.setJianchen(rs.getString("jianchen"));				
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));				
					hv.setOwername(rs.getString("owername"));
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
	public List<LiqingView> lqsmstongji(String startTime, String endTime,
			String shebeibianhao) {
		List<LiqingView> _returnValue = new ArrayList<LiqingView>();
		StringBuffer sql = new StringBuffer();
		sql.append("select banhezhanminchen,Max(id) as id,");
		sql.append("SUM(CASE WHEN (sjysb IS NULL) OR (sjysb = '') ");
		sql.append("THEN '0' ELSE 1 END) AS sjysb,");
		sql.append("SUM(CASE WHEN (sjg1 IS NULL) OR (sjg1 = '') ");
		sql.append("THEN '0' ELSE 1 END) AS sjg1,");
		sql.append("SUM(CASE WHEN (sjg2 IS NULL) OR (sjg2 = '') ");
		sql.append("THEN '0' ELSE 1 END) AS sjg2,");
		sql.append("SUM(CASE WHEN (sjg3 IS NULL) OR (sjg3 = '') ");
		sql.append("THEN '0' ELSE 1 END) AS sjg3,");
		sql.append("SUM(CASE WHEN (sjg4 IS NULL) OR (sjg4 = '') ");
		sql.append("THEN '0' ELSE 1 END) AS sjg4,");
		sql.append("SUM(CASE WHEN (sjg5 IS NULL) OR (sjg5 = '') ");
		sql.append("THEN '0' ELSE 1 END) AS sjg5,");
		sql.append("SUM(CASE WHEN (sjg6 IS NULL) OR (sjg6 = '') ");
		sql.append("THEN '0' ELSE 1 END) AS sjg6,");
		sql.append("SUM(CASE WHEN (sjg7 IS NULL) OR (sjg7 = '') ");
		sql.append("THEN '0' ELSE 1 END) AS sjg7,");
		sql.append("SUM(CASE WHEN (sjf1 IS NULL) OR (sjf1 = '') ");
		sql.append("THEN '0' ELSE 1 END) AS sjf1,");
		sql.append("SUM(CASE WHEN (sjf2 IS NULL) OR (sjf2 = '') ");
		sql.append("THEN '0' ELSE 1 END) AS sjf2,");
		sql.append("SUM(CASE WHEN (sjtjj IS NULL) OR (sjtjj = '') ");
		sql.append("THEN '0' ELSE 1 END) AS sjtjj,");
		sql.append("SUM(CASE WHEN (lqwd IS NULL) OR (lqwd = '') ");
		sql.append("THEN '0' ELSE 1 END) AS lqwd,");
		sql.append("SUM(CASE WHEN (glwd IS NULL) OR (glwd = '') ");
		sql.append("THEN '0' ELSE 1 END) AS glwd,");
		sql.append("SUM(CASE WHEN (beiy1 IS NULL) OR (beiy1 = '') ");
		sql.append("THEN '0' ELSE 1 END) AS beiy1,");
		sql.append("SUM(CASE WHEN (sjysb = '1') OR (sjysb = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowsjysb,");
		sql.append("SUM(CASE WHEN (sjg1 = '1') OR (sjg1 = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowsjg1,");
		sql.append("SUM(CASE WHEN (sjg2 = '1') OR (sjg2 = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowsjg2,");
		sql.append("SUM(CASE WHEN (sjg3 = '1') OR (sjg3 = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowsjg3,");
		sql.append("SUM(CASE WHEN (sjg4 = '1') OR (sjg4 = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowsjg4,");
		sql.append("SUM(CASE WHEN (sjg5 = '1') OR (sjg5 = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowsjg5,");
		sql.append("SUM(CASE WHEN (sjg6 = '1') OR (sjg6 = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowsjg6,");
		sql.append("SUM(CASE WHEN (sjg7 = '1') OR (sjg7 = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowsjg7,");
		sql.append("SUM(CASE WHEN (sjf1 = '1') OR (sjf1 = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowsjf1,");
		sql.append("SUM(CASE WHEN (sjf2 = '1') OR (sjf2 = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowsjf2,");
		sql.append("SUM(CASE WHEN (sjtjj = '1') OR (sjtjj = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowsjtjj,");
		sql.append("SUM(CASE WHEN (lqwd = '1') OR (lqwd = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowlqwd,");
		sql.append("SUM(CASE WHEN (glwd = '1') OR (glwd = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowglwd,");
		sql.append("SUM(CASE WHEN (beiy1 = '1') OR (beiy1 = '4') ");
		sql.append("THEN '1' ELSE 0 END) AS lowbeiy1,");
		sql.append("SUM(CASE WHEN (sjysb = '2') OR (sjysb = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midsjysb,");
		sql.append("SUM(CASE WHEN (sjg1 = '2') OR (sjg1 = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midsjg1,");
		sql.append("SUM(CASE WHEN (sjg2 = '2') OR (sjg2 = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midsjg2,");
		sql.append("SUM(CASE WHEN (sjg3 = '2') OR (sjg3 = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midsjg3,");
		sql.append("SUM(CASE WHEN (sjg4 = '2') OR (sjg4 = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midsjg4,");
		sql.append("SUM(CASE WHEN (sjg5 = '2') OR (sjg5 = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midsjg5,");
		sql.append("SUM(CASE WHEN (sjg6 = '2') OR (sjg6 = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midsjg6,");
		sql.append("SUM(CASE WHEN (sjg7 = '2') OR (sjg7 = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midsjg7,");
		sql.append("SUM(CASE WHEN (sjf1 = '2') OR (sjf1 = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midsjf1,");
		sql.append("SUM(CASE WHEN (sjf2 = '2') OR (sjf2 = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midsjf2,");
		sql.append("SUM(CASE WHEN (sjtjj = '2') OR (sjtjj = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midsjtjj,");
		sql.append("SUM(CASE WHEN (lqwd = '2') OR (lqwd = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midlqwd,");
		sql.append("SUM(CASE WHEN (glwd = '2') OR (glwd = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midglwd,");
		sql.append("SUM(CASE WHEN (beiy1 = '2') OR (beiy1 = '5') ");
		sql.append("THEN '1' ELSE 0 END) AS midbeiy1,");
		sql.append("SUM(CASE WHEN (sjysb = '3') OR (sjysb = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highsjysb,");
		sql.append("SUM(CASE WHEN (sjg1 = '3') OR (sjg1 = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highsjg1,");
		sql.append("SUM(CASE WHEN (sjg2 = '3') OR (sjg2 = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highsjg2,");
		sql.append("SUM(CASE WHEN (sjg3 = '3') OR (sjg3 = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highsjg3,");
		sql.append("SUM(CASE WHEN (sjg4 = '3') OR (sjg4 = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highsjg4,");
		sql.append("SUM(CASE WHEN (sjg5 = '3') OR (sjg5 = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highsjg5,");
		sql.append("SUM(CASE WHEN (sjg6 = '3') OR (sjg6 = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highsjg6,");
		sql.append("SUM(CASE WHEN (sjg7 = '3') OR (sjg7 = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highsjg7,");
		sql.append("SUM(CASE WHEN (sjf1 = '3') OR (sjf1 = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highsjf1,");
		sql.append("SUM(CASE WHEN (sjf2 = '3') OR (sjf2 = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highsjf2,");
		sql.append("SUM(CASE WHEN (sjtjj = '3') OR (sjtjj = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highsjtjj,");
		sql.append("SUM(CASE WHEN (lqwd = '3') OR (lqwd ='6') ");
		sql.append("THEN '1' ELSE 0 END) AS highlqwd,");
		sql.append("SUM(CASE WHEN (glwd = '3') OR (glwd = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highglwd,");
		sql.append("SUM(CASE WHEN (beiy1 = '3') OR (beiy1 = '6') ");
		sql.append("THEN '1' ELSE 0 END) AS highbeiy1");
		sql.append(" FROM LiqingxixxjieguoView");	
		sql.append(" where 1=1 ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());	

		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append("and shebeibianhao = '"+shebeibianhao+"'");
		}

		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (jieguoshijian between '"+startTime+"' and '"+endTime+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()==0)
		{
			sql.append(" and (jieguoshijian between '"+startTime+"' and '"+newDate+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()==0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (jieguoshijian between '1900-01-01' and '"+endTime+"')");
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
					LiqingView lqv = new LiqingView();		
					lqv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					lqv.setSjg1(rs.getString("sjg1"));
					lqv.setSjg2(rs.getString("sjg2"));
					lqv.setSjg3(rs.getString("sjg3"));
					lqv.setSjg4(rs.getString("sjg4"));
					lqv.setSjg5(rs.getString("sjg5"));
					lqv.setSjg6(rs.getString("sjg6"));
					lqv.setSjg7(rs.getString("sjg7"));
					lqv.setSjf1(rs.getString("sjf1"));
					lqv.setSjf2(rs.getString("sjf2"));
					lqv.setSjtjj(rs.getString("sjtjj"));
					lqv.setSjysb(rs.getString("sjysb"));
					lqv.setGlwd(rs.getString("glwd"));
					lqv.setLqwd(rs.getString("lqwd"));
					lqv.setBeiy1(rs.getString("beiy1"));
					getpangshu(lqv, startTime, endTime, rs.getInt("id"));
					_returnValue.add(lqv);
					lqv = new LiqingView();		
					lqv.setBanhezhanminchen(rs.getString("banhezhanminchen")+"一级报警");
					lqv.setSjg1(rs.getString("lowsjg1"));
					lqv.setSjg2(rs.getString("lowsjg2"));
					lqv.setSjg3(rs.getString("lowsjg3"));
					lqv.setSjg4(rs.getString("lowsjg4"));
					lqv.setSjg5(rs.getString("lowsjg5"));
					lqv.setSjg6(rs.getString("lowsjg6"));
					lqv.setSjg7(rs.getString("lowsjg7"));
					lqv.setSjf1(rs.getString("lowsjf1"));
					lqv.setSjf2(rs.getString("lowsjf2"));
					lqv.setSjtjj(rs.getString("lowsjtjj"));
					lqv.setSjysb(rs.getString("lowsjysb"));
					lqv.setGlwd(rs.getString("lowglwd"));
					lqv.setLqwd(rs.getString("lowlqwd"));
					lqv.setBeiy1(rs.getString("lowbeiy1"));
					_returnValue.add(lqv);
					lqv = new LiqingView();		
					lqv.setBanhezhanminchen(rs.getString("banhezhanminchen")+"二级报警");
					lqv.setSjg1(rs.getString("midsjg1"));
					lqv.setSjg2(rs.getString("midsjg2"));
					lqv.setSjg3(rs.getString("midsjg3"));
					lqv.setSjg4(rs.getString("midsjg4"));
					lqv.setSjg5(rs.getString("midsjg5"));
					lqv.setSjg6(rs.getString("midsjg6"));
					lqv.setSjg7(rs.getString("midsjg7"));
					lqv.setSjf1(rs.getString("midsjf1"));
					lqv.setSjf2(rs.getString("midsjf2"));
					lqv.setSjtjj(rs.getString("midsjtjj"));
					lqv.setSjysb(rs.getString("midsjysb"));
					lqv.setGlwd(rs.getString("midglwd"));
					lqv.setLqwd(rs.getString("midlqwd"));
					lqv.setBeiy1(rs.getString("midbeiy1"));
					_returnValue.add(lqv);
					lqv = new LiqingView();		
					lqv.setBanhezhanminchen(rs.getString("banhezhanminchen")+"三级报警");
					lqv.setSjg1(rs.getString("highsjg1"));
					lqv.setSjg2(rs.getString("highsjg2"));
					lqv.setSjg3(rs.getString("highsjg3"));
					lqv.setSjg4(rs.getString("highsjg4"));
					lqv.setSjg5(rs.getString("highsjg5"));
					lqv.setSjg6(rs.getString("highsjg6"));
					lqv.setSjg7(rs.getString("highsjg7"));
					lqv.setSjf1(rs.getString("highsjf1"));
					lqv.setSjf2(rs.getString("highsjf2"));
					lqv.setSjtjj(rs.getString("highsjtjj"));
					lqv.setSjysb(rs.getString("highsjysb"));
					lqv.setGlwd(rs.getString("highglwd"));
					lqv.setLqwd(rs.getString("highlqwd"));
					lqv.setBeiy1(rs.getString("highbeiy1"));
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
	
	public void getpangshu(LiqingView lqv, String startTime, String endTime,
			Integer shebeibianhao) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS changliang,COUNT(bianhao) as pangshu");	
		sql.append(" from LiqingView where 1=1 ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());	

		if (null != shebeibianhao && shebeibianhao>0) {			
			sql.append("and id = '"+shebeibianhao+"'");
		}

		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (caijishijian between '"+startTime+"' and '"+endTime+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()==0)
		{
			sql.append(" and (caijishijian between '"+startTime+"' and '"+newDate+"')");
		}
		if(StringUtil.Null2Blank(startTime).length()==0 && StringUtil.Null2Blank(endTime).length()>0)
		{
			sql.append(" and (caijishijian between '1900-01-01' and '"+endTime+"')");
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
					lqv.setBeiy2(rs.getString("changliang"));
					lqv.setBeiy3(rs.getString("pangshu"));
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
	
	//预警统计
	@Override
	public List<LiqingView> smstongji(String startTimeOne,String endTimeOne,Integer biaoduan, 
			Integer xiangmubu,String bhzid,String fn, int bsid, int fzlx, int jbsj) {
		List<LiqingView> _returnValue = new ArrayList<LiqingView>();
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
		if (jbsj == 0) {
			//总
			sql.append("SUM(CASE WHEN (sjysb is not null) ");
			sql.append("OR (sjg1 is not null) ");
			sql.append("OR (sjg2 is not null) ");
			sql.append("OR (sjg3 is not null) ");
			sql.append("OR (sjg4 is not null) ");
			sql.append("OR (sjg5 is not null) ");
			sql.append("OR (sjg6 is not null) ");
			sql.append("OR (sjg7 is not null) ");
			sql.append("OR (sjf1 is not null) ");
			sql.append("OR (sjf2 is not null) ");
			sql.append("OR (sjlq is not null) ");
			sql.append("OR (sjtjj is not null) ");
			sql.append("THEN 1 ELSE 0 END) AS cbps,");
			
			sql.append("SUM(CASE WHEN (sjysb = '3') OR (sjysb = '6') ");
			sql.append("OR (sjg1 = '3') OR (sjg1 = '6') ");
			sql.append("OR (sjg2 = '3') OR (sjg2 = '6') ");
			sql.append("OR (sjg3 = '3') OR (sjg3 = '6') ");
			sql.append("OR (sjg4 = '3') OR (sjg4 = '6') ");
			sql.append("OR (sjg5 = '3') OR (sjg5 = '6') ");
			sql.append("OR (sjg6 = '3') OR (sjg6 = '6') ");
			sql.append("OR (sjg7 = '3') OR (sjg7 = '6') ");
			sql.append("OR (sjf1 = '3') OR (sjf1 = '6') ");
			sql.append("OR (sjf2 = '3') OR (sjf2 = '6') ");
			sql.append("OR (sjlq = '3') OR (sjlq = '6') ");
			sql.append("OR (sjtjj = '3') OR (sjtjj = '6') ");
			sql.append("OR (sjysb = '2') OR (sjysb = '5') ");
			sql.append("OR (sjg1 = '2') OR (sjg1 = '5') ");
			sql.append("OR (sjg2 = '2') OR (sjg2 = '5') ");
			sql.append("OR (sjg3 = '2') OR (sjg3 = '5') ");
			sql.append("OR (sjg4 = '2') OR (sjg4 = '5') ");
			sql.append("OR (sjg5 = '2') OR (sjg5 = '5') ");
			sql.append("OR (sjg6 = '2') OR (sjg6 = '5') ");
			sql.append("OR (sjg7 = '2') OR (sjg7 = '5') ");
			sql.append("OR (sjf1 = '2') OR (sjf1 = '5') ");
			sql.append("OR (sjf2 = '2') OR (sjf2 = '5') ");
			sql.append("OR (sjlq = '2') OR (sjlq = '5') ");
			sql.append("OR (sjtjj = '2') OR (sjtjj = '5') ");
			sql.append("OR (sjysb = '0') OR (sjysb = '7') ");
			sql.append("OR (sjg1 = '0') OR (sjg1 = '7') ");
			sql.append("OR (sjg2 = '0') OR (sjg2 = '7') ");
			sql.append("OR (sjg3 = '0') OR (sjg3 = '7') ");
			sql.append("OR (sjg4 = '0') OR (sjg4 = '7') ");
			sql.append("OR (sjg5 = '0') OR (sjg5 = '7') ");
			sql.append("OR (sjg6 = '0') OR (sjg6 = '7') ");
			sql.append("OR (sjg7 = '0') OR (sjg7 = '7') ");
			sql.append("OR (sjf1 = '0') OR (sjf1 = '7') ");
			sql.append("OR (sjf2 = '0') OR (sjf2 = '7') ");
			sql.append("OR (sjlq = '0') OR (sjlq = '7') ");
			sql.append("OR (sjtjj = '0') OR (sjtjj = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS midcbps,");
			
			sql.append("SUM(CASE WHEN (sjysb = '3') OR (sjysb = '6') OR (sjysb = '0') OR (sjysb = '7') ");
			sql.append("OR (sjg1 = '3') OR (sjg1 = '6') OR (sjg1 = '0') OR (sjg1 = '7') ");
			sql.append("OR (sjg2 = '3') OR (sjg2 = '6') OR (sjg2 = '0') OR (sjg2 = '7') ");
			sql.append("OR (sjg3 = '3') OR (sjg3 = '6') OR (sjg3 = '0') OR (sjg3 = '7') ");
			sql.append("OR (sjg4 = '3') OR (sjg4 = '6') OR (sjg4 = '0') OR (sjg4 = '7') ");
			sql.append("OR (sjg5 = '3') OR (sjg5 = '6') OR (sjg5 = '0') OR (sjg5 = '7') ");
			sql.append("OR (sjg6 = '3') OR (sjg6 = '6') OR (sjg6 = '0') OR (sjg6 = '7') ");
			sql.append("OR (sjg7 = '3') OR (sjg7 = '6') OR (sjg7 = '0') OR (sjg7 = '7') ");
			sql.append("OR (sjf1 = '3') OR (sjf1 = '6') OR (sjf1 = '0') OR (sjf1 = '7') ");
			sql.append("OR (sjf2 = '3') OR (sjf2 = '6') OR (sjf2 = '0') OR (sjf2 = '7') ");
			sql.append("OR (sjlq = '3') OR (sjlq = '6') OR (sjlq = '0') OR (sjlq = '7') ");
			sql.append("OR (sjtjj = '3') OR (sjtjj = '6') OR (sjtjj = '0') OR (sjtjj = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS highcbps,");
		} else {
			sql.append("SUM(CASE WHEN (sjysb is not null) ");
			sql.append("OR (sjg1 is not null) ");
			sql.append("OR (sjg2 is not null) ");
			sql.append("OR (sjg3 is not null) ");
			sql.append("OR (sjg4 is not null) ");
			sql.append("OR (sjg5 is not null) ");
			sql.append("OR (sjg6 is not null) ");
			sql.append("OR (sjg7 is not null) ");
			sql.append("OR (sjf1 is not null) ");
			sql.append("OR (sjf2 is not null) ");
			sql.append("OR (sjlq is not null) ");
			sql.append("OR (sjtjj is not null) ");
			sql.append("THEN 1 ELSE 0 END) AS cbps,");
			
			sql.append("SUM(CASE WHEN (sjysb = '3') OR (sjysb = '6') ");
			sql.append("OR (sjg1 = '3') OR (sjg1 = '6') ");
			sql.append("OR (sjg2 = '3') OR (sjg2 = '6') ");
			sql.append("OR (sjg3 = '3') OR (sjg3 = '6') ");
			sql.append("OR (sjg4 = '3') OR (sjg4 = '6') ");
			sql.append("OR (sjg5 = '3') OR (sjg5 = '6') ");
			sql.append("OR (sjg6 = '3') OR (sjg6 = '6') ");
			sql.append("OR (sjg7 = '3') OR (sjg7 = '6') ");
			sql.append("OR (sjf1 = '3') OR (sjf1 = '6') ");
			sql.append("OR (sjf2 = '3') OR (sjf2 = '6') ");
			sql.append("OR (sjlq = '3') OR (sjlq = '6') ");
			sql.append("OR (sjtjj = '3') OR (sjtjj = '6') ");
			sql.append("OR (sjysb = '2') OR (sjysb = '5') ");
			sql.append("OR (sjg1 = '2') OR (sjg1 = '5') ");
			sql.append("OR (sjg2 = '2') OR (sjg2 = '5') ");
			sql.append("OR (sjg3 = '2') OR (sjg3 = '5') ");
			sql.append("OR (sjg4 = '2') OR (sjg4 = '5') ");
			sql.append("OR (sjg5 = '2') OR (sjg5 = '5') ");
			sql.append("OR (sjg6 = '2') OR (sjg6 = '5') ");
			sql.append("OR (sjg7 = '2') OR (sjg7 = '5') ");
			sql.append("OR (sjf1 = '2') OR (sjf1 = '5') ");
			sql.append("OR (sjf2 = '2') OR (sjf2 = '5') ");
			sql.append("OR (sjlq = '2') OR (sjlq = '5') ");
			sql.append("OR (sjtjj = '2') OR (sjtjj = '5') ");
			sql.append("OR (sjysb = '0') OR (sjysb = '7') ");
			sql.append("OR (sjg1 = '0') OR (sjg1 = '7') ");
			sql.append("OR (sjg2 = '0') OR (sjg2 = '7') ");
			sql.append("OR (sjg3 = '0') OR (sjg3 = '7') ");
			sql.append("OR (sjg4 = '0') OR (sjg4 = '7') ");
			sql.append("OR (sjg5 = '0') OR (sjg5 = '7') ");
			sql.append("OR (sjg6 = '0') OR (sjg6 = '7') ");
			sql.append("OR (sjg7 = '0') OR (sjg7 = '7') ");
			sql.append("OR (sjf1 = '0') OR (sjf1 = '7') ");
			sql.append("OR (sjf2 = '0') OR (sjf2 = '7') ");
			sql.append("OR (sjlq = '0') OR (sjlq = '7') ");
			sql.append("OR (sjtjj = '0') OR (sjtjj = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS midcbps,");
			
			sql.append("SUM(CASE WHEN (sjysb = '3') OR (sjysb = '6') OR (sjysb = '0') OR (sjysb = '7') ");
			sql.append("OR (sjg1 = '3') OR (sjg1 = '6') OR (sjg1 = '0') OR (sjg1 = '7') ");
			sql.append("OR (sjg2 = '3') OR (sjg2 = '6') OR (sjg2 = '0') OR (sjg2 = '7') ");
			sql.append("OR (sjg3 = '3') OR (sjg3 = '6') OR (sjg3 = '0') OR (sjg3 = '7') ");
			sql.append("OR (sjg4 = '3') OR (sjg4 = '6') OR (sjg4 = '0') OR (sjg4 = '7') ");
			sql.append("OR (sjg5 = '3') OR (sjg5 = '6') OR (sjg5 = '0') OR (sjg5 = '7') ");
			sql.append("OR (sjg6 = '3') OR (sjg6 = '6') OR (sjg6 = '0') OR (sjg6 = '7') ");
			sql.append("OR (sjg7 = '3') OR (sjg7 = '6') OR (sjg7 = '0') OR (sjg7 = '7') ");
			sql.append("OR (sjf1 = '3') OR (sjf1 = '6') OR (sjf1 = '0') OR (sjf1 = '7') ");
			sql.append("OR (sjf2 = '3') OR (sjf2 = '6') OR (sjf2 = '0') OR (sjf2 = '7') ");
			sql.append("OR (sjlq = '3') OR (sjlq = '6') OR (sjlq = '0') OR (sjlq = '7') ");
			sql.append("OR (sjtjj = '3') OR (sjtjj = '6') OR (sjtjj = '0') OR (sjtjj = '7') ");
			sql.append("THEN 1 ELSE 0 END) AS highcbps,");
		}
		//总（正）
		sql.append("SUM(CASE WHEN (sjysb = '4') OR (sjysb = '5') OR (sjysb = '6') OR (sjysb = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS sjysb,");
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
		sql.append("SUM(CASE WHEN  (sjg6 = '4') OR (sjg6 = '5') OR (sjg6 = '6') OR (sjg6 = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS sjg6,");
		sql.append("SUM(CASE WHEN  (sjg7 = '4') OR (sjg7 = '5') OR (sjg7 = '6') OR (sjg7 = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS sjg7,");
		sql.append("SUM(CASE WHEN  (sjf1 = '4') OR (sjf1 = '5') OR (sjf1 = '6') OR (sjf1 = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS sjf1,");
		sql.append("SUM(CASE WHEN  (sjf2 = '4') OR (sjf2 = '5') OR (sjf2 = '6') OR (sjf2 = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS sjf2,");
		sql.append("SUM(CASE WHEN  (sjlq = '4') OR (sjlq = '5') OR (sjlq = '6') OR (sjlq = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS sjlq,");
		sql.append("SUM(CASE WHEN  (sjtjj = '4') OR (sjtjj = '5')  OR (sjtjj = '6') OR (sjtjj = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS sjtjj,");
		
		//总（负）
		sql.append("SUM(CASE WHEN (sjysb = '0') OR (sjysb = '1') OR (sjysb = '2') OR (sjysb = '3') ");
		sql.append("THEN 1 ELSE 0 END) AS fsjysb,");
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
		sql.append("SUM(CASE WHEN  (sjg6 = '0') OR (sjg6 = '1') OR (sjg6 = '2') OR (sjg6 = '3') ");
		sql.append("THEN 1 ELSE 0 END) AS fsjg6,");
		sql.append("SUM(CASE WHEN  (sjg7 = '0') OR (sjg7 = '1') OR (sjg7 = '2') OR (sjg7 = '3') ");
		sql.append("THEN 1 ELSE 0 END) AS fsjg7,");
		sql.append("SUM(CASE WHEN  (sjf1 = '0') OR (sjf1 = '1') OR (sjf1 = '2')  OR (sjf1 = '3') ");
		sql.append("THEN 1 ELSE 0 END) AS fsjf1,");
		sql.append("SUM(CASE WHEN  (sjf2 = '0') OR (sjf2 = '1') OR (sjf2 = '2') OR (sjf2 = '3') ");
		sql.append("THEN 1 ELSE 0 END) AS fsjf2,");
		sql.append("SUM(CASE WHEN  (sjlq = '0') OR (sjlq = '1') OR (sjlq = '2') OR (sjlq = '3') ");
		sql.append("THEN 1 ELSE 0 END) AS fsjlq,");
		sql.append("SUM(CASE WHEN  (sjtjj = '0') OR (sjtjj = '1')  OR (sjtjj = '2') OR (sjtjj = '3') ");
		sql.append("THEN 1 ELSE 0 END) AS fsjtjj,");		
		//初中高级（正）
		sql.append("SUM(CASE WHEN  (sjysb = '4') ");
		sql.append("THEN 1 ELSE 0 END) AS lowsjysb,");
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
		sql.append("THEN 1 ELSE 0 END) AS lowsjg6,");
		sql.append("SUM(CASE WHEN (sjg7 = '4') ");
		sql.append("THEN 1 ELSE 0 END) AS lowsjg7,");
		sql.append("SUM(CASE WHEN  (sjf1 = '4') ");
		sql.append("THEN 1 ELSE 0 END) AS lowsjf1,");
		sql.append("SUM(CASE WHEN  (sjf2 = '4') ");
		sql.append("THEN 1 ELSE 0 END) AS lowsjf2,");
		sql.append("SUM(CASE WHEN  (sjlq = '4') ");
		sql.append("THEN 1 ELSE 0 END) AS lowsjlq,");
		sql.append("SUM(CASE WHEN  (sjtjj = '4') ");
		sql.append("THEN 1 ELSE 0 END) AS lowsjtjj,");
				
		sql.append("SUM(CASE WHEN  (sjysb = '5') ");
		sql.append("THEN 1 ELSE 0 END) AS midsjysb,");
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
		sql.append("THEN 1 ELSE 0 END) AS midsjg6,");
		sql.append("SUM(CASE WHEN  (sjg7 = '5') ");
		sql.append("THEN 1 ELSE 0 END) AS midsjg7,");
		sql.append("SUM(CASE WHEN  (sjf1 = '5') ");
		sql.append("THEN 1 ELSE 0 END) AS midsjf1,");
		sql.append("SUM(CASE WHEN  (sjf2 = '5') ");
		sql.append("THEN 1 ELSE 0 END) AS midsjf2,");
		sql.append("SUM(CASE WHEN  (sjlq = '5') ");
		sql.append("THEN 1 ELSE 0 END) AS midsjlq,");
		sql.append("SUM(CASE WHEN (sjtjj = '5') ");
		sql.append("THEN 1 ELSE 0 END) AS midsjtjj,");
		
		sql.append("SUM(CASE WHEN (sjysb = '6') OR  (sjysb = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS highsjysb,");
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
		sql.append("SUM(CASE WHEN (sjg6 = '6')  OR (sjg6 = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS highsjg6,");
		sql.append("SUM(CASE WHEN (sjg7 = '6')  OR (sjg7 = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS highsjg7,");
		sql.append("SUM(CASE WHEN (sjf1 = '6')  OR (sjf1 = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS highsjf1,");
		sql.append("SUM(CASE WHEN (sjf2 = '6')  OR (sjf2 = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS highsjf2,");
		sql.append("SUM(CASE WHEN (sjlq = '6')  OR (sjlq = '7') ");
		sql.append("THEN 1 ELSE 0 END) AS highsjlq,");
		sql.append("SUM(CASE WHEN (sjtjj ='6')  OR (sjtjj ='7') ");
		sql.append("THEN 1 ELSE 0 END) AS highsjtjj,");
		//初中高级（负）
		sql.append("SUM(CASE WHEN (sjysb = '1')  ");
		sql.append("THEN 1 ELSE 0 END) AS flowsjysb,");
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
		sql.append("SUM(CASE WHEN (sjg6 = '1')  ");
		sql.append("THEN 1 ELSE 0 END) AS flowsjg6,");
		sql.append("SUM(CASE WHEN (sjg7 = '1')  ");
		sql.append("THEN 1 ELSE 0 END) AS flowsjg7,");
		sql.append("SUM(CASE WHEN (sjf1 = '1')  ");
		sql.append("THEN 1 ELSE 0 END) AS flowsjf1,");
		sql.append("SUM(CASE WHEN (sjf2 = '1') ");
		sql.append("THEN 1 ELSE 0 END) AS flowsjf2,");
		sql.append("SUM(CASE WHEN (sjlq = '1') ");
		sql.append("THEN 1 ELSE 0 END) AS flowsjlq,");
		sql.append("SUM(CASE WHEN (sjtjj = '1')  ");
		sql.append("THEN 1 ELSE 0 END) AS flowsjtjj,");
		
		sql.append("SUM(CASE WHEN (sjysb = '2')  ");
		sql.append("THEN 1 ELSE 0 END) AS fmidsjysb,");
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
		sql.append("SUM(CASE WHEN (sjg6 = '2')  ");
		sql.append("THEN 1 ELSE 0 END) AS fmidsjg6,");
		sql.append("SUM(CASE WHEN (sjg7 = '2')  ");
		sql.append("THEN 1 ELSE 0 END) AS fmidsjg7,");
		sql.append("SUM(CASE WHEN (sjf1 = '2') ");
		sql.append("THEN 1 ELSE 0 END) AS fmidsjf1,");
		sql.append("SUM(CASE WHEN (sjf2 = '2')  ");
		sql.append("THEN 1 ELSE 0 END) AS fmidsjf2,");
		sql.append("SUM(CASE WHEN (sjlq = '2') ");
		sql.append("THEN 1 ELSE 0 END) AS fmidsjlq,");
		sql.append("SUM(CASE WHEN (sjtjj = '2') ");
		sql.append("THEN 1 ELSE 0 END) AS fmidsjtjj,");
		
		sql.append("SUM(CASE WHEN (sjysb = '3') OR (sjysb = '0')  ");
		sql.append("THEN 1 ELSE 0 END) AS fhighsjysb,");
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
		sql.append("SUM(CASE WHEN (sjg6 = '3') OR (sjg6 = '0')  ");
		sql.append("THEN 1 ELSE 0 END) AS fhighsjg6,");
		sql.append("SUM(CASE WHEN (sjg7 = '3') OR (sjg7 = '0')  ");
		sql.append("THEN 1 ELSE 0 END) AS fhighsjg7,");
		sql.append("SUM(CASE WHEN (sjf1 = '3') OR (sjf1 = '0')  ");
		sql.append("THEN 1 ELSE 0 END) AS fhighsjf1,");
		sql.append("SUM(CASE WHEN (sjf2 = '3') OR (sjf2 = '0') ");
		sql.append("THEN 1 ELSE 0 END) AS fhighsjf2,");
		sql.append("SUM(CASE WHEN (sjlq = '3') OR (sjlq = '0')  ");
		sql.append("THEN 1 ELSE 0 END) AS fhighsjlq,");
		sql.append("SUM(CASE WHEN (sjtjj = '3') OR (sjtjj = '0')  ");
		sql.append("THEN 1 ELSE 0 END) AS fhighsjtjj ");
		
		sql.append(" FROM LiqingxixxjieguoTjView");	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		sql.append(" where (convert(datetime,shijian,121) between '" + strstartTime
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

		if (StringUtil.Null2Blank(bhzid).length()>0) {			
			sql.append(" and shebeibianhao = '"+bhzid+"'");
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
	
			if (true) {
				rs = st.executeQuery(qsql);
				while (rs.next()) {
					LiqingView hv = new LiqingView();
					String bhzmc="";
					switch (fzlx) {
					case 0:
						bhzmc = "全线";
						getpangshu(hv, strstartTime, strendTime, biaoduan, xiangmubu, bhzid, fn, bsid);
						break;
					case 1:
						int bdid = 0;
						try {
							bdid = Integer.parseInt(rs.getString("biaoduanid"));
						} catch (Exception e) {
							// TODO: handle exception
						}
						if (bdid>0) {
							Biaoduanxinxi bd = bdService.getBeanById(bdid);
							if (null != bd) {
								bhzmc = bd.getBiaoduanminchen();
							}
							getpangshu(hv, strstartTime, strendTime, bdid, xiangmubu, bhzid, fn, bsid);
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
							getpangshu(hv, strstartTime, strendTime, biaoduan, xmbid, bhzid, fn, bsid);
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
						String shebeibianhao="";
						try {
							shebeibianhao =rs.getString("shebeibianhao");
						} catch (Exception e) {
							logger.info(e.getMessage());
						}
						if (StringUtil.Null2Blank(shebeibianhao).length()>0) {
							getpangshu(hv, strstartTime, strendTime, biaoduan, xiangmubu, shebeibianhao, fn, bsid);
						}
						break;
					}	
					hv.setBanhezhanminchen(bhzmc+"预警总数");
					hv.setBeizhu("总正");
					String fangshu = hv.getChangliang();
					String pangshu = hv.getPangshu();
					int zongpangshu = 0;
					hv.setAmbegin(rs.getString("cbps"));
					try {
						zongpangshu = Integer.parseInt(hv.getPangshu());
						if (zongpangshu>0) {
						 hv.setAmend(String.format("%1$.2f",
								 Double.parseDouble(hv.getAmbegin())*100/zongpangshu));
						} 
					} catch (Exception e) {
					}
					sql.append("SUM(CASE WHEN (sjysb = '1')  ");
					sql.append("THEN 1 ELSE 0 END) AS flowsjysb,");
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
					sql.append("SUM(CASE WHEN (sjg6 = '1')  ");
					sql.append("THEN 1 ELSE 0 END) AS flowsjg6,");
					sql.append("SUM(CASE WHEN (sjg7 = '1')  ");
					sql.append("THEN 1 ELSE 0 END) AS flowsjg7,");
					sql.append("SUM(CASE WHEN (sjf1 = '1')  ");
					sql.append("THEN 1 ELSE 0 END) AS flowsjf1,");
					sql.append("SUM(CASE WHEN (sjf2 = '1') ");
					sql.append("THEN 1 ELSE 0 END) AS flowsjf2,");
					sql.append("SUM(CASE WHEN (sjlq = '1') ");
					sql.append("THEN 1 ELSE 0 END) AS flowsjlq,");
					sql.append("SUM(CASE WHEN (sjtjj = '1')  ");
					sql.append("THEN 1 ELSE 0 END) AS flowsjtjj,");
					
					hv.setSjysb(rs.getString("sjysb"));
					hv.setSjg1(rs.getString("sjg1"));
					hv.setSjg2(rs.getString("sjg2"));
					hv.setSjg3(rs.getString("sjg3"));
					hv.setSjg4(rs.getString("sjg4"));
					hv.setSjg5(rs.getString("sjg5"));
					hv.setSjg6(rs.getString("sjg6"));
					hv.setSjg7(rs.getString("sjg7"));
					hv.setSjf1(rs.getString("sjf1"));
					hv.setSjf2(rs.getString("sjf2"));
					hv.setSjlq(rs.getString("sjlq"));
					hv.setSjtjj(rs.getString("sjtjj"));				
					_returnValue.add(hv);
					
					hv = new LiqingView();		
					hv.setBanhezhanminchen(bhzmc+"预警总数");
					hv.setBeizhu("总负");
					hv.setSjysb(rs.getString("fsjysb"));
					hv.setSjg1(rs.getString("fsjg1"));
					hv.setSjg2(rs.getString("fsjg2"));
					hv.setSjg3(rs.getString("fsjg3"));
					hv.setSjg4(rs.getString("fsjg4"));
					hv.setSjg5(rs.getString("fsjg5"));
					hv.setSjg6(rs.getString("fsjg6"));
					hv.setSjg7(rs.getString("fsjg7"));
					hv.setSjf1(rs.getString("fsjf1"));
					hv.setSjf2(rs.getString("fsjf2"));
					hv.setSjlq(rs.getString("fsjlq"));
					hv.setSjtjj(rs.getString("fsjtjj"));	
					_returnValue.add(hv);
					
					hv = new LiqingView();		
					hv.setBanhezhanminchen(bhzmc+"初级");
					hv.setBeizhu("正初级");
					hv.setChangliang(fangshu);
					hv.setPangshu(pangshu);
					hv.setAmbegin(rs.getString("cbps"));
					try {
						if (zongpangshu>0) {
						 hv.setAmend(String.format("%1$.2f",
								 Double.parseDouble(hv.getAmbegin())*100/zongpangshu));
						}
					} catch (Exception e) {
					}
					hv.setSjysb(rs.getString("lowsjysb"));
					hv.setSjg1(rs.getString("lowsjg1"));
					hv.setSjg2(rs.getString("lowsjg2"));
					hv.setSjg3(rs.getString("lowsjg3"));
					hv.setSjg4(rs.getString("lowsjg4"));
					hv.setSjg5(rs.getString("lowsjg5"));
					hv.setSjg6(rs.getString("lowsjg6"));
					hv.setSjg7(rs.getString("lowsjg7"));
					hv.setSjf1(rs.getString("lowsjf1"));
					hv.setSjf2(rs.getString("lowsjf2"));
					hv.setSjlq(rs.getString("lowsjlq"));
					hv.setSjtjj(rs.getString("lowsjtjj"));
					_returnValue.add(hv);
					
					hv = new LiqingView();		
					hv.setBanhezhanminchen(bhzmc+"初级");
					hv.setBeizhu("负初级");
					hv.setSjysb(rs.getString("flowsjysb"));
					hv.setSjg1(rs.getString("flowsjg1"));
					hv.setSjg2(rs.getString("flowsjg2"));
					hv.setSjg3(rs.getString("flowsjg3"));
					hv.setSjg4(rs.getString("flowsjg4"));
					hv.setSjg5(rs.getString("flowsjg5"));
					hv.setSjg6(rs.getString("flowsjg6"));
					hv.setSjg7(rs.getString("flowsjg7"));
					hv.setSjf1(rs.getString("flowsjf1"));
					hv.setSjf2(rs.getString("flowsjf2"));
					hv.setSjlq(rs.getString("flowsjlq"));
					hv.setSjtjj(rs.getString("flowsjtjj"));
					_returnValue.add(hv);
					
					hv = new LiqingView();		
					hv.setBanhezhanminchen(bhzmc+"中级");
					hv.setBeizhu("正中级");
					hv.setChangliang(fangshu);
					hv.setPangshu(pangshu);
					hv.setAmbegin(rs.getString("midcbps"));
					try {
						if (zongpangshu>0) {
						 hv.setAmend(String.format("%1$.2f",
								 Double.parseDouble(hv.getAmbegin())*100/zongpangshu));
						}
					} catch (Exception e) {
					}
					hv.setSjysb(rs.getString("midsjysb"));
					hv.setSjg1(rs.getString("midsjg1"));
					hv.setSjg2(rs.getString("midsjg2"));
					hv.setSjg3(rs.getString("midsjg3"));
					hv.setSjg4(rs.getString("midsjg4"));
					hv.setSjg5(rs.getString("midsjg5"));
					hv.setSjg6(rs.getString("midsjg6"));
					hv.setSjg7(rs.getString("midsjg7"));
					hv.setSjf1(rs.getString("midsjf1"));
					hv.setSjf2(rs.getString("midsjf2"));
					hv.setSjlq(rs.getString("midsjlq"));
					hv.setSjtjj(rs.getString("midsjtjj"));
					_returnValue.add(hv);
					
					hv = new LiqingView();	
					hv.setBanhezhanminchen(bhzmc+"中级");
					hv.setBeizhu("负中级");
					hv.setSjysb(rs.getString("fmidsjysb"));
					hv.setSjg1(rs.getString("fmidsjg1"));
					hv.setSjg2(rs.getString("fmidsjg2"));
					hv.setSjg3(rs.getString("fmidsjg3"));
					hv.setSjg4(rs.getString("fmidsjg4"));
					hv.setSjg5(rs.getString("fmidsjg5"));
					hv.setSjg6(rs.getString("fmidsjg6"));
					hv.setSjg7(rs.getString("fmidsjg7"));
					hv.setSjf1(rs.getString("fmidsjf1"));
					hv.setSjf2(rs.getString("fmidsjf2"));
					hv.setSjlq(rs.getString("fmidsjlq"));
					hv.setSjtjj(rs.getString("fmidsjtjj"));
					_returnValue.add(hv);
					
					hv = new LiqingView();		
					hv.setBanhezhanminchen(bhzmc+"高级");
					hv.setBeizhu("正高级");
					hv.setChangliang(fangshu);
					hv.setPangshu(pangshu);
					hv.setAmbegin(rs.getString("highcbps"));
					try {
						if (zongpangshu>0) {
						 hv.setAmend(String.format("%1$.2f",
								 Double.parseDouble(hv.getAmbegin())*100/zongpangshu));
						}
					} catch (Exception e) {
					}
					hv.setSjysb(rs.getString("highsjysb"));
					hv.setSjg1(rs.getString("highsjg1"));
					hv.setSjg2(rs.getString("highsjg2"));
					hv.setSjg3(rs.getString("highsjg3"));
					hv.setSjg4(rs.getString("highsjg4"));
					hv.setSjg5(rs.getString("highsjg5"));
					hv.setSjg6(rs.getString("highsjg6"));
					hv.setSjg7(rs.getString("highsjg7"));
					hv.setSjf1(rs.getString("highsjf1"));
					hv.setSjf2(rs.getString("highsjf2"));
					hv.setSjlq(rs.getString("highsjlq"));
					hv.setSjtjj(rs.getString("highsjtjj"));
					_returnValue.add(hv);
					
					hv = new LiqingView();		
					hv.setBanhezhanminchen(bhzmc+"高级");
					hv.setBeizhu("负高级");
					hv.setSjysb(rs.getString("fhighsjysb"));
					hv.setSjg1(rs.getString("fhighsjg1"));
					hv.setSjg2(rs.getString("fhighsjg2"));
					hv.setSjg3(rs.getString("fhighsjg3"));
					hv.setSjg4(rs.getString("fhighsjg4"));
					hv.setSjg5(rs.getString("fhighsjg5"));
					hv.setSjg6(rs.getString("fhighsjg6"));
					hv.setSjg7(rs.getString("fhighsjg7"));
					hv.setSjf1(rs.getString("fhighsjf1"));
					hv.setSjf2(rs.getString("fhighsjf2"));
					hv.setSjlq(rs.getString("fhighsjlq"));
					hv.setSjtjj(rs.getString("fhighsjtjj"));
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
	
	public void getpangshu(LiqingView lqv, String startTime, String endTime,Integer biaoduan, 
			Integer xiangmubu, String bhzid,String fn, int bsid) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT SUM(CAST((CASE WHEN (changliang IS NULL) OR (changliang = '') ");
		sql.append("THEN '0' ELSE changliang END) AS numeric(38, 2))) AS changliang,COUNT(bianhao) as pangshu");	
		sql.append(" from LiqingView");
		sql.append(" where (shijian between '"+startTime+"' and '"+endTime+"')");
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
					lqv.setChangliang(String.format("%1$.1f",rs.getFloat("changliang")/1000));
					lqv.setPangshu(rs.getString("pangshu"));
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
}
