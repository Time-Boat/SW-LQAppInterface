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
import com.mss.shtoone.domain.ShuiwenphbView;
import com.mss.shtoone.persistence.ShuiwenphbViewDAO;
import com.mss.shtoone.util.StringUtil;

@Repository
public class ShuiwenphbViewHibernateDAO extends GenericHibernateDAO<ShuiwenphbView, Integer> implements
ShuiwenphbViewDAO{
	static Log logger = LogFactory.getLog(ShuiwenphbViewHibernateDAO.class);
	@Override
	public GenericPageMode swphbviewlist(String shebeibianhao,
			String startTimeOne, String endTimeOne, Integer biaoduan,
			Integer xiangmubu, String fn, int bsid, int offset, int pagesize) {
		List<ShuiwenphbView> _returnValue = new ArrayList<ShuiwenphbView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "ShuiwenphbView";
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
					ShuiwenphbView hv = new ShuiwenphbView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));
					hv.setShijian(rs.getString("shijian"));
					hv.setShijianS(rs.getString("shijianS"));
					hv.setShijianE(rs.getString("shijianE"));
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					hv.setCaijishijian(rs.getString("caijishijian"));
					
					hv.setGlchangliang(rs.getString("glchangliang"));
					hv.setBeiy1(rs.getString("beiy1"));
					hv.setBeiy2(rs.getString("beiy2"));
					hv.setBeiy3(rs.getString("beiy3"));
					
					try {
						hv.setSjgl1(String.format("%1$.1f", rs
								.getFloat("sjgl1")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjgl2(String.format("%1$.1f", rs
								.getFloat("sjgl2")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjgl3(String.format("%1$.1f", rs
								.getFloat("sjgl3")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjgl4(String.format("%1$.1f", rs
								.getFloat("sjgl4")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjgl5(String.format("%1$.1f", rs
								.getFloat("sjgl5")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setSjfl1(String.format("%1$.2f", rs
								.getFloat("sjfl1")));
					} catch (Exception e) {						
					}
					try {
						hv.setSjfl2(String.format("%1$.2f", rs
								.getFloat("sjfl2")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setLlgl1(String.format("%1$.1f", rs
								.getFloat("llgl1")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlgl2(String.format("%1$.1f", rs
								.getFloat("llgl2")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlgl3(String.format("%1$.1f", rs
								.getFloat("llgl3")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlgl4(String.format("%1$.1f", rs
								.getFloat("llgl4")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlgl5(String.format("%1$.1f", rs
								.getFloat("llgl5")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setLlfl1(String.format("%1$.2f", rs
								.getFloat("llfl1")));
					} catch (Exception e) {						
					}
					try {
						hv.setLlfl2(String.format("%1$.2f", rs
								.getFloat("llfl2")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setPersjgl1(String.format("%1$.1f", rs
								.getFloat("persjgl1")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjgl2(String.format("%1$.1f", rs
								.getFloat("persjgl2")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjgl3(String.format("%1$.1f", rs
								.getFloat("persjgl3")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjgl4(String.format("%1$.1f", rs
								.getFloat("persjgl4")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjgl5(String.format("%1$.1f", rs
								.getFloat("persjgl5")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setPersjfl1(String.format("%1$.2f", rs
								.getFloat("persjfl1")));
					} catch (Exception e) {						
					}
					try {
						hv.setPersjfl2(String.format("%1$.2f", rs
								.getFloat("persjfl2")));
					} catch (Exception e) {						
					}
					
					
					try {
						hv.setPerllgl1(String.format("%1$.1f", rs
								.getFloat("perllgl1")));
					} catch (Exception e) {						
					}
					try {
						hv.setPerllgl2(String.format("%1$.1f", rs
								.getFloat("perllgl2")));
					} catch (Exception e) {						
					}
					try {
						hv.setPerllgl3(String.format("%1$.1f", rs
								.getFloat("perllgl3")));
					} catch (Exception e) {						
					}
					try {
						hv.setPerllgl4(String.format("%1$.1f", rs
								.getFloat("perllgl4")));
					} catch (Exception e) {						
					}
					try {
						hv.setPerllgl5(String.format("%1$.1f", rs
								.getFloat("perllgl5")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setPerllfl1(String.format("%1$.2f", rs
								.getFloat("perllfl1")));
					} catch (Exception e) {						
					}
					try {
						hv.setPerllfl2(String.format("%1$.2f", rs
								.getFloat("perllfl2")));
					} catch (Exception e) {						
					}
					
					
					try {
						hv.setWgl1(String.format("%1$.2f", rs
								.getFloat("wgl1")));
					} catch (Exception e) {						
					}
					try {
						hv.setWgl2(String.format("%1$.2f", rs
								.getFloat("wgl2")));
					} catch (Exception e) {						
					}
					try {
						hv.setWgl3(String.format("%1$.2f", rs
								.getFloat("wgl3")));
					} catch (Exception e) {						
					}
					try {
						hv.setWgl4(String.format("%1$.2f", rs
								.getFloat("wgl4")));
					} catch (Exception e) {						
					}
					try {
						hv.setWgl5(String.format("%1$.2f", rs
								.getFloat("wgl5")));
					} catch (Exception e) {						
					}
					
					try {
						hv.setWfl1(String.format("%1$.2f", rs
								.getFloat("wfl1")));
					} catch (Exception e) {						
					}
					try {
						hv.setWfl2(String.format("%1$.2f", rs
								.getFloat("wfl2")));
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
	

}
