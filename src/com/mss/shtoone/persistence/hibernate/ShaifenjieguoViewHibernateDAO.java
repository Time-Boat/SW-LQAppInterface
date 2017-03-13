package com.mss.shtoone.persistence.hibernate;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;


import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.ShaifenjieguoView;
import com.mss.shtoone.persistence.ShaifenjieguoViewDAO;
import com.mss.shtoone.util.StringUtil;

@Repository
public class ShaifenjieguoViewHibernateDAO extends GenericHibernateDAO<ShaifenjieguoView, Integer> implements ShaifenjieguoViewDAO{
	
	private static Log logger = LogFactory.getLog(ShaifenjieguoViewHibernateDAO.class);
	
	public GenericPageMode shaifenjieguoviewlist(String shebeibianhao,
			String startTimeOne,String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, Integer bsid, Integer offset, Integer pagesize,String llbuwei){
		List<ShaifenjieguoView> _returnValue = new ArrayList<ShaifenjieguoView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "ShaifenjieguoView";
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
		if (StringUtil.Null2Blank(llbuwei).length() > 0) {
			queryCondition += " and llbuwei='" + llbuwei + "'";
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
			//System.out.println("查询语句："+queryCondition);
			
			boolean bHasResultSet = cs.execute();
			if (bHasResultSet) {
				rs = cs.getResultSet();
				while (rs.next()) {
					//System.out.println("passper7:"+rs.getFloat("passper7"));
					ShaifenjieguoView hv = new ShaifenjieguoView();
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
	
	public List<ShaifenjieguoView> findBySql(String sql){
			List<ShaifenjieguoView> list=new ArrayList<ShaifenjieguoView>();
			Statement st=null;
			Connection con=null;
			ResultSet rs=null;
			try {
				con = getTemplate().getSessionFactory().openSession().connection();
				st=con.createStatement();
				rs=st.executeQuery(sql);
				while(rs.next()){
					ShaifenjieguoView shaifenjieguoview=new ShaifenjieguoView();
					//IFDObj.setId(rs.getString("id"));
				try {
					shaifenjieguoview.setLlid(Integer.parseInt(rs.getString("llid")));
				} catch (Exception e) {

				}
				try {
					shaifenjieguoview.setJieguoid(Integer.parseInt(rs.getString("jieguoid")));
				} catch (Exception e) {

				}
					shaifenjieguoview.setLlpeibiName(rs.getString("llpeibiName"));
					shaifenjieguoview.setLlbuwei(rs.getString("llbuwei"));
					shaifenjieguoview.setLlzhuanghao(rs.getString("llzhuanghao"));
					shaifenjieguoview.setLlshigongjihua(rs.getString("llshigongjihua"));
					shaifenjieguoview.setLlshigongdateS(rs.getString("llshigongdateS"));
					shaifenjieguoview.setLlshigongdateE(rs.getString("llshigongdateE"));
					shaifenjieguoview.setLllurudate(rs.getString("lllurudate"));
					shaifenjieguoview.setLlmoren(rs.getString("llmoren"));
					shaifenjieguoview.setLlg1(rs.getString("llg1"));
					shaifenjieguoview.setLlg1upper(rs.getString("llg1upper"));
					shaifenjieguoview.setLlg1floor(rs.getString("llg1floor"));
					shaifenjieguoview.setLlg2(rs.getString("llg2"));
					shaifenjieguoview.setLlg2upper(rs.getString("llg2upper"));
					shaifenjieguoview.setLlg2floor(rs.getString("llg2floor"));
					shaifenjieguoview.setLlg3(rs.getString("llg3"));
					shaifenjieguoview.setLlg3upper(rs.getString("llg3upper"));
					shaifenjieguoview.setLlg3floor(rs.getString("llg3floor"));
					shaifenjieguoview.setLlg4(rs.getString("llg4"));
					shaifenjieguoview.setLlg4upper(rs.getString("llg4upper"));
					shaifenjieguoview.setLlg4floor(rs.getString("llg4floor"));
					shaifenjieguoview.setLlg5(rs.getString("llg5"));
					shaifenjieguoview.setLlg5upper(rs.getString("llg5upper"));
					shaifenjieguoview.setLlg5floor(rs.getString("llg5floor"));
					shaifenjieguoview.setLlf1(rs.getString("llf1"));
					shaifenjieguoview.setLlf1upper(rs.getString("llf1upper"));
					shaifenjieguoview.setLlf1floor(rs.getString("llf1floor"));
					shaifenjieguoview.setLlf2(rs.getString("llf2"));
					shaifenjieguoview.setLlf2upper(rs.getString("llf2upper"));
					shaifenjieguoview.setLlf2floor(rs.getString("llf2floor"));
					shaifenjieguoview.setLlshui(rs.getString("llshui"));
					shaifenjieguoview.setLlshebeibianhao(rs.getString("llshebeibianhao"));
					shaifenjieguoview.setLlbeizhu(rs.getString("llbeizhu"));
					try{
		             shaifenjieguoview.setId(Integer.parseInt(rs.getString("id")));
					}catch (Exception e) {
						
					}
					 shaifenjieguoview.setBanhezhanminchen(rs.getString("banhezhanminchen"));
					 shaifenjieguoview.setBeizhu(rs.getString("beizhu"));
					 shaifenjieguoview.setGongkongleixin(rs.getString("gongkongleixin"));
					 shaifenjieguoview.setGongkongruanjian(rs.getString("gongkongruanjian"));
					 shaifenjieguoview.setGprsbianhao(rs.getString("gprsbianhao"));
					 shaifenjieguoview.setJiekouleixin(rs.getString("jiekouleixin"));
					 shaifenjieguoview.setShebeichanjia(rs.getString("shebeichanjia"));	
					 shaifenjieguoview.setShujukuleixin(rs.getString("shujukuleixin"));
					 shaifenjieguoview.setShuliang(rs.getString("shuliang"));
					 shaifenjieguoview.setXiangmubuminchen(rs.getString("xiangmubuminchen"));
					 shaifenjieguoview.setShebeileixin(rs.getString("shebeileixin"));
					 shaifenjieguoview.setSimhao(rs.getString("simhao"));
					 shaifenjieguoview.setShoujihao(rs.getString("shoujihao"));
					 shaifenjieguoview.setBotelu(rs.getString("botelu"));
					 shaifenjieguoview.setJianchen(rs.getString("jianchen")); 
					 shaifenjieguoview.setTaocan(rs.getString("taocan"));
					 shaifenjieguoview.setSmsbaojin(rs.getString("smsbaojin"));	
				try {
					shaifenjieguoview.setBiaoduanid(Integer.parseInt(rs.getString("biaoduanid")));
				} catch (Exception e) {

				}
				try {
					shaifenjieguoview.setXiangmubuid(Integer.parseInt(rs.getString("xiangmubuid")));
				} catch (Exception e) {

				}
				try {
					 shaifenjieguoview.setZuoyeduiid(Integer.parseInt(rs.getString("zuoyeduiid"))); 
				} catch (Exception e) {

				}
				 
					 shaifenjieguoview.setSmstype(rs.getString("smstype"));
					 shaifenjieguoview.setSendtype(rs.getString("sendtype"));
					 shaifenjieguoview.setPanshu(rs.getString("panshu"));
					 shaifenjieguoview.setAmbegin(rs.getString("ambegin"));
					 shaifenjieguoview.setAmend(rs.getString("amend"));
					 shaifenjieguoview.setSmssettype(rs.getString("smssettype"));
					 shaifenjieguoview.setPmbegin(rs.getString("pmbegin"));
					 shaifenjieguoview.setPmend(rs.getString("pmend"));
					 shaifenjieguoview.setSimpwd(rs.getString("simpwd"));
						  

					
				try {
					shaifenjieguoview.setJieguoid(Integer.parseInt(rs.getString("jieguoid")));
				} catch (Exception e) {

				}
				try {
					shaifenjieguoview.setSwbianhao(Integer.parseInt(rs.getString("swbianhao")));
				} catch (Exception e) {

				}
				
				try {	
					shaifenjieguoview.setPassper1( String.format("%1.1f", rs.getFloat("passper1")));	
				}catch(Exception ex){}
				//System.out.println(shaifenjieguoview.getPassper1());
				try {	
					shaifenjieguoview.setPassper2(String.format("%1.1f", rs.getFloat("passper2")));
				}catch(Exception ex){}
				try {		
					shaifenjieguoview.setPassper3(String.format("%1.1f", rs.getFloat("passper3")));
				}catch(Exception ex){}
				try {	
					shaifenjieguoview.setPassper4(String.format("%1.1f", rs.getFloat("passper4")));
				}catch(Exception ex){}
				try {	
					shaifenjieguoview.setPassper5(String.format("%1.1f", rs.getFloat("passper5")));
				}catch(Exception ex){}
				try {	
					shaifenjieguoview.setPassper6(String.format("%1.1f", rs.getFloat("passper6")));
				}catch(Exception ex){}
				try {	
					shaifenjieguoview.setPassper7(String.format("%1.1f", rs.getFloat("passper7")));
				}catch(Exception ex){}
				try {	
					shaifenjieguoview.setPassper8(String.format("%1.1f", rs.getFloat("passper8")));
				}catch(Exception ex){}
				try {	
					shaifenjieguoview.setPassper9(String.format("%1.1f", rs.getFloat("passper9")));
				}catch(Exception ex){}
				try {	
					shaifenjieguoview.setPassper10(String.format("%1.1f", rs.getFloat("passper10")));
				}catch(Exception ex){}
				try {	
					shaifenjieguoview.setPassper11(String.format("%1.1f", rs.getFloat("passper11")));
				}catch(Exception ex){}
				try {	
					shaifenjieguoview.setPassper12(String.format("%1.1f", rs.getFloat("passper12")));
				}catch(Exception ex){}
				try {	
					shaifenjieguoview.setPassper13(String.format("%1.1f", rs.getFloat("passper13")));
				}catch(Exception ex){}
				try {	
					shaifenjieguoview.setPassper14(String.format("%1.1f", rs.getFloat("passper14")));
				}catch(Exception ex){}
				try {	
					shaifenjieguoview.setPassper15(String.format("%1.1f", rs.getFloat("passper15")));
				}catch(Exception ex){}
					
					try{
					shaifenjieguoview.setSjglsf1(Integer.parseInt(rs.getString("sjglsf1")));
					}catch (Exception e) {
						// TODO: handle exception
					}
					try{
					shaifenjieguoview.setSjglsf2(Integer.parseInt(rs.getString("sjglsf2")));
					}catch (Exception e) {
						// TODO: handle exception
					}
					try{
					shaifenjieguoview.setSjglsf3(Integer.parseInt(rs.getString("sjglsf3")));
					}catch (Exception e) {
						// TODO: handle exception
					}
					try{
					shaifenjieguoview.setSjglsf4(Integer.parseInt(rs.getString("sjglsf4")));
					}catch (Exception e) {
						// TODO: handle exception
					}
					try{
					shaifenjieguoview.setSjglsf5(Integer.parseInt(rs.getString("sjglsf5")));
					}catch (Exception e) {
						// TODO: handle exception
					}
					try{
					shaifenjieguoview.setSjflsf1(Integer.parseInt(rs.getString("sjflsf1")));
					}catch (Exception e) {
						// TODO: handle exception
					}
					try{
					shaifenjieguoview.setSjflsf2(Integer.parseInt(rs.getString("sjflsf2")));
					}catch (Exception e) {
						// TODO: handle exception
					}
					
					shaifenjieguoview.setBaocunshijian(rs.getString("baocunshijian"));
					
		               
					shaifenjieguoview.setMaxpassper1(rs.getString("maxpassper1"));
					shaifenjieguoview.setMaxpassper2(rs.getString("maxpassper2"));
					shaifenjieguoview.setMaxpassper3(rs.getString("maxpassper3"));
					shaifenjieguoview.setMaxpassper4(rs.getString("maxpassper4"));
					shaifenjieguoview.setMaxpassper5(rs.getString("maxpassper5"));
					shaifenjieguoview.setMaxpassper6(rs.getString("maxpassper6"));
					shaifenjieguoview.setMaxpassper7(rs.getString("maxpassper7"));
					shaifenjieguoview.setMaxpassper8(rs.getString("maxpassper8"));
					shaifenjieguoview.setMaxpassper9(rs.getString("maxpassper9"));
					shaifenjieguoview.setMaxpassper10(rs.getString("maxpassper10"));
					shaifenjieguoview.setMaxpassper11(rs.getString("maxpassper11"));
					shaifenjieguoview.setMaxpassper12(rs.getString("maxpassper12"));
					shaifenjieguoview.setMaxpassper13(rs.getString("maxpassper13"));
					shaifenjieguoview.setMaxpassper14(rs.getString("maxpassper14"));
					shaifenjieguoview.setMaxpassper15(rs.getString("maxpassper15"));
					
					shaifenjieguoview.setMinpassper1(rs.getString("minpassper1"));
					shaifenjieguoview.setMinpassper2(rs.getString("minpassper2"));
					shaifenjieguoview.setMinpassper3(rs.getString("minpassper3"));
					shaifenjieguoview.setMinpassper4(rs.getString("minpassper4"));
					shaifenjieguoview.setMinpassper5(rs.getString("minpassper5"));
					shaifenjieguoview.setMinpassper6(rs.getString("minpassper6"));
					shaifenjieguoview.setMinpassper7(rs.getString("minpassper7"));
					shaifenjieguoview.setMinpassper8(rs.getString("minpassper8"));
					shaifenjieguoview.setMinpassper9(rs.getString("minpassper9"));
					shaifenjieguoview.setMinpassper10(rs.getString("minpassper10"));
					shaifenjieguoview.setMinpassper11(rs.getString("minpassper11"));
					shaifenjieguoview.setMinpassper12(rs.getString("minpassper12"));
					shaifenjieguoview.setMinpassper13(rs.getString("minpassper13"));
					shaifenjieguoview.setMinpassper14(rs.getString("minpassper14"));
					shaifenjieguoview.setMinpassper15(rs.getString("minpassper15"));
				
		                
					shaifenjieguoview.setStandPassper1(rs.getString("standPassper1"));
					shaifenjieguoview.setStandPassper2(rs.getString("standPassper2"));
					shaifenjieguoview.setStandPassper3(rs.getString("standPassper3"));
					shaifenjieguoview.setStandPassper4(rs.getString("standPassper4"));
					shaifenjieguoview.setStandPassper5(rs.getString("standPassper5"));
					shaifenjieguoview.setStandPassper6(rs.getString("standPassper6"));
					shaifenjieguoview.setStandPassper7(rs.getString("standPassper7"));
					shaifenjieguoview.setStandPassper8(rs.getString("standPassper8"));
					shaifenjieguoview.setStandPassper9(rs.getString("standPassper9"));
					shaifenjieguoview.setStandPassper10(rs.getString("standPassper10"));
					shaifenjieguoview.setStandPassper11(rs.getString("standPassper11"));
					shaifenjieguoview.setStandPassper12(rs.getString("standPassper12"));
					shaifenjieguoview.setStandPassper13(rs.getString("standPassper13"));
					shaifenjieguoview.setStandPassper14(rs.getString("standPassper14"));
					shaifenjieguoview.setStandPassper15(rs.getString("standPassper15"));
				
					
					list.add(shaifenjieguoview);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					rs.close();
					st.close();
					con.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			return list;
		}
	
}
