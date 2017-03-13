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
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.Banhezhanxinxi;
import com.mss.shtoone.domain.ChuliaokouTemperaturedataView;
import com.mss.shtoone.domain.EnvironmentView;
import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.HunnintuPageMode;
import com.mss.shtoone.domain.HunnintuView;
import com.mss.shtoone.domain.HunnintujieguoPageMode;
import com.mss.shtoone.domain.HunnintujieguoView;
import com.mss.shtoone.domain.LiqingclshejizhiView;
import com.mss.shtoone.domain.ShuiwenxixxView;
import com.mss.shtoone.domain.SpeeddataView;
import com.mss.shtoone.domain.TemperaturedataView;
import com.mss.shtoone.domain.TjjdataView;
import com.mss.shtoone.domain.Userlog;
import com.mss.shtoone.domain.UserlogView;
import com.mss.shtoone.domain.Wendingdudata;
import com.mss.shtoone.domain.Xiangmubuxinxi;
import com.mss.shtoone.domain.Yandudata;
import com.mss.shtoone.domain.Zuoyeduixinxi;
import com.mss.shtoone.persistence.BanhezhanDAO;
import com.mss.shtoone.persistence.HunnintuViewDAO;
import com.mss.shtoone.persistence.XiangmubuDAO;
import com.mss.shtoone.persistence.ZuoyeduiDAO;
import com.mss.shtoone.util.StringUtil;

@Repository
public class HunnintuViewHibernateDAO extends
		GenericHibernateDAO<HunnintuView, Integer> implements HunnintuViewDAO {
	private static Log logger = LogFactory.getLog(HunnintuViewHibernateDAO.class);
	
	@Autowired
	private XiangmubuDAO xmbDAO;
	
	@Autowired
	private BanhezhanDAO bhzDAO;	
	
	@Autowired
	private ZuoyeduiDAO zydDAO;

	@Override
	public HunnintuPageMode viewlist(String shebeibianhao, String gongchenghao,
			String startTimeOne, String endTimeOne, String jiaozhubuwei,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize) {
		List<HunnintuView> _returnValue = new ArrayList<HunnintuView>();
		HunnintuPageMode pagemode = new HunnintuPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "HunnintuView";
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
					HunnintuView hv = new HunnintuView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setXiangmubuminchen(rs.getString("xiangmubuminchen"));
					hv.setGongdanhao(rs.getString("gongdanhao"));
					hv.setGongchengmingcheng(rs.getString("gongchengmingcheng"));
					hv.setSigongdidian(rs.getString("sigongdidian"));
					hv.setJiaozuobuwei(rs.getString("jiaozuobuwei"));
					hv.setChuliaoshijian(rs.getString("chuliaoshijian"));
					hv.setChaozuozhe(rs.getString("chaozuozhe"));
					hv.setJiaobanshijian(rs.getString("jiaobanshijian"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));					
					hv.setShuini1_lilunzhi(rs.getString("shuini1_lilunzhi"));
					hv.setShuini2_lilunzhi(rs.getString("shuini2_lilunzhi"));
					hv.setKuangfen3_lilunzhi(rs.getString("kuangfen3_lilunzhi"));
					hv.setFeimeihui4_lilunzhi(rs.getString("feimeihui4_lilunzhi"));
					try {
						hv.setFenliao5_shijizhi(String.format("%1$.2f", rs
								.getFloat("fenliao5_shijizhi")));
					} catch (Exception e) {						
					}
					hv.setFenliao5_lilunzhi(rs.getString("fenliao5_lilunzhi"));
					try {
						hv.setFenliao6_shijizhi(String.format("%1$.2f", rs
								.getFloat("fenliao6_shijizhi")));
					} catch (Exception e) {						
					}
					hv.setFenliao6_lilunzhi(rs.getString("fenliao6_lilunzhi"));
					try {
						hv.setSha2_shijizhi(String.format("%1$.0f", rs
								.getFloat("sha2_shijizhi")));
					} catch (Exception e) {						
					}
					hv.setSha2_lilunzhi(rs.getString("sha2_lilunzhi"));
					try {
						hv.setSha1_shijizhi(String.format("%1$.0f", rs
								.getFloat("sha1_shijizhi")));
					} catch (Exception e) {						
					}
					hv.setSha1_lilunzhi(rs.getString("sha1_lilunzhi"));
					try {
						hv.setShi1_shijizhi(String.format("%1$.0f", rs
								.getFloat("shi1_shijizhi")));
					} catch (Exception e) {						
					}
					hv.setShi1_lilunzhi(rs.getString("shi1_lilunzhi"));
					try {
						hv.setShi2_shijizhi(String.format("%1$.0f", rs
								.getFloat("shi2_shijizhi")));
					} catch (Exception e) {						
					}
					hv.setShi2_lilunzhi(rs.getString("shi2_lilunzhi"));
					hv.setShui1_lilunzhi(rs.getString("shui1_lilunzhi"));
					try {
						hv.setShui2_shijizhi(String.format("%1$.2f", rs
								.getFloat("shui2_shijizhi")));
					} catch (Exception e) {						
					}
					hv.setShui2_lilunzhi(rs.getString("shui2_lilunzhi"));
					hv.setWaijiaji1_lilunzhi(rs.getString("waijiaji1_lilunzhi"));
					try {
						hv.setWaijiaji2_shijizhi(String.format("%1$.2f", rs
								.getFloat("waijiaji2_shijizhi")));
					} catch (Exception e) {						
					}
					hv.setWaijiaji2_lilunzhi(rs.getString("waijiaji2_lilunzhi"));
					try {
						hv.setWaijiaji3_shijizhi(String.format("%1$.2f", rs
								.getFloat("waijiaji3_shijizhi")));
					} catch (Exception e) {						
					}
					hv.setWaijiaji3_lilunzhi(rs.getString("waijiaji3_lilunzhi"));
					try {
						hv.setWaijiaji4_shijizhi(String.format("%1$.2f", rs
								.getFloat("waijiaji4_shijizhi")));
					} catch (Exception e) {						
					}
					hv.setWaijiaji4_lilunzhi(rs.getString("waijiaji4_lilunzhi"));
					try {
						hv.setGuliao5_shijizhi(String.format("%1$.0f", rs
								.getFloat("guliao5_shijizhi")));
					} catch (Exception e) {						
					}
					hv.setGuliao5_lilunzhi(rs.getString("guliao5_lilunzhi"));
					hv.setShuinipingzhong(rs.getString("shuinipingzhong"));
					hv.setWaijiajipingzhong(rs.getString("waijiajipingzhong"));
					hv.setPeifanghao(rs.getString("peifanghao"));
					hv.setQiangdudengji(rs.getString("qiangdudengji"));
					hv.setShebeibianhao(rs.getString("shebeibianhao"));
					hv.setCaijishijian(rs.getString("caijishijian"));
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
	public GenericPageMode viewspeedlist(String shebeibianhao,
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize) {
		List<SpeeddataView> _returnValue = new ArrayList<SpeeddataView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "SpeeddataView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition=  " (convert(datetime,shijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and gpsno='" + shebeibianhao + "'";
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
					SpeeddataView speeddata = new SpeeddataView();
					speeddata.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					speeddata.setJianchen(rs.getString("jianchen"));
					speeddata.setGpsno(rs.getString("gpsno"));
					try{
						speeddata.setSudu(String.format("%1$.1f",rs.getFloat("sudu")));
					}catch(Exception ex){}
					speeddata.setShijian(rs.getString("shijian"));
					_returnValue.add(speeddata);
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
	public GenericPageMode viewwddlist(String shebeibianhao,
			String startTimeOne, String endTimeOne, 
			int offset, int pagesize) {
		List<Wendingdudata> _returnValue = new ArrayList<Wendingdudata>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "Wendingdudata";
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
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and shebeibianhao='" + shebeibianhao + "'";
		}
		
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "wddid");
			cs.setString(4, "wddid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					Wendingdudata wdddata = new Wendingdudata();
					wdddata.setShebeibianhao(rs.getString("shebeibianhao"));
					wdddata.setNu(rs.getString("nu"));
					try {
						wdddata.setF_maxpressure(String.format("%1$.2f", rs
								.getFloat("f_maxpressure")));
					} catch (Exception e) {
					}
					try {
						wdddata.setF_maxdisplacement(String.format("%1$.2f", rs
								.getFloat("f_maxdisplacement")));
					} catch (Exception e) {
					}
					try {
						wdddata.setF_98displacement(String.format("%1$.2f", rs
								.getFloat("f_98displacement")));
					} catch (Exception e) {
					}
					wdddata.setDate_time(rs.getString("date_time"));
					wdddata.setBaocunshijian(rs.getString("baocunshijian"));
					wdddata.setKehuduanbianhao(rs.getString("kehuduanbianhao"));
					wdddata.setCaijishijian(rs.getString("caijishijian"));
					_returnValue.add(wdddata);
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
	public GenericPageMode viewydlist(String shebeibianhao,
			String startTimeOne, String endTimeOne, 
			int offset, int pagesize) {
		List<Yandudata> _returnValue = new ArrayList<Yandudata>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "Yandudata";
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
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and shebeibianhao='" + shebeibianhao + "'";
		}
		
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "ydid");
			cs.setString(4, "ydid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					Yandudata yddata = new Yandudata();
					yddata.setShebeibianhao(rs.getString("shebeibianhao"));
					yddata.setZh(rs.getString("zh"));					
					yddata.setDate_time(rs.getString("date_time"));
					yddata.setBaocunshijian(rs.getString("baocunshijian"));
					yddata.setKehuduanbianhao(rs.getString("kehuduanbianhao"));
					yddata.setCaijishijian(rs.getString("caijishijian"));
					yddata.setWd_set(rs.getString("wd_set"));
					yddata.setSpeed(rs.getString("speed"));
					yddata.setLength1(rs.getString("length1"));
					yddata.setLength2(rs.getString("length2"));
					yddata.setLength3(rs.getString("length3"));
					yddata.setLength1_wd(rs.getString("length1_wd"));
					yddata.setLength2_wd(rs.getString("length2_wd"));
					yddata.setLength3_wd(rs.getString("length3_wd"));
					_returnValue.add(yddata);
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
	public GenericPageMode viewtmplist(String shebeibianhao,
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize) {
		List<TemperaturedataView> _returnValue = new ArrayList<TemperaturedataView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "TemperaturedataView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition=" (convert(datetime,tmpshijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and tmpno='" + shebeibianhao + "'";
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
			cs.setString(3, "tmpid");
			cs.setString(4, "tmpid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					TemperaturedataView tmpdata = new TemperaturedataView();
					tmpdata.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					tmpdata.setJianchen(rs.getString("jianchen"));
					tmpdata.setTmpid(rs.getInt("tmpid"));
					tmpdata.setTmpno(rs.getString("tmpno"));
					try{
						tmpdata.setTmpdata(String.format("%1$.1f",rs.getFloat("tmpdata")));
					}catch(Exception ex){}
					tmpdata.setTmpshijian(rs.getString("tmpshijian"));
					_returnValue.add(tmpdata);
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
	public GenericPageMode viewtjjlist(String shebeibianhao,
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize) {
		List<TjjdataView> _returnValue = new ArrayList<TjjdataView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "TjjdataView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition =  "(convert(datetime,tjjshijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and tjjno='" + shebeibianhao + "'";
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
			cs.setString(3, "tjjid");
			cs.setString(4, "tjjid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					TjjdataView tjjdata = new TjjdataView();
					tjjdata.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					tjjdata.setJianchen(rs.getString("jianchen"));
					tjjdata.setTjjid(rs.getInt("tjjid"));
					tjjdata.setTjjno(rs.getString("tjjno"));
					tjjdata.setTjjdata(rs.getString("tjjdata"));
					tjjdata.setTjjshijian(rs.getString("tjjshijian"));
					tjjdata.setChangliang(rs.getString("changliang"));
					try {
						float cl = Float.parseFloat(tjjdata.getChangliang());
						float tl = Float.parseFloat(tjjdata.getTjjdata());				
						if (tl>0 && cl>0) {
							tjjdata.setAmbegin(String.format("%1$.2f",tl/cl*100));
						}
					} catch (Exception e) {
					}
					_returnValue.add(tjjdata);
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
	public List findTop() {
		Query query = getTemplate().getSessionFactory().openSession().createQuery("from TopRealMaxhunnintuView");
		query.setMaxResults(1);	
		return query.list();
	}

	private void appendSql(StringBuffer sql) {
		sql.append("SELECT SUM(CAST((CASE WHEN (shui1_shijizhi IS NULL) OR (shui1_shijizhi = '') ");
		sql.append("THEN '0' ELSE shui1_shijizhi END) AS numeric(38, 2))) AS shuisj1,");
		sql.append("SUM(CAST((CASE WHEN (shui2_shijizhi IS NULL) OR (shui2_shijizhi = '') ");
		sql.append("THEN '0' ELSE shui2_shijizhi END) AS numeric(38, 2))) AS shuisj2,");
		sql.append("SUM(CAST((CASE WHEN (shuini1_shijizhi IS NULL) OR (shuini1_shijizhi = '') ");
		sql.append("THEN '0' ELSE shuini1_shijizhi END) AS numeric(38, 2))) AS flsj1,");
		sql.append("SUM(CAST((CASE WHEN (shuini2_shijizhi IS NULL) OR (shuini2_shijizhi = '') ");
		sql.append("THEN '0' ELSE shuini2_shijizhi END) AS numeric(38, 2))) AS flsj2,");		
		sql.append("SUM(CAST((CASE WHEN (kuangfen3_shijizhi IS NULL) OR (kuangfen3_shijizhi = '') ");
		sql.append("THEN '0' ELSE kuangfen3_shijizhi END) AS numeric(38, 2))) AS flsj3,");
		sql.append("SUM(CAST((CASE WHEN (feimeihui4_shijizhi IS NULL) OR (feimeihui4_shijizhi = '') ");
		sql.append("THEN '0' ELSE feimeihui4_shijizhi END) AS numeric(38, 2))) AS flsj4,");		
		sql.append("SUM(CAST((CASE WHEN (fenliao5_shijizhi IS NULL) OR (fenliao5_shijizhi = '') ");
		sql.append("THEN '0' ELSE fenliao5_shijizhi END) AS numeric(38, 2))) AS flsj5,");
		sql.append("SUM(CAST((CASE WHEN (fenliao6_shijizhi IS NULL) OR (fenliao6_shijizhi = '') ");
		sql.append("THEN '0' ELSE fenliao6_shijizhi END) AS numeric(38, 2))) AS flsj6,");	
		sql.append("SUM(CAST((CASE WHEN (sha1_shijizhi IS NULL) OR (sha1_shijizhi = '') ");
		sql.append("THEN '0' ELSE sha1_shijizhi END) AS numeric(38, 2))) AS glsj1,");	
		sql.append("SUM(CAST((CASE WHEN (shi1_shijizhi IS NULL) OR (shi1_shijizhi = '') ");
		sql.append("THEN '0' ELSE shi1_shijizhi END) AS numeric(38, 2))) AS glsj2,");		
		sql.append("SUM(CAST((CASE WHEN (shi2_shijizhi IS NULL) OR (shi2_shijizhi = '') ");
		sql.append("THEN '0' ELSE shi2_shijizhi END) AS numeric(38, 2))) AS glsj3,");
		sql.append("SUM(CAST((CASE WHEN (sha2_shijizhi IS NULL) OR (sha2_shijizhi = '') ");
		sql.append("THEN '0' ELSE sha2_shijizhi END) AS numeric(38, 2))) AS glsj4,");
		sql.append("SUM(CAST((CASE WHEN (guliao5_shijizhi IS NULL) OR (guliao5_shijizhi = '') ");
		sql.append("THEN '0' ELSE guliao5_shijizhi END) AS numeric(38, 2))) AS glsj5,");	
		sql.append("SUM(CAST((CASE WHEN (waijiaji1_shijizhi IS NULL) OR (waijiaji1_shijizhi = '') ");
		sql.append("THEN '0' ELSE waijiaji1_shijizhi END) AS numeric(38, 2))) AS wjsj1,");		
		sql.append("SUM(CAST((CASE WHEN (waijiaji2_shijizhi IS NULL) OR (waijiaji2_shijizhi = '') ");
		sql.append("THEN '0' ELSE waijiaji2_shijizhi END) AS numeric(38, 2))) AS wjsj2,");				
		sql.append("SUM(CAST((CASE WHEN (waijiaji3_shijizhi IS NULL) OR (waijiaji3_shijizhi = '') ");
		sql.append("THEN '0' ELSE waijiaji3_shijizhi END) AS numeric(38, 2))) AS wjsj3,");				
		sql.append("SUM(CAST((CASE WHEN (waijiaji4_shijizhi IS NULL) OR (waijiaji4_shijizhi = '') ");
		sql.append("THEN '0' ELSE waijiaji4_shijizhi END) AS numeric(38, 2))) AS wjsj4,");		
		sql.append("SUM(CAST((CASE WHEN (shui1_lilunzhi IS NULL) OR (shui1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shui1_lilunzhi END) AS numeric(38, 2))) AS shuill1,");	
		sql.append("SUM(CAST((CASE WHEN (shui2_lilunzhi IS NULL) OR (shui2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shui2_lilunzhi END) AS numeric(38, 2))) AS shuill2,");				
		sql.append("SUM(CAST((CASE WHEN (shuini1_lilunzhi IS NULL) OR (shuini1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shuini1_lilunzhi END) AS numeric(38, 2))) AS flll1,");	
		sql.append("SUM(CAST((CASE WHEN (shuini2_lilunzhi IS NULL) OR (shuini2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shuini2_lilunzhi END) AS numeric(38, 2))) AS flll2,");				
		sql.append("SUM(CAST((CASE WHEN (kuangfen3_lilunzhi IS NULL) OR (kuangfen3_lilunzhi = '') ");
		sql.append("THEN '0' ELSE kuangfen3_lilunzhi END) AS numeric(38, 2))) AS flll3,");	
		sql.append("SUM(CAST((CASE WHEN (feimeihui4_lilunzhi IS NULL) OR (feimeihui4_lilunzhi = '') ");
		sql.append("THEN '0' ELSE feimeihui4_lilunzhi END) AS numeric(38, 2))) AS flll4,");				
		sql.append("SUM(CAST((CASE WHEN (fenliao5_lilunzhi IS NULL) OR (fenliao5_lilunzhi = '') ");
		sql.append("THEN '0' ELSE fenliao5_lilunzhi END) AS numeric(38, 2))) AS flll5,");
		sql.append("SUM(CAST((CASE WHEN (fenliao6_lilunzhi IS NULL) OR (fenliao6_lilunzhi = '') ");
		sql.append("THEN '0' ELSE fenliao6_lilunzhi END) AS numeric(38, 2))) AS flll6,");
		sql.append("SUM(CAST((CASE WHEN (sha1_lilunzhi IS NULL) OR (sha1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE sha1_lilunzhi END) AS numeric(38, 2))) AS glll1,");	
		sql.append("SUM(CAST((CASE WHEN (shi1_lilunzhi IS NULL) OR (shi1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shi1_lilunzhi END) AS numeric(38, 2))) AS glll2,");				
		sql.append("SUM(CAST((CASE WHEN (shi2_lilunzhi IS NULL) OR (shi2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shi2_lilunzhi END) AS numeric(38, 2))) AS glll3,");
		sql.append("SUM(CAST((CASE WHEN (sha2_lilunzhi IS NULL) OR (sha2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE sha2_lilunzhi END) AS numeric(38, 2))) AS glll4,");
		sql.append("SUM(CAST((CASE WHEN (guliao5_lilunzhi IS NULL) OR (guliao5_lilunzhi = '') ");
		sql.append("THEN '0' ELSE guliao5_lilunzhi END) AS numeric(38, 2))) AS glll5,");
		sql.append("SUM(CAST((CASE WHEN (waijiaji1_lilunzhi IS NULL) OR (waijiaji1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE waijiaji1_lilunzhi END) AS numeric(38, 2))) AS wjll1,");		
		sql.append("SUM(CAST((CASE WHEN (waijiaji2_lilunzhi IS NULL) OR (waijiaji2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE waijiaji2_lilunzhi END) AS numeric(38, 2))) AS wjll2,");
		sql.append("SUM(CAST((CASE WHEN (waijiaji3_lilunzhi IS NULL) OR (waijiaji3_lilunzhi = '') ");
		sql.append("THEN '0' ELSE waijiaji3_lilunzhi END) AS numeric(38, 2))) AS wjll3,");
		sql.append("SUM(CAST((CASE WHEN (waijiaji4_lilunzhi IS NULL) OR (waijiaji4_lilunzhi = '') ");
		sql.append("THEN '0' ELSE waijiaji4_lilunzhi END) AS numeric(38, 2))) AS wjll4,");
		sql.append("(SUM(CAST((CASE WHEN (shui1_shijizhi IS NULL) OR (shui1_shijizhi = '') ");
		sql.append("THEN '0' ELSE shui1_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (shui1_lilunzhi IS NULL) OR (shui1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shui1_lilunzhi END) AS numeric(38, 2)))) AS shui1chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (shui2_shijizhi IS NULL) OR (shui2_shijizhi = '') ");
		sql.append("THEN '0' ELSE shui2_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (shui2_lilunzhi IS NULL) OR (shui2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shui2_lilunzhi END) AS numeric(38, 2)))) AS shui2chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (shuini1_shijizhi IS NULL) OR (shuini1_shijizhi = '') ");
		sql.append("THEN '0' ELSE shuini1_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (shuini1_lilunzhi IS NULL) OR (shuini1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shuini1_lilunzhi END) AS numeric(38, 2)))) AS shuini1chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (shuini2_shijizhi IS NULL) OR (shuini2_shijizhi = '') ");
		sql.append("THEN '0' ELSE shuini2_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (shuini2_lilunzhi IS NULL) OR (shuini2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shuini2_lilunzhi END) AS numeric(38, 2)))) AS shuini2chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (kuangfen3_shijizhi IS NULL) OR (kuangfen3_shijizhi = '') ");
		sql.append("THEN '0' ELSE kuangfen3_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (kuangfen3_lilunzhi IS NULL) OR (kuangfen3_lilunzhi = '') ");
		sql.append("THEN '0' ELSE kuangfen3_lilunzhi END) AS numeric(38, 2)))) AS kuangfen3chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (feimeihui4_shijizhi IS NULL) OR (feimeihui4_shijizhi = '') ");
		sql.append("THEN '0' ELSE feimeihui4_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (feimeihui4_lilunzhi IS NULL) OR (feimeihui4_lilunzhi = '') ");
		sql.append("THEN '0' ELSE feimeihui4_lilunzhi END) AS numeric(38, 2)))) AS feimeihui4chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (fenliao5_shijizhi IS NULL) OR (fenliao5_shijizhi = '') ");
		sql.append("THEN '0' ELSE fenliao5_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (fenliao5_lilunzhi IS NULL) OR (fenliao5_lilunzhi = '') ");
		sql.append("THEN '0' ELSE fenliao5_lilunzhi END) AS numeric(38, 2)))) AS fenliao5chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (fenliao6_shijizhi IS NULL) OR (fenliao6_shijizhi = '') ");
		sql.append("THEN '0' ELSE fenliao6_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (fenliao6_lilunzhi IS NULL) OR (fenliao6_lilunzhi = '') ");
		sql.append("THEN '0' ELSE fenliao6_lilunzhi END) AS numeric(38, 2)))) AS fenliao6chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (sha1_shijizhi IS NULL) OR (sha1_shijizhi = '') ");
		sql.append("THEN '0' ELSE sha1_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (sha1_lilunzhi IS NULL) OR (sha1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE sha1_lilunzhi END) AS numeric(38, 2)))) AS sha1chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (shi1_shijizhi IS NULL) OR (shi1_shijizhi = '') ");
		sql.append("THEN '0' ELSE shi1_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (shi1_lilunzhi IS NULL) OR (shi1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shi1_lilunzhi END) AS numeric(38, 2)))) AS shi1chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (shi2_shijizhi IS NULL) OR (shi2_shijizhi = '') ");
		sql.append("THEN '0' ELSE shi2_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (shi2_lilunzhi IS NULL) OR (shi2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shi2_lilunzhi END) AS numeric(38, 2)))) AS shi2chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (sha2_shijizhi IS NULL) OR (sha2_shijizhi = '') ");
		sql.append("THEN '0' ELSE sha2_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (sha2_lilunzhi IS NULL) OR (sha2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE sha2_lilunzhi END) AS numeric(38, 2)))) AS sha2chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (guliao5_shijizhi IS NULL) OR (guliao5_shijizhi = '') ");
		sql.append("THEN '0' ELSE guliao5_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (guliao5_lilunzhi IS NULL) OR (guliao5_lilunzhi = '') ");
		sql.append("THEN '0' ELSE guliao5_lilunzhi END) AS numeric(38, 2)))) AS guliao5chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (waijiaji1_shijizhi IS NULL) OR (waijiaji1_shijizhi = '') ");
		sql.append("THEN '0' ELSE waijiaji1_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (waijiaji1_lilunzhi IS NULL) OR (waijiaji1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE waijiaji1_lilunzhi END) AS numeric(38, 2)))) AS waijiaji1chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (waijiaji2_shijizhi IS NULL) OR (waijiaji2_shijizhi = '') ");
		sql.append("THEN '0' ELSE waijiaji2_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (waijiaji2_lilunzhi IS NULL) OR (waijiaji2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE waijiaji2_lilunzhi END) AS numeric(38, 2)))) AS waijiaji2chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (waijiaji3_shijizhi IS NULL) OR (waijiaji3_shijizhi = '') ");
		sql.append("THEN '0' ELSE waijiaji3_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (waijiaji3_lilunzhi IS NULL) OR (waijiaji3_lilunzhi = '') ");
		sql.append("THEN '0' ELSE waijiaji3_lilunzhi END) AS numeric(38, 2)))) AS waijiaji3chazhi,");
		sql.append("(SUM(CAST((CASE WHEN (waijiaji4_shijizhi IS NULL) OR (waijiaji4_shijizhi = '') ");
		sql.append("THEN '0' ELSE waijiaji4_shijizhi END) AS numeric(38, 2)))-");
		sql.append("SUM(CAST((CASE WHEN (waijiaji4_lilunzhi IS NULL) OR (waijiaji4_lilunzhi = '') ");
		sql.append("THEN '0' ELSE waijiaji4_lilunzhi END) AS numeric(38, 2)))) AS waijiaji4chazhi,");
		sql.append("SUM(CAST((CASE WHEN (gujifangshu IS NULL) OR (gujifangshu = '') ");
		sql.append("THEN '0' ELSE gujifangshu END) AS numeric(38, 2))) AS gujifangshu");
	}
	
	private void appendlilunSql(StringBuffer sql) {
		sql.append("SELECT SUM(CAST((CASE WHEN (shui1_lilunzhi IS NULL) OR (shui1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shui1_lilunzhi END) AS numeric(38, 2))) AS shuill1,");	
		sql.append("SUM(CAST((CASE WHEN (shui2_lilunzhi IS NULL) OR (shui2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shui2_lilunzhi END) AS numeric(38, 2))) AS shuill2,");				
		sql.append("SUM(CAST((CASE WHEN (shuini1_lilunzhi IS NULL) OR (shuini1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shuini1_lilunzhi END) AS numeric(38, 2))) AS flll1,");	
		sql.append("SUM(CAST((CASE WHEN (shuini2_lilunzhi IS NULL) OR (shuini2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shuini2_lilunzhi END) AS numeric(38, 2))) AS flll2,");				
		sql.append("SUM(CAST((CASE WHEN (kuangfen3_lilunzhi IS NULL) OR (kuangfen3_lilunzhi = '') ");
		sql.append("THEN '0' ELSE kuangfen3_lilunzhi END) AS numeric(38, 2))) AS flll3,");	
		sql.append("SUM(CAST((CASE WHEN (feimeihui4_lilunzhi IS NULL) OR (feimeihui4_lilunzhi = '') ");
		sql.append("THEN '0' ELSE feimeihui4_lilunzhi END) AS numeric(38, 2))) AS flll4,");				
		sql.append("SUM(CAST((CASE WHEN (fenliao5_lilunzhi IS NULL) OR (fenliao5_lilunzhi = '') ");
		sql.append("THEN '0' ELSE fenliao5_lilunzhi END) AS numeric(38, 2))) AS flll5,");
		sql.append("SUM(CAST((CASE WHEN (fenliao6_lilunzhi IS NULL) OR (fenliao6_lilunzhi = '') ");
		sql.append("THEN '0' ELSE fenliao6_lilunzhi END) AS numeric(38, 2))) AS flll6,");
		sql.append("SUM(CAST((CASE WHEN (sha1_lilunzhi IS NULL) OR (sha1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE sha1_lilunzhi END) AS numeric(38, 2))) AS glll1,");	
		sql.append("SUM(CAST((CASE WHEN (shi1_lilunzhi IS NULL) OR (shi1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shi1_lilunzhi END) AS numeric(38, 2))) AS glll2,");				
		sql.append("SUM(CAST((CASE WHEN (shi2_lilunzhi IS NULL) OR (shi2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE shi2_lilunzhi END) AS numeric(38, 2))) AS glll3,");
		sql.append("SUM(CAST((CASE WHEN (sha2_lilunzhi IS NULL) OR (sha2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE sha2_lilunzhi END) AS numeric(38, 2))) AS glll4,");
		sql.append("SUM(CAST((CASE WHEN (guliao5_lilunzhi IS NULL) OR (guliao5_lilunzhi = '') ");
		sql.append("THEN '0' ELSE guliao5_lilunzhi END) AS numeric(38, 2))) AS glll5,");
		sql.append("SUM(CAST((CASE WHEN (waijiaji1_lilunzhi IS NULL) OR (waijiaji1_lilunzhi = '') ");
		sql.append("THEN '0' ELSE waijiaji1_lilunzhi END) AS numeric(38, 2))) AS wjll1,");		
		sql.append("SUM(CAST((CASE WHEN (waijiaji2_lilunzhi IS NULL) OR (waijiaji2_lilunzhi = '') ");
		sql.append("THEN '0' ELSE waijiaji2_lilunzhi END) AS numeric(38, 2))) AS wjll2,");
		sql.append("SUM(CAST((CASE WHEN (waijiaji3_lilunzhi IS NULL) OR (waijiaji3_lilunzhi = '') ");
		sql.append("THEN '0' ELSE waijiaji3_lilunzhi END) AS numeric(38, 2))) AS wjll3,");
		sql.append("SUM(CAST((CASE WHEN (waijiaji4_lilunzhi IS NULL) OR (waijiaji4_lilunzhi = '') ");
		sql.append("THEN '0' ELSE waijiaji4_lilunzhi END) AS numeric(38, 2))) AS wjll4");
	}
	
	private void appendShijizhiSql(StringBuffer sql) {
		sql.append("SELECT SUM(CAST((CASE WHEN (shui1_shijizhi IS NULL) OR (shui1_shijizhi = '') ");
		sql.append("THEN '0' ELSE shui1_shijizhi END) AS numeric(38, 2))) AS shuisj1,");
		sql.append("SUM(CAST((CASE WHEN (shui2_shijizhi IS NULL) OR (shui2_shijizhi = '') ");
		sql.append("THEN '0' ELSE shui2_shijizhi END) AS numeric(38, 2))) AS shuisj2,");
		sql.append("SUM(CAST((CASE WHEN (shuini1_shijizhi IS NULL) OR (shuini1_shijizhi = '') ");
		sql.append("THEN '0' ELSE shuini1_shijizhi END) AS numeric(38, 2))) AS flsj1,");
		sql.append("SUM(CAST((CASE WHEN (shuini2_shijizhi IS NULL) OR (shuini2_shijizhi = '') ");
		sql.append("THEN '0' ELSE shuini2_shijizhi END) AS numeric(38, 2))) AS flsj2,");		
		sql.append("SUM(CAST((CASE WHEN (kuangfen3_shijizhi IS NULL) OR (kuangfen3_shijizhi = '') ");
		sql.append("THEN '0' ELSE kuangfen3_shijizhi END) AS numeric(38, 2))) AS flsj3,");
		sql.append("SUM(CAST((CASE WHEN (feimeihui4_shijizhi IS NULL) OR (feimeihui4_shijizhi = '') ");
		sql.append("THEN '0' ELSE feimeihui4_shijizhi END) AS numeric(38, 2))) AS flsj4,");		
		sql.append("SUM(CAST((CASE WHEN (fenliao5_shijizhi IS NULL) OR (fenliao5_shijizhi = '') ");
		sql.append("THEN '0' ELSE fenliao5_shijizhi END) AS numeric(38, 2))) AS flsj5,");
		sql.append("SUM(CAST((CASE WHEN (fenliao6_shijizhi IS NULL) OR (fenliao6_shijizhi = '') ");
		sql.append("THEN '0' ELSE fenliao6_shijizhi END) AS numeric(38, 2))) AS flsj6,");	
		sql.append("SUM(CAST((CASE WHEN (sha1_shijizhi IS NULL) OR (sha1_shijizhi = '') ");
		sql.append("THEN '0' ELSE sha1_shijizhi END) AS numeric(38, 2))) AS glsj1,");	
		sql.append("SUM(CAST((CASE WHEN (shi1_shijizhi IS NULL) OR (shi1_shijizhi = '') ");
		sql.append("THEN '0' ELSE shi1_shijizhi END) AS numeric(38, 2))) AS glsj2,");		
		sql.append("SUM(CAST((CASE WHEN (shi2_shijizhi IS NULL) OR (shi2_shijizhi = '') ");
		sql.append("THEN '0' ELSE shi2_shijizhi END) AS numeric(38, 2))) AS glsj3,");
		sql.append("SUM(CAST((CASE WHEN (sha2_shijizhi IS NULL) OR (sha2_shijizhi = '') ");
		sql.append("THEN '0' ELSE sha2_shijizhi END) AS numeric(38, 2))) AS glsj4,");
		sql.append("SUM(CAST((CASE WHEN (guliao5_shijizhi IS NULL) OR (guliao5_shijizhi = '') ");
		sql.append("THEN '0' ELSE guliao5_shijizhi END) AS numeric(38, 2))) AS glsj5,");	
		sql.append("SUM(CAST((CASE WHEN (waijiaji1_shijizhi IS NULL) OR (waijiaji1_shijizhi = '') ");
		sql.append("THEN '0' ELSE waijiaji1_shijizhi END) AS numeric(38, 2))) AS wjsj1,");		
		sql.append("SUM(CAST((CASE WHEN (waijiaji2_shijizhi IS NULL) OR (waijiaji2_shijizhi = '') ");
		sql.append("THEN '0' ELSE waijiaji2_shijizhi END) AS numeric(38, 2))) AS wjsj2,");				
		sql.append("SUM(CAST((CASE WHEN (waijiaji3_shijizhi IS NULL) OR (waijiaji3_shijizhi = '') ");
		sql.append("THEN '0' ELSE waijiaji3_shijizhi END) AS numeric(38, 2))) AS wjsj3,");				
		sql.append("SUM(CAST((CASE WHEN (waijiaji4_shijizhi IS NULL) OR (waijiaji4_shijizhi = '') ");
		sql.append("THEN '0' ELSE waijiaji4_shijizhi END) AS numeric(38, 2))) AS wjsj4");	
	}
	
	private void appendFangshuSql(StringBuffer sql) {
		sql.append("SELECT SUM(CAST((CASE WHEN (gujifangshu IS NULL) OR (gujifangshu = '') ");
		sql.append("THEN '0' ELSE gujifangshu END) AS numeric(38, 2))) AS gujifangshu,COUNT(bianhao) as pangshu");
	}
	
	private void appenShuliangSql(StringBuffer sql) {		
		sql.append("select banhezhanminchen,Max(chuliaoshijian) as chuliaoshijian,gongchengmingcheng,");
		sql.append("sigongdidian,jiaozuobuwei,peifanghao,qiangdudengji,");
		sql.append("SUM(CAST((CASE WHEN (gujifangshu IS NULL) OR (gujifangshu = '') ");
		sql.append("THEN '0' ELSE gujifangshu END) AS numeric(38, 2))) AS gujifangshu ");
		sql.append("FROM HunnintuView");
	}
	
	@Override
	public HunnintuView materiallist(String gongchengmingcheng,String jiaozhubuwei,
			String startTime, String endTime, String shebeibianhao, 
			Integer biaoduan, Integer xiangmubu, String fn, int bsid) {
		HunnintuView hv = null;
		StringBuffer sql = new StringBuffer();
		appendSql(sql);
		sql.append(" FROM HunnintuView");
		sql.append(" where 1=1 ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());
		
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		if (StringUtil.Null2Blank(shebeibianhao).length()>0 && !shebeibianhao.equalsIgnoreCase("''")) {			
			sql.append(" and shebeibianhao in ("+shebeibianhao+")");
		}
		if(StringUtil.Null2Blank(gongchengmingcheng).length()>0){			
			sql.append(" and gongchengmingcheng='"+gongchengmingcheng+"'");
		}
		if(StringUtil.Null2Blank(jiaozhubuwei).length()>0){			
			sql.append(" and jiaozuobuwei='"+jiaozhubuwei+"'");
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
		//CallableStatement cs = null;	
		Statement st = null;
		Connection con = null;
		String qsql = sql.toString();
		
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();			
		
			if (true) {
				rs = st.executeQuery(qsql);
				if (rs.next()) {
					hv = new HunnintuView();
					/**
					 * 
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setGongdanhao(rs.getString("gongdanhao"));
					hv.setSigongdidian(rs.getString("sigongdidian"));
					hv.setJiaozuobuwei(rs.getString("jiaozuobuwei"));
					hv.setChuliaoshijian(rs.getString("chuliaoshijian"));
					hv.setChaozuozhe(rs.getString("chaozuozhe"));
					hv.setJiaobanshijian(rs.getString("jiaobanshijian"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));
					 */
					
					hv.setShui1_shijizhi(String.format("%1$.2f",rs.getDouble("shuisj1")));
					hv.setShui2_shijizhi(String.format("%1$.2f",rs.getDouble("shuisj2")));
					hv.setShuini1_shijizhi(String.format("%1$.2f",rs.getDouble("flsj1")));
					hv.setShuini2_shijizhi(String.format("%1$.2f",rs.getDouble("flsj2")));
					hv.setKuangfen3_shijizhi(String.format("%1$.2f",rs.getDouble("flsj3")));
					hv.setFeimeihui4_shijizhi(String.format("%1$.2f",rs.getDouble("flsj4")));				
					hv.setFenliao5_shijizhi(String.format("%1$.2f",rs.getDouble("flsj5")));
					hv.setFenliao6_shijizhi(String.format("%1$.2f",rs.getDouble("flsj6")));
					hv.setSha1_shijizhi(String.format("%1$.2f",rs.getDouble("glsj1")));
					hv.setShi1_shijizhi(String.format("%1$.2f",rs.getDouble("glsj2")));
					hv.setShi2_shijizhi(String.format("%1$.2f",rs.getDouble("glsj3")));
					hv.setSha2_shijizhi(String.format("%1$.2f",rs.getDouble("glsj4")));					
					hv.setGuliao5_shijizhi(String.format("%1$.2f",rs.getDouble("glsj5")));					
					hv.setWaijiaji1_shijizhi(String.format("%1$.2f",rs.getDouble("wjsj1")));
					hv.setWaijiaji2_shijizhi(String.format("%1$.2f",rs.getDouble("wjsj2")));
					hv.setWaijiaji3_shijizhi(String.format("%1$.2f",rs.getDouble("wjsj3")));
					hv.setWaijiaji4_shijizhi(String.format("%1$.2f",rs.getDouble("wjsj4")));
					
					hv.setShui1_lilunzhi(String.format("%1$.2f",rs.getDouble("shuill1")));
					hv.setShui2_lilunzhi(String.format("%1$.2f",rs.getDouble("shuill2")));	
					hv.setShuini1_lilunzhi(String.format("%1$.2f",rs.getDouble("flll1")));
					hv.setShuini2_lilunzhi(String.format("%1$.2f",rs.getDouble("flll2")));
					hv.setKuangfen3_lilunzhi(String.format("%1$.2f",rs.getDouble("flll3")));
					hv.setFeimeihui4_lilunzhi(String.format("%1$.2f",rs.getDouble("flll4")));				
					hv.setFenliao5_lilunzhi(String.format("%1$.2f",rs.getDouble("flll5")));
					hv.setFenliao6_lilunzhi(String.format("%1$.2f",rs.getDouble("flll6")));
					hv.setSha1_lilunzhi(String.format("%1$.2f",rs.getDouble("glll1")));
					hv.setShi1_lilunzhi(String.format("%1$.2f",rs.getDouble("glll2")));
					hv.setShi2_lilunzhi(String.format("%1$.2f",rs.getDouble("glll3")));
					hv.setSha2_lilunzhi(String.format("%1$.2f",rs.getDouble("glll4")));	
					hv.setGuliao5_lilunzhi(String.format("%1$.2f",rs.getDouble("glll5")));	
					
					hv.setWaijiaji1_lilunzhi(String.format("%1$.2f",rs.getDouble("wjll1")));
					hv.setWaijiaji2_lilunzhi(String.format("%1$.2f",rs.getDouble("wjll2")));
					hv.setWaijiaji3_lilunzhi(String.format("%1$.2f",rs.getDouble("wjll3")));
					hv.setWaijiaji4_lilunzhi(String.format("%1$.2f",rs.getDouble("wjll4")));
					
					hv.setGujifangshu(String.format("%1$.2f",rs.getDouble("gujifangshu")));
					hv.setSha1chazhi(String.format("%1$.2f",rs.getDouble("sha1chazhi")));
					hv.setSha2chazhi(String.format("%1$.2f",rs.getDouble("sha2chazhi")));
					hv.setShi1chazhi(String.format("%1$.2f",rs.getDouble("shi1chazhi")));
					hv.setShi2chazhi(String.format("%1$.2f",rs.getDouble("shi2chazhi")));
					hv.setGuliao5chazhi(String.format("%1$.2f",rs.getDouble("guliao5chazhi")));
					hv.setShui1chazhi(String.format("%1$.2f",rs.getDouble("shui1chazhi")));
					hv.setShui2chazhi(String.format("%1$.2f",rs.getDouble("shui2chazhi")));
					hv.setShuini1chazhi(String.format("%1$.2f",rs.getDouble("shuini1chazhi")));
					hv.setShuini2chazhi(String.format("%1$.2f",rs.getDouble("shuini2chazhi")));
					hv.setKuangfen3chazhi(String.format("%1$.2f",rs.getDouble("kuangfen3chazhi")));
					hv.setFeimeihui4chazhi(String.format("%1$.2f",rs.getDouble("feimeihui4chazhi")));
					hv.setFenliao5chazhi(String.format("%1$.2f",rs.getDouble("fenliao5chazhi")));
					hv.setFenliao6chazhi(String.format("%1$.2f",rs.getDouble("fenliao6chazhi")));
					hv.setWaijiaji1chazhi(String.format("%1$.2f",rs.getDouble("waijiaji1chazhi")));
					hv.setWaijiaji2chazhi(String.format("%1$.2f",rs.getDouble("waijiaji2chazhi")));
					hv.setWaijiaji3chazhi(String.format("%1$.2f",rs.getDouble("waijiaji3chazhi")));
					hv.setWaijiaji4chazhi(String.format("%1$.2f",rs.getDouble("waijiaji4chazhi")));
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

	@Override
	public List<HunnintuView> hntsclhslist(String startTime, String endTime,
			String shebeibianhao, String gongchengmingcheng, String jiaozuobuwei, String qiangdudengji, 
			Integer biaoduan, Integer xiangmubu, String fn, int bsid) {
		List<HunnintuView> _returnValue = new ArrayList<HunnintuView>();
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
		
		if(StringUtil.Null2Blank(gongchengmingcheng).length()>0){			
			sql.append(" and gongchengmingcheng='"+gongchengmingcheng+"'");
		}
		if(StringUtil.Null2Blank(jiaozuobuwei).length()>0){			
			sql.append(" and jiaozuobuwei='"+jiaozuobuwei+"'");
		}
		if(StringUtil.Null2Blank(qiangdudengji).length()>0){			
			sql.append(" and qiangdudengji='"+qiangdudengji+"'");
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
		
		sql.append(" group by banhezhanminchen,gongchengmingcheng,sigongdidian,jiaozuobuwei,peifanghao,qiangdudengji");
		
		ResultSet rs = null;
		//CallableStatement cs = null;	
		Statement st = null;
		Connection con = null;
		String qsql = sql.toString();
		
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();			
		
			if (true) {
				rs = st.executeQuery(qsql);
				while (rs.next()) {
					HunnintuView hv = new HunnintuView();		
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setGongchengmingcheng(rs.getString("gongchengmingcheng"));
					hv.setSigongdidian(rs.getString("sigongdidian"));
					hv.setJiaozuobuwei(rs.getString("jiaozuobuwei"));
					hv.setChuliaoshijian(rs.getString("chuliaoshijian"));
					hv.setPeifanghao(rs.getString("peifanghao"));
					hv.setQiangdudengji(rs.getString("qiangdudengji"));		
					hv.setGujifangshu(String.format("%1$.2f",rs.getDouble("gujifangshu")));
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
	public List<HunnintuView> hntcnfxlist(String startTime, String endTime,
			String shebeibianhao, Integer biaoduan, Integer xiangmubu, int cnfxlx, String fn, int bsid) {
		List<HunnintuView> _returnValue = new ArrayList<HunnintuView>();
		StringBuffer sql = new StringBuffer();
		appendFangshuSql(sql);	
	    switch (cnfxlx) {
		case 1://季度
			sql.append(",datename(year, baocunshijian) as xa, datename(quarter, baocunshijian) as xb");
			sql.append(" FROM HunnintuView");
			break;
		case 2://月份
			sql.append(",datename(year, baocunshijian) as xa, datename(month, baocunshijian) as xb");
			sql.append(" FROM HunnintuView");
			break;
		case 3://周
			sql.append(",datename(year, baocunshijian) as xa, datename(week, baocunshijian) as xb");
			sql.append(" FROM HunnintuView");
			break;
		default:
			break;
		}
	    sql.append(" where 1=1 ");
	    
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and shebeibianhao = '"+shebeibianhao+"'");
		}
		
		if (null != biaoduan) {
			sql.append(" and biaoduanid=" + biaoduan);
		}
		if (null != xiangmubu) {
			sql.append(" and xiangmubuid=" + xiangmubu);
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
		
		switch (cnfxlx) {
		case 1:
			sql.append(" group by datename(year, baocunshijian), datename(quarter, baocunshijian) order by datename(quarter, baocunshijian)");
			break;
		case 2:
			sql.append(" group by datename(year, baocunshijian), datename(month, baocunshijian) order by datename(month, baocunshijian)");
			break;
		case 3:
			sql.append(" group by datename(year, baocunshijian), datename(week, baocunshijian) order by datename(week, baocunshijian)");
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
					HunnintuView hv = new HunnintuView();
					hv.setXa(rs.getString("xa"));
					hv.setXb(rs.getString("xb"));
					hv.setPangshu(rs.getString("pangshu"));
					hv.setGujifangshu(String.format("%1$.2f",rs.getDouble("gujifangshu")));
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
	public HunnintuView hntcnfxdetail(String startTime, String endTime,
			String shebeibianhao, String biaod, String xiangmb, String myvar1, String myvar2, int cnfxlx, String fn, int bsid) {
		HunnintuView hv = null;
		StringBuffer sql = new StringBuffer();
		appendShijizhiSql(sql);	
	    switch (cnfxlx) {
		case 1://季度
			sql.append(",datename(year, baocunshijian) as xa, datename(quarter, baocunshijian) as xb");
			sql.append(" FROM HunnintuView");
			break;
		case 2://月份
			sql.append(",datename(year, baocunshijian) as xa, datename(month, baocunshijian) as xb");
			sql.append(" FROM HunnintuView");
			break;
		case 3://周
			sql.append(",datename(year, baocunshijian) as xa, datename(week, baocunshijian) as xb");
			sql.append(" FROM HunnintuView");
			break;
		default:
			break;
		}
	    sql.append(" where 1=1 ");
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and shebeibianhao = '"+shebeibianhao+"'");
		}
		
		if (StringUtil.Null2Blank(biaod).length()>0) {			
			sql.append(" and biaoduanid ="+biaod);
		}
		
		if (StringUtil.Null2Blank(xiangmb).length()>0) {			
			sql.append(" and xiangmubuid ="+xiangmb);
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
					hv = new HunnintuView();
					hv.setXa(rs.getString("xa"));
					hv.setXb(rs.getString("xb"));
					hv.setShui1_shijizhi(String.format("%1$.2f",rs.getDouble("shuisj1")));
					hv.setShui2_shijizhi(String.format("%1$.2f",rs.getDouble("shuisj2")));
					hv.setShuini1_shijizhi(String.format("%1$.2f",rs.getDouble("flsj1")));
					hv.setShuini2_shijizhi(String.format("%1$.2f",rs.getDouble("flsj2")));
					hv.setKuangfen3_shijizhi(String.format("%1$.2f",rs.getDouble("flsj3")));
					hv.setFeimeihui4_shijizhi(String.format("%1$.2f",rs.getDouble("flsj4")));				
					hv.setFenliao5_shijizhi(String.format("%1$.2f",rs.getDouble("flsj5")));
					hv.setFenliao6_shijizhi(String.format("%1$.2f",rs.getDouble("flsj6")));
					hv.setSha1_shijizhi(String.format("%1$.2f",rs.getDouble("glsj1")));
					hv.setShi1_shijizhi(String.format("%1$.2f",rs.getDouble("glsj2")));
					hv.setShi2_shijizhi(String.format("%1$.2f",rs.getDouble("glsj3")));
					hv.setSha2_shijizhi(String.format("%1$.2f",rs.getDouble("glsj4")));					
					hv.setGuliao5_shijizhi(String.format("%1$.2f",rs.getDouble("glsj5")));					
					hv.setWaijiaji1_shijizhi(String.format("%1$.2f",rs.getDouble("wjsj1")));
					hv.setWaijiaji2_shijizhi(String.format("%1$.2f",rs.getDouble("wjsj2")));
					hv.setWaijiaji3_shijizhi(String.format("%1$.2f",rs.getDouble("wjsj3")));
					hv.setWaijiaji4_shijizhi(String.format("%1$.2f",rs.getDouble("wjsj4")));					
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

	@Override
	public HunnintuView hntcnfxlilundetail(String startTime, String endTime,
			String shebeibianhao, String biaod, String xiangmb, String myvar1, String myvar2, int cnfxlx, String fn, int bsid) {
		HunnintuView hv = null;
		StringBuffer sql = new StringBuffer();
		appendlilunSql(sql);	
	    switch (cnfxlx) {
		case 1://季度
			sql.append(",datename(year, baocunshijian) as xa, datename(quarter, baocunshijian) as xb");
			sql.append(" FROM HunnintuView");
			break;
		case 2://月份
			sql.append(",datename(year, baocunshijian) as xa, datename(month, baocunshijian) as xb");
			sql.append(" FROM HunnintuView");
			break;
		case 3://周
			sql.append(",datename(year, baocunshijian) as xa, datename(week, baocunshijian) as xb");
			sql.append(" FROM HunnintuView");
			break;
		default:
			break;
		}
	    sql.append(" where 1=1 ");
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());
		
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and shebeibianhao = '"+shebeibianhao+"'");
		}
		
		if (StringUtil.Null2Blank(biaod).length()>0) {			
			sql.append(" and biaoduanid ="+biaod);
		}
		
		if (StringUtil.Null2Blank(xiangmb).length()>0) {			
			sql.append(" and xiangmubuid ="+xiangmb);
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
					hv = new HunnintuView();
					hv.setXa(rs.getString("xa"));
					hv.setXb(rs.getString("xb"));
					hv.setShui1_lilunzhi(String.format("%1$.2f",rs.getDouble("shuill1")));
					hv.setShui2_lilunzhi(String.format("%1$.2f",rs.getDouble("shuill2")));	
					hv.setShuini1_lilunzhi(String.format("%1$.2f",rs.getDouble("flll1")));
					hv.setShuini2_lilunzhi(String.format("%1$.2f",rs.getDouble("flll2")));
					hv.setKuangfen3_lilunzhi(String.format("%1$.2f",rs.getDouble("flll3")));
					hv.setFeimeihui4_lilunzhi(String.format("%1$.2f",rs.getDouble("flll4")));				
					hv.setFenliao5_lilunzhi(String.format("%1$.2f",rs.getDouble("flll5")));
					hv.setFenliao6_lilunzhi(String.format("%1$.2f",rs.getDouble("flll6")));
					hv.setSha1_lilunzhi(String.format("%1$.2f",rs.getDouble("glll1")));
					hv.setShi1_lilunzhi(String.format("%1$.2f",rs.getDouble("glll2")));
					hv.setShi2_lilunzhi(String.format("%1$.2f",rs.getDouble("glll3")));
					hv.setSha2_lilunzhi(String.format("%1$.2f",rs.getDouble("glll4")));	
					hv.setGuliao5_lilunzhi(String.format("%1$.2f",rs.getDouble("glll5")));	
					hv.setWaijiaji1_lilunzhi(String.format("%1$.2f",rs.getDouble("wjll1")));
					hv.setWaijiaji2_lilunzhi(String.format("%1$.2f",rs.getDouble("wjll2")));
					hv.setWaijiaji3_lilunzhi(String.format("%1$.2f",rs.getDouble("wjll3")));
					hv.setWaijiaji4_lilunzhi(String.format("%1$.2f",rs.getDouble("wjll4")));					
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

	@Override
	public HunnintujieguoPageMode viewjieguolist(String shebeibianhao,
			String gongchenghao, String startTimeOne, String endTimeOne,
			String jiaozhubuwei, Integer biaoduan, Integer xiangmubu,
			String fn, int bsid, int offset, int pagesize) {
		List<HunnintujieguoView> _returnValue = new ArrayList<HunnintujieguoView>();
		HunnintujieguoPageMode pagemode = new HunnintujieguoPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "HunnintujieguoView";
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
					HunnintujieguoView hv = new HunnintujieguoView();
					hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					hv.setJianchen(rs.getString("jianchen"));
					hv.setGongchengmingcheng(rs.getString("gongchengmingcheng"));
					hv.setSigongdidian(rs.getString("sigongdidian"));
					hv.setJiaozuobuwei(rs.getString("jiaozuobuwei"));
					hv.setJiaobanshijian(rs.getString("jiaobanshijian"));
					hv.setBianhao(rs.getInt("bianhao"));
					hv.setBaocunshijian(rs.getString("baocunshijian"));					
					hv.setChuliaoshijian(rs.getString("chuliaoshijian"));					
					hv.setFenliao5_shijizhi(rs.getString("fenliao5_shijizhi"));
					hv.setFenliao6_shijizhi(rs.getString("fenliao6_shijizhi"));
					hv.setSha2_shijizhi(rs.getString("sha2_shijizhi"));
					hv.setSha1_shijizhi(rs.getString("sha1_shijizhi"));
					hv.setShi1_shijizhi(rs.getString("shi1_shijizhi"));
					hv.setShi2_shijizhi(rs.getString("shi2_shijizhi"));
					hv.setShui2_shijizhi(rs.getString("shui2_shijizhi"));
					hv.setWaijiaji2_shijizhi(rs.getString("waijiaji2_shijizhi"));
					hv.setWaijiaji3_shijizhi(rs.getString("waijiaji3_shijizhi"));
					hv.setWaijiaji4_shijizhi(rs.getString("waijiaji4_shijizhi"));
					hv.setGuliao5_shijizhi(rs.getString("guliao5_shijizhi"));
					hv.setGprsbianhao(rs.getString("gprsbianhao"));
					hv.setXinxibianhao(rs.getInt("xinxibianhao"));
					hv.setLeiji(rs.getInt("leiji"));
					hv.setShuini1_shijizhi(rs.getString("shuini1_shijizhi"));
					hv.setShuini2_shijizhi(rs.getString("shuini2_shijizhi"));
					hv.setKuangfen3_shijizhi(rs.getString("kuangfen3_shijizhi"));
					hv.setFeimeihui4_shijizhi(rs.getString("feimeihui4_shijizhi"));
					hv.setShui1_shijizhi(rs.getString("shui1_shijizhi"));
					hv.setWaijiaji1_shijizhi(rs.getString("waijiaji1_shijizhi"));				
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
	public GenericPageMode viewswlist(String shebeibianhao,
			String startTimeOne, String endTimeOne,
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize,String llbuwei) {
		List<ShuiwenxixxView> _returnValue = new ArrayList<ShuiwenxixxView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "ShuiwenxixxView";
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
		if(StringUtil.Null2Blank(llbuwei).length() >0){
			queryCondition += " and llbuwei='" + llbuwei+"'";
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
					ShuiwenxixxView swxx = new ShuiwenxixxView();
					swxx.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					swxx.setJianchen(rs.getString("jianchen"));
					swxx.setXiangmubuminchen(rs.getString("xiangmubuminchen"));
					swxx.setBianhao(rs.getInt("bianhao"));
					swxx.setBaocunshijian(rs.getString("baocunshijian"));			
					swxx.setShebeibianhao(rs.getString("shebeibianhao"));
					swxx.setCaijishijian(rs.getString("caijishijian"));	
					try{
						swxx.setSjgl1(String.format("%1.2f", rs.getFloat("sjgl1")));
					}catch(Exception ex){}
					try{
						swxx.setSjgl2(String.format("%1.2f", rs.getFloat("sjgl2")));
					}catch(Exception ex){}
					try{
						swxx.setSjgl3(String.format("%1.2f", rs.getFloat("sjgl3")));
					}catch(Exception ex){}
					try{
						swxx.setSjgl4(String.format("%1.2f", rs.getFloat("sjgl4")));
					}catch(Exception ex){}
					try{
						swxx.setSjgl5(String.format("%1.2f", rs.getFloat("sjgl5")));
					}catch(Exception ex){}
					try{
						swxx.setSjfl1(String.format("%1.2f", rs.getFloat("sjfl1")));
					}catch(Exception ex){}
					try{
						swxx.setSjfl2(String.format("%1.2f", rs.getFloat("sjfl2")));
					}catch(Exception ex){}
					try{
						swxx.setSjshui(String.format("%1.2f", rs.getFloat("sjshui")));
					}catch(Exception ex){}
					try{
						swxx.setLlgl1(String.format("%1.2f", rs.getFloat("llgl1")));
					}catch(Exception ex){}
					try{
						swxx.setLlgl2(String.format("%1.2f", rs.getFloat("llgl2")));
					}catch(Exception ex){}
					try{
						swxx.setLlgl3(String.format("%1.2f", rs.getFloat("llgl3")));
					}catch(Exception ex){}
					try{
						swxx.setLlgl4(String.format("%1.2f", rs.getFloat("llgl4")));
					}catch(Exception ex){}
					try{
						swxx.setLlgl5(String.format("%1.2f", rs.getFloat("llgl5")));
					}catch(Exception ex){}
					try{
						swxx.setLlfl1(String.format("%1.2f", rs.getFloat("llfl1")));
					}catch(Exception ex){}
					try{
						swxx.setLlfl2(String.format("%1.2f", rs.getFloat("llfl2")));
					}catch(Exception ex){}
					try{
						swxx.setLlshui(String.format("%1.2f", rs.getFloat("llshui")));
					}catch(Exception ex){}
					swxx.setPersjgl1(rs.getString("persjgl1"));
					swxx.setPersjgl2(rs.getString("persjgl2"));
					swxx.setPersjgl3(rs.getString("persjgl3"));
					swxx.setPersjgl4(rs.getString("persjgl4"));
					swxx.setPersjgl5(rs.getString("persjgl5"));
					swxx.setPersjfl1(rs.getString("persjfl1"));
					swxx.setPersjfl2(rs.getString("persjfl2"));
					//swxx.setPersjshui(rs.getString("persjshui"));
					swxx.setShijian(rs.getString("shijian"));
					swxx.setShijianS(rs.getString("shijianS"));
					swxx.setShijianE(rs.getString("shijianE"));
					swxx.setGlchangliang(rs.getString("glchangliang"));
					swxx.setLlbuwei(rs.getString("llbuwei"));
					_returnValue.add(swxx);
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
	public GenericPageMode viewtemplist(String shebeibianhao,
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize) {
		List<ChuliaokouTemperaturedataView> _returnValue = new ArrayList<ChuliaokouTemperaturedataView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "ChuliaokouTemperaturedataView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition =  " (convert(datetime,tmpshijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and tmpno='" + shebeibianhao + "'";
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
			cs.setString(3, "tmpid");
			cs.setString(4, "tmpid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					ChuliaokouTemperaturedataView gprstempdata = new ChuliaokouTemperaturedataView();
					gprstempdata.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					gprstempdata.setJianchen(rs.getString("jianchen"));
					gprstempdata.setTmpid(rs.getInt("tmpid"));
					gprstempdata.setTmpdata(rs.getString("tmpdata"));
					gprstempdata.setTmpshijian(rs.getString("tmpshijian"));
					_returnValue.add(gprstempdata);
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
	
	//登录日志
	public GenericPageMode viewuserlogtongjilist(String username,String danwei,String zhiwei,String startTimeOne,
		String endTimeOne, Integer usertype, Integer biaoduan, Integer xiangmubu, Integer pageNo, int maxPageItems, String shebeibianhao){
		List<UserlogView> _returnValue = new ArrayList<UserlogView>();
		StringBuffer sql = new StringBuffer();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "UserlogtjView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		sql.append(" (convert(datetime,logdate,121) between '" + strstartTime
		+ "' and '" + strendTime + "')");
		
		
		if (StringUtil.Null2Blank(username).length() > 0) {
			sql.append(" and [name] like '%" + username + "%'");
		}
		if (StringUtil.Null2Blank(danwei).length() > 0) {
			sql.append(" and fullname like '%" + danwei + "%'");
		}
		if (StringUtil.Null2Blank(zhiwei).length() > 0) {
			sql.append(" and remark like '%" + zhiwei + "%'");
		}
		
		if (null != usertype) {
			sql.append(" and usertype=");
			sql.append(usertype);		
		}
		sql.append(" and (1=2");
		if (StringUtil.Null2Blank(shebeibianhao)!="") {			
			sql.append(" or (biaoshiid=");
			sql.append(shebeibianhao);
			sql.append(" and usertype=5))");
		}else if (null != xiangmubu) {
			sql.append(" or (biaoshiid=");
			sql.append(xiangmubu);
			sql.append(" and usertype=3)");
			List<Zuoyeduixinxi> zydlist = (List<Zuoyeduixinxi>)zydDAO.find("from Zuoyeduixinxi where xiangmubuid=?", xiangmubu);
			for (Zuoyeduixinxi zyd : zydlist) {
				sql.append(" or (biaoshiid=");
				sql.append(zyd.getId());
				sql.append(" and usertype=4)");
			}
			List<Banhezhanxinxi> bhzlist = (List<Banhezhanxinxi>)bhzDAO.find("from Banhezhanxinxi where xiangmubuid=?", xiangmubu);
			for (Banhezhanxinxi bhz : bhzlist) {
				sql.append(" or (biaoshiid=");
				sql.append(bhz.getId());
				sql.append(" and usertype=5)");
			}
			sql.append(")");
		} else if (null != biaoduan) {
			sql.append(" or (biaoshiid=");
			sql.append(biaoduan);
			sql.append(" and usertype=2)");
			List<Xiangmubuxinxi> xmblist = (List<Xiangmubuxinxi>)xmbDAO.find("from Xiangmubuxinxi where biaoduanid=?", biaoduan);
			for (Xiangmubuxinxi xmb : xmblist) {
				sql.append(" or (biaoshiid=");
				sql.append(xmb.getId());
				sql.append(" and usertype=3)");
			}
			List<Zuoyeduixinxi> zydlist = (List<Zuoyeduixinxi>)zydDAO.find("from Zuoyeduixinxi where biaoduanid=?", biaoduan);
			for (Zuoyeduixinxi zyd : zydlist) {
				sql.append(" or (biaoshiid=");
				sql.append(zyd.getId());
				sql.append(" and usertype=4)");
			}
			List<Banhezhanxinxi> bhzlist = (List<Banhezhanxinxi>)bhzDAO.find("from Banhezhanxinxi where biaoduanid=?", biaoduan);
			for (Banhezhanxinxi bhz : bhzlist) {
				sql.append(" or (biaoshiid=");
				sql.append(bhz.getId());
				sql.append(" and usertype=5)");
			}
			sql.append(")");	
		} else {
			sql.append(" or 1=1)");	
		}
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "logdate");
			cs.setString(4, "logdate DESC");
			cs.setInt(5, maxPageItems);
			cs.setInt(6, pageNo);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, sql.toString());
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					UserlogView userlog = new UserlogView();		
					userlog.setLogdate(rs.getString("logdate"));
					userlog.setLogip(rs.getString("logcount"));
					userlog.setFullname(rs.getString("fullname"));
					userlog.setName(rs.getString("name"));
					userlog.setRemark(rs.getString("remark"));
					userlog.setId(rs.getInt("id"));
					_returnValue.add(userlog);
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
			} catch (SQLException e) {
			}
			try {				
				cs.close();
			} catch (SQLException e1) {
			}
			try {
				con.close();
			} catch (SQLException e) {
			}
		}
		
		return pagemode;
	}	
	
	@Override
	public GenericPageMode viewuserloglist(String shebeibianhao,String startTimeOne,
			String endTimeOne, int offset, int pagesize) {
		List<Userlog> _returnValue = new ArrayList<Userlog>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "UserlogView";
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
		queryCondition.append(" (convert(datetime,logdate,121) between '" + strstartTime
		+ "' and '" + strendTime + "')");
		
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition.append(" and [name] like '%" + shebeibianhao + "%'");
		}
		
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "logid");
			cs.setString(4, "logid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition.toString());
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					Userlog userlog = new Userlog();
					userlog.setLogdate(rs.getString("logdate"));
					userlog.setLogname(rs.getString("name"));
					userlog.setLogoperate(rs.getString("logoperate"));
					userlog.setLogip(rs.getString("logip"));
					_returnValue.add(userlog);
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
	public List<UserlogView> viewuserlogtongjilist(String shebeibianhao,String startTimeOne,
			String endTimeOne) {
		List<UserlogView> _returnValue = new ArrayList<UserlogView>();
		StringBuffer sql = new StringBuffer();
		sql.append("select count(logid) as logcount, [name], max(fullname) as fullname,max(remark) as remark,max(logdate) as logdate, [id] from UserlogView");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		sql.append(" where (convert(datetime,logdate,121) between '" + strstartTime
		+ "' and '" + strendTime + "')");
		
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			sql.append(" and [name] like '%" + shebeibianhao + "%'");
		}
		
		sql.append(" group by id,name  order by logdate desc");
		
		ResultSet rs = null;
		//CallableStatement cs = null;	
		Statement st = null;
		Connection con = null;
		String qsql = sql.toString();
		
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();			
		
			if (true) {
				rs = st.executeQuery(qsql);
				while (rs.next()) {
					UserlogView userlog = new UserlogView();		
					userlog.setLogdate(rs.getString("logdate"));
					userlog.setLogip(rs.getString("logcount"));
					userlog.setFullname(rs.getString("fullname"));
					userlog.setName(rs.getString("name"));
					userlog.setRemark(rs.getString("remark"));
					userlog.setId(rs.getInt("id"));
					_returnValue.add(userlog);
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
	public GenericPageMode viewtanpuspeedlist(String shebeibianhao,
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize) {
		List<SpeeddataView> _returnValue = new ArrayList<SpeeddataView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "SpeeddataView1";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition="(convert(datetime,shijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and gpsno='" + shebeibianhao + "'";
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
					SpeeddataView tmpdata = new SpeeddataView();
					tmpdata.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					tmpdata.setJianchen(rs.getString("jianchen"));
					tmpdata.setGpsid(rs.getInt("gpsid"));
					tmpdata.setGpsno(rs.getString("gpsno"));
					try{
						tmpdata.setSudu(String.format("%1$.1f",rs.getFloat("sudu")*1000/60));
					}catch(Exception ex){}
					tmpdata.setShijian(rs.getString("shijian"));
					_returnValue.add(tmpdata);
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
	public GenericPageMode viewNianyaTemplist(String shebeibianhao,
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize) {
		List<TemperaturedataView> _returnValue = new ArrayList<TemperaturedataView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "TemperaturedataView1";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition= " (convert(datetime,tmpshijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and tmpno='" + shebeibianhao + "'";
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
			cs.setString(3, "tmpid");
			cs.setString(4, "tmpid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					TemperaturedataView tmpdata = new TemperaturedataView();
					//初压
/**					东交特有的温度过滤
					if(StringUtil.Null2Blank(shebeibianhao).length()>0){
						if(shebeibianhao.equalsIgnoreCase("nytpyl0101")){
							if(rs.getDouble("tmpdata")+15>100 && rs.getDouble("tmpdata")+15<210){
								try{
									if((rs.getString("tmpdata")!="" || rs.getString("tmpdata")!=null) && (rs.getString("tmpshijian")!="" || rs.getString("tmpshijian")!=null)){
										tmpdata.setBanhezhanminchen(rs.getString("banhezhanminchen"));
										tmpdata.setJianchen(rs.getString("jianchen"));
										tmpdata.setTmpid(rs.getInt("tmpid"));
										tmpdata.setTmpno(rs.getString("tmpno"));
										
										tmpdata.setTmpdata(String.format("%1$.1f",rs.getFloat("tmpdata")+15));
										tmpdata.setTmpshijian(rs.getString("tmpshijian"));
										_returnValue.add(tmpdata);
									}
								}catch(Exception ex){}
							}
						}
							
							//复压
							if(shebeibianhao.equalsIgnoreCase("nytpyl0102")){
								if(rs.getDouble("tmpdata")-10>100 && rs.getDouble("tmpdata")-10<210){
									try{
										if((rs.getString("tmpdata")!="" || rs.getString("tmpdata")!=null) && (rs.getString("tmpshijian")!="" || rs.getString("tmpshijian")!=null)){
											tmpdata.setBanhezhanminchen(rs.getString("banhezhanminchen"));
											tmpdata.setJianchen(rs.getString("jianchen"));
											tmpdata.setTmpid(rs.getInt("tmpid"));
											tmpdata.setTmpno(rs.getString("tmpno"));
											tmpdata.setTmpdata(String.format("%1$.1f",rs.getFloat("tmpdata")-10));
											tmpdata.setTmpshijian(rs.getString("tmpshijian"));
											_returnValue.add(tmpdata);
										}
									}catch(Exception ex){}
								}
							}
							//终压
							if(shebeibianhao.equalsIgnoreCase("nytpyl0103")){
								if(rs.getDouble("tmpdata")>60 && rs.getDouble("tmpdata")<200){
									try{
										if((rs.getString("tmpdata")!="" || rs.getString("tmpdata")!=null) && (rs.getString("tmpshijian")!="" || rs.getString("tmpshijian")!=null)){
											tmpdata.setBanhezhanminchen(rs.getString("banhezhanminchen"));
											tmpdata.setJianchen(rs.getString("jianchen"));
											tmpdata.setTmpid(rs.getInt("tmpid"));
											tmpdata.setTmpno(rs.getString("tmpno"));
											tmpdata.setTmpdata(String.format("%1$.1f",rs.getFloat("tmpdata")));
											tmpdata.setTmpshijian(rs.getString("tmpshijian"));
											_returnValue.add(tmpdata);
										}
									}catch(Exception ex){}
								}
							}
					}else{
						tmpdata.setBanhezhanminchen(rs.getString("banhezhanminchen"));
						tmpdata.setJianchen(rs.getString("jianchen"));
						tmpdata.setTmpid(rs.getInt("tmpid"));
						tmpdata.setTmpno(rs.getString("tmpno"));
						tmpdata.setTmpdata(String.format("%1$.1f",rs.getFloat("tmpdata")-10));
						tmpdata.setTmpshijian(rs.getString("tmpshijian"));
						_returnValue.add(tmpdata);
					}
**/					
					tmpdata.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					tmpdata.setJianchen(rs.getString("jianchen"));
					tmpdata.setTmpid(rs.getInt("tmpid"));
					tmpdata.setTmpno(rs.getString("tmpno"));
					try{
						tmpdata.setTmpdata(String.format("%1$.1f",rs.getFloat("tmpdata")));
					}catch(Exception ex){}
					tmpdata.setTmpshijian(rs.getString("tmpshijian"));
					_returnValue.add(tmpdata);
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
	public GenericPageMode environmentView(String shebeibianhao,
			String startTimeOne, String endTimeOne, Integer biaoduan,
			Integer xiangmubu, String fn, int bsid, int offset, int pagesize) {
		List<EnvironmentView> _returnValue = new ArrayList<EnvironmentView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "EnvironmentView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition= "(convert(datetime,fensushijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and fensuno='" + shebeibianhao + "'";
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
			cs.setString(3, "fensuid");
			cs.setString(4, "fensuid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					EnvironmentView environment = new EnvironmentView();
					try{
						environment.setFensu(String.format("%1$.1f",rs.getFloat("fensu")));
						environment.setWendu(String.format("%1$.1f",rs.getFloat("wendu")));
						environment.setShidu(String.format("%1$.1f",rs.getFloat("shidu")));
					}catch(Exception ex){}
					environment.setFensuno(rs.getString("fensuno"));
					environment.setFensushijian(rs.getString("fensushijian"));
					environment.setBiaoshi(rs.getString("biaoshi"));
					environment.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					_returnValue.add(environment);
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
	public GenericPageMode viewChuliaokoulist(String shebeibianhao,
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize) {
		List<ChuliaokouTemperaturedataView> _returnValue = new ArrayList<ChuliaokouTemperaturedataView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "ChuliaokouTemperaturedataView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTimeOne).length() > 0) {
			strstartTime = startTimeOne;
		}
		if (StringUtil.Null2Blank(endTimeOne).length() > 0) {
			strendTime = endTimeOne;
		}
		String queryCondition="(convert(datetime,tmpshijian,121) between '" + strstartTime
		+ "' and '" + strendTime + "')";
		if (!fn.equalsIgnoreCase("all")) {
			queryCondition += " and "+fn+"=" + bsid;
		}
		
		if (StringUtil.Null2Blank(shebeibianhao).length() > 0) {
			queryCondition += " and tmpno='" + shebeibianhao + "'";
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
			cs.setString(3, "tmpid");
			cs.setString(4, "tmpid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					ChuliaokouTemperaturedataView tmpdata = new ChuliaokouTemperaturedataView();
					tmpdata.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					tmpdata.setJianchen(rs.getString("jianchen"));
					tmpdata.setTmpid(rs.getInt("tmpid"));
					tmpdata.setTmpno(rs.getString("tmpno"));
					try{
						tmpdata.setTmpdata(String.format("%1$.1f",rs.getFloat("tmpdata")));
					}catch(Exception ex){}
					tmpdata.setTmpshijian(rs.getString("tmpshijian"));
					_returnValue.add(tmpdata);
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
	
	//沥青设计生产量
	public GenericPageMode lqshejisclview(String shebeibianhao,String peifang,String startTime,String endTime,Integer biaoduan,
				Integer xiangmubu,String fn,int bsid,Integer offset,Integer pagesize){
		List<LiqingclshejizhiView> _returnValue = new ArrayList<LiqingclshejizhiView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "LiqingclshejizhiView";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strstartTime = "2010-01-01 00:00:00";
		String strendTime = sdf.format(System.currentTimeMillis());
		if (StringUtil.Null2Blank(startTime).length() > 0) {
			strstartTime = startTime;
		}
		if (StringUtil.Null2Blank(endTime).length() > 0) {
			strendTime = endTime;
		}
		String queryCondition="(convert(datetime,baocunshijian,121) between '" + strstartTime
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
		if(StringUtil.Null2Blank(peifang).length()>0){
			queryCondition+=" and peifan='"+peifang+"'";
		}
		ResultSet rs = null;
		CallableStatement cs = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			cs = con.prepareCall(cssql);
			cs.setString(1, tablename);
			cs.setString(2, "");
			cs.setString(3, "shejiid");
			cs.setString(4, "shejiid DESC");
			cs.setInt(5, pagesize);
			cs.setInt(6, offset);
			cs.registerOutParameter(7, java.sql.Types.INTEGER);
			cs.registerOutParameter(8, java.sql.Types.INTEGER);
			cs.setString(9, queryCondition);
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					LiqingclshejizhiView tmpdata = new LiqingclshejizhiView();
					tmpdata.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					tmpdata.setJianchen(rs.getString("jianchen"));
					tmpdata.setBaocunshijian(rs.getString("baocunshijian"));
					tmpdata.setShebeibianhao(rs.getString("shebeibianhao"));
					tmpdata.setShejiid(rs.getInt("shejiid"));
					tmpdata.setChangliang(rs.getString("changliang"));
					tmpdata.setPeifang(rs.getString("peifan"));
					tmpdata.setShijichanliang(String.format("%1$.2f",rs.getFloat("shijichanliang")));
					tmpdata.setJidu(String.format("%1$.2f",rs.getFloat("jidu")));
					if(rs.getDouble("jidu")>100){
						tmpdata.setXianshizhi("100");
					}else{
						tmpdata.setXianshizhi(tmpdata.getJidu());
					}
					_returnValue.add(tmpdata);
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
