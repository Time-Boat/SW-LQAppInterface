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

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.GpsdataView;
import com.mss.shtoone.domain.XCData3;
import com.mss.shtoone.persistence.XCData3DAO;
import com.mss.shtoone.util.StringUtil;

@Repository
public class XCData3HibernateDAO extends GenericHibernateDAO<XCData3, Integer> implements
XCData3DAO {
	static Log logger = LogFactory.getLog(XCData3HibernateDAO.class);
	@Override
	public XCData3 getXCData3(String gpsno){
		String queryString = "from Gpsdata as t where t.gpsid=(select max(s.gpsid) from Gpsdata s where s.gpsno = '"+gpsno+"') ";
		List<XCData3> list = find(queryString);
		XCData3 xcData = null;
		if(!list.isEmpty()){
			xcData = (XCData3)list.get(0);
		}
        return xcData;
	}
	@Override
	public GenericPageMode viewgpslist(String shebeibianhao,String startTimeOne,
			String endTimeOne,
			String fn, int bsid, int offset, int pagesize) {
		List<GpsdataView> _returnValue = new ArrayList<GpsdataView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "GpsdataView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
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
			queryCondition += " and gpsno='" + shebeibianhao + "'";
		}
		
		
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "gpsid");
			cs.setString(4, "gpsid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					GpsdataView x3 = new GpsdataView();
					String beiwei = rs.getString("beiwei");
					try{
						
						double beiweifl = Double.valueOf(beiwei);
						int beiweidu = (int)Math.floor(beiweifl/100);
						double beiweifen = beiweifl-beiweidu*100;
						
						//将这字段临时保存原始经纬度,注意纬度分转化成度需要除以60
						double amend = beiweidu+beiweifen/60;
						x3.setAmend(String.valueOf(amend));
						
						//将纬度拼接成汉字
						beiwei = "北纬"+beiweidu+"度"+String.format("%1$.3f", beiweifen)+"分";	
					}catch(Exception e){
						
					}
					x3.setBeiwei(beiwei);
					String donjin = rs.getString("donjin");
					try{
						double donjinfl = Double.valueOf(donjin);
						int donjindu = (int)Math.floor(donjinfl/100);
						double donjinfen = donjinfl-donjindu*100;
						
						//将这字段临时保存原始经纬度,注意经纬度分转化成度需要除以60
						double ambegin = donjindu+donjinfen/60;
						x3.setAmbegin(String.valueOf(ambegin));
						
						//将经度拼接成汉字
						donjin = "东经"+donjindu+"度"+String.format("%1$.3f", donjinfen)+"分";	
					
					}catch(Exception e){
						
					}
					x3.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					x3.setJianchen(rs.getString("jianchen"));
					x3.setDonjin(donjin);
					x3.setGaodu(rs.getString("gaodu"));
					x3.setGpsid(rs.getInt("gpsid"));
					x3.setGpsno(rs.getString("gpsno"));
					x3.setShijian(rs.getString("shijian"));
					
					try {
						double sudu = rs.getFloat("sudu");
						if (StringUtil.Null2Blank(rs.getString("dinweishijian")).length() == 0 && sudu>0) {
						  sudu = sudu*1000/60;
						} 
						x3.setSudu(String.format("%1$.1f", sudu));
					} catch (Exception e) {						
					}
					x3.setStation(rs.getString("station"));
					_returnValue.add(x3);
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



