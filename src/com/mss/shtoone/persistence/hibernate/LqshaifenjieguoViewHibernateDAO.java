package com.mss.shtoone.persistence.hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LqshaifenjieguoView;
import com.mss.shtoone.persistence.LqshaifenjieguoViewDAO;
import com.mss.shtoone.util.StringUtil;

@Repository
public class LqshaifenjieguoViewHibernateDAO extends GenericHibernateDAO<LqshaifenjieguoView, Integer> implements LqshaifenjieguoViewDAO{
	
	private static Log logger = LogFactory.getLog(LqshaifenjieguoViewHibernateDAO.class);
	
	public GenericPageMode Lqshaifenjieguoviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, int offset, int pagesize, int queryalldata, String peifan){
		List<LqshaifenjieguoView> _returnValue = new ArrayList<LqshaifenjieguoView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LqshaifenjieguoView";
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

		
		String queryCondition =  " (convert(datetime,baocunshijian,121) between '" + strstartTime
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
					LqshaifenjieguoView hv = new LqshaifenjieguoView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setJieguoid(rs.getInt("jieguoid"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));	
					try{
						hv.setPassper1(String.format("%1$.1f",rs.getFloat("passper1")));
					}catch(Exception ex){}
					try{
						hv.setPassper2(String.format("%1$.1f",rs.getFloat("passper2")));
					}catch(Exception ex){}
					try{
						hv.setPassper3(String.format("%1$.1f",rs.getFloat("passper3")));
					}catch(Exception ex){}
					try{
						hv.setPassper4(String.format("%1$.1f",rs.getFloat("passper4")));
					}catch(Exception ex){}
					try{
						hv.setPassper5(String.format("%1$.1f",rs.getFloat("passper5")));
					}catch(Exception ex){}
					try{
						hv.setPassper6(String.format("%1$.1f",rs.getFloat("passper6")));
					}catch(Exception ex){}
					try{
						hv.setPassper7(String.format("%1$.1f",rs.getFloat("passper7")));
					}catch(Exception ex){}
					try{
						hv.setPassper8(String.format("%1$.1f",rs.getFloat("passper8")));
					}catch(Exception ex){}
					try{
						hv.setPassper9(String.format("%1$.1f",rs.getFloat("passper9")));
					}catch(Exception ex){}
					try{
						hv.setPassper10(String.format("%1$.1f",rs.getFloat("passper10")));
					}catch(Exception ex){}
					try{
						hv.setPassper11(String.format("%1$.1f",rs.getFloat("passper11")));
					}catch(Exception ex){}
					try{
						hv.setPassper12(String.format("%1$.1f",rs.getFloat("passper12")));
					}catch(Exception ex){}
					try{
						hv.setPassper13(String.format("%1$.1f",rs.getFloat("passper13")));
					}catch(Exception ex){}
					try{
						hv.setPassper14(String.format("%1$.1f",rs.getFloat("passper14")));
					}catch(Exception ex){}
					try{
						hv.setPassper15(String.format("%1$.1f",rs.getFloat("passper15")));
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
		return pagemode;
	}
	
	
}
