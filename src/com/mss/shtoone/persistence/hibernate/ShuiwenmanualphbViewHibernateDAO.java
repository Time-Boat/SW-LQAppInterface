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
import org.springframework.stereotype.Repository;

import com.mss.shtoone.domain.GenericPageMode;
import com.mss.shtoone.domain.LiqingphbView;
import com.mss.shtoone.domain.ShuiwenmanualphbView;
import com.mss.shtoone.domain.ShuiwenphbView;
import com.mss.shtoone.domain.ShuiwenziduancfgView;
import com.mss.shtoone.persistence.ShuiwenmanualphbViewDAO;
import com.mss.shtoone.util.DbJdbcUtil;
import com.mss.shtoone.util.StringUtil;


@Repository
public class ShuiwenmanualphbViewHibernateDAO extends GenericHibernateDAO<ShuiwenmanualphbView, Integer> implements
ShuiwenmanualphbViewDAO {
	private static Log logger = LogFactory.getLog(ShuiwenmanualphbViewHibernateDAO.class);
	
	@Override
	public GenericPageMode swwuchamanualphbviewlist(String shebeibianhao,
			String startTimeOne, String endTimeOne, Integer biaoduan,
			Integer xiangmubu, String fn, int bsid, int offset, int pagesize) {
		List<ShuiwenmanualphbView> _returnValue = new ArrayList<ShuiwenmanualphbView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "ShuiwenmanualphbView";
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
					ShuiwenmanualphbView hv=new ShuiwenmanualphbView();
					//揭博的业主引入一个目标理论配比概率，那这个试图要重新写
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
						
						hv.setLlid(rs.getInt("llid"));
						hv.setLlbuwei(rs.getString("llbuwei"));
						hv.setLlpeibiName(rs.getString("llpeibiName"));
						hv.setLlzhuanghao(rs.getString("llzhuanghao"));
						hv.setLlshigongjihua(rs.getString("llshigongjihua"));
						hv.setLlshigongdateS(rs.getString("llshigongdateS"));
						hv.setLlshigongdateE(rs.getString("llshigongdateE"));
						hv.setLllurudate(rs.getString("lllurudate"));
						hv.setLlmoren(rs.getString("llmoren"));
						//生产理论配比
						try{
							hv.setPerllgl1(String.format("%1$.2f",rs.getFloat("perllgl1")));
						}catch(Exception ex){}
						try{
							hv.setPerllgl2(String.format("%1$.2f",rs.getFloat("perllgl2")));
						}catch(Exception ex){}
						try{
							hv.setPerllgl3(String.format("%1$.2f",rs.getFloat("perllgl3")));
						}catch(Exception ex){}
						try{
							hv.setPerllgl4(String.format("%1$.2f",rs.getFloat("perllgl4")));
						}catch(Exception ex){}
						try{
							hv.setPerllgl5(String.format("%1$.2f",rs.getFloat("perllgl5")));
						}catch(Exception ex){}
						try{
							hv.setPerllfl1(String.format("%1$.2f",rs.getFloat("perllfl1")));
						}catch(Exception ex){}
						try{
							hv.setPerllfl2(String.format("%1$.2f",rs.getFloat("perllfl2")));
						}catch(Exception ex){}
						
						
						try {
							hv.setSjgl1(String.format("%1$.1f", rs.getFloat("sjgl1")));
						} catch (Exception e) {}
						try {
							hv.setSjgl2(String.format("%1$.1f", rs.getFloat("sjgl2")));
						} catch (Exception e) {}
						try {
							hv.setSjgl3(String.format("%1$.1f", rs.getFloat("sjgl3")));
						} catch (Exception e) {}
						try {
							hv.setSjgl4(String.format("%1$.1f", rs
									.getFloat("sjgl4")));
						} catch (Exception e) {}
						try {
							hv.setSjgl5(String.format("%1$.1f", rs.getFloat("sjgl5")));
						} catch (Exception e) {}
						
						try {
							hv.setSjfl1(String.format("%1$.2f", rs.getFloat("sjfl1")));
						} catch (Exception e) {}
						try {
							hv.setSjfl2(String.format("%1$.2f", rs.getFloat("sjfl2")));
						} catch (Exception e) {}
						
						
						try {
							hv.setLlfl1(String.format("%1$.1f", rs.getFloat("llgl1")));
						} catch (Exception e) {}
						try {
							hv.setLlgl2(String.format("%1$.1f", rs.getFloat("llgl2")));
						} catch (Exception e) {}
						try {
							hv.setLlgl3(String.format("%1$.1f", rs.getFloat("llgl3")));
						} catch (Exception e) {}
						try {
							hv.setLlgl4(String.format("%1$.1f", rs.getFloat("llgl4")));
						} catch (Exception e) {}
						try {
							hv.setLlgl5(String.format("%1$.1f", rs.getFloat("llgl5")));
						} catch (Exception e) {}
						
						try {
							hv.setLlfl1(String.format("%1$.2f", rs.getFloat("llfl1")));
						} catch (Exception e) {}
						try {
							hv.setLlfl2(String.format("%1$.2f", rs.getFloat("llfl2")));
						} catch (Exception e) {}
						
						try {
							hv.setPersjgl1(String.format("%1$.1f", rs.getFloat("persjgl1")));
						} catch (Exception e) {}
						try {
							hv.setPersjgl2(String.format("%1$.1f", rs.getFloat("persjgl2")));
						} catch (Exception e) {}
						try {
							hv.setPersjgl3(String.format("%1$.1f", rs.getFloat("persjgl3")));
						} catch (Exception e) {}
						try {
							hv.setPersjgl4(String.format("%1$.1f", rs.getFloat("persjgl4")));
						} catch (Exception e) {}
						try {
							hv.setPersjgl5(String.format("%1$.1f", rs.getFloat("persjgl5")));
						} catch (Exception e) {}
						
						try {
							hv.setPersjfl1(String.format("%1$.2f", rs.getFloat("persjfl1")));
						} catch (Exception e) {}
						try {
							hv.setPersjfl2(String.format("%1$.2f", rs.getFloat("persjfl2")));
						} catch (Exception e) {}
						
						//揭博业主要求为实际配合比与目标配合比进行比对，出误差
						try {
							hv.setWgl1(String.format("%1$.1f", rs.getFloat("manualwgl1")));
						} catch (Exception e) {}
						try {
							hv.setWgl2(String.format("%1$.1f", rs.getFloat("manualwgl2")));
						} catch (Exception e) {}
						try {
							hv.setWgl3(String.format("%1$.1f", rs.getFloat("manualwgl3")));
						} catch (Exception e) {}
						try {
							hv.setWgl4(String.format("%1$.1f", rs.getFloat("manualwgl4")));
						} catch (Exception e) {}
						try {
							hv.setWgl5(String.format("%1$.1f", rs.getFloat("manualwgl5")));
						} catch (Exception e) {}
						
						try {
							hv.setWfl1(String.format("%1$.2f", rs.getFloat("manualwfl1")));
						} catch (Exception e) {}
						try {
							hv.setWfl2(String.format("%1$.2f", rs.getFloat("manualwfl2")));
						} catch (Exception e) {}
						//目标理论配比
						hv.setLlg1(rs.getString("llg1"));
						hv.setLlg2(rs.getString("llg2"));
						hv.setLlg3(rs.getString("llg3"));
						hv.setLlg4(rs.getString("llg4"));
						hv.setLlg5(rs.getString("llg5"));
						hv.setLlf1(rs.getString("llf1"));
						hv.setLlf2(rs.getString("llf2"));
//						try{
//							hv.setLlg1(String.format("%1$.2f", rs.getFloat("llg1")));
//						}catch(Exception ex){}
//						try{
//							hv.setLlg2(String.format("%1$.2f", rs.getFloat("llg2")));
//						}catch(Exception ex){}
//						try{
//							hv.setLlg3(String.format("%1$.2f", rs.getFloat("llg3")));
//						}catch(Exception ex){}
//						try{
//							hv.setLlg4(String.format("%1$.2f", rs.getFloat("llg4")));
//						}catch(Exception ex){}
//						try{
//							hv.setLlg5(String.format("%1$.2f", rs.getFloat("llg5")));
//						}catch(Exception ex){}
//						try{
//							hv.setLlf1(String.format("%1$.2f", rs.getFloat("llf1")));
//						}catch(Exception ex){}
//						try{
//							hv.setLlf2(String.format("%1$.2f", rs.getFloat("llf2")));
//						}catch(Exception ex){}
					
					_returnValue.add(hv);
				}
				pagemode.setDatas(_returnValue);
				pagemode.setRecordcount(cs.getInt(7));
				pagemode.setPagetotal(cs.getInt(8));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			DbJdbcUtil.closeAll(rs, cs, con);
		}
		return pagemode;
	}
	
	
	@Override
	public GenericPageMode swchaobiaomanualviewlist(String shebeibianhao, 
			String startTimeOne, String endTimeOne, 
			Integer biaoduan, Integer xiangmubu,String fn, int bsid, 
			int offset, int pagesize,Integer chaobiaolx,ShuiwenziduancfgView swisshow,Integer cllx,String bianhao) {
		List<ShuiwenmanualphbView> _returnValue = new ArrayList<ShuiwenmanualphbView>();
		GenericPageMode pagemode = new GenericPageMode();
		String cssql = "{ call Sp_PapeView(?,?,?,?,?,?,?,?,?) }";
		String tablename = "ShuiwenmanaualchaobiaoView";
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
		//超标处理类型
		if(cllx==1){
        	queryCondition +=" and  filepath is NULL  and ISNULL(chulijieguo,'')='' ";
        }else if(cllx>=2){
        	queryCondition +=" and (filepath is NOT NULL or  ISNULL(chulijieguo,'')<>'') ";
        	if(cllx == 3){
    			queryCondition += " and  yezhuyijian is null ";
    		}else if(cllx==4){
    			queryCondition += " and  yezhuyijian is not null ";
    		}
        }
		
		//检索超标内容
		if(StringUtil.Null2Blank(bianhao).length()>0){
			queryCondition +=" and bianhao="+Integer.parseInt(bianhao);
		}
		//超标级别
		if(swisshow!=null){
			StringBuilder tempCondition = new StringBuilder("1=2");
			switch (chaobiaolx) {
				case 0:
					if(StringUtil.Null2Blank(swisshow.getSjgl1()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg1 is not null");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl2()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg2 is not null");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl3()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg3 is not null");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl4()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg4 is not null");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl5()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg5 is not null");
					}
					if(StringUtil.Null2Blank(swisshow.getSjfl1()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjf1 is not null");
					}
					if(StringUtil.Null2Blank(swisshow.getSjfl2()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjf2 is not null");
					}
					break;
				case 1:
					if(StringUtil.Null2Blank(swisshow.getSjgl1()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg1 = '1' or sjg1 = '4' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl2()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg2 = '1' or sjg2 = '4' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl3()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg3 = '1' or sjg3 = '4' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl4()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg4 = '1' or sjg4 = '4' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl5()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg5 = '1' or sjg5 = '4' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjfl1()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjf1 = '1' or sjf1 = '4' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjfl2()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjf2 = '1' or sjf2 = '4' ");
					}
					break;
				case 2:
					if(StringUtil.Null2Blank(swisshow.getSjgl1()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg1 = '2' or sjg1 = '5' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl2()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg2 = '2' or sjg2 = '5' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl3()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg3 = '2' or sjg3 = '5' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl4()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg4 = '2' or sjg4 = '5' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl5()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg5 = '2' or sjg5 = '5' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjfl1()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjf1 = '2' or sjf1 = '5' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjfl2()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjf2 = '2' or sjf2 = '5' ");
					}
					break;
				case 3:
					if(StringUtil.Null2Blank(swisshow.getSjgl1()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg1 = '3' or sjg1 = '6' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl2()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg2 = '3' or sjg2 = '6' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl3()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg3 = '3' or sjg3 = '6' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl4()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg4 = '3' or sjg4 = '6' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjgl5()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjg5 = '3' or sjg5 = '6' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjfl1()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjf1 = '3' or sjf1 = '6' ");
					}
					if(StringUtil.Null2Blank(swisshow.getSjfl2()).equalsIgnoreCase("1")){
						tempCondition.append(" or sjf2 = '3' or sjf2 = '5' ");
					}
					break;
				default:
					break;
			}
			if (tempCondition.toString().length()>6) {
				queryCondition+=" and ("+tempCondition.toString()+") ";
			}
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
					ShuiwenmanualphbView hv = new ShuiwenmanualphbView();
						hv.setBanhezhanminchen(rs.getString("banhezhanminchen"));
						hv.setJianchen(rs.getString("jianchen"));
						hv.setBianhao(rs.getInt("bianhao"));
						hv.setBaocunshijian(rs.getString("baocunshijian"));
						hv.setShijian(rs.getString("shijian"));
						hv.setShijianS(rs.getString("shijianS"));
						hv.setShijianE(rs.getString("shijianE"));
						hv.setShebeibianhao(rs.getString("shebeibianhao"));
						hv.setCaijishijian(rs.getString("caijishijian"));
						//总量
						try{
							hv.setGlchangliang(String.format("%1$.2f",rs.getFloat("glchangliang")));
						}catch(Exception ex){}
						
						hv.setBeiy2(rs.getString("beiy2"));   
						hv.setBeiy3(rs.getString("beiy3"));
						
						hv.setLlid(rs.getInt("llid"));
						hv.setLlbuwei(rs.getString("llbuwei"));
						hv.setLlpeibiName(rs.getString("llpeibiName"));
						hv.setLlzhuanghao(rs.getString("llzhuanghao"));
						hv.setLlshigongjihua(rs.getString("llshigongjihua"));
						hv.setLlshigongdateS(rs.getString("llshigongdateS"));
						hv.setLlshigongdateE(rs.getString("llshigongdateE"));
						hv.setLllurudate(rs.getString("lllurudate"));
						hv.setLlmoren(rs.getString("llmoren"));
						try{
							hv.setPerllgl1(String.format("%1$.2f",rs.getFloat("perllgl1")));
						}catch(Exception ex){}
						hv.setLlg1upper(rs.getString("llg1upper"));
						hv.setLlg1floor(rs.getString("llg1floor"));
						try{
							hv.setPerllgl2(String.format("%1$.2f",rs.getFloat("perllgl2")));
						}catch(Exception ex){}
						hv.setLlg2upper(rs.getString("llg2upper"));
						hv.setLlg2floor(rs.getString("llg2floor"));
						try{
							hv.setPerllgl3(String.format("%1$.2f",rs.getFloat("perllgl3")));
						}catch(Exception ex){}
						hv.setLlg3upper(rs.getString("llg3upper"));
						hv.setLlg3floor(rs.getString("llg3floor"));
						try{
							hv.setPerllgl4(String.format("%1$.2f",rs.getFloat("perllgl4")));
						}catch(Exception ex){}
						hv.setLlg4upper(rs.getString("llg4upper"));
						hv.setLlg4floor(rs.getString("llg4floor"));
						try{
							hv.setPerllgl5(String.format("%1$.2f",rs.getFloat("perllgl5")));
						}catch(Exception ex){}
						hv.setLlg5upper(rs.getString("llg5upper"));
						hv.setLlg5floor(rs.getString("llg5floor"));
						try{
							hv.setPerllfl1(String.format("%1$.2f",rs.getFloat("perllfl1")));
						}catch(Exception ex){}
						hv.setLlf1upper(rs.getString("llf1upper"));
						hv.setLlf1floor(rs.getString("llf1floor"));
						try{
							hv.setPerllfl2(String.format("%1$.2f",rs.getFloat("perllfl2")));
						}catch(Exception ex){}
						hv.setLlf2upper(rs.getString("llf2upper"));
						hv.setLlf2floor(rs.getString("llf2floor"));
						hv.setLlshebeibianhao(rs.getString("llshebeibianhao"));
						hv.setLlbeizhu(rs.getString("llbeizhu"));
						
						try {
							hv.setSjgl1(String.format("%1$.1f", rs.getFloat("sjgl1")));
						} catch (Exception e) {}
						try {
							hv.setSjgl2(String.format("%1$.1f", rs.getFloat("sjgl2")));
						} catch (Exception e) {}
						try {
							hv.setSjgl3(String.format("%1$.1f", rs.getFloat("sjgl3")));
						} catch (Exception e) {}
						try {
							hv.setSjgl4(String.format("%1$.1f", rs.getFloat("sjgl4")));
						} catch (Exception e) {}
						try {
							hv.setSjgl5(String.format("%1$.1f", rs.getFloat("sjgl5")));
						} catch (Exception e) {}
						try {
							hv.setBeiy1(String.format("%1$.1f", rs.getFloat("beiy1")));   //实际骨料6
						} catch (Exception e) {}
						
						try {
							hv.setSjfl1(String.format("%1$.2f", rs.getFloat("sjfl1")));
						} catch (Exception e) {}
						try {
							hv.setSjfl2(String.format("%1$.2f", rs.getFloat("sjfl2")));
						} catch (Exception e) {}
						
						try {
							hv.setLlgl1(String.format("%1$.1f", rs.getFloat("llgl1")));
						} catch (Exception e) {}
						try {
							hv.setLlgl2(String.format("%1$.1f", rs.getFloat("llgl2")));
						} catch (Exception e) {}
						try {
							hv.setLlgl3(String.format("%1$.1f", rs.getFloat("llgl3")));
						} catch (Exception e) {						
						}
						try {
							hv.setLlgl4(String.format("%1$.1f", rs.getFloat("llgl4")));
						} catch (Exception e) {}
						try {
							hv.setLlgl5(String.format("%1$.1f", rs.getFloat("llgl5")));
						} catch (Exception e) {}
						
						try {
							hv.setLlfl1(String.format("%1$.2f", rs.getFloat("llfl1")));
						} catch (Exception e) {}
						try {
							hv.setLlfl2(String.format("%1$.2f", rs.getFloat("llfl2")));
						} catch (Exception e) {}
						
						try {
							hv.setPersjgl1(String.format("%1$.1f", rs.getFloat("persjgl1")));
						} catch (Exception e) {}
						try {
							hv.setPersjgl2(String.format("%1$.1f", rs.getFloat("persjgl2")));
						} catch (Exception e) {}
						try {
							hv.setPersjgl3(String.format("%1$.1f", rs.getFloat("persjgl3")));
						} catch (Exception e) {}
						try {
							hv.setPersjgl4(String.format("%1$.1f", rs.getFloat("persjgl4")));
						} catch (Exception e) {}
						try {
							hv.setPersjgl5(String.format("%1$.1f", rs.getFloat("persjgl5")));
						} catch (Exception e) {}
						
						try {
							hv.setPersjfl1(String.format("%1$.2f", rs.getFloat("persjfl1")));
						} catch (Exception e) {}
						try {
							hv.setPersjfl2(String.format("%1$.2f", rs.getFloat("persjfl2")));
						} catch (Exception e) {}				
						
						try {
							hv.setWgl1(String.format("%1$.1f", rs.getFloat("manualwgl1")));
						} catch (Exception e) {}
						try {
							hv.setWgl2(String.format("%1$.1f", rs.getFloat("manualwgl2")));
						} catch (Exception e) {}
						try {
							hv.setWgl3(String.format("%1$.1f", rs.getFloat("manualwgl3")));
						} catch (Exception e) {}
						try {
							hv.setWgl4(String.format("%1$.1f", rs.getFloat("manualwgl4")));
						} catch (Exception e) {}
						try {
							hv.setWgl5(String.format("%1$.1f", rs.getFloat("manualwgl5")));
						} catch (Exception e) {}
						
						try {
							hv.setWfl1(String.format("%1$.2f", rs.getFloat("manualwfl1")));
						} catch (Exception e) {}
						try {
							hv.setWfl2(String.format("%1$.2f", rs.getFloat("manualwfl2")));
						} catch (Exception e) {}
					
						//目标理论配比
						hv.setLlg1(rs.getString("llg1"));
						hv.setLlg2(rs.getString("llg2"));
						hv.setLlg3(rs.getString("llg3"));
						hv.setLlg4(rs.getString("llg4"));
						hv.setLlg5(rs.getString("llg5"));
						hv.setLlf1(rs.getString("llf1"));
						hv.setLlf2(rs.getString("llf2"));
//						try{
//							hv.setLlg1(String.format("%1$.2f", rs.getFloat("llg1")));
//						}catch(Exception ex){}
//						try{
//							hv.setLlg2(String.format("%1$.2f", rs.getFloat("llg2")));
//						}catch(Exception ex){}
//						try{
//							hv.setLlg3(String.format("%1$.2f", rs.getFloat("llg3")));
//						}catch(Exception ex){}
//						try{
//							hv.setLlg4(String.format("%1$.2f", rs.getFloat("llg4")));
//						}catch(Exception ex){}
//						try{
//							hv.setLlg5(String.format("%1$.2f", rs.getFloat("llg5")));
//						}catch(Exception ex){}
//						try{
//							hv.setLlf1(String.format("%1$.2f", rs.getFloat("llf1")));
//						}catch(Exception ex){}
//						try{
//							hv.setLlf2(String.format("%1$.2f", rs.getFloat("llf2")));
//						}catch(Exception ex){}
					hv.setChulijieguo(rs.getString("chulijieguo"));
					hv.setFilepath(rs.getString("filepath"));
					hv.setLeixing(rs.getString("leixing"));
					hv.setYezhuyijian(rs.getString("yezhuyijian"));
					//超标结果标红
					hv.setSjg1(rs.getString("sjg1"));
					if(StringUtil.Null2Blank(hv.getSjg1()).length()>0){
						if(StringUtil.Null2Blank(hv.getSjg1()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getSjg1()).equalsIgnoreCase("4")){
							hv.setSjgl1_primary("1");
						}else if(StringUtil.Null2Blank(hv.getSjg1()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getSjg1()).equalsIgnoreCase("5")){
							hv.setSjgl1_middle("2");
						}else if(StringUtil.Null2Blank(hv.getSjg1()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getSjg1()).equalsIgnoreCase("6")){
							hv.setSjgl1_high("3");
						}
					}
					
					hv.setSjg2(rs.getString("sjg2"));
					if(StringUtil.Null2Blank(hv.getSjg2()).length()>0){
						if(StringUtil.Null2Blank(hv.getSjg2()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getSjg2()).equalsIgnoreCase("4")){
							hv.setSjgl2_primary("1");
						}else if(StringUtil.Null2Blank(hv.getSjg2()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getSjg2()).equalsIgnoreCase("5")){
							hv.setSjgl2_middle("2");
						}else if(StringUtil.Null2Blank(hv.getSjg2()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getSjg2()).equalsIgnoreCase("6")){
							hv.setSjgl2_high("3");
						}
					}
					
					hv.setSjg3(rs.getString("sjg3"));
					if(StringUtil.Null2Blank(hv.getSjg3()).length()>0){
						if(StringUtil.Null2Blank(hv.getSjg3()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getSjg3()).equalsIgnoreCase("4")){
							hv.setSjgl3_primary("1");
						}else if(StringUtil.Null2Blank(hv.getSjg3()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getSjg3()).equalsIgnoreCase("5")){
							hv.setSjgl3_middle("2");
						}else if(StringUtil.Null2Blank(hv.getSjg3()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getSjg3()).equalsIgnoreCase("6")){
							hv.setSjgl3_high("3");
						}
					}
					
					hv.setSjg4(rs.getString("sjg4"));
					if(StringUtil.Null2Blank(hv.getSjg4()).length()>0){
						if(StringUtil.Null2Blank(hv.getSjg4()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getSjg4()).equalsIgnoreCase("4")){
							hv.setSjgl4_primary("1");
						}else if(StringUtil.Null2Blank(hv.getSjg4()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getSjg4()).equalsIgnoreCase("5")){
							hv.setSjgl4_middle("2");
						}else if(StringUtil.Null2Blank(hv.getSjg4()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getSjg4()).equalsIgnoreCase("6")){
							hv.setSjgl4_high("3");
						}
					}
					
					hv.setSjg5(rs.getString("sjg5"));
					if(StringUtil.Null2Blank(hv.getSjg5()).length()>0){
						if(StringUtil.Null2Blank(hv.getSjg5()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getSjg5()).equalsIgnoreCase("4")){
							hv.setSjgl5_primary("1");
						}else if(StringUtil.Null2Blank(hv.getSjg5()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getSjg5()).equalsIgnoreCase("5")){
							hv.setSjgl5_middle("2");
						}else if(StringUtil.Null2Blank(hv.getSjg5()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getSjg5()).equalsIgnoreCase("6")){
							hv.setSjgl5_high("3");
						}
					}
					
					hv.setSjf1(rs.getString("sjf1"));
					if(StringUtil.Null2Blank(hv.getSjf1()).length()>0){
						if(StringUtil.Null2Blank(hv.getSjf1()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getSjf1()).equalsIgnoreCase("4")){
							hv.setSjfl1_primary("1");
						}else if(StringUtil.Null2Blank(hv.getSjf1()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getSjf1()).equalsIgnoreCase("5")){
							hv.setSjfl1_middle("2");
						}else if(StringUtil.Null2Blank(hv.getSjf1()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getSjf1()).equalsIgnoreCase("6")){
							hv.setSjfl1_high("3");
						}
					}
					
					hv.setSjf2(rs.getString("sjf2"));
					if(StringUtil.Null2Blank(hv.getSjf2()).length()>0){
						if(StringUtil.Null2Blank(hv.getSjf2()).equalsIgnoreCase("1") || 
						   StringUtil.Null2Blank(hv.getSjf2()).equalsIgnoreCase("4")){
							hv.setSjfl2_primary("1");
						}else if(StringUtil.Null2Blank(hv.getSjf2()).equalsIgnoreCase("2") ||
								 StringUtil.Null2Blank(hv.getSjf2()).equalsIgnoreCase("5")){
							hv.setSjfl2_middle("2");
						}else if(StringUtil.Null2Blank(hv.getSjf1()).equalsIgnoreCase("3") ||
								 StringUtil.Null2Blank(hv.getSjf1()).equalsIgnoreCase("6")){
							hv.setSjfl2_high("3");
						}
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
			DbJdbcUtil.closeAll(rs, cs, con);
		}
		return pagemode;
	}
	
	public ShuiwenphbView swmateriallist(String startTime,String endTime,String shebeibianhao, Integer biaoduan, 
			Integer xiangmubu, String fn, int bsid){
		ShuiwenphbView swphb=null;
		StringBuffer sql = new StringBuffer();
		appendmanualSql(sql);
		sql.append(" FROM ShuiwenmanualphbView WHERE 1=1 ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());		
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and shebeibianhao in ("+shebeibianhao+")");
		}		
		if (null != biaoduan) {			
			sql.append(" and biaoduanid ="+biaoduan);
		}		
		if (null != xiangmubu) {			
			sql.append(" and xiangmubuid ="+xiangmubu);
		}
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()>0){
			sql.append(" and (shijianS between '"+startTime+"' and '"+endTime+"')");
		}else if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()==0){
			sql.append(" and (shijianS between '"+startTime+"' and '"+newDate+"')");
		}else if(StringUtil.Null2Blank(startTime).length()==0 && StringUtil.Null2Blank(endTime).length()>0){
			sql.append(" and (shijianS between '1900-01-01' and '"+endTime+"')");
		}
			
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();	
			rs=st.executeQuery(sql.toString());
			while(rs.next()){
				swphb=new ShuiwenphbView();
				try{
					swphb.setSjgl1(String.format("%1$.2f",rs.getFloat("sjgl1")/1000));
				}catch(Exception ex){}
				try{
					swphb.setSjgl2(String.format("%1$.2f",rs.getFloat("sjgl2")/1000));
				}catch(Exception ex){}
				try{
					swphb.setSjgl3(String.format("%1$.2f",rs.getFloat("sjgl3")/1000));
				}catch(Exception ex){}
				try{
					swphb.setSjgl4(String.format("%1$.2f",rs.getFloat("sjgl4")/1000));
				}catch(Exception ex){}
				try{
					swphb.setSjgl5(String.format("%1$.2f",rs.getFloat("sjgl5")/1000));
				}catch(Exception ex){}
				try{
					swphb.setSjfl1(String.format("%1$.2f",rs.getFloat("sjfl1")/1000));
				}catch(Exception ex){}
				try{
					swphb.setSjfl2(String.format("%1$.2f",rs.getFloat("sjfl2")/1000));
				}catch(Exception ex){}
				try{
					swphb.setGlchangliang(String.format("%1$.2f",rs.getFloat("changliang")/1000));
				}catch(Exception ex){}
			}
		}catch(Exception ex){
			logger.debug(ex.getMessage());
		}finally{
			try {
				rs.close();
			} catch (Exception e) {}
			try {
				st.close();
			} catch (Exception e1) {}
			try {
				con.close();
			} catch (Exception e) {}
		}
		return swphb;
	}
	
	//app接口新增方法
	public ShuiwenphbView appSwmateriallist(String startTime,String endTime,String shebeibianhao, Integer biaoduan, 
			Integer xiangmubu, String fn, int bsid){
		ShuiwenphbView swphb=null;
		StringBuffer sql = new StringBuffer();
		appendmanualSql(sql);
		sql.append(" FROM ShuiwenmanualphbView WHERE 1=1 ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String newDate = sdf.format(new Date());		
		if (!fn.equalsIgnoreCase("all")) {
			sql.append(" and "+fn+"=" + bsid);
		}
		if (StringUtil.Null2Blank(shebeibianhao).length()>0) {			
			sql.append(" and shebeibianhao in ("+shebeibianhao+")");
		}		
		if (null != biaoduan) {			
			sql.append(" and biaoduanid ="+biaoduan);
		}		
		if (null != xiangmubu) {			
			sql.append(" and xiangmubuid ="+xiangmubu);
		}
		if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()>0){
			sql.append(" and (shijianS between '"+startTime+"' and '"+endTime+"')");
		}else if(StringUtil.Null2Blank(startTime).length()>0 && StringUtil.Null2Blank(endTime).length()==0){
			sql.append(" and (shijianS between '"+startTime+"' and '"+newDate+"')");
		}else if(StringUtil.Null2Blank(startTime).length()==0 && StringUtil.Null2Blank(endTime).length()>0){
			sql.append(" and (shijianS between '1900-01-01' and '"+endTime+"')");
		}
			
		ResultSet rs = null;
		Statement st = null;
		Connection con = null;
		try {
			con = getTemplate().getSessionFactory().openSession().connection();
			st = con.createStatement();	
			rs = st.executeQuery(sql.toString());
			while(rs.next()){
				swphb = new ShuiwenphbView();
				//实际值
				try{
					swphb.setSjgl1(String.format("%1$.2f",rs.getFloat("sjgl1")/1000));
				}catch(Exception ex){}
				try{
					swphb.setSjgl2(String.format("%1$.2f",rs.getFloat("sjgl2")/1000));
				}catch(Exception ex){}
				try{
					swphb.setSjgl3(String.format("%1$.2f",rs.getFloat("sjgl3")/1000));
				}catch(Exception ex){}
				try{
					swphb.setSjgl4(String.format("%1$.2f",rs.getFloat("sjgl4")/1000));
				}catch(Exception ex){}
				try{
					swphb.setSjgl5(String.format("%1$.2f",rs.getFloat("sjgl5")/1000));
				}catch(Exception ex){}
				try{
					swphb.setSjfl1(String.format("%1$.2f",rs.getFloat("sjfl1")/1000));
				}catch(Exception ex){}
				try{
					swphb.setSjfl2(String.format("%1$.2f",rs.getFloat("sjfl2")/1000));
				}catch(Exception ex){}
				try{
					swphb.setGlchangliang(String.format("%1$.2f",rs.getFloat("glchangliang")/1000));
				}catch(Exception ex){}
				
				//配比值
				swphb.setLlgl1(String.format("%1$.1f",rs.getDouble("llgl1")/1000));
				swphb.setLlgl2(String.format("%1$.1f",rs.getDouble("llgl2")/1000));
				swphb.setLlgl3(String.format("%1$.1f",rs.getDouble("llgl3")/1000));
				swphb.setLlgl4(String.format("%1$.1f",rs.getDouble("llgl4")/1000));
				swphb.setLlgl5(String.format("%1$.1f",rs.getDouble("llgl5")/1000));
				swphb.setLlfl1(String.format("%1$.1f",rs.getDouble("llfl1")/1000));
				swphb.setLlfl2(String.format("%1$.1f",rs.getDouble("llfl2")/1000));
				
				//偏差百分比
				if(StringUtil.Null2Blank(rs.getString("llgl1")).length()>0 && rs.getDouble("llgl1")>0){
					swphb.setPersjgl1(String.format("%1$.2f",(Double.parseDouble(swphb.getSjgl1())-Double.parseDouble(swphb.getLlgl1()))*100/Double.parseDouble(swphb.getLlgl1())));
				}else{
					swphb.setPersjgl1("0.00");
				}
				if(StringUtil.Null2Blank(rs.getString("llgl2")).length()>0 && rs.getDouble("llgl2")>0){
					swphb.setPersjgl2(String.format("%1$.2f",(Double.parseDouble(swphb.getSjgl2())-Double.parseDouble(swphb.getLlgl2()))*100/Double.parseDouble(swphb.getLlgl2())));
				}else{
					swphb.setPersjgl2("0.00");
				}
				if(StringUtil.Null2Blank(rs.getString("llgl3")).length()>0 && rs.getDouble("llgl3")>0){
					swphb.setPersjgl3(String.format("%1$.2f",(Double.parseDouble(swphb.getSjgl3())-Double.parseDouble(swphb.getLlgl3()))*100/Double.parseDouble(swphb.getLlgl3())));
				}else{
					swphb.setPersjgl3("0.00");
				}
				if(StringUtil.Null2Blank(rs.getString("llgl4")).length()>0 && rs.getDouble("llgl4")>0){
					swphb.setPersjgl4(String.format("%1$.2f",(Double.parseDouble(swphb.getSjgl4())-Double.parseDouble(swphb.getLlgl4()))*100/Double.parseDouble(swphb.getLlgl4())));
				}else{
					swphb.setPersjgl4("0.00");
				}
				if(StringUtil.Null2Blank(rs.getString("llgl5")).length()>0 && rs.getDouble("llgl5")>0){
					swphb.setPersjgl5(String.format("%1$.2f",(Double.parseDouble(swphb.getSjgl5())-Double.parseDouble(swphb.getLlgl5()))*100/Double.parseDouble(swphb.getLlgl5())));
				}else{
					swphb.setPersjgl5("0.00");
				}
				if(StringUtil.Null2Blank(rs.getString("llfl1")).length()>0 && rs.getDouble("llfl1")>0){
					swphb.setPersjfl1(String.format("%1$.2f",(Double.parseDouble(swphb.getSjfl1())-Double.parseDouble(swphb.getLlfl1()))*100/Double.parseDouble(swphb.getLlfl1())));
				}else{
					swphb.setPersjfl1("0.00");
				}
				if(StringUtil.Null2Blank(rs.getString("llfl2")).length()>0 && rs.getDouble("llfl2")>0){
					swphb.setPersjfl2(String.format("%1$.2f",(Double.parseDouble(swphb.getSjfl2())-Double.parseDouble(swphb.getLlfl2()))*100/Double.parseDouble(swphb.getLlfl2())));
				}else{
					swphb.setPersjfl2("0.00");
				}
				swphb.setShebeibianhao(rs.getString("shebeibianhao"));
			
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (Exception e) {}
			try {
				st.close();
			} catch (Exception e1) {}
			try {
				con.close();
			} catch (Exception e) {}
		}
		return swphb;
	}
	
	private void appendmanualSql(StringBuffer sql) {
		sql.append("SELECT Max(shebeibianhao) as shebeibianhao,");
		sql.append("SUM(CAST((CASE WHEN (sjgl1 IS NULL) OR (sjgl1 = '') ");
		sql.append("THEN '0' ELSE sjgl1 END) AS numeric(38, 2))) AS sjgl1,");
		sql.append("SUM(CAST((CASE WHEN (sjgl2 IS NULL) OR (sjgl2 = '') ");
		sql.append("THEN '0' ELSE sjgl2 END) AS numeric(38, 2))) AS sjgl2,");
		sql.append("SUM(CAST((CASE WHEN (sjgl3 IS NULL) OR (sjgl3 = '') ");
		sql.append("THEN '0' ELSE sjgl3 END) AS numeric(38, 2))) AS sjgl3,");
		sql.append("SUM(CAST((CASE WHEN (sjgl4 IS NULL) OR (sjgl4 = '') ");
		sql.append("THEN '0' ELSE sjgl4 END) AS numeric(38, 2))) AS sjgl4,");		
		sql.append("SUM(CAST((CASE WHEN (sjgl5 IS NULL) OR (sjgl5 = '') ");
		sql.append("THEN '0' ELSE sjgl5 END) AS numeric(38, 2))) AS sjgl5,");
		sql.append("SUM(CAST((CASE WHEN (sjfl1 IS NULL) OR (sjfl1 = '') ");
		sql.append("THEN '0' ELSE sjfl1 END) AS numeric(38, 2))) AS sjfl1,");	
		sql.append("SUM(CAST((CASE WHEN (sjfl2 IS NULL) OR (sjfl2 = '') ");
		sql.append("THEN '0' ELSE sjfl2 END) AS numeric(38, 2))) AS sjfl2,");
		//理论值
		sql.append("SUM(CAST((CASE WHEN (llgl1 IS NULL) OR (llgl1 = '')");
		sql.append("THEN '0' ELSE llgl1 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (glchangliang IS NULL)  ");
		sql.append("THEN '0' ELSE glchangliang END) AS numeric(38, 2))) AS llgl1,");
		
		sql.append("SUM(CAST((CASE WHEN (llgl2 IS NULL) OR (llgl2 = '')");
		sql.append("THEN '0' ELSE llgl2 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (glchangliang IS NULL)  ");
		sql.append("THEN '0' ELSE glchangliang END) AS numeric(38, 2))) AS llgl2,");
		
		sql.append("SUM(CAST((CASE WHEN (llgl3 IS NULL) OR (llgl3 = '')");
		sql.append("THEN '0' ELSE llgl3 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (glchangliang IS NULL)  ");
		sql.append("THEN '0' ELSE glchangliang END) AS numeric(38, 2))) AS llgl3,");
		
		sql.append("SUM(CAST((CASE WHEN (llgl4 IS NULL) OR (llgl4 = '')");
		sql.append("THEN '0' ELSE llgl4 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (glchangliang IS NULL)  ");
		sql.append("THEN '0' ELSE glchangliang END) AS numeric(38, 2))) AS llgl4,");
		
		sql.append("SUM(CAST((CASE WHEN (llgl5 IS NULL) OR (llgl5 = '')");
		sql.append("THEN '0' ELSE llgl5 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (glchangliang IS NULL)  ");
		sql.append("THEN '0' ELSE glchangliang END) AS numeric(38, 2))) AS llgl5,");
		
		sql.append("SUM(CAST((CASE WHEN (llf1 IS NULL) OR (llfl1 = '')");
		sql.append("THEN '0' ELSE llfl1 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (glchangliang IS NULL)  ");
		sql.append("THEN '0' ELSE glchangliang END) AS numeric(38, 2))) AS llfl1,");
		
		sql.append("SUM(CAST((CASE WHEN (llfl2 IS NULL) OR (llfl2 = '')");
		sql.append("THEN '0' ELSE llfl2 END) AS numeric(38, 2))/100*");
		sql.append("CAST((CASE WHEN (glchangliang IS NULL)  ");
		sql.append("THEN '0' ELSE glchangliang END) AS numeric(38, 2))) AS llfl2,");
		
		sql.append("SUM(CAST((CASE WHEN (glchangliang IS NULL)  ");
		sql.append("THEN '0' ELSE glchangliang END) AS numeric(38, 2))) AS glchangliang");
				
//		sql.append("SUM(glchangliang) AS changliang");
	}
}



