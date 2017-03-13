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

import com.mss.shtoone.domain.HunnintuwuchaPageMode;
import com.mss.shtoone.domain.HunnintuwuchaView;
import com.mss.shtoone.persistence.HunnintuwuchaViewDAO;
import com.mss.shtoone.util.StringUtil;

@Repository
public class HunnintuwuchaViewHibernateDAO extends
		GenericHibernateDAO<HunnintuwuchaView, Integer> implements HunnintuwuchaViewDAO {
	static Log logger = LogFactory.getLog(HunnintuwuchaViewHibernateDAO.class);
	@Override
	public HunnintuwuchaPageMode viewlist(String shebeibianhao, String gongchenghao,
			String startTimeOne, String endTimeOne, String jiaozhubuwei,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid,
			int offset, int pagesize) {
		List<HunnintuwuchaView> _returnValue = new ArrayList<HunnintuwuchaView>();
		HunnintuwuchaPageMode pagemode = new HunnintuwuchaPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "HunnintuwuchaView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
/*		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			try {
				startTime = sdf.parse(startTimeOne);
			} catch (Exception e) {
				
			}			
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			try {
				endTime = sdf.parse(endTimeOne);
			} catch (Exception e) {
			}	
		}	
		if (null != startTime) {
			strstartTime = sdf.format(startTime);
		}
		
		if (null != endTime) {
			strendTime = sdf.format(endTime);
		}*/
		
		String queryCondition =  " (convert(datetime,baocunshijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and shebeibianhao='" + shebeibianhao + "'";
		}
		if (StringUtil.Null2Blank(gongchenghao).length() > 0) {
			queryCondition += " and gongchengmingcheng='" + gongchenghao + "'";
		}		
		if (StringUtil.Null2Blank(jiaozhubuwei).length() > 0) {
			queryCondition += " and jiaozuobuwei='" + jiaozhubuwei + "'";
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
					HunnintuwuchaView hv = new HunnintuwuchaView();
					hv.setJianchen(rs.getString("jianchen"));
					hv.setXiangmubuminchen(rs.getString("xiangmubuminchen"));
					hv.setGongchengmingcheng(rs.getString("gongchengmingcheng"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));					
					hv.setShuini1_lilunzhi(rs.getString("shuini1_lilunzhi"));
					hv.setShuini2_lilunzhi(rs.getString("shuini2_lilunzhi"));
					hv.setKuangfen3_lilunzhi(rs.getString("kuangfen3_lilunzhi"));
					hv.setFeimeihui4_lilunzhi(rs.getString("feimeihui4_lilunzhi"));
					hv.setFenliao5_shijizhi(rs.getString("fenliao5_shijizhi"));
					hv.setFenliao5_lilunzhi(rs.getString("fenliao5_lilunzhi"));
					hv.setFenliao6_shijizhi(rs.getString("fenliao6_shijizhi"));
					hv.setFenliao6_lilunzhi(rs.getString("fenliao6_lilunzhi"));
					hv.setSha2_lilunzhi(rs.getString("sha2_lilunzhi"));
					hv.setSha1_lilunzhi(rs.getString("sha1_lilunzhi"));
					hv.setShi1_lilunzhi(rs.getString("shi1_lilunzhi"));
					hv.setShi2_lilunzhi(rs.getString("shi2_lilunzhi"));
					hv.setShui1_lilunzhi(rs.getString("shui1_lilunzhi"));
					hv.setShui2_shijizhi(rs.getString("shui2_shijizhi"));
					hv.setShui2_lilunzhi(rs.getString("shui2_lilunzhi"));
					hv.setWaijiaji1_lilunzhi(rs.getString("waijiaji1_lilunzhi"));
					hv.setWaijiaji2_shijizhi(rs.getString("waijiaji2_shijizhi"));
					hv.setWaijiaji2_lilunzhi(rs.getString("waijiaji2_lilunzhi"));
					hv.setWaijiaji3_shijizhi(rs.getString("waijiaji3_shijizhi"));
					hv.setWaijiaji3_lilunzhi(rs.getString("waijiaji3_lilunzhi"));
					hv.setWaijiaji4_shijizhi(rs.getString("waijiaji4_shijizhi"));
					hv.setWaijiaji4_lilunzhi(rs.getString("waijiaji4_lilunzhi"));
					hv.setGuliao5_shijizhi(rs.getString("guliao5_shijizhi"));
					hv.setGuliao5_lilunzhi(rs.getString("guliao5_lilunzhi"));
					hv.setShuinipingzhong(rs.getString("shuinipingzhong"));
					hv.setWaijiajipingzhong(rs.getString("waijiajipingzhong"));
					hv.setPeifanghao(rs.getString("peifanghao"));
					hv.setQiangdudengji(rs.getString("qiangdudengji"));
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					hv.setCaijishijian(rs.getString("caijishijian"));
					
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setGongdanhao(rs.getString("gongdanhao"));
					hv.setSigongdidian(rs.getString("sigongdidian"));
					hv.setJiaozuobuwei(rs.getString("jiaozuobuwei"));
					hv.setChuliaoshijian(rs.getString("chuliaoshijian"));
					hv.setChaozuozhe(rs.getString("chaozuozhe"));
					hv.setJiaobanshijian(rs.getString("jiaobanshijian"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));					
					hv.setSha2_shijizhi(rs.getString("sha2_shijizhi"));
					hv.setSha1_shijizhi(rs.getString("sha1_shijizhi"));
					hv.setShi1_shijizhi(rs.getString("shi1_shijizhi"));
					hv.setShi2_shijizhi(rs.getString("shi2_shijizhi"));
					hv.setGujifangshu(rs.getString("gujifangshu"));
					
					
					try {
						hv.setShuini1_shijizhi(String.format("%1$.2f", rs
								.getFloat("shuini1_shijizhi")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setShuini2_shijizhi(String.format("%1$.2f", rs
								.getFloat("shuini2_shijizhi")));
					} catch (Exception e) {
					}
					
					try {
						hv.setKuangfen3_shijizhi(String.format("%1$.2f", rs
								.getFloat("kuangfen3_shijizhi")));
					} catch (Exception e) {
					}
					
					try {
						hv.setFeimeihui4_shijizhi(String.format("%1$.2f", rs
								.getFloat("feimeihui4_shijizhi")));	
					} catch (Exception e) {
					}
					
					try {
						hv.setShui1_shijizhi(String.format("%1$.2f", rs
								.getFloat("shui1_shijizhi")));
					} catch (Exception e) {
					}
					
					try {
						hv.setWaijiaji1_shijizhi(String.format("%1$.2f", rs
								.getFloat("waijiaji1_shijizhi")));
					} catch (Exception e) {
					}
					
					hv.setGlw1(rs.getString("glw1"));
					hv.setGlw2(rs.getString("glw2"));
					hv.setGlw3(rs.getString("glw3"));
					hv.setGlw4(rs.getString("glw4"));
					hv.setGlw5(rs.getString("glw5"));
					hv.setShw1(rs.getString("shw1"));
					hv.setShw1(rs.getString("shw2"));
					hv.setFlw1(rs.getString("flw1"));
					hv.setFlw2(rs.getString("flw2"));
					hv.setFlw3(rs.getString("flw3"));
					hv.setFlw4(rs.getString("flw4"));
					hv.setFlw5(rs.getString("flw5"));
					hv.setFlw6(rs.getString("flw6"));
					hv.setWjw1(rs.getString("wjw1"));
					hv.setWjw2(rs.getString("wjw2"));
					hv.setWjw3(rs.getString("wjw3"));
					hv.setWjw4(rs.getString("wjw4"));

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
