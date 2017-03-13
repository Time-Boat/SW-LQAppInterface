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

import com.mss.shtoone.domain.XCData1;
import com.mss.shtoone.domain.XCData1PageMode;
import com.mss.shtoone.persistence.XCData1DAO;
import com.mss.shtoone.util.StringUtil;

@Repository
public class XCData1HibernateDAO extends GenericHibernateDAO<XCData1, Integer> implements
XCData1DAO {
	static Log logger = LogFactory.getLog(XCData1HibernateDAO.class);
	@Override
	public XCData1 getXCData1(int gprsDeviceId){
		String queryString = "from XCData1 as t where t.id=(select max(s.id) from XCData1 s where s.gprsDeviceId = "+gprsDeviceId+") ";
		List<XCData1> list = find(queryString);
		XCData1 xcData = null;
		if(!list.isEmpty()){
			xcData = (XCData1)list.get(0);
		}
        return xcData;
	}
	@Override
	public XCData1PageMode viewlist(Integer gprsDeviceId,
			String startTimeOne, String endTimeOne,
			String BiaoDuanId,int offset, int pagesize) {
		List<XCData1> _returnValue = new ArrayList<XCData1>();
		XCData1PageMode pagemode = new XCData1PageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "XCData1";
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
					XCData1 x1 = new XCData1();
					x1.setId(rs.getInt("ID"));
					x1.setGprsDeviceId(rs.getInt("GprsDeviceId"));
					x1.setCollectTime(rs.getString("CollectTime"));
					x1.setBiaoDuanId(rs.getString("BiaoDuanId"));
					x1.setAirShiDu(rs.getDouble("AirShiDu"));
					x1.setAirTemp(rs.getDouble("AirTemp"));
					x1.setSpreadTemp(rs.getDouble("SpreadTemp"));
					x1.setWindSpeed(rs.getDouble("WindSpeed"));
					
					x1.setTanPuJiWeiZhi(rs.getString("TanPuJiWeiZhi"));
					x1.setTanPuSpeed(rs.getString("TanPuSpeed"));
					x1.setNianYaTemp(rs.getString("NianYaTemp"));
					
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



