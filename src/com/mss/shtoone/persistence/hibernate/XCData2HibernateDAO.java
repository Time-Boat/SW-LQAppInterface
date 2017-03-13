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
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.XCData2;
import com.mss.shtoone.domain.XCData2PageMode;
import com.mss.shtoone.persistence.XCData2DAO;
import com.mss.shtoone.util.StringUtil;

@Repository
public class XCData2HibernateDAO extends GenericHibernateDAO<XCData2, Integer> implements
XCData2DAO {
	static Log logger = LogFactory.getLog(XCData2HibernateDAO.class);
	@Override
	public XCData2 getXCData2(int gprsDeviceId){
		String queryString = "select max(s.ID),s.CollectTime,s.GprsDeviceId,s.GrindTemp,s.BiaoDuanId from XCData2 s where s.GprsDeviceId = "+gprsDeviceId;
		XCData2 xcData2 = null;
		try{
			Query query = getTemplate().getSessionFactory().openSession().createSQLQuery(queryString);
			List list = query.list();
			if (list.size()>0) {
	            Object[] row = (Object[])list.get(0); 
	            String collectTime = (String)row[1]; 
	            Double grindTemp = (Double)row[3];
	            String biaoDuanId = (String)row[4];
	            xcData2 = new XCData2(collectTime,gprsDeviceId,grindTemp,biaoDuanId);
			}		
		}catch(Exception e){
			logger.debug(e);
		}
        return xcData2;
	}	
	@Override
	public XCData2PageMode viewlist(Integer gprsDeviceId,
			String startTimeOne, String endTimeOne,
			String BiaoDuanId,int offset, int pagesize) {
		List<XCData2> _returnValue = new ArrayList<XCData2>();
		XCData2PageMode pagemode = new XCData2PageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "XCData2";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		String queryCondition = " 1 = 1";
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
			queryCondition += " and (convert(datetime,CollectTime,121) >= '" + strstartTime;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
			queryCondition += " and (convert(datetime,CollectTime,121) <= '" + strendTime;
		}
		
		/*if (StringUtil.Null2Blank(BiaoDuanId).length() > 0) {
		queryCondition += " and BiaoDuanId='" + BiaoDuanId + "'";
	    }*/
		if(null!=gprsDeviceId){
			queryCondition += " and GprsDeviceId=" + gprsDeviceId ;
		}
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "ID");
			cs.setString(4, "ID DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					XCData2 xc = new XCData2();
					xc.setId(rs.getInt("ID"));
					xc.setGprsDeviceId(rs.getInt("GprsDeviceId"));
					xc.setCollectTime(rs.getString("CollectTime"));
					xc.setBiaoDuanId(rs.getString("BiaoDuanId"));
					xc.setGrindTemp(rs.getDouble("GrindTemp"));
					
					_returnValue.add(xc);
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



